package application.servicelevel.DTO.concreteDTO;

import application.datalevel.entities.object.BuildingObject;
import application.datalevel.entities.order.Order;
import application.datalevel.entities.concreteentities.ConcreteGrade;
import application.datalevel.entities.concreteentities.ConcretePrice;
import application.utils.enums.aggregate.Aggregate;
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

    private int id;
    private Aggregate aggregate;
    private Set<Order> orderSet;
    private Set<ConcreteGrade> gradesSet = new HashSet<>();
    private Set<ConcretePrice> concretePriceSet = new HashSet<>();
    private Set<BuildingObject> objectSet = new HashSet<>();
}
