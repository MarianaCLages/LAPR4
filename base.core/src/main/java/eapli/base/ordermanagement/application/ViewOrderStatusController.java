package eapli.base.ordermanagement.application;

import eapli.base.customermanagement.application.VerifyCustomerService;
import eapli.base.ordermanagement.domain.OrderState;
import eapli.base.servers.EstablishConnectionService;
import eapli.framework.application.UseCaseController;

import java.util.List;

@UseCaseController
public class ViewOrderStatusController {
    private final EstablishConnectionService establishConnectionService = new EstablishConnectionService();
    private final OrdersIntegrityService ordersIntegrityService = new OrdersIntegrityService();
    private final VerifyCustomerService verifyCustomerService = new VerifyCustomerService();

    public List<String> getAllOrdersFromLoggedCustomer() {
        return establishConnectionService.createConnectionWithTheTcpOrderServer((byte) 10);
    }

    public List<String> getAllOrdersFromLoggedCustomerInDeliveredState() {
        return establishConnectionService.createConnectionWithTheTcpOrderServer((byte) 11);
    }

    public void changeOrderState(int index) {
        ordersIntegrityService.getAllOrdersInTheDeliveredStateWithoutDTO(verifyCustomerService.getCustomer(verifyCustomerService.getCustomerEmail())).get(index).chanceState(OrderState.RECEIVED_BY_COSTUMER);
    }

}
