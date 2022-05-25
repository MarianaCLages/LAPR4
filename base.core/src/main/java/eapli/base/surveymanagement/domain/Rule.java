package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Rule implements ValueObject, Serializable {
    private final String rule;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ruleId;

    protected Rule(final String rule) {
        Preconditions.nonEmpty(rule);
        this.rule = rule;
    }

    public Rule() {
        // For ORM purposes only
        this.rule = null;
    }

    public static Rule valueOf(final String rule) {
        return new Rule(rule);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rule that = (Rule) obj;
        return this.rule.equals(that.rule);
    }
}
