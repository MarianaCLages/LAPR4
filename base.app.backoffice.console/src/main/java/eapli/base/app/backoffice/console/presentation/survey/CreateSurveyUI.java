package eapli.base.app.backoffice.console.presentation.survey;

import eapli.base.surveymanagement.application.CreateSurveyController;
import eapli.base.surveymanagement.domain.Period;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.domain.Rule;
import eapli.base.surveymanagement.domain.SurveyCode;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Description;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.validations.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateSurveyUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateSurveyUI.class);
    private final CreateSurveyController controller = new CreateSurveyController();

    @Override
    protected boolean doShow() {
        boolean verifySurveyCode = false;
        boolean verifyDescription = false;
        boolean verifyPeriod = false;
        boolean verifyQuestionnaire = false;
        boolean verifyRules = false;

        Optional<String> surveyCodeString = Optional.empty();
        Optional<String> descriptionString = Optional.empty();
        int period = 0;
        byte[] bytes = new byte[0];
        List<String> ruleList = new ArrayList<>();

        try {
            // Survey code
            do {
                try {
                    surveyCodeString = Optional.ofNullable((Console.readLine("\nPlease enter the alphanumeric survey code: ")));

                    if (surveyCodeString.get().isEmpty()) {
                        throw new IllegalArgumentException("Invalid survey code! Please, try again.");
                    }

                    verifySurveyCode = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifySurveyCode);

            // Description
            do {
                try {
                    descriptionString = Optional.ofNullable(Console.readLine("\nPlease enter the description of the survey: "));

                    if (descriptionString.get().isEmpty()) {
                        throw new IllegalArgumentException("Invalid short description! Please, try again.");
                    }

                    verifyDescription = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyDescription);

            // Period
            do {
                try {
                    period = Console.readInteger("\nPlease enter the period of time (number of days) of the survey: ");

                    if (period == 0) {
                        throw new IllegalArgumentException("Invalid period! It must be a number above 0. Please, try again.");
                    }

                    verifyPeriod = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (Exception ex) {
                    System.out.println("Invalid period! It must be a number above 0. Please, try again.");
                }
            } while (!verifyPeriod);

            // Questionnaire
            do {
                try {
                    String questionnairePath = Console.readLine("\nPlease enter the questionnaire's path (txt file) of the survey: ");
                    bytes = controller.validateAndVerifyQuestionnairePath(questionnairePath);

                    verifyQuestionnaire = true;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyQuestionnaire);

            // Rules
            do {
                try {
                    String rule = Console.readLine("\nPlease enter the rule of the survey: ");

                    boolean invalidOption;

                    do {
                        try {
                            String addRule = Console.readLine("Do you wish to enter another rule?");

                            if (addRule.equals("No") | addRule.equals("NO") | addRule.equals("no") | addRule.equals("N") | addRule.equals("n")) {
                                verifyRules = true;
                                ruleList.add(rule);
                            } else if (addRule.equals("Yes") | addRule.equals("YES") | addRule.equals("yes") | addRule.equals("Y") | addRule.equals("y")) {
                                verifyRules = false;
                                ruleList.add(rule);
                            } else {
                                throw new IllegalArgumentException("Please enter a valid option! (Yes or No)");
                            }

                            invalidOption = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            invalidOption = false;
                        }
                    } while (!invalidOption);


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyRules);

            try {
                controller.createSurvey(SurveyCode.valueOf(surveyCodeString.get()), Description.valueOf(descriptionString.get()), Period.valueOf(period), Questionnaire.valueOf(bytes), ruleList);

                System.out.println("\n\n### Survey Created ###\n" + controller.getSurveyDTO().toString() + "\n\nOperation success!\n");

            } catch (Exception e) {
                System.out.println("An error occurred while trying to create the survey. Please, try again.\n");
            }
        } catch (final IntegrityViolationException ex) {
            LOGGER.error("Error performing the operation!", ex);
            System.out.println("The survey inserted already exists.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Create a new Survey";
    }
}
