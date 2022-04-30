package eapli.base.agvmanagement.domain;

import eapli.base.warehousemanagement.domain.AGVDock;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class AGV implements AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long agvId;

    @Embedded
    private AGVIdentifier identifier;
    @Embedded
    private AGVAutonomy autonomy;
    @Embedded
    private AGVDescription shortDescription;
    @Embedded
    private AGVModel model;
    private AGVStatus status;
    @OneToOne
    private AGVDock dock;


    public AGV(String identifier, int autonomy, String shortDescription, String model, AGVStatus status, AGVDock dock) {
        this.identifier = AGVIdentifier.valueOf(identifier);
        this.autonomy = AGVAutonomy.valueOf(autonomy);
        this.shortDescription = AGVDescription.valueOf(shortDescription);
        this.model = AGVModel.valueOf(model);
        this.status = status;
        this.dock = dock;
    }


    protected AGV() {
        // for ORM only
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return this.agvId;
    }

    public AGVIdentifier identifier() {
        return this.identifier;
    }

    public void changeAutonomy(final AGVAutonomy autonomy){
        if (autonomy == null) {
            throw new IllegalArgumentException();
        }
        this.autonomy = autonomy;
    }


}
