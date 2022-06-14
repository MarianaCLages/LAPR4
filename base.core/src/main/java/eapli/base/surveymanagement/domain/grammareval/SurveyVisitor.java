package eapli.base.surveymanagement.domain.grammareval;

import eapli.base.surveymanagement.dto.QuestionDTO;
import eapli.base.surveymanagement.dto.QuestionDTOFactory;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.dto.SectionDTO;

import java.util.ArrayList;
import java.util.List;

public class SurveyVisitor extends GrammarBaseVisitor<Object> {
    private QuestionnaireDTO survey;

    public SurveyVisitor() {
        super();
    }

    @Override
    public Boolean visitProg(GrammarParser.ProgContext ctx) {
        super.visitProg(ctx);
        return true;
    }

    @Override
    public Boolean visitSurveystructure(GrammarParser.SurveystructureContext ctx) {
        try {
            String titulo = ctx.title.getText();
            String wecomeMessage = ctx.welcmen.getText();
            String goodbyeMessage = ctx.finMess.getText();
            String surveyId = ctx.ID().getText();
            List<SectionDTO> sections = new ArrayList<>();


            for (GrammarParser.SectionContext section : ctx.section()) {
                sections.add(visitSections((GrammarParser.SectionsContext) section));
            }

            survey = new QuestionnaireDTO(surveyId, wecomeMessage, goodbyeMessage, sections, titulo);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public SectionDTO visitSections(GrammarParser.SectionsContext ctx) {
        String sectionId = ctx.ID().getText();
        String sectionTitle = ctx.secTit.getText();
        String sectionDescription = ctx.desc.getText();
        List<QuestionDTO> questions = new ArrayList<>();

        for (GrammarParser.QuestionContext question : ctx.question()) {
            questions.add(visitQuestions((GrammarParser.QuestionsContext) question));
        }

        return new SectionDTO(sectionId, sectionDescription, questions, sectionTitle);
    }

    @Override
    public QuestionDTO visitQuestions(GrammarParser.QuestionsContext ctx) {
        String questionId = ctx.ID().getText();
        String questionText = ctx.quest.getText();
        String obligatorienes = ctx.OBLIGATORINESS().getText();
        String options = "";
        try {
            options = ctx.questiontype().option().getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String instruction;
        try {
            instruction = ctx.insttruc.getText();
        } catch (NullPointerException e) {
            instruction = "";
        }
        String extraInfo = ctx.xInfo.getText();
        String type = ctx.type.type.getText();
        return createQuestion(questionId, questionText, obligatorienes, options, instruction, extraInfo, type);

    }

    public QuestionDTO createQuestion(String questionId, String questionText, String obligatorienes, String options, String instruction, String extraInfo, String type) {
        QuestionDTOFactory questionDTOFactory = new QuestionDTOFactory();
        questionDTOFactory.setId(questionId);
        questionDTOFactory.setQuestionText(questionText);
        questionDTOFactory.setObligatory(obligatorienes);
        questionDTOFactory.setOptions(options);
        questionDTOFactory.setInstructions(instruction);
        questionDTOFactory.setExtraInfo(extraInfo);
        questionDTOFactory.setQuestionType(type);


        return questionDTOFactory.build();

    }

    public QuestionnaireDTO getSurvey() {
        return survey;
    }
}
