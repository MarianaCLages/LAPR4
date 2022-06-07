package eapli.base.customermanagement.application;

import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Optional;

public class VerifyCustomerService {

    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();


    public String getCustomerEmail() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.CLIENT_USER, BaseRoles.POWER_USER);
        Optional<SystemUser> user = authorizationService.session().filter(userSession -> userSession.authenticatedUser().identity().equals(userSession.authenticatedUser().identity())).map(UserSession::authenticatedUser);
        return user.get().email().toString();
    }


}
