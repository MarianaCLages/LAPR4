package eapli.base.surveymanagement.domain.grammareval;

import java.io.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class GrammarEvaluation {
    public static void eval(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        GrammarLexer lexer = new GrammarLexer(new ANTLRInputStream(fis));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new RuntimeException("ERROR: line " + line + ":" + charPositionInLine + " " + msg, e);
            }
        });
        ParseTree tree = parser.prog(); // parse
        GrammarBaseVisitor visitor = new GrammarBaseVisitor();
        visitor.visit(tree);
    }
}
