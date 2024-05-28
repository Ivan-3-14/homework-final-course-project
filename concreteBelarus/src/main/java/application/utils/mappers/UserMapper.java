package application.utils.mappers;

import application.datalevel.entities.users.User;
import application.servicelevel.DTO.usersDTO.UserDTO;

public class UserMapper implements Mapper<UserDTO, User> {

    private final ManagerForUserMapper managerForUserMapper = new ManagerForUserMapper();

    @Override
    public User dtoToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .telephoneNumber(userDTO.getTelephoneNumber())
                .role(userDTO.getRole())
                .manager(managerForUserMapper.dtoToEntity(userDTO.getManager()))
                .build();
    }

    @Override
    public UserDTO entityToDTO(User user) {
        if (user == null) {
            return null;
        }
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .surname(user.getSurname())
                .telephoneNumber(user.getTelephoneNumber())
                .role(user.getRole())
                .manager(managerForUserMapper.entityToDTO(user.getManager()))
                .build();
    }
}
