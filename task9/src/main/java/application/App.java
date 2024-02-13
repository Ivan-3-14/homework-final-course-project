package application;

import application.dao.daoClasses.AddressDAOImpl;
import application.dao.daoClasses.PeopleDAOImpl;
import application.dto.address.Address;
import application.dto.people.People;
import application.utils.hibernate.HibernateUtil;

import javax.persistence.EntityManager;

public class App {
    public static void main(String[] args) {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        PeopleDAOImpl peopleDAO = new PeopleDAOImpl(entityManager);
        AddressDAOImpl addressDAO = new AddressDAOImpl(entityManager);

        Address address1 = Address.builder()
                .street("New street")
                .house(1)
                .build();

        Address address2 = Address.builder()
                .street("pr. Skoriny")
                .house(15)
                .build();

        Address address3 = Address.builder()
                .street("My street")
                .house(30)
                .build();

        Address address4 = Address.builder()
                .street("Nahimova")
                .house(12)
                .build();

        Address address5 = Address.builder()
                .street("Marksa")
                .house(19)
                .build();

        People people1 = People.builder()
                .name("Roman")
                .surname("Obuhov")
                .age(21)
                .build();

        People people2 = People.builder()
                .name("Victor")
                .surname("Suvorov")
                .age(56)
                .build();

        People people3 = People.builder()
                .name("Ronald")
                .surname("Wisely")
                .age(11)
                .build();

        People people4 = People.builder()
                .name("Artur")
                .surname("Pirozkov")
                .age(46)
                .build();

        People people5 = People.builder()
                .name("Donald")
                .surname("Trump")
                .age(73)
                .build();

        peopleDAO.create(people1);
        peopleDAO.create(people2);
        peopleDAO.create(people3);
        peopleDAO.create(people4);
        peopleDAO.create(people5);

        addressDAO.create(address1);
        addressDAO.create(address2);
        addressDAO.create(address3);
        addressDAO.create(address4);
        addressDAO.create(address5);

        Address newHouse1 = addressDAO.read(4);
        Address newHouse2 = addressDAO.read(5);
        newHouse1.setHouse(newHouse1.getHouse() + 1);
        newHouse2.setHouse(newHouse2.getHouse() + 1);
        addressDAO.update(newHouse1.getId(), address4);
        addressDAO.update(newHouse2.getId(), address5);

        People newAge1 = peopleDAO.read(4);
        People newAge2 = peopleDAO.read(5);
        newAge1.setAge(newAge1.getAge() + 2);
        newAge2.setAge(newAge2.getAge() + 2);
        peopleDAO.update(newAge1.getId(), newAge1);
        peopleDAO.update(newAge2.getId(), newAge2);

        addressDAO.delete(1);
        peopleDAO.delete(1);

        HibernateUtil.close();
    }
}
