package eapli.base.ordermanagement.domain;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Entity
public class ClientOrder implements AggregateRoot<Long>, Representationable, DTOable<OrderDto> {

    private static final long serialVersionUID = 702121L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Version
    private Long version;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "OrderPrice")),
            @AttributeOverride(name = "currency", column = @Column(name = "OrderCurrency"))
    })
    private Money price;


    @Embedded
    private OrderDate date;


    private OrderState state;

    @Embedded
    private Weight weight;

    @Embedded
    private Payment payment;

    @Embedded

    private Shipping shipping;


    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLine> orderLine;


    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;


    public ClientOrder(final Customer customer, Money price, final OrderDate date, final OrderState state, final Weight weight, final Payment payment, final Shipping shipping, final List<OrderLine> orderline) {

        Preconditions.noneNull(date, state, weight, payment, shipping);

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

        return identity().equals(that.identity());
                /*&& customer.equals(that.customer)
                && date.equals(that.date) && state.equals(that.state) && weight.equals(that.weight) && date.equals(that.date) && date.equals(that.date) && date.equals(that.date) && date.equals(that.date);*/
    }



    @Override
    public Long identity() {
        return this.orderId;
    }

    public void chanceState(OrderState orderState) {
        this.state = orderState;
    }

    public OrderState state() {
        return this.state;
    }

    @Override
    public <R> R buildRepresentation(RepresentationBuilder<R> builder) {
        builder.startObject("Order").withProperty("customer", String.valueOf(customer)).withProperty("date", String.valueOf(date)).withProperty("state", String.valueOf(state)).withProperty("weight", String.valueOf(weight)).withProperty("payment", String.valueOf(payment)).withProperty("shipping", String.valueOf(shipping)).withProperty("orderline", String.valueOf(orderLine));

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


    private void changePrice(final Money orderPrice) {
        if (orderPrice == null) {
            throw new IllegalArgumentException();
        }
        this.price = orderPrice;
    }

    public OrderDate orderDate(){return this.date;}

    private void changeDate(final OrderDate date) {
        if (date == null) {
            throw new IllegalArgumentException();
        }
        this.date = date;
    }

    private void changeState(final OrderState state) {
        if (state == null) {
            throw new IllegalArgumentException();
        }
        this.state = state;
    }

    private void changeWeight(final Weight weight) {
        if (date == null) {
            throw new IllegalArgumentException();
        }
        this.weight = weight;
    }

    private void changeShipping(final Shipping shipping) {
        if (shipping == null) {
            throw new IllegalArgumentException();
        }
        this.shipping = shipping;
    }

    private void changeOrderLine(final List<OrderLine> orderLineList) {
        if (orderLine == null) {
            throw new IllegalArgumentException();
        }
        this.orderLine = orderLineList;
    }


    public void update(final Money price, final OrderState state, final OrderDate date, final Weight weight, final Shipping shipping, final List<OrderLine> orderLine) {
        Preconditions.noneNull(price, shipping, state, date, weight, orderLine);

        changeDate(date);
        changeOrderLine(orderLine);
        changeShipping(shipping);
        changePrice(price);
        changeState(state);
        changeWeight(weight);
    }

    @Override
    public OrderDto toDTO() {
        return new OrderDto(price.toString(), date.toString(), state.toString(), weight.returnValue(), payment.toString(),identity());
    }

    @Override
    public String toString() {
        return "ClientOrder{" +
                "\norderId=" + orderId +
                "\nprice=" + price +
                "\ndate=" + date +
                "\nstate=" + state +
                "\nweight=" + weight +
                "\npayment=" + payment +
                "\nshipping=" + shipping +
                "\norderLine=" + orderLine +
                "\ncustomer=" + customer +
                "}\n\n";
    }


    public Customer getCustomer() {
        return customer;
    }

    public List<OrderLine> orderLine() {
        return orderLine;
    }
}

