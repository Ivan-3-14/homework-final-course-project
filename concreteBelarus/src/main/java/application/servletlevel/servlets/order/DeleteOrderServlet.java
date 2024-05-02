package application.servletlevel.servlets.order;

import application.servicelevel.services.impl.OrderService;
import application.servicelevel.services.interfaces.OrderServiceInt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static application.utils.constant.ConstantsContainer.*;

@WebServlet(name = "DeleteOrderServlet", urlPatterns = {"/deleteOrder"})
public class DeleteOrderServlet extends HttpServlet {

    private final OrderServiceInt orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute(CURRENT_ORDER_ID);
        orderService.deleteOrder(id);
        getServletContext().getRequestDispatcher(ORDER_FORM_JSP).forward(req, resp);
    }
}
