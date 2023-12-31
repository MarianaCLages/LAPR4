package eapli.base.surveymanagement.application;

import eapli.framework.application.ApplicationService;

import java.io.IOException;
import java.util.List;

/**
 * Service to save the answers of a survey of a user into a txt file.
 */
@ApplicationService
public class AnswersSaveService {
    public void saveAnswers(String fileName, String directory, List<String> answer, List<String> question, List<String> type, List<String> options) throws IOException {

        AnswersTextExporter exporter = new AnswersTextExporter();
        exporter.beginExport(fileName, directory);
        for (int i = 0; i < answer.size(); i++) {
            exporter.addAnswer(answer.get(i), question.get(i), type.get(i), options.get(i));
        }
        exporter.endExport();
    }
}
