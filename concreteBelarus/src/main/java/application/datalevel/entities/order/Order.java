package application.datalevel.entities.order;

import application.datalevel.entities.autotransportation.AutoTransport;
import application.datalevel.entities.concreteentities.Concrete;
import application.datalevel.entities.concreteentities.ConcreteGrade;
import application.datalevel.entities.concreteentities.Mobility;
import application.datalevel.entities.object.BuildingObject;
import application.datalevel.entities.users.Manager;
import application.datalevel.entities.users.User;
import application.utils.enums.orderstatus.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "time_create")
    private Timestamp orderTimeCreate = new Timestamp(System.currentTimeMillis());

    @Column (name = "delivery_date")
    private Date dateOfDelivery;

    @Column (name = "delivery_time")
    private Time timeOfDelivery;

    @Column (name = "concrete_volume")
    private Double volumeOfConcrete;

    @Column (name = "comment")
    private String comment;
    
    @Column (name = "phone_number")
    private String numberOfPhone;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "concrete_id")
    private Concrete concrete;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "concrete_grade_id")
    private ConcreteGrade concreteGrade;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "mobility_id")
    private Mobility mobility;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "building_object_id")
    private BuildingObject buildingObject;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "orderSet", fetch = FetchType.LAZY)
    private Set<AutoTransport> autoTransportSet = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
