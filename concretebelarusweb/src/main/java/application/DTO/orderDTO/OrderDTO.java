package application.DTO.orderDTO;

import application.DTO.concreteDTO.ConcreteDTO;
import application.DTO.concreteDTO.ConcreteGradeDTO;
import application.DTO.concreteDTO.MobilityDTO;
import application.DTO.objectDTO.BuildingObjectDTO;
import application.DTO.usersDTO.ManagerDTO;
import application.DTO.usersDTO.UserDTO;
import application.entity.enums.orderstatus.OrderStatus;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

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

    private Double cost;

    private OrderStatus orderStatus;

    @NotNull
    @EqualsAndHashCode.Exclude
    private ConcreteDTO concreteDTO;

    @NotNull
    @EqualsAndHashCode.Exclude
    private ConcreteGradeDTO concreteGradeDTO;

    @NotNull
    @EqualsAndHashCode.Exclude
    private MobilityDTO mobilityDTO;

    @NotNull
    @EqualsAndHashCode.Exclude
    private BuildingObjectDTO buildingObjectDTO;

    @EqualsAndHashCode.Exclude
    private UserDTO userDTO;

    @EqualsAndHashCode.Exclude
    private ManagerDTO managerDTO;

}
