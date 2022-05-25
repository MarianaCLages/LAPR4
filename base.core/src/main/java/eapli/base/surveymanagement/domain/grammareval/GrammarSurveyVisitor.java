// Generated from C:/Users/maria/OneDrive/Documentos/lei21_22_s4_2dj_1\GrammarSurvey.g4 by ANTLR 4.10.1
package eapli.base.surveymanagement.domain.grammareval;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarSurveyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarSurveyVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarSurveyParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(GrammarSurveyParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSurveyParser#survey}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSurvey(GrammarSurveyParser.SurveyContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSurveyParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(GrammarSurveyParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSurveyParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(GrammarSurveyParser.QuestionContext ctx);
}