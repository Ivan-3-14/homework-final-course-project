package application.entity;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Range(max = 100)
    @Column(name = "readiness_level")
    private Integer readinessLevel;

    @Column(name = "description")
    private String description;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "project")
    private Set<Record> recordSet = new HashSet<>();

    @ManyToMany(mappedBy = "projectSet", fetch = FetchType.LAZY)
    private Set<User> userSet = new HashSet<>();
}

