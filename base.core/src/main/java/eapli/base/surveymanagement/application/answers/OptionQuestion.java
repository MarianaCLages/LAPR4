package eapli.base.surveymanagement.application.answers;

public class OptionQuestion implements AnswerVerifier {

    @Override
    public boolean verifyAnswer(String answer, String options) {
        return false;
    }
}
