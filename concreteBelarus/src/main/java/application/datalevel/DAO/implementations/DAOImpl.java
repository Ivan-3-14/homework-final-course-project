package application.datalevel.DAO.implementations;


import application.datalevel.DAO.interfaces.DAO;
import application.utils.functionalinterface.MyInterfaceToDAO;
import application.utils.functionalinterface.UtilsInterface;
import application.utils.hibernate.HibernateUtils;
import application.utils.reflectionutils.ReflectionUtils;

import javax.persistence.EntityManager;
import java.util.logging.Level;
import java.util.logging.Logger;

import static application.utils.constant.ConstantsContainer.*;

public abstract class DAOImpl<T> implements DAO<T> {

    private final EntityManager entityManager = HibernateUtils.getEntityManager();

    private final Logger logger = Logger.getLogger(DAOImpl.class.getName());

    public abstract Class<T> getEntityClass();

    public T create(T object) {
        if (object == null) {
            logger.log(Level.INFO, CREATE_FAILED_MSG);
            return null;
        }
         getEntityManager().persist(object);
        logger.log(Level.INFO, CREATE_SUCCESS_MSG);
        return object;
    }

    public T read(int id) {
        T object = getEntityManager().find(getEntityClass(), id);
        if (object == null) {
            logger.log(Level.INFO, READ_FAILED_MSG);
        }
        logger.log(Level.INFO, READ_SUCCESS_MSG);
        return object;
    }

    public T update(int id, T object) {
        T temp = getEntityManager().find(getEntityClass(), id);
        T result = ReflectionUtils.updateReflection(object, temp, getEntityManager());
        if (result == null) {
            logger.log(Level.INFO, UPDATE_FAILED_MSG);
        }
        logger.log(Level.INFO, UPDATE_SUCCESS_MSG);
        return result;
    }

    public Integer delete(int id) {
            T object = getEntityManager().find(getEntityClass(), id);
            getEntityManager().remove(object);
        if (object == null) {
            logger.log(Level.INFO, DELETE_FAILED_MSG);
            return -1;
        }
        logger.log(Level.INFO, DELETE_SUCCESS_MSG);
        return id;

    }

    public EntityManager getEntityManager() {
        if (!entityManager.isOpen()) {
            return HibernateUtils.getEntityManager();
        }
        return entityManager;
    }

}

