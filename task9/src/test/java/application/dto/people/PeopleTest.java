package application.dto.people;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static application.dao.daoClasses.testUtils.MockUtils.getPeople;

class PeopleTest {

    @Test
    void setId() {
        People people = getPeople();
        people.setId(1);
        Assert.assertEquals(1, people.getId(), 0);
    }

    @Test
    void setName() {
        People people = getPeople();
        people.setName("John");
        Assert.assertEquals("name not equals", people.getName(), "John");
    }

    @Test
    void setSurname() {
        People people = getPeople();
        people.setSurname("Malcovich");
        Assert.assertEquals("surname not equals", people.getSurname(), "Malcovich");
    }

    @Test
    void setAge() {
        People people = getPeople();
        people.setAge(93);
        Assert.assertEquals(93, people.getAge(), 0);
    }

    @Test
    void builder() {
        People people1 = People.builder()
                .name("Vasya")
                .surname("Rogov")
                .age(33)
                .build();

        People people2 = people1;
        Assert.assertEquals("builder not equals", people1, people2);
    }

    @Test
    void testToString() {
        People people = getPeople();
        Assert.assertEquals("to string not equals",
                people.toString(),
                "People(id=0, name=Mike, surname=Vazovsky, age=20)"
        );
    }

    @Test
    void getId() {
        People people = getPeople();
        people.setId(1);
        Assert.assertEquals( people.getId(),1, 0);
    }

    @Test
    void getName() {
        People people = getPeople();
        people.setName("John");
        Assert.assertEquals("name not equals", people.getName(), "John");
    }

    @Test
    void getSurname() {
        People people = getPeople();
        people.setSurname("Malcovich");
        Assert.assertEquals("surname not equals", people.getSurname(), "Malcovich");
    }

    @Test
    void getAge() {
        People people = getPeople();
        people.setAge(93);
        Assert.assertEquals(93, people.getAge(), 0);
    }
}