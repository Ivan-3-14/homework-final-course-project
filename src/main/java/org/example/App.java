package org.example;

import org.example.dao.PersonDAOImp;
import org.example.dto.PersonDTO;
import org.example.utils.JDBCResource;

import java.sql.*;
import java.util.List;

import static org.example.utils.ConstantContainer.*;

public class App {
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(JDBCResource.getUrl(),
                JDBCResource.getUser(),
                JDBCResource.getPassword())) {

            PersonDAOImp personDAOImp = new PersonDAOImp();
            List<PersonDTO> listOfPerson = personDAOImp.getAll(connection);

            System.out.println("Start list: ");
            listOfPerson.forEach(System.out::println);

            personDAOImp.save(connection, 31,1000,"MP1212346","Vitebsk",
                    Date.valueOf("1993-01-18"), Time.valueOf("12:30:00"), "utc");
            System.out.println("After save: ");
            listOfPerson = personDAOImp.getAll(connection);
            listOfPerson.forEach(System.out::println);

            PersonDTO personDTO = personDAOImp.get(connection, 21);
            if (personDTO == null) {
                System.out.println("This person not found!");
            } else {
                System.out.println("You get: ");
                System.out.println(personDTO);
            }
            personDTO.setAge(21);
             personDTO.setAddress("new Address");
            personDAOImp.update(connection, personDTO, 19);
            System.out.println("After update: ");
            listOfPerson = personDAOImp.getAll(connection);
            listOfPerson.forEach(System.out::println);

            personDAOImp.delete(connection, 37);
            System.out.println("After delete: ");
            listOfPerson = personDAOImp.getAll(connection);
            listOfPerson.forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createDatabase(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_DATABASE_COMMAND);
            statement.executeUpdate(CREATE_TABLE_COMMAND);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}