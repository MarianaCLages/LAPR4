package eapli.base.surveymanagement.domain.exception;

public class IllegalFormatSurvey extends RuntimeException {

    public IllegalFormatSurvey(String errorMessage) {
        super(errorMessage);
    }

    public IllegalFormatSurvey(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

}
