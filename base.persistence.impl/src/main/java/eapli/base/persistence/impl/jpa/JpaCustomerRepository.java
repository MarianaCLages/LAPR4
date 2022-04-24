package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.CustomerName;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Map;
import java.util.Optional;

public class JpaCustomerRepository extends JpaAutoTxRepository<Customer,Long,Long>
                                    implements ClientRepository{



    public JpaCustomerRepository(TransactionalContext tx) {
        super(tx, "Long");
    }

    public JpaCustomerRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "Long");
    }
}
