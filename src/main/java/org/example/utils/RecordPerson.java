package org.example.utils;

import org.example.dto.PersonDTO;

import java.sql.*;

public class RecordPerson {

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
}
