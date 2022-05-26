package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.surveymanagement.application.CreateSurveyController;
import eapli.base.surveymanagement.domain.*;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SurveyBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(SurveyBootstrapper.class);

    private final CreateSurveyController controller = new CreateSurveyController();

    @Override
    public boolean execute() {

        final SurveyCode surveyCode1 = SurveyCode.valueOf("S01");
        final SurveyCode surveyCode2 = SurveyCode.valueOf("S02");

        final Description description1 = Description.valueOf("Description about survey one");
        final Description description2 = Description.valueOf("Description about survey two");

        final Period period1 = Period.valueOf(14);
        final Period period2 = Period.valueOf(7);

        final String questionnaire1 = "SurveyExample.txt";
        final String questionnaire2 = "SurveyExample.txt";

        byte[] bytes1;
        byte[] bytes2;
        try {
            bytes1 = controller.validateAndVerifyQuestionnairePath(questionnaire1);
            bytes2 = controller.validateAndVerifyQuestionnairePath(questionnaire2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final List<String> ruleList1 = new ArrayList<>();
        ruleList1.add("Rule 1 for questionnaire 1");
        ruleList1.add("Rule 2 for questionnaire 1");

        final List<String> ruleList2 = new ArrayList<>();
        ruleList2.add("Only rule of questionnaire 2");

        createSurvey(surveyCode1, description1, period1, Questionnaire.valueOf(bytes1), ruleList1);
        createSurvey(surveyCode2, description2, period2, Questionnaire.valueOf(bytes2), ruleList2);

        return true;
    }

    private void createSurvey(final SurveyCode surveyCode, Description description, Period period, Questionnaire questionnaire, List<String> ruleList) {
        try {
            Survey survey = controller.createSurvey(surveyCode, description, period, questionnaire, ruleList);
            LOGGER.debug("»»» {}", survey);
        } catch (final IntegrityViolationException | ConcurrencyException | IOException exception) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "survey");
            LOGGER.trace("Assuming existing record", exception);
        }
    }
}
