package application.services.impl;

import application.DTO.usersDTO.ManagerDTO;
import application.DTO.usersDTO.UserDTO;
import application.converter.ManagerMapper;
import application.converter.UserMapper;
import application.entity.enums.roles.Roles;
import application.entity.users.Manager;
import application.repository.ManagerRepository;
import application.services.interfaces.ManagerService;
import application.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final UserService userService;
    private final ManagerMapper managerMapper = Mappers.getMapper(ManagerMapper.class);
    private final UserMapper userMapperMapper = Mappers.getMapper(UserMapper.class);

    public ManagerDTO readManagerDTO(Long managerId) {
        return managerMapper.toDTO(managerRepository.findById(managerId).orElse(new Manager()));
    }

    public ManagerDTO createManager(UserDTO userDTO) {
        UserDTO user = userService.createUser(userDTO);
        if (user == null) {
            System.out.println("                           nulll                            ");
            return null;
        }
        return managerMapper.toDTO(managerRepository.save(Manager.builder()
                .role(Roles.MANAGER)
                .user(userMapperMapper.toEntity(user))
                .build()
        ));
    }

}
