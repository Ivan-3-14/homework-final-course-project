package application.servicelevel.DTO.concreteDTO;

import application.datalevel.entities.concreteentities.Concrete;
import application.datalevel.entities.concreteentities.ConcreteGrade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcretePriceDTO {

    private int id;
    private Double price;
    private Concrete concrete;
    private ConcreteGrade concreteGrade;
}
