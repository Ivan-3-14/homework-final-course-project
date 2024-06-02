package application.services.interfaces;

import application.DTO.filtersDTO.FilterOrderInform;
import application.DTO.filtersDTO.OrderPaginationFilter;
import application.DTO.orderDTO.OrderDTO;
import application.entity.enums.orderstatus.OrderStatus;
import org.springframework.ui.Model;

public interface OrderService {

    FilterOrderInform createNewOrder(FilterOrderInform filterOrderInform, Model model);

    OrderDTO readOrder(Long id);

    void deleteOrder(Long orderId);

    OrderPaginationFilter readOrders(String search, Long currentUserId, int page);

    OrderPaginationFilter getOrderPaginationFilter(Long currentUserId, int currentPage, int countOrdersAtPage);

    OrderPaginationFilter getOrderPaginationFilterForManager(Long currentManagerId, int currentPage,
                                                             int countOrdersAtPage);

    OrderPaginationFilter getNewOrderList(int currentPage, int countOrdersAtPage, OrderStatus orderStatus);

    void acceptOrder(Long orderId, Long userId);
}
