package org.example;

public class ConstantContainer {
    public static final int AGE_FOR_FILTER = 21;
    public static final String DATABASE_NAME = "people";
    public static final String TABLE_NAME = "person";
    public static final String INSERT_PERSON_PREPARE_STATEMENT =
            "insert into people.person(age, salary, passport, address,dateOfBirthday, dateTimeCreate, timeToLunch, letter)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String NAME_OF_TABLE_FOR_PRINT_META = "TABLE_NAME";
    public static final String NAME_OF_COLUMNS_FOR_PRINT_META = "COLUMN_NAME";
    public static final String TYPE_OF_COLUMNS_FOR_PRINT_META = "TYPE_NAME";
    public static final String SIZE_OF_COLUMNS_FOR_PRINT_META = "COLUMN_SIZE";
}
