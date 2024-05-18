package application.repository;


import application.entity.autotransportation.AutoCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoCapacityRepository extends JpaRepository<AutoCapacity, Long> {

}
