package application.converter;

import application.DTO.orderDTO.OrderDTO;
import application.entity.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderMapper {

    @Mapping(target = "id")
    Order toEntity(OrderDTO orderDTO);

    @Mapping(target = "id")
    OrderDTO toDTO(Order order);
}
