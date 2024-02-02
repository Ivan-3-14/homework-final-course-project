package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.ConstantContainer.*;

public class PrinterDatabaseInfo {

    public static void printTableMeta(Connection connection, String databaseName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs = metaData.getTables(databaseName, null, null, null);
        List<String> tables = new ArrayList();
        while (rs.next()) {
            tables.add(rs.getString(NAME_OF_TABLE_FOR_PRINT_META));
        }
        getColumnsMetadata(tables, metaData);
        rs.close();
    }

    private static void getColumnsMetadata(List<String> tables, DatabaseMetaData metaData) throws SQLException {
        ResultSet rs;
        for (String actualTable : tables) {
            rs = metaData.getColumns(null, null, actualTable, null);
            System.out.println("table name: " + actualTable.toUpperCase());
            int count = 1;
            while (rs.next()) {

                String result = String.format(
                        "%d%s%s%s%s%s%s",
                        count,
                        NAME,
                        rs.getString(NAME_OF_COLUMNS_FOR_PRINT_META),
                        TYPE,
                        rs.getString(TYPE_OF_COLUMNS_FOR_PRINT_META),
                        SIZE,
                        rs.getString(SIZE_OF_COLUMNS_FOR_PRINT_META)
                        );
                System.out.println(result);
                count++;
            }
        }
    }
}
