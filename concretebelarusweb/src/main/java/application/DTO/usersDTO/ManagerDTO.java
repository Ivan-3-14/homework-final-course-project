package application.DTO.usersDTO;

import application.DTO.orderDTO.OrderDTO;
import application.entity.enums.roles.Roles;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDTO {

    private Long id;

    private UserDTO user;

    private Roles role = Roles.MANAGER;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrderDTO> orderSet = new HashSet<>();
}
