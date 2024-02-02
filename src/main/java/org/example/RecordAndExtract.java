package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.ConstantContainer.*;

public class RecordAndExtract {

    public static void insertPerson(PreparedStatement ps,
                                    int age,
                                    double salary,
                                    String passport,
                                    String address,
                                    Date dateOfBirthday,
                                    Time time,
                                    String letter) throws SQLException {
        ps.setInt(1, age);
        ps.setDouble(2, salary);
        ps.setString(3, passport);
        ps.setString(4, address);
        ps.setDate(5, dateOfBirthday);
        ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
        ps.setTime(7, time);
        ps.setString(8, letter);

        ps.executeUpdate();
    }


    public static void recordPerson(Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSON_PREPARE_STATEMENT)) {
            connection.setAutoCommit(false);
            try {
                insertPerson(preparedStatement, 33, 1100, "MC2222727", "Minsk",
                        Date.valueOf("1991-02-11"), Time.valueOf("13:00:00"), "hello");
                insertPerson(preparedStatement, 31, 1300, "MR3291717", "Brest",
                        Date.valueOf("1993-01-18"), Time.valueOf("12:30:00"), "wtf");
                insertPerson(preparedStatement, 22, 0, "MG1313134", "Grodno",
                        Date.valueOf("2001-09-30"), Time.valueOf("11:00:00"), "akcio");
                insertPerson(preparedStatement, 18, 1000, "MR7171456", "Gomel",
                        Date.valueOf("2005-04-03"),Time.valueOf("12:00:00"), "room");
                insertPerson(preparedStatement, 20, 1700, "MC3222721", "Mogilev",
                        Date.valueOf("2003-06-11"), Time.valueOf("11:30:00"), "hello word");
                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
            }
        }
    }

    public static void extractPerson(Connection connection, String tableName, int age) throws SQLException {
        String SELECT_PERSON_OVER_21_BY_DATE_TIME_CREATE = String.format(
                "%s%s%s%s%s%d%s%s",
                SELECT_ALL_FROM,
                tableName,
                WHERE,
                AGE,
                MORE,
                age,
                ORDER_BY,
                D_T_C
        );
        List<PersonDTO> persons = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_PERSON_OVER_21_BY_DATE_TIME_CREATE);

        while (resultSet.next()) {
            PersonDTO person = PersonDTO.builder()
                    .age(resultSet.getInt("age"))
                    .salary(resultSet.getDouble("salary"))
                    .passport(resultSet.getString("passport"))
                    .address(resultSet.getString("address"))
                    .dateOfBirthday(resultSet.getDate("date_of_birthday"))
                    .dateTimeCreate(resultSet.getTimestamp("date_time_create"))
                    .time(resultSet.getTime("time_to_lunch"))
                    .letter(resultSet.getString("letter"))
                    .build();

            persons.add(person);
        }
        persons.forEach(System.out::println);
    }
}
