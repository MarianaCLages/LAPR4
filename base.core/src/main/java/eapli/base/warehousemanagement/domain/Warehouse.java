package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;


import javax.persistence.*;
import java.util.List;

@Entity
public class Warehouse implements AggregateRoot<WarehouseName> {

    private static final long serialVersionUID = 6763256902584926321L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;
    @Version
    private Long version;

    @Transient
    private int[][] plant;

    @Column(nullable = false)
    private WarehouseName name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aisleId")
    private List<Aisle> aisles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shelfId")
    private List<AGVDock> agvDocks;

    private String unit;
    private int square;
    private int width;
    private int length;


    protected Warehouse() {
        // for ORM
    }

    public Warehouse(WarehouseName name, int length, int width, int square, String unit, List<Aisle> aisles, List<AGVDock> agvDocks) {
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
    public WarehouseName identity() {
        return name;
    }
}
