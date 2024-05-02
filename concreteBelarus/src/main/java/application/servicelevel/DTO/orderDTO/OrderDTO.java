package application.servicelevel.DTO.orderDTO;

import application.servicelevel.DTO.autoDTO.AutoTransportDTO;
import application.servicelevel.DTO.concreteDTO.ConcreteDTO;
import application.servicelevel.DTO.concreteDTO.ConcreteGradeDTO;
import application.servicelevel.DTO.concreteDTO.MobilityDTO;
import application.servicelevel.DTO.objectDTO.BuildingObjectDTO;
import application.servicelevel.DTO.usersDTO.UserDTO;
import application.utils.enums.orderstatus.OrderStatus;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private int id;

    private Timestamp orderTimeCreate;

    private Date dateOfDelivery;

    private Time timeOfDelivery;

    private Double volumeOfConcrete;

    private String comment;

    private String numberOfPhone;

    private Double cost;

    private OrderStatus orderStatus;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ConcreteDTO concreteDTO;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ConcreteGradeDTO concreteGradeDTO;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MobilityDTO mobilityDTO;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private BuildingObjectDTO buildingObjectDTO;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserDTO userDTO;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<AutoTransportDTO> autoTransportDTOSet = new HashSet<>();
}
