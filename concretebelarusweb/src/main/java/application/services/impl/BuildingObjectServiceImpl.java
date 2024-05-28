package application.services.impl;

import application.DTO.filtersDTO.BuildingObjectPaginationFilter;
import application.DTO.filtersDTO.OrderPaginationFilter;
import application.DTO.objectDTO.BuildingObjectDTO;
import application.DTO.orderDTO.OrderDTO;
import application.converter.BuildingObjectMapper;
import application.entity.object.BuildingObject;
import application.repository.BuildingObjectRepository;
import application.services.interfaces.BuildingObjectService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class BuildingObjectServiceImpl implements BuildingObjectService {

    private final BuildingObjectRepository buildingObjectRepository;
    private final BuildingObjectMapper buildingObjectMapper = Mappers.getMapper(BuildingObjectMapper.class);

    public BuildingObjectDTO createBuildingObject(BuildingObjectDTO buildingObjectDTO) {
        Long userId = buildingObjectDTO.getUser().getId();
        BuildingObjectDTO temp = checkIfExistObject(
                buildingObjectDTO.getNameOfObject(),
                buildingObjectDTO.getDistanceToObject()
        );
        if (temp != null && temp.getUser().getId().equals(userId)) {
            return temp;
        }

        return buildingObjectMapper.toDTO(
                buildingObjectRepository.save(buildingObjectMapper.toEntity(buildingObjectDTO)));
    }

    @Transactional(readOnly = true)
    public List<BuildingObjectDTO>  getAllObjectsByUserId(Long currentUserId) {
        return buildingObjectRepository.findAllByUserId(currentUserId).stream()
                .map(buildingObjectMapper::toDTO)
                .collect(Collectors.toList());
    }

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

    @Transactional(readOnly = true)
    public BuildingObjectPaginationFilter readObjects(String search, Long currentUserId, int page) {
        return buildingObjectRepository.findAllOrderWithFilter(search,
                currentUserId, page);
    }

    @Transactional(readOnly = true)
    protected BuildingObjectDTO checkIfExistObject(String nameOfObject, Double distanceToObject) {

        BuildingObject buildingObject = buildingObjectRepository.findByDistanceToObjectAndNameOfObject(distanceToObject,
                nameOfObject);
        if (buildingObject == null) {
            return null;
        }
        return buildingObjectMapper.toDTO(buildingObject);
    }

}
