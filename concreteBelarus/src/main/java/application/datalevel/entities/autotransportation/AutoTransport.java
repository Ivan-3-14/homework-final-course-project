package application.datalevel.entities.autotransportation;

import application.datalevel.entities.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "auto_transport")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class AutoTransport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "car_number")
    private String number;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "capacity_id")
    private AutoCapacity capacity;

    @ToString.Exclude
    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "orders_auto_transport",
            joinColumns = {@JoinColumn(name = "orders_id")},
            inverseJoinColumns = {@JoinColumn(name = "auto_transport_id")})
    private Set<Order> orderSet = new HashSet<>();
}
