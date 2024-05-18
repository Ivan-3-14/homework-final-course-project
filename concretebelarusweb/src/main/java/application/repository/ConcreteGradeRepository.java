package application.repository;

import application.entity.concreteentities.ConcreteGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcreteGradeRepository extends JpaRepository<ConcreteGrade, Long> {

}
