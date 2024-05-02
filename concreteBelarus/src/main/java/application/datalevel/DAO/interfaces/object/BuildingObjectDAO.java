package application.datalevel.DAO.interfaces.object;

import application.datalevel.DAO.interfaces.DAO;
import application.datalevel.entities.object.BuildingObject;

import javax.persistence.EntityManager;
import java.util.List;

public interface BuildingObjectDAO extends DAO<BuildingObject> {

    BuildingObject findBuildingObjectByNameAndDistance(String nameOfObject, Double distanceToObject);

    List<BuildingObject> findAllByPagination(int id, int start, int limit);

    int countOfPersonalObject(int id);

    EntityManager getEntityManager();
}
