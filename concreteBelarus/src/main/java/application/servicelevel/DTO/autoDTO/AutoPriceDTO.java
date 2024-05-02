package application.servicelevel.DTO.autoDTO;

import application.datalevel.entities.autotransportation.AutoCapacity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoPriceDTO {

    private int id;
    private Double price;
    private AutoCapacity capacity;
}
