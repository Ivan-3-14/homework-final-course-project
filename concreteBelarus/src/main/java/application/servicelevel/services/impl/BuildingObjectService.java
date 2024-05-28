package application.servicelevel.services.impl;

import application.datalevel.DAO.implementations.object.BuildingObjectDAOImpl;
import application.datalevel.DAO.interfaces.object.BuildingObjectDAO;
import application.datalevel.entities.object.BuildingObject;
import application.servicelevel.DTO.objectDTO.BuildingObjectDTO;
import application.servicelevel.DTO.usersDTO.UserDTO;
import application.servicelevel.services.interfaces.BuildingObjectServiceInt;
import application.utils.functionalinterface.MyInterfaceToDAO;
import application.utils.functionalinterface.UtilsInterface;
import application.utils.mappers.BuildingObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static application.utils.constant.ConstantsContainer.*;

public class BuildingObjectService implements BuildingObjectServiceInt {

    private final BuildingObjectDAO buildingObjectImpl = new BuildingObjectDAOImpl();
    private final BuildingObjectMapper buildingObjectMapper = new BuildingObjectMapper();

    // check if exist
    public BuildingObjectDTO createBuildingObject(String nameOfObject, Double distanceToObject, UserDTO userDTO) {

        MyInterfaceToDAO<BuildingObjectDTO> betweenBeginAndCommitted = () -> {
            BuildingObjectDTO temp = checkIfExistObject(nameOfObject, distanceToObject);
            if (temp != null && userDTO != null && userDTO.getId() == temp.getUser().getId()) {
                return temp;
            }

            BuildingObjectDTO buildingObjectDTO = BuildingObjectDTO.builder()
                    .nameOfObject(nameOfObject)
                    .distanceToObject(distanceToObject)
                    .user(userDTO)
                    .build();
            return buildingObjectMapper.entityToDTO(
                    buildingObjectImpl.create(buildingObjectMapper.dtoToEntity(buildingObjectDTO)));
        };
        return UtilsInterface.superMethodInterface(betweenBeginAndCommitted, buildingObjectImpl.getEntityManager());
    }

    public BuildingObjectDTO createBuildingObject(BuildingObjectDTO buildingObjectDTO) {
        MyInterfaceToDAO<BuildingObjectDTO> betweenBeginAndCommitted = () -> {
            int userId = buildingObjectDTO.getUser().getId();
        BuildingObjectDTO temp = checkIfExistObject(
                buildingObjectDTO.getNameOfObject(),
                buildingObjectDTO.getDistanceToObject()
        );
        if (temp != null && temp.getUser().getId() == userId) {
            return temp;
        }

        return buildingObjectMapper.entityToDTO(
                buildingObjectImpl.create(buildingObjectMapper.dtoToEntity(buildingObjectDTO)));
    };
        return UtilsInterface.superMethodInterface(betweenBeginAndCommitted, buildingObjectImpl.getEntityManager());
    }

    public BuildingObjectDTO updateBuildingObject(int id, BuildingObjectDTO buildingObjectDTO) {
        MyInterfaceToDAO<BuildingObjectDTO> betweenBeginAndCommitted = () -> buildingObjectMapper
                .entityToDTO(buildingObjectImpl.update(id,
                        buildingObjectMapper.dtoToEntity(buildingObjectDTO)));
        return UtilsInterface.superMethodInterface(betweenBeginAndCommitted, buildingObjectImpl.getEntityManager());
    }

    public List<BuildingObjectDTO> paginationBuildingObjectsList(int id, int start, int limit) {
        List<BuildingObjectDTO> list = new ArrayList<>();
        buildingObjectImpl.findAllByPagination(id, start, limit).forEach(f -> list.add(buildingObjectMapper.entityToDTO(f)));
        return list;
    }

    public int countOfPage(int id) {
        int result;
        int listSize = buildingObjectImpl.countOfPersonalObject(id);
        if ((listSize % ROW_IN_PAGE_OBJECTS) > ZERO) {
            result = (listSize / ROW_IN_PAGE_OBJECTS) + FIRST_PAGE;
        } else {
            result = listSize / ROW_IN_PAGE_OBJECTS;
        }
        return result;
    }

    private BuildingObjectDTO checkIfExistObject(String nameOfObject, Double distanceToObject) {
        BuildingObject buildingObject = buildingObjectImpl.findBuildingObjectByNameAndDistance(nameOfObject, distanceToObject);
        if (buildingObject == null) {
            return null;
        }
        return buildingObjectMapper.entityToDTO(buildingObject);
    }
}
