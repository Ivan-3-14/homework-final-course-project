package application.mapper;

import application.dto.ProjectDTO;
import application.dto.RecordDTO;
import application.dto.UserDTO;
import application.entity.Project;
import application.entity.Record;
import application.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecordMapper {

    @Mapping(target = "id")
    @Mapping(expression = "java(getProject(recordDTO))", target = "project")
    @Mapping(expression = "java(getUser(recordDTO))", target = "user")
    Record toEntity(RecordDTO recordDTO);

    @Mapping(target = "id")
    @Mapping(expression = "java(getProjectDTO(record))", target = "project")
    @Mapping(expression = "java(getUserDTO(record))", target = "user")
    RecordDTO toDTO(Record record);

    default ProjectDTO getProjectDTO(Record record) {
        return ProjectDTO.builder()
                .id(record.getProject().getId())
                .name(record.getProject().getName())
                .description(record.getProject().getDescription())
                .readinessLevel(record.getProject().getReadinessLevel())
                .build();
    }

    default Project getProject(RecordDTO recordDTO) {
        return Project.builder()
                .id(recordDTO.getProject().getId())
                .name(recordDTO.getProject().getName())
                .description(recordDTO.getProject().getDescription())
                .readinessLevel(recordDTO.getProject().getReadinessLevel())
                .build();
    }

    default UserDTO getUserDTO(Record record) {
        return UserDTO.builder()
                .id(record.getUser().getId())
                .build();
    }

    default User getUser(RecordDTO recordDTO) {
        return User.builder()
                .id(recordDTO.getUser().getId())
                .build();
    }
}
