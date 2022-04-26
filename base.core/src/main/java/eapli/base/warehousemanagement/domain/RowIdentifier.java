package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class RowIdentifier implements ValueObject, Comparable<RowIdentifier> {
    private int aisleIdentifier;
    private int rowIdentifier;

    public RowIdentifier(int aisleIdentifier, int rowIdentifier) {
        this.aisleIdentifier = aisleIdentifier;
        this.rowIdentifier = rowIdentifier;
    }

    protected int aisleIdentifier() {
        return this.aisleIdentifier;
    }

    protected int rowId() {
        return this.rowIdentifier;
    }

    protected RowIdentifier() {
    }
    //Equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RowIdentifier that = (RowIdentifier) o;

        if (!Objects.equals(aisleIdentifier, that.aisleIdentifier))
            return false;
        return Objects.equals(rowIdentifier, that.rowIdentifier);
    }

    @Override
    public int hashCode() {
        return HashCoder.hash(this);
    }

    @Override
    public int compareTo(RowIdentifier o) {
        if (this.aisleIdentifier < o.aisleIdentifier) {
            return -1;
        } else if (this.aisleIdentifier > o.aisleIdentifier) {
            return 1;
        } else {
            return Integer.compare(this.rowIdentifier, o.rowIdentifier);
        }
    }
}
