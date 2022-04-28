package eapli.base.ordermanagement.domain;


import eapli.framework.domain.model.ValueObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class OrderLine implements ValueObject, Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantity;
    private String price;
    private Long productId;

    protected OrderLine() {
    }


    public OrderLine(Long productId, int quantity, String price) {

        this.productId = productId;
        this.quantity = quantity;
        this.price = price;

    }

    public static OrderLine valueof(final Long productId, final int quantity, final String price) {
        return new OrderLine(productId, quantity, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return quantity == orderLine.quantity && Objects.equals(productId, orderLine.productId) && Objects.equals(price, orderLine.price);
    }

    public String price() {
        return this.price;
    }
}
