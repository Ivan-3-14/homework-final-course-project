package application.DTO.concreteDTO;

import application.entity.concreteentities.ConcreteGrade;
import application.entity.concreteentities.ConcretePrice;
import application.entity.enums.aggregate.Aggregate;
import application.entity.object.BuildingObject;
import application.entity.order.Order;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcreteDTO {

    private Long id;

    private Aggregate aggregate;

    @ToString.Exclude
    private Set<Order> orderSet;

    @ToString.Exclude
    private Set<ConcreteGrade> gradesSet = new HashSet<>();

    @ToString.Exclude
    private Set<ConcretePrice> concretePriceSet = new HashSet<>();

    @ToString.Exclude
    private Set<BuildingObject> objectSet = new HashSet<>();
}
