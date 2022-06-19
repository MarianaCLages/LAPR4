package eapli.base.app.user.console.presentation.survey;

import eapli.base.app.user.console.presentation.ClientUserBaseUI;
import eapli.base.surveymanagement.application.AnswerSurveyController;
import eapli.base.surveymanagement.dto.SurveyDTO;
import eapli.framework.io.util.Console;

import java.util.List;
import java.util.Optional;

public class AnswerSurveyUI extends ClientUserBaseUI {
    AnswerSurveyController controller = new AnswerSurveyController();

    @Override
    protected boolean doShow() {
        List<SurveyDTO> surveys = controller.getSurveys();
        if (surveys.isEmpty()) {
            System.out.println("You have no surveys to answer");
            return false;
        } else {
            SurveyDTO choosed = (SurveyDTO) showAndSelectOne(surveys, "Surveys to answer");

            controller.setSurvey(choosed);
            getAnswerSurvey();
            return true;
        }
    }

    private void getAnswerSurvey() {
        System.out.println(controller.startSurvey());

        while (controller.hasNextQuestion()) {

            Optional<String> question = controller.nextQuestion();
            if (question.isPresent()) {

                //get the answer
                String answer = "";
                boolean verification = true;
                while (verification) {
                    System.out.println(question.get());
                    answer = Console.readLine("Answer: ");
                    verification = !controller.verifyAnswer(answer);
                    if (verification) {
                        System.out.println("Invalid answer");
                    }
                }


                controller.giveAnswer(answer);

            } else {
                System.out.println("Não há mais perguntas");
            }
        }
        controller.endSurvey();
    }


    private Object showAndSelectOne(List list, String header) {
        showListNormal(list, header);
        return selectsObject(list);
    }


    private void showListNormal(List list, String header) {
        System.out.println(header);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println(index + ". " + o);
        }
        System.out.println();
        System.out.println("0 - Cancel");
    }


    private Object selectsObject(List list) {

        int value;
        do {
            value = Console.readInteger("Type your option: (Enter a valid option!)");
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }
}
