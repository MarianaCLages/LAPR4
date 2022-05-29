package eapli.base.shoppingCartManagement.domain;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.shoppingCartManagement.dto.ShoppingCartDto;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShoppingCart implements AggregateRoot<Long>, Representationable, DTOable<ShoppingCartDto> {

    private static final long serialVersionUID = 702133L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shoppingCartId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ShoppingCartLine> shoppingCartLine;

    @OneToOne
    @JoinColumn(name = "customer_id", unique = true)
    private Customer customer;

    protected ShoppingCart() {
    }

    public ShoppingCart(final Customer customer, final List<ShoppingCartLine> shoppingCartLine) {
        Preconditions.nonNull(customer, "Please insert a valid customer!");
        Preconditions.nonNull(shoppingCartLine, "Please insert a valid list of shopping cart lines");

        this.customer = customer;
        this.shoppingCartLine = shoppingCartLine;
    }


    public void updateShoppingCartLine(ShoppingCartLine shoppingCartLine) {
        this.shoppingCartLine.add(shoppingCartLine);
    }


    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof ShoppingCart)) {
            return false;
        }

        final ShoppingCart that = (ShoppingCart) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && customer.equals(that.customer);

    }

    public boolean verifyShoppingCartLines() {
        return this.shoppingCartLine.isEmpty();
    }

    @Override
    public Long identity() {
        return this.shoppingCartId;
    }

    @Override
    public <R> R buildRepresentation(RepresentationBuilder<R> builder) {
        builder.startObject("ShoppingCart").withProperty("customer", String.valueOf(customer)).withProperty("shoppingCartLine", String.valueOf(shoppingCartLine));
        return builder.build();
    }

    @Override
    public ShoppingCartDto toDTO() {
        return new ShoppingCartDto(customer.name().toString(), shoppingCartLine);
    }
}
