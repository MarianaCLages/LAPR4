package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.domain.exceptions.IllegalFormatSurvey;
import eapli.base.surveymanagement.domain.grammareval.GrammarLexer;
import eapli.base.surveymanagement.domain.grammareval.GrammarParser;
import eapli.base.surveymanagement.domain.grammareval.SurveyVisitor;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import org.antlr.v4.runtime.*;



public class ReadSurveyService {


    public QuestionnaireDTO readSurvey(String survey) throws IllegalFormatSurvey {
        GrammarLexer lexer = new GrammarLexer(CharStreams.fromString(survey));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalFormatSurvey("line " + line + ":" + charPositionInLine + " " + msg, e);
            }
        });

        SurveyVisitor visitor = new SurveyVisitor();
        visitor.visit(parser.prog());
        return visitor.getSurvey();
    }
}
