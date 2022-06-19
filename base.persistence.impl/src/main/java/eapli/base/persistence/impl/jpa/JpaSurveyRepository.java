package eapli.base.persistence.impl.jpa;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.Survey;
import eapli.base.surveymanagement.domain.SurveyCode;
import eapli.base.surveymanagement.repositories.SurveyRepository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

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
    public int countCustomersPerSurvey(String surveyId) {
        //get all customers
        ClientRepository clientRepository = PersistenceContext.repositories().client();
        Iterable<Customer> customers = clientRepository.findAll();
        Survey survey = findBySurveyCode(SurveyCode.valueOf(surveyId)).get(0);
        int numberOfCustomers = 0;
        for (Customer customer : customers) {
            boolean hasSurvey = false;
            for (Map.Entry<String, String> entry : survey.rules().entrySet()) {
                if (entry.getKey().equals(("GENDER")) && customer.gender().gender().equals(entry.getValue())) {
                    numberOfCustomers++;
                    hasSurvey = true;
                }
                if (entry.getKey().equals("ORDERED_THE_PRODUCTS") && clientRepository.orderedTheProducts(customer.toDTO().customerEmail(), customer.toDTO().customerName(), entry.getValue())) {
                    numberOfCustomers++;
                    hasSurvey = true;
                }

                if (entry.getKey().equals("ORDERED_THE_BRAND") && clientRepository.orderedTheBrand(customer.toDTO().customerEmail(), customer.toDTO().customerName(), entry.getValue())) {
                    numberOfCustomers++;
                    hasSurvey = true;
                }
                if (hasSurvey) {
                    break;
                }

            }

        }
        return numberOfCustomers;

    }

    @Override
    public Iterable<Survey> findAll() {
        final TypedQuery<Survey> q = createQuery("select s from Survey s",
                Survey.class);

        return q.getResultList();
    }
}
