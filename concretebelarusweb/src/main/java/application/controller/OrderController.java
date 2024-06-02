package application.controller;

import application.DTO.filtersDTO.FilterOrderInform;
import application.DTO.filtersDTO.OrderPaginationFilter;
import application.DTO.orderDTO.OrderDTO;
import application.services.interfaces.OrderService;
import application.utils.ModelUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import static application.utils.Constant.*;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ApplicationExceptionController applicationExceptionController;
    private final ModelUtils modelUtils;

    @GetMapping(path = "/newOrderForm")
    public String goToNewOrderPage() {
        return ORDER_FORM;
    }

    @GetMapping(path = "/orderForm")
    public String goToOrderPage(Model model, @RequestParam String name, @RequestParam String surname,
                                @RequestParam Double volumeOfConcrete, @RequestParam String telephoneNumber,
                                @RequestParam Double distanceToObject, @RequestParam String nameOfObject,
                                @RequestParam String comment
    ) {
        modelUtils.addAttributesToModel(model, nameOfObject, distanceToObject, volumeOfConcrete, comment, name,
                surname, telephoneNumber);
        return ORDER_FORM;
    }

    @PostMapping(path = "/newOrder")
    public String createNewOrder(@Valid FilterOrderInform filterOrderInform, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return applicationExceptionController.sendErrorMessage(model, ERROR_USER_FIELD_NULL, NEW_ORDER_FORM);
        }

        FilterOrderInform filterOrder = orderService.createNewOrder(filterOrderInform, model);

        if (filterOrder.getErrorMessage() != null) {
            return applicationExceptionController.sendErrorMessage(filterOrder.getModel(), filterOrder.getErrorMessage(), ORDER_FORM);
        }

        model.addAttribute(CURRENT_ORDER, filterOrder.getOrderDTO());
        return CONF_FORM;
    }

    @PostMapping(path = "/orderUpdateForm")
    public String goToOrderUpdatePage(Model model, @RequestParam Long currentOrderId) {
        OrderDTO orderDTO = orderService.readOrder(currentOrderId);
        model.addAttribute(CURRENT_ORDER, orderDTO);
        return ORDER_UPDATE;
    }

    @PostMapping(path = "/orderUpdate")
    public String orderUpdate(Model model, @Valid FilterOrderInform filterOrderInform, BindingResult bindingResult,
                              @RequestParam Long currentOrderId) {

        if (bindingResult.hasErrors()) {
            applicationExceptionController.sendErrorMessage(model, ERROR_USER_FIELD_NULL, ORDER_FORM);
        }
        filterOrderInform.setCurrentOrderId(currentOrderId);
        FilterOrderInform filterOrder = orderService.createNewOrder(filterOrderInform, model);

        if (filterOrder.getErrorMessage() != null) {
            return applicationExceptionController.sendErrorMessage(model, filterOrder.getErrorMessage(), ORDER_FORM);
        }

        OrderDTO orderDTO = filterOrder.getOrderDTO();
        model.addAttribute(CURRENT_ORDER, orderDTO);

        return CONF_FORM;
    }

    @PostMapping(path = "/deleteOrder")
    public String deleteOrder(@RequestParam Long currentOrderId) {
        orderService.deleteOrder(currentOrderId);
        return ORDER_FORM;
    }

    @GetMapping(path = "/confirmationOrder")
    public String goToConfirmationOrder() {
        return CONFIRMATION;
    }

    @GetMapping(path = "/myOrders")
    public String goToMyOrders(Model model, @RequestParam Long currentUserId, @RequestParam(defaultValue = "0") int currentPage) {
        OrderPaginationFilter orderPaginationFilter = orderService.getOrderPaginationFilter(currentUserId,
                currentPage, ROW_IN_PAGE_ORDERS);
        model.addAttribute(LIST_ORDERS, orderPaginationFilter.getListOrders());
        model.addAttribute(CURRENT_PAGE, orderPaginationFilter.getCurrentPage());
        model.addAttribute(MAX_PAGE, orderPaginationFilter.getCountOfTotalPage());
        return ALL_ORDERS;
    }

    @GetMapping(path = "/searchOrder")
    public String searchOrder(Model model, @RequestParam Long currentUserId,
                              @RequestParam String search,
                              @RequestParam(defaultValue = "0") int currentPage) {

        if (search == null || search.equals(NULL)) {
            return goToMyOrders(model, currentUserId, currentPage);
        }
        OrderPaginationFilter orderPaginationFilter = orderService.readOrders(search, currentUserId, currentPage);
        model.addAttribute(LIST_ORDERS, orderPaginationFilter.getListOrders());
        model.addAttribute(CURRENT_PAGE, orderPaginationFilter.getCurrentPage());
        model.addAttribute(MAX_PAGE, orderPaginationFilter.getCountOfTotalPage());
        model.addAttribute(SEARCH, search);
        return ALL_ORDERS;
    }

    @PostMapping(path = "/updateOrderList")
    public String updateOrderList(Model model, @RequestParam Long tempOrderID) {
        return goToOrderUpdatePage(model, tempOrderID);
    }

    @PostMapping(path = "/deleteOrderList")
    public String deleteFromOrderList(Model model, @RequestParam Long tempOrderID, @RequestParam Long currentUserId) {
        orderService.deleteOrder(tempOrderID);
        return goToMyOrders(model, currentUserId, NULL_PAGE);
    }
}
