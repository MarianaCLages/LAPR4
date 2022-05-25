package eapli.base.persistence.impl.inmemory;

import eapli.base.surveymanagement.domain.Survey;
import eapli.base.surveymanagement.domain.SurveyCode;
import eapli.base.surveymanagement.repositories.SurveyRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainAutoNumberRepository;

import java.util.List;

public class InMemorySurveyRepository extends InMemoryDomainAutoNumberRepository<Survey> implements SurveyRepository {

    private static final String NOT_SUPPORTED_YET = "Not supported yet.";

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Survey findById(long id) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public List<Survey> findBySurveyCode(SurveyCode code) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Iterable<Survey> findAll() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }
}
