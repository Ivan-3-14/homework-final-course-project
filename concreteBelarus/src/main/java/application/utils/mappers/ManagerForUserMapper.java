package application.utils.mappers;

import application.datalevel.entities.users.Manager;
import application.servicelevel.DTO.usersDTO.ManagerDTO;

public class ManagerForUserMapper implements Mapper<ManagerDTO, Manager> {

    @Override
    public Manager dtoToEntity(ManagerDTO managerDTO) {
        if (managerDTO == null) {
            return null;
        }
        return Manager.builder()
                .id(managerDTO.getId())
                .role(managerDTO.getRole())
                .build();
    }

    @Override
    public ManagerDTO entityToDTO(Manager manager) {
        if (manager == null) {
            return null;
        }
        return ManagerDTO.builder()
                .id(manager.getId())
                .role(manager.getRole())
                .build();
    }
}
