package eapli.base.agvmanagement;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class AGVAutonomy implements ValueObject, Comparable<AGVAutonomy> {

    private static final long serialVersionUID = 1L;

    private long autonomy;

    private AGVAutonomy(long autonomy) {
        Preconditions.ensure(autonomy > 0, "Autonomy must be greater than zero");
        this.autonomy = autonomy;
    }

    protected AGVAutonomy() {
        // for ORM
    }

    public double autonomyInHours() {
        return (double) this.autonomy / 60;
    }

    public long autonomyInMinutes() {
        return this.autonomy;
    }

    public long autonomyInSeconds() {
        return this.autonomy * 60;
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
        final AGVAutonomy other = (AGVAutonomy) obj;
        return this.autonomy == other.autonomy;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.autonomy ^ (this.autonomy >>> 32));
        return result;
    }

    @Override
    public int compareTo(AGVAutonomy other) {
        return Long.compare(this.autonomy, other.autonomy);
    }

    public static AGVAutonomy valueOf(long autonomy) {
        return new AGVAutonomy(autonomy);
    }

    public static AGVAutonomy fromHours(double autonomy) {
        return new AGVAutonomy((long) (autonomy * 60));
    }
}
