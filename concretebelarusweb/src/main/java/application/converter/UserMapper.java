package application.converter;

import application.DTO.usersDTO.UserDTO;
import application.entity.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "id")
    User toEntity(UserDTO userDTO);

    @Mapping(target = "id")
    UserDTO toDTO(User user);
}
