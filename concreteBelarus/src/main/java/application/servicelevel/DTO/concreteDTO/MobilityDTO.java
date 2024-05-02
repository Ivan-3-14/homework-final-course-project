package application.servicelevel.DTO.concreteDTO;

import application.datalevel.entities.order.Order;
import application.datalevel.entities.concreteentities.ConcreteGrade;
import application.utils.enums.mobilityvalue.MobilityValue;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobilityDTO {

    private int id;

    private MobilityValue mobilityValue;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order order;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ConcreteGrade> concreteGradeSet = new HashSet<>();
}
