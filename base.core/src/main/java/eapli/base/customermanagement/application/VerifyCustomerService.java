package eapli.base.customermanagement.application;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.Email;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Optional;

public class VerifyCustomerService {

    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final ClientRepository clientRepository = PersistenceContext.repositories().client();

    public String getCustomerEmail() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.CLIENT_USER, BaseRoles.POWER_USER);
        Optional<SystemUser> user = authorizationService.session().filter(userSession -> userSession.authenticatedUser().identity().equals(userSession.authenticatedUser().identity())).map(UserSession::authenticatedUser);
        return user.get().email().toString();
    }

    public Customer getCustomer(String email) {
        return clientRepository.findByEmailOnly(new Email(email));
    }


}
