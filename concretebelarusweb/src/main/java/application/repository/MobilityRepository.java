package application.repository;

import application.entity.concreteentities.Mobility;
import application.entity.enums.mobilityvalue.MobilityValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobilityRepository extends JpaRepository<Mobility, Long> {

    Mobility getByMobilityValue(MobilityValue mobilityValue);

}
