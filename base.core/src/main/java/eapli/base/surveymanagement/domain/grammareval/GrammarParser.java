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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, NEWLINE=19, OBLIGATORINESS=20, DECISION=21, DECISIONANSWER=22, 
		FREETEXT=23, REP=24, NUMERIC=25, CHOICE=26, SORT=27, CHOICEINPUT=28, MULTIPLECHOICEINPUT=29, 
		SCALINGOPTIONS=30, INT=31, ID=32, WORD=33, WS=34;
	public static final int
		RULE_prog = 0, RULE_survey = 1, RULE_section = 2, RULE_question = 3, RULE_questiontype = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "survey", "section", "question", "questiontype"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'ID: '", "'Survey'", "'Title: '", "'Welcome Message:'", "'List of Sections:'", 
			"'Final Message: '", "'Section Title: '", "'Description:'", "'Content:'", 
			"'Question: '", "'Instruction:'", "'Extra Information: '", "'Question Type: '", 
			"'Answer: '", "'Possible Answers:'", "'ANSWER: '", "'Options:'", "'Answer:'", 
			null, null, "'Decision'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "NEWLINE", "OBLIGATORINESS", 
			"DECISION", "DECISIONANSWER", "FREETEXT", "REP", "NUMERIC", "CHOICE", 
			"SORT", "CHOICEINPUT", "MULTIPLECHOICEINPUT", "SCALINGOPTIONS", "INT", 
			"ID", "WORD", "WS"
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
	public static class SurveystructureContext extends SurveyContext {
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public List<TerminalNode> WORD() { return getTokens(GrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(GrammarParser.WORD, i);
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
			setState(12);
			match(T__0);
			setState(13);
			match(ID);
			setState(14);
			match(NEWLINE);
			setState(15);
			match(T__1);
			setState(16);
			match(NEWLINE);
			setState(17);
			match(T__2);
			setState(19); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(18);
				match(WORD);
				}
				}
				setState(21); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(24); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(23);
				match(NEWLINE);
				}
				}
				setState(26); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(28);
			match(T__3);
			setState(30); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(29);
				match(WORD);
				}
				}
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				match(NEWLINE);
				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(39);
			match(T__4);
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				match(NEWLINE);
				}
				}
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(46); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(45);
				section();
				}
				}
				setState(48); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(50);
			match(T__5);
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
			int _alt;
			_localctx = new SectionsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__0);
			setState(57);
			match(ID);
			setState(59); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(58);
				match(NEWLINE);
				}
				}
				setState(61); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(63);
			match(T__6);
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(64);
				match(WORD);
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(70); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(69);
				match(NEWLINE);
				}
				}
				setState(72); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(74);
			match(T__7);
			setState(76); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(75);
				match(WORD);
				}
				}
				setState(78); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(81); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(80);
				match(NEWLINE);
				}
				}
				setState(83); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(85);
			match(OBLIGATORINESS);
			setState(86);
			match(NEWLINE);
			setState(87);
			match(REP);
			setState(88);
			match(NEWLINE);
			setState(89);
			match(T__8);
			setState(91); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(90);
				match(NEWLINE);
				}
				}
				setState(93); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(96); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(95);
					question();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(98); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
			setState(100);
			match(T__0);
			setState(101);
			match(ID);
			setState(103); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(102);
				match(NEWLINE);
				}
				}
				setState(105); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(107);
			match(T__9);
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(108);
				match(WORD);
				}
				}
				setState(111); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(114); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(113);
				match(NEWLINE);
				}
				}
				setState(116); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(118);
				match(T__10);
				setState(120); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(119);
					match(WORD);
					}
					}
					setState(122); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WORD );
				}
			}

			setState(126);
			questiontype();
			setState(127);
			match(OBLIGATORINESS);
			setState(128);
			match(NEWLINE);
			setState(129);
			match(T__11);
			setState(131); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(130);
				match(WORD);
				}
				}
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(135);
				match(NEWLINE);
				}
				}
				setState(140);
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

	public static class QuestiontypeContext extends ParserRuleContext {
		public TerminalNode FREETEXT() { return getToken(GrammarParser.FREETEXT, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public List<TerminalNode> WORD() { return getTokens(GrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(GrammarParser.WORD, i);
		}
		public TerminalNode CHOICE() { return getToken(GrammarParser.CHOICE, 0); }
		public List<TerminalNode> INT() { return getTokens(GrammarParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(GrammarParser.INT, i);
		}
		public TerminalNode CHOICEINPUT() { return getToken(GrammarParser.CHOICEINPUT, 0); }
		public TerminalNode MULTIPLECHOICEINPUT() { return getToken(GrammarParser.MULTIPLECHOICEINPUT, 0); }
		public TerminalNode NUMERIC() { return getToken(GrammarParser.NUMERIC, 0); }
		public TerminalNode SCALINGOPTIONS() { return getToken(GrammarParser.SCALINGOPTIONS, 0); }
		public TerminalNode DECISION() { return getToken(GrammarParser.DECISION, 0); }
		public TerminalNode DECISIONANSWER() { return getToken(GrammarParser.DECISIONANSWER, 0); }
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
			setState(278);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				match(T__12);
				setState(142);
				match(FREETEXT);
				setState(143);
				match(NEWLINE);
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(144);
					match(T__13);
					setState(146); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(145);
						match(WORD);
						}
						}
						setState(148); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(150);
					match(NEWLINE);
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
				match(CHOICE);
				setState(155);
				match(NEWLINE);
				setState(156);
				match(T__14);
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
						match(WORD);
						}
						}
						setState(161); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(163);
					match(NEWLINE);
					}
					}
					setState(166); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WORD );
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(168);
					match(T__13);
					setState(169);
					match(INT);
					setState(170);
					match(NEWLINE);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(173);
				match(T__12);
				setState(174);
				match(CHOICEINPUT);
				setState(175);
				match(NEWLINE);
				setState(176);
				match(T__14);
				setState(177);
				match(NEWLINE);
				setState(184); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(179); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(178);
						match(WORD);
						}
						}
						setState(181); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(183);
					match(NEWLINE);
					}
					}
					setState(186); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WORD );
				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(188);
					match(T__13);
					setState(189);
					match(INT);
					setState(190);
					match(NEWLINE);
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(193);
				match(T__12);
				setState(194);
				match(MULTIPLECHOICEINPUT);
				setState(195);
				match(NEWLINE);
				setState(196);
				match(T__14);
				setState(197);
				match(NEWLINE);
				setState(204); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(199); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(198);
						match(WORD);
						}
						}
						setState(201); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(203);
					match(NEWLINE);
					}
					}
					setState(206); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WORD );
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(208);
					match(T__13);
					setState(210); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(209);
						match(WORD);
						}
						}
						setState(212); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(214);
					match(NEWLINE);
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(217);
				match(T__12);
				setState(218);
				match(NUMERIC);
				setState(219);
				match(NEWLINE);
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(220);
					match(T__15);
					setState(222); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(221);
						match(INT);
						}
						}
						setState(224); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==INT );
					setState(226);
					match(NEWLINE);
					}
				}

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(229);
				match(T__12);
				setState(230);
				match(SCALINGOPTIONS);
				setState(231);
				match(NEWLINE);
				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(232);
					match(T__13);
					setState(234); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(233);
						match(INT);
						}
						}
						setState(236); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==INT );
					setState(238);
					match(NEWLINE);
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(241);
				match(T__12);
				setState(242);
				match(DECISION);
				setState(243);
				match(NEWLINE);
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(244);
					match(T__13);
					setState(245);
					match(DECISIONANSWER);
					setState(246);
					match(NEWLINE);
					}
				}

				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(249);
				match(T__12);
				setState(250);
				match(SORT);
				setState(251);
				match(NEWLINE);
				setState(252);
				match(T__16);
				setState(253);
				match(NEWLINE);
				setState(260); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(255); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(254);
						match(WORD);
						}
						}
						setState(257); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(259);
					match(NEWLINE);
					}
					}
					setState(262); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WORD );
				setState(276);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__17) {
					{
					setState(264);
					match(T__17);
					setState(265);
					match(NEWLINE);
					setState(272); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(267); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(266);
							match(WORD);
							}
							}
							setState(269); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==WORD );
						setState(271);
						match(NEWLINE);
						}
						}
						setState(274); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
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
		"\u0004\u0001\"\u0119\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0004\u0001\u0014\b\u0001\u000b\u0001\f"+
		"\u0001\u0015\u0001\u0001\u0004\u0001\u0019\b\u0001\u000b\u0001\f\u0001"+
		"\u001a\u0001\u0001\u0001\u0001\u0004\u0001\u001f\b\u0001\u000b\u0001\f"+
		"\u0001 \u0001\u0001\u0004\u0001$\b\u0001\u000b\u0001\f\u0001%\u0001\u0001"+
		"\u0001\u0001\u0004\u0001*\b\u0001\u000b\u0001\f\u0001+\u0001\u0001\u0004"+
		"\u0001/\b\u0001\u000b\u0001\f\u00010\u0001\u0001\u0001\u0001\u0004\u0001"+
		"5\b\u0001\u000b\u0001\f\u00016\u0001\u0002\u0001\u0002\u0001\u0002\u0004"+
		"\u0002<\b\u0002\u000b\u0002\f\u0002=\u0001\u0002\u0001\u0002\u0004\u0002"+
		"B\b\u0002\u000b\u0002\f\u0002C\u0001\u0002\u0004\u0002G\b\u0002\u000b"+
		"\u0002\f\u0002H\u0001\u0002\u0001\u0002\u0004\u0002M\b\u0002\u000b\u0002"+
		"\f\u0002N\u0001\u0002\u0004\u0002R\b\u0002\u000b\u0002\f\u0002S\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0004"+
		"\u0002\\\b\u0002\u000b\u0002\f\u0002]\u0001\u0002\u0004\u0002a\b\u0002"+
		"\u000b\u0002\f\u0002b\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003"+
		"h\b\u0003\u000b\u0003\f\u0003i\u0001\u0003\u0001\u0003\u0004\u0003n\b"+
		"\u0003\u000b\u0003\f\u0003o\u0001\u0003\u0004\u0003s\b\u0003\u000b\u0003"+
		"\f\u0003t\u0001\u0003\u0001\u0003\u0004\u0003y\b\u0003\u000b\u0003\f\u0003"+
		"z\u0003\u0003}\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0004\u0003\u0084\b\u0003\u000b\u0003\f\u0003\u0085\u0001"+
		"\u0003\u0005\u0003\u0089\b\u0003\n\u0003\f\u0003\u008c\t\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u0093\b\u0004"+
		"\u000b\u0004\f\u0004\u0094\u0001\u0004\u0003\u0004\u0098\b\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004"+
		"\u0004\u00a0\b\u0004\u000b\u0004\f\u0004\u00a1\u0001\u0004\u0004\u0004"+
		"\u00a5\b\u0004\u000b\u0004\f\u0004\u00a6\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004\u00ac\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u00b4\b\u0004\u000b\u0004\f"+
		"\u0004\u00b5\u0001\u0004\u0004\u0004\u00b9\b\u0004\u000b\u0004\f\u0004"+
		"\u00ba\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00c0\b\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004"+
		"\u0004\u00c8\b\u0004\u000b\u0004\f\u0004\u00c9\u0001\u0004\u0004\u0004"+
		"\u00cd\b\u0004\u000b\u0004\f\u0004\u00ce\u0001\u0004\u0001\u0004\u0004"+
		"\u0004\u00d3\b\u0004\u000b\u0004\f\u0004\u00d4\u0001\u0004\u0003\u0004"+
		"\u00d8\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0004\u0004\u00df\b\u0004\u000b\u0004\f\u0004\u00e0\u0001\u0004\u0003"+
		"\u0004\u00e4\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0004\u0004\u00eb\b\u0004\u000b\u0004\f\u0004\u00ec\u0001\u0004"+
		"\u0003\u0004\u00f0\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004\u00f8\b\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u0100\b\u0004"+
		"\u000b\u0004\f\u0004\u0101\u0001\u0004\u0004\u0004\u0105\b\u0004\u000b"+
		"\u0004\f\u0004\u0106\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u010c"+
		"\b\u0004\u000b\u0004\f\u0004\u010d\u0001\u0004\u0004\u0004\u0111\b\u0004"+
		"\u000b\u0004\f\u0004\u0112\u0003\u0004\u0115\b\u0004\u0003\u0004\u0117"+
		"\b\u0004\u0001\u0004\u0000\u0000\u0005\u0000\u0002\u0004\u0006\b\u0000"+
		"\u0000\u0145\u0000\n\u0001\u0000\u0000\u0000\u0002\f\u0001\u0000\u0000"+
		"\u0000\u00048\u0001\u0000\u0000\u0000\u0006d\u0001\u0000\u0000\u0000\b"+
		"\u0116\u0001\u0000\u0000\u0000\n\u000b\u0003\u0002\u0001\u0000\u000b\u0001"+
		"\u0001\u0000\u0000\u0000\f\r\u0005\u0001\u0000\u0000\r\u000e\u0005 \u0000"+
		"\u0000\u000e\u000f\u0005\u0013\u0000\u0000\u000f\u0010\u0005\u0002\u0000"+
		"\u0000\u0010\u0011\u0005\u0013\u0000\u0000\u0011\u0013\u0005\u0003\u0000"+
		"\u0000\u0012\u0014\u0005!\u0000\u0000\u0013\u0012\u0001\u0000\u0000\u0000"+
		"\u0014\u0015\u0001\u0000\u0000\u0000\u0015\u0013\u0001\u0000\u0000\u0000"+
		"\u0015\u0016\u0001\u0000\u0000\u0000\u0016\u0018\u0001\u0000\u0000\u0000"+
		"\u0017\u0019\u0005\u0013\u0000\u0000\u0018\u0017\u0001\u0000\u0000\u0000"+
		"\u0019\u001a\u0001\u0000\u0000\u0000\u001a\u0018\u0001\u0000\u0000\u0000"+
		"\u001a\u001b\u0001\u0000\u0000\u0000\u001b\u001c\u0001\u0000\u0000\u0000"+
		"\u001c\u001e\u0005\u0004\u0000\u0000\u001d\u001f\u0005!\u0000\u0000\u001e"+
		"\u001d\u0001\u0000\u0000\u0000\u001f \u0001\u0000\u0000\u0000 \u001e\u0001"+
		"\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!#\u0001\u0000\u0000\u0000"+
		"\"$\u0005\u0013\u0000\u0000#\"\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000"+
		"\u0000%#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&\'\u0001\u0000"+
		"\u0000\u0000\')\u0005\u0005\u0000\u0000(*\u0005\u0013\u0000\u0000)(\u0001"+
		"\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000"+
		"+,\u0001\u0000\u0000\u0000,.\u0001\u0000\u0000\u0000-/\u0003\u0004\u0002"+
		"\u0000.-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u00000.\u0001\u0000"+
		"\u0000\u000001\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u000024\u0005"+
		"\u0006\u0000\u000035\u0005!\u0000\u000043\u0001\u0000\u0000\u000056\u0001"+
		"\u0000\u0000\u000064\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u0000"+
		"7\u0003\u0001\u0000\u0000\u000089\u0005\u0001\u0000\u00009;\u0005 \u0000"+
		"\u0000:<\u0005\u0013\u0000\u0000;:\u0001\u0000\u0000\u0000<=\u0001\u0000"+
		"\u0000\u0000=;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>?\u0001"+
		"\u0000\u0000\u0000?A\u0005\u0007\u0000\u0000@B\u0005!\u0000\u0000A@\u0001"+
		"\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000"+
		"CD\u0001\u0000\u0000\u0000DF\u0001\u0000\u0000\u0000EG\u0005\u0013\u0000"+
		"\u0000FE\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000HF\u0001\u0000"+
		"\u0000\u0000HI\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000JL\u0005"+
		"\b\u0000\u0000KM\u0005!\u0000\u0000LK\u0001\u0000\u0000\u0000MN\u0001"+
		"\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000"+
		"OQ\u0001\u0000\u0000\u0000PR\u0005\u0013\u0000\u0000QP\u0001\u0000\u0000"+
		"\u0000RS\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001\u0000"+
		"\u0000\u0000TU\u0001\u0000\u0000\u0000UV\u0005\u0014\u0000\u0000VW\u0005"+
		"\u0013\u0000\u0000WX\u0005\u0018\u0000\u0000XY\u0005\u0013\u0000\u0000"+
		"Y[\u0005\t\u0000\u0000Z\\\u0005\u0013\u0000\u0000[Z\u0001\u0000\u0000"+
		"\u0000\\]\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001\u0000"+
		"\u0000\u0000^`\u0001\u0000\u0000\u0000_a\u0003\u0006\u0003\u0000`_\u0001"+
		"\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000"+
		"bc\u0001\u0000\u0000\u0000c\u0005\u0001\u0000\u0000\u0000de\u0005\u0001"+
		"\u0000\u0000eg\u0005 \u0000\u0000fh\u0005\u0013\u0000\u0000gf\u0001\u0000"+
		"\u0000\u0000hi\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000ij\u0001"+
		"\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000km\u0005\n\u0000\u0000ln\u0005"+
		"!\u0000\u0000ml\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000om\u0001"+
		"\u0000\u0000\u0000op\u0001\u0000\u0000\u0000pr\u0001\u0000\u0000\u0000"+
		"qs\u0005\u0013\u0000\u0000rq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000"+
		"\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000u|\u0001\u0000"+
		"\u0000\u0000vx\u0005\u000b\u0000\u0000wy\u0005!\u0000\u0000xw\u0001\u0000"+
		"\u0000\u0000yz\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000z{\u0001"+
		"\u0000\u0000\u0000{}\u0001\u0000\u0000\u0000|v\u0001\u0000\u0000\u0000"+
		"|}\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~\u007f\u0003\b\u0004"+
		"\u0000\u007f\u0080\u0005\u0014\u0000\u0000\u0080\u0081\u0005\u0013\u0000"+
		"\u0000\u0081\u0083\u0005\f\u0000\u0000\u0082\u0084\u0005!\u0000\u0000"+
		"\u0083\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000"+
		"\u0085\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000"+
		"\u0086\u008a\u0001\u0000\u0000\u0000\u0087\u0089\u0005\u0013\u0000\u0000"+
		"\u0088\u0087\u0001\u0000\u0000\u0000\u0089\u008c\u0001\u0000\u0000\u0000"+
		"\u008a\u0088\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000"+
		"\u008b\u0007\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000"+
		"\u008d\u008e\u0005\r\u0000\u0000\u008e\u008f\u0005\u0017\u0000\u0000\u008f"+
		"\u0097\u0005\u0013\u0000\u0000\u0090\u0092\u0005\u000e\u0000\u0000\u0091"+
		"\u0093\u0005!\u0000\u0000\u0092\u0091\u0001\u0000\u0000\u0000\u0093\u0094"+
		"\u0001\u0000\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0095"+
		"\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0098"+
		"\u0005\u0013\u0000\u0000\u0097\u0090\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0001\u0000\u0000\u0000\u0098\u0117\u0001\u0000\u0000\u0000\u0099\u009a"+
		"\u0005\r\u0000\u0000\u009a\u009b\u0005\u001a\u0000\u0000\u009b\u009c\u0005"+
		"\u0013\u0000\u0000\u009c\u009d\u0005\u000f\u0000\u0000\u009d\u00a4\u0005"+
		"\u0013\u0000\u0000\u009e\u00a0\u0005!\u0000\u0000\u009f\u009e\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000"+
		"\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a3\u00a5\u0005\u0013\u0000\u0000\u00a4\u009f\u0001\u0000"+
		"\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00ab\u0001\u0000"+
		"\u0000\u0000\u00a8\u00a9\u0005\u000e\u0000\u0000\u00a9\u00aa\u0005\u001f"+
		"\u0000\u0000\u00aa\u00ac\u0005\u0013\u0000\u0000\u00ab\u00a8\u0001\u0000"+
		"\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u0117\u0001\u0000"+
		"\u0000\u0000\u00ad\u00ae\u0005\r\u0000\u0000\u00ae\u00af\u0005\u001c\u0000"+
		"\u0000\u00af\u00b0\u0005\u0013\u0000\u0000\u00b0\u00b1\u0005\u000f\u0000"+
		"\u0000\u00b1\u00b8\u0005\u0013\u0000\u0000\u00b2\u00b4\u0005!\u0000\u0000"+
		"\u00b3\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b9\u0005\u0013\u0000\u0000"+
		"\u00b8\u00b3\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000"+
		"\u00ba\u00b8\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bf\u0001\u0000\u0000\u0000\u00bc\u00bd\u0005\u000e\u0000\u0000"+
		"\u00bd\u00be\u0005\u001f\u0000\u0000\u00be\u00c0\u0005\u0013\u0000\u0000"+
		"\u00bf\u00bc\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c0\u0117\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005\r\u0000\u0000\u00c2"+
		"\u00c3\u0005\u001d\u0000\u0000\u00c3\u00c4\u0005\u0013\u0000\u0000\u00c4"+
		"\u00c5\u0005\u000f\u0000\u0000\u00c5\u00cc\u0005\u0013\u0000\u0000\u00c6"+
		"\u00c8\u0005!\u0000\u0000\u00c7\u00c6\u0001\u0000\u0000\u0000\u00c8\u00c9"+
		"\u0001\u0000\u0000\u0000\u00c9\u00c7\u0001\u0000\u0000\u0000\u00c9\u00ca"+
		"\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb\u00cd"+
		"\u0005\u0013\u0000\u0000\u00cc\u00c7\u0001\u0000\u0000\u0000\u00cd\u00ce"+
		"\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000\u00ce\u00cf"+
		"\u0001\u0000\u0000\u0000\u00cf\u00d7\u0001\u0000\u0000\u0000\u00d0\u00d2"+
		"\u0005\u000e\u0000\u0000\u00d1\u00d3\u0005!\u0000\u0000\u00d2\u00d1\u0001"+
		"\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d4\u00d2\u0001"+
		"\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d6\u0001"+
		"\u0000\u0000\u0000\u00d6\u00d8\u0005\u0013\u0000\u0000\u00d7\u00d0\u0001"+
		"\u0000\u0000\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000\u00d8\u0117\u0001"+
		"\u0000\u0000\u0000\u00d9\u00da\u0005\r\u0000\u0000\u00da\u00db\u0005\u0019"+
		"\u0000\u0000\u00db\u00e3\u0005\u0013\u0000\u0000\u00dc\u00de\u0005\u0010"+
		"\u0000\u0000\u00dd\u00df\u0005\u001f\u0000\u0000\u00de\u00dd\u0001\u0000"+
		"\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u00de\u0001\u0000"+
		"\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000"+
		"\u0000\u0000\u00e2\u00e4\u0005\u0013\u0000\u0000\u00e3\u00dc\u0001\u0000"+
		"\u0000\u0000\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4\u0117\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e6\u0005\r\u0000\u0000\u00e6\u00e7\u0005\u001e\u0000"+
		"\u0000\u00e7\u00ef\u0005\u0013\u0000\u0000\u00e8\u00ea\u0005\u000e\u0000"+
		"\u0000\u00e9\u00eb\u0005\u001f\u0000\u0000\u00ea\u00e9\u0001\u0000\u0000"+
		"\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec\u00ea\u0001\u0000\u0000"+
		"\u0000\u00ec\u00ed\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000"+
		"\u0000\u00ee\u00f0\u0005\u0013\u0000\u0000\u00ef\u00e8\u0001\u0000\u0000"+
		"\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0\u0117\u0001\u0000\u0000"+
		"\u0000\u00f1\u00f2\u0005\r\u0000\u0000\u00f2\u00f3\u0005\u0015\u0000\u0000"+
		"\u00f3\u00f7\u0005\u0013\u0000\u0000\u00f4\u00f5\u0005\u000e\u0000\u0000"+
		"\u00f5\u00f6\u0005\u0016\u0000\u0000\u00f6\u00f8\u0005\u0013\u0000\u0000"+
		"\u00f7\u00f4\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000"+
		"\u00f8\u0117\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005\r\u0000\u0000\u00fa"+
		"\u00fb\u0005\u001b\u0000\u0000\u00fb\u00fc\u0005\u0013\u0000\u0000\u00fc"+
		"\u00fd\u0005\u0011\u0000\u0000\u00fd\u0104\u0005\u0013\u0000\u0000\u00fe"+
		"\u0100\u0005!\u0000\u0000\u00ff\u00fe\u0001\u0000\u0000\u0000\u0100\u0101"+
		"\u0001\u0000\u0000\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0101\u0102"+
		"\u0001\u0000\u0000\u0000\u0102\u0103\u0001\u0000\u0000\u0000\u0103\u0105"+
		"\u0005\u0013\u0000\u0000\u0104\u00ff\u0001\u0000\u0000\u0000\u0105\u0106"+
		"\u0001\u0000\u0000\u0000\u0106\u0104\u0001\u0000\u0000\u0000\u0106\u0107"+
		"\u0001\u0000\u0000\u0000\u0107\u0114\u0001\u0000\u0000\u0000\u0108\u0109"+
		"\u0005\u0012\u0000\u0000\u0109\u0110\u0005\u0013\u0000\u0000\u010a\u010c"+
		"\u0005!\u0000\u0000\u010b\u010a\u0001\u0000\u0000\u0000\u010c\u010d\u0001"+
		"\u0000\u0000\u0000\u010d\u010b\u0001\u0000\u0000\u0000\u010d\u010e\u0001"+
		"\u0000\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000\u010f\u0111\u0005"+
		"\u0013\u0000\u0000\u0110\u010b\u0001\u0000\u0000\u0000\u0111\u0112\u0001"+
		"\u0000\u0000\u0000\u0112\u0110\u0001\u0000\u0000\u0000\u0112\u0113\u0001"+
		"\u0000\u0000\u0000\u0113\u0115\u0001\u0000\u0000\u0000\u0114\u0108\u0001"+
		"\u0000\u0000\u0000\u0114\u0115\u0001\u0000\u0000\u0000\u0115\u0117\u0001"+
		"\u0000\u0000\u0000\u0116\u008d\u0001\u0000\u0000\u0000\u0116\u0099\u0001"+
		"\u0000\u0000\u0000\u0116\u00ad\u0001\u0000\u0000\u0000\u0116\u00c1\u0001"+
		"\u0000\u0000\u0000\u0116\u00d9\u0001\u0000\u0000\u0000\u0116\u00e5\u0001"+
		"\u0000\u0000\u0000\u0116\u00f1\u0001\u0000\u0000\u0000\u0116\u00f9\u0001"+
		"\u0000\u0000\u0000\u0117\t\u0001\u0000\u0000\u0000,\u0015\u001a %+06="+
		"CHNS]biotz|\u0085\u008a\u0094\u0097\u00a1\u00a6\u00ab\u00b5\u00ba\u00bf"+
		"\u00c9\u00ce\u00d4\u00d7\u00e0\u00e3\u00ec\u00ef\u00f7\u0101\u0106\u010d"+
		"\u0112\u0114\u0116";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}