package application.datalevel.DAO.implementations.auto;

import application.datalevel.DAO.implementations.DAOImpl;
import application.datalevel.DAO.interfaces.auto.AutoCapacityDAO;
import application.datalevel.entities.autotransportation.AutoCapacity;
import application.utils.enums.cartype.CarType;

import java.util.ArrayList;
import java.util.List;

import static application.utils.constant.ConstantsContainer.*;

public class AutoCapacityDAOImpl extends DAOImpl<AutoCapacity> implements AutoCapacityDAO {

    @Override
    public Class<AutoCapacity> getEntityClass() {
        return AutoCapacity.class;
    }

    @Override
    public List<Integer> getCapacityByCarType(CarType carType) {
        String query = String.format("%s%s%s", GET_CAPACITY_BY_CAR_TYPE, carType.toString(),
                END_QUERY
        );
        List<AutoCapacity> list = getEntityManager().createNativeQuery(query, AutoCapacity.class).getResultList();
        if (list.isEmpty()) {
            return null;
        }
            List<Integer> integerList = new ArrayList<>();
            list.forEach(l -> integerList.add(l.getAutoCapacity()));
            return integerList;

    }

    @Override
    public AutoCapacity getAutoCapacityByCapacity(int capacity) {
        String query = String.format("%s%s%s", GET_CAPACITY_BY_AUTO_CAPACITY, capacity,
                END_QUERY
        );
        List<AutoCapacity> list = getEntityManager().createNativeQuery(query, AutoCapacity.class).getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.stream().findFirst().get();
    }
}
