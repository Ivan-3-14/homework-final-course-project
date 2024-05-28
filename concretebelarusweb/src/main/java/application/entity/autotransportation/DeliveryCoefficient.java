package application.entity.autotransportation;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "delivery_coefficient")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryCoefficient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "del_coeff_before50")
    private Integer deliveryCoefficientBfr50;

    @Column(name = "del_coeff_aft50")
    private Integer deliveryCoefficientAft50;

}
