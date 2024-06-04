package application.utils;

public class Constant {

    public static final String NAME = "name";
    public static final String UTC = "UTC";
    public static final String SURNAME = "surname";
    public static final String TEL_NUMBER = "telephoneNumber";
    public static final String CURRENT_USER = "currentUser";
    public static final String CURRENT_ORDER = "currentOrder";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String CURRENT_ORDER_ID = "currentOrderId";
    public static final String MAX_PAGE = "maxPage";
    public static final String ORDER_STATUS = "orderStatus";
    public static final String LIST_ORDERS = "listOrders";
    public static final String LIST_OF_OBJECTS = "listOfObjects";
    public static final String START_WORK_TIME = "07:00:00";
    public static final String END_WORK_TIME = "21:00:00";
    public static final String AGGREGATE = "aggregate";
    public static final String GRADE = "gradesConcrete";
    public static final String CONCRETE = "concrete";
    public static final String BUILDING_OBJECT = "buildingObject";
    public static final String OBJECT_NAME = "nameOfObject";
    public static final String DISTANCE = "distanceToObject";
    public static final String VOLUME = "volumeOfConcrete";
    public static final String DATE_OF_DEL = "dateOfDelivery";
    public static final String TIME_OF_DEL = "timeOfDelivery";
    public static final String USER = "user";
    public static final String SUPER_USER = "superuser";
    public static final String MANAGER = "manager";
    public static final String ID = "id";
    public static final String NULL = "null";
    public static final String COMMENT = "comment";
    public static final String COUNT_NEW_ORDERS = "countNewOrders";

    public static final String DATE_REGEX = "(\\d{4})?-\\d{2}-\\d{2}";
    public static final String PHONE_NUMBER_REGEX = "^\\+?375[- ]?\\d{2}[- ]?\\d{3}[- ]?\\d{2}[- ]?\\d{2}[- ]?$";
    public static final String EMAIL_REGEX = "^\\w+(@)\\w+(.)\\w{1,3}$";
    public static final String LIKE_QUERY_PATTERN = "%%%s%%";
    public static final String MY_DATE_FORMAT = "yyyy-MM-dd";
    public static final String MY_TIME_FORMAT = "hh:mm";
    public static final String LOG_PATTERN = "%s%s%s%s";
    public static final String LOG_PRINT_PATTERN = "%s%s%s";
    public static final String START_COLOR_STRING = "\u001B[34m";
    public static final String END_COLOR_STRING = "\u001B[0m";

    public static final String INDEX = "index";
    public static final String ERROR = "error";
    public static final String SING_UP = "signUp";
    public static final String SEARCH = "search";
    public static final String LOGIN = "login";
    public static final String AUTHORIZATION_PAGE = "registration";
    public static final String PERSONAL_ACCOUNT = "personalAccount";
    public static final String FORGOT_PASSWORD_FORM = "forgotPasswordForm";
    public static final String CHANGE_PASSWORD_FORM = "changePasswordForm";
    public static final String VERIFY_PASSWORD_FORM = "verifyPasswordForm";
    public static final String UPDATE_ME = "updateMe";
    public static final String UPDATE_MANAGER_FORM = "updateManagerForm";
    public static final String ORDER_FORM = "orderForm";
    public static final String NEW_ORDER_FORM = "newOrderForm";
    public static final String ORDER_UPDATE = "orderUpdate";
    public static final String CONFIRMATION = "confirmation";
    public static final String ALL_ORDERS = "allOrders";
    public static final String CHANGE_PRICES = "changePrices";
    public static final String CREATE_MANAGER_FORM = "createManagerForm";
    public static final String NEW_MANAGER_FORM = "/newManager";
    public static final String CONF_FORM = "/confirmationForm";
    public static final String MAIN_PAGE_FOR_USER = "mainPageForUser";
    public static final String MAIN_PAGE_ADMIN = "mainPageForAdmin";
    public static final String MAIN_PAGE_MANAGER = "mainPageForManager";
    public static final String ALL_NEW_ORDER = "allNewOrders";
    public static final String ALL_USERS = "allUsers";
    public static final String USERS_UPDATE_FORM = "userUpdateManagerForm";
    public static final String ALL_OBJECTS = "allObjects";

    public static final String CONCRETE_PRICE_LIST = "concretePriceList";
    public static final String AUTO_PRICE_LIST = "autoPriceList";
    public static final String AUTO_CAPACITY_LIST = "autoCapacityList";
    public static final String ALL_MANAGER_LIST = "allManagerList";
    public static final String USER_LIST = "usersList";
    public static final String MANAGER_ID = "managerId";
    public static final String UPDATE_USER = "updateUser";
    public static final String STATUS = "status";
    public static final String USER_FORM = "userForm";

