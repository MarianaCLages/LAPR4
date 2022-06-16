package eapli.base.surveymanagement.application.answers;

import eapli.base.surveymanagement.domain.QuestionType;
import eapli.framework.util.Factory;

public class AnswerVerifierFactory implements Factory<AnswerVerifier> {
    private QuestionType type;

    public void setType(QuestionType type) {
        this.type = type;
    }


    @Override
    public AnswerVerifier build() {
        switch (type) {
            case TEXT:
                return new FreeTextQuestion();
            case NUMERIC:
                return new NumericQuestion();
            case MULTIPLECHOICEINPUT:
            case OPTION:
                return new MultipleChoiceQuestion();
            case DECISION:
                return new DecisionQuestion();
            case INPUT:
            case SCALING:
                return new SingleChoiceQuestion();
            case SORT:
                return new SortQuestion();
            default:
                return null;
        }
    }
}
