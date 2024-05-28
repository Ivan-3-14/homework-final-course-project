package application.repository;

import application.DTO.filtersDTO.OrderPaginationFilter;
public interface OrderCriteriaRepository {

    OrderPaginationFilter findAllOrderWithFilter(String search, Long currentUserId, int page);
}
