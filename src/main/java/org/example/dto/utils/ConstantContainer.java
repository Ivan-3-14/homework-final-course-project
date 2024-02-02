package org.example.dto.utils;

public class ConstantContainer {

    public static final String CREATE_TABLE_COMMAND = "create table people3.person "
            + "(id INT PRIMARY KEY AUTO_INCREMENT,"
            + "age INT, salary DECIMAL, "
            + "passport CHAR(10), "
            + "address VARCHAR(200), "
            + "date_of_birthday DATE, "
            + "date_time_create TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
            + "time_to_lunch TIME, "
            + "letter TEXT)";
    public static final String TABLE_NAME = "person";
    public static final String DROP_PEOPLE_TABLE = "drop table people3.person";
    public static final String INSERT_PERSON_PREPARE_STATEMENT =
            "insert into people3.person(age, salary, passport, address,date_of_birthday, date_time_create, time_to_lunch, letter)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_QUERY = "update people3.person set age = ?, salary = ?, passport = ?, address = ?, "
            + "date_of_birthday = ?, date_time_create = ?, time_to_lunch = ?, letter = ? where id = ?";
    public static final String DELETE_BY_ID = "delete from " + TABLE_NAME +" where id = ?";
    public static final String SELECT_ALL_FROM = "select * from ";
    public static final String WHERE_ID = " where id = ";

    private ConstantContainer(){}

}
