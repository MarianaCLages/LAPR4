package eapli.base.agvmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class AGVCapacity implements ValueObject {

    private long weight;

    protected AGVCapacity() {

    }

    public AGVCapacity(final long weight) {
        this.weight = weight;
    }

    public static AGVCapacity valueOf(final long weight) {
        return new AGVCapacity(weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AGVCapacity other = (AGVCapacity) obj;
        return this.weight == other.weight;
    }

    @Override
    public String toString() {
        return "" + weight;
    }

    public long returnValue() {
        return this.weight;
    }


}
