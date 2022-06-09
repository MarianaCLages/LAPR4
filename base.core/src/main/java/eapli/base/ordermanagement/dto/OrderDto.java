package eapli.base.ordermanagement.dto;

import eapli.framework.representations.dto.DTO;

import java.io.Serializable;

@DTO
public class OrderDto implements Serializable {

    private long id;
    private String price;
    private String calendar;
    private String state;
    private double weight;
    private String paymentMethod;

    public OrderDto(final String price, final String calendar, final String state, final double weight, final String paymentMethod, long id) {
        this.price = price;
        this.calendar = calendar;
        this.state = state;
        this.weight = weight;
        this.paymentMethod = paymentMethod;
        this.id = id;

    }

    @Override
    public String toString() {
        return "Order -> \n" +
                "Price : " + price + '\n' +
                "Date : " + calendar + '\n' +
                "State : " + state + '\n' +
                "Weight : " + weight + '\n' +
                "PaymentMethod : " + paymentMethod;
    }

    public long getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public String getState() {
        return state;
    }
}
