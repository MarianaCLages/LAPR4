package eapli.base.app.user.console.presentation.survey;

import eapli.base.app.user.console.presentation.ClientUserBaseUI;
import eapli.base.surveymanagement.application.AnswerSurveyController;
import eapli.framework.io.util.Console;

import java.util.Optional;

public class AnswerSurveyUI extends ClientUserBaseUI {
    AnswerSurveyController controller = new AnswerSurveyController();

    @Override
    protected boolean doShow() {
        //codigo para escolher o questionario
        //TODO: implementar

        //mostrar o questionario e recolhe respostas
        getAnswerSurvey();
        return false;
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
}
