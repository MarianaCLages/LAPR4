package eapli.base.surveymanagement.domain;

import eapli.framework.general.domain.model.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SurveyBuilderTest {

    private static final SurveyCode SURVEY_CODE = SurveyCode.valueOf("S01");
    private static final Description DESCRIPTION = Description.valueOf("Survey description");
    private static final Period PERIOD = Period.valueOf(7);

    private static final byte[] test = new byte[0];
    private static final Questionnaire QUESTIONNAIRE = Questionnaire.valueOf(test);

    private static final Map<String, String> RULE_LIST = new HashMap<>();

    private Survey buildSurvey() {
        return new SurveyBuilder().withASurveyCode(SURVEY_CODE).withADescription(DESCRIPTION).withAPeriod(PERIOD).withAQuestionnaire(QUESTIONNAIRE).withASetOfRules(RULE_LIST).build();
    }

    @Test
    public void ensureCanCreateSurvey() {
        final Survey subject = new SurveyBuilder().withASurveyCode(SURVEY_CODE).withADescription(DESCRIPTION).withAPeriod(PERIOD).withAQuestionnaire(QUESTIONNAIRE).withASetOfRules(RULE_LIST).build();

        assertNotNull(subject);
    }

    @Test
    void ensureCannotCreateSurveyWithCodeNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new SurveyBuilder().withASurveyCode("").withADescription(DESCRIPTION).withAPeriod(PERIOD).withAQuestionnaire(QUESTIONNAIRE).withASetOfRules(RULE_LIST).build());
        assertEquals("Survey code should neither be null nor empty", exception.getMessage());
    }

    @Test
    void ensureCannotCreateSurveyWithDescriptionNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new SurveyBuilder().withASurveyCode(SURVEY_CODE).withADescription("").withAPeriod(PERIOD).withAQuestionnaire(QUESTIONNAIRE).withASetOfRules(RULE_LIST).build());
        assertEquals("Description should neither be null nor empty", exception.getMessage());
    }

    @Test
    void ensureCannotCreateSurveyWithPeriodNull() {
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> new SurveyBuilder().withASurveyCode(SURVEY_CODE).withADescription(DESCRIPTION).withAPeriod(null).withAQuestionnaire(QUESTIONNAIRE).withASetOfRules(RULE_LIST).build());
        assertNull(exception.getMessage());
    }

    @Test
    void ensureCannotCreateSurveyWithQuestionnaireNull() {
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> new SurveyBuilder().withASurveyCode(SURVEY_CODE).withADescription(DESCRIPTION).withAPeriod(PERIOD).withAQuestionnaire((Questionnaire) null).withASetOfRules(RULE_LIST).build());
        assertNull(exception.getMessage());
    }
}