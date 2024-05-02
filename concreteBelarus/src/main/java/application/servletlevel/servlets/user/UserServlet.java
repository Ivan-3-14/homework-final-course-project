package application.servletlevel.servlets.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static application.utils.constant.ConstantsContainer.MAIN_PAGE_USER_JSP;

@WebServlet(name = "UserServlet", urlPatterns = {"/mainPageUser"})
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(MAIN_PAGE_USER_JSP).forward(req, resp);
    }
}
