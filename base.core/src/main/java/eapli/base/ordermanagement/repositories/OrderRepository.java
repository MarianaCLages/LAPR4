package eapli.base.ordermanagement.repositories;

import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface OrderRepository extends DomainRepository<Long, ClientOrder> {
    List<ClientOrder> findAllToBePreparedOrders();

    ClientOrder findById(long id);

}
