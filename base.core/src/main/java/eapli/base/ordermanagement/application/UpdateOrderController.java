package eapli.base.ordermanagement.application;

import eapli.base.agvmanagement.application.ViewAllAgvsService;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.dto.AGVDto;
import eapli.base.ordermanagement.domain.ClientOrder;
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

    public void updateOrderStatus(OrderDto order) {
        ordersService.changeOrderStatus(order);
    }
}
