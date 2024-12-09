package application.mapper;

import application.dto.RoleDTO;
import application.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id")
    Role toEntity(RoleDTO roleDTO);

    @Mapping(target = "id")
    RoleDTO toDTO(Role role);

}
