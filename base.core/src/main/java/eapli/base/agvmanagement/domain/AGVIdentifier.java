package eapli.base.agvmanagement.domain;


import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class AGVIdentifier implements ValueObject, Comparable<AGVIdentifier> {

    private String id;

    private AGVIdentifier(String id) {
        Preconditions.nonNull(id, "AGV identifier must exist");
        Preconditions.nonEmpty(id, "AGV identifier must be non-empty");
        Preconditions.ensure(id.length() == 8, "AGV identifier must be 8 characters long");
        this.id = id;
    }

    protected AGVIdentifier() {

    }

    public String id() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AGVIdentifier that = (AGVIdentifier) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(AGVIdentifier o) {
        return id.compareTo(o.id);
    }

    public static AGVIdentifier valueOf(String id) {
        return new AGVIdentifier(id);
    }

    public String toString() {
        return this.id;
    }
}
