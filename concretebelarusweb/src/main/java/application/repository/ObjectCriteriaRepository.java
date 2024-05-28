package application.repository;

import application.DTO.filtersDTO.BuildingObjectPaginationFilter;
import application.entity.object.BuildingObject;
import application.entity.order.Order;

import java.util.List;

public interface ObjectCriteriaRepository {

    BuildingObjectPaginationFilter findAllOrderWithFilter(String search, Long currentUserId, int page);
}
