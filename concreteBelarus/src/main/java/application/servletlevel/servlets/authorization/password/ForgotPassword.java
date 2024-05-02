package application.servletlevel.servlets.authorization.password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static application.utils.constant.ConstantsContainer.FORGOT_PASSWORD_JSP;

@WebServlet(name = "ForgotPassword", urlPatterns = {"/forgotPass"})
public class ForgotPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(FORGOT_PASSWORD_JSP).forward(req, resp);
    }
}
