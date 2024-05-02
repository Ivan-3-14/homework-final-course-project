package application.datalevel.DAO.interfaces.concrete;

import application.datalevel.DAO.interfaces.DAO;
import application.datalevel.entities.concreteentities.ConcreteGrade;
import application.utils.enums.grades.GradesConcrete;

import javax.persistence.EntityManager;

public interface ConcreteGradeDAO extends DAO<ConcreteGrade> {

    ConcreteGrade getConcreteGradeByGradeValue(GradesConcrete gradesConcrete);

    EntityManager getEntityManager();
}
