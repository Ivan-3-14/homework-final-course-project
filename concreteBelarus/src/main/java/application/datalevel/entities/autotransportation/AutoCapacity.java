package application.datalevel.entities.autotransportation;

import application.utils.enums.cartype.CarType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "capacity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoCapacity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "car_type")
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Column(name = "auto_capacity")
    private Integer autoCapacity;

    @Column(name = "delivery_coeff")
    private Integer deliveryCoefficient;

    @Column(name = "del_coef_aft50")
    private Integer deliCoeffAft50;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "auto_price_id")
    private AutoPrice autoPrice;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "capacity")
    private Set<AutoTransport> autoTransport = new HashSet<>();
}
