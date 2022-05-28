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
    private String[][] plant;

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

    public String[][] generatePlant() {
        int matrixLength = length / square;
        int matrixWidth = width / square;
        this.plant = new String[matrixLength + 1][matrixWidth + 1];

        // Initialize the plant
        for (int i = 0; i < matrixLength + 1; i++) {
            for (int j = 0; j < matrixWidth + 1; j++) {
                this.plant[i][j] = "|  |";
            }
        }

        for (Aisle a : aisles) {
            plant[(int) a.begin().lSquare()][(int) a.begin().wSquare()] = "|A" + a.identification() + "|";
            plant[(int) a.end().lSquare()][(int) a.end().wSquare()] = "|A" + a.identification() + "|";
            plant[(int) a.depth().lSquare()][(int) a.depth().wSquare()] = "|A" + a.identification() + "|";


            //fill the space beteween the begin and end/depth
            if (a.begin().wSquare() > a.depth().wSquare()) {
                for (int i = (int) a.depth().wSquare(); i <= a.begin().wSquare(); i++) {
                    for (int j = (int) a.begin().lSquare(); j < a.end().lSquare(); j++) {
                        this.plant[j][i] = "|A" + a.identification() + "|";
                    }
                }
            } else {
                for (int i = (int) a.begin().wSquare(); i <= a.depth().wSquare(); i++) {
                    for (int j = (int) a.begin().lSquare(); j <= a.end().lSquare(); j++) {
                        this.plant[j][i] = "|A" + a.identification() + "|";
                    }
                }

            }

            for (int i = (int) a.begin().wSquare(); i <= a.depth().wSquare(); i++) {
                for (int j = (int) a.begin().lSquare(); j < a.end().lSquare(); j++) {
                    this.plant[j][i] = "|A" + a.identification() + "|";
                }
            }


            for (Row r : a.rows()) {
                plant[(int) r.begin().lSquare()][(int) r.begin().wSquare()] = "|R" + r.identification() + "|";
                if (r.end().lSquare() != r.begin().lSquare()) {
                    plant[((int) r.end().lSquare())][((int) r.end().wSquare())] = "|R" + r.identification() + "|";
                }

                //fill the space between
                for (int i = ((int) r.begin().wSquare() + 1); i < r.end().wSquare(); i++) {
                    plant[(int) r.begin().lSquare()][i] = "|R" + r.identification() + "|";
                }
                for (int i = ((int) r.begin().lSquare() + 1); i < r.end().lSquare(); i++) {
                    plant[i][(int) r.begin().wSquare()] = "|R" + r.identification() + "|";
                }
            }


        }

        //add the agv docks
        for (AGVDock dock : agvDocks) {
            plant[((int) dock.begin().lSquare())][((int) dock.begin().wSquare())] = "|" + dock.dockDesignation() + "|";
            plant[((int) dock.end().lSquare())][((int) dock.end().wSquare())] = "|" + dock.dockDesignation() + "|";
            plant[((int) dock.depth().lSquare())][((int) dock.depth().wSquare())] = "|" + dock.dockDesignation() + "|";

            //fill the space between
            for (int i = ((int) dock.begin().lSquare() + 1); i < (dock.end().lSquare()); i++) {
                plant[i][((int) dock.begin().wSquare())] = "|D" + dock.dockDesignation() + "|";
            }
        }

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
