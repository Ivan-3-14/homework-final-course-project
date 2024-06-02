package application.DTO.concreteDTO;

import application.entity.enums.aggregate.Aggregate;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcreteDTO {

    private Long id;

    private Aggregate aggregate;

}
