package application.datalevel.DAO.interfaces.auto;

import application.datalevel.DAO.interfaces.DAO;
import application.datalevel.entities.autotransportation.AutoCapacity;
import application.utils.enums.cartype.CarType;

import javax.persistence.EntityManager;
import java.util.List;

public interface AutoCapacityDAO extends DAO<AutoCapacity> {

    List<Integer> getCapacityByCarType(CarType carType);

    AutoCapacity getAutoCapacityByCapacity(int capacity);

    EntityManager getEntityManager();
}
