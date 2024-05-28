package application.utils;

public class Constant {

    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String CURRENT_MANAGER = "currentManager";
    public static final String ROLE = "role";

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
    public static final String ID = "id";
    public static final String NULL = "null";
    public static final String COMMENT = "comment";

    public static final String DATE_REGEX = "(\\d{4})?-\\d{2}-\\d{2}";
    public static final String LIKE_QUERY_PATTERN = "%%%s%%";
    public static final String MY_DATE_FORMAT = "yyyy-MM-dd";
    public static final String MY_TIME_FORMAT = "hh:mm";

    //адреса страниц
    public static final String INDEX = "index";
    public static final String ERROR = "error";
    public static final String SING_UP = "signUp";
    public static final String SEARCH = "search";
    public static final String SING_UP_FORM = "signUpForm";
    public static final String AUTHORIZATION_PAGE = "authorizationPage";
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
    public static final String CONF_FORM= "/confirmationForm";
    public static final String MAIN_PAGE_FOR_USER = "mainPageForUser";
    public static final String MAIN_PAGE_ADMIN= "mainPageForAdmin";
    public static final String MAIN_PAGE_MANAGER = "mainPageForManager";

    //реквест-параметры
    public static final String CONCRETE_PRICE_LIST = "concretePriceList";
    public static final String AUTO_PRICE_LIST = "autoPriceList";
    public static final String AUTO_CAPACITY_LIST = "autoCapacityList";
    public static final String ALL_MANAGER_LIST = "allManagerList";
    public static final String MANAGER_ID = "managerId";

    // сообщения об ошибке
    public static final String SING_UP_ERROR_MESSAGE = "неверный email или пароль";
    public static final String PASSWORD_ERROR_MESSAGE = "неверный пароль";
    public static final String DEFAULT_ERROR_MESSAGE = "что-то пошло не так";
    public static final String PASSWORDS_ERROR_MESSAGE = "пароли не совпадают";
    public static final String ERROR_USER_FIELD_NULL = "все указанные поля должны быть заполнены, расстояние и объём укажите в цифрах";
    public static final String UPDATE_USER_FIELD_NULL = "все указанные поля должны быть заполнены";
    public static final String ERROR_USER_ALREADY_EXIST = "пользователь с таким email уже существует";
    public static final String ERROR_USER_EMAIL_NOT_FOUND = "пользователь с таким email не найден";
    public static final String INCORRECT_DATE_OR_TIME = "выберите дату и время";
    public static final String INCORRECT_DATE = "выберите верную дату";
    public static final String INCORRECT_TIME = "выберите время доставки между 07:00 и 21:00";
    public static final String INCORRECT_AGGREGATE = "марка бетона на гравии не может превышать М300";

    // констаны в ошибках
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String PATH_TO_RETURN = "pathToReturn";
    public static final String ADMIN_ERROR = "adminError";

    public static final Integer MOBILITY_CONSTANT = 3;
    public static final Integer TRANSPORT_CONSTANT = 50;
    public static final Integer GRAVEL_BORDER = 300;
    public static final Integer ROW_IN_PAGE_ORDERS = 2;
    public static final Integer ONE = 1;
    public static final Long MINUS_ONE = -1L;
    public static final Integer ZERO = 0;
    public static final Double DOUBLE_ZERO = 0.0;

    public static final Integer NULL_PAGE = 0;
    public static final Integer ROW_IN_PAGE_OBJECTS = 4;
    public static final Integer FIRST_PAGE = 1;

    private Constant() {
    }
}
