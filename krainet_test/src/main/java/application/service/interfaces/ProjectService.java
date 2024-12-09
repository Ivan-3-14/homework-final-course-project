package application.service.interfaces;

import application.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO saveProject(ProjectDTO projectDTO);

    ProjectDTO readProjectById(Long projectId);

    List<ProjectDTO> getAllProjects();
}
