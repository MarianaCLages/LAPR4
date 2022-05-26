package eapli.base.shoppingCartManagement.application;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.Email;
import eapli.base.customermanagement.domain.Name;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.shoppingCartManagement.domain.ShoppingCart;
import eapli.base.shoppingCartManagement.domain.ShoppingCartLine;
import eapli.base.shoppingCartManagement.domain.ShoppingCartLineProductQuantity;
import eapli.base.shoppingCartManagement.repositories.ShoppingCartRepository;
import eapli.framework.application.ApplicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationService
public class VerifyUserIntegrityService {

    private final ClientRepository clientRepository = PersistenceContext.repositories().client();
    private final ShoppingCartRepository shoppingCartRepository = PersistenceContext.repositories().carts();

    public boolean verifyUserByUserEmail(String userEmail, String firstName, String lastName) {
        //TODO verify if there is a customer with this email
        return shoppingCartRepository.verifyIfACustomerAlreadyHasAShoppingCart(Email.valueOf(userEmail), Name.valueOf(firstName + lastName));
    }

    public void createShoppingCartForGivenCustomer(String userEmail, String firstName, String lastName) {
        List<ShoppingCartLine> shoppingCartLineList = new ArrayList<>();

        try {
            shoppingCartRepository.save(new ShoppingCart(clientRepository.findByEmail(Email.valueOf(userEmail), Name.valueOf(firstName + lastName)), shoppingCartLineList));
        } catch (Exception e) {
            shoppingCartRepository.save(new ShoppingCart(clientRepository.findByEmailOnly(Email.valueOf(userEmail)), shoppingCartLineList));
        }

    }

    public boolean addProductsToCart(Map<Product, Integer> products, String userEmail, String firstName, String lastName) {

        Customer customer;

        try {
            customer = clientRepository.findByEmail(Email.valueOf(userEmail), Name.valueOf(firstName + lastName));
        } catch (Exception e) {
            customer = clientRepository.findByEmailOnly(Email.valueOf(userEmail));
        }

        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByCustomer(customer, Name.valueOf(firstName + lastName));


        for (Product p : products.keySet()) {
            shoppingCart.updateShoppingCartLine(ShoppingCartLine.valueOf(p, ShoppingCartLineProductQuantity.valueOf(products.get(p))));
        }

        shoppingCartRepository.save(shoppingCart);

        return true;

    }

}