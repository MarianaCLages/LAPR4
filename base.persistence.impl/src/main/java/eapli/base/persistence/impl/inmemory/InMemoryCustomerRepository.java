package eapli.base.persistence.impl.inmemory;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainAutoNumberRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCustomerRepository extends InMemoryDomainRepository<Customer,Long>
        implements ClientRepository {//extends InMemoryDomainAutoNumberRepository<Customer> implements ClientRepository {



    private static final String NOT_SUPPORTED_YET = "Not supported yet.";


    static{
        InMemoryInitializer.init();
    }
}
