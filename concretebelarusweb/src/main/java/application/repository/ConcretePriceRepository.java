package application.repository;

import application.entity.concreteentities.ConcretePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcretePriceRepository extends JpaRepository<ConcretePrice, Long> {

}
