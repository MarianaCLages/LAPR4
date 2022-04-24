package eapli.base.customermanagement.application;

import eapli.base.customermanagement.domain.*;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@ApplicationService
class CreateCustomerService {

    private final ClientRepository clientRepository = PersistenceContext.repositories().createClient();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public Customer registerCustomer(final PhoneNumber customerPhoneNumber, final CustomerBirthDate customerBirthDate, final CustomerName customerName, final CustomerGender customerGender, final CustomerVAT customerVAT, final CustomerEmail customerEmail){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.EMPLOYEE);

        final Customer customer = new CustomerBuilder()
                .brithDate(customerBirthDate)
                .email(customerEmail)
                .gender(customerGender)
                .named(customerName)
                .number(customerPhoneNumber)
                .vat(customerVAT)
                .build();

        return clientRepository.save(customer);

    }
}
