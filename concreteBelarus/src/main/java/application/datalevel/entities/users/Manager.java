package application.datalevel.entities.users;

import application.datalevel.entities.order.Order;
import application.utils.enums.roles.Roles;
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
    private int id;

    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Roles role = Roles.MANAGER;

    @OneToMany(mappedBy = "manager")
    private Set<Order> orderSet = new HashSet<>();
}
