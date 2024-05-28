package application.datalevel.DAO.implementations.order;

import application.datalevel.DAO.implementations.DAOImpl;
import application.datalevel.DAO.interfaces.order.OrderDAO;
import application.datalevel.entities.order.Order;
import application.utils.hibernate.HibernateUtils;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static application.utils.constant.ConstantsContainer.*;

public class OrderDAOImpl extends DAOImpl<Order> implements OrderDAO {

    private final Logger logger = Logger.getLogger(OrderDAOImpl.class.getName());

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }

    @Override
    public List<Order> getAllOrders() {
        logger.log(Level.INFO, START_GET_ALL_ORDERS);
        List<Order> list = new ArrayList<>();
        try {
            list = getEntityManager()
                    .createQuery(GET_ALL_ORDERS_QUERY, getEntityClass())
                    .getResultList();
        } catch (NullPointerException e) {
            logger.log(Level.INFO, LIST_OF_ORDERS_EMPTY);
        }
        logger.log(Level.INFO, GET_ALL_ORDERS_SUCCESSFUL);
        return list;
    }

    @Override
    public List<Order> findAll(int id, int start, int limit) {

        String hql = FIND_ALL_ORDERS + id;
        TypedQuery<Order> typedQuery = HibernateUtils.getEntityManager().createQuery(hql, Order.class);
        typedQuery.setFirstResult(limit * (start - FIRST_PAGE));
        typedQuery.setMaxResults(limit);

        return typedQuery.getResultList();
    }

    @Override
    public int countOfPersonalOrders(int id) {
        String hql =  String.format("%s%s", FIND_ALL_ORDERS, id);
        TypedQuery<Order> typedQuery = HibernateUtils.getEntityManager().createQuery(hql, Order.class);
        return typedQuery.getResultList().size();
    }
}
