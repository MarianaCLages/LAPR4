package eapli.base.surveymanagement.application.answers;

import eapli.base.surveymanagement.domain.exception.MissingOptionException;
import eapli.base.surveymanagement.domain.exception.RepeatedOptionException;

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
                throw new RepeatedOptionException("Repeated option in answer");
            } else {
                answerSet.add(answerOption);
            }
        }

        //checks if the answers elements and options elements are the same
        Arrays.sort(answerArray);
        Arrays.sort(optionsArray);
        if (!Arrays.equals(answerArray, optionsArray)){
            throw new MissingOptionException("Missing option");
        }

        return true;
    }
}
