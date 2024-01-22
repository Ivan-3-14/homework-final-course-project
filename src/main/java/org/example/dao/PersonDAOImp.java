package org.example.dao;

import org.example.dto.PersonDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.ConstantContainer.*;
import static org.example.utils.RecordPerson.insertPerson;

public class PersonDAOImp implements PersonDAO {

    @Override
    public List<PersonDTO> getAll(Connection connection) throws SQLException {
        List<PersonDTO> persons = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM + TABLE_NAME);

        while (resultSet.next()) {
            PersonDTO personDTO = buildPersonDTO(resultSet);
            persons.add(personDTO);
        }
        return persons;
    }

    @Override
    public PersonDTO save(Connection connection,
                          int age,
                          double salary,
                          String passport,
                          String address,
                          Date dateOfBirthday,
                          Time time,
                          String letter
    ) throws SQLException {
        PersonDTO personDTO = PersonDTO.builder()
                .age(age)
                .salary(salary)
                .passport(passport)
                .address(address)
                .dateOfBirthday(dateOfBirthday)
                .timeToLunch(time)
                .dateTimeCreate(new Timestamp(System.currentTimeMillis()))
                .letter(letter)
                .build();

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
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM + TABLE_NAME + " where id = " + id);
        PersonDTO personDTO = null;
        while (resultSet.next()) {
            personDTO = buildPersonDTO(resultSet);
        }
        return personDTO;
    }

    @Override
    public void update(Connection connection, PersonDTO personDTO, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS);
        insertPerson(preparedStatement, personDTO);
        preparedStatement.setInt(8, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();

    }

    private PersonDTO buildPersonDTO(ResultSet resultSet) throws SQLException {
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
