package application.dao.daoClasses.testUtils;

import application.dto.address.Address;
import application.dto.people.People;
import org.junit.Assert;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static application.dao.daoClasses.testUtils.MockConstant.*;

public class MockUtils {

    public static People getPeople(){
        return People
                .builder()
                .name(PEOPLE_NAME)
                .surname(PEOPLE_SURNAME)
                .age(PEOPLE_AGE)
                .build();
    }

    public static People getPeople2(){
        return People
                .builder()
                .name(PEOPLE_NAME2)
                .surname(PEOPLE_SURNAME2)
                .age(PEOPLE_AGE2)
                .build();
    }

    public static Address getAddress() {
        return Address
                .builder()
                .street(ADDRESS_STREET)
                .house(ADDRESS_HOUSE)
                .build();
    }

    public static Address getAddress2() {
        return Address
                .builder()
                .street(ADDRESS_STREET2)
                .house(ADDRESS_HOUSE2)
                .build();
    }


    public static void  assertPeople(People entityPeople, People people) {
        Assert.assertNotNull(entityPeople);
        Assert.assertEquals("name not equals", entityPeople.getName(), people.getName());
        Assert.assertEquals("surname not equals", entityPeople.getSurname(), people.getSurname());
        Assert.assertEquals("age not equals", entityPeople.getAge(), people.getAge());
    }

    public static void assertAddress(Address entityAddress, Address address) {
        Assert.assertNotNull(entityAddress);
        Assert.assertEquals("street not equals", entityAddress.getStreet(), address.getStreet());
        Assert.assertEquals("house not equals", entityAddress.getHouse(), address.getHouse());
    }

    public static Long getDataBaseCount(EntityManager entityManager, Object tClass){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> queryEntity = builder.createQuery(Long.class);
        queryEntity.select(builder.count(queryEntity.from(tClass.getClass())));
        return entityManager.createQuery(queryEntity).getSingleResult();
    }
    public static ByteArrayOutputStream getByteArrayOutputStream() {
        ByteArrayOutputStream mockOut = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(mockOut);
        System.setOut(newOut);
        return mockOut;
    }
}

