package eapli.base.surveymanagement.application.answers;

public class DecisionQuestion implements AnswerVerifier {



    @Override
    public boolean verifyAnswer(String answer, String options) {
        //YES/NO Y/N SIM/NÃO S/N
        return answer.equalsIgnoreCase("S")
                || answer.equalsIgnoreCase("N")
                || answer.equalsIgnoreCase("SIM")
                || answer.equalsIgnoreCase("NÃO")
                || answer.equalsIgnoreCase("YES")
                || answer.equalsIgnoreCase("NO")
                || answer.equalsIgnoreCase("Y");
    }
}
