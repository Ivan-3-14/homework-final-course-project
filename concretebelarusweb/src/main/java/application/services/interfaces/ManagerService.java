package application.services.interfaces;

import application.DTO.usersDTO.ManagerDTO;
import application.DTO.usersDTO.UserDTO;

public interface ManagerService {

    ManagerDTO readManagerDTO(Long managerId);

    ManagerDTO createManager(UserDTO userDTO);
}
