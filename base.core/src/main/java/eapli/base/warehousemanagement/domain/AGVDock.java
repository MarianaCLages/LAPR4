package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;

@Entity
public class AGVDock implements DomainEntity<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    private int dockId;
    @Embedded
    private Location begin;
    @Embedded
    private Location end;
    @Embedded
    private Location depth;
    private Accessibility accessibility;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    protected Warehouse getWarehouse() {
        return warehouse;
    }

    protected void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }


    protected AGVDock() {
        // for ORM
    }

    public AGVDock(int dockId, Location begin, Location end, Location depth, Accessibility accessibility) {
        this.dockId = dockId;
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
}
