package eapli.base.ordermanagement.domain;

import eapli.base.customermanagement.domain.Customer;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.List;

@Entity
public class ClientOrder implements AggregateRoot<Long>,Representationable {

    private static final long serialVersionUID = 702121L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Version
    private Long version;

    @Column(nullable = false)
    private OrderPrice price;

    @Column(nullable = false)
    private OrderDate date;

    @Column(nullable = false)
    private OrderState state;

    @Column(nullable = false)
    private Weight weight;

    @Column(nullable = false)
    private Payment payment;

    @Column(nullable = false)
    private Shipping shipping;

    @Column(nullable = false)

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLine> orderLine;


    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;


    public ClientOrder(final Customer customer, final OrderPrice price, final OrderDate date, final OrderState state, final Weight weight, final Payment payment, final Shipping shipping, final List<OrderLine> orderline) {

        Preconditions.noneNull(customer,price, date, state, weight, payment, shipping, orderline);

        this.price = price;
        this.date = date;
        this.state = state;
        this.weight = weight;
        this.payment = payment;
        this.shipping = shipping;
        this.orderLine = orderline;
        this.customer = customer;

    }

    protected ClientOrder() {
        // for ORM only.
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof ClientOrder)) {
            return false;
        }

        final ClientOrder that = (ClientOrder) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && customer.equals(that.customer) && price.equals(that.price)
                && date.equals(that.date) && state.equals(that.state) && weight.equals(that.weight) && date.equals(that.date) && date.equals(that.date) && date.equals(that.date) && date.equals(that.date);
    }

    @Override
    public Long identity() {
        return this.orderId;
    }

    @Override
    public <R> R buildRepresentation(RepresentationBuilder<R> builder) {
        builder.startObject("Order").withProperty("customer",String.valueOf(customer)).withProperty("price", String.valueOf(price)).withProperty("date", String.valueOf(date)).withProperty("state", String.valueOf(state)).withProperty("weight", String.valueOf(weight)).withProperty("payment", String.valueOf(payment)).withProperty("shipping", String.valueOf(shipping)).withProperty("orderline", String.valueOf(orderLine));

        return builder.build();
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }


    private void changePrice(final OrderPrice orderPrice){
        if(orderPrice == null) {
            throw new IllegalArgumentException();
        }
        this.price = orderPrice;
    }

    private void changeDate(final OrderDate date){
        if(date == null) {
            throw new IllegalArgumentException();
        }
        this.date = date;
    }

    private void changeState(final OrderState state){
        if(state == null) {
            throw new IllegalArgumentException();
        }
        this.state = state;
    }

    private void changeWeight(final Weight weight){
        if(date == null) {
            throw new IllegalArgumentException();
        }
        this.weight = weight;
    }

    private void changeShipping(final Shipping shipping){
        if(shipping == null) {
            throw new IllegalArgumentException();
        }
        this.shipping = shipping;
    }

    private void changeOrderLine(final List<OrderLine> orderLineList){
        if(orderLine == null) {
            throw new IllegalArgumentException();
        }
        this.orderLine = orderLineList;
    }

    public void update(final OrderPrice price, final OrderState state, final OrderDate date, final Weight weight, final Shipping shipping, final List<OrderLine> orderLine) {
        Preconditions.noneNull(price,shipping,state,date,weight,orderLine);

        changeDate(date);
        changeOrderLine(orderLine);
        changeShipping(shipping);
        changePrice(price);
        changeState(state);
        changeWeight(weight);
    }

}