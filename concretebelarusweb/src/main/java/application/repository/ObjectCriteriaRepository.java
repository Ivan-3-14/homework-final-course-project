package application.repository;

import application.DTO.filtersDTO.BuildingObjectPaginationFilter;

public interface ObjectCriteriaRepository {

    BuildingObjectPaginationFilter findAllOrderWithFilter(String search, Long currentUserId, int page);
}
