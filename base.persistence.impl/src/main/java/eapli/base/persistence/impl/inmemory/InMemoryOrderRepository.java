package eapli.base.persistence.impl.inmemory;

import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainAutoNumberRepository;

public class InMemoryOrderRepository extends InMemoryDomainAutoNumberRepository<ClientOrder> implements OrderRepository {

    private static final String NOT_SUPPORTED_YET = "Not supported yet.";

    static {
        InMemoryInitializer.init();
    }
}
