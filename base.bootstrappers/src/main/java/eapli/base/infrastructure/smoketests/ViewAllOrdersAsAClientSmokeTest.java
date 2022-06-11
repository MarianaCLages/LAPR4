package eapli.base.infrastructure.smoketests;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.application.OrdersIntegrityService;
import eapli.base.ordermanagement.domain.OrderState;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.framework.actions.Action;
import org.slf4j.Logger;

import java.util.List;


public class ViewAllOrdersAsAClientSmokeTest implements Action {
    Logger logger = org.slf4j.LoggerFactory.getLogger(ViewAllOrdersAsAClientSmokeTest.class);

    private final ClientRepository repository = PersistenceContext.repositories().client();
    private final OrdersIntegrityService ordersIntegrityService = new OrdersIntegrityService();

    @Override
    public boolean execute() {
        viewAllOpenOrders();
        return false;
    }

    private void viewAllOpenOrders() {
        try {
            Customer customer = repository.findById(82);

            String receivedCostumer = OrderState.RECEIVED_BY_COSTUMER.toString();

            List<OrderDto> list = ordersIntegrityService.getAllOrdersFromCustomer(customer);

            if (list.isEmpty()) {
                throw new RuntimeException("The List is empty! Please verify if the Customer is valid or if the customer has any open order!");
            }

            for (OrderDto dto : list) {
                if (dto.getState().equals(OrderState.RECEIVED_BY_COSTUMER.toString())) {
                    throw new RuntimeException("There is an order with an invalid state inside the list!");
                }

                dto.changeState(receivedCostumer);

                if (!dto.getState().equals(receivedCostumer)) {
                    throw new RuntimeException("There was an error while changing the order state!");
                }

            }

            logger.info(("»»» View all client orders (Success!)"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}