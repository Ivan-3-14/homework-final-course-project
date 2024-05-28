package application.datalevel.DAO.implementations.users;

import application.datalevel.DAO.implementations.DAOImpl;
import application.datalevel.DAO.interfaces.users.UserDAO;
import application.datalevel.entities.users.User;
import application.utils.enums.roles.Roles;
import application.utils.hibernate.HibernateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static application.utils.constant.ConstantsContainer.*;

public class UserDAOImpl extends DAOImpl<User> implements UserDAO {

    private final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public User getUserByEmail(String email) {
        List<User> users;
        String query = String.format("%s%s%s", GET_USER_BY_EMAIL_QUERY, email, END_QUERY);
        users = HibernateUtils.getEntityManager().createNativeQuery(query, User.class).getResultList();
        if (users.isEmpty()) {
            logger.log(Level.INFO, BY_EMAIL_USER_NOT_FOUND);
            return null;
        } else {
            return users.stream().findFirst().get();
        }
    }

    @Override
    public User getUserByPassword(String password) {
        String query = String.format("%s%s%s", GET_USER_BY_PASSWORD_QUERY, password, END_QUERY);
        List<User> users = HibernateUtils.getEntityManager().createNativeQuery(query, User.class).getResultList();
        if (users.isEmpty()) {
            logger.log(Level.INFO, BY_PASSWORD_USER_NOT_FOUND);
            return null;
        } else {
            getEntityManager().getTransaction().commit();
            return users.stream().findFirst().get();
        }
    }

    @Override
    public List<User> getUsersByLoginAndPassword(String email, String password) {
        String query = String.format("%s%s%s%s%s", GET_USER_BY_EMAIL_QUERY, email, AND_PASSWORD, password, END_QUERY2);
        List<User> users = HibernateUtils.getEntityManager().createNativeQuery(query, User.class).getResultList();
        if (users == null || users.isEmpty()) {
            logger.log(Level.INFO, BY_EMAIL_AND_PASSWORD_USER_NOT_FOUND);
            return null;
        }
        return users;
    }

    @Override
    public User getSuperUserByPhoneNumber(String phoneNumber) {
        String query = String.format(
                "%s%s%s%s%s", GET_USER_BY_NUMBER, phoneNumber, AND_ROLE, Roles.SUPERUSER,  END_QUERY2);
        List<User> users = HibernateUtils.getEntityManager().createNativeQuery(query, User.class).getResultList();

        if (users.isEmpty()) {
            logger.log(Level.INFO, THIS_SUPER_USER_NOT_FOUND);
            return null;
        }
        return users.stream().findFirst().get();
    }

    public List<User> getAllUserWithRoleManager() {
        List<User> users = HibernateUtils.getEntityManager().createNativeQuery(
                GET_USER_BY_MANAGER_QUERY, User.class).getResultList();

        if (users.isEmpty()) {
            logger.log(Level.INFO, MANAGER_NOT_FOUND);
            return new ArrayList<>();
        }
        return users;
    }
}
