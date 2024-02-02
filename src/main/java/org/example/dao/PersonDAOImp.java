package org.example.dao;

import org.example.dto.PersonDTO;
import org.example.dto.utils.PersonUtilsMethod;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.dto.utils.ConstantContainer.*;
import static org.example.dto.utils.PersonUtilsMethod.insertPerson;

public class PersonDAOImp implements PersonDAO {

    @Override
    public List<PersonDTO> getAll(Connection connection) throws SQLException {
        List<PersonDTO> persons = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM + TABLE_NAME);

        while (resultSet.next()) {
            PersonDTO personDTO = PersonUtilsMethod.buildPersonDTO(resultSet);
            persons.add(personDTO);
        }
        resultSet.close();
        statement.close();
        return persons;
    }

    @Override
    public PersonDTO save(Connection connection, PersonDTO personDTO) throws SQLException {
              try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSON_PREPARE_STATEMENT)) {
            try {
                insertPerson(preparedStatement, personDTO);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                connection.rollback();
            }
        }
        return personDTO;
    }

    @Override
    public PersonDTO get(Connection connection, int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM + TABLE_NAME + WHERE_ID + id);
        PersonDTO personDTO = null;
        while (resultSet.next()) {
            personDTO = PersonUtilsMethod.buildPersonDTO(resultSet);
        }
        resultSet.close();
        statement.close();
        return personDTO;
    }

    @Override
    public void update(Connection connection, PersonDTO personDTO, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS);
        insertPerson(preparedStatement, personDTO);
        preparedStatement.setInt(9, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public int delete(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

}
