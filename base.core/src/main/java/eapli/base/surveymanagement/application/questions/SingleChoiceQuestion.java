package eapli.base.surveymanagement.application.questions;

public class SingleChoiceQuestion implements QuestionPrinter {
    private final String question;
    private final String options;

    public SingleChoiceQuestion(String question, String options) {
        this.question = question;
        this.options = options;
    }

    @Override
    public String print() {
        return question + " " + options;
    }
}
