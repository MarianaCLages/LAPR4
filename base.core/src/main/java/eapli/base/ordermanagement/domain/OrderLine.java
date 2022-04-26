/*package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Money;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderLine implements ValueObject, Serializable {

    private final ProductDto productDto;
    private final int quantity;
    private final Money price;

    protected OrderLine(final ProductDto productDto, final int quantity, final Money price){
        Preconditions.nonNull(productDto, "Product shouldn't be null!");
        Preconditions.nonNull(quantity, "Quantity neither be null nor empty!");
        Preconditions.nonNull(price, "Money neither be null nor empty!");

        this.productDto = productDto;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderLine(){
        //For ORM purposes only
        this.productDto = null;
        this.quantity = null;
        this.price = null;
    }

    public static OrderLine valueof(final ProductDto productDto, final int quantity, final Money price) {
        return new OrderLine(productDto,quantity,price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return quantity == orderLine.quantity && Objects.equals(productDto, orderLine.productDto) && Objects.equals(price, orderLine.price);
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "productDto=" + productDto +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

}*/
