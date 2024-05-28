package application.repository;


import application.entity.autotransportation.AutoCapacity;
import application.entity.enums.cartype.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoCapacityRepository extends JpaRepository<AutoCapacity, Long> {

    List<AutoCapacity> findAllByCarType(CarType carType);

    AutoCapacity findByAutoCapacityAndCarType(int autoCapacity, CarType carType);

}
