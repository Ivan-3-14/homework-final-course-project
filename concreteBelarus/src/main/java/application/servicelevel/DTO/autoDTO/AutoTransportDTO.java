package application.servicelevel.DTO.autoDTO;

import application.datalevel.entities.order.Order;
import application.datalevel.entities.autotransportation.AutoCapacity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoTransportDTO {

    private int id;
    private String number;
    private AutoCapacity capacity;
    private Set<Order> orderSet = new HashSet<>();
}
