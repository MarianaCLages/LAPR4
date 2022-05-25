// Generated from C:/Users/maria/OneDrive/Documentos/lei21_22_s4_2dj_1\GrammarSurvey.g4 by ANTLR 4.10.1
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
public class GrammarSurveyParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, TYPE=3, OBLIGATORINESS=4, REPEATABLE=5, MESSAGE=6, SETENCE=7, 
		ID=8, LINE=9, SYMBOL=10, PONTUATION=11, WORD=12, INT=13, DNOT=14;
	public static final int
		RULE_prog = 0, RULE_survey = 1, RULE_section = 2, RULE_question = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "survey", "section", "question"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Section'", "'Question'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "TYPE", "OBLIGATORINESS", "REPEATABLE", "MESSAGE", 
			"SETENCE", "ID", "LINE", "SYMBOL", "PONTUATION", "WORD", "INT", "DNOT"
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
	public String getGrammarFileName() { return "GrammarSurvey.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarSurveyParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public List<SurveyContext> survey() {
			return getRuleContexts(SurveyContext.class);
		}
		public SurveyContext survey(int i) {
			return getRuleContext(SurveyContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSurveyListener ) ((GrammarSurveyListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSurveyListener ) ((GrammarSurveyListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSurveyVisitor ) return ((GrammarSurveyVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(9); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(8);
				survey();
				}
				}
				setState(11); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
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
		public TerminalNode ID() { return getToken(GrammarSurveyParser.ID, 0); }
		public List<TerminalNode> LINE() { return getTokens(GrammarSurveyParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(GrammarSurveyParser.LINE, i);
		}
		public List<TerminalNode> MESSAGE() { return getTokens(GrammarSurveyParser.MESSAGE); }
		public TerminalNode MESSAGE(int i) {
			return getToken(GrammarSurveyParser.MESSAGE, i);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public SurveyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_survey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSurveyListener ) ((GrammarSurveyListener)listener).enterSurvey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSurveyListener ) ((GrammarSurveyListener)listener).exitSurvey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSurveyVisitor ) return ((GrammarSurveyVisitor<? extends T>)visitor).visitSurvey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SurveyContext survey() throws RecognitionException {
		SurveyContext _localctx = new SurveyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_survey);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			match(ID);
			setState(14);
			match(LINE);
			setState(15);
			match(MESSAGE);
			setState(16);
			match(LINE);
			setState(20); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(17);
				section();
				setState(18);
				match(MESSAGE);
				}
				}
				setState(22); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
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
		public TerminalNode ID() { return getToken(GrammarSurveyParser.ID, 0); }
		public List<TerminalNode> LINE() { return getTokens(GrammarSurveyParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(GrammarSurveyParser.LINE, i);
		}
		public List<TerminalNode> MESSAGE() { return getTokens(GrammarSurveyParser.MESSAGE); }
		public TerminalNode MESSAGE(int i) {
			return getToken(GrammarSurveyParser.MESSAGE, i);
		}
		public TerminalNode OBLIGATORINESS() { return getToken(GrammarSurveyParser.OBLIGATORINESS, 0); }
		public TerminalNode REPEATABLE() { return getToken(GrammarSurveyParser.REPEATABLE, 0); }
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSurveyListener ) ((GrammarSurveyListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSurveyListener ) ((GrammarSurveyListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSurveyVisitor ) return ((GrammarSurveyVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(T__0);
			setState(25);
			match(ID);
			setState(26);
			match(LINE);
			setState(27);
			match(MESSAGE);
			setState(28);
			match(LINE);
			setState(29);
			match(MESSAGE);
			setState(30);
			match(LINE);
			setState(31);
			match(OBLIGATORINESS);
			setState(32);
			match(LINE);
			setState(33);
			match(REPEATABLE);
			setState(34);
			match(LINE);
			setState(36); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(35);
				question();
				}
				}
				setState(38); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 );
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
		public TerminalNode ID() { return getToken(GrammarSurveyParser.ID, 0); }
		public List<TerminalNode> LINE() { return getTokens(GrammarSurveyParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(GrammarSurveyParser.LINE, i);
		}
		public List<TerminalNode> MESSAGE() { return getTokens(GrammarSurveyParser.MESSAGE); }
		public TerminalNode MESSAGE(int i) {
			return getToken(GrammarSurveyParser.MESSAGE, i);
		}
		public TerminalNode TYPE() { return getToken(GrammarSurveyParser.TYPE, 0); }
		public TerminalNode OBLIGATORINESS() { return getToken(GrammarSurveyParser.OBLIGATORINESS, 0); }
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSurveyListener ) ((GrammarSurveyListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSurveyListener ) ((GrammarSurveyListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSurveyVisitor ) return ((GrammarSurveyVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(T__1);
			setState(41);
			match(ID);
			setState(42);
			match(LINE);
			setState(43);
			match(MESSAGE);
			setState(44);
			match(LINE);
			setState(45);
			match(TYPE);
			setState(46);
			match(LINE);
			setState(47);
			match(OBLIGATORINESS);
			setState(48);
			match(LINE);
			setState(49);
			match(MESSAGE);
			setState(50);
			match(LINE);
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
		"\u0004\u0001\u000e5\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0004\u0000\n\b"+
		"\u0000\u000b\u0000\f\u0000\u000b\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u0001\u0015\b\u0001\u000b"+
		"\u0001\f\u0001\u0016\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0004\u0002%\b\u0002\u000b\u0002\f\u0002&\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0000\u0000\u0004\u0000\u0002\u0004\u0006\u0000\u00003\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0002\r\u0001\u0000\u0000\u0000\u0004\u0018\u0001\u0000"+
		"\u0000\u0000\u0006(\u0001\u0000\u0000\u0000\b\n\u0003\u0002\u0001\u0000"+
		"\t\b\u0001\u0000\u0000\u0000\n\u000b\u0001\u0000\u0000\u0000\u000b\t\u0001"+
		"\u0000\u0000\u0000\u000b\f\u0001\u0000\u0000\u0000\f\u0001\u0001\u0000"+
		"\u0000\u0000\r\u000e\u0005\b\u0000\u0000\u000e\u000f\u0005\t\u0000\u0000"+
		"\u000f\u0010\u0005\u0006\u0000\u0000\u0010\u0014\u0005\t\u0000\u0000\u0011"+
		"\u0012\u0003\u0004\u0002\u0000\u0012\u0013\u0005\u0006\u0000\u0000\u0013"+
		"\u0015\u0001\u0000\u0000\u0000\u0014\u0011\u0001\u0000\u0000\u0000\u0015"+
		"\u0016\u0001\u0000\u0000\u0000\u0016\u0014\u0001\u0000\u0000\u0000\u0016"+
		"\u0017\u0001\u0000\u0000\u0000\u0017\u0003\u0001\u0000\u0000\u0000\u0018"+
		"\u0019\u0005\u0001\u0000\u0000\u0019\u001a\u0005\b\u0000\u0000\u001a\u001b"+
		"\u0005\t\u0000\u0000\u001b\u001c\u0005\u0006\u0000\u0000\u001c\u001d\u0005"+
		"\t\u0000\u0000\u001d\u001e\u0005\u0006\u0000\u0000\u001e\u001f\u0005\t"+
		"\u0000\u0000\u001f \u0005\u0004\u0000\u0000 !\u0005\t\u0000\u0000!\"\u0005"+
		"\u0005\u0000\u0000\"$\u0005\t\u0000\u0000#%\u0003\u0006\u0003\u0000$#"+
		"\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000"+
		"\u0000&\'\u0001\u0000\u0000\u0000\'\u0005\u0001\u0000\u0000\u0000()\u0005"+
		"\u0002\u0000\u0000)*\u0005\b\u0000\u0000*+\u0005\t\u0000\u0000+,\u0005"+
		"\u0006\u0000\u0000,-\u0005\t\u0000\u0000-.\u0005\u0003\u0000\u0000./\u0005"+
		"\t\u0000\u0000/0\u0005\u0004\u0000\u000001\u0005\t\u0000\u000012\u0005"+
		"\u0006\u0000\u000023\u0005\t\u0000\u00003\u0007\u0001\u0000\u0000\u0000"+
		"\u0003\u000b\u0016&";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}