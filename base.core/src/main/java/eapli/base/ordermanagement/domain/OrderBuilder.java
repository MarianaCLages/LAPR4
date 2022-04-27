package eapli.base.ordermanagement.domain;

import eapli.base.customermanagement.domain.Customer;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Money;

import java.util.Calendar;
import java.util.List;

public class OrderBuilder implements DomainFactory<ClientOrder> {

    private ClientOrder theClientOrder;

    private OrderPrice price;
    private OrderDate date;
    private OrderState state;
    private Weight weight;
    private Payment payment;
    private Shipping shipping;
    private List<OrderLine> orderLine;
    private Customer customer;

    public OrderBuilder addPrice(final OrderPrice price){
        this.price = price;
        return this;
    }

    public OrderBuilder addPrice(final Money price) {
        return addPrice(OrderPrice.valueOf(price));
    }

    public OrderBuilder addDate(final OrderDate date){
        this.date = date;
        return this;
    }

    public OrderBuilder addDate(final Calendar calendar) {
        return addDate(OrderDate.valueOf(calendar));
    }

    public OrderBuilder addState(final OrderState state){
        this.state = state;
        return this;
    }

    public OrderBuilder addWeight(final Weight weight){
        this.weight = weight;
        return this;
    }

    public OrderBuilder addCustomer(final Customer customer){
        this.customer = customer;
        return this;
    }

    public OrderBuilder addWeight(final double weight) {
        return addWeight(Weight.valueOf(weight));
    }

    public OrderBuilder addPayment(final Payment payment){
        this.payment = payment;
        return this;
    }

    public OrderBuilder addShipping(final Shipping shipping){
        this.shipping = shipping;
        return  this;
    }

    public OrderBuilder addOrderLine(final List<OrderLine> orderLine){
        this.orderLine = orderLine;
        return this;
    }

    @Override
    public ClientOrder build() {
        final ClientOrder ord = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do not change
        // the previously built order.
        theClientOrder = null;
        return ord;
    }

    private ClientOrder buildOrThrow() {
        if (theClientOrder != null) {
            return theClientOrder;
        } else if (price != null && date != null && state != null && weight != null && payment != null && shipping != null && orderLine != null) {
            theClientOrder = new ClientOrder(customer,price,date,state,weight,payment,shipping,orderLine);
            return theClientOrder;
        } else {
            throw new IllegalStateException();
        }
    }

}