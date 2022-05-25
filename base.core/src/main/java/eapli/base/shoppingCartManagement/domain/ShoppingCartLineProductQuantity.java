package eapli.base.shoppingCartManagement.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ShoppingCartLineProductQuantity {

    private static final long serialVersionUID = 812133L;

    private int quantity;

    public ShoppingCartLineProductQuantity(final int quantity) {
        this.quantity = quantity;
    }

    protected ShoppingCartLineProductQuantity() {
    }

    public static ShoppingCartLineProductQuantity valueOf(final int quantity) {
        return new ShoppingCartLineProductQuantity(quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartLineProductQuantity that = (ShoppingCartLineProductQuantity) o;
        return quantity == that.quantity;
    }

}