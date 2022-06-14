// Generated from C:/Users/eduar/Desktop/ProjetoIntegrador/lei21_22_s4_2dj_1\Grammar.g4 by ANTLR 4.10.1
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
		INT=29, ID=30, VIRGULA=31, MESSAGE=32;
	public static final int
		RULE_prog = 0, RULE_survey = 1, RULE_section = 2, RULE_question = 3, RULE_questiontype = 4, 
		RULE_option = 5, RULE_answers = 6, RULE_message = 7, RULE_resnumericas = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "survey", "section", "question", "questiontype", "option", "answers", 
			"message", "resnumericas"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'ID: '", "'Survey'", "'Title: '", "'Welcome Message:'", "'List of Sections:'", 
			"'Final Message: '", "'Section Title: '", "'Description:'", "'Content:'", 
			"'Question: '", "'Instruction:'", "'Extra Information: '", "'Question Type: '", 
			"'Possible Answers:'", "'Options:'", "'Answer:'", null, null, "'Decision'", 
			null, null, null, null, null, null, null, null, null, null, null, "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "NEWLINE", "OBLIGATORINESS", "DECISION", 
			"DECISIONANSWER", "TEXT", "REPEATABILITY", "NUMERIC", "OPTION", "SORT", 
			"INPUT", "MULTIPLECHOICEINPUT", "SCALINGOPTIONS", "INT", "ID", "VIRGULA", 
			"MESSAGE"
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
			setState(18);
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
		public MessageContext title;
		public MessageContext welcmen;
		public SectionContext sections;
		public MessageContext finMess;
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
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
			setState(20);
			match(T__0);
			setState(21);
			match(ID);
			setState(22);
			match(NEWLINE);
			setState(23);
			match(T__1);
			setState(24);
			match(NEWLINE);
			setState(25);
			match(T__2);
			setState(26);
			((SurveystructureContext)_localctx).title = message(0);
			setState(28); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(27);
				match(NEWLINE);
				}
				}
				setState(30); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(32);
				match(T__3);
				setState(33);
				((SurveystructureContext)_localctx).welcmen = message(0);
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
				}
			}

			setState(41);
			match(T__4);
			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42);
				match(NEWLINE);
				}
				}
				setState(45); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(48); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(47);
				((SurveystructureContext)_localctx).sections = section();
				}
				}
				setState(50); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(52);
			match(T__5);
			setState(53);
			((SurveystructureContext)_localctx).finMess = message(0);
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
		public MessageContext secTit;
		public MessageContext desc;
		public QuestionContext questions;
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public TerminalNode OBLIGATORINESS() { return getToken(GrammarParser.OBLIGATORINESS, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public TerminalNode REPEATABILITY() { return getToken(GrammarParser.REPEATABILITY, 0); }
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
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
			setState(55);
			match(T__0);
			setState(56);
			match(ID);
			setState(58); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(57);
				match(NEWLINE);
				}
				}
				setState(60); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(62);
			match(T__6);
			setState(63);
			((SectionsContext)_localctx).secTit = message(0);
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(64);
				match(NEWLINE);
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(69);
			match(T__7);
			setState(70);
			((SectionsContext)_localctx).desc = message(0);
			setState(72); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(71);
				match(NEWLINE);
				}
				}
				setState(74); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(76);
			match(OBLIGATORINESS);
			setState(77);
			match(NEWLINE);
			setState(78);
			match(REPEATABILITY);
			setState(79);
			match(NEWLINE);
			setState(80);
			match(T__8);
			setState(82); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(81);
				match(NEWLINE);
				}
				}
				setState(84); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(87); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(86);
					((SectionsContext)_localctx).questions = question();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(89); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
		public MessageContext quest;
		public MessageContext insttruc;
		public QuestiontypeContext type;
		public MessageContext xInfo;
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public TerminalNode OBLIGATORINESS() { return getToken(GrammarParser.OBLIGATORINESS, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public QuestiontypeContext questiontype() {
			return getRuleContext(QuestiontypeContext.class,0);
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
			setState(91);
			match(T__0);
			setState(92);
			match(ID);
			setState(94); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(93);
				match(NEWLINE);
				}
				}
				setState(96); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(98);
			match(T__9);
			setState(99);
			((QuestionsContext)_localctx).quest = message(0);
			setState(101); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(100);
				match(NEWLINE);
				}
				}
				setState(103); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(105);
				match(T__10);
				setState(106);
				((QuestionsContext)_localctx).insttruc = message(0);
				}
			}

			setState(109);
			((QuestionsContext)_localctx).type = questiontype();
			setState(110);
			match(OBLIGATORINESS);
			setState(111);
			match(NEWLINE);
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(112);
				match(T__11);
				setState(113);
				((QuestionsContext)_localctx).xInfo = message(0);
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(114);
					match(NEWLINE);
					}
					}
					setState(119);
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
		public Token type;
		public AnswersContext answer;
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public TerminalNode TEXT() { return getToken(GrammarParser.TEXT, 0); }
		public AnswersContext answers() {
			return getRuleContext(AnswersContext.class,0);
		}
		public OptionContext option() {
			return getRuleContext(OptionContext.class,0);
		}
		public TerminalNode OPTION() { return getToken(GrammarParser.OPTION, 0); }
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
		try {
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				match(T__12);
				setState(123);
				((QuestiontypeContext)_localctx).type = match(TEXT);
				setState(124);
				match(NEWLINE);
				setState(125);
				((QuestiontypeContext)_localctx).answer = answers();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				match(T__12);
				setState(127);
				((QuestiontypeContext)_localctx).type = match(OPTION);
				setState(128);
				match(NEWLINE);
				setState(129);
				match(T__13);
				setState(130);
				match(NEWLINE);
				setState(131);
				option();
				setState(132);
				((QuestiontypeContext)_localctx).answer = answers();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				match(T__12);
				setState(135);
				((QuestiontypeContext)_localctx).type = match(INPUT);
				setState(136);
				match(NEWLINE);
				setState(137);
				match(T__13);
				setState(138);
				match(NEWLINE);
				setState(139);
				option();
				setState(140);
				((QuestiontypeContext)_localctx).answer = answers();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(142);
				match(T__12);
				setState(143);
				((QuestiontypeContext)_localctx).type = match(MULTIPLECHOICEINPUT);
				setState(144);
				match(NEWLINE);
				setState(145);
				match(T__13);
				setState(146);
				match(NEWLINE);
				setState(147);
				option();
				setState(148);
				((QuestiontypeContext)_localctx).answer = answers();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(150);
				match(T__12);
				setState(151);
				((QuestiontypeContext)_localctx).type = match(NUMERIC);
				setState(152);
				match(NEWLINE);
				setState(153);
				((QuestiontypeContext)_localctx).answer = answers();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(154);
				match(T__12);
				setState(155);
				((QuestiontypeContext)_localctx).type = match(SCALINGOPTIONS);
				setState(156);
				match(NEWLINE);
				setState(157);
				((QuestiontypeContext)_localctx).answer = answers();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(158);
				match(T__12);
				setState(159);
				((QuestiontypeContext)_localctx).type = match(DECISION);
				setState(160);
				match(NEWLINE);
				setState(161);
				((QuestiontypeContext)_localctx).answer = answers();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(162);
				match(T__12);
				setState(163);
				((QuestiontypeContext)_localctx).type = match(SORT);
				setState(164);
				match(NEWLINE);
				setState(165);
				match(T__14);
				setState(166);
				match(NEWLINE);
				setState(167);
				option();
				setState(168);
				((QuestiontypeContext)_localctx).answer = answers();
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

	public static class OptionContext extends ParserRuleContext {
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_option);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(175); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(172);
					message(0);
					setState(173);
					match(NEWLINE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(177); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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

	public static class AnswersContext extends ParserRuleContext {
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(GrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GrammarParser.NEWLINE, i);
		}
		public ResnumericasContext resnumericas() {
			return getRuleContext(ResnumericasContext.class,0);
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
		enterRule(_localctx, 12, RULE_answers);
		try {
			int _alt;
			setState(204);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(180);
				match(T__15);
				setState(181);
				message(0);
				setState(182);
				match(NEWLINE);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(184);
				match(T__15);
				setState(185);
				resnumericas();
				setState(186);
				match(NEWLINE);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(188);
				match(T__15);
				setState(189);
				resnumericas();
				setState(190);
				match(NEWLINE);
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(192);
				match(T__15);
				setState(193);
				match(DECISIONANSWER);
				setState(194);
				match(NEWLINE);
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(195);
				match(T__15);
				setState(196);
				match(NEWLINE);
				setState(200); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(197);
						message(0);
						setState(198);
						match(NEWLINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(202); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class MessageContext extends ParserRuleContext {
		public TerminalNode MESSAGE() { return getToken(GrammarParser.MESSAGE, 0); }
		public MessageContext message() {
			return getRuleContext(MessageContext.class,0);
		}
		public MessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageContext message() throws RecognitionException {
		return message(0);
	}

	private MessageContext message(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MessageContext _localctx = new MessageContext(_ctx, _parentState);
		MessageContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_message, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				}
				break;
			case 2:
				{
				setState(207);
				match(MESSAGE);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(214);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MessageContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_message);
					setState(210);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(211);
					match(MESSAGE);
					}
					} 
				}
				setState(216);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ResnumericasContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(GrammarParser.INT, 0); }
		public TerminalNode VIRGULA() { return getToken(GrammarParser.VIRGULA, 0); }
		public ResnumericasContext resnumericas() {
			return getRuleContext(ResnumericasContext.class,0);
		}
		public ResnumericasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resnumericas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterResnumericas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitResnumericas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitResnumericas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResnumericasContext resnumericas() throws RecognitionException {
		ResnumericasContext _localctx = new ResnumericasContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_resnumericas);
		try {
			setState(221);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(217);
				match(INT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				match(INT);
				setState(219);
				match(VIRGULA);
				setState(220);
				resnumericas();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return message_sempred((MessageContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean message_sempred(MessageContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001 \u00e0\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u0001"+
		"\u001d\b\u0001\u000b\u0001\f\u0001\u001e\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0004\u0001$\b\u0001\u000b\u0001\f\u0001%\u0003\u0001(\b\u0001"+
		"\u0001\u0001\u0001\u0001\u0004\u0001,\b\u0001\u000b\u0001\f\u0001-\u0001"+
		"\u0001\u0004\u00011\b\u0001\u000b\u0001\f\u00012\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u0002;\b\u0002"+
		"\u000b\u0002\f\u0002<\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u0002"+
		"B\b\u0002\u000b\u0002\f\u0002C\u0001\u0002\u0001\u0002\u0001\u0002\u0004"+
		"\u0002I\b\u0002\u000b\u0002\f\u0002J\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u0002S\b\u0002\u000b\u0002"+
		"\f\u0002T\u0001\u0002\u0004\u0002X\b\u0002\u000b\u0002\f\u0002Y\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0004\u0003_\b\u0003\u000b\u0003\f\u0003"+
		"`\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003f\b\u0003\u000b\u0003"+
		"\f\u0003g\u0001\u0003\u0001\u0003\u0003\u0003l\b\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003t\b"+
		"\u0003\n\u0003\f\u0003w\t\u0003\u0003\u0003y\b\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00ab"+
		"\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005\u00b0\b\u0005"+
		"\u000b\u0005\f\u0005\u00b1\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0004\u0006"+
		"\u00c9\b\u0006\u000b\u0006\f\u0006\u00ca\u0003\u0006\u00cd\b\u0006\u0001"+
		"\u0007\u0001\u0007\u0003\u0007\u00d1\b\u0007\u0001\u0007\u0001\u0007\u0005"+
		"\u0007\u00d5\b\u0007\n\u0007\f\u0007\u00d8\t\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0003\b\u00de\b\b\u0001\b\u0000\u0001\u000e\t\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0000\u0000\u00f6\u0000\u0012\u0001\u0000\u0000"+
		"\u0000\u0002\u0014\u0001\u0000\u0000\u0000\u00047\u0001\u0000\u0000\u0000"+
		"\u0006[\u0001\u0000\u0000\u0000\b\u00aa\u0001\u0000\u0000\u0000\n\u00af"+
		"\u0001\u0000\u0000\u0000\f\u00cc\u0001\u0000\u0000\u0000\u000e\u00d0\u0001"+
		"\u0000\u0000\u0000\u0010\u00dd\u0001\u0000\u0000\u0000\u0012\u0013\u0003"+
		"\u0002\u0001\u0000\u0013\u0001\u0001\u0000\u0000\u0000\u0014\u0015\u0005"+
		"\u0001\u0000\u0000\u0015\u0016\u0005\u001e\u0000\u0000\u0016\u0017\u0005"+
		"\u0011\u0000\u0000\u0017\u0018\u0005\u0002\u0000\u0000\u0018\u0019\u0005"+
		"\u0011\u0000\u0000\u0019\u001a\u0005\u0003\u0000\u0000\u001a\u001c\u0003"+
		"\u000e\u0007\u0000\u001b\u001d\u0005\u0011\u0000\u0000\u001c\u001b\u0001"+
		"\u0000\u0000\u0000\u001d\u001e\u0001\u0000\u0000\u0000\u001e\u001c\u0001"+
		"\u0000\u0000\u0000\u001e\u001f\u0001\u0000\u0000\u0000\u001f\'\u0001\u0000"+
		"\u0000\u0000 !\u0005\u0004\u0000\u0000!#\u0003\u000e\u0007\u0000\"$\u0005"+
		"\u0011\u0000\u0000#\"\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000"+
		"%#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&(\u0001\u0000\u0000"+
		"\u0000\' \u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000()\u0001\u0000"+
		"\u0000\u0000)+\u0005\u0005\u0000\u0000*,\u0005\u0011\u0000\u0000+*\u0001"+
		"\u0000\u0000\u0000,-\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000"+
		"-.\u0001\u0000\u0000\u0000.0\u0001\u0000\u0000\u0000/1\u0003\u0004\u0002"+
		"\u00000/\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u000020\u0001\u0000"+
		"\u0000\u000023\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u000045\u0005"+
		"\u0006\u0000\u000056\u0003\u000e\u0007\u00006\u0003\u0001\u0000\u0000"+
		"\u000078\u0005\u0001\u0000\u00008:\u0005\u001e\u0000\u00009;\u0005\u0011"+
		"\u0000\u0000:9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<:\u0001"+
		"\u0000\u0000\u0000<=\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000"+
		">?\u0005\u0007\u0000\u0000?A\u0003\u000e\u0007\u0000@B\u0005\u0011\u0000"+
		"\u0000A@\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000CA\u0001\u0000"+
		"\u0000\u0000CD\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000EF\u0005"+
		"\b\u0000\u0000FH\u0003\u000e\u0007\u0000GI\u0005\u0011\u0000\u0000HG\u0001"+
		"\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000"+
		"JK\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LM\u0005\u0012\u0000"+
		"\u0000MN\u0005\u0011\u0000\u0000NO\u0005\u0016\u0000\u0000OP\u0005\u0011"+
		"\u0000\u0000PR\u0005\t\u0000\u0000QS\u0005\u0011\u0000\u0000RQ\u0001\u0000"+
		"\u0000\u0000ST\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001"+
		"\u0000\u0000\u0000UW\u0001\u0000\u0000\u0000VX\u0003\u0006\u0003\u0000"+
		"WV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000"+
		"\u0000YZ\u0001\u0000\u0000\u0000Z\u0005\u0001\u0000\u0000\u0000[\\\u0005"+
		"\u0001\u0000\u0000\\^\u0005\u001e\u0000\u0000]_\u0005\u0011\u0000\u0000"+
		"^]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000"+
		"\u0000`a\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000bc\u0005\n\u0000"+
		"\u0000ce\u0003\u000e\u0007\u0000df\u0005\u0011\u0000\u0000ed\u0001\u0000"+
		"\u0000\u0000fg\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000gh\u0001"+
		"\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000ij\u0005\u000b\u0000\u0000"+
		"jl\u0003\u000e\u0007\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000"+
		"\u0000lm\u0001\u0000\u0000\u0000mn\u0003\b\u0004\u0000no\u0005\u0012\u0000"+
		"\u0000ox\u0005\u0011\u0000\u0000pq\u0005\f\u0000\u0000qu\u0003\u000e\u0007"+
		"\u0000rt\u0005\u0011\u0000\u0000sr\u0001\u0000\u0000\u0000tw\u0001\u0000"+
		"\u0000\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000vy\u0001"+
		"\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000xp\u0001\u0000\u0000\u0000"+
		"xy\u0001\u0000\u0000\u0000y\u0007\u0001\u0000\u0000\u0000z{\u0005\r\u0000"+
		"\u0000{|\u0005\u0015\u0000\u0000|}\u0005\u0011\u0000\u0000}\u00ab\u0003"+
		"\f\u0006\u0000~\u007f\u0005\r\u0000\u0000\u007f\u0080\u0005\u0018\u0000"+
		"\u0000\u0080\u0081\u0005\u0011\u0000\u0000\u0081\u0082\u0005\u000e\u0000"+
		"\u0000\u0082\u0083\u0005\u0011\u0000\u0000\u0083\u0084\u0003\n\u0005\u0000"+
		"\u0084\u0085\u0003\f\u0006\u0000\u0085\u00ab\u0001\u0000\u0000\u0000\u0086"+
		"\u0087\u0005\r\u0000\u0000\u0087\u0088\u0005\u001a\u0000\u0000\u0088\u0089"+
		"\u0005\u0011\u0000\u0000\u0089\u008a\u0005\u000e\u0000\u0000\u008a\u008b"+
		"\u0005\u0011\u0000\u0000\u008b\u008c\u0003\n\u0005\u0000\u008c\u008d\u0003"+
		"\f\u0006\u0000\u008d\u00ab\u0001\u0000\u0000\u0000\u008e\u008f\u0005\r"+
		"\u0000\u0000\u008f\u0090\u0005\u001b\u0000\u0000\u0090\u0091\u0005\u0011"+
		"\u0000\u0000\u0091\u0092\u0005\u000e\u0000\u0000\u0092\u0093\u0005\u0011"+
		"\u0000\u0000\u0093\u0094\u0003\n\u0005\u0000\u0094\u0095\u0003\f\u0006"+
		"\u0000\u0095\u00ab\u0001\u0000\u0000\u0000\u0096\u0097\u0005\r\u0000\u0000"+
		"\u0097\u0098\u0005\u0017\u0000\u0000\u0098\u0099\u0005\u0011\u0000\u0000"+
		"\u0099\u00ab\u0003\f\u0006\u0000\u009a\u009b\u0005\r\u0000\u0000\u009b"+
		"\u009c\u0005\u001c\u0000\u0000\u009c\u009d\u0005\u0011\u0000\u0000\u009d"+
		"\u00ab\u0003\f\u0006\u0000\u009e\u009f\u0005\r\u0000\u0000\u009f\u00a0"+
		"\u0005\u0013\u0000\u0000\u00a0\u00a1\u0005\u0011\u0000\u0000\u00a1\u00ab"+
		"\u0003\f\u0006\u0000\u00a2\u00a3\u0005\r\u0000\u0000\u00a3\u00a4\u0005"+
		"\u0019\u0000\u0000\u00a4\u00a5\u0005\u0011\u0000\u0000\u00a5\u00a6\u0005"+
		"\u000f\u0000\u0000\u00a6\u00a7\u0005\u0011\u0000\u0000\u00a7\u00a8\u0003"+
		"\n\u0005\u0000\u00a8\u00a9\u0003\f\u0006\u0000\u00a9\u00ab\u0001\u0000"+
		"\u0000\u0000\u00aaz\u0001\u0000\u0000\u0000\u00aa~\u0001\u0000\u0000\u0000"+
		"\u00aa\u0086\u0001\u0000\u0000\u0000\u00aa\u008e\u0001\u0000\u0000\u0000"+
		"\u00aa\u0096\u0001\u0000\u0000\u0000\u00aa\u009a\u0001\u0000\u0000\u0000"+
		"\u00aa\u009e\u0001\u0000\u0000\u0000\u00aa\u00a2\u0001\u0000\u0000\u0000"+
		"\u00ab\t\u0001\u0000\u0000\u0000\u00ac\u00ad\u0003\u000e\u0007\u0000\u00ad"+
		"\u00ae\u0005\u0011\u0000\u0000\u00ae\u00b0\u0001\u0000\u0000\u0000\u00af"+
		"\u00ac\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000\u00b1"+
		"\u00af\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2"+
		"\u000b\u0001\u0000\u0000\u0000\u00b3\u00cd\u0001\u0000\u0000\u0000\u00b4"+
		"\u00b5\u0005\u0010\u0000\u0000\u00b5\u00b6\u0003\u000e\u0007\u0000\u00b6"+
		"\u00b7\u0005\u0011\u0000\u0000\u00b7\u00cd\u0001\u0000\u0000\u0000\u00b8"+
		"\u00b9\u0005\u0010\u0000\u0000\u00b9\u00ba\u0003\u0010\b\u0000\u00ba\u00bb"+
		"\u0005\u0011\u0000\u0000\u00bb\u00cd\u0001\u0000\u0000\u0000\u00bc\u00bd"+
		"\u0005\u0010\u0000\u0000\u00bd\u00be\u0003\u0010\b\u0000\u00be\u00bf\u0005"+
		"\u0011\u0000\u0000\u00bf\u00cd\u0001\u0000\u0000\u0000\u00c0\u00c1\u0005"+
		"\u0010\u0000\u0000\u00c1\u00c2\u0005\u0014\u0000\u0000\u00c2\u00cd\u0005"+
		"\u0011\u0000\u0000\u00c3\u00c4\u0005\u0010\u0000\u0000\u00c4\u00c8\u0005"+
		"\u0011\u0000\u0000\u00c5\u00c6\u0003\u000e\u0007\u0000\u00c6\u00c7\u0005"+
		"\u0011\u0000\u0000\u00c7\u00c9\u0001\u0000\u0000\u0000\u00c8\u00c5\u0001"+
		"\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001"+
		"\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb\u00cd\u0001"+
		"\u0000\u0000\u0000\u00cc\u00b3\u0001\u0000\u0000\u0000\u00cc\u00b4\u0001"+
		"\u0000\u0000\u0000\u00cc\u00b8\u0001\u0000\u0000\u0000\u00cc\u00bc\u0001"+
		"\u0000\u0000\u0000\u00cc\u00c0\u0001\u0000\u0000\u0000\u00cc\u00c3\u0001"+
		"\u0000\u0000\u0000\u00cd\r\u0001\u0000\u0000\u0000\u00ce\u00d1\u0006\u0007"+
		"\uffff\uffff\u0000\u00cf\u00d1\u0005 \u0000\u0000\u00d0\u00ce\u0001\u0000"+
		"\u0000\u0000\u00d0\u00cf\u0001\u0000\u0000\u0000\u00d1\u00d6\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d3\n\u0001\u0000\u0000\u00d3\u00d5\u0005 \u0000"+
		"\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d5\u00d8\u0001\u0000\u0000"+
		"\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d6\u00d7\u0001\u0000\u0000"+
		"\u0000\u00d7\u000f\u0001\u0000\u0000\u0000\u00d8\u00d6\u0001\u0000\u0000"+
		"\u0000\u00d9\u00de\u0005\u001d\u0000\u0000\u00da\u00db\u0005\u001d\u0000"+
		"\u0000\u00db\u00dc\u0005\u001f\u0000\u0000\u00dc\u00de\u0003\u0010\b\u0000"+
		"\u00dd\u00d9\u0001\u0000\u0000\u0000\u00dd\u00da\u0001\u0000\u0000\u0000"+
		"\u00de\u0011\u0001\u0000\u0000\u0000\u0016\u001e%\'-2<CJTY`gkux\u00aa"+
		"\u00b1\u00ca\u00cc\u00d0\u00d6\u00dd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}