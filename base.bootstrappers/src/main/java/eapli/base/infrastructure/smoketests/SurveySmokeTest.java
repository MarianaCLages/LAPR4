package eapli.base.infrastructure.smoketests;

import eapli.base.surveymanagement.application.CreateSurveyController;
import eapli.base.surveymanagement.domain.Period;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.domain.SurveyCode;
import eapli.base.surveymanagement.dto.SurveyDTO;
import eapli.framework.actions.Action;
import eapli.framework.general.domain.model.Description;
import eapli.framework.validations.Invariants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SurveySmokeTest implements Action {

    private static final Logger LOGGER = LogManager.getLogger(SurveySmokeTest.class);

    private static final CreateSurveyController controller = new CreateSurveyController();

    @Override
    public boolean execute() {
        return false;
    }

    private void testGetSurveyDTO() {
        SurveyCode SURVEY_CODE = SurveyCode.valueOf("S001");
        Description DESCRIPTION = Description.valueOf("Test description");
        Period PERIOD = Period.valueOf(10);

        byte[] bytes = new byte[0];
        Questionnaire QUESTIONNAIRE = Questionnaire.valueOf(bytes);
        List<String> RULE_LIST = new ArrayList<>();

        try {
            controller.createSurvey(SURVEY_CODE, DESCRIPTION, PERIOD, QUESTIONNAIRE, RULE_LIST);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        final SurveyDTO surveyDTO = controller.getSurveyDTO();

        Invariants.ensure(surveyDTO != null);

        LOGGER.info("»»» Survey DTO", surveyDTO);
    }
}
