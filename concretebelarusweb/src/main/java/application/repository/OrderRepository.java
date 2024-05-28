package application.repository;

import application.entity.order.Order;
import liquibase.parser.core.ParsedNodeException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderCriteriaRepository {

    Page<Order> findAllByUserId(Long userId, Pageable paging);

}
