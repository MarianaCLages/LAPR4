package eapli.base.surveymanagement.dto;

import eapli.base.surveymanagement.domain.QuestionType;
import eapli.framework.util.Factory;

public class QuestionDTOFactory implements Factory<QuestionDTO> {

    private String questionType;
    private String questionId;
    private String extraInfo;
    private String obligatorienes;
    private String instruction;
    private String options;
    private String questionText;

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public void setInstructions(String instructions) {
        this.instruction = instructions;
    }

    public void setObligatory(String obligatory) {
        this.obligatorienes = obligatory;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public void setId(String id) {
        this.questionId = id;
    }


    @Override
    public QuestionDTO build() {
        switch (questionType) {
            case "Decision":
                return new QuestionDTO(questionId, questionText, QuestionType.DECISION, options, instruction, obligatorienes, extraInfo);
            case "Multiple Choice":
            case "Multiple choice":
                return new QuestionDTO(questionId, questionText, QuestionType.MULTIPLECHOICEINPUT, options, instruction, obligatorienes, extraInfo);
            case "Single Choice":
            case "Single choice":
            case "single choice":
            case "single Choice":
                return new QuestionDTO(questionId, questionText, QuestionType.INPUT, options, instruction, obligatorienes, extraInfo);
            case "Sort Options":
            case "sort options":
            case "sort Options":
            case "Sort options":
                return new QuestionDTO(questionId, questionText, QuestionType.SORT, options, instruction, obligatorienes, extraInfo);
            case "Scaling Options":
            case "scaling options":
            case "scaling Options":
            case "Scaling options":
                return new QuestionDTO(questionId, questionText, QuestionType.SCALING, options, instruction, obligatorienes, extraInfo);
            case "Free-Text":
            case "free text":
            case "Free Text":
            case "free-text":
                return new QuestionDTO(questionId, questionText, QuestionType.TEXT, options, instruction, obligatorienes, extraInfo);
            case "Numeric":
            case "numeric":
                return new QuestionDTO(questionId, questionText, QuestionType.NUMERIC, options, instruction, obligatorienes, extraInfo);
            default:
                return null;
        }
    }
}
