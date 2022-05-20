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

    private final ClientRepository clientRepository = PersistenceContext.repositories().client();


    public Customer registerCustomer(final PhoneNumber customerPhoneNumber, final BirthDate birthDate,
                                     final Name name, final Gender gender, final VAT vat, final Email email,
                                     final Address address){



        final Customer customer = new CustomerBuilder()
                .brithDate(birthDate)
                .email(email)
                .gender(gender)
                .named(name)
                .number(customerPhoneNumber)
                .vat(vat)
                .address(address)
                .build();

        return clientRepository.save(customer);

    }

}
