package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ShelfIdentifier implements ValueObject, Comparable<ShelfIdentifier> {
    private int aisleIdentifier;
    private int rowId;
    private int shelfIdentifier;

    public ShelfIdentifier(int aisleIdentifier, int rowIdentifier, int shelfId) {
        this.aisleIdentifier = aisleIdentifier;
        this.rowId = rowIdentifier;
        this.shelfIdentifier = shelfId;
    }

    protected int aisleIdentifier() {
        return aisleIdentifier;
    }

    protected int rowIdentifier() {
        return rowId;
    }

    protected int shelfIdentifier() {
        return shelfIdentifier;
    }

    protected ShelfIdentifier() {
    }

    //Equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShelfIdentifier that = (ShelfIdentifier) o;

        if (!Objects.equals(aisleIdentifier, that.aisleIdentifier)) return false;
        if (!Objects.equals(rowId, that.rowId)) return false;
        return Objects.equals(shelfIdentifier, that.shelfIdentifier);

    }

    @Override
    public int hashCode() {
        HashCoder hc = new HashCoder();
        hc.with(aisleIdentifier);
        hc.with(rowId);
        hc.with(shelfIdentifier);
        return hc.code();
    }

    @Override
    public int compareTo(ShelfIdentifier o) {
        if (this.aisleIdentifier > o.aisleIdentifier) {
            return 1;
        }
        if (this.aisleIdentifier < o.aisleIdentifier) {
            return -1;
        }
        if (this.rowId > o.rowId) {
            return 1;
        }
        if (this.rowId < o.rowId) {
            return -1;
        }
        return Integer.compare(this.shelfIdentifier, o.shelfIdentifier);
    }

}
