package application.repository;

import application.entity.autotransportation.AutoPrice;
import application.entity.enums.mobilityvalue.MobilityValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoPriceRepository extends JpaRepository<AutoPrice, Long> {

//TODO delete
}
