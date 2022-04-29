package eapli.base.binmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class BinLocation implements ValueObject {

    private final int aisleIdentifier;
    private final int rowIdentifier;
    private final int shelfIdentifier;

    public BinLocation(int aisleIdentifier, int rowIdentifier, int shelfIdentifier) {
        Preconditions.nonNegative(aisleIdentifier);
        Preconditions.nonNegative(rowIdentifier);
        Preconditions.nonNegative(shelfIdentifier);


        this.aisleIdentifier = aisleIdentifier;
        this.rowIdentifier = rowIdentifier;
        this.shelfIdentifier = shelfIdentifier;

    }

    protected BinLocation() {
        //for ORM purposes only
        this.aisleIdentifier = 0;
        this.rowIdentifier = 0;
        this.shelfIdentifier = 0;
    }

    public static BinLocation valueOf(int shelfId, int rowId, int aisleId) {
        return new BinLocation(shelfId, rowId, aisleId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinLocation that = (BinLocation) o;
        return this.aisleIdentifier == that.aisleIdentifier && this.rowIdentifier == that.rowIdentifier && this.shelfIdentifier == that.shelfIdentifier;
    }


}
