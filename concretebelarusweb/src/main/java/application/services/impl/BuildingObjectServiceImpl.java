package application.services.impl;

import application.DTO.filtersDTO.BuildingObjectPaginationFilter;
import application.DTO.objectDTO.BuildingObjectDTO;
import application.converter.BuildingObjectMapper;
import application.entity.concreteentities.Concrete;
import application.entity.concreteentities.ConcreteGrade;
import application.entity.object.BuildingObject;
import application.repository.BuildingObjectRepository;
import application.repository.ConcreteGradeRepository;
import application.repository.ConcreteRepository;
import application.services.interfaces.BuildingObjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

import static application.utils.Constant.*;
import static application.logger.LoggerPrinter.logPrint;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class BuildingObjectServiceImpl implements BuildingObjectService {

    private final BuildingObjectRepository buildingObjectRepository;
    private final ConcreteRepository concreteRepository;
    private final ConcreteGradeRepository concreteGradeRepository;
    private final BuildingObjectMapper buildingObjectMapper = Mappers.getMapper(BuildingObjectMapper.class);

    @Override
    public BuildingObjectDTO createBuildingObject(BuildingObjectDTO buildingObjectDTO, Concrete concrete, ConcreteGrade concreteGrade) {
        Long userId = buildingObjectDTO.getUser().getId();

        BuildingObject temp = checkIfExistObject(
                buildingObjectDTO.getNameOfObject(),
                buildingObjectDTO.getDistanceToObject(),
                userId
        );

        if (temp != null) {
            log.info(logPrint(CHECK_EXIST_OBJECT_FIND));
            temp = getBuildingObjectWithConcreteAndGrade(concrete, concreteGrade, temp);
            log.info(logPrint(CREATE_OBJECT_SET_CONCRETE_AND_GRADE));
            return buildingObjectMapper.toDTO(temp);
        }

        log.info(logPrint(CHECK_EXIST_OBJECT_NOT_FOUND));
        BuildingObject result = buildingObjectMapper.toEntity(buildingObjectDTO);
        result.setConcretesSet(new HashSet<>());
        result.setConcreteGradeSet(new HashSet<>());
        result = getBuildingObjectWithConcreteAndGrade(concrete, concreteGrade, result);

        return buildingObjectMapper.toDTO(result);
    }

    @Override
    @Transactional(readOnly = true)
    public BuildingObjectPaginationFilter getObjectPaginationFilter(Long currentUserId, int currentPage,
                                                                   int countObjectsAtPage) {
        Page<BuildingObjectDTO> listOrders = buildingObjectRepository.findAllByUserId(currentUserId,
                PageRequest.of(currentPage, countObjectsAtPage)).map(buildingObjectMapper::toDTO);
        return BuildingObjectPaginationFilter.builder()
                .objectDTOList(listOrders.toList())
                .currentPage(currentPage)
                .countOfTotalPage(listOrders.getTotalPages())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public BuildingObjectPaginationFilter readObjects(String search, Long currentUserId, int page) {
        return buildingObjectRepository.findAllOrderWithFilter(search,
                currentUserId, page);
    }

    @Transactional(readOnly = true)
    protected BuildingObject checkIfExistObject(String nameOfObject, Double distanceToObject, Long userId) {
        return buildingObjectRepository.findByDistanceToObjectAndNameOfObjectAndUserId(
                distanceToObject,
                nameOfObject,
                userId);
    }

    private BuildingObject getBuildingObjectWithConcreteAndGrade(Concrete concrete, ConcreteGrade concreteGrade, BuildingObject buildingObject) {
        buildingObject.getConcretesSet().add(concrete);
        buildingObject.getConcreteGradeSet().add(concreteGrade);
        buildingObject = buildingObjectRepository.save(buildingObject);

        concrete.getObjectSet().add(buildingObject);
        concreteGrade.getObjectSet().add(buildingObject);
        concreteRepository.save(concrete);
        concreteGradeRepository.save(concreteGrade);

        return buildingObject;
    }
}
