package application.entity.order;

import application.entity.concreteentities.Concrete;
import application.entity.concreteentities.ConcreteGrade;
import application.entity.concreteentities.Mobility;
import application.entity.enums.orderstatus.OrderStatus;
import application.entity.object.BuildingObject;
import application.entity.users.Manager;
import application.entity.users.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Builder
@Data
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_create")
    private Timestamp orderTimeCreate = new Timestamp(System.currentTimeMillis());

    @Column(name = "delivery_date")
    private Date dateOfDelivery;

    @Column(name = "delivery_time")
    private Time timeOfDelivery;

    @Column(name = "concrete_volume")
    private Double volumeOfConcrete;

    @Column(name = "comment")
    private String comment;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    @JoinColumn(name = "concrete_id")
    private Concrete concrete;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "concrete_grade_id")
    private ConcreteGrade concreteGrade;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "mobility_id")
    private Mobility mobility;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "building_object_id")
    private BuildingObject buildingObject;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
