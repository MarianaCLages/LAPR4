package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BrandName implements ValueObject, Serializable {

    private final String brandName;

    protected BrandName(final String brandName) {
        Preconditions.nonEmpty(brandName, "Brand name should neither be null nor empty");
        this.brandName = brandName;
    }

    public BrandName() {
        //For ORM purposes only
        this.brandName = null;
    }

    public static BrandName valueOf(final String brandName) {
        return new BrandName(brandName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandName that = (BrandName) o;
        return this.brandName.equals(that.brandName);
    }

    @Override
    public String toString() {
        return this.brandName;
    }
}
