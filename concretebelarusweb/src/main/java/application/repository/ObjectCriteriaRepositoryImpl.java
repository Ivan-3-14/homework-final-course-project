package application.repository;

import application.DTO.filtersDTO.BuildingObjectPaginationFilter;
import application.converter.BuildingObjectMapper;
import application.entity.enums.aggregate.Aggregate;
import application.entity.enums.grades.GradesConcrete;
import application.entity.enums.orderstatus.OrderStatus;
import application.entity.object.BuildingObject;
import application.entity.order.Order;
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
public class ObjectCriteriaRepositoryImpl implements ObjectCriteriaRepository {

    private final EntityManager entityManager;
    private final BuildingObjectMapper buildingObjectMapper = Mappers.getMapper(BuildingObjectMapper.class);

    public BuildingObjectPaginationFilter findAllOrderWithFilter(String search, Long currentUserId, int page) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BuildingObject> criteriaQuery = criteriaBuilder.createQuery(BuildingObject.class);
        Root<BuildingObject> buildingObjectRoot = criteriaQuery.from(BuildingObject.class);

        criteriaQuery.where(getPredicate(search, buildingObjectRoot, criteriaBuilder, currentUserId).toArray(Predicate[]::new));

        TypedQuery<BuildingObject> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(page * ROW_IN_PAGE_OBJECTS);
        typedQuery.setMaxResults(ROW_IN_PAGE_OBJECTS);

        double temp = Math. ceil((double) entityManager.createQuery(criteriaQuery).getResultList().size() /
                ROW_IN_PAGE_OBJECTS);
        return BuildingObjectPaginationFilter.builder()
                .objectDTOList(typedQuery.getResultList().stream().map(buildingObjectMapper::toDTO).collect(Collectors.toList()))
                .countOfTotalPage((int) temp)
                .currentPage(page)
                .build();
    }

    private List<Predicate> getPredicate(String search, Root<BuildingObject> objectRoot, CriteriaBuilder criteriaBuilder,
                                         Long currentUserId) {
        List<Predicate> predicates = new ArrayList<>();

        String pattern = String.format(LIKE_QUERY_PATTERN, search);
        Predicate predicate;
        Predicate idPredicate = criteriaBuilder.equal(objectRoot.get(USER).get(ID), currentUserId);

        if (!StringUtils.isBlank(search)) {

            if (StringUtils.isNumeric(search)) {
                predicate = criteriaBuilder.like(objectRoot.get(DISTANCE).as(String.class), pattern.toLowerCase());
                predicates.add(criteriaBuilder.and(idPredicate, predicate));
            }

            if (predicates.isEmpty()) {
                predicate = criteriaBuilder.like(objectRoot.get(OBJECT_NAME), pattern.toLowerCase());
                predicates.add(criteriaBuilder.and(idPredicate, predicate));
            }
        }
        return predicates;
    }
}
