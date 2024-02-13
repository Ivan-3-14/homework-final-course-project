package application.dao.daoClasses;

import application.dao.daoInterfaces.PeopleDAO;
import application.dto.people.People;

import javax.persistence.EntityManager;
import java.util.List;

import static application.utils.constant.ConstantContainer.SELECT_ALL_PEOPLE_FROM_DB;

public class PeopleDAOImpl extends DAOImpl<People> implements PeopleDAO {

    public PeopleDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<People> getEntityClass() {
        return People.class;
    }

    @Override
    public List<People> getAll() {
        List<People> peopleList;
        entityManager.getTransaction().begin();
        peopleList = entityManager.createQuery(SELECT_ALL_PEOPLE_FROM_DB, getEntityClass()).getResultList();
        return peopleList;
    }

    @Override
    public void create(People people) {
        super.create(people);
    }

    @Override
    public People read(int id) {
        return super.read(id);
    }

    @Override
    public People update(int id, People people) {
        return super.update(id, people);
    }

    @Override
    public Integer delete(int id) {
        return super.delete(id);
    }
}
