package exampl.dao;

import exampl.enums.Parameters;
import exampl.person.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO extends DAO<Person> {
    @Override
    void save(Person person) throws SQLException;

    @Override
    List <Person> findByParameter(
            Parameters parameter,
            String mainCondition,
            Object value,
            String additionalCondition
    ) throws SQLException;
}
