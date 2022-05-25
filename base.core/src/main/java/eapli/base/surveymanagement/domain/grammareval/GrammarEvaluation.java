package eapli.base.surveymanagement.domain.grammareval;

import java.io.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class GrammarEvaluation {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(new File("SurveyExample.txt"));
        GrammarSurveyLexer lexer = new GrammarSurveyLexer(new ANTLRInputStream(fis));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarSurveyParser parser = new GrammarSurveyParser(tokens);
        ParseTree tree = parser.prog(); // parse
        GrammarVisitor visitor = new GrammarVisitor();
        visitor.visit(tree);
    }
}
