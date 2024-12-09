package application.service.impl;

import application.dto.ProjectDTO;
import application.mapper.ProjectMapper;
import application.repository.ProjectRepository;
import application.service.interfaces.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper = Mappers.getMapper(ProjectMapper.class);

    @Override
    public ProjectDTO saveProject(ProjectDTO projectDTO) {
        projectDTO.setReadinessLevel(0.0);
        return projectMapper.toDTO(projectRepository.save(projectMapper.toEntity(projectDTO)));
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDTO readProjectById(Long projectId) {
        return projectMapper.toDTO(projectRepository.getById(projectId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> getAllProjects() {
        List<ProjectDTO> result = new ArrayList<>();
        projectRepository.findAll()
                .forEach(p -> result.add(projectMapper.toDTO(p)));
        return result;
    }
}
