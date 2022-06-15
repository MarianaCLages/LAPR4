package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

@Embeddable
public class Questionnaire implements ValueObject, Serializable {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private final byte[] content;

    protected Questionnaire(final byte[] content) {
        Preconditions.nonEmpty(Collections.singleton(content), "Questionnaire should neither be null nor empty");
        this.content = content;
    }

    public Questionnaire() {
        // For ORM purposes only
        this.content = null;
    }

    public static Questionnaire valueOf(final byte[] content) {
        return new Questionnaire(content);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.content);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Questionnaire that = (Questionnaire) obj;
        return Arrays.equals(this.content, that.content);
    }

    @Override
    public String toString() {
        return new String(this.content);
    }
}
