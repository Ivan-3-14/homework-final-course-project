package application.converter;

import application.DTO.objectDTO.BuildingObjectDTO;
import application.entity.object.BuildingObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BuildingObjectMapper {

    @Mapping(target = "id")
    BuildingObject toEntity(BuildingObjectDTO buildingObjectDTO);

    @Mapping(target = "id")
    BuildingObjectDTO toDTO(BuildingObject buildingObject);
}
