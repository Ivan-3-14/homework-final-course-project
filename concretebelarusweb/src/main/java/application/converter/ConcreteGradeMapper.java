package application.converter;

import application.DTO.concreteDTO.ConcreteGradeDTO;
import application.entity.concreteentities.ConcreteGrade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ConcreteGradeMapper {

    @Mapping(target = "id")
    ConcreteGrade toEntity(ConcreteGradeDTO concreteGradeDTO);

    @Mapping(target = "id")
    ConcreteGradeDTO toDTO(ConcreteGrade concreteGrade);
}
