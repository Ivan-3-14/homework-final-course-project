package application.repository;

import application.entity.concreteentities.ConcretePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.zip.DeflaterOutputStream;

@Repository
public interface ConcretePriceRepository extends JpaRepository<ConcretePrice, Long> {

    ConcretePrice findByConcreteIdAndConcreteGradeId(Long concreteDTOId, Long concreteGradeId);

}
