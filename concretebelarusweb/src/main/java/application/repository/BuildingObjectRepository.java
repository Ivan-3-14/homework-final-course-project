package application.repository;

import application.entity.object.BuildingObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingObjectRepository extends JpaRepository<BuildingObject, Long>, ObjectCriteriaRepository {

    BuildingObject findByDistanceToObjectAndNameOfObjectAndUserId(Double distanceToObject, String nameOfObject, Long Id);

    Page<BuildingObject> findAllByUserId(Long userId, Pageable paging);
}
