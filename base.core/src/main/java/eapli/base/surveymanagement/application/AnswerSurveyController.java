package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.application.questions.QuestionPrinter;
import eapli.base.surveymanagement.application.questions.QuestionPrinterFactory;
import eapli.base.surveymanagement.domain.Survey;
import eapli.base.surveymanagement.domain.SurveyCode;
import eapli.base.surveymanagement.dto.QuestionDTO;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.dto.SurveyDTO;
import eapli.base.surveymanagement.repositories.SurveyRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UseCaseController
public class AnswerSurveyController {
    private QuestionnaireDTO questionnaireDTO;
    private int questionIndex = 0;
    private int sectionIndex = 0;
    private Survey survey;

    private final ReadSurveyService service = new ReadSurveyService();
    private final AnswersSaveService saveService = new AnswersSaveService();
    private final VerifyAnswerService verifyService = new VerifyAnswerService();
    private final FindTargetedSurveyService findTargetedSurveyService = new FindTargetedSurveyService();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private final SurveyRepository repository = PersistenceContext.repositories().surveys();
    private final QuestionPrinterFactory questionPrinterFactory = new QuestionPrinterFactory();
    private final List<String> answers = new ArrayList<>();
    private final List<String> questions = new ArrayList<>();
    private final List<String> types = new ArrayList<>();
    private final List<String> options = new ArrayList<>();


    //mock method to have a survey to answer
    public List<SurveyDTO> getSurveys() {

        Optional<SystemUser> user = authorizationService.session().filter(userSession -> userSession.authenticatedUser().identity().equals(userSession.authenticatedUser().identity())).map(UserSession::authenticatedUser);

        if (!user.isPresent()) {
            throw new IllegalStateException("User not logged in");
        }

        List<Survey> surveys = findTargetedSurveyService.findByUserTargeted(user.get().email().toString(), user.get().name().toString());
        List<SurveyDTO> surveyDTOs = new ArrayList<>();

        //survey to surveyDTO
        for (Survey survey : surveys) {
            surveyDTOs.add(survey.toDTO());
        }

        return surveyDTOs;
    }

    public boolean hasNextQuestion() {
        if (this.sectionIndex < this.questionnaireDTO.sections().size()) {

            return this.questionIndex < this.questionnaireDTO.sections().get(sectionIndex).questions().size();
        } else {
            return false;
        }
    }

    public Optional<String> nextQuestion() {
        if (hasNextQuestion()) {
            QuestionDTO q = this.questionnaireDTO.sections().get(sectionIndex).questions().get(questionIndex);

            questionPrinterFactory.setType(q.questionType());
            questionPrinterFactory.setOptions(q.options());
            questionPrinterFactory.setQuestion(q.questionText());

            QuestionPrinter printer = questionPrinterFactory.build();

            return Optional.ofNullable(printer.print());

        } else {
            return Optional.empty();
        }
    }

    public void giveAnswer(String answer) {


        answers.add(answer);
        questions.add(this.questionnaireDTO.sections().get(sectionIndex).questions().get(questionIndex).questionText());
        types.add(this.questionnaireDTO.sections().get(sectionIndex).questions().get(questionIndex).questionType().toString());
        options.add(this.questionnaireDTO.sections().get(sectionIndex).questions().get(questionIndex).options());

        if (this.questionnaireDTO.sections().get(sectionIndex).questions().size() - 1 == questionIndex) {
            this.sectionIndex++;
            this.questionIndex = 0;
        } else {
            this.questionIndex++;
        }

    }

    public String startSurvey() {

        questionnaireDTO = service.readSurvey(survey.questionnaire().toString());

        final StringBuilder header = new StringBuilder();
        header.append("\n");
        header.append("Survey: ").append(questionnaireDTO.title()).append("\n");
        header.append(questionnaireDTO.welcomingMessage()).append("\n");
        return header.toString();
    }

    public String endSurvey() {
        final String surveyAnswersPath = String.format("docs/Extra/Surveys/Survey_%s/Survey%s_%s.txt", survey.toDTO().getSurveyCode(), survey.toDTO().getSurveyCode(), authorizationService.session().get().authenticatedUser().identity());
        final String surveyDirectory = String.format("docs/Extra/Surveys/Survey_%s", survey.toDTO().getSurveyCode());

        try {
            saveService.saveAnswers(surveyAnswersPath, surveyDirectory, answers, questions, types, options);
        } catch (IOException e) {
            return "Error saving survey answers, please try again.";
        }
        //returns end message of the survey
        return questionnaireDTO.goodbyeMessage();

    }

    public boolean verifyAnswer(String answer) {

        return verifyService.verifyAnswer(answer, questionnaireDTO.sections().get(sectionIndex).questions().get(questionIndex).questionType(), questionnaireDTO.sections().get(sectionIndex).questions().get(questionIndex).options());
    }

    public void setSurvey(SurveyDTO chosen) {
        this.survey = repository.findBySurveyCode(SurveyCode.valueOf(chosen.getSurveyCode())).get(0);
    }
}
