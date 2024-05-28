package application.entity.users;

import application.entity.enums.roles.Roles;
import application.entity.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Roles role = Roles.MANAGER;

    @OneToMany(mappedBy = "manager")
    private Set<Order> orderSet = new HashSet<>();
}
