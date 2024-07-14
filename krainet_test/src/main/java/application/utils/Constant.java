package application.utils;

public class Constant {

    public static final String LOG_PATTERN = "%s%s%s%s";
    public static final String LOG_PRINT_PATTERN = "%s%s%s";
    public static final String START_COLOR_STRING = "\u001B[34m";
    public static final String END_COLOR_STRING = "\u001B[0m";

    public static final String LOGIN = "login";
    public static final String EXIT = "exit";
    public static final String CURRENT_USER = "currentUser";
    public static final String CURRENT_PROJECT = "currentProject";
    public static final String CURRENT_PROJECT_ID = "currentProjectId";
    public static final String CURRENT_RECORD = "currentRecord";
    public static final String PROJECT_LIST = "projectList";
    public static final String USER_FOR_PROJECT_LIST = "userList";

    public static final String START_METHOD = "start method ";
    public static final String END_METHOD = "end method ";
    public static final String FROM = "() from ";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String PATH_TO_RETURN = "pathToReturn";
    public static final String ERROR_USER_NOT_FOUND = "user not found";
    public static final String CREATE_USER_SUCCESSFUL_END = "method createUser() completed with create and add new user";
    public static final String CREATE_USER_EXIST_MESSAGE = "this user already exist";
    public static final String ERROR_CREATE_USER_MESSAGE = "fields should be not null";

    public static final String ADMIN_PAGE = "adminPage";
    public static final String USER_PAGE = "userPage";
    public static final String READ_PROJECT = "readProject";
    public static final String RECORD = "record";
    public static final String NEW_PROJECT_FORM = "newProjectForm";
    public static final String NEW_USER_FORM = "newUserForm";
    public static final String ERROR = "error";
    public static final String CREATE_NEW_USER = "createNewUser";
    public static final String ALL_PROJECTS = "allProjects";
    public static final String ADD_USERS_TO_PROJECTS = "addUserToProject";
    public static final String DELETE_USERS_FROM_PROJECTS = "deleteUsersFromProject";

    private Constant() {
    }
}
