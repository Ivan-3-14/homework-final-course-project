package application.dto;

import application.entity.Record;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @ToString.Exclude
    private String password;

    @NotBlank
    private String login;

    @ToString.Exclude
    private Set<RoleDTO> roles;

    @ToString.Exclude
    private Set<ProjectDTO> projectSet;

    @ToString.Exclude
    private Set<Record> recordSet;
}
