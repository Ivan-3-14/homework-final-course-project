package application.converter;

import application.DTO.usersDTO.ManagerDTO;
import application.DTO.usersDTO.UserDTO;
import application.entity.enums.roles.Roles;
import application.entity.users.Manager;
import application.entity.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ManagerMapper {

    @Mapping(target = "id")
    @Mapping(expression = "java(getUser(managerDTO))", target = "user")
    Manager toEntity(ManagerDTO managerDTO);

    @Mapping(target = "id")
    @Mapping(expression = "java(getUserDTO(manager))", target = "user")
    ManagerDTO toDTO(Manager manager);

    default UserDTO getUserDTO(Manager manager) {
        if (manager != null && manager.getUser() != null) {
            return UserDTO.builder()
                    .id(manager.getUser().getId())
                    .role(Roles.MANAGER)
                    .build();
        }
        return new UserDTO();
    }

    default User getUser(ManagerDTO managerDTO) {
        if (managerDTO != null && managerDTO.getUser() != null) {
            return User.builder()
                    .id(managerDTO.getUser().getId())
                    .role(Roles.MANAGER)
                    .build();
        }
        return new User();
    }
}
