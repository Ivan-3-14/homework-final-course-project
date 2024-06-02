package application.DTO.filtersDTO;

import application.DTO.objectDTO.BuildingObjectDTO;
import application.DTO.orderDTO.OrderDTO;
import application.DTO.usersDTO.ManagerDTO;
import application.DTO.usersDTO.UserDTO;
import application.entity.concreteentities.Concrete;
import application.entity.concreteentities.ConcreteGrade;
import application.entity.enums.orderstatus.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.ui.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static application.utils.Constant.PHONE_NUMBER_REGEX;
import static org.springframework.format.annotation.NumberFormat.Style.NUMBER;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterOrderInform {

    private Long currentOrderId;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Pattern(regexp = PHONE_NUMBER_REGEX)
    private String telephoneNumber;

    @NotBlank
    private String nameOfObject;

    @NumberFormat(style = NUMBER)
    @Range(max = 120)
    private Double distanceToObject;

    private String aggregate;

    private String concreteGrade;

    private OrderStatus orderStatus;

    @NotNull
    @Range(max = 250)
    @NumberFormat(style = NUMBER)
    private Double volumeOfConcrete;

    private String mobility;

    private String dateOfDelivery;

    private String timeOfDelivery;

    private String comment;

    private UserDTO userDTO;

    private OrderDTO orderDTO;

    private BuildingObjectDTO buildingObjectDTO;

    private String errorMessage;

    private Model model;

    private ConcreteGrade grade;

    private Concrete concrete;

    private Long managerID;

}
