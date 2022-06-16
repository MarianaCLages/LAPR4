package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.application.questions.QuestionPrinter;
import eapli.base.surveymanagement.application.questions.QuestionPrinterFactory;
import eapli.base.surveymanagement.domain.Survey;
import eapli.base.surveymanagement.dto.QuestionDTO;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.SurveyRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

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
    private final AsnwersSaveService saveService = new AsnwersSaveService();
    private final VerifyAnswerService verifyService = new VerifyAnswerService();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private final SurveyRepository repository = PersistenceContext.repositories().surveys();
    private final QuestionPrinterFactory questionPrinterFactory = new QuestionPrinterFactory();
    private final List<String> answers = new ArrayList<>();
    private final List<String> questions = new ArrayList<>();
    private final List<String> types = new ArrayList<>();
    private final List<String> options = new ArrayList<>();


    //mock method to have a survey to answer
    private void setSurvey() {
        Iterable<Survey> surveys = repository.findAll();
        this.survey = surveys.iterator().next();

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
        setSurvey();

        questionnaireDTO = service.readSurvey(survey.questionnaire().toString());


        final StringBuilder header = new StringBuilder();
        header.append("\n\n");
        header.append("Survey: ").append(questionnaireDTO.title()).append("\n");
        header.append(questionnaireDTO.welcomingMessage()).append("\n");
        header.append("\n");
        return header.toString();
    }

    public String endSurvey() {
        final String surveyAnswersPath = String.format("docs/Extra/Surveys/Survey_%s/Survey%s_%s.txt", questionnaireDTO.id(), questionnaireDTO.id(), authorizationService.session().get().authenticatedUser().identity());
        final String surveyDirectory = String.format("docs/Extra/Surveys/Survey_%s", questionnaireDTO.id());

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
}
