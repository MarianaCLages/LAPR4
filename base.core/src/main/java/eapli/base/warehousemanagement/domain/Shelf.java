package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;

@Entity
public class Shelf implements DomainEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer shelfId;

/*    @ManyToOne
    @JoinColumn(name = "row_id")
    private Row row;*/

    protected Shelf() {
        //for ORM
    }

    private Availability availability;

    public Shelf(int aisleId, int shelfId, int rowID) {
        identifier = new ShelfIdentifier(aisleId, shelfId, rowID);
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
}
