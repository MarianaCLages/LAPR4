package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Money;
import eapli.framework.validations.Preconditions;

import java.util.Objects;

public class ShippingPrice implements ValueObject {

    private final Money price;

    protected ShippingPrice(final Money price) {
        Preconditions.noneNull(price, "Please enter a valid number!");

        if (price.isNegative())
            throw new IllegalArgumentException("Please enter a positive number! (There cannot exist negative prices!) ");

        this.price = price;

    }

    public ShippingPrice() {
        //For ORM purposes only
        this.price = null;
    }

    public static ShippingPrice valueOf(final Money price) {
        return new ShippingPrice(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingPrice that = (ShippingPrice) o;
        return Objects.equals(price, that.price);
    }

}