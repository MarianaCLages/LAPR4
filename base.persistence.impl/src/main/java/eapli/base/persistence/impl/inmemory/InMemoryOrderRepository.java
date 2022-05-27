package eapli.base.persistence.impl.inmemory;

import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.domain.OrderDate;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainAutoNumberRepository;

import java.util.List;

public class InMemoryOrderRepository extends InMemoryDomainAutoNumberRepository<ClientOrder> implements OrderRepository {

    private static final String NOT_SUPPORTED_YET = "Not supported yet.";

    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<ClientOrder> findAllToBePreparedOrders() {return null;}

    @Override
    public ClientOrder findById(long id) {
        return null;
    }

    @Override
    public void updateOrder(ClientOrder clientOrder) {}

    @Override
    public ClientOrder findByCalendar(OrderDate orderDate) {
        return null;
    }

}
