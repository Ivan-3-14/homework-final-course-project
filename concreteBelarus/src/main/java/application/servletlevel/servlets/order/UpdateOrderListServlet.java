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

@WebServlet(name = "UpdateOrderServlet", urlPatterns = {"/updateOrderList"})
public class UpdateOrderListServlet extends HttpServlet {

    private final OrderServiceInt orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = req.getParameter(TEMP_ORDER_ID);
        req.getSession().setAttribute(CURRENT_ORDER, orderService.readOrderDTO(Integer.parseInt(result)));
        getServletContext().getRequestDispatcher(ORDER_UPDATE_JSP).forward(req, resp);
    }
}
