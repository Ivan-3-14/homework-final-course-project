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

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "delivery_id")
    private DeliveryCoefficient deliveryCoefficient;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "auto_price_id")
    private AutoPrice autoPrice;

}
