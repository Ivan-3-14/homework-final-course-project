package application.servicelevel.services.impl;

import application.datalevel.DAO.implementations.users.UserDAOImpl;
import application.datalevel.DAO.interfaces.users.UserDAO;
import application.datalevel.entities.users.User;
import application.servicelevel.DTO.usersDTO.ManagerDTO;
import application.servicelevel.DTO.usersDTO.UserDTO;
import application.servicelevel.services.interfaces.UserServiceInt;
import application.utils.enums.roles.Roles;
import application.utils.functionalinterface.MyInterfaceToDAO;
import application.utils.functionalinterface.UtilsInterface;
import application.utils.mappers.ManagerForUserMapper;
import application.utils.mappers.UserMapper;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static application.utils.constant.ConstantsContainer.WRONG;

public class UserService implements UserServiceInt {

    private final UserDAO userDAOImpl = new UserDAOImpl();
    private final UserMapper userMapper = new UserMapper();
    private final ManagerForUserMapper managerForUserMapper = new ManagerForUserMapper();

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        MyInterfaceToDAO<UserDTO> betweenBeginAndCommitted = () ->
                userMapper.entityToDTO(userDAOImpl.create(userMapper.dtoToEntity(userDTO)));
        return UtilsInterface.superMethodInterface(betweenBeginAndCommitted, userDAOImpl.getEntityManager());
    }

    public UserDTO createNewUser(UserDTO userDTO, HttpServletRequest req, HttpServletResponse resp, String path) {
        MyInterfaceToDAO<UserDTO> betweenBeginAndCommitted = () -> {
            if (alreadyExists(userDTO)) {
                req.setAttribute(WRONG, true);
                req.getServletContext().getRequestDispatcher(path).forward(req, resp);
                return null;
            }
            userDTO.setPassword(getHashedPassword(userDTO.getPassword()));
            return userMapper.entityToDTO(userDAOImpl.create(userMapper.dtoToEntity(userDTO)));
        };
        return UtilsInterface.superMethodInterface(betweenBeginAndCommitted, userDAOImpl.getEntityManager());
    }

    public UserDTO readUser(int id) {
        MyInterfaceToDAO<UserDTO> betweenBeginAndCommitted = () -> {
            User user = userDAOImpl.read(id);
            if (user == null) {
                return null;
            }
            return userMapper.entityToDTO(user);
        };
        return UtilsInterface.superMethodInterface(betweenBeginAndCommitted, userDAOImpl.getEntityManager());
    }

    public UserDTO updateUser(int id, UserDTO userDTO) {
        MyInterfaceToDAO<UserDTO> betweenBeginAndCommitted = () ->
                userMapper.entityToDTO(userDAOImpl.update(id, userMapper.dtoToEntity(userDTO)));
        return UtilsInterface.superMethodInterface(betweenBeginAndCommitted, userDAOImpl.getEntityManager());
    }

    public Integer deleteUser(int id) {
        MyInterfaceToDAO<Integer> betweenBeginAndCommitted = () -> userDAOImpl.delete(id);
        return UtilsInterface.superMethodInterface(betweenBeginAndCommitted, userDAOImpl.getEntityManager());
    }

    public UserDTO getUserByLoginAndPassword(String email, String password) {
        User user = userDAOImpl.getUserByEmail(email);
        UserDTO userDTO;
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            userDTO = userMapper.entityToDTO(user);
            if (userDTO.getManager() != null) {
              ManagerDTO managerDTO =  managerForUserMapper.entityToDTO(user.getManager());
              managerDTO.setUser(userDTO);
            }
            return userDTO;
        }
        return null;
    }

    public UserDTO getUserByPassword(String password) {
        return userMapper.entityToDTO(userDAOImpl.getUserByPassword(getHashedPassword(password)));
    }

    public boolean alreadyExists(UserDTO userDTO) {
        return getUserByEmail(userDTO.getEmail()) != null;
    }

    public UserDTO checkExistUser(UserDTO userDTO, String name, String surname, String numberOfPhone) {
        if (userDTO == null) {
            userDTO = checkSuperUserByPhoneNumber(numberOfPhone);
            if (userDTO == null) {
                userDTO = createUser(
                        UserDTO.builder()
                                .role(Roles.SUPERUSER)
                                .telephoneNumber(numberOfPhone)
                                .name(name)
                                .surname(surname)
                                .build()
                );
            }
        }
        return userDTO;
    }

    public UserDTO updateUserPassword(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        userDTO.setManager(null);
        userDTO.setPassword(getHashedPassword(userDTO.getPassword()));
        return updateUser(userDTO.getId(), userDTO);
    }

    public UserDTO createNewManager(UserDTO userDTO, HttpServletRequest req, HttpServletResponse resp, String path)
            throws ServletException, IOException {
            if (alreadyExists(userDTO)) {
                req.setAttribute(WRONG, true);
                req.getServletContext().getRequestDispatcher(path).forward(req, resp);
                return null;
            }
            userDTO.setPassword(getHashedPassword(userDTO.getPassword()));
            return userMapper.entityToDTO(userDAOImpl.create(userMapper.dtoToEntity(userDTO)));
    }

    private UserDTO getUserByEmail(String email) {
        User user = userDAOImpl.getUserByEmail(email);
        if (user == null) {
            return null;
        }
        return userMapper.entityToDTO(user);
    }

    private UserDTO checkSuperUserByPhoneNumber(String phoneNumber) {
        User user = userDAOImpl.getSuperUserByPhoneNumber(phoneNumber);
        if (user == null) {
            return null;
        }
        return userMapper.entityToDTO(user);
    }

    public String getHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
