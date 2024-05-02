package application.utils.mappers;

import application.datalevel.entities.concreteentities.Concrete;
import application.servicelevel.DTO.concreteDTO.ConcreteDTO;

public class ConcreteMapper implements Mapper<ConcreteDTO, Concrete> {

    @Override
    public Concrete dtoToEntity(ConcreteDTO concreteDTO) {
        return Concrete.builder()
                .id(concreteDTO.getId())
                .aggregate(concreteDTO.getAggregate())
                .gradesSet(concreteDTO.getGradesSet())
                .concretePriceSet(concreteDTO.getConcretePriceSet())
                .objectSet(concreteDTO.getObjectSet())
                .orderSet(concreteDTO.getOrderSet())
                .build();
    }

    @Override
    public ConcreteDTO entityToDTO(Concrete concrete) {
        return ConcreteDTO.builder()
                .id(concrete.getId())
                .aggregate(concrete.getAggregate())
                .gradesSet(concrete.getGradesSet())
                .concretePriceSet(concrete.getConcretePriceSet())
                .objectSet(concrete.getObjectSet())
                .orderSet(concrete.getOrderSet())
                .build();
    }
}
