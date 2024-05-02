package application.utils.mappers;

import application.datalevel.entities.autotransportation.AutoTransport;
import application.servicelevel.DTO.autoDTO.AutoTransportDTO;

public class AutoTransportMapper implements Mapper<AutoTransportDTO, AutoTransport> {

    @Override
    public AutoTransport dtoToEntity(AutoTransportDTO autoTransportDTO) {
        return AutoTransport.builder()
                .id(autoTransportDTO.getId())
                .number(autoTransportDTO.getNumber())
                .capacity(autoTransportDTO.getCapacity())
                .orderSet(autoTransportDTO.getOrderSet())
                .build();
    }

    @Override
    public AutoTransportDTO entityToDTO(AutoTransport autoTransport) {
        return AutoTransportDTO.builder()
                .id(autoTransport.getId())
                .number(autoTransport.getNumber())
                .capacity(autoTransport.getCapacity())
                .orderSet(autoTransport.getOrderSet())
                .build();
    }
}
