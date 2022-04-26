/*package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;


public class Order implements AggregateRoot<Long>, DTOable<OrderDTO>, Representationable {

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
    private State state;

    @Column(nullable = false)
    private Weight weight;

    @Column(nullable = false)
    private Payment payment;

    @Column(nullable = false)
    private Shipping shipping;

    @Column(nullable = false)
    private OrderLine orderLine;

    public Order(final OrderPrice price,final OrderDate date,final State state, final Weight weight,final Payment payment,final Shipping shipping, final Orderline orderline) {

        Preconditions.noneNull(price,date,state,weight,payment,shipping,orderline);

        this.price = price;
        this.date = date;
        this.state = state;
        this.weight = weight;
        this.payment = payment;
        this.shipping = shipping;
        this.orderLine = orderline;

    }

    protected Order() {
        // for ORM only.
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Order)) {
            return false;
        }

        final Order that = (Order) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && code.equals(that.code)
                && description.equals(that.description);
    }

    @Override
    public Long identity() {
        return this.orderId;
    }

    @Override
    public <R> R buildRepresentation(RepresentationBuilder<R> builder) {
        builder.startObject("Category").withProperty("description", description).withProperty("code", String.valueOf(code));

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

    @Override
    public OrderDto toDTO() {
        return new OrderDto(price, date,state,weight,payment,shipping,orderLine);
    }


}*/