package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.domain.exception.InvalidAnswerFileException;
import eapli.base.surveymanagement.domain.exception.NoFilesInsideDirectoryException;
import eapli.framework.application.ApplicationService;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

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
            if (answers.contains(s)) {
                m.put(s, m.get(s) + 1);
            }
        }

        sb.append("Question: ").append(question).append("\n").append("Distribution (in %) of responses for each alternative:\n");

        // Calculate the percentages
        for (String s : m.keySet()) {
            m.put(s, ((m.get(s) * 100) / answers.size()));
            sb.append("\t- ").append(s).append(" --> ").append(m.get(s)).append("\n");
        }

        return sb;
    }

    // Multiple-choice
    public String multipleChoiceCalculus(String question, List<String> answerList, List<String> opt) {
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

        String aux = null;

        boolean contains;
        // Check all answers
        for (String s : answerList) {
            //go through each combination
            for (String[] sm : possAnswerList) {
                contains = true;
                for (String smv : sm) {
                    //check if each element of the combination exists in the answer
                    if (!s.contains(smv)) {
                        contains = false;
                        break;
                    }
                    aux = Arrays.toString(sm);
                }
                //if all the combinations elements exist in the answer, it's added one to the corresponding counter
                if (contains) {
                    int value = m.get(aux) + 1;
                    m.put(Arrays.toString(sm), value);
                }
            }
        }

        // Calculate the percentages
        for (String s : m.keySet()) {
            m.put(s, ((m.get(s) * 100) / answerList.size()));
            sb.append("\t-").append(s).append(" --> ").append(m.get(s)).append("\n");
        }

        return sb.toString();
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
                if (m.containsKey(s)) {
                    m.put(s, m.get(s) + 1);
                }
            }

            // Calculating the percentages for each position
            for (String s : m.keySet()) {
                m.put(s, ((m.get(s) * 100) / answerList.size()));
                sb.append("   ").append(i + 1).append("º place\n");
                sb.append("\t-").append(s).append(" --> ").append(m.get(s)).append("\n");
            }
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

    public void getAllClientAnswersFromSurvey(int surveyId) throws NoFilesInsideDirectoryException, InvalidAnswerFileException {
        File directory = new File("docs/Extra/Surveys/Survey_" + surveyId);
        int fileCount = directory.list().length;

        if (fileCount == 0) {
            throw new NoFilesInsideDirectoryException();
        }

        //Question TYPE | Key - Question / Value - Type
        Map<String, String> questionType = new HashMap<>();

        //Question Options | Key - Question / Value - List of Options
        Map<String, List<String>> options = new HashMap<>();

        //Question Answers | Key - Question / Value - List of answers
        Map<String, List<String>> answers = new HashMap<>();

        for (final File fileEntry : Objects.requireNonNull(directory.listFiles())) {
            if (!fileEntry.isDirectory()) {

                try (BufferedReader reader = new BufferedReader(new FileReader(fileEntry))) {

                    BufferedReader fileReader = new BufferedReader(new FileReader(fileEntry));

                    int lines = 0;
                    while (reader.readLine() != null) lines++;

                    if (lines % 2 != 0) throw new InvalidAnswerFileException();

                    List<List<String>> records = new ArrayList<>();
                    String line;
                    lines = 0;

                    List<String> optionsList = new ArrayList<>();
                    List<String> answersList = new ArrayList<>();

                    List<String> auxList = new ArrayList<>();

                    String question = null;

                    while ((line = fileReader.readLine()) != null) {

                        String[] values = line.split(";");

                        if (lines % 2 == 0) {

                            question = values[1];
                            String type = values[0];

                            //Assume a type for a question
                            questionType.put(question, type);

                            //Question options (If there exists any)
                            for (int i = 2; i < values.length; i++) {
                                optionsList.add(values[i]);
                            }

                            auxList = optionsList;

                            options.put(question, auxList);

                            optionsList = new ArrayList<>();
                            lines++;

                        } else {

                            answersList.addAll(Arrays.asList(values));

                            auxList = answersList;

                            if (answers.containsKey(question) && !answers.get(question).isEmpty()) {
                                answers.get(question).addAll(auxList);
                            } else {
                                answers.put(question, auxList);
                            }

                            answersList = new ArrayList<>();

                            lines++;

                        }

                    }

                    fileReader.close();

                } catch (Exception e) {
                    //throw new InvalidAnswerFileException("Error while reading the answer file!");
                    e.printStackTrace();
                }

            }

        }

        //GERAR REPORT
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(generateHtmlReport());

        //Percorrer todas as questões
        for (String question : questionType.keySet()) {
            if (questionType.get(question).equals("Multiple-Choice")) {
                //GERAR INFORMAÇÃO REPORT COM MULTIPLE CHOICE
                stringBuilder.append(multipleChoiceCalculus(question, answers.get(question), options.get(question)));

            } else if (questionType.get(question).equals("ScalingOption") || questionType.get(question).equals("SingleChoice")) {
                //GERAR INFORMAÇÃO REPORT COM SCALING OPTION / SINGLE CHOiCE
                stringBuilder.append(singleChoiceAndScalingCalculus(question, answers.get(question), options.get(question))).toString();

            } else if (questionType.get(question).equals("SortingOption")) {
                //GERAR INFORMAÇÃO REPORT COM SORTING OPTION
                stringBuilder.append(sortingOptionCalculus(question, answers.get(question), options.get(question))).toString();

            }

            stringBuilder.append("\n");

        }

        stringBuilder.append(generateEndPageHtmlReport());

        try (PrintWriter out = new PrintWriter("docs/Extra/StatisticalReport/ReportSurvey" + surveyId)) {
            out.println(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void openSurveyFile(String path) {
        try {
            File file = new File(path);

            if (!Desktop.isDesktopSupported()) {
                System.out.println("not supported");
                return;
            }

            Desktop desktop = Desktop.getDesktop();

            if (file.exists()) desktop.open(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateHtmlReport() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<!DOCTYPE html>\n" +
                "\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Statistical Report</title>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <style>\n" +
                "\n" +
                "        .rep{\n" +
                "            border-radius: 20px;\n" +
                "            style: padding 10px;\n" +
                "            border: 2px black;\n" +
                "            background-color: white;\n" +
                "        }\n" +
                "\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        html, body {\n" +
                "            height: 100%;\n" +
                "        }\n" +
                "\n" +
                "        header {\n" +
                "            height: 25%;\n" +
                "        }\n" +
                "\n" +
                "        section {\n" +
                "            height: 65%;\n" +
                "        }\n" +
                "\n" +
                "        footer {\n" +
                "            height: 10%;\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            text-align: center;\n" +
                "            color: white;\n" +
                "            text-transform: uppercase;\n" +
                "            padding: 1px;\n" +
                "            font-weight: 100;\n" +
                "            position: relative;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "        }\n" +
                "\n" +
                "        h1 a {\n" +
                "            background: black;\n" +
                "            display: block;\n" +
                "            padding: 20px;\n" +
                "            text-decoration: none;\n" +
                "            letter-spacing: 20px;\n" +
                "            color: white;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "        }\n" +
                "\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "\n" +
                "            clear: left;\n" +
                "\t´   bgcolor=\"#00a2e8\"\n" +
                "            display: block;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        .context h1 {\n" +
                "            text-align: center;\n" +
                "            color: #fff;\n" +
                "            font-size: 30px;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "        }\n" +
                "\n" +
                "        footer {\n" +
                "            position: static;\n" +
                "            text-align: center;\n" +
                "            bottom: 0px;\n" +
                "            width: 100%;\n" +
                "            font-size: medium;\n" +
                "            style: padding 10px;\n" +
                "            border: 2px black;\n" +
                "            background-color: aliceblue;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "        }\n" +
                "\n" +
                "        footer address {\n" +
                "            font-size: 1em;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<center>\n" +
                "    <h2>\n" +
                "        <a href=\"#0\">\n" +
                "            Survey Report\n" +
                "        </a>\n" +
                "    </h1>\n" +
                "\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "\n" +
                "    <div class=\"rep\">\n" +
                "        REPORT AQUI\n" +
                "    <!DOCTYPE html>\n" +
                "\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>AGV Status Dashboard</title>\n" +
                "    <link rel=\"icon\" type=\"image/x-icon\" href=\"amazon2.png\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <script src=\"rcomp-ajax.js\"></script>\n" +
                "    <style>\n" +
                "\n" +
                "        .agv {\n" +
                "            border-radius: 20px;\n" +
                "            style: padding 10px;\n" +
                "            border: 2px black;\n" +
                "            background-color: white;\n" +
                "        }\n" +
                "\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        html, body {\n" +
                "            height: 100%;\n" +
                "        }\n" +
                "\n" +
                "        header {\n" +
                "            height: 25%;\n" +
                "        }\n" +
                "\n" +
                "        section {\n" +
                "            height: 65%;\n" +
                "        }\n" +
                "\n" +
                "        footer {\n" +
                "            height: 10%;\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            text-align: center;\n" +
                "            color: white;\n" +
                "            text-transform: uppercase;\n" +
                "            padding: 1px;\n" +
                "            font-weight: 100;\n" +
                "            position: relative;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "        }\n" +
                "\n" +
                "        h1 a {\n" +
                "            background: black;\n" +
                "            display: block;\n" +
                "            padding: 20px;\n" +
                "            text-decoration: none;\n" +
                "            letter-spacing: 20px;\n" +
                "            color: white;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "        }\n" +
                "\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "\n" +
                "            clear: left;\n" +
                "            display: block;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        .context h1 {\n" +
                "            text-align: center;\n" +
                "            color: #fff;\n" +
                "            font-size: 30px;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "        }\n" +
                "\n" +
                "        @keyframes animate {\n" +
                "\n" +
                "            50% {\n" +
                "                transform: translateY(0) rotate(0deg);\n" +
                "                opacity: 1;\n" +
                "                border-radius: 0;\n" +
                "            }\n" +
                "\n" +
                "            50% {\n" +
                "                transform: translateY(-1000px) rotate(720deg);\n" +
                "                opacity: 0;\n" +
                "                border-radius: 50%;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        footer {\n" +
                "            position: static;\n" +
                "            text-align: center;\n" +
                "            bottom: 0px;\n" +
                "            width: 100%;\n" +
                "            font-size: medium;\n" +
                "            style: padding 10px;\n" +
                "            border: 2px black;\n" +
                "            background-color: aliceblue;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "        }\n" +
                "\n" +
                "        footer address {\n" +
                "            font-size: 1em;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body bgcolor=\"#00a2e8\">\n" +
                "\n" +
                "<center>\n" +
                "    <h1>\n" +
                "        <a href=\"#0\">\n" +
                "            Survey Report\n" +
                "        </a>\n" +
                "    </h1>\n" +
                "\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "\n" +
                "    <div class=\"agv\">\n" +
                "\t<p>");

        return stringBuilder.toString();

    }

    public String generateEndPageHtmlReport() {

        StringBuilder sc = new StringBuilder();

        sc.append("</p></div>\n" +
                "\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "\n" +
                "    <div class=\"footer\" style=\"position: relative;\n" +
                "                text-align: center;\n" +
                "                font-family: 'Slabo 27px', serif;\n" +
                "                bottom: 0px;\n" +
                "                width: 100%;\n" +
                "                font-size: medium;\n" +
                "                style: padding 10px;\n" +
                "                border: 2px black;\n" +
                "                background-color: aliceblue;\">\n" +
                "        <h3>LEI-2DJ-G01-LAPR4</h3>\n" +
                "        <table style=\"width:115%\">\n" +
                "            <tr>\n" +
                "                <td>Miguel Jordao</td>\n" +
                "                <td>1201487@isep.ipp.pt</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Mariana Lages</td>\n" +
                "                <td>1200902@isep.ipp.pt</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Tiago Ferreira</td>\n" +
                "                <td>1200601@isep.ipp.pt</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Eduardo Sousa</td>\n" +
                "                <td>1200920@isep.ipp.pt</td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "\n" +
                "</center>\n" +
                "</body>\n" +
                "</html>");

        return sc.toString();

    }

}