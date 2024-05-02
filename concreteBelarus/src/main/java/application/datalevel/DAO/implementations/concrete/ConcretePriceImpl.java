package application.datalevel.DAO.implementations.concrete;

import application.datalevel.DAO.implementations.DAOImpl;
import application.datalevel.DAO.interfaces.concrete.ConcretePriceDAO;
import application.datalevel.entities.concreteentities.ConcretePrice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static application.utils.constant.ConstantsContainer.*;

public class ConcretePriceImpl extends DAOImpl<ConcretePrice> implements ConcretePriceDAO {

    private final Logger logger = Logger.getLogger(ConcretePriceImpl.class.getName());

    @Override
    public Class<ConcretePrice> getEntityClass() {
        return ConcretePrice.class;
    }

    @Override
    public List<Double> getAllConcretePrice() {
        logger.log(Level.INFO, START_GET_ALL_CONCRETE_PRICE);
        List<Double> listPrice = new ArrayList<>();
        try {
            List<ConcretePrice> list = getEntityManager()
                    .createQuery(GET_ALL_CONCRETE_PRICE_QUERY, getEntityClass())
                    .getResultList();
            list.forEach(l -> listPrice.add(l.getPrice()));
        } catch (NullPointerException e) {
            logger.log(Level.INFO, LIST_OF_CONCRETE_PRICE_EMPTY);
        }
        logger.log(Level.INFO, GET_ALL_CONCRETE_PRICE_SUCCESSFUL);
        return listPrice;
    }

    @Override
    public Double getConcretePriceByConcreteAndGrade(int concreteId, int concreteGradeId) {
        String query = String.format("%s%s%s%s%s", GET_CONCRETE_PRICE_BY_CONCRETE, concreteId,
                AND_CONCRETE_GRADE, concreteGradeId, END_QUERY2);
        List<ConcretePrice> prices = getEntityManager().createNativeQuery(query, ConcretePrice.class).getResultList();
        if (prices == null || prices.isEmpty()) {
            logger.log(Level.INFO, BY_CONCRETE_AND_GRADE_THIS_PRICE_NOT_FOUND);
            return null;
        }
        return prices.stream().findFirst().get().getPrice();
    }
}
