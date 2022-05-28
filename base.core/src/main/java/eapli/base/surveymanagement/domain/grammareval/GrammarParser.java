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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, NEWLINE=17, 
		OBLIGATORINESS=18, DECISION=19, DECISIONANSWER=20, TEXT=21, REPEATABILITY=22, 
		NUMERIC=23, OPTION=24, SORT=25, INPUT=26, MULTIPLECHOICEINPUT=27, SCALINGOPTIONS=28, 
		INT=29, ID=30, MESSAGE=31;
	public static final int
		RULE_prog = 0, RULE_survey = 1, RULE_section = 2, RULE_question = 3, RULE_questiontype = 4, 
		RULE_answers = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "survey", "section", "question", "questiontype", "answers"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'ID: '", "'Survey'", "'Title: '", "'Welcome Message:'", "'List of Sections:'", 
			"'Final Message: '", "'Section Title: '", "'Description:'", "'Content:'", 
			"'Question: '", "'Instruction:'", "'Extra Information: '", "'Question Type: '", 
			"'Possible Answers:'", "'Options:'", "'Answer:'", null, null, "'Decision'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "NEWLINE", "OBLIGATORINESS", "DECISION", 
			"DECISIONANSWER", "TEXT", "REPEATABILITY", "NUMERIC", "OPTION", "SORT", 
			"INPUT", "MULTIPLECHOICEINPUT", "SCALINGOPTIONS", "INT", "ID", "MESSAGE"
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
			setState(12);
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
	public static class SurveystructureContext extends SurveyContext {
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public List<TerminalNode> MESSAGE() { return getTokens(GrammarParser.MESSAGE); }
		public TerminalNode MESSAGE(int i) {
			return getToken(GrammarParser.MESSAGE, i);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public SurveystructureContext(SurveyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterSurveystructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitSurveystructure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitSurveystructure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SurveyContext survey() throws RecognitionException {
		SurveyContext _localctx = new SurveyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_survey);
		int _la;
		try {
			_localctx = new SurveystructureContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			match(T__0);
			setState(15);
			match(ID);
			setState(16);
			match(NEWLINE);
			setState(17);
			match(T__1);
			setState(18);
			match(NEWLINE);
			setState(19);
			match(T__2);
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(20);
				match(MESSAGE);
				}
				}
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MESSAGE );
			setState(26); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(25);
				match(NEWLINE);
				}
				}
				setState(28); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(30);
				match(T__3);
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(31);
					match(MESSAGE);
					}
					}
					setState(34); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==MESSAGE );
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(36);
					match(NEWLINE);
					}
					}
					setState(39); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
			}

			setState(43);
			match(T__4);
			setState(45); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(44);
				match(NEWLINE);
				}
				}
				setState(47); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(50); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(49);
				section();
				}
				}
				setState(52); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(54);
			match(T__5);
			setState(56); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(55);
				match(MESSAGE);
				}
				}
				setState(58); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MESSAGE );
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
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public TerminalNode OBLIGATORINESS() { return getToken(GrammarParser.OBLIGATORINESS, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public TerminalNode REPEATABILITY() { return getToken(GrammarParser.REPEATABILITY, 0); }
		public List<TerminalNode> MESSAGE() { return getTokens(GrammarParser.MESSAGE); }
		public TerminalNode MESSAGE(int i) {
			return getToken(GrammarParser.MESSAGE, i);
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
			int _alt;
			_localctx = new SectionsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(T__0);
			setState(61);
			match(ID);
			setState(63); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(62);
				match(NEWLINE);
				}
				}
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(67);
			match(T__6);
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68);
				match(MESSAGE);
				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MESSAGE );
			setState(74); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(73);
				match(NEWLINE);
				}
				}
				setState(76); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(78);
			match(T__7);
			setState(80); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(79);
				match(MESSAGE);
				}
				}
				setState(82); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MESSAGE );
			setState(85); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(84);
				match(NEWLINE);
				}
				}
				setState(87); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(89);
			match(OBLIGATORINESS);
			setState(90);
			match(NEWLINE);
			setState(91);
			match(REPEATABILITY);
			setState(92);
			match(NEWLINE);
			setState(93);
			match(T__8);
			setState(95); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(94);
				match(NEWLINE);
				}
				}
				setState(97); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(100); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(99);
					question();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(102); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public QuestiontypeContext questiontype() {
			return getRuleContext(QuestiontypeContext.class,0);
		}
		public TerminalNode OBLIGATORINESS() { return getToken(GrammarParser.OBLIGATORINESS, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public List<TerminalNode> MESSAGE() { return getTokens(GrammarParser.MESSAGE); }
		public TerminalNode MESSAGE(int i) {
			return getToken(GrammarParser.MESSAGE, i);
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
			setState(104);
			match(T__0);
			setState(105);
			match(ID);
			setState(107); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(106);
				match(NEWLINE);
				}
				}
				setState(109); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(111);
			match(T__9);
			setState(113); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(112);
				match(MESSAGE);
				}
				}
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MESSAGE );
			setState(118); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(117);
				match(NEWLINE);
				}
				}
				setState(120); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(122);
				match(T__10);
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(123);
					match(MESSAGE);
					}
					}
					setState(126); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==MESSAGE );
				}
			}

			setState(130);
			questiontype();
			setState(131);
			match(OBLIGATORINESS);
			setState(132);
			match(NEWLINE);
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(133);
				match(T__11);
				setState(135); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(134);
					match(MESSAGE);
					}
					}
					setState(137); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==MESSAGE );
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(139);
					match(NEWLINE);
					}
					}
					setState(144);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
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

	public static class QuestiontypeContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(GrammarParser.TEXT, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public AnswersContext answers() {
			return getRuleContext(AnswersContext.class,0);
		}
		public TerminalNode OPTION() { return getToken(GrammarParser.OPTION, 0); }
		public List<TerminalNode> MESSAGE() { return getTokens(GrammarParser.MESSAGE); }
		public TerminalNode MESSAGE(int i) {
			return getToken(GrammarParser.MESSAGE, i);
		}
		public TerminalNode INPUT() { return getToken(GrammarParser.INPUT, 0); }
		public TerminalNode MULTIPLECHOICEINPUT() { return getToken(GrammarParser.MULTIPLECHOICEINPUT, 0); }
		public TerminalNode NUMERIC() { return getToken(GrammarParser.NUMERIC, 0); }
		public TerminalNode SCALINGOPTIONS() { return getToken(GrammarParser.SCALINGOPTIONS, 0); }
		public TerminalNode DECISION() { return getToken(GrammarParser.DECISION, 0); }
		public TerminalNode SORT() { return getToken(GrammarParser.SORT, 0); }
		public QuestiontypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questiontype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterQuestiontype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitQuestiontype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitQuestiontype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestiontypeContext questiontype() throws RecognitionException {
		QuestiontypeContext _localctx = new QuestiontypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_questiontype);
		int _la;
		try {
			setState(243);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(147);
				match(T__12);
				setState(148);
				match(TEXT);
				setState(149);
				match(NEWLINE);
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(150);
					answers();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				match(T__12);
				setState(154);
				match(OPTION);
				setState(155);
				match(NEWLINE);
				setState(156);
				match(T__13);
				setState(157);
				match(NEWLINE);
				setState(164); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(159); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(158);
						match(MESSAGE);
						}
						}
						setState(161); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==MESSAGE );
					setState(163);
					match(NEWLINE);
					}
					}
					setState(166); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==MESSAGE );
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(168);
					answers();
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(171);
				match(T__12);
				setState(172);
				match(INPUT);
				setState(173);
				match(NEWLINE);
				setState(174);
				match(T__13);
				setState(175);
				match(NEWLINE);
				setState(182); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(177); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(176);
						match(MESSAGE);
						}
						}
						setState(179); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==MESSAGE );
					setState(181);
					match(NEWLINE);
					}
					}
					setState(184); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==MESSAGE );
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(186);
					answers();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(189);
				match(T__12);
				setState(190);
				match(MULTIPLECHOICEINPUT);
				setState(191);
				match(NEWLINE);
				setState(192);
				match(T__13);
				setState(193);
				match(NEWLINE);
				setState(200); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(195); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(194);
						match(MESSAGE);
						}
						}
						setState(197); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==MESSAGE );
					setState(199);
					match(NEWLINE);
					}
					}
					setState(202); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==MESSAGE );
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(204);
					answers();
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(207);
				match(T__12);
				setState(208);
				match(NUMERIC);
				setState(209);
				match(NEWLINE);
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(210);
					answers();
					}
				}

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(213);
				match(T__12);
				setState(214);
				match(SCALINGOPTIONS);
				setState(215);
				match(NEWLINE);
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(216);
					answers();
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(219);
				match(T__12);
				setState(220);
				match(DECISION);
				setState(221);
				match(NEWLINE);
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(222);
					answers();
					}
				}

				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(225);
				match(T__12);
				setState(226);
				match(SORT);
				setState(227);
				match(NEWLINE);
				setState(228);
				match(T__14);
				setState(229);
				match(NEWLINE);
				setState(236); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(231); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(230);
						match(MESSAGE);
						}
						}
						setState(233); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==MESSAGE );
					setState(235);
					match(NEWLINE);
					}
					}
					setState(238); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==MESSAGE );
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(240);
					answers();
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

	public static class AnswersContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public List<TerminalNode> MESSAGE() { return getTokens(GrammarParser.MESSAGE); }
		public TerminalNode MESSAGE(int i) {
			return getToken(GrammarParser.MESSAGE, i);
		}
		public List<TerminalNode> INT() { return getTokens(GrammarParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(GrammarParser.INT, i);
		}
		public TerminalNode DECISIONANSWER() { return getToken(GrammarParser.DECISIONANSWER, 0); }
		public AnswersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterAnswers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitAnswers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitAnswers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswersContext answers() throws RecognitionException {
		AnswersContext _localctx = new AnswersContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_answers);
		int _la;
		try {
			setState(277);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(245);
				match(T__15);
				setState(247); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(246);
					match(MESSAGE);
					}
					}
					setState(249); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==MESSAGE );
				setState(251);
				match(NEWLINE);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(252);
				match(T__15);
				setState(253);
				match(INT);
				setState(254);
				match(NEWLINE);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(255);
				match(T__15);
				setState(257); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(256);
					match(INT);
					}
					}
					setState(259); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==INT );
				setState(261);
				match(NEWLINE);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(262);
				match(T__15);
				setState(263);
				match(DECISIONANSWER);
				setState(264);
				match(NEWLINE);
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(265);
				match(T__15);
				setState(266);
				match(NEWLINE);
				setState(273); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(268); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(267);
						match(MESSAGE);
						}
						}
						setState(270); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==MESSAGE );
					setState(272);
					match(NEWLINE);
					}
					}
					setState(275); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==MESSAGE );
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
		"\u0004\u0001\u001f\u0118\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u0001"+
		"\u0016\b\u0001\u000b\u0001\f\u0001\u0017\u0001\u0001\u0004\u0001\u001b"+
		"\b\u0001\u000b\u0001\f\u0001\u001c\u0001\u0001\u0001\u0001\u0004\u0001"+
		"!\b\u0001\u000b\u0001\f\u0001\"\u0001\u0001\u0004\u0001&\b\u0001\u000b"+
		"\u0001\f\u0001\'\u0003\u0001*\b\u0001\u0001\u0001\u0001\u0001\u0004\u0001"+
		".\b\u0001\u000b\u0001\f\u0001/\u0001\u0001\u0004\u00013\b\u0001\u000b"+
		"\u0001\f\u00014\u0001\u0001\u0001\u0001\u0004\u00019\b\u0001\u000b\u0001"+
		"\f\u0001:\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u0002@\b\u0002\u000b"+
		"\u0002\f\u0002A\u0001\u0002\u0001\u0002\u0004\u0002F\b\u0002\u000b\u0002"+
		"\f\u0002G\u0001\u0002\u0004\u0002K\b\u0002\u000b\u0002\f\u0002L\u0001"+
		"\u0002\u0001\u0002\u0004\u0002Q\b\u0002\u000b\u0002\f\u0002R\u0001\u0002"+
		"\u0004\u0002V\b\u0002\u000b\u0002\f\u0002W\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u0002`\b\u0002\u000b"+
		"\u0002\f\u0002a\u0001\u0002\u0004\u0002e\b\u0002\u000b\u0002\f\u0002f"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003l\b\u0003\u000b\u0003"+
		"\f\u0003m\u0001\u0003\u0001\u0003\u0004\u0003r\b\u0003\u000b\u0003\f\u0003"+
		"s\u0001\u0003\u0004\u0003w\b\u0003\u000b\u0003\f\u0003x\u0001\u0003\u0001"+
		"\u0003\u0004\u0003}\b\u0003\u000b\u0003\f\u0003~\u0003\u0003\u0081\b\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003"+
		"\u0088\b\u0003\u000b\u0003\f\u0003\u0089\u0001\u0003\u0005\u0003\u008d"+
		"\b\u0003\n\u0003\f\u0003\u0090\t\u0003\u0003\u0003\u0092\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u0098\b\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004"+
		"\u0004\u00a0\b\u0004\u000b\u0004\f\u0004\u00a1\u0001\u0004\u0004\u0004"+
		"\u00a5\b\u0004\u000b\u0004\f\u0004\u00a6\u0001\u0004\u0003\u0004\u00aa"+
		"\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0004\u0004\u00b2\b\u0004\u000b\u0004\f\u0004\u00b3\u0001\u0004"+
		"\u0004\u0004\u00b7\b\u0004\u000b\u0004\f\u0004\u00b8\u0001\u0004\u0003"+
		"\u0004\u00bc\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0004\u0004\u00c4\b\u0004\u000b\u0004\f\u0004\u00c5"+
		"\u0001\u0004\u0004\u0004\u00c9\b\u0004\u000b\u0004\f\u0004\u00ca\u0001"+
		"\u0004\u0003\u0004\u00ce\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004\u00d4\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004\u00da\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004\u00e0\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u00e8\b\u0004\u000b\u0004\f"+
		"\u0004\u00e9\u0001\u0004\u0004\u0004\u00ed\b\u0004\u000b\u0004\f\u0004"+
		"\u00ee\u0001\u0004\u0003\u0004\u00f2\b\u0004\u0003\u0004\u00f4\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0004\u0005\u00f8\b\u0005\u000b\u0005\f\u0005"+
		"\u00f9\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0004\u0005\u0102\b\u0005\u000b\u0005\f\u0005\u0103\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0004\u0005\u010d\b\u0005\u000b\u0005\f\u0005\u010e\u0001\u0005\u0004"+
		"\u0005\u0112\b\u0005\u000b\u0005\f\u0005\u0113\u0003\u0005\u0116\b\u0005"+
		"\u0001\u0005\u0000\u0000\u0006\u0000\u0002\u0004\u0006\b\n\u0000\u0000"+
		"\u0147\u0000\f\u0001\u0000\u0000\u0000\u0002\u000e\u0001\u0000\u0000\u0000"+
		"\u0004<\u0001\u0000\u0000\u0000\u0006h\u0001\u0000\u0000\u0000\b\u00f3"+
		"\u0001\u0000\u0000\u0000\n\u0115\u0001\u0000\u0000\u0000\f\r\u0003\u0002"+
		"\u0001\u0000\r\u0001\u0001\u0000\u0000\u0000\u000e\u000f\u0005\u0001\u0000"+
		"\u0000\u000f\u0010\u0005\u001e\u0000\u0000\u0010\u0011\u0005\u0011\u0000"+
		"\u0000\u0011\u0012\u0005\u0002\u0000\u0000\u0012\u0013\u0005\u0011\u0000"+
		"\u0000\u0013\u0015\u0005\u0003\u0000\u0000\u0014\u0016\u0005\u001f\u0000"+
		"\u0000\u0015\u0014\u0001\u0000\u0000\u0000\u0016\u0017\u0001\u0000\u0000"+
		"\u0000\u0017\u0015\u0001\u0000\u0000\u0000\u0017\u0018\u0001\u0000\u0000"+
		"\u0000\u0018\u001a\u0001\u0000\u0000\u0000\u0019\u001b\u0005\u0011\u0000"+
		"\u0000\u001a\u0019\u0001\u0000\u0000\u0000\u001b\u001c\u0001\u0000\u0000"+
		"\u0000\u001c\u001a\u0001\u0000\u0000\u0000\u001c\u001d\u0001\u0000\u0000"+
		"\u0000\u001d)\u0001\u0000\u0000\u0000\u001e \u0005\u0004\u0000\u0000\u001f"+
		"!\u0005\u001f\u0000\u0000 \u001f\u0001\u0000\u0000\u0000!\"\u0001\u0000"+
		"\u0000\u0000\" \u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#%\u0001"+
		"\u0000\u0000\u0000$&\u0005\u0011\u0000\u0000%$\u0001\u0000\u0000\u0000"+
		"&\'\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000\'(\u0001\u0000"+
		"\u0000\u0000(*\u0001\u0000\u0000\u0000)\u001e\u0001\u0000\u0000\u0000"+
		")*\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+-\u0005\u0005\u0000"+
		"\u0000,.\u0005\u0011\u0000\u0000-,\u0001\u0000\u0000\u0000./\u0001\u0000"+
		"\u0000\u0000/-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u000002\u0001"+
		"\u0000\u0000\u000013\u0003\u0004\u0002\u000021\u0001\u0000\u0000\u0000"+
		"34\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u000045\u0001\u0000\u0000"+
		"\u000056\u0001\u0000\u0000\u000068\u0005\u0006\u0000\u000079\u0005\u001f"+
		"\u0000\u000087\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:8\u0001"+
		"\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000;\u0003\u0001\u0000\u0000"+
		"\u0000<=\u0005\u0001\u0000\u0000=?\u0005\u001e\u0000\u0000>@\u0005\u0011"+
		"\u0000\u0000?>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000A?\u0001"+
		"\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000"+
		"CE\u0005\u0007\u0000\u0000DF\u0005\u001f\u0000\u0000ED\u0001\u0000\u0000"+
		"\u0000FG\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000GH\u0001\u0000"+
		"\u0000\u0000HJ\u0001\u0000\u0000\u0000IK\u0005\u0011\u0000\u0000JI\u0001"+
		"\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000"+
		"LM\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000NP\u0005\b\u0000\u0000"+
		"OQ\u0005\u001f\u0000\u0000PO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000"+
		"\u0000RP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000SU\u0001\u0000"+
		"\u0000\u0000TV\u0005\u0011\u0000\u0000UT\u0001\u0000\u0000\u0000VW\u0001"+
		"\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000"+
		"XY\u0001\u0000\u0000\u0000YZ\u0005\u0012\u0000\u0000Z[\u0005\u0011\u0000"+
		"\u0000[\\\u0005\u0016\u0000\u0000\\]\u0005\u0011\u0000\u0000]_\u0005\t"+
		"\u0000\u0000^`\u0005\u0011\u0000\u0000_^\u0001\u0000\u0000\u0000`a\u0001"+
		"\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000"+
		"bd\u0001\u0000\u0000\u0000ce\u0003\u0006\u0003\u0000dc\u0001\u0000\u0000"+
		"\u0000ef\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000"+
		"\u0000\u0000g\u0005\u0001\u0000\u0000\u0000hi\u0005\u0001\u0000\u0000"+
		"ik\u0005\u001e\u0000\u0000jl\u0005\u0011\u0000\u0000kj\u0001\u0000\u0000"+
		"\u0000lm\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000mn\u0001\u0000"+
		"\u0000\u0000no\u0001\u0000\u0000\u0000oq\u0005\n\u0000\u0000pr\u0005\u001f"+
		"\u0000\u0000qp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000sq\u0001"+
		"\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tv\u0001\u0000\u0000\u0000"+
		"uw\u0005\u0011\u0000\u0000vu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000"+
		"\u0000xv\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000y\u0080\u0001"+
		"\u0000\u0000\u0000z|\u0005\u000b\u0000\u0000{}\u0005\u001f\u0000\u0000"+
		"|{\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000"+
		"\u0000~\u007f\u0001\u0000\u0000\u0000\u007f\u0081\u0001\u0000\u0000\u0000"+
		"\u0080z\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081"+
		"\u0082\u0001\u0000\u0000\u0000\u0082\u0083\u0003\b\u0004\u0000\u0083\u0084"+
		"\u0005\u0012\u0000\u0000\u0084\u0091\u0005\u0011\u0000\u0000\u0085\u0087"+
		"\u0005\f\u0000\u0000\u0086\u0088\u0005\u001f\u0000\u0000\u0087\u0086\u0001"+
		"\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u0087\u0001"+
		"\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008e\u0001"+
		"\u0000\u0000\u0000\u008b\u008d\u0005\u0011\u0000\u0000\u008c\u008b\u0001"+
		"\u0000\u0000\u0000\u008d\u0090\u0001\u0000\u0000\u0000\u008e\u008c\u0001"+
		"\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0092\u0001"+
		"\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091\u0085\u0001"+
		"\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0007\u0001"+
		"\u0000\u0000\u0000\u0093\u0094\u0005\r\u0000\u0000\u0094\u0095\u0005\u0015"+
		"\u0000\u0000\u0095\u0097\u0005\u0011\u0000\u0000\u0096\u0098\u0003\n\u0005"+
		"\u0000\u0097\u0096\u0001\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000"+
		"\u0000\u0098\u00f4\u0001\u0000\u0000\u0000\u0099\u009a\u0005\r\u0000\u0000"+
		"\u009a\u009b\u0005\u0018\u0000\u0000\u009b\u009c\u0005\u0011\u0000\u0000"+
		"\u009c\u009d\u0005\u000e\u0000\u0000\u009d\u00a4\u0005\u0011\u0000\u0000"+
		"\u009e\u00a0\u0005\u001f\u0000\u0000\u009f\u009e\u0001\u0000\u0000\u0000"+
		"\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000\u0000\u0000"+
		"\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a3\u00a5\u0005\u0011\u0000\u0000\u00a4\u009f\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00a9\u0001\u0000\u0000\u0000"+
		"\u00a8\u00aa\u0003\n\u0005\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00a9"+
		"\u00aa\u0001\u0000\u0000\u0000\u00aa\u00f4\u0001\u0000\u0000\u0000\u00ab"+
		"\u00ac\u0005\r\u0000\u0000\u00ac\u00ad\u0005\u001a\u0000\u0000\u00ad\u00ae"+
		"\u0005\u0011\u0000\u0000\u00ae\u00af\u0005\u000e\u0000\u0000\u00af\u00b6"+
		"\u0005\u0011\u0000\u0000\u00b0\u00b2\u0005\u001f\u0000\u0000\u00b1\u00b0"+
		"\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b1"+
		"\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4\u00b5"+
		"\u0001\u0000\u0000\u0000\u00b5\u00b7\u0005\u0011\u0000\u0000\u00b6\u00b1"+
		"\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00bb"+
		"\u0001\u0000\u0000\u0000\u00ba\u00bc\u0003\n\u0005\u0000\u00bb\u00ba\u0001"+
		"\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00f4\u0001"+
		"\u0000\u0000\u0000\u00bd\u00be\u0005\r\u0000\u0000\u00be\u00bf\u0005\u001b"+
		"\u0000\u0000\u00bf\u00c0\u0005\u0011\u0000\u0000\u00c0\u00c1\u0005\u000e"+
		"\u0000\u0000\u00c1\u00c8\u0005\u0011\u0000\u0000\u00c2\u00c4\u0005\u001f"+
		"\u0000\u0000\u00c3\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00c9\u0005\u0011"+
		"\u0000\u0000\u00c8\u00c3\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000"+
		"\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000"+
		"\u0000\u0000\u00cb\u00cd\u0001\u0000\u0000\u0000\u00cc\u00ce\u0003\n\u0005"+
		"\u0000\u00cd\u00cc\u0001\u0000\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000"+
		"\u0000\u00ce\u00f4\u0001\u0000\u0000\u0000\u00cf\u00d0\u0005\r\u0000\u0000"+
		"\u00d0\u00d1\u0005\u0017\u0000\u0000\u00d1\u00d3\u0005\u0011\u0000\u0000"+
		"\u00d2\u00d4\u0003\n\u0005\u0000\u00d3\u00d2\u0001\u0000\u0000\u0000\u00d3"+
		"\u00d4\u0001\u0000\u0000\u0000\u00d4\u00f4\u0001\u0000\u0000\u0000\u00d5"+
		"\u00d6\u0005\r\u0000\u0000\u00d6\u00d7\u0005\u001c\u0000\u0000\u00d7\u00d9"+
		"\u0005\u0011\u0000\u0000\u00d8\u00da\u0003\n\u0005\u0000\u00d9\u00d8\u0001"+
		"\u0000\u0000\u0000\u00d9\u00da\u0001\u0000\u0000\u0000\u00da\u00f4\u0001"+
		"\u0000\u0000\u0000\u00db\u00dc\u0005\r\u0000\u0000\u00dc\u00dd\u0005\u0013"+
		"\u0000\u0000\u00dd\u00df\u0005\u0011\u0000\u0000\u00de\u00e0\u0003\n\u0005"+
		"\u0000\u00df\u00de\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000"+
		"\u0000\u00e0\u00f4\u0001\u0000\u0000\u0000\u00e1\u00e2\u0005\r\u0000\u0000"+
		"\u00e2\u00e3\u0005\u0019\u0000\u0000\u00e3\u00e4\u0005\u0011\u0000\u0000"+
		"\u00e4\u00e5\u0005\u000f\u0000\u0000\u00e5\u00ec\u0005\u0011\u0000\u0000"+
		"\u00e6\u00e8\u0005\u001f\u0000\u0000\u00e7\u00e6\u0001\u0000\u0000\u0000"+
		"\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\u00e7\u0001\u0000\u0000\u0000"+
		"\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea\u00eb\u0001\u0000\u0000\u0000"+
		"\u00eb\u00ed\u0005\u0011\u0000\u0000\u00ec\u00e7\u0001\u0000\u0000\u0000"+
		"\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000"+
		"\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef\u00f1\u0001\u0000\u0000\u0000"+
		"\u00f0\u00f2\u0003\n\u0005\u0000\u00f1\u00f0\u0001\u0000\u0000\u0000\u00f1"+
		"\u00f2\u0001\u0000\u0000\u0000\u00f2\u00f4\u0001\u0000\u0000\u0000\u00f3"+
		"\u0093\u0001\u0000\u0000\u0000\u00f3\u0099\u0001\u0000\u0000\u0000\u00f3"+
		"\u00ab\u0001\u0000\u0000\u0000\u00f3\u00bd\u0001\u0000\u0000\u0000\u00f3"+
		"\u00cf\u0001\u0000\u0000\u0000\u00f3\u00d5\u0001\u0000\u0000\u0000\u00f3"+
		"\u00db\u0001\u0000\u0000\u0000\u00f3\u00e1\u0001\u0000\u0000\u0000\u00f4"+
		"\t\u0001\u0000\u0000\u0000\u00f5\u00f7\u0005\u0010\u0000\u0000\u00f6\u00f8"+
		"\u0005\u001f\u0000\u0000\u00f7\u00f6\u0001\u0000\u0000\u0000\u00f8\u00f9"+
		"\u0001\u0000\u0000\u0000\u00f9\u00f7\u0001\u0000\u0000\u0000\u00f9\u00fa"+
		"\u0001\u0000\u0000\u0000\u00fa\u00fb\u0001\u0000\u0000\u0000\u00fb\u0116"+
		"\u0005\u0011\u0000\u0000\u00fc\u00fd\u0005\u0010\u0000\u0000\u00fd\u00fe"+
		"\u0005\u001d\u0000\u0000\u00fe\u0116\u0005\u0011\u0000\u0000\u00ff\u0101"+
		"\u0005\u0010\u0000\u0000\u0100\u0102\u0005\u001d\u0000\u0000\u0101\u0100"+
		"\u0001\u0000\u0000\u0000\u0102\u0103\u0001\u0000\u0000\u0000\u0103\u0101"+
		"\u0001\u0000\u0000\u0000\u0103\u0104\u0001\u0000\u0000\u0000\u0104\u0105"+
		"\u0001\u0000\u0000\u0000\u0105\u0116\u0005\u0011\u0000\u0000\u0106\u0107"+
		"\u0005\u0010\u0000\u0000\u0107\u0108\u0005\u0014\u0000\u0000\u0108\u0116"+
		"\u0005\u0011\u0000\u0000\u0109\u010a\u0005\u0010\u0000\u0000\u010a\u0111"+
		"\u0005\u0011\u0000\u0000\u010b\u010d\u0005\u001f\u0000\u0000\u010c\u010b"+
		"\u0001\u0000\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000\u010e\u010c"+
		"\u0001\u0000\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000\u010f\u0110"+
		"\u0001\u0000\u0000\u0000\u0110\u0112\u0005\u0011\u0000\u0000\u0111\u010c"+
		"\u0001\u0000\u0000\u0000\u0112\u0113\u0001\u0000\u0000\u0000\u0113\u0111"+
		"\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000\u0000\u0000\u0114\u0116"+
		"\u0001\u0000\u0000\u0000\u0115\u00f5\u0001\u0000\u0000\u0000\u0115\u00fc"+
		"\u0001\u0000\u0000\u0000\u0115\u00ff\u0001\u0000\u0000\u0000\u0115\u0106"+
		"\u0001\u0000\u0000\u0000\u0115\u0109\u0001\u0000\u0000\u0000\u0116\u000b"+
		"\u0001\u0000\u0000\u0000-\u0017\u001c\"\')/4:AGLRWafmsx~\u0080\u0089\u008e"+
		"\u0091\u0097\u00a1\u00a6\u00a9\u00b3\u00b8\u00bb\u00c5\u00ca\u00cd\u00d3"+
		"\u00d9\u00df\u00e9\u00ee\u00f1\u00f3\u00f9\u0103\u010e\u0113\u0115";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}