package application.datalevel.DAO.implementations.auto;

import application.datalevel.DAO.implementations.DAOImpl;
import application.datalevel.DAO.interfaces.auto.AutoCapacityDAO;
import application.datalevel.DAO.interfaces.auto.AutoTransportDAO;
import application.datalevel.entities.autotransportation.AutoTransport;

import static application.utils.constant.ConstantsContainer.END_QUERY;
import static application.utils.constant.ConstantsContainer.GET_COUNT_OF_CAPACITY;

public class AutoTransportImpl extends DAOImpl<AutoTransport> implements AutoTransportDAO {

    @Override
    public Class<AutoTransport> getEntityClass() {
        return AutoTransport.class;
    }

    @Override
    public Long getCountOfCarCapacity(int capacity) {

        AutoCapacityDAO autoCapacityDAOImpl = new AutoCapacityImpl();
        int id = autoCapacityDAOImpl.getAutoCapacityByCapacity(capacity).getId();

        String query = String.format("%s%s%s", GET_COUNT_OF_CAPACITY, id, END_QUERY);

        return getEntityManager().createQuery(query, Long.class).getSingleResult();
    }
}
