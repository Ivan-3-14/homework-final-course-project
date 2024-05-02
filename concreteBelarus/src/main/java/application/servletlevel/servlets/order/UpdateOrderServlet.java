package application.servletlevel.servlets.order;

import application.servicelevel.DTO.orderDTO.OrderDTO;
import application.servicelevel.services.impl.OrderService;
import application.servicelevel.services.interfaces.OrderServiceInt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static application.utils.constant.ConstantsContainer.*;

@WebServlet(name = "UpdateOrderServlet", urlPatterns = {"/updateOrder"})
public class UpdateOrderServlet extends HttpServlet {

    private final OrderServiceInt orderService = new OrderService();
    private final FrontDataConverterDTO frontDataConverterDTO = new FrontDataConverterDTO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(ORDER_UPDATE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            int id = (int) req.getSession().getAttribute(CURRENT_ORDER_ID);

            OrderDTO result = orderService.updateOrder(frontDataConverterDTO.getOrderInformDTOFromReq(req, resp, ORDER_UPDATE_JSP), id);

            req.getSession().setAttribute(CURRENT_ORDER, result);
            getServletContext().getRequestDispatcher(CONFIRMATION).forward(req, resp);
    }
}
