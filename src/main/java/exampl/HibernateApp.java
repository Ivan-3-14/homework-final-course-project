package exampl;

import exampl.enums.Parameters;
import exampl.person.Person;
import exampl.person.PersonDAOImpl;
import exampl.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

import static exampl.utils.ConstantContainer.*;

public class HibernateApp {
    public static void main(String[] args) {

        Person person1 = Person.createdPerson(
                31,
                2040.0,
                "MC3270001",
                "Minsk",
                "1992-08-08",
                "12:00:00"
        );

        Person person2 = Person.createdPerson(
                33,
                1500.5,
                "MC6245687",
                "Grodno",
                "1990-06-11",
                "11:30:00"
        );

        Person person3 = Person.createdPerson(
                20,
                1100.9,
                "MR7112468",
                "Vitebsk",
                "2004-01-11",
                "12:30:00"
        );

        Person person4 = Person.createdPerson(
                23,
                1500.5,
                "MK2743691",
                "Brest",
                "2000-12-28",
                "13:00:00"
        );

        Person person5 = Person.createdPerson(
                16,
                0.0,
                "MR1235182",
                "Gomel",
                "1990-06-11",
                "11:30:00"
        );


        EntityManager entityManager = HibernateUtil.getEntityManager();
        Session session = entityManager.unwrap(Session.class);

        PersonDAOImpl personDAO = new PersonDAOImpl(session);

        personDAO.save(person1);
        personDAO.save(person2);
        personDAO.save(person3);
        personDAO.save(person4);
        personDAO.save(person5);


        List<Person> persons = personDAO.findByParameter(Parameters.AGE, MORE, 21, ORDER_BY_DTC);
        persons.forEach(System.out::println);

        session.close();
        entityManager.close();
    }
}
