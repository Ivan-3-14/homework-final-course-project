package application.utils.constant;

public class ConstantsContainer {

    public static final String GET_ALL_ORDERS_QUERY = "select o from Order o";
    public static final String GET_ALL_AUTO_PRICE_QUERY = "select a from AutoPrice a";
    public static final String GET_ALL_CONCRETE_PRICE_QUERY = "select p from ConcretePrice p";

    public static final String CREATE_FAILED_MSG = "Create failed. Nothing found";
    public static final String CREATE_SUCCESS_MSG = "Entity created";

    public static final String READ_FAILED_MSG = "Read failed. Nothing found";
    public static final String READ_SUCCESS_MSG = "Read the entity";

    public static final String UPDATE_FAILED_MSG = "Update failed. Nothing found";
    public static final String UPDATE_SUCCESS_MSG = "Updated the entity";

    public static final String DELETE_FAILED_MSG = "Delete failed. Nothing found";
    public static final String DELETE_SUCCESS_MSG = "Deleted the entity";

    public static final String START_GET_ALL_ORDERS = "Method getAllOrders is start";
    public static final String GET_ALL_ORDERS_SUCCESSFUL = "Method getAllOrders successful";

    public static final String START_GET_ALL_CONCRETE_PRICE = "Method getAllConcretePrice() is start";
    public static final String START_GET_ALL_AUTO_PRICE = "Method getAllAutoPrice() is start";
    public static final String GET_ALL_CONCRETE_PRICE_SUCCESSFUL = "Method getAllConcretePrice() successful";
    public static final String GET_ALL_AUTO_PRICE_SUCCESSFUL = "Method getAllAutoPrice() successful";

    public static final String LIST_OF_ORDERS_EMPTY = "list of orders is empty";
    public static final String LIST_OF_CONCRETE_PRICE_EMPTY = "list of concrete price is empty";
    public static final String LIST_OF_AUTO_PRICE_EMPTY = "list of auto price is empty";

    public static final String NAME = "name";
    public static final String UTC = "UTC";
    public static final String SURNAME = "surname";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String TEL_NUMBER = "telephoneNumber";
    public static final String WRONG = "wrong";
    public static final String CURRENT = "current";
    public static final String CURRENT_MANAGER = "currentManager";
    public static final String CURRENT_ORDER = "currentOrder";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String CURRENT_ORDER_ID = "currentOrderId";
    public static final String MAX_PAGE = "maxPage";
    public static final String ROLE = "role";
    public static final String LIST_ORDERS = "listOrders";
    public static final String LIST_OF_OBJECTS = "listOfObjects";
    public static final String TEMP_ORDER_ID = "tempOrderID";
    public static final String LAST_DAYS = "lastDays";
    public static final String INCORRECT_TIME = "incorrectTime";
    public static final String START_WORK_TIME = "07:00:00";
    public static final String END_WORK_TIME = "21:00:00";

    public static final String FOR_ALL_PAGE = "forAllPage.jsp";
    public static final String AUTH_JSP = "/authorization.jsp";
    public static final String MAIN_PAGE_USER = "/mainPageUser";
    public static final String SIGN_UP = "/signUp";
    public static final String SIGN_UP_JSP = "/signUp.jsp";
    public static final String ALL_ORDERS_JSP = "/allOrders.jsp";
    public static final String ALL_OBJECTS_JSP = "/allObjects.jsp";
    public static final String CONF_FORM_JSP = "/confirmationForm.jsp";
    public static final String ORDER_FORM_JSP = "/orderForm.jsp";
    public static final String CONFIRMATION = "/confirmation";
    public static final String ORDER_UPDATE_JSP = "/orderUpdate.jsp";
    public static final String MANAGER_UPDATE_JSP = "/updateManager.jsp";
    public static final String MAIN_PAGE_USER_JSP = "/mainPageForUser.jsp";
    public static final String MAIN_PAGE_ADMIN_JSP = "/mainPageForAdmin.jsp";
    public static final String MAIN_PAGE_MANAGER_JSP = "/mainPageForManager.jsp";
    public static final String PER_ACCOUNT = "/personalAccount.jsp";
    public static final String INCORRECT_TIME_OR_DATE = "/incorrectDateTime.jsp";
    public static final String FORGOT_PASSWORD_JSP = "/forgotPassword.jsp";
    public static final String CHANGE_PASSWORD_JSP = "/changePassword.jsp";
    public static final String VERIFY_PASSWORD_JSP = "/verifyPassword.jsp";
    public static final String CREATE_MANAGER_FORM_JSP = "/createManagerForm.jsp";
    public static final String CHANGE_PRICE_JSP = "/changePrices.jsp";
    public static final String ADMIN_MAIN_PAGE = "/adminMyself";

