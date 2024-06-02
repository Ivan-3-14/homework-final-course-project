package application.entity.users;

import application.entity.enums.roles.Roles;
import application.entity.object.BuildingObject;
import application.entity.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "User.findAllUsersAndSuperUsers", query = "select u from User u where u.role = ?1 OR u.role = ?2")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @ToString.Exclude
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Roles role;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user")
    private Set<BuildingObject> buildingObjectSet = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "user")
    private Set<Order> orderSet = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "user")
    private Manager manager;
}
