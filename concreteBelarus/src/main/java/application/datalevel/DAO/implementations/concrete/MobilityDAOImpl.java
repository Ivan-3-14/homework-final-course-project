package application.datalevel.DAO.implementations.concrete;

import application.datalevel.DAO.implementations.DAOImpl;
import application.datalevel.DAO.interfaces.concrete.MobilityDAO;
import application.datalevel.entities.concreteentities.Mobility;

import application.utils.enums.mobilityvalue.MobilityValue;

import java.util.List;

import static application.utils.constant.ConstantsContainer.END_QUERY;
import static application.utils.constant.ConstantsContainer.GET_CONCRETE_MOBILITY_BY_VALUE;

public class MobilityDAOImpl extends DAOImpl<Mobility> implements MobilityDAO {

    @Override
    public Class<Mobility> getEntityClass() {
        return Mobility.class;
    }

    @Override
    public Mobility getMobilityByMobilityValue(MobilityValue mobilityValue) {

        String query = String.format("%s%s%s", GET_CONCRETE_MOBILITY_BY_VALUE, mobilityValue.toString(), END_QUERY);
        List<Mobility> mobilities = getEntityManager().createNativeQuery(query, Mobility.class).getResultList();

        if (mobilities.isEmpty()) {
            return null;
        } else {
            return mobilities.stream().findFirst().orElse(null);
        }
    }
}
