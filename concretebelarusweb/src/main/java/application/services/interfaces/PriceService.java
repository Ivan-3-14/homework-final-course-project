package application.services.interfaces;

import application.DTO.autoDTO.AutoCapacityDTO;
import application.DTO.autoDTO.AutoPriceDTO;
import application.DTO.concreteDTO.ConcretePriceDTO;
import application.DTO.filtersDTO.PriceFilterToFront;

import java.util.List;

public interface PriceService {

    PriceFilterToFront getAllPrice();

    ConcretePriceDTO updateConcretePrice(Long id, Double newPrice);

    AutoPriceDTO updateAutoPriceDTO(Long id, Double newPrice);

    AutoCapacityDTO updateAutoCapacityDTO(Long idDelBefore50, Long idDelAfter50, Integer delCoefficient);
}
