package application.repository;

import application.entity.concreteentities.Concrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcreteRepository extends JpaRepository<Concrete, Long> {

}
