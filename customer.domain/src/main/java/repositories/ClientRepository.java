package repositories;

import eapli.framework.domain.repositories.DomainRepository;
import model.Customer;

public interface ClientRepository extends DomainRepository<Long, Customer> {

    /**
     *
     * @return
     */

    Iterable<Customer> findAllergensNotInUse();

}
