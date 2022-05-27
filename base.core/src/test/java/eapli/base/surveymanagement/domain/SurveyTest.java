package eapli.base.surveymanagement.domain;

import eapli.framework.general.domain.model.Description;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SurveyTest {

    private static final SurveyCode SURVEY_CODE = SurveyCode.valueOf("S01");
    private static final Description DESCRIPTION = Description.valueOf("Survey description");
    private static final Period PERIOD = Period.valueOf(7);

    private static final byte[] bytes = new byte[0];
    private static final Questionnaire QUESTIONNAIRE = Questionnaire.valueOf(bytes);

    private static final List<Rule> RULE_LIST = new ArrayList<>();

    private Survey buildSurvey() {
        return new SurveyBuilder().withASurveyCode(SURVEY_CODE).withADescription(DESCRIPTION).withAPeriod(PERIOD).withAQuestionnaire(QUESTIONNAIRE).withASetOfRules(RULE_LIST).build();
    }

    @Test
    void ensureCanCreateSurvey() {
        new Survey(SURVEY_CODE, DESCRIPTION, PERIOD, QUESTIONNAIRE, RULE_LIST);

        assertTrue(true);
    }

    @Test
    void ensureMustHaveSurveyCode() {
        assertThrows(IllegalArgumentException.class, () -> new Survey(null, DESCRIPTION, PERIOD, QUESTIONNAIRE, RULE_LIST));
    }

    @Test
    void ensureMustHaveDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Survey(SURVEY_CODE, null, PERIOD, QUESTIONNAIRE, RULE_LIST));
    }

    @Test
    void ensureMustHavePeriod() {
        assertThrows(IllegalArgumentException.class, () -> new Survey(SURVEY_CODE, DESCRIPTION, null, QUESTIONNAIRE, RULE_LIST));
    }

    @Test
    void ensureMustHaveQuestionnaire() {
        assertThrows(IllegalArgumentException.class, () -> new Survey(SURVEY_CODE, DESCRIPTION, PERIOD, null, RULE_LIST));
    }

    @Test
    void ensureMustHaveRule() {
        assertThrows(IllegalArgumentException.class, () -> new Survey(SURVEY_CODE, DESCRIPTION, PERIOD, QUESTIONNAIRE, null));
    }

    @Test
    void ensureCannotChangeSurveyCodeToNull() {
        final Survey subject = buildSurvey();

        assertThrows(IllegalArgumentException.class, () -> subject.update(null, DESCRIPTION, PERIOD, QUESTIONNAIRE, RULE_LIST));
    }

    @Test
    void ensureCannotChangeDescriptionToNull() {
        final Survey subject = buildSurvey();

        assertThrows(IllegalArgumentException.class, () -> subject.update(SURVEY_CODE, null, PERIOD, QUESTIONNAIRE, RULE_LIST));
    }

    @Test
    void ensureCannotChangePeriodToNull() {
        final Survey subject = buildSurvey();

        assertThrows(IllegalArgumentException.class, () -> subject.update(SURVEY_CODE, DESCRIPTION, null, QUESTIONNAIRE, RULE_LIST));
    }

    @Test
    void ensureCannotChangeQuestionnaireToNull() {
        final Survey subject = buildSurvey();

        assertThrows(IllegalArgumentException.class, () -> subject.update(SURVEY_CODE, DESCRIPTION, PERIOD, null, RULE_LIST));
    }

    @Test
    void ensureCannotChangeRuleToNull() {
        final Survey subject = buildSurvey();

        assertThrows(IllegalArgumentException.class, () -> subject.update(SURVEY_CODE, DESCRIPTION, PERIOD, QUESTIONNAIRE, null));
    }

    @Test
    void ensureCanChangeSurveyCode() {
        final Survey subject = buildSurvey();

        final SurveyCode newInfo = SurveyCode.valueOf("S02");

        subject.update(newInfo, DESCRIPTION, PERIOD, QUESTIONNAIRE, RULE_LIST);
    }

    @Test
    void ensureCanChangeDescription() {
        final Survey subject = buildSurvey();

        final Description newInfo = Description.valueOf("Another description");

        subject.update(SURVEY_CODE, newInfo, PERIOD, QUESTIONNAIRE, RULE_LIST);
    }

    @Test
    void ensureCanChangePeriod() {
        final Survey subject = buildSurvey();

        final Period newInfo = Period.valueOf(30);

        subject.update(SURVEY_CODE, DESCRIPTION, newInfo, QUESTIONNAIRE, RULE_LIST);
    }

    @Test
    void ensureCanChangeQuestionnaire() {
        final Survey subject = buildSurvey();

        byte[] bytes = new byte[0];
        final Questionnaire newInfo = Questionnaire.valueOf(bytes);

        subject.update(SURVEY_CODE, DESCRIPTION, PERIOD, newInfo, RULE_LIST);
    }

    @Test
    void ensureCanChangeRule() {
        final Survey subject = buildSurvey();

        final List<Rule> newInfo = new ArrayList<>();

        subject.update(SURVEY_CODE, DESCRIPTION, PERIOD, QUESTIONNAIRE, newInfo);
    }
}