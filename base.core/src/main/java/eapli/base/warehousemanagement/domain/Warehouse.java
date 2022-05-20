package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;


import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Warehouse implements AggregateRoot<Long> {

    private static final long serialVersionUID = 6763256902584926321L;

    @Id
    private Long warehouseId;

    @Version
    private Long version;

    @Transient
    private int[][] plant;

    @Column(nullable = false)
    private WarehouseName name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Aisle> aisles;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AGVDock> agvDocks;

    private String unit;
    private int square;
    private int width;
    private int length;


    protected Warehouse() {
        // for ORM
    }

    public Warehouse(WarehouseName name, int length, int width, int square, String unit, List<Aisle> aisles, List<AGVDock> agvDocks) {
        Preconditions.ensure(agvDocks.size() > 0, "There must be at least one AGVDock");
        Preconditions.ensure(aisles.size() > 0, "There must be at least one Aisle");
        Preconditions.ensure(aisles.stream().noneMatch(aisle -> aisle.rows().isEmpty()), "Aisles must have rows");
        Preconditions.isPositive(length, "Length must be positive");
        Preconditions.isPositive(width, "Width must be positive");
        Preconditions.isPositive(square, "Square must be positive");
        Preconditions.nonEmpty(unit, "No unit was set");
        Preconditions.nonNull(unit, "Unit must be non null");
        this.warehouseId = 1L;
        this.name = name;
        this.aisles = aisles;
        this.agvDocks = agvDocks;
        this.length = length;
        this.width = width;
        this.square = square;
        this.unit = unit;

        this.plant = generatePlant();

    }

    private int[][] generatePlant() {
        int[][] plant = new int[length][width];
        //TODO: generate the plant
        return plant;
    }

    protected List<Aisle> aisles() {
        return this.aisles;
    }

    public List<AGVDock> agvDocks() {
        return this.agvDocks;
    }

    //equals and hashcode
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
        return false;
    }

    @Override
    public Long identity() {
        return warehouseId;
    }

    public String name() {
        return this.name.toString();
    }

    public Shelf assignShelf() {
        List<Shelf> shelves = this.aisles.stream().flatMap(aisle -> aisle.rows().stream()).flatMap(row -> row.shelves().stream()).collect(Collectors.toList());
        shelves.removeIf(shelf -> !shelf.isAvailable());
        if (shelves.isEmpty()) {
            throw new IllegalStateException("There are no available shelves");
        }
        shelves.get(0).makeUnavailable();
        return shelves.get(0);
    }
}
