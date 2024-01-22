package org.example.utils;

public class ConstantContainer {

    public static final String CREATE_DATABASE_COMMAND = "create database people";
    public static final String CREATE_TABLE_COMMAND = "create table people.person "
            + "(id INT PRIMARY KEY AUTO_INCREMENT,"
            + "age INT, salary DECIMAL, "
            + "passport CHAR(10), "
            + "address VARCHAR(200), "
            + "date_of_birthday DATE, "
            + "date_time_create TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
            + "time_to_lunch TIME, "
            + "letter TEXT)";
    public static final String TABLE_NAME = "person";
    public static final String INSERT_PERSON_PREPARE_STATEMENT =
            "insert into people.person(age, salary, passport, address,dateOfBirthday, dateTimeCreate, timeToLunch, letter)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_QUERY = "update people.person set age = ?, salary = ?, passport = ?, address = ?, "
            + "dateOfBirthday = ?, timeToLunch = ?, letter = ? where id = ?";
    public static final String DELETE_BY_ID = "delete from " + TABLE_NAME +" where id = ?";
    public static final String SELECT_ALL_FROM = "select * from ";

    private ConstantContainer(){}

}
