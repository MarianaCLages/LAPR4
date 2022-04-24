package eapli.base.customermanagement.application;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class DeleteCustomerService {

    private ClientRepository clientRepository = PersistenceContext.repositories().createClient();
    private AuthorizationService authz = AuthzRegistry.authorizationService();

    public boolean deleteCustomer(Customer customer){
        clientRepository.delete(customer);
        return true;
    }
}
