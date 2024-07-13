package application.mapper;

import application.dto.ProjectDTO;
import application.dto.RoleDTO;
import application.dto.UserDTO;
import application.entity.Project;
import application.entity.Role;
import application.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RoleMapper ROLE_MAPPER = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "id")
    @Mapping(target = "recordSet", ignore = true)
    @Mapping(expression = "java(getRoleSet(userDTO))", target = "roles")
    @Mapping(expression = "java(getProjectSet(userDTO))", target = "projectSet")
    User toEntity(UserDTO userDTO);

    @Mapping(target = "id")
    @Mapping(expression = "java(getRoleDTOSet(user))", target = "roles")
    @Mapping(expression = "java(getProjectDTOSet(user))", target = "projectSet")
    UserDTO toDTO(User user);

    default Set<RoleDTO> getRoleDTOSet(User user) {
        Set<RoleDTO> roleDTOSet = new HashSet<>();
        if (user != null && user.getRoles() != null) {
            user.getRoles().forEach(role -> roleDTOSet.add(ROLE_MAPPER.toDTO(role)));
        }
        return roleDTOSet;
    }

    default Set<Role> getRoleSet(UserDTO userDTO) {
        Set<Role> roleSet = new HashSet<>();
        if (userDTO != null && userDTO.getRoles() != null) {
            userDTO.getRoles().forEach(role -> roleSet.add(ROLE_MAPPER.toEntity(role)));
        }
        return roleSet;
    }

    default Set<ProjectDTO> getProjectDTOSet(User user) {
        Set<ProjectDTO> projectDTOSet = new HashSet<>();
        if (user != null && user.getProjectSet() != null) {
            user.getProjectSet().forEach(p -> projectDTOSet.add(ProjectDTO.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .description(p.getDescription())
                    .readinessLevel(p.getReadinessLevel())
                    .build()));
        }
        return projectDTOSet;
    }

    default Set<Project> getProjectSet(UserDTO userDTO) {
        Set<Project> projectSet = new HashSet<>();
        if (userDTO != null && userDTO.getProjectSet() != null) {
            userDTO.getProjectSet().forEach(p -> projectSet.add(Project.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .description(p.getDescription())
                    .readinessLevel(p.getReadinessLevel())
                    .build()));
        }
        return projectSet;
    }
}
