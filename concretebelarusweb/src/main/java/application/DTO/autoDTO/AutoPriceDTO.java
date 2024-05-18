package application.DTO.autoDTO;

import application.entity.autotransportation.AutoCapacity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoPriceDTO {

    private Long id;

    private Double price;

    private AutoCapacity capacity;
}
