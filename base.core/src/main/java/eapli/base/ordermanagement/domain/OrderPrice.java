package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Money;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class OrderPrice implements ValueObject {


    private final Money price;

    protected OrderPrice(final Money price) {
        Preconditions.noneNull(price, "Please enter a valid amount!");

        this.price = price;
    }

    public OrderPrice() {
        //For ORM purposes only
        this.price = null;
    }

    public static OrderPrice valueOf(final Money price) {
        return new OrderPrice(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPrice that = (OrderPrice) o;
        return Objects.equals(price, that.price);
    }

    @Override
    public String toString() {
        return "OrderPrice{" +
                "price=" + price +
                '}';
    }
}
