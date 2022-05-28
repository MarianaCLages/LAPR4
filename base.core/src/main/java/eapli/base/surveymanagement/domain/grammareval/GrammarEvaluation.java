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
        ParseTree tree = parser.prog(); // parse
        GrammarBaseVisitor visitor = new GrammarBaseVisitor();
        visitor.visit(tree);
    }
}
