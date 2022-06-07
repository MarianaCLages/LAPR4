package eapli.base.ordermanagement.application;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;

import java.util.ArrayList;
import java.util.List;

@ApplicationService
public class OrdersIntegrityService {

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public List<OrderDto> getAllOrdersFromCustomer(Customer customer) {
        List<OrderDto> orderList = new ArrayList<>();

        for (ClientOrder clientOrder : orderRepository.findAll()) {
            if (clientOrder.getCustomer().equals(customer)) {
                orderList.add(clientOrder.toDTO());
            }
        }

        return orderList;
    }
}