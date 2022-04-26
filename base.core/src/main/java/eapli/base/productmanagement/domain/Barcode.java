package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Barcode implements ValueObject, Serializable {

    private final Long barcode;

    protected Barcode(final Long barcode) {
        Preconditions.nonNegative(barcode, "Barcode should neither be null nor empty");
        this.barcode = barcode;
    }

    public Barcode() {
        //For ORM purposes only
        this.barcode = null;
    }

    public static Barcode valueOf(final Long barcode) {
        return new Barcode(barcode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barcode that = (Barcode) o;
        return this.barcode.equals(that.barcode);
    }
}
