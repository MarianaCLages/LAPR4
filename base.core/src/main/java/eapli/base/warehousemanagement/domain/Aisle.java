package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "aisle")
public class Aisle implements DomainEntity<Integer> {

    private static final long serialVersionUID = 1L;
    // id is the primary key for the database, Aisle is the unique identifier in business logic
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer aisleId;

    @Column(unique = true)
    private Integer aisleIdentifier;

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
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lsquare", column = @Column(name = "depth_lsquare")),
            @AttributeOverride(name = "wsquare", column = @Column(name = "depth_wsquare"))
    })
    private Location depth;

    private Accessibility accessibility;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Row> rows;

    /* @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "warehouse_id")
     private Warehouse warehouse;
 */
    public Aisle(Integer aisleId, Location begin, Location end, Location depth, Accessibility accessibility) {
        this.aisleIdentifier = aisleId;
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
        //For ORM
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
        Row row = new Row(rowId, begin, end, numberOfShelfs, identification());
        rows.add(row);
    }

    public int identification() {
        return this.aisleIdentifier;
    }
}
