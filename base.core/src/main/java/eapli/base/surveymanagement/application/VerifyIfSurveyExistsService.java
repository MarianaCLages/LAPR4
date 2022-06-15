package eapli.base.surveymanagement.application;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.repositories.SurveyRepository;

public class VerifyIfSurveyExistsService {

    private final SurveyRepository surveyRepository = PersistenceContext.repositories().surveys();

    public boolean findSurvey(int id) {
        return surveyRepository.findById(id) != null;
    }

}