package application.repository;

import application.entity.concreteentities.Concrete;
import application.entity.enums.aggregate.Aggregate;
import liquibase.pro.packaged.S;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcreteRepository extends JpaRepository<Concrete, Long> {

    Concrete getByAggregate(Aggregate aggregate);

}
