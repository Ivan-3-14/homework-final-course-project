package application.servicelevel.services.impl;

import application.datalevel.DAO.implementations.order.OrderDAOImpl;
import application.datalevel.DAO.implementations.users.ManagerDAOImpl;
import application.datalevel.DAO.interfaces.order.OrderDAO;
import application.datalevel.DAO.interfaces.users.ManagerDAO;
import application.datalevel.entities.order.Order;
import application.datalevel.entities.users.Manager;
import application.servicelevel.DTO.orderDTO.OrderDTO;
import application.servicelevel.DTO.usersDTO.ManagerDTO;
import application.utils.functionalinterface.MyInterfaceToDAO;
import application.utils.functionalinterface.UtilsInterface;
import application.utils.mappers.ManagerForUserMapper;
import application.utils.mappers.OrderMapper;

import java.util.List;

public class ManagerService {

    private static final ManagerService INSTANCE = new ManagerService();
    private final OrderDAO orderDAOImpl = new OrderDAOImpl();
    private final OrderMapper orderMapper = new OrderMapper();
    private final ManagerDAO managerDAOImpl = new ManagerDAOImpl();
    private final ManagerForUserMapper managerForUserMapper = new ManagerForUserMapper();

    private ManagerService() {
    }

    public ManagerDTO readManager(int id) {
        MyInterfaceToDAO<ManagerDTO> betweenBeginAndCommitted = () -> {
            Manager manager = managerDAOImpl.read(id);
            if (manager == null) {
                return null;
            }
            return managerForUserMapper.entityToDTO(manager);
        };
        return UtilsInterface.superMethodInterface(betweenBeginAndCommitted, managerDAOImpl.getEntityManager());
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order result = orderDAOImpl.create(orderMapper.dtoToEntity(orderDTO));
        return orderMapper.entityToDTO(result);
    }

    public OrderDTO readOrder(int id) {
        return orderMapper.entityToDTO(orderDAOImpl.read(id));
    }

    public OrderDTO updateOrder(int id, OrderDTO orderDTO, int managerId) {
        Order temp = orderMapper.dtoToEntity(orderDTO);
        if (temp == null || temp.getManager().getId() != managerId) {
            return null;
        }
        Order result = orderDAOImpl.update(id, temp);
        return orderMapper.entityToDTO(result);
    }

    public Integer deleteOrder(int id, int managerId) {
        return orderDAOImpl.delete(id);
    }

    public List<Order> getAllOrders() {
       return orderDAOImpl.getAllOrders();
    }

    public static ManagerService getINSTANCE() {
        return INSTANCE;
    }
}
