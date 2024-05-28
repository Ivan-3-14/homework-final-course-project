package application.repository;

import application.entity.enums.roles.Roles;
import application.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByTelephoneNumber(String phoneNumber);

    List<User> findAllByRole(Roles role);
}
