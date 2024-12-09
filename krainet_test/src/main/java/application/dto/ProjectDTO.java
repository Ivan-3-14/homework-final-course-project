package application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Long id;

    private String name;

    private Double readinessLevel;

    private String description;

    private Set<RecordDTO> recordDTOSet;

    private Set<UserDTO> userDTOSet;
}
