package eapli.base.surveymanagement.application.questions;

public class DecisionQuestion implements QuestionPrinter {
    private final String question;

    public DecisionQuestion(String question) {
        this.question = question;
    }

    @Override
    public String print() {
        return question + " S/N Y/N SIM/NÃO YES/NO";
    }
}
