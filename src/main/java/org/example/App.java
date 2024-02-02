package org.example;

import org.example.dao.PersonDAOImp;
import org.example.dto.PersonDTO;
import org.example.dto.utils.JDBCResource;

import java.sql.*;
import java.util.List;

import static org.example.dto.utils.ConstantContainer.*;

public class App {
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(JDBCResource.getUrl(),
                JDBCResource.getUser(),
                JDBCResource.getPassword())) {

            createTable(connection);

            PersonDAOImp personDAOImp = new PersonDAOImp();
            List<PersonDTO> listOfPerson = personDAOImp.getAll(connection);

            System.out.println("Start list: ");
            listOfPerson.forEach(System.out::println);

            PersonDTO personDTO = PersonDTO.builder()
                    .age(31)
                    .salary(1000.1)
                    .passport("MP1212346")
                    .address("Vitebsk")
                    .dateOfBirthday(Date.valueOf("1993-01-18"))
                    .timeToLunch(Time.valueOf("12:30:00"))
                    .dateTimeCreate(new Timestamp(System.currentTimeMillis()))
                    .letter("utc")
                    .build();

            personDAOImp.save(connection, personDTO);
            personDAOImp.save(connection, personDTO);
            personDAOImp.save(connection, personDTO);


            System.out.println("After save: ");
            listOfPerson = personDAOImp.getAll(connection);
            listOfPerson.forEach(System.out::println);

            PersonDTO personDTO1 = personDAOImp.get(connection, 3);
            if (personDTO1 == null) {
                System.out.println("This person not found!");
            } else {
                System.out.println("You get: ");
                System.out.println(personDTO1);
            }

            assert personDTO1 != null;
            personDTO1.setAge(1);
            personDTO1.setAddress("new Address");
            personDAOImp.update(connection, personDTO1, 1);
            System.out.println("After update: ");
            listOfPerson = personDAOImp.getAll(connection);
            listOfPerson.forEach(System.out::println);

            personDAOImp.delete(connection, 2);
            System.out.println("After delete: ");
            listOfPerson = personDAOImp.getAll(connection);
            listOfPerson.forEach(System.out::println);

            dropTable(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_COMMAND);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void dropTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_PEOPLE_TABLE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}