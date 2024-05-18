package application.DTO.usersDTO;

import application.DTO.objectDTO.BuildingObjectDTO;
import application.DTO.orderDTO.OrderDTO;
import application.entity.enums.roles.Roles;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String telephoneNumber;

    private String email;

    private String password;

    private Roles role = Roles.USER;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrderDTO> orderSet = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<BuildingObjectDTO> buildingObjectSet = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ManagerDTO manager;
}
