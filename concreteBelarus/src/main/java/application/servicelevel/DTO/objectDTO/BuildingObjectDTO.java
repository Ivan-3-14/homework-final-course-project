package application.servicelevel.DTO.objectDTO;

import application.servicelevel.DTO.concreteDTO.ConcreteDTO;
import application.servicelevel.DTO.concreteDTO.ConcreteGradeDTO;
import application.servicelevel.DTO.orderDTO.OrderDTO;
import application.servicelevel.DTO.usersDTO.UserDTO;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingObjectDTO {

    private int id;

    private String nameOfObject;

    private Double distanceToObject;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserDTO user;

    private Set<ConcreteDTO> concretesSet = new HashSet<>();

    private Set<ConcreteGradeDTO> concreteGradeSet = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrderDTO> orderSet = new HashSet<>();

}
