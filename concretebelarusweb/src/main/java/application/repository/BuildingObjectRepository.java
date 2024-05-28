package application.repository;

import application.entity.object.BuildingObject;
import application.entity.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingObjectRepository extends JpaRepository<BuildingObject, Long>, ObjectCriteriaRepository {

    BuildingObject findByDistanceToObjectAndNameOfObject( Double distanceToObject, String nameOfObject);

    List<BuildingObject> findAllByUserId(Long userId);

    Page<BuildingObject> findAllByUserId(Long userId, Pageable paging);
}
