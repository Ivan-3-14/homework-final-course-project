package application.services.interfaces;

import application.DTO.filtersDTO.FilterOrderInform;
import application.DTO.filtersDTO.OrderPaginationFilter;
import application.DTO.orderDTO.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.List;

public interface OrderService {

    FilterOrderInform createNewOrder(FilterOrderInform filterOrderInform, Model model);

    OrderDTO readOrder(Long id);

    void deleteOrder(Long orderId);

    OrderPaginationFilter readOrders(String search, Long currentUserId, int page);


    OrderPaginationFilter getOrderPaginationFilter(Long currentUserId, int currentPage,
                                                   int countOrdersAtPage);

}
