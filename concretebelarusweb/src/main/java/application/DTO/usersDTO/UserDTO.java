package application.DTO.usersDTO;

import application.DTO.objectDTO.BuildingObjectDTO;
import application.DTO.orderDTO.OrderDTO;
import application.entity.enums.roles.Roles;
import lombok.*;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String telephoneNumber;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private Roles role = Roles.USER;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrderDTO> orderSet;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<BuildingObjectDTO> buildingObjectSet;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ManagerDTO manager;
}
