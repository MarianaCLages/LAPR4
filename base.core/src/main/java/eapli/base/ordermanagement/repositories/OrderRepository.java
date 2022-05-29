package eapli.base.ordermanagement.repositories;

import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.domain.OrderDate;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface OrderRepository extends DomainRepository<Long, ClientOrder> {
    List<ClientOrder> findAllToBePreparedOrders();

    List<ClientOrder> findAllToBeDispatchedOrders();

    ClientOrder findById(long id);


    void updateOrder(ClientOrder clientOrder);

    ClientOrder findByCalendar(OrderDate orderDate);
}
