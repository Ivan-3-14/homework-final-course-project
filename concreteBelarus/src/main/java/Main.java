import application.datalevel.DAO.implementations.users.UserImpl;
import application.datalevel.DAO.interfaces.users.UserDAO;
import application.datalevel.entities.users.Manager;
import application.datalevel.entities.users.User;
import application.servicelevel.DTO.orderDTO.OrderDTO;
import application.servicelevel.services.impl.BuildingObjectService;
import application.servicelevel.services.interfaces.BuildingObjectServiceInt;
import application.utils.functionalinterface.MyInterfaceToDAO;
import application.utils.functionalinterface.UtilsInterface;
import org.mindrot.jbcrypt.BCrypt;

public class Main {
    public static void main(String[] args) {
//        String password = "manager";
//        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
//        System.out.println(hashedPassword);

//        BuildingObjectServiceInt buildingObjectService = new BuildingObjectService();
//        System.out.println(buildingObjectService.countOfPage(2));
        UserDAO userDAO = new UserImpl();
        userDAO.getEntityManager().getTransaction().begin();
        User user = userDAO.read(1);
        userDAO.getEntityManager().getTransaction().commit();

        System.out.println(user.getManager());
    }
}
