package eapli.base.persistence.impl.jpa;

import eapli.base.surveymanagement.domain.Survey;
import eapli.base.surveymanagement.domain.SurveyCode;
import eapli.base.surveymanagement.repositories.SurveyRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class JpaSurveyRepository extends BasepaRepositoryBase<Survey, Long, Long> implements SurveyRepository {

    public JpaSurveyRepository() {
        super("id");
    }

    @Override
    public Survey findById(long id) {
        final TypedQuery<Survey> q = createQuery("SELECT e FROM Survey e WHERE  e.surveyId = :m",
                Survey.class);

        q.setParameter("m", id);
        return q.getSingleResult();
    }

    @Override
    public List<Survey> findBySurveyCode(SurveyCode code) {
        final TypedQuery<Survey> q = createQuery("SELECT e FROM Survey e WHERE  e.surveyCode = :m",
                Survey.class);

        q.setParameter("m", code);
        return q.getResultList();
    }

    @Override
    public Iterable<Survey> findAll() {
        final TypedQuery<Survey> q = createQuery("select s from Survey s",
                Survey.class);

        return q.getResultList();
    }
}
