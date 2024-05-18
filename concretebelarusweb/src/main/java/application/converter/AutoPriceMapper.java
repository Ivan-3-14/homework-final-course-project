package application.converter;

import application.DTO.autoDTO.AutoPriceDTO;
import application.entity.autotransportation.AutoPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AutoPriceMapper {

    @Mapping(target = "id")
    AutoPrice toEntity(AutoPriceDTO autoPriceDTO);

    @Mapping(target = "id")
    AutoPriceDTO toDTO(AutoPrice autoPrice);
}
