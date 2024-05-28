package application.datalevel.DAO.implementations.users;

import application.datalevel.DAO.implementations.DAOImpl;
import application.datalevel.DAO.interfaces.users.ManagerDAO;
import application.datalevel.entities.users.Manager;

import java.util.logging.Logger;

public class ManagerDAOImpl extends DAOImpl<Manager> implements ManagerDAO {

    private final Logger logger = Logger.getLogger(ManagerDAOImpl.class.getName());

    @Override
    public Class<Manager> getEntityClass() {
        return Manager.class;
    }

}
