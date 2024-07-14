package application.controller;

import application.dto.ProjectDTO;
import application.dto.UserDTO;
import application.entity.Roles;
import application.service.interfaces.ProjectService;
import application.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static application.utils.Constant.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProjectService projectService;

    @GetMapping(path = "/login")
    public String goToSignUpForm() {
        return LOGIN;
    }

    @GetMapping(path = "/userPage")
    public String signUp(Model model) {
        UserDTO userDTO = userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userDTO == null) {
            return null;
        }
        model.addAttribute(CURRENT_USER, userDTO);

        AtomicReference<String> path = new AtomicReference<>(LOGIN);

        if (userDTO.getRoles() != null) {
            userDTO.getRoles().forEach(r -> {
                if (Roles.ADMIN.equals(r.getRole())) {
                    path.set(ADMIN_PAGE);
                } else if (Roles.USER.equals(r.getRole())) {
                    path.set(USER_PAGE);
                }
            });
        }
        return path.get();
    }

    @GetMapping(path = "/createNewUser")
    public String createNewUser() {
        return NEW_USER_FORM;
    }

    @PostMapping(path = "/saveUser")
    public String saveUser(Model model, @Valid UserDTO userForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(ERROR_MESSAGE, ERROR_CREATE_USER_MESSAGE);
            model.addAttribute(PATH_TO_RETURN, CREATE_NEW_USER);
            return ERROR;
        }
        UserDTO userDTO = userService.createUser(userForm);
        if (userDTO == null) {
            model.addAttribute(ERROR_MESSAGE, CREATE_USER_EXIST_MESSAGE);
            model.addAttribute(PATH_TO_RETURN, CREATE_NEW_USER);
            return ERROR;
        }
        return ADMIN_PAGE;
    }

    @GetMapping(path = "/getAllProjects")
    public String getAllProjects(Model model) {
        List<ProjectDTO> projectDTOList = projectService.getAllProjects();
        model.addAttribute(PROJECT_LIST, projectDTOList);
        return ALL_PROJECTS;
    }

    @GetMapping(path = "/exit")
    public String exit(Model model) {
        model.addAttribute(CURRENT_USER, null);
        SecurityContextHolder.getContext().setAuthentication(null);
        return LOGIN;
    }
}
