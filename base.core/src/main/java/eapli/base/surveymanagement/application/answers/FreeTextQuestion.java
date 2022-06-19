package eapli.base.surveymanagement.application.answers;

public class FreeTextQuestion implements AnswerVerifier {


    @Override
    public boolean verifyAnswer(String answer, String options) {
        if (answer.equals("")) {
            throw new IllegalArgumentException("Answer must not be empty");
        }
        return true;
    }
}

