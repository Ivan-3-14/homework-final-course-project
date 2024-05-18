package application.DTO;

import application.DTO.objectDTO.BuildingObjectDTO;
import application.DTO.orderDTO.OrderDTO;
import application.DTO.usersDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterOrderInform {

    private UserDTO userDTO;

    private OrderDTO orderDTO;

    private BuildingObjectDTO buildingObjectDTO;

    private String name;

    private String surname;

    private String telephoneNumber;
}
