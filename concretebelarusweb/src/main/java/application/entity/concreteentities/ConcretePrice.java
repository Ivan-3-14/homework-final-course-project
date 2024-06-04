package application.entity.concreteentities;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@Entity
@Table(name = "concrete_price")
@AllArgsConstructor
@NoArgsConstructor
public class ConcretePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double price;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "concrete_id")
    private Concrete concrete;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "concrete_grade_id")
    private ConcreteGrade concreteGrade;
}
