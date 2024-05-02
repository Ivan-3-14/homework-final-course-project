package application.datalevel.DAO.implementations.users;

import application.datalevel.DAO.implementations.DAOImpl;
import application.datalevel.DAO.interfaces.users.ManagerDAO;
import application.datalevel.DAO.interfaces.users.UserDAO;
import application.datalevel.entities.users.Manager;
import application.datalevel.entities.users.User;
import application.utils.enums.roles.Roles;
import application.utils.hibernate.HibernateUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static application.utils.constant.ConstantsContainer.*;

public class ManagerImpl extends DAOImpl<Manager> implements ManagerDAO {

    private final Logger logger = Logger.getLogger(ManagerImpl.class.getName());

    @Override
    public Class<Manager> getEntityClass() {
        return Manager.class;
    }

}
