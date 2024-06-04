package application.DTO.usersDTO;

import application.DTO.objectDTO.BuildingObjectDTO;
import application.DTO.orderDTO.OrderDTO;
import application.entity.enums.roles.Roles;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Set;

import static application.utils.Constant.EMAIL_REGEX;
import static application.utils.Constant.PHONE_NUMBER_REGEX;

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

    @Pattern(regexp = PHONE_NUMBER_REGEX)
    private String telephoneNumber;

    @Pattern(regexp = EMAIL_REGEX)
    private String email;

    @NotBlank
    private String password;

    private Roles role;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrderDTO> orderSet;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<BuildingObjectDTO> buildingObjectSet;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ManagerDTO manager;

    private Set<Roles> roles;
}
