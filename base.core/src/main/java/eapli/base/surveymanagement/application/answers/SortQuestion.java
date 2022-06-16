package eapli.base.surveymanagement.application.answers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SortQuestion implements AnswerVerifier {


    @Override
    public boolean verifyAnswer(String answer, String options) {
        //checks is answer is not empty
        if (answer.equals("")) {
            return false;
        }

        //answer to array of strings
        String[] answerArray = answer.split(" ");
        //options to array of strings
        String[] optionsArray = options.split(System.lineSeparator());


        //checks if there is not repeated answer in the answerArray
        Set<String> answerSet = new HashSet<>();
        for (String answerOption : answerArray) {
            if (answerSet.contains(answerOption)) {
                return false;
            } else {
                answerSet.add(answerOption);
            }
        }

        //checks if the answers elements and options elements are the same
        Arrays.sort(answerArray);
        Arrays.sort(optionsArray);
        return Arrays.equals(answerArray, optionsArray);
    }
}
