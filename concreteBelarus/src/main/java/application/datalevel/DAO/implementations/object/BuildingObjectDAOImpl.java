package application.datalevel.DAO.implementations.object;

import application.datalevel.DAO.implementations.DAOImpl;
import application.datalevel.DAO.interfaces.object.BuildingObjectDAO;
import application.datalevel.entities.object.BuildingObject;
import application.utils.hibernate.HibernateUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static application.utils.constant.ConstantsContainer.*;

public class BuildingObjectDAOImpl extends DAOImpl<BuildingObject> implements BuildingObjectDAO {

    @Override
    public EntityManager getEntityManager() {
        return super.getEntityManager();
    }

    @Override
    public Class<BuildingObject> getEntityClass() {
        return BuildingObject.class;
    }

    @Override
    public BuildingObject findBuildingObjectByNameAndDistance(String nameOfObject, Double distanceToObject) {
        String query = String.format("%s%s%s%s%s", GET_OBJECT_BY_NAME, nameOfObject, AND_OBJECT_DIST, distanceToObject,
                END_QUERY);
        List<BuildingObject> list = getEntityManager().createNativeQuery(query, BuildingObject.class).getResultList();

        if (list.isEmpty()) {
            return null;
        } else {
            return list.stream().findFirst().orElse(null);
        }
    }

    @Override
    public List<BuildingObject> findAllByPagination(int id, int start, int limit) {
        String hql = String.format("%s%s", FROM_B_O_WHERE_USER, id);
        TypedQuery<BuildingObject> typedQuery = HibernateUtils.getEntityManager().createQuery(hql, BuildingObject.class);
        typedQuery.setFirstResult(limit * (start - FIRST_PAGE));
        typedQuery.setMaxResults(limit);
        return typedQuery.getResultList();
    }

    public int countOfPersonalObject(int id) {
        String hql = String.format("%s%s", FROM_B_O_WHERE_USER, id);
        TypedQuery<BuildingObject> typedQuery = HibernateUtils.getEntityManager().createQuery(hql, BuildingObject.class);
        return typedQuery.getResultList().size();
    }
}
