package application.servicelevel.services.impl;

import application.datalevel.DAO.implementations.auto.AutoPriceDAOImpl;
import application.datalevel.DAO.implementations.order.OrderDAOImpl;
import application.datalevel.DAO.implementations.concrete.ConcretePriceDAOImpl;
import application.datalevel.DAO.implementations.concrete.MobilityDAOImpl;
import application.datalevel.DAO.interfaces.auto.AutoPriceDAO;
import application.datalevel.DAO.interfaces.order.OrderDAO;
import application.datalevel.DAO.interfaces.concrete.ConcretePriceDAO;
import application.datalevel.DAO.interfaces.concrete.MobilityDAO;

import application.servicelevel.DTO.OrderInformDTO;
import application.servicelevel.DTO.objectDTO.BuildingObjectDTO;
import application.servicelevel.DTO.orderDTO.OrderDTO;
import application.servicelevel.DTO.usersDTO.UserDTO;
import application.servicelevel.services.interfaces.OrderServiceInt;
import application.servicelevel.services.interfaces.UserServiceInt;
import application.utils.functionalinterface.MyInterfaceToDAO;
import application.utils.functionalinterface.UtilsInterface;
import application.utils.mappers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static application.utils.constant.ConstantsContainer.*;

public class OrderService implements OrderServiceInt {

    private final OrderDAO orderDAOImpl = new OrderDAOImpl();
    private final OrderMapper orderMapper = new OrderMapper();
    private final MobilityDAO mobilityDAOImpl = new MobilityDAOImpl();
    private final BuildingObjectService buildingObjectService = new BuildingObjectService();
    private final ConcretePriceDAO concretePriceDAOImpl = new ConcretePriceDAOImpl();
    private final AutoPriceDAO autoPriceDAOImpl = new AutoPriceDAOImpl();
    private final UserServiceInt userService = new UserService();

    public OrderDTO creatOrder(OrderDTO orderDTO) {
        MyInterfaceToDAO<OrderDTO> betweenBeginAndCommitted = () ->
                orderMapper.entityToDTO(orderDAOImpl.create(orderMapper.dtoToEntity(orderDTO)));
        return UtilsInterface.superMethodInterface(betweenBeginAndCommitted, orderDAOImpl.getEntityManager());
    }

    public OrderDTO checkAndCreateOrder(UserDTO userDTO,
                                        OrderDTO orderDTO,
                                        BuildingObjectDTO buildingObjectDTO,
                                        String name,
                                        String surname,
                                        String numberOfPhone
    ) {

        MyInterfaceToDAO<OrderDTO> betweenBeginAndCommitted = () -> {
            UserDTO user = userService.checkExistUser(userDTO, name, surname, numberOfPhone);
            buildingObjectDTO.setUser(user);
            orderDTO.setUserDTO(user);
            BuildingObjectDTO buildingObject = buildingObjectService.createBuildingObject(buildingObjectDTO);
            Double coast = getOrderCoast(orderDTO, buildingObjectDTO);
            orderDTO.setCost(coast);
            orderDTO.setBuildingObjectDTO(buildingObject);

            return orderMapper.entityToDTO(orderDAOImpl.create(orderMapper.dtoToEntity(orderDTO)));
        };
        return UtilsInterface.superMethodInterface(betweenBeginAndCommitted, orderDAOImpl.getEntityManager());
    }

    public OrderDTO updateOrder(OrderInformDTO orderInformDTO, int id) {
        MyInterfaceToDAO<OrderDTO> betweenBeginAndCommitted = () -> {
            OrderDTO oldOrder = readOrderDTO(id);
            AtomicReference<OrderDTO> order = new AtomicReference<>();

            if (orderInformDTO != null) {
                orderInformDTO.setUserDTO(oldOrder.getUserDTO());
                Optional<UserDTO> userDTO = Optional.ofNullable(orderInformDTO.getUserDTO());
                Optional<OrderDTO> orderDTO = Optional.ofNullable(orderInformDTO.getOrderDTO());
                Optional<BuildingObjectDTO> buildingObjectDTO = Optional.ofNullable(orderInformDTO.getBuildingObjectDTO());

                userDTO.ifPresent(u -> {
                    u.setRole(oldOrder.getUserDTO().getRole());
                    u.setTelephoneNumber(orderInformDTO.getTelephoneNumber());
                    u.setSurname(orderInformDTO.getSurname());
                    u.setName(orderInformDTO.getName());
                    UserDTO user = userService.updateUser(oldOrder.getUserDTO().getId(), u);

                    buildingObjectDTO.ifPresent(b -> {
                        b.setUser(user);
                        BuildingObjectDTO buildingObject = buildingObjectService.updateBuildingObject(
                                oldOrder.getBuildingObjectDTO().getId(), b);

                        orderDTO.ifPresent(o -> {
                            o.setBuildingObjectDTO(buildingObject);
                            o.setUserDTO(user);
                            o.setCost(getOrderCoast(o, buildingObject));
                            order.set(o);
                        });
                    });
                });
            }
            return orderMapper.entityToDTO(orderDAOImpl.update(id, orderMapper.dtoToEntity(order.get())));
        };
        return UtilsInterface.superMethodInterface(betweenBeginAndCommitted, orderDAOImpl.getEntityManager());
    }

    public OrderDTO readOrderDTO(int id) {
        return orderMapper.entityToDTO(orderDAOImpl.read(id));
    }

    public List<OrderDTO> orderPaginationList(int id, int start, int limit) {
        List<OrderDTO> list = new ArrayList<>();
        orderDAOImpl.findAll(id, start, limit).forEach(f -> list.add(orderMapper.entityToDTO(f)));
        return list;
    }

    public Integer deleteOrder(int orderId) {
        MyInterfaceToDAO<Integer> betweenBeginAndCommitted = () -> orderDAOImpl.delete(orderId);
        return UtilsInterface.superMethodInterface(betweenBeginAndCommitted, orderDAOImpl.getEntityManager());
    }

    public int countOfPage(int id) {
        int result;
        int listSize = orderDAOImpl.countOfPersonalOrders(id);
        if ((listSize % ROW_IN_PAGE_ORDERS) > ZERO) {
            result = (listSize / ROW_IN_PAGE_ORDERS) + FIRST_PAGE;
        } else {
            result = listSize / ROW_IN_PAGE_ORDERS;
        }
        return result;
    }

    private Double getOrderCoast(OrderDTO orderDTO, BuildingObjectDTO buildingObjectDTO) {
        Double concretePrice = concretePriceDAOImpl.getConcretePriceByConcreteAndGrade(
                orderDTO.getConcreteDTO().getId(),
                orderDTO.getConcreteGradeDTO().getId()
        );
        Double autoPrice = autoPriceDAOImpl.getAutoPriceByMobilityAndVolumeOfConcrete(
                mobilityDAOImpl.getMobilityByMobilityValue(orderDTO.getMobilityDTO().getMobilityValue()),
                orderDTO.getVolumeOfConcrete(), buildingObjectDTO.getDistanceToObject()
        );
        return concretePrice * orderDTO.getVolumeOfConcrete() + autoPrice;
    }
}
