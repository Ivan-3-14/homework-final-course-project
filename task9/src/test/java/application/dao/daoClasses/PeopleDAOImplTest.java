package application.dao.daoClasses;

import application.dao.daoClasses.testUtils.MockUtils;
import application.dto.people.People;
import application.utils.hibernate.HibernateUtil;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeopleDAOImplTest {

    @AfterClass
    public static void closeConnection(){
        HibernateUtil.close();
    }

    private final EntityManager entityManager = HibernateUtil.getEntityManager();
    private final PeopleDAOImpl peopleDAO = new PeopleDAOImpl(entityManager);
    private final People people1 = MockUtils.getPeople();
    private final People people2 = MockUtils.getPeople2();

    @Test
    void getAll() {
        peopleDAO.create(people1);
        peopleDAO.create(people2);
        List<People> peopleList = peopleDAO.getAll();
        int countOfPeople = Math.toIntExact(MockUtils.getDataBaseCount(entityManager, people1));
        Assert.assertEquals(java.util.Optional.of(countOfPeople), java.util.Optional.of(peopleList.size()));
    }
}