package application.dto;

import lombok.*;

import java.sql.Timestamp;
import java.time.Duration;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO {

    private Long id;

    private Timestamp startTime;

    private Timestamp endTime;

    private Duration duration;

    @EqualsAndHashCode.Exclude
    private UserDTO user;

    @EqualsAndHashCode.Exclude
    private ProjectDTO project;
}
