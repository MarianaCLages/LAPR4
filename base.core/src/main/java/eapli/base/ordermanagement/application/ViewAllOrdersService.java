package eapli.base.ordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;

import java.util.ArrayList;
import java.util.List;

@ApplicationService
public class ViewAllOrdersService {

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public List<OrderDto> viewAllOrders() {

        List<OrderDto> orderDtoList = new ArrayList<>();

        for (ClientOrder order : orderRepository.findAll()) {
            orderDtoList.add(order.toDTO());
        }

        return orderDtoList;

    }

}
