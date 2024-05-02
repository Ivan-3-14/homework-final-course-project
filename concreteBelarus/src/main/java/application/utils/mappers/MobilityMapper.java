package application.utils.mappers;

import application.datalevel.entities.concreteentities.Mobility;
import application.servicelevel.DTO.concreteDTO.MobilityDTO;

public class MobilityMapper implements Mapper<MobilityDTO, Mobility> {

    @Override
    public Mobility dtoToEntity(MobilityDTO mobilityDTO) {
        return Mobility.builder()
                .id(mobilityDTO.getId())
                .mobilityValue(mobilityDTO.getMobilityValue())
                .concreteGradeSet(mobilityDTO.getConcreteGradeSet())
                .build();
    }

    @Override
    public MobilityDTO entityToDTO(Mobility mobility) {
        return MobilityDTO.builder()
                .id(mobility.getId())
                .mobilityValue(mobility.getMobilityValue())
                .concreteGradeSet(mobility.getConcreteGradeSet())
                .build();
    }
}
