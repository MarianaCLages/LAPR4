package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.validations.Preconditions;

import java.util.ArrayList;
import java.util.List;

public class WarehouseBuilder implements DomainFactory<Warehouse> {
    private final List<Aisle> aisles = new ArrayList<>();
    private final List<AGVDock> agvDocks = new ArrayList<>();
    private int length;
    private int width;
    private int square;

    private String unit;
    private WarehouseName name;

    public WarehouseBuilder withName(WarehouseName name) {
        this.name = name;
        return this;
    }

    public WarehouseBuilder withLength(int length) {
        this.length = length;
        return this;
    }

    public WarehouseBuilder withWidth(int width) {
        this.width = width;
        return this;
    }

    public WarehouseBuilder withUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public WarehouseBuilder withSquare(int square) {
        this.square = square;
        return this;
    }

    public WarehouseBuilder withName(String name) {
        this.name = new WarehouseName(Designation.valueOf(name));
        return this;
    }

    public WarehouseBuilder addAgvDock(String dockId, Location begin, Location end, Location depth, Accessibility accessibility) {
        AGVDock a = new AGVDock(dockId, begin, end, depth, accessibility);
        agvDocks.add(a);

        return this;

    }

    public WarehouseBuilder addAisle(int aisleId, Location begin, Location end, Location depth, Accessibility accessibility) {
        Aisle a = new Aisle(aisleId, begin, end, depth, accessibility);
        aisles.add(a);
        return this;
    }

    public WarehouseBuilder addRow(int aisleId, int rowId, Location begin, Location end, int numberOfShelfs) {

        Preconditions.ensure(aisles.stream().anyMatch(aisle -> {
            return aisle.identification() == aisleId;
        }), "Aisle with id " + aisleId + " does not exist");

        Aisle a = aisles.stream().filter(aisle -> aisle.identification() == aisleId).findFirst().get();
        a.addRow(rowId, begin, end, numberOfShelfs);
        return this;
    }

    @Override
    public Warehouse build() {

        return new Warehouse(name, length, width, square, unit, aisles, agvDocks);
    }

}
