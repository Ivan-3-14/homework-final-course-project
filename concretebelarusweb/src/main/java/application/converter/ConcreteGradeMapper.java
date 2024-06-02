package application.converter;

import application.DTO.concreteDTO.ConcreteGradeDTO;
import application.entity.concreteentities.ConcreteGrade;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ConcreteGradeMapper {

    @Mapping(target = "id")
    @Mapping(target = "orderSet", ignore = true)
    @Mapping(target = "objectSet", ignore = true)
    @Mapping(target = "mobilitySet", ignore = true)
    @Mapping(target = "concretesSet", ignore = true)
    @Mapping(target = "concretePriceSet", ignore = true)
    ConcreteGrade toEntity(ConcreteGradeDTO concreteGradeDTO);

    @Mapping(target = "id")
    ConcreteGradeDTO toDTO(ConcreteGrade concreteGrade);
}
