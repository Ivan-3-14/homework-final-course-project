package application.service.interfaces;

import application.dto.UserDTO;

import java.util.List;

public interface UserService {

   UserDTO getUserByLogin(String login);

   UserDTO createUser(UserDTO userDTO);

   List<UserDTO> getAllUserParticipantInProject(Long projectId);

   List<UserDTO> getAllUserNoParticipantInProject(Long projectId);

   List<UserDTO> addUserToProject(Long projectId, Long userId);

   List<UserDTO> deleteUserFromProject(Long projectId, Long userId);
}
