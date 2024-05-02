package application.datalevel.DAO.interfaces.users;

import application.datalevel.DAO.interfaces.DAO;
import application.datalevel.entities.users.Manager;

import javax.persistence.EntityManager;

public interface ManagerDAO extends DAO<Manager> {

    EntityManager getEntityManager();
}
