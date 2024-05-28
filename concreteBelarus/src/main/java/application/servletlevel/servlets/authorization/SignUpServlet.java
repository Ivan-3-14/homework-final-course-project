package application.servletlevel.servlets.authorization;

import application.servicelevel.DTO.usersDTO.ManagerDTO;
import application.servicelevel.DTO.usersDTO.UserDTO;
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

@WebServlet(name = "SignUpServlet", urlPatterns = {"/signUp"})
public class SignUpServlet extends HttpServlet {

    private final UserServiceInt userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(SIGN_UP_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter(PASSWORD);
        String email = req.getParameter(EMAIL);
        UserDTO userDTO = null;
        if (email != null && password != null) {
             userDTO = userService.getUserByLoginAndPassword(email, password);
        }

        if (userDTO == null) {
            req.setAttribute(WRONG, true);
            doGet(req, resp);
            return;
        }

        req.getSession().setAttribute(CURRENT, userDTO);
        req.getSession().setAttribute(EMAIL, email);
        req.getSession().setAttribute(ROLE, userDTO.getRole());

        if (Roles.MANAGER.equals(userDTO.getRole())) {
            if (userDTO.getManager() != null) {
                ManagerDTO managerDTO = userDTO.getManager();
                managerDTO.setUser(userDTO);
                req.getSession().setAttribute(MANAGER_ID, managerDTO.getId());
                getServletContext().getRequestDispatcher(MAIN_PAGE_MANAGER_JSP).forward(req, resp);
            }
        } else if (Roles.USER.equals(userDTO.getRole())) {
            getServletContext().getRequestDispatcher(MAIN_PAGE_USER).forward(req, resp);
        } else  if (Roles.ADMIN.equals(userDTO.getRole())) {
            getServletContext().getRequestDispatcher(ADMIN_MAIN_PAGE).forward(req, resp);
        }
    }
}
