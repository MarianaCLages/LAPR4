package eapli.base.surveymanagement.application.questions;

public class FreeTextQuestion implements QuestionPrinter {
    private final String question;

    public FreeTextQuestion(String question) {
        this.question = question;
    }

    @Override
    public String print() {
        return question;
    }
}

