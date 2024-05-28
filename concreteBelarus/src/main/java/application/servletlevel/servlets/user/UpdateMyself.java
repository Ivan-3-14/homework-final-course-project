package application.servletlevel.servlets.user;

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

@WebServlet(name = "UpdateMyself", urlPatterns = {"/updateMyself"})
public class UpdateMyself extends HttpServlet {

    private final UserServiceInt userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter(NAME);
        String surname = req.getParameter(SURNAME);
        String email = req.getParameter(EMAIL);
        String telephoneNumber = req.getParameter(TEL_NUMBER);
        Roles role = Roles.USER;

        UserDTO user = (UserDTO) req.getSession().getAttribute(CURRENT);

        if (user != null) {
            if (name == null || name.isEmpty()) {
                name = user.getName();
            }
            if (surname == null || surname.isEmpty()) {
                surname = user.getSurname();
            }
            if (email == null || email.isEmpty()) {
                email = user.getEmail();
            }
            if (telephoneNumber == null || telephoneNumber.isEmpty()) {
                telephoneNumber = user.getTelephoneNumber();
            }
            if (user.getRole() != null) {
                role = user.getRole();
            }

            UserDTO userDTO = UserDTO.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(user.getPassword())
                    .telephoneNumber(telephoneNumber)
                    .role(role)
                    .build();

            UserDTO result = userService.updateUser(user.getId(), userDTO);

            req.getSession().setAttribute(CURRENT, result);

            getServletContext().getRequestDispatcher(PER_ACCOUNT).forward(req, resp);
        }
    }
}
