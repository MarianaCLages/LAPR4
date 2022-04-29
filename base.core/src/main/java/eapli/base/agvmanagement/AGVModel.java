package eapli.base.agvmanagement;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class AGVModel implements ValueObject, Comparable<AGVModel> {
    private String model;

    protected AGVModel(String model) {
        Preconditions.nonNull(model, "There must be a model");
        Preconditions.nonEmpty(model, "The model must not be empty");
        Preconditions.ensure(model.length() <= 50, "The model must not be longer than 50 characters");
        this.model = model;
    }

    protected AGVModel() {
    }


    public String model() {
        return this.model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AGVModel agvModel = (AGVModel) o;

        return this.model.equals(agvModel.model);
    }

    @Override
    public int hashCode() {
        return this.model.hashCode();
    }

    @Override
    public String toString() {
        return this.model;
    }

    @Override
    public int compareTo(AGVModel o) {
        return this.model.compareTo(o.model);
    }

    public static AGVModel valueOf(String model) {
        return new AGVModel(model);
    }
}
