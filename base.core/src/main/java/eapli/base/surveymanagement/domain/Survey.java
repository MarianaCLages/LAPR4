package eapli.base.surveymanagement.domain;

import eapli.base.surveymanagement.dto.SurveyDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.List;

@Entity
public class Survey implements AggregateRoot<Long>, DTOable<SurveyDTO>, Representationable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long surveyId;

    @Version
    private Long version;

    @Column(nullable = false)
    private SurveyCode surveyCode;

    @Column(nullable = false)
    private Description description;

    @Column(nullable = false)
    private Period period;

    @Column(nullable = false)
    private Questionnaire questionnaire;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Rule> rules;

    protected Survey() {
        // For ORM only
    }

    public Survey(final SurveyCode surveyCode, Description description, Period period, Questionnaire questionnaire, List<Rule> rules) {
        Preconditions.noneNull(surveyCode, description, period, questionnaire, rules);

        this.surveyCode = surveyCode;
        this.description = description;
        this.period = period;
        this.questionnaire = questionnaire;
        this.rules = rules;
    }

    @Override
    public Long identity() {
        return this.surveyId;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Survey)) {
            return false;
        }

        final Survey that = (Survey) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && surveyCode.equals(that.surveyCode)
                && description.equals(that.description)
                && period.equals(that.period)
                && questionnaire.equals(that.questionnaire)
                && rules.equals(that.rules);
    }

    @Override
    public <R> R buildRepresentation(RepresentationBuilder<R> builder) {
        builder.startObject("Survey").withProperty("code", String.valueOf(surveyCode))
                .withProperty("description", description)
                .withProperty("period", String.valueOf(period))
                .withProperty("questionnaire", String.valueOf(questionnaire))
                .withProperty("rules", String.valueOf(rules));

        return builder.build();
    }

    @Override
    public boolean equals(Object obj) {
        return DomainEntities.areEqual(this, obj);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public SurveyDTO toDTO() {
        return new SurveyDTO(surveyCode.toString(), description.toString(), period.toString());
    }

    private void changeSurveyCode(final SurveyCode surveyCode) {
        if (surveyCode == null) {
            throw new IllegalArgumentException();
        }
        this.surveyCode = surveyCode;
    }

    private void changeDescription(final Description description) {
        if (description == null) {
            throw new IllegalArgumentException();
        }
        this.description = description;
    }

    private void changePeriod(final Period period) {
        if (period == null) {
            throw new IllegalArgumentException();
        }
        this.period = period;
    }

    private void changeQuestionnaire(final Questionnaire questionnaire) {
        if (questionnaire == null) {
            throw new IllegalArgumentException();
        }
        this.questionnaire = questionnaire;
    }

    private void changeRules(final List<Rule> rules) {
        if (rules == null) {
            throw new IllegalArgumentException();
        }
        this.rules = rules;
    }

    public Questionnaire questionnaire() {
        return questionnaire;
    }

    public void update(final SurveyCode surveyCode, final Description description, final Period period, final Questionnaire questionnaire, final List<Rule> rules) {
        Preconditions.noneNull(surveyCode, description, period, rules);

        changeSurveyCode(surveyCode);
        changeDescription(description);
        changePeriod(period);
        changeQuestionnaire(questionnaire);
        changeRules(rules);
    }
}
