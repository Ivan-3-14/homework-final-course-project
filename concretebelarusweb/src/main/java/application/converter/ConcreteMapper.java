package application.converter;

import application.DTO.concreteDTO.ConcreteDTO;
import application.entity.concreteentities.Concrete;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper()
public interface ConcreteMapper {

    @Mapping(target = "id")
    @Mapping(target = "orderSet", ignore = true)
    @Mapping(target = "objectSet", ignore = true)
    @Mapping(target = "gradesSet", ignore = true)
    @Mapping(target = "concretePriceSet", ignore = true)
    Concrete toEntity(ConcreteDTO concreteDTO);

    @Mapping(target = "id")
    ConcreteDTO toDTO(Concrete concrete);
}
