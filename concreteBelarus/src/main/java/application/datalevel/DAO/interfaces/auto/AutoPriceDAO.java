package application.datalevel.DAO.interfaces.auto;

import application.datalevel.DAO.interfaces.DAO;
import application.datalevel.entities.autotransportation.AutoPrice;
import application.datalevel.entities.concreteentities.Mobility;

import javax.persistence.EntityManager;
import java.util.List;

public interface AutoPriceDAO extends DAO<AutoPrice> {

    List<AutoPrice> getAllAutoPrice();

    Double getAutoPriceByMobilityAndVolumeOfConcrete(Mobility mobility, Double volume, Double distance);

    EntityManager getEntityManager();
}
