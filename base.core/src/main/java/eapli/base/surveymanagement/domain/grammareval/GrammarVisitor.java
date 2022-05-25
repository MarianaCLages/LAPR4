package eapli.base.surveymanagement.domain.grammareval;
public class GrammarVisitor extends GrammarSurveyBaseVisitor<Integer> {
    @Override
    public Integer visitProg(GrammarSurveyParser.ProgContext ctx) {
        return visitChildren(ctx);
    }
}
