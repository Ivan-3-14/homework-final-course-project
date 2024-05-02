package application.servicelevel.DTO.concreteDTO;

import application.datalevel.entities.object.BuildingObject;
import application.datalevel.entities.order.Order;
import application.datalevel.entities.concreteentities.Concrete;
import application.datalevel.entities.concreteentities.ConcretePrice;
import application.datalevel.entities.concreteentities.Mobility;
import application.utils.enums.classess.ConcreteClass;
import application.utils.enums.frostresistance.FrostResistance;
import application.utils.enums.grades.GradesConcrete;
import application.utils.enums.waterproof.Waterproof;
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
public class ConcreteGradeDTO {
    private int id;
    private GradesConcrete gradesConcrete;
    private ConcreteClass concreteClass;
    private Waterproof waterproof;
    private FrostResistance frostResistance;
    private Set<Order> orderSet = new HashSet<>();
    private Set<Concrete> concretesSet = new HashSet<>();
    private Set<ConcretePrice> concretePriceSet = new HashSet<>();
    private Set<Mobility> mobilitySet = new HashSet<>();
    private Set<BuildingObject> objectSet = new HashSet<>();
}
