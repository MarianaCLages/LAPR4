package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.AttributeOverride;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class WarehouseName implements ValueObject, Comparable<WarehouseName> {

    private static final long serialVersionUID = 1L;

    @Embedded
    @AttributeOverride(name = "name", column = @javax.persistence.Column(name = "name"))
    private Designation name;

    public Designation getName() {
        return name;
    }

    public WarehouseName(final Designation name) {
        Preconditions.nonNull(name, "name cannot be null");
        this.name = name;
    }

    protected WarehouseName() {
        // for ORM
    }

    public String name() {
        return this.name.toString();
    }

    //equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarehouseName that = (WarehouseName) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        final HashCoder coder = new HashCoder().with(name);
        return coder.code();
    }



    @Override
    public int compareTo(WarehouseName o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
