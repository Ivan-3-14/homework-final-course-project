package application.repository;

import application.entity.concreteentities.Mobility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobilityRepository extends JpaRepository<Mobility, Long> {

}
