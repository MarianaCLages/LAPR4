package eapli.base.surveymanagement.repositories;

import eapli.base.productmanagement.domain.Product;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.domain.Survey;
import eapli.base.surveymanagement.domain.SurveyCode;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface SurveyRepository extends DomainRepository<Long, Survey> {

    Survey findById(long id);

    List<Survey> findBySurveyCode(SurveyCode code);

    Iterable<Survey> findAll();
}
