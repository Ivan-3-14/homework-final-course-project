package application.datalevel.DAO.interfaces.concrete;

import application.datalevel.DAO.interfaces.DAO;
import application.datalevel.entities.concreteentities.Concrete;
import application.utils.enums.aggregate.Aggregate;

import javax.persistence.EntityManager;

public interface ConcreteDAO extends DAO<Concrete> {

    Concrete getConcreteByValue(Aggregate aggregate);

    EntityManager getEntityManager();
}
