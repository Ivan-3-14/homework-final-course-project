package org.example;

import java.sql.*;

import static org.example.ConstantContainer.*;

public class App {
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(JDBCResource.getUrl(),
                JDBCResource.getUser(),
                JDBCResource.getPassword())) {

            RecordAndExtract.recordPerson(connection);
            RecordAndExtract.extractPerson(connection, TABLE_NAME, AGE_FOR_FILTER);
            PrinterDatabaseInfo.printTableMeta(connection, DATABASE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}