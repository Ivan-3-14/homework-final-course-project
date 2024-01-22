package org.example.dao;

import org.example.dto.PersonDTO;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.ConstantContainer.*;
import static org.example.utils.RecordPerson.insertPerson;

public class PersonDAOImp implements PersonDAO{

    @Override
    public List<PersonDTO> getAll(Connection connection, String tableName) throws SQLException {
        List<PersonDTO> persons = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM + tableName);

        while (resultSet.next()) {
            PersonDTO person = buildPersonDTO(resultSet);
            persons.add(person);
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
            connection.setAutoCommit(false);
            try {
               insertPerson(preparedStatement, personDTO);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }
        }
        return personDTO;
    }

    @Override
    public PersonDTO get(Connection connection,String tableName, Serializable id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM + tableName + " where id = " + id );
        PersonDTO person = null;
        while (resultSet.next()) {
            person = buildPersonDTO(resultSet);
        }
        if (person == null) {
            System.out.println("This person not found!");
            person = PersonDTO.builder().build();
        }
            return person;
    }

    @Override
    public void update(Connection connection, PersonDTO personDTO, int id) throws SQLException {
       PreparedStatement preparedStatement = createUpdateQuery(connection, UPDATE_QUERY, personDTO);
       preparedStatement.setInt(8, id);
       preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Connection connection, Serializable id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, (Integer) id);
        return  preparedStatement.executeUpdate();

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

    private PreparedStatement createUpdateQuery(Connection connection, String query, PersonDTO person) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, person.getAge());
        preparedStatement.setDouble(2, person.getSalary());
        preparedStatement.setString(3, person.getPassport());
        preparedStatement.setString(4, person.getAddress());
        preparedStatement.setDate(5, new Date(person.getDateOfBirthday().getTime()));
        preparedStatement.setTime(6, person.getTimeToLunch());
        preparedStatement.setString(7, person.getLetter());
        return preparedStatement;
    }
}
