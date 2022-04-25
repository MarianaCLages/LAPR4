package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaCustomerRepository extends JpaAutoTxRepository<Customer,Long,Long>
                                    implements ClientRepository{



    public JpaCustomerRepository(TransactionalContext tx) {
        super(tx, "Long");
    }

    public JpaCustomerRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "Long");
    }
}
