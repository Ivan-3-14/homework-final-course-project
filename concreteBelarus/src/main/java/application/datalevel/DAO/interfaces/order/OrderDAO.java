package application.datalevel.DAO.interfaces.order;

import application.datalevel.DAO.interfaces.DAO;
import application.datalevel.entities.order.Order;

import javax.persistence.EntityManager;
import java.util.List;

public interface OrderDAO extends DAO<Order> {

    List<Order> getAllOrders();

    List<Order> findAll(int id, int start, int limit);

    int countOfPersonalOrders(int id);

    EntityManager getEntityManager();
}
