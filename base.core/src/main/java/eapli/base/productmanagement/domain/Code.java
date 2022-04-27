package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Code implements ValueObject, Serializable {

    private final String code;

    public Code(final String code) {
        Preconditions.nonEmpty(code, "Code should neither be null nor empty");
        this.code = code;
    }

    public Code() {
        //For ORM purposes only
        this.code = null;
    }

    public static Code valueOf(final String code) {
        return new Code(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code that = (Code) o;
        return this.code.equals(that.code);
    }

    @Override
    public String toString() {
        return this.code;
    }
}
