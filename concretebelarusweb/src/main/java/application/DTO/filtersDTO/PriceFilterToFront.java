package application.DTO.filtersDTO;

import application.DTO.autoDTO.AutoCapacityDTO;
import application.DTO.autoDTO.AutoPriceDTO;
import application.DTO.concreteDTO.ConcretePriceDTO;
import application.entity.autotransportation.AutoCapacity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceFilterToFront {

    private List<ConcretePriceDTO> concretePriceDTOList;

    private List<AutoPriceDTO> autoPriceDTOList;

    private List<AutoCapacityDTO> autoCapacityDTOList;
}
