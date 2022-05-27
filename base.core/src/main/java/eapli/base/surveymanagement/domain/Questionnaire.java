package eapli.base.surveymanagement.domain;

import eapli.base.productmanagement.domain.Photo;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Questionnaire that = (Questionnaire) obj;
        return Arrays.equals(this.content, that.content);
    }
}
