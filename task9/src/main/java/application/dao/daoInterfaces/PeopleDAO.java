package application.dao.daoInterfaces;

import application.dto.people.People;

import java.util.List;

public interface PeopleDAO extends DAO<People> {
    List<People> getAll();

}
