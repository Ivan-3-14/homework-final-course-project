package exampl.dao;

import exampl.enums.Parameters;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    void save(T obj) throws SQLException;

    List<T> findByParameter(
            Parameters parameter,
            String mainCondition,
            Object value,
            String additionalCondition
    ) throws SQLException;
}
