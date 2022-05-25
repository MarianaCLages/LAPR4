package eapli.base.shoppingCartManagement.repositories;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.productmanagement.domain.Product;
import eapli.base.shoppingCartManagement.domain.ShoppingCart;
import eapli.framework.domain.repositories.DomainRepository;

public interface ShoppingCartRepository extends DomainRepository<Long, ShoppingCart> {

    ShoppingCart findShoppingCartByCustomerID(long id);

    void updateShoppingCartWhenAddingAProduct(ShoppingCart shoppingCart, Product product);

    boolean verifyIfACustomerAlreadyHasAShoppingCart(Customer customer);

}
