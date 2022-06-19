package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.*;
import eapli.base.surveymanagement.domain.grammareval.GrammarEvaluation;
import eapli.base.surveymanagement.repositories.SurveyRepository;
import eapli.framework.application.ApplicationService;
import eapli.framework.general.domain.model.Description;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@ApplicationService
public class CreateSurveyService {

    private final SurveyRepository surveyRepository = PersistenceContext.repositories().surveys();

    public Survey createSurvey(final SurveyCode surveyCode, final Description description, final Period period, final Questionnaire questionnaire, final Map<String, String> rules) throws IOException {
        return surveyRepository.save(new Survey(surveyCode, description, period, questionnaire, rules));
    }

    public byte[] validateQuestionnairePath(String path) throws IOException {
        GrammarEvaluation.eval(path);
        byte[] txtInBytes;

        try {
            File file = new File(path);
            txtInBytes = new byte[(int) file.length()];
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(txtInBytes);
            fileInputStream.close();

        } catch (Exception e) {
            throw new IllegalStateException("Invalid file! Please, try again.");
        }

        return txtInBytes;
    }
}
