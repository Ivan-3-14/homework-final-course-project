package application.services.impl;

import application.DTO.usersDTO.UserDTO;
import application.converter.UserMapper;
import application.entity.enums.roles.Roles;
import application.entity.users.User;
import application.repository.UserRepository;
import application.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public UserDTO createUser(UserDTO userDTO) {
        if (!alreadyExists(userDTO)) {
            userDTO.setPassword(getHashedPassword(userDTO.getPassword()));
            return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
        }
        return null;
    }

    public UserDTO readUser(Long userId) {
        return userMapper.toDTO(userRepository.getById(userId));
    }

    public UserDTO updateUser(UserDTO userDTO) {
        userDTO = checkExistUpdateUser(userDTO);
        return Optional.ofNullable(userDTO)
                .map(userMapper::toEntity)
                .map(userRepository::save)
                .map(userMapper::toDTO)
                .orElse(null);
    }

    public void deleteUser(Long userId) {
            userRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserByEmailAndPassword(String email, String password) {
        if (email != null && password != null) {
            UserDTO user = getUserByEmail(email);
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email) {
        return userMapper.toDTO(userRepository.findByEmail(email));
    }

    public UserDTO changePassword(String password1, String password2, Long currentUserId) {
        if (!password1.equals(password2)) {
            return null;
        }
        User user = userRepository.getById(currentUserId);
        user.setPassword(getHashedPassword(password1));
        return userMapper.toDTO(userRepository.save(user));
    }

    public UserDTO checkExistUser(UserDTO userDTO) {
        if (userDTO != null) {
            UserDTO user = checkSuperUserByPhoneNumber(userDTO.getTelephoneNumber());
            if (user == null) {
                user = userMapper.toDTO(userRepository.save(userMapper.toEntity(
                        UserDTO.builder()
                                .role(Roles.SUPERUSER)
                                .telephoneNumber(userDTO.getTelephoneNumber())
                                .name(userDTO.getName())
                                .surname(userDTO.getSurname())
                                .build()
                        )
                ));
            }
            return user;
        }
        return null;
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
            temp = getUserByEmail(userDTO.getEmail());
            if (temp == null) {
                return userDTO;
            } else if(userDTO.getId().equals(temp.getId())) {
                userDTO.setRole(temp.getRole());
                return userDTO;
            }
        }
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
