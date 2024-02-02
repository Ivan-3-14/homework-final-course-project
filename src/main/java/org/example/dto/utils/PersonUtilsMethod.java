package org.example.dto.utils;

import org.example.dto.PersonDTO;

import java.sql.*;

public class PersonUtilsMethod {

    public static void insertPerson(PreparedStatement ps,
                                     PersonDTO personDTO) throws SQLException {
        ps.setInt(1, personDTO.getAge());
        ps.setDouble(2, personDTO.getSalary());
        ps.setString(3, personDTO.getPassport());
        ps.setString(4, personDTO.getAddress());
        ps.setDate(5, personDTO.getDateOfBirthday());
        ps.setTimestamp(6, personDTO.getDateTimeCreate());
        ps.setTime(7, personDTO.getTimeToLunch());
        ps.setString(8, personDTO.getLetter());
    }

    public static PersonDTO buildPersonDTO(ResultSet resultSet) throws SQLException {
        return PersonDTO.builder()
                .id(resultSet.getInt(1))
                .age(resultSet.getInt(2))
                .salary(resultSet.getDouble(3))
                .passport(resultSet.getString(4))
                .address(resultSet.getString(5))
                .dateOfBirthday(resultSet.getDate(6))
                .dateTimeCreate(resultSet.getTimestamp(7))
                .timeToLunch(resultSet.getTime(8))
                .letter(resultSet.getString(9))
                .build();
    }
}
