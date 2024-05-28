package application.services.interfaces;

import application.DTO.filtersDTO.BuildingObjectPaginationFilter;
import application.DTO.objectDTO.BuildingObjectDTO;

import java.util.List;

public interface BuildingObjectService {

    BuildingObjectDTO createBuildingObject(BuildingObjectDTO buildingObjectDTO);

    BuildingObjectPaginationFilter readObjects(String search, Long currentUserId, int page);

    BuildingObjectPaginationFilter getObjectPaginationFilter(Long currentUserId, int currentPage, int countObjectsAtPage);


}