    public static final String SING_UP_ERROR_MESSAGE = "неверный email или пароль";
    public static final String PASSWORD_ERROR_MESSAGE = "неверный пароль";
    public static final String DEFAULT_ERROR_MESSAGE = "что-то пошло не так";
    public static final String PASSWORDS_ERROR_MESSAGE = "пароли не совпадают";
    public static final String ERROR_USER_FIELD_NULL = "все указанные поля должны быть заполнены,"
            + " расстояние и объём бетона укажите в цифрах,"
            + " телефон в формате +375-ХХ-ХХХ-ХХ-ХХ,"
            + " электронная почта хх@xxxx.xxx";
    public static final String UPDATE_USER_FIELD_NULL = "все указанные поля должны быть заполнены,"
            + " телефон в формате +375-ХХ-ХХХ-ХХ-ХХ,"
            + " электронная почта хх@xxxx.xxx";
    public static final String ERROR_USER_ALREADY_EXIST = "пользователь с таким email уже существует";
    public static final String ERROR_USER_EMAIL_NOT_FOUND = "пользователь с таким email не найден";
    public static final String INCORRECT_DATE_OR_TIME = "выберите дату и время";
    public static final String INCORRECT_DATE = "выберите верную дату";
    public static final String INCORRECT_TIME = "выберите время доставки между 07:00 и 21:00";
    public static final String INCORRECT_AGGREGATE = "марка бетона на гравии не может превышать М300";
    public static final String ERROR_USER_NOT_FOUND = "пользователь с таким email не найден";

    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String PATH_TO_RETURN = "pathToReturn";
    public static final String PERSONAL_ERROR = "personalError";

    public static final Integer MOBILITY_CONSTANT = 3;
    public static final Integer TRANSPORT_CONSTANT = 50;
    public static final Integer GRAVEL_BORDER = 300;
    public static final Integer ROW_IN_PAGE_ORDERS = 2;
    public static final Integer ROW_IN_PAGE_OBJECTS = 4;
    public static final Integer ONE = 1;
    public static final Long MINUS_ONE = -1L;
    public static final Integer ZERO = 0;
    public static final Double DOUBLE_ZERO = 0.0;

    public static final Integer NULL_PAGE = 0;
    public static final Integer FIRST_PAGE = 1;
    public static final Integer SECOND_PAGE = 2;

    public static final String CREATE_NEW_ORDER_SUCCESSFUL = " method createNewOrder() completed successful";
    public static final String GET_ALL_ORDERS_BY_USER_ID_SUCCESSFUL = "method getAllOrdersByUserId() successful";
    public static final String GET_NEW_ORDER_LIST_END = "end method getNewOrderList()";
    public static final String FILTER_DTO_FROM_FRONT_CATCH = "method getFilterDTOFromFront() enter to block catch";
    public static final String CHECK_AGGREGATE_FALSE = " method checkAggregate() is false - grade of gravel concrete more than M300";
    public static final String CHECK_DATE_FALSE = " method checkDate() is false - incorrect Date";
    public static final String CHECK_TIME_FALSE = " method checkTIME() is false - incorrect time";
    public static final String CREATE_USER_EXIST_MESSAGE = "this user already exist";
    public static final String CREATE_USER_SUCCESSFUL_END = "method createUser() completed with create and add new user";
    public static final String PASSWORD_NOT_EQUALS = "method changePassword() password1 not equals password2";
    public static final String GET_USER_BY_EMAIL_PASS_SUCCESSFUL = "method getUserByEmailAndPassword() get user";
    public static final String GET_USER_BY_EMAIL_PASS_NOT_SUCCESSFUL = "method getUserByEmailAndPassword() not found user";
    public static final String CHECK_EXIST_USER_END = "method checkExistUser() completed with null";
    public static final String CHECK_EXIST_USER_SUCCESSFUL_END = "method checkExistUser() completed successful ";
    public static final String CHECK_SUPER_USER_NOT_FOUND = "method checkSuperUserByPhoneNumber() not found user";
    public static final String CREATE_NEW_USER = "method checkSuperUserByPhoneNumber() create and add new user";
    public static final String ALREADY_EXIST_END = "successful end method alreadyExists()";
    public static final String CHECK_EXIST_UPDATE_NOT_FOUND = "method checkExistUpdateUser() not found user for update by email and phone number ";
    public static final String CHECK_EXIST_UPDATE_NOT_FOUND_MESSAGE = "will be created a new user";
    public static final String CHECK_EXIST_UPDATE_END = " checkExistUpdateUser() completed with null";
    public static final String CHECK_EXIST_UPDATE_SUCCESSFUL_END = " method checkExistUpdateUser() completed successful";
    public static final String CREATE_MANAGER_END = "end method createManager() with null: this user already exist";
    public static final String GET_MANAGER_DTO_SUCCESSFUL_END = "method getManagerDTO() completed successful";
    public static final String CHECK_TIME_SUCCESSFUL_END = "method checkTime() completed successful";
    public static final String CHECK_DATE_SUCCESSFUL_END = "method checkDate() completed successful";
    public static final String CHECK_AGGREGATE_SUCCESSFUL_END = "method checkAggregate() completed successful";
    public static final String CHECK_USER_ROLE = "end method checkUserRole() with null";
    public static final String CHECK_EXIST_OBJECT_FIND = "method checkIfExistObject() find building object";
    public static final String CHECK_EXIST_OBJECT_NOT_FOUND = "method checkIfExistObject() not found building object";
    public static final String CREATE_OBJECT_SET_CONCRETE_AND_GRADE = "method createBuildingObject() add concrete and grade to building object";
    public static final String START_METHOD = "start method ";
    public static final String END_METHOD = "end method ";
    public static final String FROM = "() from ";

    private Constant() {
    }
}
