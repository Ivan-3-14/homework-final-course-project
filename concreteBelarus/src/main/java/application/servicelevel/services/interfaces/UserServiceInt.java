package application.servicelevel.services.interfaces;

import application.servicelevel.DTO.usersDTO.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserServiceInt {

    UserDTO createUser(UserDTO userDTO) throws ServletException, IOException;

    UserDTO createNewUser(UserDTO userDTO, HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException;

    UserDTO readUser(int id) throws ServletException, IOException;

    UserDTO updateUser(int id, UserDTO userDTO);

    Integer deleteUser(int id) throws ServletException, IOException;

    UserDTO getUserByLoginAndPassword(String email, String password);

    UserDTO getUserByPassword(String password);

    boolean alreadyExists(UserDTO userDTO);

    UserDTO updateUserPassword(UserDTO userDTO);

    UserDTO createNewManager(UserDTO userDTO, HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException;

    UserDTO checkExistUser(UserDTO userDTO, String name, String surname, String numberOfPhone) throws ServletException, IOException;
}
