package eapli.base.surveymanagement.domain.exception;

public class MissingOptionException extends RuntimeException {
    public MissingOptionException(String missingOption) {
        super(missingOption);
    }
}
