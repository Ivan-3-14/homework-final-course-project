package application.converter;

import application.DTO.objectDTO.BuildingObjectDTO;
import application.DTO.orderDTO.OrderDTO;
import application.DTO.usersDTO.UserDTO;
import application.entity.object.BuildingObject;
import application.entity.order.Order;
import application.entity.users.User;
import liquibase.repackaged.net.sf.jsqlparser.statement.create.table.CreateTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface BuildingObjectMapper {

    ManagerMapper managerMapper = Mappers.getMapper(ManagerMapper.class);

    @Mapping(target = "id")
    @Mapping(expression = "java(getOrderSet(buildingObjectDTO))", target = "orderSet")
    @Mapping(expression = "java(getUser(buildingObjectDTO))", target = "user")
    BuildingObject toEntity(BuildingObjectDTO buildingObjectDTO);

    @Mapping(target = "id")
    @Mapping(expression = "java(getOrderDTOSet(buildingObject))", target = "orderSet")
    @Mapping(expression = "java(getUserDTO(buildingObject))", target = "user")
    BuildingObjectDTO toDTO(BuildingObject buildingObject);

    default Set<OrderDTO> getOrderDTOSet(BuildingObject buildingObject) {
        Set<OrderDTO> orderDTOSet = new HashSet<>();
        if (buildingObject != null && buildingObject.getOrderSet() != null) {
            buildingObject.getOrderSet().forEach(o -> {
                orderDTOSet.add(OrderDTO.builder()
                        .id(o.getId())
                        .volumeOfConcrete(o.getVolumeOfConcrete())
                        .build());
            });
        }
           return orderDTOSet;
    }

    default Set<Order> getOrderSet(BuildingObjectDTO buildingObjectDTO) {
        Set<Order> orderSet = new HashSet<>();
        if (buildingObjectDTO != null && buildingObjectDTO.getOrderSet() != null) {
            buildingObjectDTO.getOrderSet().forEach(o -> {
                orderSet.add(Order.builder()
                        .id(o.getId())
                        .volumeOfConcrete(o.getVolumeOfConcrete())
                        .build());
            });
        }
        return orderSet;
    }

    default UserDTO getUserDTO(BuildingObject buildingObject) {
        return UserDTO.builder()
                .id(buildingObject.getUser().getId())
                .name(buildingObject.getUser().getName())
                .surname(buildingObject.getUser().getSurname())
                .email(buildingObject.getUser().getEmail())
                .password(buildingObject.getUser().getPassword())
                .manager(managerMapper.toDTO(buildingObject.getUser().getManager()))
                .telephoneNumber(buildingObject.getUser().getTelephoneNumber())
                .role(buildingObject.getUser().getRole())
                .build();
    }

    default User getUser(BuildingObjectDTO buildingObjectDTO) {
        return User.builder()
                .id(buildingObjectDTO.getUser().getId())
                .name(buildingObjectDTO.getUser().getName())
                .surname(buildingObjectDTO.getUser().getSurname())
                .email(buildingObjectDTO.getUser().getEmail())
                .password(buildingObjectDTO.getUser().getPassword())
                .manager(managerMapper.toEntity(buildingObjectDTO.getUser().getManager()))
                .telephoneNumber(buildingObjectDTO.getUser().getTelephoneNumber())
                .role(buildingObjectDTO.getUser().getRole())
                .buildingObjectSet(new HashSet<>())
                .build();
    }

}
