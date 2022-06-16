package eapli.base.surveymanagement.application.questions;

import eapli.base.surveymanagement.domain.QuestionType;
import eapli.framework.util.Factory;

public class QuestionPrinterFactory implements Factory<QuestionPrinter> {
    private QuestionType type;
    private String question;
    private String options;

    public void setType(QuestionType type) {
        this.type = type;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    public QuestionPrinter build() {
        switch (type) {
            case TEXT:
                return new FreeTextQuestion(question);
            case NUMERIC:
                return new NumericQuestion(question);
            case SCALING:
                return new ScalingQuestion(question, options);
            case MULTIPLECHOICEINPUT:
            case OPTION:
                return new MultipleChoiceQuestion(question, options);
            case DECISION:
                return new DecisionQuestion(question);
            case INPUT:
                return new SingleChoiceQuestion(question, options);
            case SORT:
                return new SortQuestion(question, options);
            default:
                return null;
        }
    }
}
