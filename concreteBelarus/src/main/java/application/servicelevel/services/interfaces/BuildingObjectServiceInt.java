package application.servicelevel.services.interfaces;

import application.servicelevel.DTO.objectDTO.BuildingObjectDTO;
import application.servicelevel.DTO.usersDTO.UserDTO;

import java.util.List;

public interface BuildingObjectServiceInt {

    BuildingObjectDTO createBuildingObject(String nameOfObject, Double distanceToObject, UserDTO userDTO);

    BuildingObjectDTO createBuildingObject(BuildingObjectDTO buildingObjectDTO);

    BuildingObjectDTO updateBuildingObject(int id, BuildingObjectDTO buildingObjectDTO);

    List<BuildingObjectDTO> paginationBuildingObjectsList(int id, int start, int limit);

    int countOfPage(int id);
}
