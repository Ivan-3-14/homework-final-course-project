package application.datalevel.DAO.implementations.concrete;

import application.datalevel.DAO.implementations.DAOImpl;
import application.datalevel.DAO.interfaces.concrete.ConcreteDAO;
import application.datalevel.entities.concreteentities.Concrete;
import application.utils.enums.aggregate.Aggregate;

import java.util.List;

import static application.utils.constant.ConstantsContainer.END_QUERY;
import static application.utils.constant.ConstantsContainer.GET_CONCRETE_BY_AGGREGATE;

public class ConcreteDAOImpl extends DAOImpl<Concrete> implements ConcreteDAO {

    @Override
    public Class<Concrete> getEntityClass() {
        return Concrete.class;
    }

    @Override
    public Concrete getConcreteByValue(Aggregate aggregate) {

        String query = String.format("%s%s%s", GET_CONCRETE_BY_AGGREGATE, aggregate.toString(), END_QUERY);
        List<Concrete> list = getEntityManager().createNativeQuery(query, Concrete.class).getResultList();

        if (list.isEmpty()) {
            return null;
        } else {
            return list.stream().findFirst().get();
        }
    }
}
