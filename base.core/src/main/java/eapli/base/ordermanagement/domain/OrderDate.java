package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.Calendar;
import java.util.Objects;

@Embeddable
public class OrderDate implements ValueObject {

    private final Calendar date;

    protected OrderDate(final Calendar calendar) {
        this.date = calendar;
    }

    public OrderDate() {
        //For ORM Purposes only
        this.date = null;
    }

    public static OrderDate valueOf(final Calendar calendar) {
        return new OrderDate(calendar);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDate orderDate = (OrderDate) o;
        return Objects.equals(date, orderDate.date);
    }

    @Override
    public String toString() {
        assert date != null;
        return "" + date.getTime();
    }
}
