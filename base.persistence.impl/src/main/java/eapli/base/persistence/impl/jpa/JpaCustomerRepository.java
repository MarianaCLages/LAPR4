package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.Email;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaCustomerRepository extends BasepaRepositoryBase<Customer, Long, Long> implements ClientRepository {


    public JpaCustomerRepository() {
        super("id");
    }

    @Override
    public Customer findById(long id) {
        final TypedQuery<Customer> q = createQuery("SELECT e FROM Customer e WHERE  e.customerId = :m",
                Customer.class);

        q.setParameter("m", id);
        return q.getSingleResult();
    }

    @Override
    public Customer findByEmail(Email email) {
        final TypedQuery<Customer> q = createQuery("SELECT e FROM Customer e WHERE  e.email = :m",
                Customer.class);

        q.setParameter("m", email);
        return q.getResultList().get(0);
    }


}
