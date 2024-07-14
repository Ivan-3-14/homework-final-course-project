package application.repository;

import application.entity.Project;
import application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    List<User> findAllByProjectSetContains(Project project);

    List<User> findAllByProjectSetNotContains(Project project);

}
