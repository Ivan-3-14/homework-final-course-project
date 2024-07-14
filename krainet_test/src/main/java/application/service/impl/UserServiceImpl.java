package application.service.impl;

import application.dto.RoleDTO;
import application.dto.UserDTO;
import application.entity.Project;
import application.entity.Roles;
import application.entity.User;
import application.mapper.RoleMapper;
import application.mapper.UserMapper;
import application.repository.ProjectRepository;
import application.repository.RoleRepository;
import application.repository.UserRepository;
import application.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static application.logger.LoggerPrinter.logPrint;
import static application.utils.Constant.*;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProjectRepository projectRepository;
    private final RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);


    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByLogin(String login) {
        return userMapper.toDTO(userRepository.findByLogin(login));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Set<RoleDTO> rolesSet = new HashSet<>();
        rolesSet.add(roleMapper.toDTO(roleRepository.findByRole(Roles.USER)));
        if (!alreadyExists(userDTO)) {
            userDTO.setRoles(rolesSet);
            userDTO.setPassword(getHashedPassword(userDTO.getPassword()));
            log.info(logPrint(CREATE_USER_SUCCESSFUL_END));
            return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
        }
        log.info(logPrint(CREATE_USER_EXIST_MESSAGE));
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUserParticipantInProject(Long projectId) {
        Project project = projectRepository.getById(projectId);
        List<User> users = userRepository.findAllByProjectSetContains(project);
        return getUserDTOSFromProjectWithRoleUser(users);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUserNoParticipantInProject(Long projectId) {
        Project project = projectRepository.getById(projectId);
        List<User> users = userRepository.findAllByProjectSetNotContains(project);
        return getUserDTOSFromProjectWithRoleUser(users);
    }

    @Override
    public List<UserDTO> addUserToProject(Long projectId, Long userId) {
        Project project = projectRepository.getById(projectId);
        User user = userRepository.getById(userId);
        Set<Project> projectSet = user.getProjectSet();
        Set<User> userSet = project.getUserSet();

        if (projectSet != null) {
            projectSet.add(project);
        }
        user.setProjectSet(projectSet);

        if (userSet != null) {
            userSet.add(user);
        }
        userRepository.save(user);
        projectRepository.save(project);

        return getAllUserNoParticipantInProject(projectId);
    }

    @Override
    public List<UserDTO> deleteUserFromProject(Long projectId, Long userId) {
        Project project = projectRepository.getById(projectId);
        User user = userRepository.getById(userId);
        Set<Project> projectSet = user.getProjectSet();
        Set<User> userSet = project.getUserSet();

        if (projectSet != null) {
            projectSet.remove(project);
        }
        user.setProjectSet(projectSet);

        if (userSet != null) {
            userSet.remove(user);
        }
        userRepository.save(user);
        projectRepository.save(project);

        return getAllUserParticipantInProject(projectId);
    }

    /**
     * checks whether a user exists in the database by his login
     *
     * @param userDTO the user with whom the values ​​in the database are compared
     * @return true if the specified object is contained in the database
     */
    @Transactional(readOnly = true)
    protected boolean alreadyExists(UserDTO userDTO) {
        return getUserByLogin(userDTO.getLogin()) != null;
    }

    /**
     * implements password hashing
     *
     * @param password user password entered when creating it
     * @return encrypted password in type String
     */
    private String getHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private List<UserDTO> getUserDTOSFromProjectWithRoleUser(List<User> users) {
        List<UserDTO> result = new ArrayList<>();
        users.forEach(u -> u.getRoles().forEach(r -> {
            if (!Roles.ADMIN.equals(r.getRole())) {
                result.add(userMapper.toDTO(u));
            }
        }));
        return result;
    }
}
