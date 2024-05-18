package application.DTO.concreteDTO;

import application.entity.concreteentities.ConcreteGrade;
import application.entity.concreteentities.ConcretePrice;
import application.entity.enums.aggregate.Aggregate;
import application.entity.object.BuildingObject;
import application.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcreteDTO {

    private Long id;

    private Aggregate aggregate;

    private Set<Order> orderSet;

    private Set<ConcreteGrade> gradesSet = new HashSet<>();

    private Set<ConcretePrice> concretePriceSet = new HashSet<>();

    private Set<BuildingObject> objectSet = new HashSet<>();
}
