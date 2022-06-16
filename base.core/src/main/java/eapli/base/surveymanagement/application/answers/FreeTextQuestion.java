package eapli.base.surveymanagement.application.answers;

public class FreeTextQuestion implements AnswerVerifier {



    @Override
    public boolean verifyAnswer(String answer, String options) {
        return !answer.equals("");
    }
}

