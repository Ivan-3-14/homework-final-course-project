package application.services.impl;

import application.DTO.concreteDTO.ConcreteDTO;
import application.DTO.concreteDTO.ConcreteGradeDTO;
import application.DTO.concreteDTO.MobilityDTO;
import application.DTO.filtersDTO.FilterOrderInform;
import application.DTO.filtersDTO.OrderPaginationFilter;
import application.DTO.objectDTO.BuildingObjectDTO;
import application.DTO.orderDTO.OrderDTO;
import application.DTO.usersDTO.UserDTO;
import application.converter.ConcreteGradeMapper;
import application.converter.ConcreteMapper;
import application.converter.MobilityMapper;
import application.converter.OrderMapper;
import application.entity.autotransportation.AutoCapacity;
import application.entity.concreteentities.Mobility;
import application.entity.enums.aggregate.Aggregate;
import application.entity.enums.cartype.CarType;
import application.entity.enums.grades.GradesConcrete;
import application.entity.enums.mobilityvalue.MobilityValue;
import application.entity.enums.orderstatus.OrderStatus;
import application.entity.enums.roles.Roles;
import application.entity.order.Order;
import application.repository.*;
import application.services.interfaces.BuildingObjectService;
import application.services.interfaces.OrderService;
import application.services.interfaces.UserService;
import application.utils.ModelUtils;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static application.utils.Constant.*;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final BuildingObjectService buildingObjectService;

    private final MobilityMapper mobilityMapper = Mappers.getMapper(MobilityMapper.class);
    private final ConcreteGradeMapper concreteGradeMapper = Mappers.getMapper(ConcreteGradeMapper.class);
    private final ConcreteMapper concreteMapper = Mappers.getMapper(ConcreteMapper.class);
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    private final OrderRepository orderRepository;
    private final MobilityRepository mobilityRepository;
    private final ConcreteRepository concreteRepository;
    private final ConcreteGradeRepository concreteGradeRepository;
    private final AutoCapacityRepository autoCapacityRepository;
    private final ConcretePriceRepository concretePriceRepository;

    private final ModelUtils modelUtils;

    public FilterOrderInform createNewOrder(FilterOrderInform filterOrderInform, Model model) {

        FilterOrderInform filterOrder = getFilterDTOFromFront(filterOrderInform, model);

        if (filterOrder.getErrorMessage() == null) {
            OrderDTO orderDTO = filterOrder.getOrderDTO();
            BuildingObjectDTO buildingObjectDTO = filterOrder.getBuildingObjectDTO();
            UserDTO userDTO = userService.checkExistUser(filterOrder.getUserDTO());

            buildingObjectDTO.setUser(userDTO);
            orderDTO.setUserDTO(userDTO);
            BuildingObjectDTO buildingObject = buildingObjectService.createBuildingObject(buildingObjectDTO);

            Double coast = getOrderCoast(orderDTO, buildingObject);
            orderDTO.setCost(coast);
            orderDTO.setBuildingObjectDTO(buildingObject);
            OrderDTO order = orderMapper.toDTO(orderRepository.save(orderMapper.toEntity(orderDTO)));

            filterOrder.setUserDTO(userDTO);
            filterOrder.setBuildingObjectDTO(buildingObject);
            filterOrder.setOrderDTO(order);
        }

        return filterOrder;
    }

    public OrderDTO readOrder(Long id) {
        return orderMapper.toDTO(orderRepository.findById(id).orElse(new Order()));
    }

    public void deleteOrder(Long orderId) {
            orderRepository.deleteById(orderId);
    }

    public OrderPaginationFilter readOrders(String search, Long currentUserId, int page) {
        return orderRepository.findAllOrderWithFilter(search, currentUserId, page);
    }

    public OrderPaginationFilter getOrderPaginationFilter(Long currentUserId, int currentPage,
                                                          int countOrdersAtPage) {
        Page<OrderDTO> listOrders = getAllOrdersByUserId(currentUserId,
                PageRequest.of(currentPage, countOrdersAtPage));

        return OrderPaginationFilter.builder()
                .listOrders(listOrders.toList())
                .currentPage(currentPage)
                .countOfTotalPage(listOrders.getTotalPages())
                .build();
    }

    private FilterOrderInform getFilterDTOFromFront(FilterOrderInform filterOrderInform, Model model) {

        String aggregate = filterOrderInform.getAggregate();
        String gradeConcrete = filterOrderInform.getConcreteGrade();
        String mobilityConcrete = filterOrderInform.getMobility();

        String nameOfObject = filterOrderInform.getNameOfObject();
        Double distanceToObject = filterOrderInform.getDistanceToObject();
        Double volumeOfConcrete = filterOrderInform.getVolumeOfConcrete();
        String dateOfDelivery = filterOrderInform.getDateOfDelivery();
        String timeOfDelivery = filterOrderInform.getTimeOfDelivery();
        String comment = filterOrderInform.getComment();

        String name = filterOrderInform.getName();
        String surname = filterOrderInform.getSurname();
        String telephoneNumber = filterOrderInform.getTelephoneNumber();

        BuildingObjectDTO buildingObjectDTO;
        OrderDTO orderDTO;
        UserDTO userDTO;

        modelUtils.addAttributesToModel(model, nameOfObject, distanceToObject, volumeOfConcrete, comment, name,
                surname, telephoneNumber);

        try {
            int grade = Integer.parseInt(gradeConcrete);
            int mobility = Integer.parseInt(mobilityConcrete);
            double distance = DOUBLE_ZERO;

            if (distanceToObject != null) {
                distance = distanceToObject;
            }

            if (checkAggregate(filterOrderInform, model, aggregate, grade)) return filterOrderInform;

            Date date = orderDateConverter(dateOfDelivery);
            if (checkDate(filterOrderInform, model, date)) return filterOrderInform;

            Time time = orderTimeConverter(timeOfDelivery);
            if (checkTime(filterOrderInform, model, time)) return filterOrderInform;

            MobilityDTO mobilityDTO = orderMobilityConverter(mobility);
            ConcreteGradeDTO concreteGradeDTO = orderGradeConverter(grade);
            ConcreteDTO concreteDTO = orderConcreteConverter(aggregate);

            userDTO = UserDTO.builder()
                    .name(name)
                    .surname(surname)
                    .telephoneNumber(telephoneNumber)
                    .build();
            userDTO = checkUserRole(userDTO);

            buildingObjectDTO = BuildingObjectDTO.builder()
                    .nameOfObject(nameOfObject)
                    .distanceToObject(distance)
                    .concreteGradeSet(new HashSet<>())
                    .concretesSet(new HashSet<>())
                    .build();
            buildingObjectDTO.getConcretesSet().add(concreteDTO);
            buildingObjectDTO.getConcreteGradeSet().add(concreteGradeDTO);

            orderDTO = OrderDTO.builder()
                    .id(filterOrderInform.getCurrentOrderId())
                    .concreteDTO(concreteDTO)
                    .concreteGradeDTO(concreteGradeDTO)
                    .mobilityDTO(mobilityDTO)
                    .volumeOfConcrete(volumeOfConcrete)
                    .dateOfDelivery(date)
                    .timeOfDelivery(time)
                    .comment(comment)
                    .orderStatus(OrderStatus.NEW)
                    .orderTimeCreate(new Timestamp(System.currentTimeMillis()))
                    .build();

        } catch (ParseException e) {
            filterOrderInform.setErrorMessage(INCORRECT_DATE_OR_TIME);
            filterOrderInform.setModel(model);
            return filterOrderInform;
        }

        filterOrderInform.setBuildingObjectDTO(buildingObjectDTO);
        filterOrderInform.setUserDTO(userDTO);
        filterOrderInform.setOrderDTO(orderDTO);
        return filterOrderInform;
    }

    private boolean checkTime(FilterOrderInform filterOrderInform, Model model, Time time) {
        if (time.compareTo(Time.valueOf(START_WORK_TIME)) < ZERO
                || time.compareTo(Time.valueOf(END_WORK_TIME)) > ZERO) {
            filterOrderInform.setModel(model);
            filterOrderInform.setErrorMessage(INCORRECT_TIME);
            return true;
        }
        return false;
    }

    private boolean checkDate(FilterOrderInform filterOrderInform, Model model, Date date) {
        if (date.compareTo(new java.util.Date()) < ZERO) {
            filterOrderInform.setModel(model);
            filterOrderInform.setErrorMessage(INCORRECT_DATE);
            return true;
        }
        return false;
    }

    private boolean checkAggregate(FilterOrderInform filterOrderInform, Model model, String aggregate, int grade) {
        if (Aggregate.GRAVEL.toString().equals(aggregate) && grade > GRAVEL_BORDER) {
            filterOrderInform.setModel(model);
            filterOrderInform.setErrorMessage(INCORRECT_AGGREGATE);
            return true;
        }
        return false;
    }
    @Transactional(readOnly = true)
    protected Page<OrderDTO> getAllOrdersByUserId(Long userId, Pageable paging) {
        Page<OrderDTO> page = orderRepository.findAllByUserId(userId, paging).map(orderMapper::toDTO);
        return page;
    }

    private Date orderDateConverter(String dateOfDelivery) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(MY_DATE_FORMAT, Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone(UTC));
        return new Date(formatter.parse(dateOfDelivery).getTime());
    }

    private Time orderTimeConverter(String timeOfDelivery) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(MY_TIME_FORMAT);
        return new java.sql.Time(dateFormat.parse(timeOfDelivery).getTime());
    }

    @Transactional(readOnly = true)
    protected MobilityDTO orderMobilityConverter(int mobility) {
        MobilityValue orderMobility = MobilityValue.getMobilityFromIntValue(mobility);
        return mobilityMapper.toDTO(mobilityRepository.getByMobilityValue(orderMobility));
    }

    @Transactional(readOnly = true)
    protected ConcreteGradeDTO orderGradeConverter(int grade) {
        GradesConcrete orderGrade = GradesConcrete.getGradeFromValue(grade);
        return concreteGradeMapper.toDTO(concreteGradeRepository.getByGradesConcrete(orderGrade));
    }

    @Transactional(readOnly = true)
    protected ConcreteDTO orderConcreteConverter(String aggregate) {
        Aggregate orderAggregate = Aggregate.getAggregateByValue(aggregate);
        return concreteMapper.toDTO(concreteRepository.getByAggregate(orderAggregate));
    }

    private UserDTO checkUserRole(UserDTO userDTO) {
        if (userDTO != null && Roles.MANAGER.equals(userDTO.getRole())) {
            return null;
        }
        return userDTO;
    }

    @Transactional(readOnly = true)
    protected Double getOrderCoast(OrderDTO orderDTO, BuildingObjectDTO buildingObjectDTO) {
        Double concretePrice = concretePriceRepository.findByConcreteIdAndConcreteGradeId(
                orderDTO.getConcreteDTO().getId(),
                orderDTO.getConcreteGradeDTO().getId()
        ).getPrice();
        Double autoPrice = getAutoPriceByMobilityAndVolumeOfConcrete(
                mobilityRepository.getByMobilityValue(orderDTO.getMobilityDTO().getMobilityValue()),
                orderDTO.getVolumeOfConcrete(), buildingObjectDTO.getDistanceToObject()
        );
        return concretePrice * orderDTO.getVolumeOfConcrete() + autoPrice;
    }

    @Transactional(readOnly = true)
    protected Double getAutoPriceByMobilityAndVolumeOfConcrete(Mobility mobility, Double volume, Double distance) {
        CarType carType;
        int coefficient;
        double result;
        int price = ZERO;

        if (mobility.getMobilityValue().getIntValueOfMobility() < MOBILITY_CONSTANT) {
            carType = CarType.DUMP_TRUCK;
        } else {
            carType = CarType.CONCRETE_MIXER_TRUCK;
        }

        List<AutoCapacity> existCapacity = autoCapacityRepository.findAllByCarType(carType);

        if (distance < TRANSPORT_CONSTANT) {
            coefficient = existCapacity.get(ZERO).getDeliveryCoefficient().getDeliveryCoefficientBfr50();
        } else {
            coefficient = existCapacity.get(ZERO).getDeliveryCoefficient().getDeliveryCoefficientAft50();
        }

        if (volume <= existCapacity.get(existCapacity.size() - ONE).getAutoCapacity()) {
            for (var e : existCapacity) {
                if (volume <= e.getAutoCapacity()) {
                    return (autoCapacityRepository.findByAutoCapacityAndCarType(e.getAutoCapacity(), carType)
                            .getAutoPrice().getPrice() + coefficient * distance);
                }
            }
        } else {
            result = volume / existCapacity.get(ZERO).getAutoCapacity();
            if (result % ONE > ZERO) {
                price = (int) result;
                price++;
            } else {
                price = (int) result;
            }
        }
        var temp = autoCapacityRepository.findByAutoCapacityAndCarType(existCapacity.get(ZERO).getAutoCapacity(),
                carType)
                .getAutoPrice().getPrice() + coefficient * distance;
        return (price * temp);
    }
}
