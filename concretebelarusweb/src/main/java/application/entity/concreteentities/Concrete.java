package application.entity.concreteentities;

import application.entity.enums.aggregate.Aggregate;
import application.entity.object.BuildingObject;
import application.entity.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@Table(name = "concrete")
@AllArgsConstructor
@NoArgsConstructor
public class Concrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aggregate")
    @Enumerated(EnumType.STRING)
    private Aggregate aggregate;

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "concrete_concrete_grade",
            joinColumns = {@JoinColumn(name = "concrete_id")},
            inverseJoinColumns = {@JoinColumn(name = "concrete_grade_id")})
    private Set<ConcreteGrade> gradesSet = new HashSet<>();

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "concrete")
    private Set<ConcretePrice> concretePriceSet = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "concrete")
    private Set<Order> orderSet;

    @Builder.Default
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "object_concrete",
            joinColumns = {@JoinColumn(name = "concrete_id")},
            inverseJoinColumns = {@JoinColumn(name = "object_id")})
    private Set<BuildingObject> objectSet = new HashSet<>();
}
