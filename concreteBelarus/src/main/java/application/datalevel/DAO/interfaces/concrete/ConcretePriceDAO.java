package application.datalevel.DAO.interfaces.concrete;

import application.datalevel.DAO.interfaces.DAO;
import application.datalevel.entities.concreteentities.ConcretePrice;

import javax.persistence.EntityManager;
import java.util.List;

public interface ConcretePriceDAO extends DAO<ConcretePrice> {

    List<Double> getAllConcretePrice();

    Double getConcretePriceByConcreteAndGrade(int concreteId, int concreteGradeId);

    EntityManager getEntityManager();
}
