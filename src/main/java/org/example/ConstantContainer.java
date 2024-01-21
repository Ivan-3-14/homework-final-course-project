package org.example;

public class ConstantContainer {
    public static final int AGE_FOR_FILTER = 21;
    public static final String DATABASE_NAME = "people";
    public static final String TABLE_NAME = "person";
    public static final String INSERT_PERSON_PREPARE_STATEMENT =
            "insert into people.person(age, salary, passport, address,dateOfBirthday, dateTimeCreate, timeToLunch, letter)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
}
