package eapli.base.surveymanagement.application.questions;

public class NumericQuestion implements QuestionPrinter {
    private final String question;

    public NumericQuestion(String question) {
        this.question = question;
    }

    @Override
    public String print() {
        return question;
    }
}
