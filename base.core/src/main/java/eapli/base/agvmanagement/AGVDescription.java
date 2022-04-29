package eapli.base.agvmanagement;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class AGVDescription implements ValueObject, Comparable<AGVDescription> {

    private String description;

    private AGVDescription(String description) {
        Preconditions.nonEmpty(description, "description cannot be null");
        Preconditions.ensure(description.length() <= 30, "description cannot be longer than 30 characters");
        this.description = description;
    }

    protected AGVDescription() {
        // for ORM
    }

    public String description() {
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AGVDescription)) {
            return false;
        }

        final AGVDescription other = (AGVDescription) o;
        return this.description.equals(other.description);
    }

    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public int compareTo(AGVDescription other) {
        return this.description.compareTo(other.description);
    }

    public static AGVDescription valueOf(String description) {
        return new AGVDescription(description);
    }
}
