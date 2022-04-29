package eapli.base.binmanagement.domain;

import eapli.base.binmanagement.dto.BinDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class Bin implements AggregateRoot<Long>, DTOable<BinDTO>, Representationable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long binId;

    @Column(nullable = false)
    private BinLocation binLocation;

    @JoinColumn(nullable = false, name = "product_id")
    private Long productId;

    protected Bin() {
        // For ORM only
    }

    public Bin(final BinLocation binLocation, final Long productId) {
        Preconditions.noneNull(binLocation, productId);

        this.binLocation = binLocation;
        this.productId = productId;
    }

    @Override
    public Long identity() {
        return this.binId;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Bin)) {
            return false;
        }

        final Bin that = (Bin) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity())
                && binLocation.equals(that.binLocation)
                && productId.equals(that.productId);
    }

    @Override
    public <R> R buildRepresentation(RepresentationBuilder<R> builder) {
        builder.startObject("Bin").withProperty("bin location", String.valueOf(binLocation))
                .withProperty("product ID", String.valueOf(productId));

        return builder.build();
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public BinDTO toDTO() {
        return new BinDTO(binLocation.toString(), productId.toString());
    }

    private void changeBinLocation(final BinLocation binLocation) {
        if (binLocation == null) {
            throw new IllegalArgumentException();
        }
        this.binLocation = binLocation;
    }

    private void changeProduct(final Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException();
        }
        this.productId = productId;
    }

    public void update(final BinLocation binLocation, final Long productId) {
        Preconditions.noneNull(binLocation, productId);

        changeBinLocation(binLocation);
        changeProduct(productId);
    }
}
