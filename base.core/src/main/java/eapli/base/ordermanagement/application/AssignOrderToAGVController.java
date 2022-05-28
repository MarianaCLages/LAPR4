package eapli.base.ordermanagement.application;

import eapli.base.agvmanagement.application.ViewAllAgvsService;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.dto.AGVDto;
import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.domain.OrderState;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.framework.application.UseCaseController;

import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class AssignOrderToAGVController {

    private final ViewAllOrdersService viewAllOrdersService = new ViewAllOrdersService();
    private final ViewAllAgvsService viewAllAgvsService = new ViewAllAgvsService();
    private final AssignOrderToAGVService assignOrderToAGVService = new AssignOrderToAGVService();

    public List<OrderDto> viewAllOrdersToBePrepared() {
        return viewAllOrdersService.viewAllOrdersToBePrepared();
    }

    public List<AGVDto> viewAllAGVs(double weight) {

        List<AGVDto> list = new ArrayList<>();

        for (AGVDto agvDto : viewAllAgvsService.viewAll()) {

            if (agvDto.getCapacity() > weight) {
                list.add(agvDto);
            }
        }
        return list;
    }


    public void assignOrderToAGV(OrderDto orderDto, AGVDto agvDto) {

        ClientOrder order = viewAllOrdersService.getOrderById(orderDto.getId());
        AGV agv = viewAllAgvsService.getAgvById(agvDto.getId());

        if (assignOrderToAGVService.assignOrderToAGV(orderDto, agvDto)) {
            agv.changeClientOrder(order);
            viewAllAgvsService.saveAgv(agv);
            order.chanceState(OrderState.BEING_PREPARED);
            viewAllOrdersService.saveOrder(order);
        }
    }
}
