package application.services.impl;

import application.DTO.usersDTO.UserDTO;
import application.converter.UserMapper;
import application.entity.enums.roles.Roles;
import application.entity.users.User;
import application.repository.UserRepository;
import application.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static application.utils.Constant.*;
import static application.logger.LoggerPrinter.logPrint;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (!alreadyExists(userDTO)) {
            log.info(logPrint(ALREADY_EXIST_END));
            userDTO.setPassword(getHashedPassword(userDTO.getPassword()));
            log.info(logPrint(CREATE_USER_SUCCESSFUL_END));
            return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
        }
        log.info(logPrint(CREATE_USER_EXIST_MESSAGE));
        return null;
    }

    @Override
    public UserDTO readUser(Long userId) {
        return userMapper.toDTO(userRepository.getById(userId));
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        userDTO = checkExistUpdateUser(userDTO);
        return Optional.ofNullable(userDTO)
                .map(userMapper::toEntity)
                .map(userRepository::save)
                .map(userMapper::toDTO)
                .orElse(null);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByEmailAndPassword(String email, String password) {
        if (email != null && password != null) {
            UserDTO user = getUserByEmail(email);
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                log.info(logPrint(GET_USER_BY_EMAIL_PASS_SUCCESSFUL));
                return user;
            }
        }
        log.info(logPrint(GET_USER_BY_EMAIL_PASS_NOT_SUCCESSFUL));
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email) {
        return userMapper.toDTO(userRepository.findByEmail(email));
    }

    @Override
    public UserDTO changePassword(String password1, String password2, Long currentUserId) {
        if (!password1.equals(password2)) {
            log.info(logPrint(PASSWORD_NOT_EQUALS));
            return null;
        }
        User user = userRepository.getById(currentUserId);
        user.setPassword(getHashedPassword(password1));
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO checkExistUser(UserDTO userDTO) {
        if (userDTO != null) {
            UserDTO user = checkSuperUserByPhoneNumber(userDTO.getTelephoneNumber());
            if (user == null) {
                log.info(logPrint(CHECK_SUPER_USER_NOT_FOUND));
                user = userMapper.toDTO(userRepository.save(userMapper.toEntity(
                        UserDTO.builder()
                                .role(Roles.SUPERUSER)
                                .telephoneNumber(userDTO.getTelephoneNumber())
                                .name(userDTO.getName())
                                .surname(userDTO.getSurname())
                                .build()
                        )
                ));
                log.info(logPrint(CREATE_NEW_USER));
            }
            log.info(logPrint(CHECK_EXIST_USER_SUCCESSFUL_END));
            return user;
        }
        log.info(logPrint(CHECK_EXIST_USER_END));
        return null;
    }

    @Override
    public Page<UserDTO> getAllUsers(int currentPage, int countOrdersAtPage) {
        return userRepository.findAllUsersAndSuperUsers(Roles.USER, Roles.SUPERUSER,
                PageRequest.of(currentPage, countOrdersAtPage)).map(userMapper::toDTO);
    }

    @Override
    public Page<UserDTO> getFilterUsers(int currentPage, int countOrdersAtPage, Roles role) {
        return userRepository.findUserByRole(role,
                PageRequest.of(currentPage, countOrdersAtPage)).map(userMapper::toDTO);
    }

    @Override
    public List<UserDTO> getAllManager() {
        return userRepository.findAllByRole(Roles.MANAGER)
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    protected UserDTO checkExistUpdateUser(UserDTO userDTO) {
        UserDTO temp;
        if (userDTO != null) {
            if (userDTO.getEmail() == null) {
                temp = checkSuperUserByPhoneNumber(userDTO.getTelephoneNumber());
            } else {
                temp = getUserByEmail(userDTO.getEmail());
            }
            if (temp == null) {
                log.info(logPrint(CHECK_EXIST_UPDATE_NOT_FOUND));
                log.info(logPrint(CHECK_EXIST_UPDATE_NOT_FOUND_MESSAGE));
                return userDTO;
            } else if (userDTO.getId().equals(temp.getId())) {
                userDTO.setRole(temp.getRole());
                log.info(logPrint(CHECK_EXIST_UPDATE_SUCCESSFUL_END));
                return userDTO;
            }
        }
        log.info(logPrint(CHECK_EXIST_UPDATE_END));
        return null;
    }

    @Transactional(readOnly = true)
    protected UserDTO checkSuperUserByPhoneNumber(String phoneNumber) {
        return userMapper.toDTO(userRepository.findByTelephoneNumber(phoneNumber));
    }

    @Transactional(readOnly = true)
    protected boolean alreadyExists(UserDTO userDTO) {
        return getUserByEmail(userDTO.getEmail()) != null;
    }

    private String getHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
