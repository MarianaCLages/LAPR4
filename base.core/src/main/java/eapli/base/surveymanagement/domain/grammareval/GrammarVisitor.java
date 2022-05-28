// Generated from C:/Users/maria/OneDrive/Documentos/lei21_22_s4_2dj_1\Grammar.g4 by ANTLR 4.10.1
package eapli.base.surveymanagement.domain.grammareval;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(GrammarParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code questionnaire}
	 * labeled alternative in {@link GrammarParser#survey}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaire(GrammarParser.QuestionnaireContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sections}
	 * labeled alternative in {@link GrammarParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSections(GrammarParser.SectionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code questions}
	 * labeled alternative in {@link GrammarParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestions(GrammarParser.QuestionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(GrammarParser.TypeContext ctx);
}