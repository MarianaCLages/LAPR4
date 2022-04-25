package eapli.base.customermanagement.application;

import eapli.base.customermanagement.domain.*;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

import java.util.Calendar;
import java.util.Set;

@UseCaseController
public class CreateCustomerController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CreateCustomerService createCustomerService = new CreateCustomerService();
    private final CreateUserService createUserService = new CreateUserService();
    private Customer customer = null;

    public Customer registerCustomer(final PhoneNumber customerPhoneNumber, final CustomerBirthDate customerBirthDate,
                                     final CustomerName customerName, final CustomerGender customerGender,
                                     final CustomerVAT customerVAT, final CustomerEmail customerEmail,
                                     final String username, final String password, final String firstName,
                                     final String lastName, final String email, final Set<Role> roles, final Calendar createdOn) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK);

        customer = createCustomerService.registerCustomer(customerPhoneNumber, customerBirthDate, customerName, customerGender, customerVAT, customerEmail);
        createUserService.createUser(username, password, firstName, lastName, email, roles, createdOn);

        return customer;
    }

    public boolean deleteCustomer() {
        final DeleteCustomerService deleteCustomerService = new DeleteCustomerService();
        deleteCustomerService.deleteCustomer(customer);
        return true;
    }

}


