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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(SurveyBootstrapper.class);

    private final CreateSurveyController controller = new CreateSurveyController();

    @Override
    public boolean execute() {

        final SurveyCode surveyCode1 = SurveyCode.valueOf("S01");
        final SurveyCode surveyCode2 = SurveyCode.valueOf("S02");
        final SurveyCode surveyCode3 = SurveyCode.valueOf("S03");
        final SurveyCode surveyCode4 = SurveyCode.valueOf("S04");

        final Description description1 = Description.valueOf("Description about survey one");
        final Description description2 = Description.valueOf("Description about survey two");
        final Description description3 = Description.valueOf("Description about survey three");
        final Description description4 = Description.valueOf("Description about survey four");

        final Period period1 = Period.valueOf(14);
        final Period period2 = Period.valueOf(7);
        final Period period3 = Period.valueOf(60);
        final Period period4 = Period.valueOf(30);

        final String questionnaire1 = "SurveyExample.txt";
        final String questionnaire2 = "SurveyExample2.txt";
        final String questionnaire3 = "SurveyExample3.txt";
        final String questionnaire4 = "SurveyExample4.txt";

        byte[] bytes1;
        byte[] bytes2;
        byte[] bytes3;
        byte[] bytes4;
        try {
            bytes1 = controller.validateAndVerifyQuestionnairePath(questionnaire1);
            bytes2 = controller.validateAndVerifyQuestionnairePath(questionnaire2);
            bytes3 = controller.validateAndVerifyQuestionnairePath(questionnaire3);
            bytes4 = controller.validateAndVerifyQuestionnairePath(questionnaire4);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //creates 3 MAPS of rules and fills it with the values
        final Map<TargetRules, String> rules1 = new HashMap<>();
        rules1.put(TargetRules.AGE, "18");
        rules1.put(TargetRules.GENDER, "Male");
        final Map<TargetRules, String> rules2 = new HashMap<>();
        rules2.put(TargetRules.ORDERED_THE_BRAND, "IKEA");
        final Map<TargetRules, String> rules3 = new HashMap<>();
        rules3.put(TargetRules.ORDERED_THE_BRAND, "IKEA");
        rules3.put(TargetRules.ORDERED_THE_PRODUCTS, "Baby Yoda");
        final Map<TargetRules, String> rules4 = new HashMap<>();
        rules4.put(TargetRules.ORDERED_THE_PRODUCTS, "Baby Yoda");
        rules4.put(TargetRules.AGE, "18");


        final List<String> ruleList4 = new ArrayList<>();
        ruleList4.add("Only rule of questionnaire 4");

        createSurvey(surveyCode1, description1, period1, Questionnaire.valueOf(bytes1), rules1);
        createSurvey(surveyCode2, description2, period2, Questionnaire.valueOf(bytes2), rules2);
        createSurvey(surveyCode3, description3, period3, Questionnaire.valueOf(bytes3), rules3);
        createSurvey(surveyCode4, description4, period4, Questionnaire.valueOf(bytes4), rules4);

        return true;
    }

    private void createSurvey(final SurveyCode surveyCode, Description description, Period period, Questionnaire questionnaire, Map<TargetRules, String> ruleList) {
        try {
            Survey survey = controller.createSurvey(surveyCode, description, period, questionnaire, ruleList);
            LOGGER.debug("»»» {}", survey);
        } catch (final IntegrityViolationException | ConcurrencyException | IOException exception) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "survey");
            LOGGER.trace("Assuming existing record", exception);
        }
    }
}
