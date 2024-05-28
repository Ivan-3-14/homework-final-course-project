package application.services.interfaces;

import application.DTO.usersDTO.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO readUser(Long userId);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(Long userId);

    UserDTO checkExistUser(UserDTO userDTO);

    UserDTO getUserByEmail(String email);

    UserDTO getUserByEmailAndPassword(String email, String password);

    UserDTO changePassword(String password1, String password2, Long currentUserId);

    List<UserDTO> getAllManager();

}
