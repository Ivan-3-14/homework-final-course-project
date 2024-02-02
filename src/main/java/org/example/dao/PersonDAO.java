package org.example.dao;

import org.example.dto.PersonDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PersonDAO extends DAO<PersonDTO>{
    List<PersonDTO> getAll(Connection connection) throws SQLException;
}
