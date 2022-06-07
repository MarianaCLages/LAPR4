package eapli.base.ordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.domain.OrderState;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.servers.EstablishConnectionService;
import eapli.framework.application.ApplicationService;

import java.util.ArrayList;
import java.util.List;

@ApplicationService
public class ViewAllOrdersService {

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final EstablishConnectionService establishConnectionService = new EstablishConnectionService();

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

    public List<OrderDto> getOrdersToBeDispatchedList() {
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (ClientOrder order : orderRepository.findAllToBeDispatchedOrders()) {
            orderDtoList.add(order.toDTO());
        }

        return orderDtoList;
    }

    public void changeOrderStatusAsDispatched(OrderDto order) {
        ClientOrder clientOrder = orderRepository.findById(order.getId());
        clientOrder.chanceState(OrderState.DISPATCHED);
        saveOrder(clientOrder);
    }

    public void changeOrderStatusAsBeingDelivered(OrderDto order) {
        ClientOrder clientOrder = orderRepository.findById(order.getId());
        clientOrder.chanceState(OrderState.DELIVERED_BY_CARRIED);
        saveOrder(clientOrder);
    }

    public List<String> getAllOrdersFromServer() {
        return establishConnectionService.createConnectionWithTheTcpOrderServer((byte) 10);
    }


}
