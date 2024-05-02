package application.datalevel.entities.concreteentities;

import application.datalevel.entities.order.Order;
import application.utils.enums.mobilityvalue.MobilityValue;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@Table(name = "concrete_mobility")
@AllArgsConstructor
@NoArgsConstructor
public class Mobility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @Enumerated(EnumType.STRING)
    private MobilityValue mobilityValue;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mobility")
    private Set<Order> orderSet = new HashSet<>();

    @ToString.Exclude
    @Builder.Default
    @ManyToMany(mappedBy = "mobilitySet", fetch = FetchType.LAZY)
    private Set<ConcreteGrade> concreteGradeSet = new HashSet<>();
}
