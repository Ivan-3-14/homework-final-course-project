package application.controller;

import application.dto.ProjectDTO;
import application.dto.UserDTO;
import application.service.interfaces.ProjectService;
import application.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static application.utils.Constant.*;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    @GetMapping(path = "/readProject")
    public String readProject(Model model, @RequestParam Long projectId) {
        ProjectDTO projectDTO = projectService.readProjectById(projectId);
        model.addAttribute(CURRENT_PROJECT, projectDTO);
        return READ_PROJECT;
    }

    @GetMapping(path = "/addNewProject")
    public String addNewProject() {
        return NEW_PROJECT_FORM;
    }

    @PostMapping(path = "/saveProject")
    public String saveProject(ProjectDTO projectForm) {
        projectService.saveProject(projectForm);
        return ADMIN_PAGE;
    }

    @PostMapping(path = "/addUsersToProject")
    public String addUsersToProject(Model model, @RequestParam Long projectId) {
        List<UserDTO> userDTOList = userService.getAllUserNoParticipantInProject(projectId);
        model.addAttribute(USER_FOR_PROJECT_LIST, userDTOList);
        model.addAttribute(CURRENT_PROJECT_ID, projectId);
        return ADD_USERS_TO_PROJECTS;
    }

    @PostMapping(path = "/addUserToProject")
    public String addUserToProject(Model model, @RequestParam Long projectId,
                                   @RequestParam Long userId) {
        List<UserDTO> userDTOList = userService.addUserToProject(projectId, userId);
        model.addAttribute(USER_FOR_PROJECT_LIST, userDTOList);
        model.addAttribute(CURRENT_PROJECT_ID, projectId);
        return ADD_USERS_TO_PROJECTS;
    }

    @PostMapping(path = "/deleteUsersFromProject")
    public String deleteUsersFromProject(Model model, @RequestParam Long projectId) {
        List<UserDTO> userDTOList = userService.getAllUserParticipantInProject(projectId);
        model.addAttribute(USER_FOR_PROJECT_LIST, userDTOList);
        model.addAttribute(CURRENT_PROJECT_ID, projectId);
        return DELETE_USERS_FROM_PROJECTS;
    }

    @PostMapping(path = "/deleteUserFromProject")
    public String deleteUserFromProject(Model model, @RequestParam Long projectId,
                                   @RequestParam Long userId) {
        List<UserDTO> userDTOList = userService.deleteUserFromProject(projectId, userId);
        model.addAttribute(USER_FOR_PROJECT_LIST, userDTOList);
        model.addAttribute(CURRENT_PROJECT_ID, projectId);
        return DELETE_USERS_FROM_PROJECTS;
    }
}
