package application.repository;

import application.DTO.filtersDTO.OrderPaginationFilter;
import application.converter.OrderMapper;
import application.entity.enums.aggregate.Aggregate;
import application.entity.enums.grades.GradesConcrete;
import application.entity.enums.orderstatus.OrderStatus;
import application.entity.enums.roles.Roles;
import application.entity.order.Order;
import application.entity.users.User;
import liquibase.repackaged.org.apache.commons.lang3.EnumUtils;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static application.utils.Constant.*;

@Repository
@RequiredArgsConstructor
public class OrderCriteriaRepositoryImpl implements OrderCriteriaRepository {

    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Override
    public OrderPaginationFilter findAllOrderWithFilter(String search, Long currentUserId, int page) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = criteriaQuery.from(Order.class);

        criteriaQuery.where(getPredicate(search, orderRoot, criteriaBuilder, currentUserId).toArray(Predicate[]::new));

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(page * ROW_IN_PAGE_ORDERS);
        typedQuery.setMaxResults(ROW_IN_PAGE_ORDERS);

        double temp = Math.ceil((double) entityManager.createQuery(criteriaQuery).getResultList().size()
                / ROW_IN_PAGE_ORDERS);

        return OrderPaginationFilter.builder()
                .listOrders(typedQuery.getResultList().stream().map(orderMapper::toDTO).collect(Collectors.toList()))
                .countOfTotalPage((int) temp)
                .currentPage(page)
                .build();
    }

    private List<Predicate> getPredicate(String search, Root<Order> orderRoot, CriteriaBuilder criteriaBuilder,
                                         Long currentUserId) {
        List<Predicate> predicates = new ArrayList<>();
        User user = userRepository.getById(currentUserId);

        String pattern = String.format(LIKE_QUERY_PATTERN, search);
        Predicate predicate;
        Predicate idPredicate = criteriaBuilder.equal(orderRoot.get(USER).get(ID), currentUserId);

        if (Roles.MANAGER.equals(user.getRole())) {
            idPredicate = criteriaBuilder.equal(orderRoot.get(MANAGER).get(ID), user.getManager().getId());
        }

        if (!StringUtils.isBlank(search)) {

            if (StringUtils.isNumeric(search)) {
                predicate = criteriaBuilder.like(orderRoot.get(VOLUME).as(String.class), pattern.toLowerCase());
                predicates.add(criteriaBuilder.and(idPredicate, predicate));
            }
            if (EnumUtils.isValidEnumIgnoreCase(OrderStatus.class, OrderStatus.getOrderStatusFromStringStatus(search))) {
                predicate = criteriaBuilder.equal(orderRoot.get(ORDER_STATUS),
                        OrderStatus.valueOf(OrderStatus.getOrderStatusFromStringStatus(search)));
                predicates.add(criteriaBuilder.and(idPredicate, predicate));
            }
            if (EnumUtils.isValidEnumIgnoreCase(Aggregate.class, Aggregate.getStringAggregate(search.toLowerCase()))) {
                predicate = criteriaBuilder.equal(orderRoot.get(CONCRETE).get(AGGREGATE),
                        Aggregate.valueOf(Aggregate.getStringAggregate(search.toLowerCase())));
                predicates.add(criteriaBuilder.and(idPredicate, predicate));
            }
            if (EnumUtils.isValidEnumIgnoreCase(GradesConcrete.class, GradesConcrete.getStringGrade(search.toUpperCase()))) {
                predicate = criteriaBuilder.equal(orderRoot.get(CONCRETE).get(GRADE),
                        GradesConcrete.valueOf(GradesConcrete.getStringGrade(search.toUpperCase())));
                predicates.add(criteriaBuilder.and(idPredicate, predicate));
            }
            if (Pattern.matches(DATE_REGEX, search)) {
                SimpleDateFormat formatter = new SimpleDateFormat(MY_DATE_FORMAT, Locale.ENGLISH);
                formatter.setTimeZone(TimeZone.getTimeZone(UTC));
                try {
                    predicate = (criteriaBuilder.equal(orderRoot.get(DATE_OF_DEL), new Date(formatter.parse(search).getTime())));
                    predicates.add(criteriaBuilder.and(idPredicate, predicate));
                } catch (ParseException e) {
                    predicates = new ArrayList<>();
                }
            }

            if (predicates.isEmpty()) {
                predicate = criteriaBuilder.like(orderRoot.get(BUILDING_OBJECT).get(OBJECT_NAME), pattern.toLowerCase());
                predicates.add(criteriaBuilder.and(idPredicate, predicate));
            }
        }
        return predicates;
    }
}
