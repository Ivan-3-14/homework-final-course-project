package application.converter;

import application.DTO.usersDTO.ManagerDTO;
import application.entity.users.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ManagerMapper {

    @Mapping(target = "id")
    Manager toEntity(ManagerDTO managerDTO);

    @Mapping(target = "id")
    ManagerDTO toDTO(Manager manager);
}
