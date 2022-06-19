package eapli.base.surveymanagement.application.answers;

public class ScalingQuestion implements AnswerVerifier {

    @Override
    public boolean verifyAnswer(String answer, String options) {
        //checks if answer is not empty
        if (answer.equals("")) {
            throw new IllegalArgumentException("Answer cannot be empty");
        }
        //Clean options from last space
        if (answer.endsWith(" ")) {
            answer = answer.substring(0, answer.length() - 1);
        }
        //checks if there is only one answer
        if (answer.contains(" ")) {
            throw new IllegalArgumentException("Answer cannot contain more than one answer");
        }

        //transforns options to array of strings
        String[] optionsArray = options.split(" ");
        //checks if answer is in the options array
        for (String option : optionsArray) {
            if (answer.equals(option)) {
                return true;
            }
        }

        throw new IllegalArgumentException("Answer is not in the options");
    }
}

