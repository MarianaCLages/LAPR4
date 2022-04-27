package eapli.base.customermanagement.repositories;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.repositories.DomainRepository;


public interface ClientRepository extends DomainRepository<Long,Customer> {

    Customer findById(long id);



}
