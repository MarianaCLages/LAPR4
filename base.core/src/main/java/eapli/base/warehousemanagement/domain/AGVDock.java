package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;

@Entity
public class AGVDock implements DomainEntity<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer dockId;

    @Column(unique = true)
    private String dockDesignation;

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


    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;


    protected AGVDock() {
        // for ORM
    }

    public AGVDock(String dockDesignation, Location begin, Location end, Location depth, Accessibility accessibility) {
        this.dockDesignation = dockDesignation;
        this.begin = begin;
        this.end = end;
        this.depth = depth;
        this.accessibility = accessibility;
    }

    @Override
    public boolean sameAs(Object other) {
        return this.identity().equals(((AGVDock) other).identity());
    }

    @Override
    public Integer identity() {
        return this.dockId;
    }

    public Integer identification() {
        return this.dockId;
    }
}