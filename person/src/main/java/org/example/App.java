package org.example;

import java.sql.*;

import static org.example.ConstantContainer.*;

public class App {
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(JDBCResource.getUrl(),
                JDBCResource.getUser(),
                JDBCResource.getPassword())) {

            createTable(connection);

            RecordAndExtract.recordPerson(connection);
            RecordAndExtract.extractPerson(connection, TABLE_NAME, AGE_FOR_FILTER);
            PrinterDatabaseInfo.printTableMeta(connection, DATABASE_NAME);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void createTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_PERSON_TABLE);
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