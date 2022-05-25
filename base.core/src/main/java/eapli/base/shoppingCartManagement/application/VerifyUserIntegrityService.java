package eapli.base.shoppingCartManagement.application;

import eapli.base.clientusermanagement.domain.ClientUser;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.Email;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.shoppingCartManagement.repositories.ShoppingCartRepository;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.Optional;

@ApplicationService
public class VerifyUserIntegrityService {

    private final ClientRepository clientRepository = PersistenceContext.repositories().client();
    private final ShoppingCartRepository shoppingCartRepository = PersistenceContext.repositories().carts();

    public void verifyUserByUserEmail(String userEmail) {

        //TODO verify if there is a customer with this email
        boolean value = shoppingCartRepository.verifyIfACustomerAlreadyHasAShoppingCart(userEmail);

        if(!value){
            Customer customerWithoutShoppingCart = clientRepository.findByEmail(Email.valueOf(userEmail));



        }

    }


}
