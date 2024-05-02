package application.servletlevel.servlets.authorization.password;

import application.servicelevel.DTO.usersDTO.ManagerDTO;
import application.servicelevel.DTO.usersDTO.UserDTO;
import application.servicelevel.services.impl.ManagerService;
import application.servicelevel.services.impl.UserService;
import application.servicelevel.services.interfaces.UserServiceInt;
import application.utils.enums.roles.Roles;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static application.utils.constant.ConstantsContainer.*;

@WebServlet(name = "ChangePassword", urlPatterns = {"/changePass"})
public class ChangePassword extends HttpServlet {

    private final UserServiceInt userService = new UserService();
    private final ManagerService managerService = ManagerService.getINSTANCE();
    private UserDTO changeUser = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String password = req.getParameter(PASSWORD);
        String email = req.getParameter(EMAIL);

        if (email != null && password != null) {
            changeUser = userService.getUserByLoginAndPassword(email, password);
        }
        if (changeUser == null) {
            getServletContext().getRequestDispatcher(SIGN_UP).forward(req, resp);
        }
        getServletContext().getRequestDispatcher(CHANGE_PASSWORD_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String newPass = req.getParameter(NEW_PASSWORD);
        String newPass2 = req.getParameter(NEW_PASSWORD2);

        if (!newPass.equals(newPass2)) {
            req.setAttribute(WRONG, true);
            doGet(req, resp);
            return;
        }
        if (changeUser == null) {
            getServletContext().getRequestDispatcher(FORGOT_PASSWORD_JSP).forward(req, resp);
        } else {
            changeUser.setPassword(newPass);
            UserDTO newUser = userService.updateUserPassword(changeUser);

            int managerDTOId = (Integer) req.getSession().getAttribute(MANAGER_ID);
            ManagerDTO managerDTO = managerService.readManager(managerDTOId);
            managerDTO.setUser(newUser);
            newUser.setManager(managerDTO);

            req.getSession().setAttribute(CURRENT, newUser);
            req.getSession().setAttribute(PASSWORD, newUser.getPassword());

            if (Roles.USER.equals(newUser.getRole())) {
                getServletContext().getRequestDispatcher(MAIN_PAGE_USER_JSP).forward(req, resp);
            } else  if (Roles.ADMIN.equals(newUser.getRole())) {
                getServletContext().getRequestDispatcher(MAIN_PAGE_ADMIN_JSP).forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher(MAIN_PAGE_MANAGER_JSP).forward(req, resp);
            }
        }
    }
}
