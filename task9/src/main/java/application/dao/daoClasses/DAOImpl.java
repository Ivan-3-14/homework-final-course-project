package application.dao.daoClasses;

import application.dao.daoInterfaces.DAO;
import application.utils.printer.Printer;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.util.Arrays;

import static application.utils.printer.Printer.*;

public abstract class DAOImpl<T> implements DAO<T> {

    protected final EntityManager entityManager;

    public DAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Class<T> getEntityClass();

    public void create(T object) {
        if (object != null) {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
            printSuccessfulSaveMethodMessage();
        } else {
            printNullPointerMessage();
        }
    }

    public T read(int id) {
        entityManager.getTransaction().begin();
        T object = entityManager.find(getEntityClass(), id);
        entityManager.getTransaction().commit();
        return object;
    }

    public T update(int id, T object) {
        entityManager.getTransaction().begin();

        T result = entityManager.find(getEntityClass(), id);
        if (checkFindById(result)) {
            entityManager.getTransaction().commit();
            return null;
        }

        Field[] objFields = getEntityClass().getDeclaredFields();
        Arrays.stream(objFields)
                .peek(q -> q.setAccessible(true))
                .filter(field -> field.isAnnotationPresent(Column.class))
                .forEach(field -> {
                    try {
                        field.set(result, field.get(object));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        entityManager.merge(result);
        entityManager.getTransaction().commit();
        printSuccessfulUpdateMethodMessage();
        return object;
    }

    public Integer delete(int id) {
        entityManager.getTransaction().begin();
        T object = entityManager.find(getEntityClass(), id);
        if (checkFindById(object)) {
            entityManager.getTransaction().commit();
            return null;
        }
        entityManager.remove(object);
        entityManager.getTransaction().commit();
        printSuccessfulDeleteMethodMessage();
        return id;
    }

    private boolean checkFindById(T object) {
        if (null == object) {
            Printer.printNotFoundMessage();
            return true;
        }
        return false;
    }
}
