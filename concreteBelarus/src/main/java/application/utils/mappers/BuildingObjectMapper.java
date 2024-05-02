package application.utils.mappers;

import application.datalevel.entities.concreteentities.Concrete;
import application.datalevel.entities.concreteentities.ConcreteGrade;
import application.datalevel.entities.object.BuildingObject;
import application.servicelevel.DTO.concreteDTO.ConcreteDTO;
import application.servicelevel.DTO.concreteDTO.ConcreteGradeDTO;
import application.servicelevel.DTO.objectDTO.BuildingObjectDTO;

import java.util.HashSet;
import java.util.Set;

public class BuildingObjectMapper implements Mapper<BuildingObjectDTO, BuildingObject> {

    private final ConcreteMapper concreteMapper = new ConcreteMapper();
    private final ConcreteGradeMapper concreteGradeMapper = new ConcreteGradeMapper();
    private final UserMapper userMapper = new UserMapper();

    @Override
    public BuildingObject dtoToEntity(BuildingObjectDTO buildingObjectDTO) {

        if (buildingObjectDTO == null) {
            return null;
        }

        Set<Concrete> concretes = new HashSet<>();
        Set<ConcreteGrade> concreteGrades = new HashSet<>();

        if (buildingObjectDTO.getConcreteGradeSet() != null) {
            buildingObjectDTO.getConcretesSet().forEach(c -> concretes.add(concreteMapper.dtoToEntity(c)));
        }

        if (buildingObjectDTO.getConcretesSet() != null) {
            buildingObjectDTO.getConcreteGradeSet().forEach(c -> concreteGrades.add(concreteGradeMapper.dtoToEntity(c)));
        }

        return BuildingObject.builder()
                .id(buildingObjectDTO.getId())
                .nameOfObject(buildingObjectDTO.getNameOfObject())
                .distanceToObject(buildingObjectDTO.getDistanceToObject())
                .concretesSet(concretes)
                .concreteGradeSet(concreteGrades)
                .user(userMapper.dtoToEntity(buildingObjectDTO.getUser()))
                .build();
    }

    @Override
    public BuildingObjectDTO entityToDTO(BuildingObject buildingObject) {

        if (buildingObject == null) {
            return null;
        }

        Set<ConcreteDTO> concretes = new HashSet<>();
        Set<ConcreteGradeDTO> concreteGrades = new HashSet<>();

        if (buildingObject.getConcreteGradeSet() != null) {
            buildingObject.getConcretesSet().forEach(c -> concretes.add(concreteMapper.entityToDTO(c)));
        }

        if (buildingObject.getConcretesSet() != null) {
            buildingObject.getConcreteGradeSet().forEach(c -> concreteGrades.add(concreteGradeMapper.entityToDTO(c)));
        }

        return BuildingObjectDTO.builder()
                .id(buildingObject.getId())
                .nameOfObject(buildingObject.getNameOfObject())
                .distanceToObject(buildingObject.getDistanceToObject())
                .concretesSet(concretes)
                .concreteGradeSet(concreteGrades)
                .user(userMapper.entityToDTO(buildingObject.getUser()))
                .build();
    }
}
