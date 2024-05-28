package application.converter;

import application.DTO.concreteDTO.MobilityDTO;
import application.entity.concreteentities.Mobility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MobilityMapper {

    @Mapping(target = "id")
    Mobility toEntity(MobilityDTO mobilityDTO);

    @Mapping(target = "id")
    MobilityDTO toDTO(Mobility mobility);
}
