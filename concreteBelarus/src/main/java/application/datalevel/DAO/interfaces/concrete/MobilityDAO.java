package application.datalevel.DAO.interfaces.concrete;

import application.datalevel.DAO.interfaces.DAO;
import application.datalevel.entities.concreteentities.Mobility;
import application.utils.enums.mobilityvalue.MobilityValue;

import javax.persistence.EntityManager;

public interface MobilityDAO extends DAO<Mobility> {

    Mobility getMobilityByMobilityValue(MobilityValue mobilityValue);

    EntityManager getEntityManager();
}
