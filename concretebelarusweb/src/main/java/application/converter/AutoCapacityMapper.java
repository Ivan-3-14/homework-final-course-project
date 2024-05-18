package application.converter;

import application.DTO.autoDTO.AutoCapacityDTO;
import application.entity.autotransportation.AutoCapacity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AutoCapacityMapper {

    @Mapping(target = "id")
    AutoCapacity toEntity(AutoCapacityDTO autoCapacityDTO);

    @Mapping(target = "id")
    AutoCapacityDTO toDTO(AutoCapacity autoCapacity);
}
