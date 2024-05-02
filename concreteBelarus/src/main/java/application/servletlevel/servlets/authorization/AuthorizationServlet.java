package application.servletlevel.servlets.authorization;

import application.servicelevel.DTO.usersDTO.UserDTO;
import application.servicelevel.services.impl.UserService;
import application.utils.enums.roles.Roles;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static application.utils.constant.ConstantsContainer.*;

@WebServlet(name = "AuthorizationServlet", urlPatterns = {"/authorization"})
public class AuthorizationServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(AUTH_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter(NAME);
        String surname = req.getParameter(SURNAME);
        String password = req.getParameter(PASSWORD);
        String email = req.getParameter(EMAIL);
        String telephoneNumber = req.getParameter(TEL_NUMBER);

        UserDTO userDTO = UserDTO.builder()
                .name(name)
                .surname(surname)
                .password(password)
                .email(email)
                .telephoneNumber(telephoneNumber)
                .role(Roles.USER)
                .build();

        UserDTO result = userService.createNewUser(userDTO, req, resp, AUTH_JSP);

        if (Roles.USER.equals(userDTO.getRole())) {
            req.getSession().setAttribute(CURRENT, result);
            getServletContext().getRequestDispatcher(MAIN_PAGE_USER).forward(req, resp);
        }
    }
}
