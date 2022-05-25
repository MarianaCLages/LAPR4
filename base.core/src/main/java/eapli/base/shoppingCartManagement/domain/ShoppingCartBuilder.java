package eapli.base.shoppingCartManagement.domain;

import eapli.base.customermanagement.domain.Customer;
import eapli.framework.domain.model.DomainFactory;

import java.util.List;

public class ShoppingCartBuilder implements DomainFactory<ShoppingCart> {

    private ShoppingCart shoppingCart;

    private List<ShoppingCartLine> shoppingCartLine;
    private Customer customer;


    public ShoppingCartBuilder addCustomer(final Customer customer) {
        this.customer = customer;
        return this;
    }

    public ShoppingCartBuilder addCartLines(final List<ShoppingCartLine> shoppingCartLine) {
        this.shoppingCartLine = shoppingCartLine;
        return this;
    }

    @Override
    public ShoppingCart build() {
        final ShoppingCart cart = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do not change
        // the previously built order.
        shoppingCart = null;
        return cart;

    }

    private ShoppingCart buildOrThrow() {
        if (shoppingCart != null) {
            return shoppingCart;
        } else if (shoppingCartLine != null && customer != null) {
            shoppingCart = new ShoppingCart(customer, shoppingCartLine);
            return shoppingCart;
        } else {
            throw new IllegalStateException();
        }

    }
}
