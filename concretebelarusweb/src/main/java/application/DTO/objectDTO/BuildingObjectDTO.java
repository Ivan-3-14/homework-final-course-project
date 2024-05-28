package application.DTO.objectDTO;

import application.DTO.concreteDTO.ConcreteDTO;
import application.DTO.concreteDTO.ConcreteGradeDTO;
import application.DTO.orderDTO.OrderDTO;
import application.DTO.usersDTO.UserDTO;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingObjectDTO {

    private Long id;

    @NotNull
    private String nameOfObject;

    @NotNull
    private Double distanceToObject;

    @EqualsAndHashCode.Exclude
    private UserDTO user;

    @ToString.Exclude
    private Set<ConcreteDTO> concretesSet = new HashSet<>();

    @ToString.Exclude
    private Set<ConcreteGradeDTO> concreteGradeSet = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrderDTO> orderSet = new HashSet<>();

}
