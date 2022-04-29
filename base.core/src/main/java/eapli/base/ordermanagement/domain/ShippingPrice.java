package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Money;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class ShippingPrice implements ValueObject {

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "ShippingPrice")),
            @AttributeOverride(name = "currency", column = @Column(name = "ShippingCurrency"))
    })
    private final Money shippingPrice;

    protected ShippingPrice(final Money price) {
        Preconditions.noneNull(price, "Please enter a valid number!");

        if (price.isNegative())
            throw new IllegalArgumentException("Please enter a positive number! (There cannot exist negative prices!) ");

        this.shippingPrice = price;

    }

    public ShippingPrice() {
        //For ORM purposes only
        this.shippingPrice = null;
    }

    public static ShippingPrice valueOf(final Money price) {
        return new ShippingPrice(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingPrice that = (ShippingPrice) o;
        return Objects.equals(shippingPrice, that.shippingPrice);
    }

}