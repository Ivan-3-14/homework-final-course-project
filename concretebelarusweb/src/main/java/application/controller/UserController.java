package application.controller;

import application.DTO.filtersDTO.PriceFilterToFront;
import application.DTO.usersDTO.ManagerDTO;
import application.DTO.usersDTO.UserDTO;
import application.entity.enums.roles.Roles;
import application.services.interfaces.PriceService;
import application.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping(path = "/signUp")
    public String goToSignUpForm() {
        return SING_UP_FORM;
    }

    @PostMapping(path = "/signUpEnter")
    public String signUp(Model model, @RequestParam String password, @RequestParam String email) {
        UserDTO userDTO = userService.getUserByEmailAndPassword(email, password);
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
                return MAIN_PAGE_MANAGER;
            }
        } else  if (Roles.ADMIN.equals(userDTO.getRole())) {
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
        return userDTO != null ? CHANGE_PASSWORD_FORM : applicationExceptionController.sendErrorMessage(model,
                PASSWORD_ERROR_MESSAGE, PERSONAL_ACCOUNT);
    }

    @PostMapping(path = "/changePassword")
    public String changePassword(Model model, @RequestParam String newPassword1, @RequestParam String newPassword2,
                                 @RequestParam long currentUserId) {
        UserDTO userDTO = userService.changePassword(newPassword1, newPassword2, currentUserId);
        return userDTO != null ? MAIN_PAGE_FOR_USER : applicationExceptionController.sendErrorMessage(model,
                PASSWORDS_ERROR_MESSAGE, AUTHORIZATION_PAGE);
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
            applicationExceptionController.sendErrorMessage(model, ERROR_USER_ALREADY_EXIST, UPDATE_ME);
            }
        model.addAttribute(CURRENT_USER, result);
        return adminController.goToAccount();
    }

    @GetMapping(path = "/exit")
    public String exitFromAccount(Model model) {
        model.addAttribute(CURRENT_USER, null);
        return getAuthorizationForm();
    }




}
