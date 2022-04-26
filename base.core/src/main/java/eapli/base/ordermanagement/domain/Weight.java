package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Weight implements ValueObject {

    private final double value;

    protected Weight(final double weight) {
        if (weight < 0) throw new IllegalArgumentException("The weight must have a positive value!");

        this.value = weight;
    }

    public Weight() {
        //For ORM purposes only
        this.value = 0;
    }

    public static Weight valueOf(final double weight) {
        return new Weight(weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight weight = (Weight) o;
        return Double.compare(weight.value, value) == 0;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "value=" + value +
                '}';
    }
}
