package org.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public interface DAO <T> {
    T save(Connection connection,
           int age,
           double salary,
           String passport,
           String address,
           Date dateOfBirthday,
           Time time,
           String letter) throws SQLException;
    T get(Connection connection, int id) throws SQLException;
    void update(Connection connection, T t, int id) throws SQLException;
    int delete(Connection connection, int id) throws SQLException;
}
