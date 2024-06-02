package application.controller;

import application.DTO.filtersDTO.OrderPaginationFilter;
import application.DTO.filtersDTO.PriceFilterToFront;
import application.DTO.usersDTO.ManagerDTO;
import application.DTO.usersDTO.UserDTO;
import application.entity.enums.orderstatus.OrderStatus;
import application.entity.enums.roles.Roles;
import application.services.interfaces.OrderService;
import application.services.interfaces.PriceService;
import application.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import static application.utils.Constant.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final PriceService priceService;
    private final UserService userService;
    private final OrderService orderService;
    private final AdminController adminController;
    private final ApplicationExceptionController applicationExceptionController;

    @GetMapping(path = "/mainPage")
    public String goToMainPage(Model model) {
        PriceFilterToFront priceFilterToFront = priceService.getAllPrice();
        model.addAttribute(CONCRETE_PRICE_LIST, priceFilterToFront.getConcretePriceDTOList());
        model.addAttribute(AUTO_PRICE_LIST, priceFilterToFront.getAutoPriceDTOList());
        model.addAttribute(AUTO_CAPACITY_LIST, priceFilterToFront.getAutoCapacityDTOList());
        return INDEX;
    }

    @GetMapping(path = "/authorizationPage")
    public String getAuthorizationForm() {
        return AUTHORIZATION_PAGE;
    }

    @PostMapping(path = "/authorization")
    public String createNewUser(Model model, @Valid UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return applicationExceptionController.sendErrorMessage(model, UPDATE_USER_FIELD_NULL, AUTHORIZATION_PAGE);
        }

        UserDTO newUser = userService.createUser(userDTO);
        if (newUser == null) {
            return applicationExceptionController.sendErrorMessage(model, ERROR_USER_ALREADY_EXIST, AUTHORIZATION_PAGE);
        }
        model.addAttribute(CURRENT_USER, newUser);
        return MAIN_PAGE_FOR_USER;
    }

    @GetMapping(path = "/login")
    public String goToSignUpForm() {
        return LOGIN;
    }

    @GetMapping(path = "/signUp")
    public String signUp(Model model) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        UserDTO userDTO = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userDTO == null) {
            applicationExceptionController.sendErrorMessage(model, SING_UP_ERROR_MESSAGE, SING_UP);
            return null;
        }
        model.addAttribute(CURRENT_USER, userDTO);

        if (Roles.MANAGER.equals(userDTO.getRole())) {
            if (userDTO.getManager() != null) {
                ManagerDTO managerDTO = userDTO.getManager();
                managerDTO.setUser(userDTO);
                model.addAttribute(MANAGER_ID, managerDTO.getId());
                return goToManagerMainPage(model, userDTO.getId(), ZERO);
            }
        } else if (Roles.ADMIN.equals(userDTO.getRole())) {
            return adminController.goToAdminMainPage(model);
        }
        return MAIN_PAGE_FOR_USER;
    }

    @GetMapping(path = "/forgotPassword")
    public String goToForgotPasswordForm() {
        return FORGOT_PASSWORD_FORM;
    }

    @PostMapping(path = "/forgotPass")
    public String forgotPassword(Model model, @RequestParam String email) {
        UserDTO userDTO = userService.getUserByEmail(email);
        model.addAttribute(CURRENT_USER, userDTO);
        return userDTO != null ? CHANGE_PASSWORD_FORM : applicationExceptionController.sendErrorMessage(model,
                ERROR_USER_EMAIL_NOT_FOUND, AUTHORIZATION_PAGE);
    }

    @GetMapping(path = "/verifyPassword")
    public String goToVerifyPasswordForm() {
        return VERIFY_PASSWORD_FORM;
    }

    @GetMapping(path = "/checkPassword")
    public String checkOldPassword(Model model, @RequestParam String email, @RequestParam String password) {
        UserDTO userDTO = userService.getUserByEmailAndPassword(email, password);
        model.addAttribute(CURRENT_USER, userDTO);
        model.addAttribute(PERSONAL_ERROR, PERSONAL_ERROR);
        return userDTO != null ? CHANGE_PASSWORD_FORM : applicationExceptionController.sendErrorMessage(model,
                PASSWORD_ERROR_MESSAGE, AUTHORIZATION_PAGE);
    }

    @PostMapping(path = "/changePassword")
    public String changePassword(Model model, @RequestParam String newPassword1, @RequestParam String newPassword2,
                                 @RequestParam long currentUserId) {
        UserDTO userDTO = userService.changePassword(newPassword1, newPassword2, currentUserId);

        if (userDTO != null) {
            if (Roles.MANAGER.equals(userDTO.getRole())) {
                return goToManagerMainPage(model, userDTO.getId(), ZERO);
            } else if (Roles.ADMIN.equals(userDTO.getRole())) {
                return adminController.goToAdminMainPage(model);
            } else {
                return MAIN_PAGE_FOR_USER;
            }
        }
        return applicationExceptionController.sendErrorMessage(model, PASSWORDS_ERROR_MESSAGE, AUTHORIZATION_PAGE);
    }

    @GetMapping(path = "/userMainPage")
    public String goToUserMainPage() {
        return MAIN_PAGE_FOR_USER;
    }

    @GetMapping(path = "/updateMe")
    public String goToUpdateUserForm() {
        return UPDATE_ME;
    }

    @PostMapping(path = "/updateMyself")
    public String updateMyself(Model model, UserDTO userDTO, BindingResult bindingResult,
                               @RequestParam Long currentUserId) {
        if (bindingResult.hasErrors()) {
            return applicationExceptionController.sendErrorMessage(model, UPDATE_USER_FIELD_NULL, UPDATE_ME);
        }
        userDTO.setId(currentUserId);
        UserDTO result = userService.updateUser(userDTO);

        if (result == null) {
            return applicationExceptionController.sendErrorMessage(model, ERROR_USER_ALREADY_EXIST, UPDATE_ME);
        }

        model.addAttribute(CURRENT_USER, result);
        return adminController.goToAccount();
    }

    @GetMapping(path = "/managerMainPage")
    public String goToManagerMainPage(Model model, @RequestParam Long currentUserId,
                                      @RequestParam(defaultValue = "0") int currentPage) {

        OrderPaginationFilter orderPaginationFilter = orderService.getOrderPaginationFilterForManager(currentUserId,
                currentPage, ROW_IN_PAGE_ORDERS);
        model.addAttribute(LIST_ORDERS, orderPaginationFilter.getListOrders());
        model.addAttribute(CURRENT_PAGE, orderPaginationFilter.getCurrentPage());
        model.addAttribute(MAX_PAGE, orderPaginationFilter.getCountOfTotalPage());
        model.addAttribute(COUNT_NEW_ORDERS, orderPaginationFilter.getCountOfNewOrders());
        return MAIN_PAGE_MANAGER;
    }

    @GetMapping(path = "/searchManagerOrder")
    public String searchOrder(Model model, @RequestParam Long currentUserId,
                              @RequestParam String search,
                              @RequestParam(defaultValue = "0") int currentPage) {

        if (search == null || search.equals(NULL)) {
            return goToManagerMainPage(model, currentUserId, currentPage);
        }
        OrderPaginationFilter orderPaginationFilter = orderService.readOrders(search, currentUserId, currentPage);
        model.addAttribute(LIST_ORDERS, orderPaginationFilter.getListOrders());
        model.addAttribute(CURRENT_PAGE, orderPaginationFilter.getCurrentPage());
        model.addAttribute(MAX_PAGE, orderPaginationFilter.getCountOfTotalPage());
        model.addAttribute(SEARCH, search);
        model.addAttribute(COUNT_NEW_ORDERS, orderPaginationFilter.getCountOfNewOrders());
        return MAIN_PAGE_MANAGER;
    }

    @PostMapping(path = "/managerDeleteOrder")
    public String deleteFromOrderList(Model model, @RequestParam Long tempOrderID, @RequestParam Long currentUserId) {
        orderService.deleteOrder(tempOrderID);
        return goToManagerMainPage(model, currentUserId, ZERO);
    }

    @GetMapping(path = "/allNewOrders")
    public String getAllNewOrders(Model model, @RequestParam(defaultValue = "0") int currentPage) {
        OrderPaginationFilter orderPaginationFilter = orderService.getNewOrderList(currentPage, ROW_IN_PAGE_ORDERS,
                OrderStatus.NEW);
        model.addAttribute(LIST_ORDERS, orderPaginationFilter.getListOrders());
        model.addAttribute(CURRENT_PAGE, orderPaginationFilter.getCurrentPage());
        model.addAttribute(MAX_PAGE, orderPaginationFilter.getCountOfTotalPage());
        return ALL_NEW_ORDER;
    }

    @PostMapping(path = "/acceptOrder")
    public String acceptOrder(Model model, @RequestParam(defaultValue = "0") int currentPage,
                              @RequestParam Long tempOrderID, @RequestParam Long currentUserId) {
        orderService.acceptOrder(tempOrderID, currentUserId);
        return getAllNewOrders(model, currentPage);
    }

    @GetMapping(path = "/getAllUserForManager")
    public String getAllUserForManager(Model model, @RequestParam(defaultValue = "0") int currentPage) {
        Page<UserDTO> userDTOPage = userService.getAllUsers(currentPage, SECOND_PAGE);
        model.addAttribute(USER_LIST, userDTOPage.toList());
        model.addAttribute(CURRENT_PAGE, currentPage);
        model.addAttribute(MAX_PAGE, userDTOPage.getTotalPages());
        return ALL_USERS;
    }

    @GetMapping(path = "/toUpdateUserFromM")
    public String toUpdateManager(Model model, @RequestParam Long userId,
                                  @RequestParam(defaultValue = "0") int currentPage) {

        model.addAttribute(UPDATE_USER, userService.readUser(userId));
        model.addAttribute(CURRENT_PAGE, currentPage);
        return USERS_UPDATE_FORM;
    }

    @PostMapping(path = "/updateUserFromM")
    public String updateUserFromManager(Model model, UserDTO userDTO, @RequestParam Long userId,
                                        @RequestParam(defaultValue = "0") int currentPage) {
        userDTO.setId(userId);
        userService.updateUser(userDTO);
        return getAllUserForManager(model, currentPage);
    }

    @GetMapping(path = "/filterUser")
    public String filterUser(Model model,  @RequestParam(defaultValue = "0") int currentPage,
                             @RequestParam String status) {
        Page<UserDTO> userDTOPage;
        if (USER.equals(status)) {
            userDTOPage = userService.getFilterUsers(currentPage, ROW_IN_PAGE_ORDERS, Roles.USER);
            model.addAttribute(STATUS, USER);
        } else if (SUPER_USER.equals(status)) {
            userDTOPage = userService.getFilterUsers(currentPage, ROW_IN_PAGE_ORDERS, Roles.SUPERUSER);
            model.addAttribute(STATUS, SUPER_USER);
        } else {
            return getAllUserForManager(model, currentPage);
        }
        model.addAttribute(USER_LIST, userDTOPage.toList());
        model.addAttribute(CURRENT_PAGE, currentPage);
        model.addAttribute(MAX_PAGE, userDTOPage.getTotalPages());
        return ALL_USERS;
    }

    @GetMapping(path = "/exit")
    public String exitFromAccount(Model model) {
        model.addAttribute(CURRENT_USER, null);
        return getAuthorizationForm();
    }
}
