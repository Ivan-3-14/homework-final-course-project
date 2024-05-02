package application.utils.mappers;

import application.datalevel.entities.concreteentities.ConcreteGrade;
import application.servicelevel.DTO.concreteDTO.ConcreteGradeDTO;

public class ConcreteGradeMapper implements Mapper<ConcreteGradeDTO, ConcreteGrade> {

    @Override
    public ConcreteGrade dtoToEntity(ConcreteGradeDTO concreteGradeDTO) {
        return ConcreteGrade.builder()
                .id(concreteGradeDTO.getId())
                .gradesConcrete(concreteGradeDTO.getGradesConcrete())
                .concreteClass(concreteGradeDTO.getConcreteClass())
                .waterproof(concreteGradeDTO.getWaterproof())
                .frostResistance(concreteGradeDTO.getFrostResistance())
                .mobilitySet(concreteGradeDTO.getMobilitySet())
                .concretesSet(concreteGradeDTO.getConcretesSet())
                .concretePriceSet(concreteGradeDTO.getConcretePriceSet())
                .objectSet(concreteGradeDTO.getObjectSet())
                .orderSet(concreteGradeDTO.getOrderSet())
                .build();
    }

    @Override
    public ConcreteGradeDTO entityToDTO(ConcreteGrade concreteGrade) {
        return ConcreteGradeDTO.builder()
                .id(concreteGrade.getId())
                .gradesConcrete(concreteGrade.getGradesConcrete())
                .concreteClass(concreteGrade.getConcreteClass())
                .waterproof(concreteGrade.getWaterproof())
                .frostResistance(concreteGrade.getFrostResistance())
                .mobilitySet(concreteGrade.getMobilitySet())
                .concretesSet(concreteGrade.getConcretesSet())
                .concretePriceSet(concreteGrade.getConcretePriceSet())
                .objectSet(concreteGrade.getObjectSet())
                .orderSet(concreteGrade.getOrderSet())
                .build();
    }
}
