package application.datalevel.DAO.interfaces.auto;

import application.datalevel.DAO.interfaces.DAO;
import application.datalevel.entities.autotransportation.AutoTransport;

import javax.persistence.EntityManager;

public interface AutoTransportDAO extends DAO<AutoTransport> {

    Long getCountOfCarCapacity(int capacity);

    EntityManager getEntityManager();
}
