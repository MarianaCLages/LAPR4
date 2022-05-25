package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.*;
import eapli.base.surveymanagement.repositories.SurveyRepository;
import eapli.framework.application.ApplicationService;
import eapli.framework.general.domain.model.Description;

import java.util.List;

@ApplicationService
public class CreateSurveyService {

    private final SurveyRepository surveyRepository = PersistenceContext.repositories().surveys();

    public Survey createSurvey(final SurveyCode surveyCode, final Description description, final Period period, final Questionnaire questionnaire, final List<Rule> rules) {
        return surveyRepository.save(new Survey(surveyCode, description, period, questionnaire, rules));
    }
}
