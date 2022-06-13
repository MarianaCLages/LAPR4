package eapli.base.surveymanagement.application;

import eapli.framework.application.ApplicationService;

import java.util.*;

@ApplicationService
public class GenerateReportService {

    // Single-choice and Scaling option
    public StringBuilder singleChoiceAndScalingCalculus(String question, List<String> answers, List<String> opt) {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> m = new HashMap<>();

        // Add the options to the map starting with a zero counter
        for (String s : opt) {
            m.put(s, 0);
        }

        // Check all answers
        for (String s : answers) {
            // Since the answer is received by its option number, we need to go through the option list to get the actual answer
            if (m.containsKey(opt.get(Integer.parseInt(s) - 1))) {
                m.put(s, m.get(s) + 1);
            }
        }

        sb.append("Question: ").append(question).append("\n").append("Distribution (in %) of responses for each alternative:\n");

        // Calculate the percentages
        for (String s : m.keySet()) {
            m.put(s, ((m.get(s) / answers.size()) * 100));
            sb.append("\t- ").append(s).append(" --> ").append(m.get(s)).append("\n");
        }

        return sb;
    }

    // Multiple-choice
    public StringBuilder multipleChoiceCalculus(String question, List<String> answerList, List<String> opt) {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> m = new HashMap<>();

        sb.append("Question - ").append(question).append("\n").append("Distribution (in %):\n");

        // Get all possible combinations list
        List<String[]> possAnswerList = new ArrayList<>();
        getCombination(opt, possAnswerList, opt.size());

        //insert all the possible combinations into the map keys
        for (String[] s : possAnswerList) {
            m.put(Arrays.toString(s), 0);
        }

        boolean contains;
        // Check all answers
        for (String s : answerList) {
            //go through each combination
            for (String[] sm : possAnswerList) {
                contains = true;
                for (String smv : sm) {
                    //check if each element of the combination exists in the answer
                    if (!s.contains(Integer.toString(opt.indexOf(smv)))) {
                        contains = false;
                        break;
                    }
                }
                //if all the combinations elements exist in the answer, it's added one to the corresponding counter
                if (contains) {
                    m.put(Arrays.toString(sm), m.get(Arrays.toString(sm) + 1));
                }
            }
        }

        // Calculate the percentages
        for (String s : m.keySet()) {
            m.put(s, ((m.get(s) / answerList.size()) * 100));
            sb.append("\t-").append(s).append(" --> ").append(m.get(s)).append("\n");
        }

        return sb;
    }

    private static void getCombination(List<String> opt, List<String[]> list, int n) {
        String[] data;

        for (int i = 1; i <= n; i++) {
            data = new String[i];
            combinationUtil(opt, list, data, 0, n - 1, 0, i);
        }
    }

    private static void combinationUtil(List<String> opt, List<String[]> list, String[] data, int start, int end, int index, int r) {
        if (index == r) {
            list.add(data.clone());
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = opt.get(i);
            combinationUtil(opt, list, data, i + 1, end, index + 1, r);
        }
    }

    public StringBuilder sortingOptionCalculus(String question, List<String> answerList, List<String> opt) {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> m = new HashMap<>();

        sb.append("Question: ").append(question).append("\n").append("Distribution (in %):\n");

        // Loop to get each position for the answers
        for (int i = 0; i < opt.size(); i++) {
            //initializing all the options' counters to 0
            for (String s : opt) {
                m.put(s, 0);
            }

            // Check all answers
            for (String s : answerList) {
                if (m.containsKey(opt.get(Integer.parseInt(s)))) {
                    m.put(s, m.get(s) + 1);
                }
            }

            // Calculating the percentages for each position
            for (String s : m.keySet()) {
                m.put(s, ((m.get(s) / answerList.size()) * 100));
                sb.append("   ").append(i + 1).append("º place\n");
                sb.append("\t-").append(s).append(" --> ").append(m.get(s)).append("\n");
            }
        }
        return sb;
    }
}
