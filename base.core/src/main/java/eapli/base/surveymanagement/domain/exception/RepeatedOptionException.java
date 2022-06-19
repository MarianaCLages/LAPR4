package eapli.base.surveymanagement.domain.exception;

public class RepeatedOptionException extends RuntimeException {
    public RepeatedOptionException(String repeated_option_in_answer) {
        super(repeated_option_in_answer);
    }


}
