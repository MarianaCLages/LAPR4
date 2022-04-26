package eapli.base.ordermanagement.dto;

import eapli.base.ordermanagement.domain.*;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.general.domain.model.Money;
import eapli.framework.representations.dto.DTO;

import java.util.Calendar;

@DTO
public class OrderDto {

    private Money price;
    private Calendar calendar;
    private OrderState state;
    private double weight;
    private PaymentMethod paymentMethod;
    private ShippingPrice shippingPrice;
    private ShippingType shippingType;
    private ProductDTO productDto;
    private int quantity;
    private Money productPrice;

    public OrderDto(final Money price, final Calendar calendar, final OrderState state, final double weight, final PaymentMethod paymentMethod, final ShippingPrice shippingPrice, final ShippingType shippingType, final ProductDTO productDto, final int quantity, final Money productPrice) {
        this.price = price;
        this.calendar = calendar;
        this.state = state;
        this.weight = weight;
        this.paymentMethod = paymentMethod;
        this.shippingPrice = shippingPrice;
        this.shippingType = shippingType;
        this.productDto = productDto;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

}
