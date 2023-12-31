package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class Shipping implements ValueObject {
/*
    @Embedded
    private final ShippingType shippingType;
*/
    @Embedded
    private final ShippingPrice price;

    public Shipping(final ShippingType shippingType, final ShippingPrice price) {
        Preconditions.noneNull(shippingType, "Please select one valid shipping type!");
        Preconditions.noneNull(price, "Please enter a price!");

       // this.shippingType = shippingType;
        this.price = price;
    }

    public Shipping() {
        //For ORM purposes only
        //this.shippingType = null;
        this.price = null;
    }

    public static Shipping valueOf(final ShippingPrice shippingPrice, final ShippingType shippingType) {
        return new Shipping(shippingType, shippingPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipping shipping = (Shipping) o;
        return Objects.equals(price, shipping.price);
    }

}
