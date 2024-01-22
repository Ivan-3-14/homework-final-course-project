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
            List<PersonDTO> listOfPerson = personDAOImp.getAll(connection, TABLE_NAME);

            System.out.println("Start list: ");
            listOfPerson.forEach(System.out::println);

            personDAOImp.save(connection, 31,1000,"MP1212346","Vitebsk",
                    new Date(93, 0, 18), new Time(12, 30, 0), "utc");
            System.out.println("After save: ");
            listOfPerson = personDAOImp.getAll(connection, TABLE_NAME);
            listOfPerson.forEach(System.out::println);

            PersonDTO personDTO = personDAOImp.get(connection, TABLE_NAME, 21);
            System.out.println("You get: ");
            System.out.println(personDTO);

            personDAOImp.update(connection, personDTO, 18);
            System.out.println("After update: ");
            listOfPerson = personDAOImp.getAll(connection, TABLE_NAME);
            listOfPerson.forEach(System.out::println);

            personDAOImp.delete(connection, 21);
            System.out.println("After delete: ");
            listOfPerson = personDAOImp.getAll(connection, TABLE_NAME);
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