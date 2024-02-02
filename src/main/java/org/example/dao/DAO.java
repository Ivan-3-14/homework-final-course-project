package org.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAO <T> {
    T save(Connection connection, T personDTO) throws SQLException;
    T get(Connection connection, int id) throws SQLException;
    void update(Connection connection, T t, int id) throws SQLException;
    int delete(Connection connection, int id) throws SQLException;
}
