package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Row implements DomainEntity<RowIdentifier> {
    @Id
    private Integer id;
    @Column(unique = true)
    @Embedded
    private RowIdentifier rowIdentifier;
    @Version
    private Long version;

    @Embedded
    private Location begin;
    @Embedded
    private Location end;

    @OneToMany(mappedBy = "shelfId", cascade = CascadeType.ALL)
    private List<Shelf> shelfs;

    @ManyToOne
    @JoinColumn(name = "aisle_id", nullable = false)
    private Aisle aisle;


    public Row(int rowId, Location begin, Location end, int numberOfShelfs, int aisleId) {
        this.rowIdentifier = new RowIdentifier(aisleId, rowId);
        this.begin = begin;
        this.end = end;
        this.shelfs = new ArrayList<>();
        generateShelfs(numberOfShelfs);
    }

    protected Row() {
        // for ORM
    }

    private void generateShelfs(int numberOfShelfs) {
        for (int i = 0; i < numberOfShelfs; i++) {
            Shelf shelf = new Shelf(rowIdentifier.aisleIdentifier(), i + 1, rowIdentifier.rowId());
            this.shelfs.add(shelf);
        }
    }


    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public RowIdentifier identity() {
        return rowIdentifier;
    }
}
