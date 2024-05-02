package application.datalevel.DAO.implementations.auto;

import application.datalevel.DAO.implementations.DAOImpl;
import application.datalevel.DAO.implementations.concrete.ConcretePriceImpl;
import application.datalevel.DAO.interfaces.auto.AutoCapacityDAO;
import application.datalevel.DAO.interfaces.auto.AutoPriceDAO;
import application.datalevel.entities.autotransportation.AutoPrice;
import application.datalevel.entities.concreteentities.Mobility;
import application.utils.enums.cartype.CarType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static application.utils.constant.ConstantsContainer.*;

public class AutoPriceImpl extends DAOImpl<AutoPrice> implements AutoPriceDAO {

    private final Logger logger = Logger.getLogger(ConcretePriceImpl.class.getName());

    @Override
    public Class<AutoPrice> getEntityClass() {
        return AutoPrice.class;
    }

    @Override
    public List<Double> getAllAutoPrice() {
        logger.log(Level.INFO, START_GET_ALL_AUTO_PRICE);
        List<Double> listPrice = new ArrayList<>();
        try {
            List<AutoPrice> list = getEntityManager()
                    .createQuery(GET_ALL_AUTO_PRICE_QUERY, getEntityClass())
                    .getResultList();
            list.forEach(l -> listPrice.add(l.getPrice()));
        } catch (NullPointerException e) {
            logger.log(Level.INFO, LIST_OF_AUTO_PRICE_EMPTY);
        }
        logger.log(Level.INFO, GET_ALL_AUTO_PRICE_SUCCESSFUL);
        return listPrice;
    }

    @Override
    public Double getAutoPriceByMobilityAndVolumeOfConcrete(Mobility mobility, Double volume, Double distance) {
        CarType carType;
        int coefficient;
        double result;
        int price = ZERO;
        AutoCapacityDAO autoCapacityDAO = new AutoCapacityImpl();

        if (mobility.getMobilityValue().getIntValueOfMobility() < MOBILITY_CONSTANT) {
            carType = CarType.DUMP_TRUCK;
        } else {
            carType = CarType.CONCRETE_MIXER_TRUCK;
        }

        List<Integer> existCapacity = autoCapacityDAO.getCapacityByCarType(carType)
                .stream().sorted().collect(Collectors.toList());

        if (distance < TRANSPORT_CONSTANT) {
            coefficient = autoCapacityDAO.getAutoCapacityByCapacity(existCapacity.get(ZERO)).getDeliveryCoefficient();
        } else {
            coefficient = autoCapacityDAO.getAutoCapacityByCapacity(existCapacity.get(ZERO)).getDeliCoeffAft50();
        }

        if (volume <= existCapacity.get(existCapacity.size() - ONE)) {
            for (var e : existCapacity) {
                if (volume <= e) {
                    return (autoCapacityDAO.getAutoCapacityByCapacity(
                            e).getAutoPrice().getPrice() + coefficient * distance);
                }
            }
        } else {
            result = volume / existCapacity.get(ZERO);
            if (result % ONE > ZERO) {
                price = (int) result;
                price++;
            } else {
                price = (int) result;
            }
        }
        var temp = autoCapacityDAO.getAutoCapacityByCapacity(
                existCapacity.get(ZERO)).getAutoPrice().getPrice() + coefficient * distance;
        return (price * temp);
    }

}
