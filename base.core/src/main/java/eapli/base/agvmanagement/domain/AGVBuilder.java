package eapli.base.agvmanagement.domain;

import eapli.base.warehousemanagement.domain.AGVDock;
import eapli.framework.domain.model.DomainFactory;

public class AGVBuilder implements DomainFactory<AGV> {

    private AGV theAgv;

    private String identifier;
    private int autonomy;
    private String shortDescription;
    private String model;
    private AGVStatus status;
    private AGVDock dock;

    public AGVBuilder identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public AGVBuilder autonomy(int autonomy) {
        this.autonomy = autonomy;
        return this;
    }

    public AGVBuilder description(String description) {
        this.shortDescription = description;
        return this;
    }

    public AGVBuilder model(String agvModel) {
        this.model = agvModel;
        return this;
    }

    public AGVBuilder status(AGVStatus status) {
        this.status = status;
        return this;
    }

    public AGVBuilder dock(AGVDock dock) {
        this.dock = dock;
        return this;
    }




    private AGV buildOrThrow() {

        if (theAgv != null) {
            return this.theAgv;
        } else if (identifier != null && autonomy > 0 && shortDescription != null &&
                  status != null ) {
            return theAgv =  new AGV(identifier,autonomy,shortDescription,model,status,dock);
        }
        else {
            throw new IllegalStateException();
        }

    }

    @Override
    public AGV build(){

        final AGV ret = buildOrThrow();
        theAgv = null;
        return ret;
    }

}
