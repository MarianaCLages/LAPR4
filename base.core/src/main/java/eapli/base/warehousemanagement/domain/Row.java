package eapli.base.warehousemanagement.domain;


import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "WarehouseRow")
public class Row implements DomainEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rowId;

    private int aisleIdentifier;
    private int rowIdentifier;

    @Version
    private Long version;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lsquare", column = @Column(name = "begin_lsquare")),
            @AttributeOverride(name = "wsquare", column = @Column(name = "begin_wsquare"))
    })
    private Location begin;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lsquare", column = @Column(name = "end_lsquare")),
            @AttributeOverride(name = "wsquare", column = @Column(name = "end_wsquare"))
    })
    private Location end;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shelf> shelfs;


   /* @ManyToOne
    @JoinColumn(name = "aisle_id")
    private Aisle aisle;*/


    public Row(int rowIdentifier, Location begin, Location end, int numberOfShelfs, int aisleId) {
        this.rowIdentifier = rowIdentifier;
        this.aisleIdentifier = aisleId;
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
            Shelf shelf = new Shelf(aisleIdentifier, i + 1, rowIdentifier);
            this.shelfs.add(shelf);
        }
    }


    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Integer identity() {
        return this.rowId;
    }
}
