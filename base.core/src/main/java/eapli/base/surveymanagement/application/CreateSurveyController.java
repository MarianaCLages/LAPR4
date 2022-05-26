package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.domain.*;
import eapli.base.surveymanagement.dto.SurveyDTO;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.Description;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class CreateSurveyController {

    private Survey survey;

    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final CreateSurveyService createSurveyService = new CreateSurveyService();

    public Survey createSurvey(final SurveyCode surveyCode, final Description description, final Period period, final Questionnaire questionnaire, final List<String> rules) throws IOException {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_MANAGER, BaseRoles.POWER_USER);

        this.survey = createSurveyService.createSurvey(surveyCode, description, period, questionnaire, convertStringIntoRule(rules));

        return survey;
    }

    public SurveyDTO getSurveyDTO() {
        return survey.toDTO();
    }

    public byte[] validateAndVerifyQuestionnairePath(String path) throws IOException {
        String extension = FilenameUtils.getExtension(path);

        if (extension.equals("txt")) {
            File file = new File(path);
            byte[] bytes = createSurveyService.validateQuestionnairePath(path);

            if (file.exists() && !file.isDirectory() && bytes != null) {
                return bytes;

            } else {
                throw new IllegalStateException("Invalid path! The path introduced does not exist.");
            }
        } else {
            throw new IllegalStateException("Invalid file format! Please enter a .txt file.");
        }
    }

    public List<Rule> convertStringIntoRule(List<String> stringList) {
        List<Rule> ruleList = new ArrayList<>();

        for (String s : stringList) {
            Rule r = Rule.valueOf(s);
            ruleList.add(r);
        }

        return ruleList;
    }
}
