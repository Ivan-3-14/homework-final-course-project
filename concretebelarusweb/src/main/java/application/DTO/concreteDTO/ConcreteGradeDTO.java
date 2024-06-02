package application.DTO.concreteDTO;

import application.entity.enums.classess.ConcreteClass;
import application.entity.enums.frostresistance.FrostResistance;
import application.entity.enums.grades.GradesConcrete;
import application.entity.enums.waterproof.Waterproof;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcreteGradeDTO {

    private Long id;

    private GradesConcrete gradesConcrete;

    private ConcreteClass concreteClass;

    private Waterproof waterproof;

    private FrostResistance frostResistance;

}
