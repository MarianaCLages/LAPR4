package eapli.base.customermanagement.repositories;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.Email;
import eapli.base.customermanagement.domain.Name;
import eapli.framework.domain.repositories.DomainRepository;


public interface ClientRepository extends DomainRepository<Long, Customer> {

    Customer findById(long id);

    Customer findByEmail(Email email, Name name);

    Customer findByEmailOnly(Email email);

    boolean orderedTheBrand(String name, String email, String ruleValue);

    boolean orderedTheProducts(String name, String email, String ruleValue);
}
