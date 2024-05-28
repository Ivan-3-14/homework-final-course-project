package application.DTO.autoDTO;

import application.entity.autotransportation.AutoCapacity;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryCoefficientDTO {

    private Long id;

    private Integer deliveryCoefficientBfr50;

    private Integer deliveryCoefficientAft50;
}
