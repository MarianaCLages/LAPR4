package eapli.base.surveymanagement.application;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.Email;
import eapli.base.customermanagement.domain.Name;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.Survey;
import eapli.base.surveymanagement.repositories.SurveyRepository;
import eapli.framework.application.ApplicationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationService
public class FindTargetedSurveyService {

    private final SurveyRepository surveyRepository = PersistenceContext.repositories().surveys();
    private final ClientRepository customerRepository = PersistenceContext.repositories().client();

    public List<Survey> findByUserTargeted(String email, String name) {
        Iterable<Survey> surveys = surveyRepository.findAll();
        List<Survey> surveysTargeted = new ArrayList<>();

        for (Survey survey : surveys) {
            if (userTarget(survey, email, name)) {
                surveysTargeted.add(survey);
            }
        }

        return surveysTargeted;

    }

    private boolean userTarget(Survey survey, String email, String name) {
        Map<String, String> targetRules = survey.rules();

        for (Map.Entry<String, String> entry : targetRules.entrySet()) {
            String ruleType = entry.getKey();
            String ruleValue = entry.getValue();

            switch (ruleType) {
                case "GENDER":
                    Customer customer = customerRepository.findByEmail(Email.valueOf(email), Name.valueOf(name));
                    if (customer.gender().gender().equals(ruleValue)) {
                        return true;
                    }
                    break;
                case "ORDERED_THE_BRAND":
                    return customerRepository.orderedTheBrand(email, name, ruleValue);
                case "ORDERED_THE_PRODUCTS":
                    return customerRepository.orderedTheProducts(email, name, ruleValue);
                default:
                    break;
            }
        }
        return false;
    }
}
