package eapli.base.surveymanagement.application.answers;

public class NumericQuestion implements AnswerVerifier {


    @Override
    public boolean verifyAnswer(String answer, String options) {
        //checks if answer is a number
        if (!answer.matches("[0-9]+")) {
            throw new IllegalArgumentException("Answer must be a number");
        }
        return true;
    }
}
