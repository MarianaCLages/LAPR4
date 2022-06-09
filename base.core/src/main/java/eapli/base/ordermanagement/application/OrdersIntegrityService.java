package eapli.base.ordermanagement.application;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.domain.OrderState;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationService
public class OrdersIntegrityService {

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final List<ClientOrder> listToChange = new ArrayList<>();

    public List<OrderDto> getAllOrdersFromCustomer(Customer customer) {
        List<OrderDto> orderList = new ArrayList<>();

        for (ClientOrder clientOrder : orderRepository.findAll()) {
            if (Objects.equals(clientOrder.getCustomer().identity(), customer.identity()) && !(clientOrder.state().equals(OrderState.RECEIVED_BY_COSTUMER))) {
                orderList.add(clientOrder.toDTO());
            }
        }

        return orderList;
    }

    public List<OrderDto> getAllOrdersInTheDeliveredState(Customer customer) {
        List<OrderDto> orderList = new ArrayList<>();

        for (ClientOrder clientOrder : orderRepository.findAll()) {
            if (Objects.equals(clientOrder.getCustomer().identity(), customer.identity()) && (clientOrder.state().equals(OrderState.DELIVERED_BY_CARRIED))) {
                orderList.add(clientOrder.toDTO());
                listToChange.add(clientOrder);
            }
        }

        return orderList;
    }

    public List<ClientOrder> getListToChange() {
        return this.listToChange;
    }

    public void saveOrder(ClientOrder order) {
        orderRepository.save(order);
    }

}