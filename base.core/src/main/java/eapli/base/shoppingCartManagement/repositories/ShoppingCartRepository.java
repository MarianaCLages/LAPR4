package eapli.base.shoppingCartManagement.repositories;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.Email;
import eapli.base.customermanagement.domain.Name;
import eapli.base.shoppingCartManagement.domain.ShoppingCart;
import eapli.framework.domain.repositories.DomainRepository;

public interface ShoppingCartRepository extends DomainRepository<Long, ShoppingCart> {

    ShoppingCart findShoppingCartByCustomer(Customer customer, Name customerName);

    ShoppingCart findShoppingCartByCustomerID(long id);

    boolean verifyIfACustomerAlreadyHasAShoppingCart(Email customerEmail, Name customerName);

}
