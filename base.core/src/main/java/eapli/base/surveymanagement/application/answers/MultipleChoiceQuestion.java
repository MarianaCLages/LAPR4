package eapli.base.surveymanagement.application.answers;

public class MultipleChoiceQuestion implements AnswerVerifier {




    @Override
    public boolean verifyAnswer(String answer, String options) {
        //checks if there is no answer
        if (answer.equals("")) {
            return false;
        }

        //transforns options to array of strings
        String[] optionsArray = options.split(" ");
        //transforms answer to array of strings
        String[] answerArray = answer.split(" ");
        //checks if there is not repeated answer
        for (String answerOption : answerArray) {
            for (String option : optionsArray) {
                if (answerOption.equals(option)) {
                    return true;
                }
            }
        }

        boolean found = false;

        //Checks if all answers are in the options
        for (String answerOption : answerArray) {
            found = false;
            for (String option : optionsArray) {
                if (answerOption.equals(option)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }

        return found;

    }
}
