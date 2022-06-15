package eapli.base.surveymanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionnaireTest {

    @Test
    void testToString() {
        final String content = "This is a test";
        final byte[] array = content.getBytes();

        final Questionnaire questionnaire = new Questionnaire(array);
        assertEquals("This is a test", questionnaire.toString());
    }
}