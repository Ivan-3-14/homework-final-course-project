package application.repository;

import application.entity.concreteentities.ConcreteGrade;
import application.entity.enums.grades.GradesConcrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcreteGradeRepository extends JpaRepository<ConcreteGrade, Long> {

    ConcreteGrade getByGradesConcrete (GradesConcrete gradesConcrete);

}
