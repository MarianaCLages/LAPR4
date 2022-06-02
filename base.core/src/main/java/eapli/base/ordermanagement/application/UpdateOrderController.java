package eapli.base.ordermanagement.application;

import eapli.base.agvmanagement.application.ViewAllAgvsService;
import eapli.base.agvmanagement.dto.AGVDto;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.framework.application.UseCaseController;

import java.util.List;

@UseCaseController
public class UpdateOrderController {

    private final ViewAllAgvsService agvsService = new ViewAllAgvsService();
    private final ViewAllOrdersService ordersService = new ViewAllOrdersService();

    public List<AGVDto> getAgvDTOList() {
        return agvsService.getAGVList();
    }

    public List<OrderDto> getOrdersToBeDispatchedFromAGV(AGVDto agv) {
        return agvsService.getOrdersToBeDispatchedFromAGV(agv);
    }

    public List<OrderDto> getOrdersToBeDispatchedList() {
        return ordersService.getOrdersToBeDispatchedList();
    }

    public void updateOrderStatusAsDispatched(OrderDto order) {
        ordersService.changeOrderStatusAsDispatched(order);
    }

    public void updateOrderStatusAsBeingDelivered(OrderDto order){ ordersService.changeOrderStatusAsBeingDelivered(order);}
}
