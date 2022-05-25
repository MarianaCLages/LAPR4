package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.domain.Period;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.domain.Survey;
import eapli.base.surveymanagement.domain.SurveyCode;
import eapli.base.surveymanagement.dto.SurveyDTO;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.Description;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.List;

@UseCaseController
public class CreateSurveyController {

    private Survey survey;

    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final CreateSurveyService createSurveyService = new CreateSurveyService();

    /*
    public Survey createSurvey(final SurveyCode surveyCode, final Description description, final Period period, final Questionnaire questionnaire, final List<String> rules) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_MANAGER, BaseRoles.POWER_USER);

        this.survey = createSurveyService.createSurvey(surveyCode, description, period, questionnaire, rules);

        return survey;
    }

     */

    public SurveyDTO getSurveyDTO() {
        return survey.toDTO();
    }

    public boolean validateAndVerifyQuestionnairePath(String path) {
        String extension = FilenameUtils.getExtension(path);

        if (extension.equals("txt")) {
            File file = new File(path);

            if (file.exists() && !file.isDirectory()) {
                return true;

            } else {
                throw new IllegalStateException("Invalid path! The path introduced does not exist.");
            }
        } else {
            throw new IllegalStateException("Invalid file format! Please enter a .txt file.");
        }
    }

    //TODO: método que vá buscar o conteudo do txt file quando inserido o path
}
