package application.converter;


import application.DTO.concreteDTO.ConcreteDTO;
import application.entity.concreteentities.Concrete;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ConcreteMapper {

    @Mapping(target = "id")
    Concrete toEntity(ConcreteDTO concreteDTO);

    @Mapping(target = "id")
    ConcreteDTO toDTO(Concrete concrete);
}
