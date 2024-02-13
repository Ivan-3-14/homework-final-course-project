package application.dto.address;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static application.dao.daoClasses.testUtils.MockUtils.getAddress;

class AddressTest {

    @Test
    void setId() {
        Address address = getAddress();
        address.setId(1);
        Assert.assertEquals(1, address.getId(), 0);
    }

    @Test
    void setStreet() {
        Address address = getAddress();
        address.setStreet("new street");
        Assert.assertEquals("street not equals", address.getStreet(), "new street");
    }

    @Test
    void setHouse() {
        Address address = getAddress();
        address.setHouse(168);
        Assert.assertEquals(168, address.getHouse(), 0);
    }

    @Test
    void builder() {
        Address address1 = Address.builder()
                .street("My street")
                .house(145)
                .build();

        Address address2 = address1;
        Assert.assertEquals("builder not equals", address1, address2);

    }

    @Test
    void testToString() {
        Address address = getAddress();
        Assert.assertEquals("to string not equals",
                address.toString(),
                "Address(id=0, street=Nemiga, house=15)"
        );
    }

    @Test
    void getId() {
        Address address = getAddress();
        address.setId(1);
        Assert.assertEquals(address.getId(), 1, 0);
    }

    @Test
    void getStreet() {
        Address address = getAddress();
        address.setStreet("new street");
        Assert.assertEquals("street not equals", address.getStreet(), "new street");
    }

    @Test
    void getHouse() {
        Address address = getAddress();
        address.setHouse(168);
        Assert.assertEquals(address.getHouse(),168,  0);
    }
}