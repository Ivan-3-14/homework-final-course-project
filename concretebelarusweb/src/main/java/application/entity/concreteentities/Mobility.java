package application.entity.concreteentities;

import application.entity.enums.mobilityvalue.MobilityValue;
import application.entity.order.Order;
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
    private Long id;

    @Column(name = "mobility_value")
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
