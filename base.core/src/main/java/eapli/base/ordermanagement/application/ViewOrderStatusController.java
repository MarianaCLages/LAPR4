package eapli.base.ordermanagement.application;

import eapli.base.customermanagement.application.VerifyCustomerService;
import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.domain.OrderState;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.servers.EstablishConnectionService;
import eapli.framework.application.UseCaseController;

import java.util.List;

@UseCaseController
public class ViewOrderStatusController {
    private final EstablishConnectionService establishConnectionService = new EstablishConnectionService();
    private final OrdersIntegrityService ordersIntegrityService = new OrdersIntegrityService();
    private final VerifyCustomerService verifyCustomerService = new VerifyCustomerService();

    public List<OrderDto> getAllOrdersFromLoggedCustomer() {
        return ordersIntegrityService.getAllOrdersFromCustomer(verifyCustomerService.getCustomer(verifyCustomerService.getCustomerEmail()));
        //return establishConnectionService.createConnectionWithTheTcpOrderServer((byte) 10);
    }

    public List<OrderDto> getAllOrdersFromLoggedCustomerInDeliveredState() {
        return ordersIntegrityService.getAllOrdersInTheDeliveredState(verifyCustomerService.getCustomer(verifyCustomerService.getCustomerEmail()));
        //return establishConnectionService.createConnectionWithTheTcpOrderServer((byte) 11);
    }

    public void changeOrderState(int option) {
        ClientOrder order = ordersIntegrityService.getListToChange().get(option);

        order.chanceState(OrderState.RECEIVED_BY_COSTUMER);
        ordersIntegrityService.saveOrder(order);
    }

}
