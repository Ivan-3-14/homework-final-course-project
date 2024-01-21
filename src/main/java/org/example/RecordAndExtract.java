package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.ConstantContainer.INSERT_PERSON_PREPARE_STATEMENT;

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
                        new Date(91, 1, 11), new Time(13, 0, 0), "hello");
                insertPerson(preparedStatement, 31, 1300, "MR3291717", "Brest",
                        new Date(93, 0, 18), new Time(12, 30, 0), "wtf");
                insertPerson(preparedStatement, 22, 0, "MG1313134", "Grodno",
                        new Date(101, 8, 30), new Time(11, 0, 0), "akcio");
                insertPerson(preparedStatement, 18, 1000, "MR7171456", "Gomel",
                        new Date(105, 3, 3), new Time(12, 0, 0), "room");
                insertPerson(preparedStatement, 20, 1700, "MC3222721", "Mogilev",
                        new Date(103, 5, 11), new Time(11, 30, 0), "hello word");
                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
            }
        }
    }

    public static void extractPerson(Connection connection, String tableName, int age) throws SQLException {
        String SELECT_PERSON_OVER_21_BY_DATE_TIME_CREATE = "select * from " + tableName + " where age > " + age
                + " order by dateTimeCreate";
        List<PersonDTO> persons = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_PERSON_OVER_21_BY_DATE_TIME_CREATE);

        while (resultSet.next()) {
            PersonDTO person = PersonDTO.builder()
                    .age(resultSet.getInt("age"))
                    .salary(resultSet.getDouble("salary"))
                    .passport(resultSet.getString("passport"))
                    .address(resultSet.getString("address"))
                    .dateOfBirthday(resultSet.getDate("dateOfBirthday"))
                    .dateTimeCreate(resultSet.getTimestamp("dateTimeCreate"))
                    .time(resultSet.getTime("timeToLunch"))
                    .letter(resultSet.getString("letter"))
                    .build();

            persons.add(person);
        }
        persons.forEach(System.out::println);
    }
}
