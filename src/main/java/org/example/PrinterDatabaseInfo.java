package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrinterDatabaseInfo {

    public static void printTableMeta(Connection connection, String databaseName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs = metaData.getTables(databaseName, null, null, null);
        List tables = new ArrayList();
        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
        }
        getColumnsMetadata(tables, metaData);
    }

    private static void getColumnsMetadata(List<String> tables, DatabaseMetaData metaData) throws SQLException {
        ResultSet rs;
        for (String actualTable : tables) {
            rs = metaData.getColumns(null, null, actualTable, null);
            System.out.println("table name: " + actualTable.toUpperCase());
            int count = 1;
            while (rs.next()) {
                System.out.println(count + "), name: " + rs.getString("COLUMN_NAME") + ", type: "
                        + rs.getString("TYPE_NAME") + ", size: "
                        + rs.getString("COLUMN_SIZE")
                );
                count++;
            }
        }
    }
}
