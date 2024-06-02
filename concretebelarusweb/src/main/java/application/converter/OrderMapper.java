package application.converter;

import application.DTO.objectDTO.BuildingObjectDTO;
import application.DTO.orderDTO.OrderDTO;
import application.DTO.usersDTO.ManagerDTO;
import application.DTO.usersDTO.UserDTO;
import application.entity.enums.roles.Roles;
import application.entity.order.Order;
import org.mapstruct.*;

@Mapper
public interface OrderMapper {

    @Mapping(target = "id")
    @Mapping(source = "concreteDTO", target = "concrete")
    @Mapping(source = "concreteGradeDTO", target = "concreteGrade")
    @Mapping(source = "mobilityDTO", target = "mobility")
    @Mapping(source = "buildingObjectDTO", target = "buildingObject")
    @Mapping(source = "userDTO", target = "user")
    @Mapping(source = "managerDTO", target = "manager")
    Order toEntity(OrderDTO orderDTO);

    @Mapping(target = "id")
    @Mapping(source = "concrete", target = "concreteDTO")
    @Mapping(source = "concreteGrade", target = "concreteGradeDTO")
    @Mapping(source = "mobility", target = "mobilityDTO")
    @Mapping(expression = "java(getManagerDTO(order))", target = "managerDTO")
    @Mapping(expression = "java(getBuildingObjectDTO(order))", target = "buildingObjectDTO")
    @Mapping(expression = "java(getUserDTO(order))", target = "userDTO")
    OrderDTO toDTO(Order order);

    default BuildingObjectDTO getBuildingObjectDTO(Order order) {
        return BuildingObjectDTO.builder()
                .id(order.getBuildingObject().getId())
                .nameOfObject(order.getBuildingObject().getNameOfObject())
                .distanceToObject(order.getBuildingObject().getDistanceToObject())
                .user(getUserDTO(order))
                .build();
    }

    default UserDTO getUserDTO(Order order) {
        return UserDTO.builder()
                .id(order.getUser().getId())
                .name(order.getUser().getName())
                .surname(order.getUser().getSurname())
                .telephoneNumber(order.getUser().getTelephoneNumber())
                .role(order.getUser().getRole())
                .email(order.getUser().getEmail())
                .build();
    }

    default ManagerDTO getManagerDTO(Order order) {
        if (order.getManager() != null) {
            return ManagerDTO.builder()
                    .id(order.getManager().getId())
                    .role(Roles.MANAGER)
                    .build();
        }
        return null;
    }
}
