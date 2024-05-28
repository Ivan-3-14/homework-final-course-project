package application.servletlevel.servlets.authorization.password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static application.utils.constant.ConstantsContainer.VERIFY_PASSWORD_JSP;

@WebServlet(name = "VerifyPassword", urlPatterns = {"/verifyPass"})
public class VerifyPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(VERIFY_PASSWORD_JSP).forward(req, resp);
    }
}
