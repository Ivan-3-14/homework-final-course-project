package application.servletlevel.servlets.user;

import application.servicelevel.DTO.objectDTO.BuildingObjectDTO;
import application.servicelevel.DTO.usersDTO.UserDTO;
import application.servicelevel.services.impl.BuildingObjectService;
import application.servicelevel.services.interfaces.BuildingObjectServiceInt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static application.utils.constant.ConstantsContainer.*;

@WebServlet(name = "MyObjectsServlet", urlPatterns = {"/myObjects"})
public class MyObjectsServlet extends HttpServlet {

    private final BuildingObjectServiceInt buildingObjectService = new BuildingObjectService();

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
        List<BuildingObjectDTO> objectDTOList = buildingObjectService.paginationBuildingObjectsList(userDTO.getId(),
                currentPage, ROW_IN_PAGE_OBJECTS
        );
        req.setAttribute(LIST_OF_OBJECTS, objectDTOList);
        req.setAttribute(CURRENT_PAGE, currentPage);
        req.setAttribute(MAX_PAGE, buildingObjectService.countOfPage(userDTO.getId()));
        getServletContext().getRequestDispatcher(ALL_OBJECTS_JSP).forward(req, resp);
    }
}
