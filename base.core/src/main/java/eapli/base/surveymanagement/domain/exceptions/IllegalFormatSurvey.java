package eapli.base.surveymanagement.domain.exceptions;

public class IllegalFormatSurvey extends RuntimeException {

    public IllegalFormatSurvey(String errorMessage) {
        super(errorMessage);
    }

    public IllegalFormatSurvey(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

}
