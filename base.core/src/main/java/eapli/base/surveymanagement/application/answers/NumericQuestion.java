package eapli.base.surveymanagement.application.answers;

public class NumericQuestion implements AnswerVerifier {


    @Override
    public boolean verifyAnswer(String answer, String options) {
        //checks if answer is a number
        return answer.matches("[0-9]+");
    }
}
