package eapli.base.shoppingCartManagement.domain;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.ValueObject;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ShoppingCartLine implements ValueObject {

    private static final long serialVersionUID = 702143L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long shoppingCartLineId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    private ShoppingCartLineProductQuantity shoppingCartLineProductQuantity;

    protected ShoppingCartLine() {
    }

    public ShoppingCartLine(final Product product, final ShoppingCartLineProductQuantity shoppingCartLineProductQuantity) {
        this.product = product;
        this.shoppingCartLineProductQuantity = shoppingCartLineProductQuantity;
    }

    public static ShoppingCartLine valueOf(final Product product, final ShoppingCartLineProductQuantity shoppingCartLineProductQuantity) {
        return new ShoppingCartLine(product, shoppingCartLineProductQuantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartLine that = (ShoppingCartLine) o;
        return Objects.equals(product, that.product) && Objects.equals(shoppingCartLineProductQuantity, that.shoppingCartLineProductQuantity);
    }

    @Override
    public String toString() {
        return "\n\t ShoppingCartLine -> [" +
                ", ProductQuantity : " + shoppingCartLineProductQuantity +
                ", Product : " + product +
                "]";

    }

    @Override
    public int hashCode() {
        return Objects.hash(product, shoppingCartLineProductQuantity);
    }

}
