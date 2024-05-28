package application.converter;

import application.DTO.usersDTO.ManagerDTO;
import application.DTO.usersDTO.UserDTO;
import application.entity.enums.roles.Roles;
import application.entity.users.Manager;
import application.entity.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "id")
    @Mapping(target = "orderSet", ignore = true)
    @Mapping(target = "buildingObjectSet", ignore = true)
    @Mapping(expression = "java(getManager(userDTO))", target = "manager")
    User toEntity(UserDTO userDTO);

    @Mapping(target = "id")
    @Mapping(target = "orderSet", ignore = true)
    @Mapping(target = "buildingObjectSet", ignore = true)
    @Mapping(expression = "java(getManagerDTO(user))", target = "manager")
    UserDTO toDTO(User user);

    default Manager getManager(UserDTO userDTO) {
        if (userDTO != null && userDTO.getManager() != null) {
            return Manager.builder()
                    .id(userDTO.getManager().getId())
                    .role(Roles.MANAGER)
                    .build();
        }
        return null;
    }

    default ManagerDTO getManagerDTO(User user) {
        if (user != null && user.getManager() != null) {
            return ManagerDTO.builder()
                    .id(user.getManager().getId())
                    .role(Roles.MANAGER)
                    .build();
        }
        return null;
    }
}
