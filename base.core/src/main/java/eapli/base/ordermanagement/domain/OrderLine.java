package eapli.base.ordermanagement.domain;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class OrderLine implements ValueObject, Serializable {


    private int quantity;
    private String price;
    private Long productId;
    @Id
    private Long id;

    protected OrderLine() {

    }


    public OrderLine(Long id,Long productId, int quantity, String price) {

        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;

    }

    public static OrderLine valueof(final Long id,final Long productId, final int quantity, final String price) {
        return new OrderLine(id,productId, quantity, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return quantity == orderLine.quantity && Objects.equals(productId, orderLine.productId) && Objects.equals(price, orderLine.price);
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
