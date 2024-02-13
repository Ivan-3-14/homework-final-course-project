package application.dao.daoClasses;

import application.dao.daoClasses.testUtils.MockUtils;
import application.dto.address.Address;
import application.dto.people.People;
import application.utils.hibernate.HibernateUtil;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

class DAOImplTest {

    private final EntityManager entityManager = HibernateUtil.getEntityManager();
    private final PeopleDAOImpl peopleDAO = new PeopleDAOImpl(entityManager);
    private final AddressDAOImpl addressDAO = new AddressDAOImpl(entityManager);
    private final People people = MockUtils.getPeople();
    private final Address address= MockUtils.getAddress();

    @BeforeEach
    void setUp() {
        peopleDAO.create(MockUtils.getPeople());
        addressDAO.create(MockUtils.getAddress());
    }

    @AfterClass
    public static void closeConnection(){
        HibernateUtil.close();
    }

    @Test
    void getEntityClass() {
        Assert.assertEquals(addressDAO.getEntityClass(), Address.class);
        Assert.assertEquals(peopleDAO.getEntityClass(), People.class);
    }

    @Test
    void create() {
        Long countPeopleBefore = MockUtils.getDataBaseCount(entityManager, people);
        Long countAddressBefore = MockUtils.getDataBaseCount(entityManager, address);

        peopleDAO.create(people);
        addressDAO.create(address);

        Long countPeopleAfter = MockUtils.getDataBaseCount(entityManager, people);
        Long countAddressAfter = MockUtils.getDataBaseCount(entityManager, address);

        Assert.assertNotNull(countPeopleAfter);
        Assert.assertNotNull(countAddressAfter);
        Assert.assertEquals(java.util.Optional.of(countPeopleBefore + 1), java.util.Optional.of(countPeopleAfter));
        Assert.assertEquals(java.util.Optional.of(countAddressBefore + 1), java.util.Optional.of(countAddressAfter));

        People entityPeople = peopleDAO.read(1);
        Address entityAddress = addressDAO.read(1);

        MockUtils.assertPeople(entityPeople, people);
        MockUtils.assertAddress(entityAddress, address);
    }

    @Test
    void read() {
        peopleDAO.create(people);
        peopleDAO.create(people);
        addressDAO.create(address);
        addressDAO.create(address);

        People entityPeople = peopleDAO.read(3);
        Address entityAddress = addressDAO.read(3);

        MockUtils.assertPeople(entityPeople, people);
        MockUtils.assertAddress(entityAddress, address);

        Assert.assertTrue(entityManager.isOpen());
        Assert.assertFalse(entityManager.getTransaction().isActive());
    }

    @Test
    void update() {
        People people2 = MockUtils.getPeople2();
        Address address2= MockUtils.getAddress2();

        peopleDAO.update(2, people2);
        addressDAO.update(2, address2);

        People entityPeople = peopleDAO.read(2);
        Address entityAddress = addressDAO.read(2);

        MockUtils.assertPeople(entityPeople, people2);
        MockUtils.assertAddress(entityAddress, address2);
    }

    @Test
    void delete() {
        Long countPeopleBefore = MockUtils.getDataBaseCount(entityManager, people);
        Long countAddressBefore = MockUtils.getDataBaseCount(entityManager, address);

        peopleDAO.delete(1);
        addressDAO.delete(1);

        Long countPeopleAfter = MockUtils.getDataBaseCount(entityManager, people);
        Long countAddressAfter = MockUtils.getDataBaseCount(entityManager, address);

        Assert.assertNotNull(countPeopleBefore);
        Assert.assertNotNull(countAddressBefore);
        Assert.assertEquals(java.util.Optional.of(countPeopleBefore - 1), java.util.Optional.of(countPeopleAfter));
        Assert.assertEquals(java.util.Optional.of(countAddressBefore - 1), java.util.Optional.of(countAddressAfter));
    }
}