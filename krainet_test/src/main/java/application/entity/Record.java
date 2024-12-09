package application.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Duration;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private Timestamp startTime = new Timestamp(System.currentTimeMillis());

    @Column(name = "end_time")
    private Timestamp endTime = new Timestamp(System.currentTimeMillis());

    @Column(name = "record_duration")
    private Duration duration;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private Project project;
}
