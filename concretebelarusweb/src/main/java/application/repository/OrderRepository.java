package application.repository;

import application.entity.enums.orderstatus.OrderStatus;
import application.entity.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderCriteriaRepository {

    Page<Order> findAllByUserId(Long userId, Pageable paging);

    Page<Order> findAllByManagerId(Long userId, Pageable paging);

    Page<Order> findAllByOrderStatus(OrderStatus orderStatus, Pageable paging);

    Integer countOrderByOrderStatus(OrderStatus orderStatus);

}
