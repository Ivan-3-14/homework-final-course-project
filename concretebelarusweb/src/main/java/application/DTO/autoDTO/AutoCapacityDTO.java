package application.DTO.autoDTO;

import application.entity.autotransportation.AutoPrice;
import application.entity.autotransportation.DeliveryCoefficient;
import application.entity.enums.cartype.CarType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoCapacityDTO {

    private Long id;

    private CarType carType;

    private Integer autoCapacity;

    private DeliveryCoefficient deliveryCoefficient;

    private AutoPrice autoPrice;
}
