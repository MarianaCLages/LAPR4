package eapli.base.shoppingCartManagement.domain;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

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
        Preconditions.nonNull(product, "Please enter a valid product!");
        Preconditions.nonNull(shoppingCartLineProductQuantity, "Please enter a valid quantity!");

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
        return "\nProduct quantity : " + shoppingCartLineProductQuantity +
                ", Product Identification : " + product.identity();

    }

    @Override
    public int hashCode() {
        return Objects.hash(product, shoppingCartLineProductQuantity);
    }

}
