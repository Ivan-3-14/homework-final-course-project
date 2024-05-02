package application.datalevel.entities.autotransportation;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "auto_price")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Double price;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "autoPrice")
    private AutoCapacity capacity;
}
