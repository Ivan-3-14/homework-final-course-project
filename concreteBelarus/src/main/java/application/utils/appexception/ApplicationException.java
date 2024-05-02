package application.utils.appexception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static application.utils.constant.ConstantsContainer.*;

public class ApplicationException {

    public void orderNumberFormatException(HttpServletRequest req,
                                           HttpServletResponse resp,
                                           String name,
                                           String surname,
                                           String numberOfPhone,
                                           String nameOfObject,
                                           String comment,
                                           String path
    ) throws ServletException, IOException {
        req.setAttribute(NAME, name);
        req.setAttribute(SURNAME, surname);
        req.setAttribute(NUMBER_OF_PHONE, numberOfPhone);
        req.setAttribute(OBJECT_NAME, nameOfObject);
        req.setAttribute(COMMENT, comment);
        req.setAttribute(WRONG_DIST_OR_VOL, true);
        req.getServletContext().getRequestDispatcher(path).forward(req, resp);
    }

    public void orderParseException(HttpServletRequest req,
                                    HttpServletResponse resp,
                                    String name,
                                    String surname,
                                    String numberOfPhone,
                                    String distanceToObject,
                                    String volumeOfConcrete,
                                    String nameOfObject,
                                    String comment,
                                    String path
    ) throws ServletException, IOException {
        req.setAttribute(NAME, name);
        req.setAttribute(SURNAME, surname);
        req.setAttribute(NUMBER_OF_PHONE, numberOfPhone);
        req.setAttribute(DISTANCE, distanceToObject);
        req.setAttribute(VOLUME, volumeOfConcrete);
        req.setAttribute(OBJECT_NAME, nameOfObject);
        req.setAttribute(COMMENT, comment);
        req.setAttribute(WRONG_TIME_OR_DATE, true);
        req.getServletContext().getRequestDispatcher(path).forward(req, resp);
    }

    public void dateException(HttpServletRequest req,
                              HttpServletResponse resp,
                              String name,
                              String surname,
                              String numberOfPhone,
                              String distanceToObject,
                              String volumeOfConcrete,
                              String nameOfObject,
                              String comment,
                              String path) throws ServletException, IOException {
        req.setAttribute(NAME, name);
        req.setAttribute(SURNAME, surname);
        req.setAttribute(NUMBER_OF_PHONE, numberOfPhone);
        req.setAttribute(DISTANCE, distanceToObject);
        req.setAttribute(VOLUME, volumeOfConcrete);
        req.setAttribute(OBJECT_NAME, nameOfObject);
        req.setAttribute(COMMENT, comment);
        req.setAttribute(LAST_DAYS, true);
        req.getServletContext().getRequestDispatcher(path).forward(req, resp);
    }

    public void timeException(HttpServletRequest req,
                              HttpServletResponse resp,
                              String name,
                              String surname,
                              String numberOfPhone,
                              String distanceToObject,
                              String volumeOfConcrete,
                              String nameOfObject,
                              String comment,
                              String path) throws ServletException, IOException {
        req.setAttribute(NAME, name);
        req.setAttribute(SURNAME, surname);
        req.setAttribute(NUMBER_OF_PHONE, numberOfPhone);
        req.setAttribute(DISTANCE, distanceToObject);
        req.setAttribute(VOLUME, volumeOfConcrete);
        req.setAttribute(OBJECT_NAME, nameOfObject);
        req.setAttribute(COMMENT, comment);
        req.setAttribute(INCORRECT_TIME, true);
        req.getServletContext().getRequestDispatcher(path).forward(req, resp);

    }
}
