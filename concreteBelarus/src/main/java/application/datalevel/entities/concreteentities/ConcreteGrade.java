package application.datalevel.entities.concreteentities;

import application.datalevel.entities.object.BuildingObject;
import application.datalevel.entities.order.Order;
import application.utils.enums.classess.ConcreteClass;
import application.utils.enums.frostresistance.FrostResistance;
import application.utils.enums.grades.GradesConcrete;
import application.utils.enums.waterproof.Waterproof;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@Table(name = "concrete_grade")
@AllArgsConstructor
@NoArgsConstructor

public class ConcreteGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nomination")
    @Enumerated(EnumType.STRING)
    private GradesConcrete gradesConcrete;

    @Column(name = "concrete_class")
    @Enumerated(EnumType.STRING)
    private ConcreteClass concreteClass;

    @Column(name = "max_waterproof")
    @Enumerated(EnumType.STRING)
    private Waterproof waterproof;

    @Column(name = "max_frost_resistance")
    @Enumerated(EnumType.STRING)
    private FrostResistance frostResistance;

    @OneToMany(mappedBy = "concreteGrade")
    private Set<Order> orderSet = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "gradesSet", fetch = FetchType.LAZY)
    private Set<Concrete> concretesSet = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "concreteGrade")
    private Set<ConcretePrice> concretePriceSet = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "concrete_grade_mobility",
            joinColumns = {@JoinColumn(name = "concrete_grade_id")},
            inverseJoinColumns = {@JoinColumn(name = "mobility_id")})
    private Set<Mobility> mobilitySet = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "object_concrete_grade",
            joinColumns = {@JoinColumn(name = "concrete_grade_id")},
            inverseJoinColumns = {@JoinColumn(name = "bulding_object_id")})
    private Set<BuildingObject> objectSet = new HashSet<>();
}