    public static final String AGGREGATE = "aggregate";
    public static final String GRADE = "concreteGrade";
    public static final String MOBILITY = "mobility";
    public static final String OBJECT_NAME = "nameOfObject";
    public static final String DISTANCE = "distanceToObject";
    public static final String VOLUME = "volumeOfConcrete";
    public static final String DATE_OF_DEL = "dateOfDelivery";
    public static final String TIME_OF_DEL = "timeOfDelivery";
    public static final String COMMENT = "comment";
    public static final String CHECK_GRADE = "checkGrade";
    public static final String WRONG_DIST_OR_VOL = "wrongDistanceOrVolume";
    public static final String WRONG_TIME_OR_DATE = "wrongTimeOrDate";
    public static final String INCORRECT_D_T = "incorrectDT";
    public static final String MANAGER_ID = "managerId";
    public static final String CONCRETE_PRICE = "concretePrice";
    public static final String AUTO_PRICE = "autoPrice";
    public static final String TEMP_AUTO_PRICE_ID = "tempAutoPriceID";
    public static final String TEMP_CONCRETE_PRICE_ID = "tempConcretePriceID";
    public static final String ALL_MANAGER_LIST = "allManagerList";

    public static final String FIND_ALL_ORDERS = "FROM Order o where o.user=";

    public static final String BY_EMAIL_USER_NOT_FOUND = "BY_EMAIL_USER_NOT_FOUND";
    public static final String BY_PASSWORD_USER_NOT_FOUND = "BY_PASSWORD_USER_NOT_FOUND";
    public static final String BY_EMAIL_AND_PASSWORD_USER_NOT_FOUND = "BY_EMAIL_AND_PASSWORD_USER_NOT_FOUND";
    public static final String THIS_SUPER_USER_NOT_FOUND = "THIS_SUPER_USER_NOT_FOUND";
    public static final String MANAGER_NOT_FOUND = "MANAGER_NOT_FOUND";
    public static final String MY_DATE_FORMAT = "yyyy-MM-dd";
    public static final String MY_TIME_FORMAT = "hh:mm";
    public static final String BY_CONCRETE_AND_GRADE_THIS_PRICE_NOT_FOUND = "BY_CONCRETE_AND_GRADE_THIS_PRICE_NOT_FOUND";

    public static final String GET_USER_BY_EMAIL_QUERY = "select * from user where email = '";
    public static final String GET_USER_BY_PASSWORD_QUERY = "select * from user where password = '";
    public static final String GET_USER_BY_MANAGER_QUERY = "select * from user where role = 'MANAGER'";
    public static final String END_QUERY = "'";
    public static final String END_QUERY2 = "';";
    public static final String AND_PASSWORD = "' and password ='";
    public static final String FROM_B_O_WHERE_USER = "FROM BuildingObject b where b.user=";
    public static final String NEW_PASSWORD = "new";
    public static final String NEW_PASSWORD2 = "new2";

    public static final String GET_CONCRETE_BY_NOMINATION_QUERY = "select * from concrete_grade where nomination= '";
    public static final String GET_CAPACITY_BY_CAR_TYPE = "select * from capacity where car_type= '";
    public static final String GET_CAPACITY_BY_AUTO_CAPACITY = "select * from capacity where auto_capacity= '";
    public static final String GET_COUNT_OF_CAPACITY = "select count(*) from AutoTransport a where a.capacity= '";
    public static final String GET_CONCRETE_BY_AGGREGATE = "select * from concrete where aggregate= '";
    public static final String GET_CONCRETE_PRICE_BY_CONCRETE = "select * from concrete_price where concrete_id = '";
    public static final String AND_CONCRETE_GRADE = "' and concrete_grade_id ='";
    public static final String GET_CONCRETE_MOBILITY_BY_VALUE = "select * from concrete_mobility where value = '";
    public static final String GET_OBJECT_BY_NAME = "select * from building_object where object_name= '";
    public static final String AND_OBJECT_DIST = "' and object_distance ='";
    public static final String GET_USER_BY_NUMBER = "select * from user where telephone_number ='";
    public static final String AND_ROLE = "' and role ='";

    public static final Integer MOBILITY_CONSTANT = 3;
    public static final Integer TRANSPORT_CONSTANT = 50;
    public static final Integer GRAVEL_BORDER = 300;
    public static final Integer ROW_IN_PAGE_ORDERS = 2;
    public static final Integer ROW_IN_PAGE_OBJECTS = 4;
    public static final Integer FIRST_PAGE = 1;
    public static final Integer ONE = 1;
    public static final Integer NULL_PAGE = 0;
    public static final Integer ZERO = 0;
    public static final Double DOUBLE_ZERO = 0.0;

    private ConstantsContainer() {
    }
}
