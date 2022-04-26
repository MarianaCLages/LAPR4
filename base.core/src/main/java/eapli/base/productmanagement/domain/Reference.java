package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Reference implements ValueObject, Serializable {

    public final String reference;

    protected Reference(final String reference) {
        Preconditions.nonEmpty(reference, "Reference should neither be null nor empty");
        this.reference = reference;
    }

    public Reference() {
        //For ORM purposes only
        this.reference = null;
    }

    public static Reference valueOf(final String reference) {
        return new Reference(reference);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reference that = (Reference) o;
        return this.reference.equals(that.reference);
    }

    @Override
    public String toString() {
        return this.reference;
    }
}
