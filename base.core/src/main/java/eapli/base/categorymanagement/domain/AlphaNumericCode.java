package eapli.base.categorymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AlphaNumericCode implements ValueObject, Serializable {

    private final String code;

    protected AlphaNumericCode(final String code) {
        Preconditions.nonEmpty(code, "AlphaNumeric code should neither be null nor empty");
        this.code = code;
    }

    public AlphaNumericCode() {
        //For ORM purposes only
        this.code = null;
    }

    public static AlphaNumericCode valueOf(final String code) {
        return new AlphaNumericCode(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlphaNumericCode that = (AlphaNumericCode) o;
        return this.code.equals(that.code);
    }

}
