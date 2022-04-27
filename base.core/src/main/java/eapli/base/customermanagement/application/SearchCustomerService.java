package eapli.base.customermanagement.application;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.Email;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class SearchCustomerService {


    private final ClientRepository clientRepository = PersistenceContext.repositories().client();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();


    public Customer searchCustomerServiceById(Long id){
        return clientRepository.findById(id);
    }

    public Customer searchCustomerServiceByEmail(String email){
        return clientRepository.findByEmail(new Email(email));
    }

}
