package application.DTO.filtersDTO;

import application.DTO.orderDTO.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaginationFilter {

    private int currentPage;

    private int countOfTotalPage;

    private List<OrderDTO> listOrders;
}
