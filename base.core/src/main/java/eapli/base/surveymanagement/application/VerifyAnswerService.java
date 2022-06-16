package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.application.answers.AnswerVerifier;
import eapli.base.surveymanagement.application.answers.AnswerVerifierFactory;
import eapli.base.surveymanagement.domain.QuestionType;

public class VerifyAnswerService {
    private final AnswerVerifierFactory answerVerifierFactory = new AnswerVerifierFactory();

    public boolean verifyAnswer(String answer, QuestionType questionType, String options) {

        answerVerifierFactory.setType(questionType);
        AnswerVerifier answerVerifier = answerVerifierFactory.build();
        return answerVerifier.verifyAnswer(answer, options);

    }
}
