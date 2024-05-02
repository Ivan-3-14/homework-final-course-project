package application.servletlevel.servlets.user;

import application.servicelevel.DTO.orderDTO.OrderDTO;
import application.servicelevel.DTO.usersDTO.UserDTO;
import application.servicelevel.services.impl.OrderService;
import application.servicelevel.services.interfaces.OrderServiceInt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static application.utils.constant.ConstantsContainer.*;

@WebServlet(name = "MyOrderServlet", urlPatterns = {"/myOrders"})
public class MyOrderServlet extends HttpServlet {

    private final OrderServiceInt orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int currentPage;
        try {
            currentPage = Integer.parseInt(req.getParameter(CURRENT_PAGE));
        } catch (NumberFormatException e) {
            currentPage = FIRST_PAGE;
        }
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute(CURRENT);
        if (currentPage == NULL_PAGE) {
            currentPage = FIRST_PAGE;
        }
        List<OrderDTO> list = orderService.orderPaginationList(userDTO.getId(), currentPage, ROW_IN_PAGE_ORDERS);
        req.setAttribute(LIST_ORDERS, list);
        req.setAttribute(CURRENT_PAGE, currentPage);
        req.setAttribute(MAX_PAGE, orderService.countOfPage(userDTO.getId()));
        getServletContext().getRequestDispatcher(ALL_ORDERS_JSP).forward(req, resp);
    }
}
