package application.converter;


import application.DTO.concreteDTO.ConcreteDTO;
import application.entity.concreteentities.Concrete;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ConcreteGradeMapper.class, ConcretePriceMapper.class,
        BuildingObjectMapper.class, OrderMapper.class})
public interface ConcreteMapper {

    ConcreteMapper INSTANCE = Mappers.getMapper(ConcreteMapper.class);

    @Mapping(target = "id")
    @Mapping(target = "orderSet", ignore = true)
    @Mapping(target = "objectSet", ignore = true)
    @Mapping(target = "gradesSet", ignore = true)
    @Mapping(target = "concretePriceSet", ignore = true)
    Concrete toEntity(ConcreteDTO concreteDTO);

    @Mapping(target = "id")
    @Mapping(target = "orderSet", ignore = true)
    @Mapping(target = "objectSet", ignore = true)
    @Mapping(target = "gradesSet", ignore = true)
    @Mapping(target = "concretePriceSet", ignore = true)
    ConcreteDTO toDTO(Concrete concrete);
}
