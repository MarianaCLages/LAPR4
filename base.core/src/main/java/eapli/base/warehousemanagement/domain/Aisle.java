package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Aisle implements DomainEntity<Integer> {

    private static final long serialVersionUID = 1L;
    // id is the primary key for the database, Aisle is the unique identifier in business logic
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(unique = true)
    private Integer aisleId;

    @Version
    private Long version;

    @Embedded
    private Location begin;
    @Embedded
    private Location end;
    @Embedded
    private Location depth;

    private Accessibility accessibility;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rowIdentifier")
    private List<Row> rows;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Aisle(Integer aisleId, Location begin, Location end, Location depth, Accessibility accessibility) {
        this.aisleId = aisleId;
        this.begin = begin;
        this.end = end;
        this.depth = depth;
        this.accessibility = accessibility;
        this.rows = new ArrayList<>();

    }

    protected List<Row> rows() {
        return rows;
    }

    public Aisle() {
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
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Integer identity() {
        return this.aisleId;
    }

    protected void addRow(int rowId, Location begin, Location end, int numberOfShelfs) {
        Row row = new Row(rowId, begin, end, numberOfShelfs, identity());
        rows.add(row);
    }
}
