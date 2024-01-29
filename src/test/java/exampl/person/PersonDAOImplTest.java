package exampl.person;

import exampl.enums.Parameters;
import exampl.utils.HibernateUtil;
import exampl.utils.MockUtils;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.junit.Assert;

import static exampl.utils.ConstantContainer.*;

import javax.persistence.EntityManager;

public class PersonDAOImplTest extends TestCase {

    public void testSave() {
        Person person = MockUtils.getTestPerson();
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Session session = entityManager.unwrap(Session.class);
        PersonDAOImpl personDAO = new PersonDAOImpl(session);

        personDAO.save(person);

        Person entityPerson = entityManager.find(Person.class, 1);
        myAssert(entityPerson, person);

        session.close();
        entityManager.close();
    }

    public void testFindByParameter() {
        Person person1 = MockUtils.getTestPerson();
        Person person = MockUtils.getTestPerson2();
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Session session = entityManager.unwrap(Session.class);
        PersonDAOImpl personDAO = new PersonDAOImpl(session);


        personDAO.save(person);
        personDAO.save(person1);
        /**
         * Тест поиска по дате и времени создания имеет особенность - поскольку person и person1 создаются в одно время,
         * положительное завершение теста зависит от очередности записи в БД person и person1. Если сравниваемый объект
         * был записан в БД вторым, то entityPerson должно равняться второму элементу полученного List<Person>, т.е .get(1)
         */
        Person entityPerson = personDAO.findByParameter(Parameters.DATA_TIME_CREATED, EQUALS,
                String.valueOf(person.getDataTimeCreate()), NULL_CONDITION).get(0);
        myAssert(entityPerson, person);

        entityPerson = personDAO.findByParameter(Parameters.AGE, EQUALS, person.getAge(), NULL_CONDITION).get(0);
        myAssert(entityPerson, person);

        entityPerson = personDAO.findByParameter(Parameters.SALARY, EQUALS, person.getSalary(), NULL_CONDITION).get(0);
        myAssert(entityPerson, person);

        entityPerson = personDAO.findByParameter(Parameters.PASSPORT, EQUALS, person.getPassport(), NULL_CONDITION).get(0);
        myAssert(entityPerson, person);

        entityPerson = personDAO.findByParameter(Parameters.ADDRESS, EQUALS, person.getAddress(), NULL_CONDITION).get(0);
        myAssert(entityPerson, person);

        entityPerson = personDAO.findByParameter(Parameters.DATE_OF_BIRTHDAY, EQUALS,
                String.valueOf(person.getDateOfBirthday()), NULL_CONDITION).get(0);
        myAssert(entityPerson, person);

        entityPerson = personDAO.findByParameter(Parameters.TIME_TO_LUNCH, EQUALS,
                String.valueOf(person.getTimeToLunch()), NULL_CONDITION).get(0);
        myAssert(entityPerson, person);

        session.close();
        entityManager.close();
    }

    private void myAssert(Person entityPerson, Person person) {
        Assert.assertNotNull(entityPerson);
        Assert.assertEquals("age not equals", entityPerson.getAge(), person.getAge());
        Assert.assertEquals("salary not equals", entityPerson.getSalary(), person.getSalary());
        Assert.assertEquals("passport not equals", entityPerson.getPassport(), person.getPassport());
        Assert.assertEquals("address not equals", entityPerson.getAddress(), person.getAddress());
        Assert.assertEquals("date of birthday not equals", entityPerson.getDateOfBirthday(), person.getDateOfBirthday());
        Assert.assertEquals("time to lunch not equals", entityPerson.getTimeToLunch(), person.getTimeToLunch());
        Assert.assertEquals("data time create not equals", entityPerson.getDataTimeCreate(), person.getDataTimeCreate());
    }
}