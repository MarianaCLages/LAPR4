package eapli.base.surveymanagement.dto;

import eapli.base.surveymanagement.domain.QuestionType;

public class QuestionDTO {
    private final String id;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id).append("\n");
        sb.append("question: ").append(questionText).append("\n");
        sb.append("type: ").append(questionType).append("\n");
        if (!options.isEmpty()) {
            sb.append("options: ").append(options).append("\n");
        }
        if (!instructions.isEmpty()) {
            sb.append("instructions: ").append(instructions).append("\n");
        }
        sb.append("obligatory: ").append(obligatory).append("\n");
        sb.append("extra info: ").append(extraInfo).append("\n");
        return sb.toString();
    }

    private final String questionText;
    private final QuestionType questionType;
    private final String options;
    private final String instructions;
    private final String obligatory;
    private final String extraInfo;

    public QuestionDTO(String id, String questionText, QuestionType questionType, String options, String instructions, String obligatory, String extraInfo) {
        this.id = id;
        this.questionText = questionText;
        this.questionType = questionType;
        this.options = options;
        this.instructions = instructions;
        this.obligatory = obligatory;
        this.extraInfo = extraInfo;
    }

    public String id() {
        return id;
    }

    public String questionText() {
        return questionText;
    }

    public QuestionType questionType() {
        return questionType;
    }

    public String options() {
        return options;
    }

    public String obligatory() {
        return obligatory;
    }

    public String extraInfo() {
        return extraInfo;
    }
}
