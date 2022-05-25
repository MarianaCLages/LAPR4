package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Description;

import java.util.ArrayList;
import java.util.List;

public class SurveyBuilder implements DomainFactory<Survey> {

    private Survey theSurvey;


    private SurveyCode surveyCode;
    private Description description;
    private Period period;
    private Questionnaire questionnaire;
    private final List<Rule> rules = new ArrayList<>();

    public SurveyBuilder withASurveyCode(final SurveyCode surveyCode) {
        this.surveyCode = surveyCode;
        return this;
    }

    public SurveyBuilder withASurveyCode(final String surveyCode) {
        return withASurveyCode(SurveyCode.valueOf(surveyCode));
    }

    public SurveyBuilder withADescription(final Description description) {
        this.description = description;
        return this;
    }

    public SurveyBuilder withADescription(final String description) {
        return withADescription(Description.valueOf(description));
    }

    public SurveyBuilder withAPeriod(final Period period) {
        this.period = period;
        return this;
    }

    public SurveyBuilder withAPeriod(final int period) {
        return withAPeriod(Period.valueOf(period));
    }


    public SurveyBuilder withAQuestionnaire(final Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
        return this;
    }

    public SurveyBuilder withAQuestionnaire(final byte[] content) {
        return withAQuestionnaire(Questionnaire.valueOf(content));
    }

    public SurveyBuilder withASetOfRules(final List<Rule> rules) {
        this.rules.addAll(rules);
        return this;
    }

    public SurveyBuilder addRules(final List<String> rules) {
        for (String s : rules) {
            this.rules.add(Rule.valueOf(s));
        }
        return this;
    }

    private Survey buildOrThrow() {
        if (theSurvey != null) {
            return theSurvey;
        } else if (surveyCode != null && description != null && period != null && questionnaire != null && rules != null) {
            theSurvey = new Survey(surveyCode, description, period, questionnaire, rules);
            return theSurvey;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Survey build() {
        final Survey ret = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do not change
        // the previously built survey.
        theSurvey = null;
        return ret;
    }
}
