package application.DTO.concreteDTO;

import application.entity.concreteentities.ConcreteGrade;
import application.entity.enums.mobilityvalue.MobilityValue;
import application.entity.order.Order;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ConcreteGrade> concreteGradeSet = new HashSet<>();
}
