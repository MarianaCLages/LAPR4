package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductionCode implements ValueObject, Serializable {

    private final String productionCode;

    protected ProductionCode(final String productionCode) {
        Preconditions.nonEmpty(productionCode, "Production code should neither be null nor empty");
        this.productionCode = productionCode;
    }

    public ProductionCode() {
        //For ORM purposes only
        this.productionCode = null;
    }

    public static ProductionCode valueOf(final String productionCode) {
        return new ProductionCode(productionCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionCode that = (ProductionCode) o;
        return this.productionCode.equals(that.productionCode);
    }
}
