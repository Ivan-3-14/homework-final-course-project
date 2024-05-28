package application.datalevel.DAO.interfaces.users;

import application.datalevel.DAO.interfaces.DAO;
import application.datalevel.entities.users.User;

import javax.persistence.EntityManager;
import java.util.List;

public interface UserDAO extends DAO<User> {

    User getUserByEmail(String email);

    User getUserByPassword(String password);

    List<User> getUsersByLoginAndPassword(String email, String password);

    User getSuperUserByPhoneNumber(String phoneNumber);

    List<User> getAllUserWithRoleManager();

    EntityManager getEntityManager();
}
