package application.utils.hibernate;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

class HibernateUtilTest {

    @AfterClass
    public static void closeConnection(){
        HibernateUtil.close();
    }

    @Test
    void getEntityManager() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Assert.assertTrue(entityManager.isOpen());
        entityManager.close();
    }

    @Test
    void close() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        HibernateUtil.close();
        Assert.assertFalse(entityManager.isOpen());
    }
}