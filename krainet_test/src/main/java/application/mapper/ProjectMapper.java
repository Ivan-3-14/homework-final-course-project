package application.mapper;

import application.dto.ProjectDTO;
import application.dto.RecordDTO;
import application.dto.UserDTO;
import application.entity.Project;
import application.entity.Record;
import application.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    RoleMapper ROLE_MAPPER = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "id")
    @Mapping(expression = "java(getRecordSet(projectDTO))", target = "recordSet")
    @Mapping(expression = "java(getUserSet(projectDTO))", target = "userSet")
    Project toEntity(ProjectDTO projectDTO);

    @Mapping(target = "id")
    @Mapping(expression = "java(getRecordDTOSet(project))", target = "recordDTOSet")
    @Mapping(expression = "java(getUserDTOSet(project))", target = "userDTOSet")
    ProjectDTO toDTO(Project project);

    default Set<RecordDTO> getRecordDTOSet(Project project) {
        Set<RecordDTO> recordDTOSet = new HashSet<>();
        if (project != null && project.getRecordSet() != null) {
            project.getRecordSet().forEach(r -> recordDTOSet.add(RecordDTO.builder()
                    .id(r.getId())
                    .startTime(r.getStartTime())
                    .endTime(r.getEndTime())
                    .duration(r.getDuration())
                    .build()));
        }
        return recordDTOSet;
    }

    default Set<Record> getRecordSet(ProjectDTO projectDTO) {
        Set<Record> recordDTOSet = new HashSet<>();
        if (projectDTO != null && projectDTO.getRecordDTOSet() != null) {
            projectDTO.getRecordDTOSet().forEach(r -> recordDTOSet.add(Record.builder()
                    .id(r.getId())
                    .startTime(r.getStartTime())
                    .endTime(r.getEndTime())
                    .duration(r.getDuration())
                    .build()));
        }
        return recordDTOSet;
    }

    default Set<UserDTO> getUserDTOSet(Project project) {
        Set<UserDTO> userDTOSet = new HashSet<>();
        if (project != null && project.getUserSet() != null) {
            project.getUserSet().forEach(u -> userDTOSet.add(UserDTO.builder()
                    .id(u.getId())
                    .name(u.getName())
                    .surname(u.getSurname())
                    .roles(u.getRoles().stream().map(ROLE_MAPPER::toDTO).collect(Collectors.toSet()))
                    .build()));
        }
        return userDTOSet;
    }

    default Set<User> getUserSet(ProjectDTO projectDTO) {
        Set<User> userSet = new HashSet<>();
        if (projectDTO != null && projectDTO.getUserDTOSet() != null) {
            projectDTO.getUserDTOSet().forEach(u -> userSet.add(User.builder()
                    .id(u.getId())
                    .name(u.getName())
                    .surname(u.getSurname())
                    .roles(u.getRoles().stream().map(ROLE_MAPPER::toEntity).collect(Collectors.toSet()))
                    .build()));
        }
        return userSet;
    }
}
