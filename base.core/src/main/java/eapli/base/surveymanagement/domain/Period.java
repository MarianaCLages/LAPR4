package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Period implements ValueObject, Serializable {

    private final int period;

    protected Period(int period) {
        Preconditions.nonNegative(period, "Invalid period of time! It can't be negative.");
        this.period = period;
    }

    public Period() {
        // For ORM purposes only
        this.period = 0;
    }

    public static Period valueOf(final int period) {
        return new Period(period);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Period that = (Period) obj;
        return Objects.equals(this.period, that.period);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
