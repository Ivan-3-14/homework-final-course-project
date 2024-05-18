package application.DTO.concreteDTO;


import application.entity.concreteentities.Concrete;
import application.entity.concreteentities.ConcretePrice;
import application.entity.concreteentities.Mobility;
import application.entity.enums.classess.ConcreteClass;
import application.entity.enums.frostresistance.FrostResistance;
import application.entity.enums.grades.GradesConcrete;
import application.entity.enums.waterproof.Waterproof;
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
public class ConcreteGradeDTO {

    private Long id;

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
