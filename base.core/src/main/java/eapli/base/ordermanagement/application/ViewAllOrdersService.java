package eapli.base.ordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.domain.OrderDate;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public List<OrderDto> viewAllOrdersToBePrepared() {

        List<OrderDto> orderDtoList = new ArrayList<>();

        for (ClientOrder order : orderRepository.findAllToBePreparedOrders()) {
            orderDtoList.add(order.toDTO());
        }

        return orderDtoList;

    }

    public OrderDto getOrderDTObyID(long id) {
        return orderRepository.findById(id).toDTO();
    }

    public ClientOrder getOrderById(long id) {
        return orderRepository.findById(id);
    }

    public void saveOrder(ClientOrder order) {
        orderRepository.save(order);
    }

}
