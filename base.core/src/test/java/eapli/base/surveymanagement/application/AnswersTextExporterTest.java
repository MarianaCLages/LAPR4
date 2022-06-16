package eapli.base.surveymanagement.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnswersTextExporterTest {

    @Test
    void optionsFormater() {
        AnswersTextExporter exporter = new AnswersTextExporter();
        String options = "a b c d";
        String optionsFormated = "a;b;c;d";
        assertEquals(optionsFormated, exporter.optionsFormater(options));
    }

    @Test
    void optionsFormaterWithLineBreaks() {
        AnswersTextExporter exporter = new AnswersTextExporter();
        String options = "a" + System.lineSeparator() + "b" + System.lineSeparator() + "c" + System.lineSeparator() + "d ";
        String optionsFormated = "a;b;c;d";
        assertEquals(optionsFormated, exporter.optionsFormater(options));
    }
}