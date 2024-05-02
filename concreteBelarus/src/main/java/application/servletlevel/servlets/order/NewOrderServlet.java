package application.servletlevel.servlets.order;

import application.servicelevel.DTO.OrderInformDTO;
import application.servicelevel.DTO.objectDTO.BuildingObjectDTO;
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

import static application.utils.constant.ConstantsContainer.*;

@WebServlet(name = "NewOrderServlet", urlPatterns = {"/newOrder"})
public class NewOrderServlet extends HttpServlet {

    private final OrderServiceInt orderService = new OrderService();
    private final FrontDataConverterDTO frontDataConverterDTO = new FrontDataConverterDTO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(ORDER_FORM_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO;
        OrderDTO orderDTO;
        BuildingObjectDTO buildingObjectDTO;
        String name;
        String surname;
        String numberOfPhone;

        OrderInformDTO orderInformDTO = frontDataConverterDTO.getOrderInformDTOFromReq(req, resp, ORDER_FORM_JSP);

        if (orderInformDTO != null) {
            userDTO = orderInformDTO.getUserDTO();
            orderDTO = orderInformDTO.getOrderDTO();
            buildingObjectDTO = orderInformDTO.getBuildingObjectDTO();
            name = orderInformDTO.getName();
            surname = orderInformDTO.getSurname();
            numberOfPhone = orderInformDTO.getTelephoneNumber();
            if (orderDTO != null && buildingObjectDTO != null) {
                OrderDTO result = orderService.checkAndCreateOrder(userDTO, orderDTO, buildingObjectDTO, name, surname, numberOfPhone);
                req.getSession().setAttribute(CURRENT_ORDER, result);
            }
            getServletContext().getRequestDispatcher(CONFIRMATION).forward(req, resp);
        }
    }
}
