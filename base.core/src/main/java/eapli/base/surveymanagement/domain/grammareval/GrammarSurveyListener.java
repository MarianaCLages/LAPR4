// Generated from C:/Users/maria/OneDrive/Documentos/lei21_22_s4_2dj_1\GrammarSurvey.g4 by ANTLR 4.10.1
package eapli.base.surveymanagement.domain.grammareval;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarSurveyParser}.
 */
public interface GrammarSurveyListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarSurveyParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(GrammarSurveyParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSurveyParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(GrammarSurveyParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSurveyParser#survey}.
	 * @param ctx the parse tree
	 */
	void enterSurvey(GrammarSurveyParser.SurveyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSurveyParser#survey}.
	 * @param ctx the parse tree
	 */
	void exitSurvey(GrammarSurveyParser.SurveyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSurveyParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(GrammarSurveyParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSurveyParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(GrammarSurveyParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSurveyParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(GrammarSurveyParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSurveyParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(GrammarSurveyParser.QuestionContext ctx);
}