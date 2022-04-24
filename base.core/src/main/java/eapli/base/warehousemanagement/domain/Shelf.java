package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;

@Entity
public class Shelf implements DomainEntity<ShelfIdentifier> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    protected Shelf() {
        //for ORM
    }

    public Shelf(int aisleId, int shelfId, int rowID) {
        identifier = new ShelfIdentifier(aisleId, shelfId, rowID);
    }

    @Column(unique = true)
    @Embedded
    private ShelfIdentifier identifier;

    @ManyToOne
    @JoinColumn(name = "row_id")
    private Row row;


    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public ShelfIdentifier identity() {
        return identifier;
    }
}
