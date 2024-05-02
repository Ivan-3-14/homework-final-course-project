package application.servletlevel.servlets.authorization;

import application.utils.hibernate.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static application.utils.constant.ConstantsContainer.*;

@WebServlet(name = "ExitServlet", urlPatterns = {"/exit"})
public class ExitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateUtils.closeEntityManager();
        req.getSession().setAttribute(CURRENT, null);
        req.getSession().setAttribute(CURRENT_PAGE, null);
        getServletContext().getRequestDispatcher(SIGN_UP).forward(req, resp);
    }
}
