package eapli.base.ordermanagement.dto;

import eapli.framework.representations.dto.DTO;

import java.io.Serializable;

@DTO
public class OrderDto implements Serializable {

    private String price;
    private String calendar;
    private String state;
    private String weight;
    private String paymentMethod;

    public OrderDto(final String price, final String calendar, final String state, final String weight, final String paymentMethod) {
        this.price = price;
        this.calendar = calendar;
        this.state = state;
        this.weight = weight;
        this.paymentMethod = paymentMethod;

    }

    @Override
    public String toString() {
        return "Order -> [" +
                "Price : " + price + '\n' +
                "Date : " + calendar + '\n' +
                "State : " + state + '\n' +
                "PaymentMethod : " + paymentMethod;
    }
}
