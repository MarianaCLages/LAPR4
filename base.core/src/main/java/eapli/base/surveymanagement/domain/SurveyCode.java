package eapli.base.surveymanagement.domain;

import eapli.base.productmanagement.domain.Code;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SurveyCode implements ValueObject, Serializable {

    private final String code;

    public SurveyCode(final String code) {
        Preconditions.nonEmpty(code, "Survey code should neither be null nor empty");
        this.code = code;
    }

    public SurveyCode() {
        // For ORM purposes only
        this.code = null;
    }

    public static SurveyCode valueOf(final String code) {
        return new SurveyCode(code);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SurveyCode that = (SurveyCode) obj;
        return this.code.equals(that.code);
    }

    @Override
    public String toString() {
        return this.code;
    }
}
