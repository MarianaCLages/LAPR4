package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;

@Entity
public class Shelf implements DomainEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer shelfId;

    @ManyToOne
    @JoinColumn(name = "row_id")
    private Row row;

    protected Shelf() {
        //for ORM
    }

    public Shelf(int aisleId, int shelfId, int rowID) {
        identifier = new ShelfIdentifier(aisleId, shelfId, rowID);
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
}
