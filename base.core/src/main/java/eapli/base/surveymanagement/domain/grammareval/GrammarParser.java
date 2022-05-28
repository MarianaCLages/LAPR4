// Generated from C:/Users/maria/OneDrive/Documentos/lei21_22_s4_2dj_1\Grammar.g4 by ANTLR 4.10.1
package eapli.base.surveymanagement.domain.grammareval;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, NEWLINE=11, MANDATORY=12, DECISION=13, FREETEXT=14, REP=15, NUM=16, 
		CHOICE=17, SORT=18, CHOICEINPUT=19, INT=20, ID=21, WORD=22, WS=23;
	public static final int
		RULE_prog = 0, RULE_survey = 1, RULE_section = 2, RULE_question = 3, RULE_type = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "survey", "section", "question", "type"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Survey - '", "'ID: '", "'Section - '", "'Question - '", "'Type: '", 
			"'Answer: '", "'Options:'", "'Options: '", "'Other'", "'other'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "NEWLINE", 
			"MANDATORY", "DECISION", "FREETEXT", "REP", "NUM", "CHOICE", "SORT", 
			"CHOICEINPUT", "INT", "ID", "WORD", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public SurveyContext survey() {
			return getRuleContext(SurveyContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			survey();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SurveyContext extends ParserRuleContext {
		public SurveyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_survey; }
	 
		public SurveyContext() { }
		public void copyFrom(SurveyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class QuestionnaireContext extends SurveyContext {
		public List<TerminalNode> WORD() { return getTokens(GrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(GrammarParser.WORD, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public QuestionnaireContext(SurveyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterQuestionnaire(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitQuestionnaire(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitQuestionnaire(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SurveyContext survey() throws RecognitionException {
		SurveyContext _localctx = new SurveyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_survey);
		int _la;
		try {
			_localctx = new QuestionnaireContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			match(T__0);
			setState(14); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(13);
				match(WORD);
				}
				}
				setState(16); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(19); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(18);
				match(NEWLINE);
				}
				}
				setState(21); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(23);
			match(T__1);
			setState(25); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(24);
				match(WORD);
				}
				}
				setState(27); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(30); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(29);
				match(NEWLINE);
				}
				}
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				section();
				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SectionContext extends ParserRuleContext {
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
	 
		public SectionContext() { }
		public void copyFrom(SectionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SectionsContext extends SectionContext {
		public TerminalNode MANDATORY() { return getToken(GrammarParser.MANDATORY, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public TerminalNode REP() { return getToken(GrammarParser.REP, 0); }
		public List<TerminalNode> WORD() { return getTokens(GrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(GrammarParser.WORD, i);
		}
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public SectionsContext(SectionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterSections(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitSections(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitSections(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_section);
		int _la;
		try {
			_localctx = new SectionsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(T__2);
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				match(WORD);
				}
				}
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(46); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(45);
				match(NEWLINE);
				}
				}
				setState(48); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(50);
			match(T__1);
			setState(52); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(51);
				match(WORD);
				}
				}
				setState(54); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(57); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(56);
				match(NEWLINE);
				}
				}
				setState(59); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(61);
			match(MANDATORY);
			setState(62);
			match(NEWLINE);
			setState(63);
			match(REP);
			setState(64);
			match(NEWLINE);
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				question();
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuestionContext extends ParserRuleContext {
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
	 
		public QuestionContext() { }
		public void copyFrom(QuestionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class QuestionsContext extends QuestionContext {
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode MANDATORY() { return getToken(GrammarParser.MANDATORY, 0); }
		public List<TerminalNode> WORD() { return getTokens(GrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(GrammarParser.WORD, i);
		}
		public QuestionsContext(QuestionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterQuestions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitQuestions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitQuestions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_question);
		int _la;
		try {
			_localctx = new QuestionsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(T__3);
			setState(72); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(71);
				match(WORD);
				}
				}
				setState(74); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(76);
			match(NEWLINE);
			setState(77);
			match(T__1);
			setState(79); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(78);
				match(WORD);
				}
				}
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(83);
			match(NEWLINE);
			setState(84);
			type();
			setState(85);
			match(MANDATORY);
			setState(86);
			match(NEWLINE);
			setState(88); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(87);
				match(WORD);
				}
				}
				setState(90); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(92);
				match(NEWLINE);
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode FREETEXT() { return getToken(GrammarParser.FREETEXT, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public List<TerminalNode> WORD() { return getTokens(GrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(GrammarParser.WORD, i);
		}
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public List<TerminalNode> INT() { return getTokens(GrammarParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(GrammarParser.INT, i);
		}
		public TerminalNode CHOICE() { return getToken(GrammarParser.CHOICE, 0); }
		public TerminalNode SORT() { return getToken(GrammarParser.SORT, 0); }
		public TerminalNode CHOICEINPUT() { return getToken(GrammarParser.CHOICEINPUT, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		int _la;
		try {
			setState(188);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				match(T__4);
				setState(99);
				match(FREETEXT);
				setState(100);
				match(NEWLINE);
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(101);
					match(T__5);
					setState(103); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(102);
						match(WORD);
						}
						}
						setState(105); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(107);
					match(NEWLINE);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				match(T__4);
				setState(111);
				match(NUM);
				setState(112);
				match(NEWLINE);
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(113);
					match(T__5);
					setState(115); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(114);
						match(INT);
						}
						}
						setState(117); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==INT );
					setState(119);
					match(NEWLINE);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
				match(T__4);
				setState(123);
				match(CHOICE);
				setState(124);
				match(NEWLINE);
				setState(125);
				match(T__6);
				setState(126);
				match(NEWLINE);
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(128); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(127);
						match(WORD);
						}
						}
						setState(130); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(132);
					match(NEWLINE);
					}
					}
					setState(135); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WORD );
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(137);
					match(T__5);
					setState(138);
					match(INT);
					setState(139);
					match(NEWLINE);
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(142);
				match(T__4);
				setState(143);
				match(SORT);
				setState(144);
				match(NEWLINE);
				setState(145);
				match(T__7);
				setState(146);
				match(NEWLINE);
				setState(153); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(148); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(147);
						match(WORD);
						}
						}
						setState(150); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(152);
					match(NEWLINE);
					}
					}
					setState(155); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WORD );
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(157);
					match(T__5);
					setState(159); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(158);
						match(INT);
						}
						}
						setState(161); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==INT );
					setState(163);
					match(NEWLINE);
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(166);
				match(T__4);
				setState(167);
				match(CHOICEINPUT);
				setState(168);
				match(NEWLINE);
				setState(169);
				match(T__6);
				setState(170);
				match(NEWLINE);
				setState(177); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(172); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(171);
						match(WORD);
						}
						}
						setState(174); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(176);
					match(NEWLINE);
					}
					}
					setState(179); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WORD );
				setState(181);
				_la = _input.LA(1);
				if ( !(_la==T__8 || _la==T__9) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(182);
				match(NEWLINE);
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(183);
					match(T__5);
					setState(184);
					match(INT);
					setState(185);
					match(NEWLINE);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0017\u00bf\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0004\u0001\u000f\b\u0001"+
		"\u000b\u0001\f\u0001\u0010\u0001\u0001\u0004\u0001\u0014\b\u0001\u000b"+
		"\u0001\f\u0001\u0015\u0001\u0001\u0001\u0001\u0004\u0001\u001a\b\u0001"+
		"\u000b\u0001\f\u0001\u001b\u0001\u0001\u0004\u0001\u001f\b\u0001\u000b"+
		"\u0001\f\u0001 \u0001\u0001\u0004\u0001$\b\u0001\u000b\u0001\f\u0001%"+
		"\u0001\u0002\u0001\u0002\u0004\u0002*\b\u0002\u000b\u0002\f\u0002+\u0001"+
		"\u0002\u0004\u0002/\b\u0002\u000b\u0002\f\u00020\u0001\u0002\u0001\u0002"+
		"\u0004\u00025\b\u0002\u000b\u0002\f\u00026\u0001\u0002\u0004\u0002:\b"+
		"\u0002\u000b\u0002\f\u0002;\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0004\u0002C\b\u0002\u000b\u0002\f\u0002D\u0001\u0003"+
		"\u0001\u0003\u0004\u0003I\b\u0003\u000b\u0003\f\u0003J\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0004\u0003P\b\u0003\u000b\u0003\f\u0003Q\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003Y\b\u0003"+
		"\u000b\u0003\f\u0003Z\u0001\u0003\u0005\u0003^\b\u0003\n\u0003\f\u0003"+
		"a\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0004\u0004h\b\u0004\u000b\u0004\f\u0004i\u0001\u0004\u0003\u0004m\b"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004"+
		"\u0004t\b\u0004\u000b\u0004\f\u0004u\u0001\u0004\u0003\u0004y\b\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0004\u0004\u0081\b\u0004\u000b\u0004\f\u0004\u0082\u0001\u0004\u0004"+
		"\u0004\u0086\b\u0004\u000b\u0004\f\u0004\u0087\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004\u008d\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u0095\b\u0004\u000b\u0004"+
		"\f\u0004\u0096\u0001\u0004\u0004\u0004\u009a\b\u0004\u000b\u0004\f\u0004"+
		"\u009b\u0001\u0004\u0001\u0004\u0004\u0004\u00a0\b\u0004\u000b\u0004\f"+
		"\u0004\u00a1\u0001\u0004\u0003\u0004\u00a5\b\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u00ad\b\u0004"+
		"\u000b\u0004\f\u0004\u00ae\u0001\u0004\u0004\u0004\u00b2\b\u0004\u000b"+
		"\u0004\f\u0004\u00b3\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004\u00bb\b\u0004\u0003\u0004\u00bd\b\u0004\u0001\u0004"+
		"\u0000\u0000\u0005\u0000\u0002\u0004\u0006\b\u0000\u0001\u0001\u0000\t"+
		"\n\u00d9\u0000\n\u0001\u0000\u0000\u0000\u0002\f\u0001\u0000\u0000\u0000"+
		"\u0004\'\u0001\u0000\u0000\u0000\u0006F\u0001\u0000\u0000\u0000\b\u00bc"+
		"\u0001\u0000\u0000\u0000\n\u000b\u0003\u0002\u0001\u0000\u000b\u0001\u0001"+
		"\u0000\u0000\u0000\f\u000e\u0005\u0001\u0000\u0000\r\u000f\u0005\u0016"+
		"\u0000\u0000\u000e\r\u0001\u0000\u0000\u0000\u000f\u0010\u0001\u0000\u0000"+
		"\u0000\u0010\u000e\u0001\u0000\u0000\u0000\u0010\u0011\u0001\u0000\u0000"+
		"\u0000\u0011\u0013\u0001\u0000\u0000\u0000\u0012\u0014\u0005\u000b\u0000"+
		"\u0000\u0013\u0012\u0001\u0000\u0000\u0000\u0014\u0015\u0001\u0000\u0000"+
		"\u0000\u0015\u0013\u0001\u0000\u0000\u0000\u0015\u0016\u0001\u0000\u0000"+
		"\u0000\u0016\u0017\u0001\u0000\u0000\u0000\u0017\u0019\u0005\u0002\u0000"+
		"\u0000\u0018\u001a\u0005\u0016\u0000\u0000\u0019\u0018\u0001\u0000\u0000"+
		"\u0000\u001a\u001b\u0001\u0000\u0000\u0000\u001b\u0019\u0001\u0000\u0000"+
		"\u0000\u001b\u001c\u0001\u0000\u0000\u0000\u001c\u001e\u0001\u0000\u0000"+
		"\u0000\u001d\u001f\u0005\u000b\u0000\u0000\u001e\u001d\u0001\u0000\u0000"+
		"\u0000\u001f \u0001\u0000\u0000\u0000 \u001e\u0001\u0000\u0000\u0000 "+
		"!\u0001\u0000\u0000\u0000!#\u0001\u0000\u0000\u0000\"$\u0003\u0004\u0002"+
		"\u0000#\"\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000%#\u0001\u0000"+
		"\u0000\u0000%&\u0001\u0000\u0000\u0000&\u0003\u0001\u0000\u0000\u0000"+
		"\')\u0005\u0003\u0000\u0000(*\u0005\u0016\u0000\u0000)(\u0001\u0000\u0000"+
		"\u0000*+\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000+,\u0001\u0000"+
		"\u0000\u0000,.\u0001\u0000\u0000\u0000-/\u0005\u000b\u0000\u0000.-\u0001"+
		"\u0000\u0000\u0000/0\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u0000"+
		"01\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u000024\u0005\u0002\u0000"+
		"\u000035\u0005\u0016\u0000\u000043\u0001\u0000\u0000\u000056\u0001\u0000"+
		"\u0000\u000064\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u000079\u0001"+
		"\u0000\u0000\u00008:\u0005\u000b\u0000\u000098\u0001\u0000\u0000\u0000"+
		":;\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000"+
		"\u0000<=\u0001\u0000\u0000\u0000=>\u0005\f\u0000\u0000>?\u0005\u000b\u0000"+
		"\u0000?@\u0005\u000f\u0000\u0000@B\u0005\u000b\u0000\u0000AC\u0003\u0006"+
		"\u0003\u0000BA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DB\u0001"+
		"\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000E\u0005\u0001\u0000\u0000"+
		"\u0000FH\u0005\u0004\u0000\u0000GI\u0005\u0016\u0000\u0000HG\u0001\u0000"+
		"\u0000\u0000IJ\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000JK\u0001"+
		"\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LM\u0005\u000b\u0000\u0000"+
		"MO\u0005\u0002\u0000\u0000NP\u0005\u0016\u0000\u0000ON\u0001\u0000\u0000"+
		"\u0000PQ\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000QR\u0001\u0000"+
		"\u0000\u0000RS\u0001\u0000\u0000\u0000ST\u0005\u000b\u0000\u0000TU\u0003"+
		"\b\u0004\u0000UV\u0005\f\u0000\u0000VX\u0005\u000b\u0000\u0000WY\u0005"+
		"\u0016\u0000\u0000XW\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000"+
		"ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[_\u0001\u0000\u0000"+
		"\u0000\\^\u0005\u000b\u0000\u0000]\\\u0001\u0000\u0000\u0000^a\u0001\u0000"+
		"\u0000\u0000_]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`\u0007"+
		"\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000bc\u0005\u0005\u0000"+
		"\u0000cd\u0005\u000e\u0000\u0000dl\u0005\u000b\u0000\u0000eg\u0005\u0006"+
		"\u0000\u0000fh\u0005\u0016\u0000\u0000gf\u0001\u0000\u0000\u0000hi\u0001"+
		"\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000"+
		"jk\u0001\u0000\u0000\u0000km\u0005\u000b\u0000\u0000le\u0001\u0000\u0000"+
		"\u0000lm\u0001\u0000\u0000\u0000m\u00bd\u0001\u0000\u0000\u0000no\u0005"+
		"\u0005\u0000\u0000op\u0005\u0010\u0000\u0000px\u0005\u000b\u0000\u0000"+
		"qs\u0005\u0006\u0000\u0000rt\u0005\u0014\u0000\u0000sr\u0001\u0000\u0000"+
		"\u0000tu\u0001\u0000\u0000\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000"+
		"\u0000\u0000vw\u0001\u0000\u0000\u0000wy\u0005\u000b\u0000\u0000xq\u0001"+
		"\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000y\u00bd\u0001\u0000\u0000"+
		"\u0000z{\u0005\u0005\u0000\u0000{|\u0005\u0011\u0000\u0000|}\u0005\u000b"+
		"\u0000\u0000}~\u0005\u0007\u0000\u0000~\u0085\u0005\u000b\u0000\u0000"+
		"\u007f\u0081\u0005\u0016\u0000\u0000\u0080\u007f\u0001\u0000\u0000\u0000"+
		"\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000"+
		"\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000"+
		"\u0084\u0086\u0005\u000b\u0000\u0000\u0085\u0080\u0001\u0000\u0000\u0000"+
		"\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000"+
		"\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u008c\u0001\u0000\u0000\u0000"+
		"\u0089\u008a\u0005\u0006\u0000\u0000\u008a\u008b\u0005\u0014\u0000\u0000"+
		"\u008b\u008d\u0005\u000b\u0000\u0000\u008c\u0089\u0001\u0000\u0000\u0000"+
		"\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u00bd\u0001\u0000\u0000\u0000"+
		"\u008e\u008f\u0005\u0005\u0000\u0000\u008f\u0090\u0005\u0012\u0000\u0000"+
		"\u0090\u0091\u0005\u000b\u0000\u0000\u0091\u0092\u0005\b\u0000\u0000\u0092"+
		"\u0099\u0005\u000b\u0000\u0000\u0093\u0095\u0005\u0016\u0000\u0000\u0094"+
		"\u0093\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096"+
		"\u0094\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097"+
		"\u0098\u0001\u0000\u0000\u0000\u0098\u009a\u0005\u000b\u0000\u0000\u0099"+
		"\u0094\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b"+
		"\u0099\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c"+
		"\u00a4\u0001\u0000\u0000\u0000\u009d\u009f\u0005\u0006\u0000\u0000\u009e"+
		"\u00a0\u0005\u0014\u0000\u0000\u009f\u009e\u0001\u0000\u0000\u0000\u00a0"+
		"\u00a1\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000\u0000\u0000\u00a1"+
		"\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3"+
		"\u00a5\u0005\u000b\u0000\u0000\u00a4\u009d\u0001\u0000\u0000\u0000\u00a4"+
		"\u00a5\u0001\u0000\u0000\u0000\u00a5\u00bd\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a7\u0005\u0005\u0000\u0000\u00a7\u00a8\u0005\u0013\u0000\u0000\u00a8"+
		"\u00a9\u0005\u000b\u0000\u0000\u00a9\u00aa\u0005\u0007\u0000\u0000\u00aa"+
		"\u00b1\u0005\u000b\u0000\u0000\u00ab\u00ad\u0005\u0016\u0000\u0000\u00ac"+
		"\u00ab\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae"+
		"\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af"+
		"\u00b0\u0001\u0000\u0000\u0000\u00b0\u00b2\u0005\u000b\u0000\u0000\u00b1"+
		"\u00ac\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4"+
		"\u00b5\u0001\u0000\u0000\u0000\u00b5\u00b6\u0007\u0000\u0000\u0000\u00b6"+
		"\u00ba\u0005\u000b\u0000\u0000\u00b7\u00b8\u0005\u0006\u0000\u0000\u00b8"+
		"\u00b9\u0005\u0014\u0000\u0000\u00b9\u00bb\u0005\u000b\u0000\u0000\u00ba"+
		"\u00b7\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb"+
		"\u00bd\u0001\u0000\u0000\u0000\u00bcb\u0001\u0000\u0000\u0000\u00bcn\u0001"+
		"\u0000\u0000\u0000\u00bcz\u0001\u0000\u0000\u0000\u00bc\u008e\u0001\u0000"+
		"\u0000\u0000\u00bc\u00a6\u0001\u0000\u0000\u0000\u00bd\t\u0001\u0000\u0000"+
		"\u0000\u001d\u0010\u0015\u001b %+06;DJQZ_ilux\u0082\u0087\u008c\u0096"+
		"\u009b\u00a1\u00a4\u00ae\u00b3\u00ba\u00bc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}