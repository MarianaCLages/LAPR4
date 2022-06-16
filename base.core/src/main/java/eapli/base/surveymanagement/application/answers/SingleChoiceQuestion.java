package eapli.base.surveymanagement.application.answers;

public class SingleChoiceQuestion implements AnswerVerifier {


    @Override
    public boolean verifyAnswer(String answer, String options) {
        //checks if answer is not empty
        if (answer.equals("")) {
            return false;
        }
        //Clean options from last space
        if (answer.endsWith(" ")) {
            answer = answer.substring(0, answer.length() - 1);
        }
        //checks if there is only one answer
        if (answer.contains(" ")) {
            return false;
        }

        //transforns options to array of strings
        String[] optionsArray = options.split(" ");
        //checks if answer is in the options array
        for (String option : optionsArray) {
            if (answer.equals(option)) {
                return true;
            }
        }

        return false;
    }
}
