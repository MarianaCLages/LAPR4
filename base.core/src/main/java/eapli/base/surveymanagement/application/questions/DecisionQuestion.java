package eapli.base.surveymanagement.application.questions;

public class DecisionQuestion implements QuestionPrinter {
    private final String question;
    private final String options;

    public DecisionQuestion(String question, String options) {
        this.question = question;
        this.options = options;
    }

    @Override
    public String print() {
        return question + " " + options;
    }
}
