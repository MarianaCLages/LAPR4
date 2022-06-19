package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.SurveyCode;
import eapli.base.surveymanagement.repositories.SurveyRepository;

public class VerifyIfSurveyExistsService {

    private final SurveyRepository surveyRepository = PersistenceContext.repositories().surveys();

    public boolean findSurvey(String code) {
        return surveyRepository.findBySurveyCode(SurveyCode.valueOf(code)) != null;
    }

}