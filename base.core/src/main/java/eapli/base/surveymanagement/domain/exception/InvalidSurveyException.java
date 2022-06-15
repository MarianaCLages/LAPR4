package eapli.base.surveymanagement.domain.exception;

public class InvalidSurveyException extends Exception{

    public InvalidSurveyException(){
        super("The ID that you entered is not valid! Please enter a valid questionnary ID!");
    }

}
