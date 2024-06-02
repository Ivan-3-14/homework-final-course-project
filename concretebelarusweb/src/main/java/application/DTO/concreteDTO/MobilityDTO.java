package application.DTO.concreteDTO;

import application.entity.enums.mobilityvalue.MobilityValue;
import application.entity.order.Order;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobilityDTO {

    private Long id;

    private MobilityValue mobilityValue;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order order;

}
