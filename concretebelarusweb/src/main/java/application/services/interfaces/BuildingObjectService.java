package application.services.interfaces;

import application.DTO.filtersDTO.BuildingObjectPaginationFilter;
import application.DTO.objectDTO.BuildingObjectDTO;
import application.entity.concreteentities.Concrete;
import application.entity.concreteentities.ConcreteGrade;

public interface BuildingObjectService {

    BuildingObjectDTO createBuildingObject(BuildingObjectDTO buildingObjectDTO, Concrete concrete, ConcreteGrade concreteGrade);

    BuildingObjectPaginationFilter readObjects(String search, Long currentUserId, int page);

    BuildingObjectPaginationFilter getObjectPaginationFilter(Long currentUserId, int currentPage, int countObjectsAtPage);

}
