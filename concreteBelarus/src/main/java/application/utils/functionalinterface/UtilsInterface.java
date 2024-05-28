package application.utils.functionalinterface;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import java.io.IOException;

public class UtilsInterface {

    public static <T> T superMethodInterface(MyInterfaceToDAO<T> method, EntityManager entityManager) {
        entityManager.getTransaction().begin();
        T result = null;
        try {
            result = method.betweenBeginAndCommitted();
        } catch (ServletException | IOException | RuntimeException e) {
            entityManager.getTransaction().rollback();
        }
        entityManager.getTransaction().commit();
        return result;
    }
}
