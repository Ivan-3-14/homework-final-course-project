package application.utils.mappers;

import application.datalevel.entities.autotransportation.AutoCapacity;
import application.servicelevel.DTO.autoDTO.AutoCapacityDTO;

public class AutoCapacityMapper implements Mapper<AutoCapacityDTO, AutoCapacity> {

    @Override
    public AutoCapacity dtoToEntity(AutoCapacityDTO autoCapacityDTO) {
        return AutoCapacity.builder()
                .id(autoCapacityDTO.getId())
                .carType(autoCapacityDTO.getCarType())
                .autoCapacity(autoCapacityDTO.getAutoCapacity())
                .autoTransport(autoCapacityDTO.getAutoTransport())
                .autoPrice(autoCapacityDTO.getAutoPrice())
                .deliveryCoefficient(autoCapacityDTO.getDeliveryCoefficient())
                .build();
    }

    @Override
    public AutoCapacityDTO entityToDTO(AutoCapacity autoCapacity) {
        return AutoCapacityDTO.builder()
                .id(autoCapacity.getId())
                .carType(autoCapacity.getCarType())
                .autoCapacity(autoCapacity.getAutoCapacity())
                .autoTransport(autoCapacity.getAutoTransport())
                .autoPrice(autoCapacity.getAutoPrice())
                .deliveryCoefficient(autoCapacity.getDeliveryCoefficient())
                .build();
    }
}
