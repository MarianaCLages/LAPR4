package eapli.base.surveymanagement.application.questions;

public class SortQuestion implements QuestionPrinter {
    private final String question;
    private final String options;

    public SortQuestion(String question, String options) {
        this.question = question;
        this.options = options;
    }

    @Override
    public String print() {
        return question + "\n" + options;
    }
}
