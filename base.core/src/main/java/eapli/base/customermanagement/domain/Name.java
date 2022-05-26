package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Name implements ValueObject, Comparable<Name> {

    private String customerName;


    public Name(final String customerName) {

        this.customerName = customerName;
    }

    public Name() {

    }

    public static Name valueOf(String name) {
        return new Name(name);
    }

    @Override
    public int compareTo(Name o) {

        if (this.customerName.equals(o.customerName)) {
            return 0;
        } else return -1;
    }

    @Override
    public String toString() {
        return "" + customerName;
    }
}
