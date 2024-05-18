package application.DTO.concreteDTO;

import application.entity.concreteentities.Concrete;
import application.entity.concreteentities.ConcreteGrade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcretePriceDTO {

    private Long id;

    private Double price;

    private Concrete concrete;

    private ConcreteGrade concreteGrade;
}
