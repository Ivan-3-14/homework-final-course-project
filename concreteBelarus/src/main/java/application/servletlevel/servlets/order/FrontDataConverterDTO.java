package application.servletlevel.servlets.order;

import application.datalevel.DAO.implementations.concrete.ConcreteGradeDAOImpl;
import application.datalevel.DAO.implementations.concrete.ConcreteDAOImpl;
import application.datalevel.DAO.implementations.concrete.MobilityDAOImpl;
import application.datalevel.DAO.interfaces.concrete.ConcreteDAO;
import application.datalevel.DAO.interfaces.concrete.ConcreteGradeDAO;
import application.datalevel.DAO.interfaces.concrete.MobilityDAO;
import application.servicelevel.DTO.OrderInformDTO;
import application.servicelevel.DTO.concreteDTO.ConcreteDTO;
import application.servicelevel.DTO.concreteDTO.ConcreteGradeDTO;
import application.servicelevel.DTO.concreteDTO.MobilityDTO;
import application.servicelevel.DTO.objectDTO.BuildingObjectDTO;
import application.servicelevel.DTO.orderDTO.OrderDTO;
import application.servicelevel.DTO.usersDTO.UserDTO;
import application.utils.appexception.ApplicationException;
import application.utils.enums.orderstatus.OrderStatus;
import application.utils.enums.aggregate.Aggregate;
import application.utils.enums.grades.GradesConcrete;
import application.utils.enums.mobilityvalue.MobilityValue;
import application.utils.enums.roles.Roles;
import application.utils.mappers.ConcreteGradeMapper;
import application.utils.mappers.ConcreteMapper;
import application.utils.mappers.MobilityMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import static application.utils.constant.ConstantsContainer.*;
import static application.utils.constant.ConstantsContainer.MY_TIME_FORMAT;

public class FrontDataConverterDTO {

    private final MobilityDAO mobilityDAOImpl = new MobilityDAOImpl();
    private final MobilityMapper mobilityMapper = new MobilityMapper();
    private final ConcreteDAO concreteDAOImpl = new ConcreteDAOImpl();
    private final ConcreteMapper concreteMapper = new ConcreteMapper();
    private final ConcreteGradeDAO concreteGradeDAOImpl = new ConcreteGradeDAOImpl();
    private final ConcreteGradeMapper concreteGradeMapper = new ConcreteGradeMapper();
    private final ApplicationException applicationException = new ApplicationException();

