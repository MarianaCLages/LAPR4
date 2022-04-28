package eapli.base.warehousemanagement.domain;

import eapli.base.warehousemanagement.DTO.ShelfDTO;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import eapli.framework.representations.dto.DTOable;

import javax.persistence.*;

@Entity
public class Shelf implements DomainEntity<Integer>, DTOable<ShelfDTO>, Representationable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer shelfId;

    protected Shelf() {
        //for ORM
    }

    private Availability availability;

    public Shelf(int aisleId, int shelfId, int rowID) {
        this.identifier = new ShelfIdentifier(aisleId, shelfId, rowID);
        this.availability = Availability.Available;
    }

    @Column(unique = true)
    @Embedded
    private ShelfIdentifier identifier;


    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Integer identity() {
        return this.shelfId;
    }

    public void makeUnavailable() {
        this.availability = Availability.Unavailable;
    }

    public void makeAvailable() {
        this.availability = Availability.Available;
    }

    public boolean isAvailable() {
        return this.availability == Availability.Available;
    }

    @Override
    public <R> R buildRepresentation(RepresentationBuilder<R> builder) {
        return builder.withProperty("aisle", this.identifier.aisleIdentifier())
                .withProperty("row", this.identifier.rowIdentifier())
                .withProperty("shelf", this.identifier.shelfIdentifier())
                .withProperty("availability", String.valueOf(this.availability))
                .build();
    }

    @Override
    public ShelfDTO toDTO() {
        return new ShelfDTO(this.identifier.aisleIdentifier(), this.identifier.rowIdentifier(), this.identifier.shelfIdentifier());
    }
}
