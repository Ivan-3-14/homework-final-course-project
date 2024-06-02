package application.services.impl;

import application.DTO.concreteDTO.MobilityDTO;
import application.DTO.filtersDTO.FilterOrderInform;
import application.DTO.filtersDTO.OrderPaginationFilter;
import application.DTO.objectDTO.BuildingObjectDTO;
import application.DTO.orderDTO.OrderDTO;
import application.DTO.usersDTO.ManagerDTO;
import application.DTO.usersDTO.UserDTO;
import application.converter.*;
import application.entity.autotransportation.AutoCapacity;
import application.entity.concreteentities.Concrete;
import application.entity.concreteentities.ConcreteGrade;
import application.entity.concreteentities.Mobility;
import application.entity.enums.aggregate.Aggregate;
import application.entity.enums.cartype.CarType;
import application.entity.enums.grades.GradesConcrete;
import application.entity.enums.mobilityvalue.MobilityValue;
import application.entity.enums.orderstatus.OrderStatus;
import application.entity.enums.roles.Roles;
import application.entity.order.Order;
import application.entity.users.Manager;
import application.repository.*;
import application.services.interfaces.BuildingObjectService;
import application.services.interfaces.OrderService;
import application.services.interfaces.UserService;
import application.utils.ModelUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import static application.utils.Constant.*;
import static application.logger.LoggerPrinter.logPrint;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final BuildingObjectService buildingObjectService;

    private final MobilityMapper mobilityMapper = Mappers.getMapper(MobilityMapper.class);
    private final ConcreteGradeMapper concreteGradeMapper = Mappers.getMapper(ConcreteGradeMapper.class);
    private final ConcreteMapper concreteMapper = Mappers.getMapper(ConcreteMapper.class);
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
    private final ManagerMapper managerMapper = Mappers.getMapper(ManagerMapper.class);

    private final OrderRepository orderRepository;
    private final MobilityRepository mobilityRepository;
    private final ConcreteRepository concreteRepository;
    private final ConcreteGradeRepository concreteGradeRepository;
    private final AutoCapacityRepository autoCapacityRepository;
    private final ConcretePriceRepository concretePriceRepository;
    private final ManagerRepository managerRepository;

    private final ModelUtils modelUtils;

    @Override
    public FilterOrderInform createNewOrder(FilterOrderInform filterOrderInform, Model model) {

        FilterOrderInform filterOrder = getFilterDTOFromFront(filterOrderInform, model);

        if (filterOrder.getErrorMessage() == null) {
            OrderDTO orderDTO = filterOrder.getOrderDTO();
            BuildingObjectDTO buildingObjectDTO = filterOrder.getBuildingObjectDTO();
            UserDTO userDTO = userService.checkExistUser(filterOrder.getUserDTO());
            Concrete concrete = filterOrder.getConcrete();
            ConcreteGrade concreteGrade = filterOrder.getGrade();

            buildingObjectDTO.setUser(userDTO);
            orderDTO.setUserDTO(userDTO);
            BuildingObjectDTO buildingObject = buildingObjectService.createBuildingObject(buildingObjectDTO,
                    concrete, concreteGrade);

            Double coast = getOrderCoast(orderDTO, buildingObject);
            orderDTO.setCost(coast);
            orderDTO.setBuildingObjectDTO(buildingObject);
            OrderDTO order = orderMapper.toDTO(orderRepository.save(orderMapper.toEntity(orderDTO)));

            filterOrder.setUserDTO(userDTO);
            filterOrder.setBuildingObjectDTO(buildingObject);
            filterOrder.setOrderDTO(order);
            log.info(logPrint(CREATE_NEW_ORDER_SUCCESSFUL));
        }
        return filterOrder;
    }

    @Override
    public OrderDTO readOrder(Long id) {
        return orderMapper.toDTO(orderRepository.findById(id).orElse(new Order()));
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderPaginationFilter readOrders(String search, Long currentUserId, int page) {
        OrderPaginationFilter orderPaginationFilter =
                orderRepository.findAllOrderWithFilter(search, currentUserId, page);
        orderPaginationFilter.setCountOfNewOrders(orderRepository.countOrderByOrderStatus(OrderStatus.NEW));
        return orderPaginationFilter;
    }

    @Override
    public OrderPaginationFilter getOrderPaginationFilter(Long currentUserId, int currentPage,
                                                          int countOrdersAtPage) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        int totalPage = 0;

        Page<OrderDTO> listOrders = getAllOrdersByUserId(currentUserId,
                PageRequest.of(currentPage, countOrdersAtPage));

        if (listOrders != null) {
            log.info(logPrint(GET_ALL_ORDERS_BY_USER_ID_SUCCESSFUL));
        orderDTOList = listOrders.toList();
        totalPage = listOrders.getTotalPages();
        }

        return OrderPaginationFilter.builder()
                .listOrders(orderDTOList)
                .currentPage(currentPage)
                .countOfTotalPage(totalPage)
                .build();
    }

    @Override
    public OrderPaginationFilter getOrderPaginationFilterForManager(Long currentUserId, int currentPage,
                                                                    int countOrdersAtPage) {
        Long managerId = userService.readUser(currentUserId).getManager().getId();

        Page<OrderDTO> listOrders = orderRepository.findAllByManagerId(managerId,
                PageRequest.of(currentPage, countOrdersAtPage)).map(orderMapper::toDTO);
        return OrderPaginationFilter.builder()
                .listOrders(listOrders.toList())
                .currentPage(currentPage)
                .countOfTotalPage(listOrders.getTotalPages())
                .countOfNewOrders(orderRepository.countOrderByOrderStatus(OrderStatus.NEW))
                .build();
    }

    @Override
    public OrderPaginationFilter getNewOrderList(int currentPage,
                                                 int countOrdersAtPage, OrderStatus orderStatus) {
        Page<OrderDTO> listOrders = orderRepository.findAllByOrderStatus(orderStatus,
                PageRequest.of(currentPage, countOrdersAtPage)).map(orderMapper::toDTO);
        log.info(logPrint(GET_NEW_ORDER_LIST_END));
        return OrderPaginationFilter.builder()
                .listOrders(listOrders.toList())
                .currentPage(currentPage)
                .countOfTotalPage(listOrders.getTotalPages())
                .build();
    }

    @Override
    public void acceptOrder(Long orderId, Long userId) {
        Manager manager = managerRepository.getById(userService.readUser(userId).getManager().getId());
        Order order = orderRepository.getById(orderId);
        order.setOrderStatus(OrderStatus.IN_WORK);
        order.setManager(manager);
        orderRepository.save(order);
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
        OrderStatus orderStatus = filterOrderInform.getOrderStatus();

        if (orderStatus == null) {
            orderStatus = OrderStatus.NEW;
        }

        BuildingObjectDTO buildingObjectDTO;
        OrderDTO orderDTO;
        UserDTO userDTO;
        ManagerDTO managerDTO = getManagerDTO(filterOrderInform);

        if (OrderStatus.NEW.equals(orderStatus)) {
            managerDTO = null;
        }
        modelUtils.addAttributesToModel(model, nameOfObject, distanceToObject, volumeOfConcrete, comment, name,
                surname, telephoneNumber);

        try {
            int grade = Integer.parseInt(gradeConcrete);
            int mobility = Integer.parseInt(mobilityConcrete);
            double distance = DOUBLE_ZERO;

            if (distanceToObject != null) {
                distance = distanceToObject;
            }
            if (checkAggregate(filterOrderInform, model, aggregate, grade)) {
                log.info(logPrint(CHECK_AGGREGATE_FALSE));
                return filterOrderInform;
            }
            Date date = orderDateConverter(dateOfDelivery);
            if (checkDate(filterOrderInform, model, date)) {
                log.info(logPrint(CHECK_DATE_FALSE));
                return filterOrderInform;
            }
            Time time = orderTimeConverter(timeOfDelivery);
            if (checkTime(filterOrderInform, model, time)) {
                log.info(logPrint(CHECK_TIME_FALSE));
                return filterOrderInform;
            }

            MobilityDTO mobilityDTO = orderMobilityConverter(mobility);
            ConcreteGrade concreteGrade = orderGradeConverter(grade);
            Concrete concrete = orderConcreteConverter(aggregate);
            filterOrderInform.setConcrete(concrete);
            filterOrderInform.setGrade(concreteGrade);

            userDTO = UserDTO.builder()
                    .name(name)
                    .surname(surname)
                    .telephoneNumber(telephoneNumber)
                    .build();
            userDTO = checkUserRole(userDTO);

            buildingObjectDTO = BuildingObjectDTO.builder()
                    .nameOfObject(nameOfObject)
                    .distanceToObject(distance)
                    .build();

            orderDTO = OrderDTO.builder()
                    .id(filterOrderInform.getCurrentOrderId())
                    .concreteDTO(concreteMapper.toDTO(concrete))
                    .concreteGradeDTO(concreteGradeMapper.toDTO(concreteGrade))
                    .mobilityDTO(mobilityDTO)
                    .volumeOfConcrete(volumeOfConcrete)
                    .dateOfDelivery(date)
                    .timeOfDelivery(time)
                    .comment(comment)
                    .orderStatus(orderStatus)
                    .managerDTO(managerDTO)
                    .orderTimeCreate(new Timestamp(System.currentTimeMillis()))
                    .build();
        } catch (ParseException e) {
            log.info(logPrint(FILTER_DTO_FROM_FRONT_CATCH));
            filterOrderInform.setErrorMessage(INCORRECT_DATE_OR_TIME);
            filterOrderInform.setModel(model);
            return filterOrderInform;
        }

        filterOrderInform.setBuildingObjectDTO(buildingObjectDTO);
        filterOrderInform.setUserDTO(userDTO);
        filterOrderInform.setOrderDTO(orderDTO);
        return filterOrderInform;
    }

    @Transactional(readOnly = true)
    protected Page<OrderDTO> getAllOrdersByUserId(Long userId, Pageable paging) {
        return orderRepository.findAllByUserId(userId, paging).map(orderMapper::toDTO);
    }

    @Transactional(readOnly = true)
    protected MobilityDTO orderMobilityConverter(int mobility) {
        MobilityValue orderMobility = MobilityValue.getMobilityFromIntValue(mobility);
        return mobilityMapper.toDTO(mobilityRepository.getByMobilityValue(orderMobility));
    }

    @Transactional(readOnly = true)
    protected ConcreteGrade orderGradeConverter(int grade) {
        GradesConcrete orderGrade = GradesConcrete.getGradeFromValue(grade);
        return concreteGradeRepository.getByGradesConcrete(orderGrade);
    }

    @Transactional(readOnly = true)
    protected Concrete orderConcreteConverter(String aggregate) {
        Aggregate orderAggregate = Aggregate.getAggregateByValue(aggregate);
        return concreteRepository.getByAggregate(orderAggregate);
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

    @Transactional(readOnly = true)
    protected ManagerDTO getManagerDTO(FilterOrderInform filterOrderInform) {
        if (filterOrderInform != null) {
            Long managerId = filterOrderInform.getManagerID();
            if (managerId != null) {
                log.info(logPrint(GET_MANAGER_DTO_SUCCESSFUL_END));
                return managerMapper.toDTO(managerRepository.getById(managerId));
            }
        }
        return null;
    }

    private boolean checkTime(FilterOrderInform filterOrderInform, Model model, Time time) {
        if (time.compareTo(Time.valueOf(START_WORK_TIME)) < ZERO
                || time.compareTo(Time.valueOf(END_WORK_TIME)) > ZERO) {
            filterOrderInform.setModel(model);
            filterOrderInform.setErrorMessage(INCORRECT_TIME);
            log.info(logPrint(CHECK_TIME_SUCCESSFUL_END));
            return true;
        }
        return false;
    }

    private boolean checkDate(FilterOrderInform filterOrderInform, Model model, Date date) {
        if (date.compareTo(new java.util.Date()) < ZERO) {
            filterOrderInform.setModel(model);
            filterOrderInform.setErrorMessage(INCORRECT_DATE);
            log.info(logPrint(CHECK_DATE_SUCCESSFUL_END));
            return true;
        }
        return false;
    }

    private boolean checkAggregate(FilterOrderInform filterOrderInform, Model model, String aggregate, int grade) {
        if (Aggregate.GRAVEL.toString().equals(aggregate) && grade > GRAVEL_BORDER) {
            filterOrderInform.setModel(model);
            filterOrderInform.setErrorMessage(INCORRECT_AGGREGATE);
            log.info(logPrint(CHECK_AGGREGATE_SUCCESSFUL_END));
            return true;
        }
        return false;
    }

    private UserDTO checkUserRole(UserDTO userDTO) {
        if (userDTO != null && Roles.MANAGER.equals(userDTO.getRole())) {
            log.info(logPrint(CHECK_USER_ROLE));
            return null;
        }
        return userDTO;
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
}
