package application.utils.mappers;

import application.datalevel.entities.autotransportation.AutoPrice;
import application.servicelevel.DTO.autoDTO.AutoPriceDTO;

public class AutoPriceMapper implements Mapper<AutoPriceDTO, AutoPrice> {

    @Override
    public AutoPrice dtoToEntity(AutoPriceDTO autoPriceDTO) {
        return AutoPrice.builder()
                .id(autoPriceDTO.getId())
                .capacity(autoPriceDTO.getCapacity())
                .price(autoPriceDTO.getPrice())
                .build();
    }

    @Override
    public AutoPriceDTO entityToDTO(AutoPrice autoPrice) {
        return AutoPriceDTO.builder()
                .id(autoPrice.getId())
                .capacity(autoPrice.getCapacity())
                .price(autoPrice.getPrice())
                .build();
    }
}
