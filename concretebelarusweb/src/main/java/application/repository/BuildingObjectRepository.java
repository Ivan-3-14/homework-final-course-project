package application.repository;

import application.entity.object.BuildingObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingObjectRepository extends JpaRepository<BuildingObject, Long> {

}
