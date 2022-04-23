package eapli.base.customermanagement.application;

import eapli.base.clientusermanagement.application.ClientUserService;
import eapli.base.usermanagement.application.AddUserController;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;
import java.util.Set;

@ApplicationService
public class CreateUserService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AddUserController theController = new AddUserController();


    public SystemUser createUser(final String username, final String password, final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn){

        return theController.addUser(username,password,firstName,lastName,email,roles,createdOn);
    }
}
