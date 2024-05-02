package application.servicelevel.DTO.usersDTO;

import application.servicelevel.DTO.objectDTO.BuildingObjectDTO;
import application.servicelevel.DTO.orderDTO.OrderDTO;
import application.utils.enums.roles.Roles;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;

    private String name;

    private String surname;

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
