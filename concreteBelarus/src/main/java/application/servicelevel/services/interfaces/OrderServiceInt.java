package application.servicelevel.services.interfaces;

import application.servicelevel.DTO.OrderInformDTO;
import application.servicelevel.DTO.objectDTO.BuildingObjectDTO;
import application.servicelevel.DTO.orderDTO.OrderDTO;
import application.servicelevel.DTO.usersDTO.UserDTO;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public interface OrderServiceInt {

    OrderDTO creatOrder(OrderDTO orderDTO);

    OrderDTO checkAndCreateOrder(UserDTO userDTO, OrderDTO orderDTO, BuildingObjectDTO buildingObjectDTO,
                                 String name, String surname, String numberOfPhone
    ) throws ServletException, IOException;

    OrderDTO updateOrder(OrderInformDTO orderInformDTO, int id);

    OrderDTO readOrderDTO(int id);

    Integer deleteOrder(int orderId);

    List<OrderDTO> orderPaginationList(int id, int start, int limit);

    int countOfPage(int id);
}
