package application.datalevel.entities.object;

import application.datalevel.entities.order.Order;
import application.datalevel.entities.concreteentities.Concrete;
import application.datalevel.entities.concreteentities.ConcreteGrade;
import application.datalevel.entities.users.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@Table(name = "building_object")
@AllArgsConstructor
@NoArgsConstructor
public class BuildingObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "object_name")
    private String nameOfObject;

    @Column (name = "object_distance")
    private Double distanceToObject;

    @ManyToMany(mappedBy = "objectSet", fetch = FetchType.LAZY)
    private Set<Concrete> concretesSet = new HashSet<>();

    @ManyToMany(mappedBy = "objectSet", fetch = FetchType.LAZY)
    private Set<ConcreteGrade> concreteGradeSet = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "buildingObject")
    private Set<Order> orderSet = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