    public OrderInformDTO getOrderInformDTOFromReq(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        String aggregate = req.getParameter(AGGREGATE);
        String gradeConcrete = req.getParameter(GRADE);
        String mobilityConcrete = req.getParameter(MOBILITY);

        String nameOfObject = req.getParameter(OBJECT_NAME);
        String distanceToObject = req.getParameter(DISTANCE);
        String volumeOfConcrete = req.getParameter(VOLUME);
        String dateOfDelivery = req.getParameter(DATE_OF_DEL);
        String timeOfDelivery = req.getParameter(TIME_OF_DEL);
        String comment = req.getParameter(COMMENT);

        String name = req.getParameter(NAME);
        String surname = req.getParameter(SURNAME);
        String telephoneNumber = req.getParameter(TEL_NUMBER);

        BuildingObjectDTO buildingObjectDTO = null;
        OrderDTO orderDTO = null;
        UserDTO userDTO = null;

        try {
            int grade = Integer.parseInt(gradeConcrete);
            double volume = Double.parseDouble(volumeOfConcrete);
            int mobility = Integer.parseInt(mobilityConcrete);
            double distance = DOUBLE_ZERO;

            if (!distanceToObject.isEmpty()) {
                distance = Double.parseDouble(distanceToObject);
            }

            if (Aggregate.GRAVEL.toString().equals(aggregate) && grade > GRAVEL_BORDER) {
                req.setAttribute(NAME, name);
                req.setAttribute(SURNAME, surname);
                req.setAttribute(TEL_NUMBER, telephoneNumber);
                req.setAttribute(DISTANCE, distanceToObject);
                req.setAttribute(VOLUME, volumeOfConcrete);
                req.setAttribute(OBJECT_NAME, nameOfObject);
                req.setAttribute(COMMENT, comment);
                req.setAttribute(CHECK_GRADE, true);
                req.getServletContext().getRequestDispatcher(path).forward(req, resp);
            }

            if (name.isEmpty() || surname.isEmpty() || telephoneNumber.isEmpty()) {
                req.getServletContext().getRequestDispatcher(path).forward(req, resp);
            }

            Date date = orderDateConverter(dateOfDelivery);
            if (date.compareTo(new java.util.Date()) < ZERO) {
                applicationException.dateException(req, resp, name, surname, telephoneNumber, distanceToObject,
                        volumeOfConcrete, nameOfObject, comment, INCORRECT_TIME_OR_DATE);
                return null;
            }

            Time time = orderTimeConverter(timeOfDelivery);
            if (time.compareTo(Time.valueOf(START_WORK_TIME)) < ZERO
                    || time.compareTo(Time.valueOf(END_WORK_TIME)) > ZERO) {
                applicationException.timeException(req, resp, name, surname, telephoneNumber, distanceToObject,
                        volumeOfConcrete, nameOfObject, comment, INCORRECT_TIME_OR_DATE);
                return null;
            }

            MobilityDTO mobilityDTO = orderMobilityConverter(mobility);
            ConcreteGradeDTO concreteGradeDTO = orderGradeConverter(grade);
            ConcreteDTO concreteDTO = orderConcreteConverter(aggregate);
            userDTO = (UserDTO) req.getSession().getAttribute(CURRENT);
            userDTO = checkUserRole(userDTO);

            buildingObjectDTO = BuildingObjectDTO.builder()
                    .nameOfObject(nameOfObject)
                    .distanceToObject(distance)
                    .build();

            orderDTO = OrderDTO.builder()
                    .concreteDTO(concreteDTO)
                    .concreteGradeDTO(concreteGradeDTO)
                    .mobilityDTO(mobilityDTO)
                    .volumeOfConcrete(volume)
                    .dateOfDelivery(date)
                    .timeOfDelivery(time)
                    .comment(comment)
                    .orderStatus(OrderStatus.NEW)
                    .orderTimeCreate(new Timestamp(System.currentTimeMillis()))
                    .build();
        } catch (NumberFormatException e) {
            applicationException.orderNumberFormatException(req, resp, name, surname, telephoneNumber, nameOfObject,
                    comment, path);
        } catch (ParseException e) {
            applicationException.orderParseException(req, resp, name, surname, telephoneNumber, distanceToObject,
                    volumeOfConcrete, nameOfObject, comment, path);
        }
        return OrderInformDTO.builder()
                .userDTO(userDTO)
                .orderDTO(orderDTO)
                .buildingObjectDTO(buildingObjectDTO)
                .name(name)
                .surname(surname)
                .telephoneNumber(telephoneNumber)
                .build();
    }

    private Date orderDateConverter(String dateOfDelivery) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(MY_DATE_FORMAT, Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone(UTC));
        return new Date(formatter.parse(dateOfDelivery).getTime());
    }

    private Time orderTimeConverter(String timeOfDelivery) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(MY_TIME_FORMAT);
        return new java.sql.Time(dateFormat.parse(timeOfDelivery).getTime());
    }

    private MobilityDTO orderMobilityConverter(int mobility) {
        MobilityValue orderMobility = MobilityValue.getMobilityFromIntValue(mobility);
        return mobilityMapper.entityToDTO(mobilityDAOImpl.getMobilityByMobilityValue(orderMobility));
    }

    private ConcreteGradeDTO orderGradeConverter(int grade) {
        GradesConcrete orderGrade = GradesConcrete.getGradeFromValue(grade);
        return concreteGradeMapper.entityToDTO(concreteGradeDAOImpl.getConcreteGradeByGradeValue(orderGrade));
    }

    private ConcreteDTO orderConcreteConverter(String aggregate) {
        Aggregate orderAggregate = Aggregate.getAggregateByValue(aggregate);
        return concreteMapper.entityToDTO(concreteDAOImpl.getConcreteByValue(orderAggregate));
    }

    private UserDTO checkUserRole(UserDTO userDTO) {
        if (userDTO != null && Roles.MANAGER.equals(userDTO.getRole())) {
            return null;
        }
        return userDTO;
    }
}