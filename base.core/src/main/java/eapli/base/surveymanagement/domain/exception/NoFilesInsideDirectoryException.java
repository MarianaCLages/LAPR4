package eapli.base.surveymanagement.domain.exception;

public class NoFilesInsideDirectoryException extends Exception{

    public NoFilesInsideDirectoryException(){
        super("There are no answers for that specific questionnaire yet!");
    }
}
