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
		T__9=10, NEWLINE=11, OBLIGATORINESS=12, DECISION=13, TEXT=14, REP=15, 
		NUM=16, CHOICE=17, SORT=18, CHOICEINPUT=19, INT=20, ID=21, WORD=22, WS=23;
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
			null, "'Survey: '", "'ID: '", "'Final Message: '", "'Section: '", "'Content:'", 
			"'Question: '", "'Extra Information: '", "'Question Type: '", "'Answer: '", 
			"'Possible Answers:'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "NEWLINE", 
			"OBLIGATORINESS", "DECISION", "TEXT", "REP", "NUM", "CHOICE", "SORT", 
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
	public static class SurveystructureContext extends SurveyContext {
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
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
			setState(24);
			match(ID);
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
			setState(31); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(30);
				section();
				}
				}
				setState(33); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 );
			setState(35);
			match(T__2);
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(36);
				match(WORD);
				}
				}
				setState(39); 
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
			_localctx = new SectionsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(T__3);
			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42);
				match(WORD);
				}
				}
				setState(45); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(48); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(47);
				match(NEWLINE);
				}
				}
				setState(50); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(52);
			match(T__1);
			setState(53);
			match(ID);
			setState(55); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(54);
				match(NEWLINE);
				}
				}
				setState(57); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(59);
			match(OBLIGATORINESS);
			setState(60);
			match(NEWLINE);
			setState(61);
			match(REP);
			setState(62);
			match(NEWLINE);
			setState(63);
			match(T__4);
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
			} while ( _la==T__5 );
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
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OBLIGATORINESS() { return getToken(GrammarParser.OBLIGATORINESS, 0); }
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
			match(T__5);
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
			setState(78);
			match(ID);
			setState(79);
			match(NEWLINE);
			setState(80);
			type();
			setState(81);
			match(OBLIGATORINESS);
			setState(82);
			match(NEWLINE);
			setState(83);
			match(T__6);
			setState(85); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(84);
				match(WORD);
				}
				}
				setState(87); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(89);
				match(NEWLINE);
				}
				}
				setState(94);
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
		public TerminalNode TEXT() { return getToken(GrammarParser.TEXT, 0); }
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
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				match(T__7);
				setState(96);
				match(TEXT);
				setState(97);
				match(NEWLINE);
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(98);
					match(T__8);
					setState(100); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(99);
						match(WORD);
						}
						}
						setState(102); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(104);
					match(NEWLINE);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				match(T__7);
				setState(108);
				match(NUM);
				setState(109);
				match(NEWLINE);
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(110);
					match(T__8);
					setState(112); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(111);
						match(INT);
						}
						}
						setState(114); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==INT );
					setState(116);
					match(NEWLINE);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(119);
				match(T__7);
				setState(120);
				match(CHOICE);
				setState(121);
				match(NEWLINE);
				setState(122);
				match(T__9);
				setState(123);
				match(NEWLINE);
				setState(130); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(125); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(124);
						match(WORD);
						}
						}
						setState(127); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(129);
					match(NEWLINE);
					}
					}
					setState(132); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WORD );
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(134);
					match(T__8);
					setState(135);
					match(INT);
					setState(136);
					match(NEWLINE);
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(139);
				match(T__7);
				setState(140);
				match(SORT);
				setState(141);
				match(NEWLINE);
				setState(142);
				match(T__9);
				setState(143);
				match(NEWLINE);
				setState(150); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(145); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(144);
						match(WORD);
						}
						}
						setState(147); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(149);
					match(NEWLINE);
					}
					}
					setState(152); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WORD );
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(154);
					match(T__8);
					setState(156); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(155);
						match(INT);
						}
						}
						setState(158); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==INT );
					setState(160);
					match(NEWLINE);
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(163);
				match(T__7);
				setState(164);
				match(CHOICEINPUT);
				setState(165);
				match(NEWLINE);
				setState(166);
				match(T__9);
				setState(167);
				match(NEWLINE);
				setState(174); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(169); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(168);
						match(WORD);
						}
						}
						setState(171); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==WORD );
					setState(173);
					match(NEWLINE);
					}
					}
					setState(176); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WORD );
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(178);
					match(T__8);
					setState(179);
					match(INT);
					setState(180);
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
		"\u0004\u0001\u0017\u00ba\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0004\u0001\u000f\b\u0001"+
		"\u000b\u0001\f\u0001\u0010\u0001\u0001\u0004\u0001\u0014\b\u0001\u000b"+
		"\u0001\f\u0001\u0015\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u0001\u001b"+
		"\b\u0001\u000b\u0001\f\u0001\u001c\u0001\u0001\u0004\u0001 \b\u0001\u000b"+
		"\u0001\f\u0001!\u0001\u0001\u0001\u0001\u0004\u0001&\b\u0001\u000b\u0001"+
		"\f\u0001\'\u0001\u0002\u0001\u0002\u0004\u0002,\b\u0002\u000b\u0002\f"+
		"\u0002-\u0001\u0002\u0004\u00021\b\u0002\u000b\u0002\f\u00022\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0004\u00028\b\u0002\u000b\u0002\f\u00029\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0004\u0002C\b\u0002\u000b\u0002\f\u0002D\u0001\u0003\u0001\u0003"+
		"\u0004\u0003I\b\u0003\u000b\u0003\f\u0003J\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0004\u0003V\b\u0003\u000b\u0003\f\u0003W\u0001\u0003\u0005\u0003"+
		"[\b\u0003\n\u0003\f\u0003^\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0004\u0004e\b\u0004\u000b\u0004\f\u0004f\u0001"+
		"\u0004\u0003\u0004j\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0004\u0004q\b\u0004\u000b\u0004\f\u0004r\u0001\u0004"+
		"\u0003\u0004v\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0004\u0004~\b\u0004\u000b\u0004\f\u0004\u007f"+
		"\u0001\u0004\u0004\u0004\u0083\b\u0004\u000b\u0004\f\u0004\u0084\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u008a\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u0092"+
		"\b\u0004\u000b\u0004\f\u0004\u0093\u0001\u0004\u0004\u0004\u0097\b\u0004"+
		"\u000b\u0004\f\u0004\u0098\u0001\u0004\u0001\u0004\u0004\u0004\u009d\b"+
		"\u0004\u000b\u0004\f\u0004\u009e\u0001\u0004\u0003\u0004\u00a2\b\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0004\u0004\u00aa\b\u0004\u000b\u0004\f\u0004\u00ab\u0001\u0004\u0004"+
		"\u0004\u00af\b\u0004\u000b\u0004\f\u0004\u00b0\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004\u00b6\b\u0004\u0003\u0004\u00b8\b\u0004\u0001"+
		"\u0004\u0000\u0000\u0005\u0000\u0002\u0004\u0006\b\u0000\u0000\u00d2\u0000"+
		"\n\u0001\u0000\u0000\u0000\u0002\f\u0001\u0000\u0000\u0000\u0004)\u0001"+
		"\u0000\u0000\u0000\u0006F\u0001\u0000\u0000\u0000\b\u00b7\u0001\u0000"+
		"\u0000\u0000\n\u000b\u0003\u0002\u0001\u0000\u000b\u0001\u0001\u0000\u0000"+
		"\u0000\f\u000e\u0005\u0001\u0000\u0000\r\u000f\u0005\u0016\u0000\u0000"+
		"\u000e\r\u0001\u0000\u0000\u0000\u000f\u0010\u0001\u0000\u0000\u0000\u0010"+
		"\u000e\u0001\u0000\u0000\u0000\u0010\u0011\u0001\u0000\u0000\u0000\u0011"+
		"\u0013\u0001\u0000\u0000\u0000\u0012\u0014\u0005\u000b\u0000\u0000\u0013"+
		"\u0012\u0001\u0000\u0000\u0000\u0014\u0015\u0001\u0000\u0000\u0000\u0015"+
		"\u0013\u0001\u0000\u0000\u0000\u0015\u0016\u0001\u0000\u0000\u0000\u0016"+
		"\u0017\u0001\u0000\u0000\u0000\u0017\u0018\u0005\u0002\u0000\u0000\u0018"+
		"\u001a\u0005\u0015\u0000\u0000\u0019\u001b\u0005\u000b\u0000\u0000\u001a"+
		"\u0019\u0001\u0000\u0000\u0000\u001b\u001c\u0001\u0000\u0000\u0000\u001c"+
		"\u001a\u0001\u0000\u0000\u0000\u001c\u001d\u0001\u0000\u0000\u0000\u001d"+
		"\u001f\u0001\u0000\u0000\u0000\u001e \u0003\u0004\u0002\u0000\u001f\u001e"+
		"\u0001\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!\u001f\u0001\u0000"+
		"\u0000\u0000!\"\u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#%\u0005"+
		"\u0003\u0000\u0000$&\u0005\u0016\u0000\u0000%$\u0001\u0000\u0000\u0000"+
		"&\'\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000\'(\u0001\u0000"+
		"\u0000\u0000(\u0003\u0001\u0000\u0000\u0000)+\u0005\u0004\u0000\u0000"+
		"*,\u0005\u0016\u0000\u0000+*\u0001\u0000\u0000\u0000,-\u0001\u0000\u0000"+
		"\u0000-+\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000.0\u0001\u0000"+
		"\u0000\u0000/1\u0005\u000b\u0000\u00000/\u0001\u0000\u0000\u000012\u0001"+
		"\u0000\u0000\u000020\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u0000"+
		"34\u0001\u0000\u0000\u000045\u0005\u0002\u0000\u000057\u0005\u0015\u0000"+
		"\u000068\u0005\u000b\u0000\u000076\u0001\u0000\u0000\u000089\u0001\u0000"+
		"\u0000\u000097\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:;\u0001"+
		"\u0000\u0000\u0000;<\u0005\f\u0000\u0000<=\u0005\u000b\u0000\u0000=>\u0005"+
		"\u000f\u0000\u0000>?\u0005\u000b\u0000\u0000?@\u0005\u0005\u0000\u0000"+
		"@B\u0005\u000b\u0000\u0000AC\u0003\u0006\u0003\u0000BA\u0001\u0000\u0000"+
		"\u0000CD\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000\u0000DE\u0001\u0000"+
		"\u0000\u0000E\u0005\u0001\u0000\u0000\u0000FH\u0005\u0006\u0000\u0000"+
		"GI\u0005\u0016\u0000\u0000HG\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000"+
		"\u0000JH\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000KL\u0001\u0000"+
		"\u0000\u0000LM\u0005\u000b\u0000\u0000MN\u0005\u0002\u0000\u0000NO\u0005"+
		"\u0015\u0000\u0000OP\u0005\u000b\u0000\u0000PQ\u0003\b\u0004\u0000QR\u0005"+
		"\f\u0000\u0000RS\u0005\u000b\u0000\u0000SU\u0005\u0007\u0000\u0000TV\u0005"+
		"\u0016\u0000\u0000UT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000"+
		"WU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000X\\\u0001\u0000\u0000"+
		"\u0000Y[\u0005\u000b\u0000\u0000ZY\u0001\u0000\u0000\u0000[^\u0001\u0000"+
		"\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]\u0007"+
		"\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000_`\u0005\b\u0000\u0000"+
		"`a\u0005\u000e\u0000\u0000ai\u0005\u000b\u0000\u0000bd\u0005\t\u0000\u0000"+
		"ce\u0005\u0016\u0000\u0000dc\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000"+
		"\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0001\u0000"+
		"\u0000\u0000hj\u0005\u000b\u0000\u0000ib\u0001\u0000\u0000\u0000ij\u0001"+
		"\u0000\u0000\u0000j\u00b8\u0001\u0000\u0000\u0000kl\u0005\b\u0000\u0000"+
		"lm\u0005\u0010\u0000\u0000mu\u0005\u000b\u0000\u0000np\u0005\t\u0000\u0000"+
		"oq\u0005\u0014\u0000\u0000po\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000"+
		"\u0000rp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000st\u0001\u0000"+
		"\u0000\u0000tv\u0005\u000b\u0000\u0000un\u0001\u0000\u0000\u0000uv\u0001"+
		"\u0000\u0000\u0000v\u00b8\u0001\u0000\u0000\u0000wx\u0005\b\u0000\u0000"+
		"xy\u0005\u0011\u0000\u0000yz\u0005\u000b\u0000\u0000z{\u0005\n\u0000\u0000"+
		"{\u0082\u0005\u000b\u0000\u0000|~\u0005\u0016\u0000\u0000}|\u0001\u0000"+
		"\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f}\u0001\u0000\u0000\u0000"+
		"\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000"+
		"\u0081\u0083\u0005\u000b\u0000\u0000\u0082}\u0001\u0000\u0000\u0000\u0083"+
		"\u0084\u0001\u0000\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084"+
		"\u0085\u0001\u0000\u0000\u0000\u0085\u0089\u0001\u0000\u0000\u0000\u0086"+
		"\u0087\u0005\t\u0000\u0000\u0087\u0088\u0005\u0014\u0000\u0000\u0088\u008a"+
		"\u0005\u000b\u0000\u0000\u0089\u0086\u0001\u0000\u0000\u0000\u0089\u008a"+
		"\u0001\u0000\u0000\u0000\u008a\u00b8\u0001\u0000\u0000\u0000\u008b\u008c"+
		"\u0005\b\u0000\u0000\u008c\u008d\u0005\u0012\u0000\u0000\u008d\u008e\u0005"+
		"\u000b\u0000\u0000\u008e\u008f\u0005\n\u0000\u0000\u008f\u0096\u0005\u000b"+
		"\u0000\u0000\u0090\u0092\u0005\u0016\u0000\u0000\u0091\u0090\u0001\u0000"+
		"\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0091\u0001\u0000"+
		"\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000"+
		"\u0000\u0000\u0095\u0097\u0005\u000b\u0000\u0000\u0096\u0091\u0001\u0000"+
		"\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000"+
		"\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099\u00a1\u0001\u0000"+
		"\u0000\u0000\u009a\u009c\u0005\t\u0000\u0000\u009b\u009d\u0005\u0014\u0000"+
		"\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000"+
		"\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000"+
		"\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0\u00a2\u0005\u000b\u0000"+
		"\u0000\u00a1\u009a\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a2\u00b8\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005\b\u0000\u0000"+
		"\u00a4\u00a5\u0005\u0013\u0000\u0000\u00a5\u00a6\u0005\u000b\u0000\u0000"+
		"\u00a6\u00a7\u0005\n\u0000\u0000\u00a7\u00ae\u0005\u000b\u0000\u0000\u00a8"+
		"\u00aa\u0005\u0016\u0000\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00aa"+
		"\u00ab\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ab"+
		"\u00ac\u0001\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad"+
		"\u00af\u0005\u000b\u0000\u0000\u00ae\u00a9\u0001\u0000\u0000\u0000\u00af"+
		"\u00b0\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b0"+
		"\u00b1\u0001\u0000\u0000\u0000\u00b1\u00b5\u0001\u0000\u0000\u0000\u00b2"+
		"\u00b3\u0005\t\u0000\u0000\u00b3\u00b4\u0005\u0014\u0000\u0000\u00b4\u00b6"+
		"\u0005\u000b\u0000\u0000\u00b5\u00b2\u0001\u0000\u0000\u0000\u00b5\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b6\u00b8\u0001\u0000\u0000\u0000\u00b7_\u0001"+
		"\u0000\u0000\u0000\u00b7k\u0001\u0000\u0000\u0000\u00b7w\u0001\u0000\u0000"+
		"\u0000\u00b7\u008b\u0001\u0000\u0000\u0000\u00b7\u00a3\u0001\u0000\u0000"+
		"\u0000\u00b8\t\u0001\u0000\u0000\u0000\u001b\u0010\u0015\u001c!\'-29D"+
		"JW\\firu\u007f\u0084\u0089\u0093\u0098\u009e\u00a1\u00ab\u00b0\u00b5\u00b7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}