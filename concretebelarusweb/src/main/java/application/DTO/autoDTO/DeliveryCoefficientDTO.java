package application.DTO.autoDTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryCoefficientDTO {

    private Long id;

    private Integer deliveryCoefficientBfr50;

    private Integer deliveryCoefficientAft50;
}
