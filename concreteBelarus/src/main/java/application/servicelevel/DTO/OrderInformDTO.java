package application.servicelevel.DTO;

import application.servicelevel.DTO.objectDTO.BuildingObjectDTO;
import application.servicelevel.DTO.orderDTO.OrderDTO;
import application.servicelevel.DTO.usersDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInformDTO {

    private UserDTO userDTO;

    private OrderDTO orderDTO;

    private BuildingObjectDTO buildingObjectDTO;

    private String name;

    private String surname;

    private String telephoneNumber;
}
