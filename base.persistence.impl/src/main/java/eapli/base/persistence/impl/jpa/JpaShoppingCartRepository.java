package eapli.base.persistence.impl.jpa;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.productmanagement.domain.Product;
import eapli.base.shoppingCartManagement.domain.ShoppingCart;
import eapli.base.shoppingCartManagement.repositories.ShoppingCartRepository;

import javax.persistence.TypedQuery;

public class JpaShoppingCartRepository extends BasepaRepositoryBase<ShoppingCart, Long, Long> implements ShoppingCartRepository {

    public JpaShoppingCartRepository() {
        super("id");
    }

    @Override
    public ShoppingCart findShoppingCartByCustomerID(long id) {
        final TypedQuery<ShoppingCart> q = createQuery("SELECT e FROM ShoppingCart e WHERE  e.shoppingCartId = :m",
                ShoppingCart.class);

        q.setParameter("m", id);
        return q.getSingleResult();
    }

    @Override
    public void updateShoppingCartWhenAddingAProduct(ShoppingCart shoppingCart, Product product) {

    }

    @Override
    public boolean verifyIfACustomerAlreadyHasAShoppingCart(Customer customer) {
        return false;
    }
}
