package eapli.base.surveymanagement.domain.exception;

public class InvalidAnswerFileException extends Exception{

    public InvalidAnswerFileException(){
        super("The answer file is invalid! Please communicate with an admin!");
    }

    public InvalidAnswerFileException(String str){
        super(str);
    }

}
