package application.DTO.orderDTO;

import application.DTO.concreteDTO.ConcreteDTO;
import application.DTO.concreteDTO.ConcreteGradeDTO;
import application.DTO.concreteDTO.MobilityDTO;
import application.DTO.objectDTO.BuildingObjectDTO;
import application.DTO.usersDTO.UserDTO;
import application.entity.enums.orderstatus.OrderStatus;
import jakarta.validation.constraints.NotNull;
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

    private Long id;

    private Timestamp orderTimeCreate;

    private Date dateOfDelivery;

    private Time timeOfDelivery;

    @NotNull
    private Double volumeOfConcrete;

    private String comment;

    @NotNull
    private String numberOfPhone;

    private Double cost;

    private OrderStatus orderStatus;

    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ConcreteDTO concreteDTO;

    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ConcreteGradeDTO concreteGradeDTO;

    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MobilityDTO mobilityDTO;

    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private BuildingObjectDTO buildingObjectDTO;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserDTO userDTO;

}
