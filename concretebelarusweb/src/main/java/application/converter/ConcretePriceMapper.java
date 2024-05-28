package application.converter;

import application.DTO.concreteDTO.ConcretePriceDTO;
import application.entity.concreteentities.ConcretePrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConcretePriceMapper {

    @Mapping(target = "id")
    ConcretePrice toEntity(ConcretePriceDTO concretePriceDTO);

    @Mapping(target = "id")
    ConcretePriceDTO toDTO(ConcretePrice concretePrice);
}
