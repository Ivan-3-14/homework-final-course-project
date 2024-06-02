package application.services.interfaces;

import application.DTO.filtersDTO.PriceFilterToFront;

public interface PriceService {

    PriceFilterToFront getAllPrice();

    void updateConcretePrice(Long id, Double newPrice);

    void updateAutoPriceDTO(Long id, Double newPrice);

    void updateAutoCapacityDTO(Long idDelBefore50, Long idDelAfter50, Integer delCoefficient);
}
