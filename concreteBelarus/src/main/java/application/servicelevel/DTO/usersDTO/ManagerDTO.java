package application.servicelevel.DTO.usersDTO;

import application.datalevel.entities.users.User;
import application.servicelevel.DTO.orderDTO.OrderDTO;
import application.utils.enums.roles.Roles;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDTO  {

    private int id;

    private UserDTO user;

    private Roles role = Roles.MANAGER;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrderDTO> orderSet = new HashSet<>();
}
