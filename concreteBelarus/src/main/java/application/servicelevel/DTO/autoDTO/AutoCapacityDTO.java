package application.servicelevel.DTO.autoDTO;

import application.datalevel.entities.autotransportation.AutoPrice;
import application.datalevel.entities.autotransportation.AutoTransport;
import application.utils.enums.cartype.CarType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoCapacityDTO {

    private int id;
    private CarType carType;
    private Integer autoCapacity;
    private Integer deliveryCoefficient;
    private AutoPrice autoPrice;
    private Set<AutoTransport> autoTransport;
}
