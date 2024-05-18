package application.entity.autotransportation;

import application.entity.enums.cartype.CarType;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "capacity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoCapacity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_type")
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Column(name = "auto_capacity")
    private Integer autoCapacity;

    @Column(name = "delivery_coeff")
    private Integer deliveryCoefficient;

    @Column(name = "del_coeff_aft50")
    private Integer deliveryCoefficientAft50;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "auto_price_id")
    private AutoPrice autoPrice;

}
