package application.utils.mappers;

import application.datalevel.entities.concreteentities.ConcretePrice;
import application.servicelevel.DTO.concreteDTO.ConcretePriceDTO;

public class ConcretePriceMapper implements Mapper<ConcretePriceDTO, ConcretePrice> {

    @Override
    public ConcretePrice dtoToEntity(ConcretePriceDTO concretePriceDTO) {
        return ConcretePrice.builder()
                .id(concretePriceDTO.getId())
                .concrete(concretePriceDTO.getConcrete())
                .concreteGrade(concretePriceDTO.getConcreteGrade())
                .price(concretePriceDTO.getPrice())
                .build();
    }

    @Override
    public ConcretePriceDTO entityToDTO(ConcretePrice concretePrice) {
        return ConcretePriceDTO.builder()
                .id(concretePrice.getId())
                .concrete(concretePrice.getConcrete())
                .concreteGrade(concretePrice.getConcreteGrade())
                .price(concretePrice.getPrice())
                .build();
    }
}
