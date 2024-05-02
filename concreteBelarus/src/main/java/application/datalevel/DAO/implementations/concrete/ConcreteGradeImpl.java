package application.datalevel.DAO.implementations.concrete;

import application.datalevel.DAO.implementations.DAOImpl;
import application.datalevel.DAO.interfaces.concrete.ConcreteGradeDAO;
import application.datalevel.entities.concreteentities.ConcreteGrade;
import application.utils.enums.grades.GradesConcrete;

import java.util.List;

import static application.utils.constant.ConstantsContainer.END_QUERY;
import static application.utils.constant.ConstantsContainer.GET_CONCRETE_BY_NOMINATION_QUERY;

public class ConcreteGradeImpl extends DAOImpl<ConcreteGrade> implements ConcreteGradeDAO {

    @Override
    public Class<ConcreteGrade> getEntityClass() {
        return ConcreteGrade.class;
    }

    @Override
    public ConcreteGrade getConcreteGradeByGradeValue(GradesConcrete gradesConcrete) {

        String query = String.format("%s%s%s", GET_CONCRETE_BY_NOMINATION_QUERY, gradesConcrete.toString(), END_QUERY);
        List<ConcreteGrade> list = getEntityManager().createNativeQuery(query, ConcreteGrade.class).getResultList();

        if (list.isEmpty()) {
            return null;
        } else {
            return list.stream().findFirst().get();
        }
    }
}
