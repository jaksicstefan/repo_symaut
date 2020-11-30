// Generated from C:/repo_symaut/sym_automata/src/parser/StlParser.g4 by ANTLR 4.5.3

package gen;

import java.util.List;

import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class StlParser extends Parser {
	static {
		RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int LPAREN = 1, RPAREN = 2, LBRACE = 3, RBRACE = 4, LBRACK = 5, RBRACK = 6, SEMICOLON = 7,
			COLON = 8, COMMA = 9, DOT = 10, Input = 11, Output = 12, Internal = 13, Constant = 14, DomainTypeReal = 15,
			DomainTypeInt = 16, DomainTypeBool = 17, Assertion = 18, From = 19, NotOperator = 20, OrOperator = 21,
			AndOperator = 22, IffOperator = 23, ImpliesOperator = 24, XorOperator = 25, RiseOperator = 26, FallOperator = 27,
			AlwaysOperator = 28, EventuallyOperator = 29, UntilOperator = 30, HistoricallyOperator = 31, OnceOperator = 32,
			SinceOperator = 33, NextOperator = 34, OracleOperator = 35, PreviousOperator = 36, EqualOperator = 37,
			NotEqualOperator = 38, GreaterOrEqualOperator = 39, LesserOrEqualOperator = 40, GreaterOperator = 41,
			LesserOperator = 42, EQUAL = 43, BooleanLiteral = 44, TRUE = 45, FALSE = 46, IntegerLiteral = 47,
			RealLiteral = 48, Identifier = 49, LINE_TERMINATOR = 50, WHITESPACE = 51, COMMENT = 52, LINE_COMMENT = 53;
	public static final int RULE_stlfile = 0, RULE_stlSpecification = 1, RULE_assertion = 2, RULE_declaration = 3,
			RULE_variableDeclaration = 4, RULE_assignment = 5, RULE_domainType = 6, RULE_ioType = 7, RULE_interval = 8,
			RULE_intervalTime = 9, RULE_expression = 10, RULE_idComp = 11, RULE_equalityCmpOp = 12, RULE_comparisonOp = 13,
			RULE_primary = 14, RULE_booleanLiteral = 15, RULE_integerLiteral = 16, RULE_literal = 17, RULE_identifier = 18;
	public static final String[] ruleNames = { "stlfile", "stlSpecification", "assertion", "declaration",
			"variableDeclaration", "assignment", "domainType", "ioType", "interval", "intervalTime", "expression", "idComp",
			"equalityCmpOp", "comparisonOp", "primary", "booleanLiteral", "integerLiteral", "literal", "identifier" };

	private static final String[] _LITERAL_NAMES = {};
	private static final String[] _SYMBOLIC_NAMES = { null, "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK",
			"SEMICOLON", "COLON", "COMMA", "DOT", "Input", "Output", "Internal", "Constant", "DomainTypeReal",
			"DomainTypeInt", "DomainTypeBool", "Assertion", "From", "NotOperator", "OrOperator", "AndOperator", "IffOperator",
			"ImpliesOperator", "XorOperator", "RiseOperator", "FallOperator", "AlwaysOperator", "EventuallyOperator",
			"UntilOperator", "HistoricallyOperator", "OnceOperator", "SinceOperator", "NextOperator", "OracleOperator",
			"PreviousOperator", "EqualOperator", "NotEqualOperator", "GreaterOrEqualOperator", "LesserOrEqualOperator",
			"GreaterOperator", "LesserOperator", "EQUAL", "BooleanLiteral", "TRUE", "FALSE", "IntegerLiteral", "RealLiteral",
			"Identifier", "LINE_TERMINATOR", "WHITESPACE", "COMMENT", "LINE_COMMENT" };
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
	public String getGrammarFileName() {
		return "StlParser.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	public StlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	public static class StlfileContext extends ParserRuleContext {
		public StlSpecificationContext stlSpecification() {
			return getRuleContext(StlSpecificationContext.class, 0);
		}

		public TerminalNode EOF() {
			return getToken(StlParser.EOF, 0);
		}

		public StlfileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_stlfile;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterStlfile(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitStlfile(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitStlfile(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final StlfileContext stlfile() throws RecognitionException {
		StlfileContext _localctx = new StlfileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stlfile);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(38);
				stlSpecification();
				setState(39);
				match(EOF);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StlSpecificationContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}

		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class, i);
		}

		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}

		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class, i);
		}

		public StlSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_stlSpecification;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterStlSpecification(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitStlSpecification(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitStlSpecification(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final StlSpecificationContext stlSpecification() throws RecognitionException {
		StlSpecificationContext _localctx = new StlSpecificationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stlSpecification);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Input) | (1L << Output) | (1L << Internal)
						| (1L << Constant) | (1L << DomainTypeReal) | (1L << DomainTypeInt) | (1L << DomainTypeBool))) != 0)) {
					{
						{
							setState(41);
							declaration();
						}
					}
					setState(46);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(47);
							assertion();
						}
					}
					setState(50);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == Assertion);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssertionContext extends ParserRuleContext {
		public TerminalNode Assertion() {
			return getToken(StlParser.Assertion, 0);
		}

		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public TerminalNode COLON() {
			return getToken(StlParser.COLON, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public TerminalNode SEMICOLON() {
			return getToken(StlParser.SEMICOLON, 0);
		}

		public AssertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_assertion;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterAssertion(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitAssertion(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitAssertion(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AssertionContext assertion() throws RecognitionException {
		AssertionContext _localctx = new AssertionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(52);
				match(Assertion);
				setState(53);
				identifier();
				setState(54);
				match(COLON);
				setState(55);
				expression(0);
				setState(56);
				match(SEMICOLON);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_declaration;
		}

		public DeclarationContext() {
		}

		public void copyFrom(DeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class DeclVariableContext extends DeclarationContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class, 0);
		}

		public DeclVariableContext(DeclarationContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterDeclVariable(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitDeclVariable(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitDeclVariable(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaration);
		try {
			_localctx = new DeclVariableContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
				setState(58);
				variableDeclaration();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public DomainTypeContext domainType() {
			return getRuleContext(DomainTypeContext.class, 0);
		}

		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public TerminalNode SEMICOLON() {
			return getToken(StlParser.SEMICOLON, 0);
		}

		public TerminalNode Constant() {
			return getToken(StlParser.Constant, 0);
		}

		public IoTypeContext ioType() {
			return getRuleContext(IoTypeContext.class, 0);
		}

		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class, 0);
		}

		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_variableDeclaration;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterVariableDeclaration(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitVariableDeclaration(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitVariableDeclaration(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(61);
				_la = _input.LA(1);
				if (_la == Constant) {
					{
						setState(60);
						match(Constant);
					}
				}

				setState(64);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Input) | (1L << Output) | (1L << Internal))) != 0)) {
					{
						setState(63);
						ioType();
					}
				}

				setState(66);
				domainType();
				setState(67);
				identifier();
				setState(69);
				_la = _input.LA(1);
				if (_la == EQUAL) {
					{
						setState(68);
						assignment();
					}
				}

				setState(71);
				match(SEMICOLON);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_assignment;
		}

		public AssignmentContext() {
		}

		public void copyFrom(AssignmentContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class AsgnExprContext extends AssignmentContext {
		public TerminalNode EQUAL() {
			return getToken(StlParser.EQUAL, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public AsgnExprContext(AssignmentContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterAsgnExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitAsgnExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitAsgnExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class AsgnLiteralContext extends AssignmentContext {
		public TerminalNode EQUAL() {
			return getToken(StlParser.EQUAL, 0);
		}

		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class, 0);
		}

		public AsgnLiteralContext(AssignmentContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterAsgnLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitAsgnLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitAsgnLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignment);
		try {
			setState(77);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 5, _ctx)) {
			case 1:
				_localctx = new AsgnLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1); {
				setState(73);
				match(EQUAL);
				setState(74);
				literal();
			}
				break;
			case 2:
				_localctx = new AsgnExprContext(_localctx);
				enterOuterAlt(_localctx, 2); {
				setState(75);
				match(EQUAL);
				setState(76);
				expression(0);
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainTypeContext extends ParserRuleContext {
		public TerminalNode DomainTypeReal() {
			return getToken(StlParser.DomainTypeReal, 0);
		}

		public TerminalNode DomainTypeBool() {
			return getToken(StlParser.DomainTypeBool, 0);
		}

		public TerminalNode DomainTypeInt() {
			return getToken(StlParser.DomainTypeInt, 0);
		}

		public DomainTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_domainType;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterDomainType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitDomainType(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitDomainType(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final DomainTypeContext domainType() throws RecognitionException {
		DomainTypeContext _localctx = new DomainTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_domainType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(79);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0
						&& ((1L << _la) & ((1L << DomainTypeReal) | (1L << DomainTypeInt) | (1L << DomainTypeBool))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					consume();
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IoTypeContext extends ParserRuleContext {
		public TerminalNode Input() {
			return getToken(StlParser.Input, 0);
		}

		public TerminalNode Output() {
			return getToken(StlParser.Output, 0);
		}

		public TerminalNode Internal() {
			return getToken(StlParser.Internal, 0);
		}

		public IoTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ioType;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterIoType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitIoType(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitIoType(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IoTypeContext ioType() throws RecognitionException {
		IoTypeContext _localctx = new IoTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ioType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(81);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Input) | (1L << Output) | (1L << Internal))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					consume();
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntervalContext extends ParserRuleContext {
		public List<IntervalTimeContext> intervalTime() {
			return getRuleContexts(IntervalTimeContext.class);
		}

		public IntervalTimeContext intervalTime(int i) {
			return getRuleContext(IntervalTimeContext.class, i);
		}

		public TerminalNode COLON() {
			return getToken(StlParser.COLON, 0);
		}

		public TerminalNode LPAREN() {
			return getToken(StlParser.LPAREN, 0);
		}

		public TerminalNode LBRACK() {
			return getToken(StlParser.LBRACK, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(StlParser.RPAREN, 0);
		}

		public TerminalNode RBRACK() {
			return getToken(StlParser.RBRACK, 0);
		}

		public IntervalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_interval;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterInterval(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitInterval(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitInterval(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IntervalContext interval() throws RecognitionException {
		IntervalContext _localctx = new IntervalContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_interval);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(83);
				_la = _input.LA(1);
				if (!(_la == LPAREN || _la == LBRACK)) {
					_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(84);
				intervalTime();
				setState(85);
				match(COLON);
				setState(86);
				intervalTime();
				setState(87);
				_la = _input.LA(1);
				if (!(_la == RPAREN || _la == RBRACK)) {
					_errHandler.recoverInline(this);
				} else {
					consume();
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntervalTimeContext extends ParserRuleContext {
		public IntervalTimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_intervalTime;
		}

		public IntervalTimeContext() {
		}

		public void copyFrom(IntervalTimeContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class IntervalTimeLiteralContext extends IntervalTimeContext {
		public TerminalNode IntegerLiteral() {
			return getToken(StlParser.IntegerLiteral, 0);
		}

		public IntervalTimeLiteralContext(IntervalTimeContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterIntervalTimeLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitIntervalTimeLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitIntervalTimeLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IntervalTimeContext intervalTime() throws RecognitionException {
		IntervalTimeContext _localctx = new IntervalTimeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_intervalTime);
		try {
			_localctx = new IntervalTimeLiteralContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
				setState(89);
				match(IntegerLiteral);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_expression;
		}

		public ExpressionContext() {
		}

		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class ExprNotUnaryExprContext extends ExpressionContext {
		public TerminalNode NotOperator() {
			return getToken(StlParser.NotOperator, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public ExprNotUnaryExprContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprNotUnaryExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprNotUnaryExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprNotUnaryExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprSinceContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public TerminalNode SinceOperator() {
			return getToken(StlParser.SinceOperator, 0);
		}

		public IntervalContext interval() {
			return getRuleContext(IntervalContext.class, 0);
		}

		public ExprSinceContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprSince(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprSince(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprSince(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprIdCompContext extends ExpressionContext {
		public IdCompContext idComp() {
			return getRuleContext(IdCompContext.class, 0);
		}

		public ExprIdCompContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprIdComp(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprIdComp(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprIdComp(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprEventuallyContext extends ExpressionContext {
		public TerminalNode EventuallyOperator() {
			return getToken(StlParser.EventuallyOperator, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public IntervalContext interval() {
			return getRuleContext(IntervalContext.class, 0);
		}

		public ExprEventuallyContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprEventually(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprEventually(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprEventually(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprNotUnaryIdContext extends ExpressionContext {
		public TerminalNode NotOperator() {
			return getToken(StlParser.NotOperator, 0);
		}

		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public ExprNotUnaryIdContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprNotUnaryId(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprNotUnaryId(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprNotUnaryId(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprAlwaysExprContext extends ExpressionContext {
		public TerminalNode AlwaysOperator() {
			return getToken(StlParser.AlwaysOperator, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public IntervalContext interval() {
			return getRuleContext(IntervalContext.class, 0);
		}

		public ExprAlwaysExprContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprAlwaysExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprAlwaysExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprAlwaysExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprIffContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public TerminalNode IffOperator() {
			return getToken(StlParser.IffOperator, 0);
		}

		public ExprIffContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprIff(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprIff(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprIff(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprImpliesContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public TerminalNode ImpliesOperator() {
			return getToken(StlParser.ImpliesOperator, 0);
		}

		public ExprImpliesContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprImplies(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprImplies(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprImplies(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprUntilContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public TerminalNode UntilOperator() {
			return getToken(StlParser.UntilOperator, 0);
		}

		public IntervalContext interval() {
			return getRuleContext(IntervalContext.class, 0);
		}

		public ExprUntilContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprUntil(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprUntil(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprUntil(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprPrimaryContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class, 0);
		}

		public ExprPrimaryContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprPrimary(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprPrimary(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprPrimary(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprAndContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public TerminalNode AndOperator() {
			return getToken(StlParser.AndOperator, 0);
		}

		public ExprAndContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprAnd(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprAnd(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprAnd(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprOnceExprContext extends ExpressionContext {
		public TerminalNode OnceOperator() {
			return getToken(StlParser.OnceOperator, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public IntervalContext interval() {
			return getRuleContext(IntervalContext.class, 0);
		}

		public ExprOnceExprContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprOnceExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprOnceExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprOnceExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprOracleExprContext extends ExpressionContext {
		public TerminalNode OracleOperator() {
			return getToken(StlParser.OracleOperator, 0);
		}

		public TerminalNode IntegerLiteral() {
			return getToken(StlParser.IntegerLiteral, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public ExprOracleExprContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprOracleExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprOracleExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprOracleExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprXorContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public TerminalNode XorOperator() {
			return getToken(StlParser.XorOperator, 0);
		}

		public ExprXorContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprXor(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprXor(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprXor(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprRiseFallContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public TerminalNode RiseOperator() {
			return getToken(StlParser.RiseOperator, 0);
		}

		public TerminalNode FallOperator() {
			return getToken(StlParser.FallOperator, 0);
		}

		public ExprRiseFallContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprRiseFall(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprRiseFall(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprRiseFall(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprEqualExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public EqualityCmpOpContext equalityCmpOp() {
			return getRuleContext(EqualityCmpOpContext.class, 0);
		}

		public ExprEqualExprContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprEqualExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprEqualExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprEqualExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprHistoricallyExprContext extends ExpressionContext {
		public TerminalNode HistoricallyOperator() {
			return getToken(StlParser.HistoricallyOperator, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public IntervalContext interval() {
			return getRuleContext(IntervalContext.class, 0);
		}

		public ExprHistoricallyExprContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprHistoricallyExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprHistoricallyExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprHistoricallyExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprPreviousExprContext extends ExpressionContext {
		public TerminalNode PreviousOperator() {
			return getToken(StlParser.PreviousOperator, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public ExprPreviousExprContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprPreviousExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprPreviousExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprPreviousExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprOrContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public TerminalNode OrOperator() {
			return getToken(StlParser.OrOperator, 0);
		}

		public ExprOrContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprOr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprOr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprOr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ExprNextExprContext extends ExpressionContext {
		public TerminalNode NextOperator() {
			return getToken(StlParser.NextOperator, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public ExprNextExprContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterExprNextExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitExprNextExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitExprNextExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(127);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 10, _ctx)) {
				case 1: {
					_localctx = new ExprPrimaryContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;

					setState(92);
					primary();
				}
					break;
				case 2: {
					_localctx = new ExprNotUnaryExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(93);
					match(NotOperator);
					setState(94);
					expression(19);
				}
					break;
				case 3: {
					_localctx = new ExprNotUnaryIdContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(95);
					match(NotOperator);
					setState(96);
					identifier();
				}
					break;
				case 4: {
					_localctx = new ExprIdCompContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(97);
					idComp();
				}
					break;
				case 5: {
					_localctx = new ExprRiseFallContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(98);
					_la = _input.LA(1);
					if (!(_la == RiseOperator || _la == FallOperator)) {
						_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(99);
					expression(15);
				}
					break;
				case 6: {
					_localctx = new ExprEventuallyContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(100);
					match(EventuallyOperator);
					setState(102);
					_errHandler.sync(this);
					switch (getInterpreter().adaptivePredict(_input, 6, _ctx)) {
					case 1: {
						setState(101);
						interval();
					}
						break;
					}
					setState(104);
					expression(7);
				}
					break;
				case 7: {
					_localctx = new ExprOnceExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(105);
					match(OnceOperator);
					setState(107);
					_errHandler.sync(this);
					switch (getInterpreter().adaptivePredict(_input, 7, _ctx)) {
					case 1: {
						setState(106);
						interval();
					}
						break;
					}
					setState(109);
					expression(6);
				}
					break;
				case 8: {
					_localctx = new ExprAlwaysExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(110);
					match(AlwaysOperator);
					setState(112);
					_errHandler.sync(this);
					switch (getInterpreter().adaptivePredict(_input, 8, _ctx)) {
					case 1: {
						setState(111);
						interval();
					}
						break;
					}
					setState(114);
					expression(5);
				}
					break;
				case 9: {
					_localctx = new ExprHistoricallyExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(115);
					match(HistoricallyOperator);
					setState(117);
					_errHandler.sync(this);
					switch (getInterpreter().adaptivePredict(_input, 9, _ctx)) {
					case 1: {
						setState(116);
						interval();
					}
						break;
					}
					setState(119);
					expression(4);
				}
					break;
				case 10: {
					_localctx = new ExprPreviousExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(120);
					match(PreviousOperator);
					setState(121);
					expression(3);
				}
					break;
				case 11: {
					_localctx = new ExprNextExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(122);
					match(NextOperator);
					setState(123);
					expression(2);
				}
					break;
				case 12: {
					_localctx = new ExprOracleExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(124);
					match(OracleOperator);
					setState(125);
					match(IntegerLiteral);
					setState(126);
					expression(1);
				}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(162);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 14, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null)
							triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(160);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 13, _ctx)) {
							case 1: {
								_localctx = new ExprEqualExprContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(129);
								if (!(precpred(_ctx, 16)))
									throw new FailedPredicateException(this, "precpred(_ctx, 16)");
								setState(130);
								equalityCmpOp();
								setState(131);
								expression(17);
							}
								break;
							case 2: {
								_localctx = new ExprAndContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(133);
								if (!(precpred(_ctx, 14)))
									throw new FailedPredicateException(this, "precpred(_ctx, 14)");
								setState(134);
								match(AndOperator);
								setState(135);
								expression(15);
							}
								break;
							case 3: {
								_localctx = new ExprOrContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(136);
								if (!(precpred(_ctx, 13)))
									throw new FailedPredicateException(this, "precpred(_ctx, 13)");
								setState(137);
								match(OrOperator);
								setState(138);
								expression(14);
							}
								break;
							case 4: {
								_localctx = new ExprXorContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(139);
								if (!(precpred(_ctx, 12)))
									throw new FailedPredicateException(this, "precpred(_ctx, 12)");
								setState(140);
								match(XorOperator);
								setState(141);
								expression(13);
							}
								break;
							case 5: {
								_localctx = new ExprImpliesContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(142);
								if (!(precpred(_ctx, 11)))
									throw new FailedPredicateException(this, "precpred(_ctx, 11)");
								setState(143);
								match(ImpliesOperator);
								setState(144);
								expression(12);
							}
								break;
							case 6: {
								_localctx = new ExprIffContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(145);
								if (!(precpred(_ctx, 10)))
									throw new FailedPredicateException(this, "precpred(_ctx, 10)");
								setState(146);
								match(IffOperator);
								setState(147);
								expression(11);
							}
								break;
							case 7: {
								_localctx = new ExprUntilContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(148);
								if (!(precpred(_ctx, 9)))
									throw new FailedPredicateException(this, "precpred(_ctx, 9)");
								setState(149);
								match(UntilOperator);
								setState(151);
								_errHandler.sync(this);
								switch (getInterpreter().adaptivePredict(_input, 11, _ctx)) {
								case 1: {
									setState(150);
									interval();
								}
									break;
								}
								setState(153);
								expression(10);
							}
								break;
							case 8: {
								_localctx = new ExprSinceContext(new ExpressionContext(_parentctx, _parentState));
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(154);
								if (!(precpred(_ctx, 8)))
									throw new FailedPredicateException(this, "precpred(_ctx, 8)");
								setState(155);
								match(SinceOperator);
								setState(157);
								_errHandler.sync(this);
								switch (getInterpreter().adaptivePredict(_input, 12, _ctx)) {
								case 1: {
									setState(156);
									interval();
								}
									break;
								}
								setState(159);
								expression(9);
							}
								break;
							}
						}
					}
					setState(164);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 14, _ctx);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class IdCompContext extends ParserRuleContext {
		public IdCompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_idComp;
		}

		public IdCompContext() {
		}

		public void copyFrom(IdCompContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class IntCompIdContext extends IdCompContext {
		public TerminalNode IntegerLiteral() {
			return getToken(StlParser.IntegerLiteral, 0);
		}

		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public ComparisonOpContext comparisonOp() {
			return getRuleContext(ComparisonOpContext.class, 0);
		}

		public EqualityCmpOpContext equalityCmpOp() {
			return getRuleContext(EqualityCmpOpContext.class, 0);
		}

		public IntCompIdContext(IdCompContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterIntCompId(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitIntCompId(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitIntCompId(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class IdCompIdContext extends IdCompContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}

		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class, i);
		}

		public ComparisonOpContext comparisonOp() {
			return getRuleContext(ComparisonOpContext.class, 0);
		}

		public EqualityCmpOpContext equalityCmpOp() {
			return getRuleContext(EqualityCmpOpContext.class, 0);
		}

		public IdCompIdContext(IdCompContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterIdCompId(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitIdCompId(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitIdCompId(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class IdCompIntContext extends IdCompContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public TerminalNode IntegerLiteral() {
			return getToken(StlParser.IntegerLiteral, 0);
		}

		public ComparisonOpContext comparisonOp() {
			return getRuleContext(ComparisonOpContext.class, 0);
		}

		public EqualityCmpOpContext equalityCmpOp() {
			return getRuleContext(EqualityCmpOpContext.class, 0);
		}

		public IdCompIntContext(IdCompContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterIdCompInt(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitIdCompInt(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitIdCompInt(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IdCompContext idComp() throws RecognitionException {
		IdCompContext _localctx = new IdCompContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_idComp);
		try {
			setState(186);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 18, _ctx)) {
			case 1:
				_localctx = new IdCompIdContext(_localctx);
				enterOuterAlt(_localctx, 1); {
				setState(165);
				identifier();
				setState(168);
				switch (_input.LA(1)) {
				case GreaterOrEqualOperator:
				case LesserOrEqualOperator:
				case GreaterOperator:
				case LesserOperator: {
					setState(166);
					comparisonOp();
				}
					break;
				case EqualOperator:
				case NotEqualOperator: {
					setState(167);
					equalityCmpOp();
				}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(170);
				identifier();
			}
				break;
			case 2:
				_localctx = new IdCompIntContext(_localctx);
				enterOuterAlt(_localctx, 2); {
				setState(172);
				identifier();
				setState(175);
				switch (_input.LA(1)) {
				case GreaterOrEqualOperator:
				case LesserOrEqualOperator:
				case GreaterOperator:
				case LesserOperator: {
					setState(173);
					comparisonOp();
				}
					break;
				case EqualOperator:
				case NotEqualOperator: {
					setState(174);
					equalityCmpOp();
				}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(177);
				match(IntegerLiteral);
			}
				break;
			case 3:
				_localctx = new IntCompIdContext(_localctx);
				enterOuterAlt(_localctx, 3); {
				setState(179);
				match(IntegerLiteral);
				setState(182);
				switch (_input.LA(1)) {
				case GreaterOrEqualOperator:
				case LesserOrEqualOperator:
				case GreaterOperator:
				case LesserOperator: {
					setState(180);
					comparisonOp();
				}
					break;
				case EqualOperator:
				case NotEqualOperator: {
					setState(181);
					equalityCmpOp();
				}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(184);
				identifier();
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualityCmpOpContext extends ParserRuleContext {
		public EqualityCmpOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_equalityCmpOp;
		}

		public EqualityCmpOpContext() {
		}

		public void copyFrom(EqualityCmpOpContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class NeqOpContext extends EqualityCmpOpContext {
		public TerminalNode NotEqualOperator() {
			return getToken(StlParser.NotEqualOperator, 0);
		}

		public NeqOpContext(EqualityCmpOpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterNeqOp(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitNeqOp(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitNeqOp(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class EqOpContext extends EqualityCmpOpContext {
		public TerminalNode EqualOperator() {
			return getToken(StlParser.EqualOperator, 0);
		}

		public EqOpContext(EqualityCmpOpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterEqOp(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitEqOp(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitEqOp(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final EqualityCmpOpContext equalityCmpOp() throws RecognitionException {
		EqualityCmpOpContext _localctx = new EqualityCmpOpContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_equalityCmpOp);
		try {
			setState(190);
			switch (_input.LA(1)) {
			case EqualOperator:
				_localctx = new EqOpContext(_localctx);
				enterOuterAlt(_localctx, 1); {
				setState(188);
				match(EqualOperator);
			}
				break;
			case NotEqualOperator:
				_localctx = new NeqOpContext(_localctx);
				enterOuterAlt(_localctx, 2); {
				setState(189);
				match(NotEqualOperator);
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonOpContext extends ParserRuleContext {
		public ComparisonOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_comparisonOp;
		}

		public ComparisonOpContext() {
		}

		public void copyFrom(ComparisonOpContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class CmpOpGteContext extends ComparisonOpContext {
		public TerminalNode GreaterOrEqualOperator() {
			return getToken(StlParser.GreaterOrEqualOperator, 0);
		}

		public CmpOpGteContext(ComparisonOpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterCmpOpGte(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitCmpOpGte(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitCmpOpGte(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class CmpOpLseContext extends ComparisonOpContext {
		public TerminalNode LesserOperator() {
			return getToken(StlParser.LesserOperator, 0);
		}

		public CmpOpLseContext(ComparisonOpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterCmpOpLse(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitCmpOpLse(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitCmpOpLse(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class CmpOpGtContext extends ComparisonOpContext {
		public TerminalNode GreaterOperator() {
			return getToken(StlParser.GreaterOperator, 0);
		}

		public CmpOpGtContext(ComparisonOpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterCmpOpGt(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitCmpOpGt(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitCmpOpGt(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class CmpOpLsContext extends ComparisonOpContext {
		public TerminalNode LesserOrEqualOperator() {
			return getToken(StlParser.LesserOrEqualOperator, 0);
		}

		public CmpOpLsContext(ComparisonOpContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterCmpOpLs(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitCmpOpLs(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitCmpOpLs(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ComparisonOpContext comparisonOp() throws RecognitionException {
		ComparisonOpContext _localctx = new ComparisonOpContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_comparisonOp);
		try {
			setState(196);
			switch (_input.LA(1)) {
			case LesserOrEqualOperator:
				_localctx = new CmpOpLsContext(_localctx);
				enterOuterAlt(_localctx, 1); {
				setState(192);
				match(LesserOrEqualOperator);
			}
				break;
			case GreaterOrEqualOperator:
				_localctx = new CmpOpGteContext(_localctx);
				enterOuterAlt(_localctx, 2); {
				setState(193);
				match(GreaterOrEqualOperator);
			}
				break;
			case LesserOperator:
				_localctx = new CmpOpLseContext(_localctx);
				enterOuterAlt(_localctx, 3); {
				setState(194);
				match(LesserOperator);
			}
				break;
			case GreaterOperator:
				_localctx = new CmpOpGtContext(_localctx);
				enterOuterAlt(_localctx, 4); {
				setState(195);
				match(GreaterOperator);
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_primary;
		}

		public PrimaryContext() {
		}

		public void copyFrom(PrimaryContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class PrimaryLiteralBoolContext extends PrimaryContext {
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class, 0);
		}

		public PrimaryLiteralBoolContext(PrimaryContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterPrimaryLiteralBool(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitPrimaryLiteralBool(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitPrimaryLiteralBool(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class PrimaryParenthesisContext extends PrimaryContext {
		public TerminalNode LPAREN() {
			return getToken(StlParser.LPAREN, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(StlParser.RPAREN, 0);
		}

		public PrimaryParenthesisContext(PrimaryContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterPrimaryParenthesis(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitPrimaryParenthesis(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitPrimaryParenthesis(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class PrimaryIdContext extends PrimaryContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public PrimaryIdContext(PrimaryContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterPrimaryId(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitPrimaryId(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitPrimaryId(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_primary);
		try {
			setState(204);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 21, _ctx)) {
			case 1:
				_localctx = new PrimaryParenthesisContext(_localctx);
				enterOuterAlt(_localctx, 1); {
				setState(198);
				match(LPAREN);
				setState(199);
				expression(0);
				setState(200);
				match(RPAREN);
			}
				break;
			case 2:
				_localctx = new PrimaryLiteralBoolContext(_localctx);
				enterOuterAlt(_localctx, 2); {
				setState(202);
				booleanLiteral();
			}
				break;
			case 3:
				_localctx = new PrimaryIdContext(_localctx);
				enterOuterAlt(_localctx, 3); {
				setState(203);
				identifier();
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanLiteralContext extends ParserRuleContext {
		public BooleanLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_booleanLiteral;
		}

		public BooleanLiteralContext() {
		}

		public void copyFrom(BooleanLiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class LiteralBoolContext extends BooleanLiteralContext {
		public TerminalNode BooleanLiteral() {
			return getToken(StlParser.BooleanLiteral, 0);
		}

		public LiteralBoolContext(BooleanLiteralContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterLiteralBool(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitLiteralBool(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitLiteralBool(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class LiteralBoolParenContext extends BooleanLiteralContext {
		public TerminalNode LPAREN() {
			return getToken(StlParser.LPAREN, 0);
		}

		public TerminalNode BooleanLiteral() {
			return getToken(StlParser.BooleanLiteral, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(StlParser.RPAREN, 0);
		}

		public LiteralBoolParenContext(BooleanLiteralContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterLiteralBoolParen(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitLiteralBoolParen(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitLiteralBoolParen(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BooleanLiteralContext booleanLiteral() throws RecognitionException {
		BooleanLiteralContext _localctx = new BooleanLiteralContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_booleanLiteral);
		try {
			setState(210);
			switch (_input.LA(1)) {
			case LPAREN:
				_localctx = new LiteralBoolParenContext(_localctx);
				enterOuterAlt(_localctx, 1); {
				setState(206);
				match(LPAREN);
				setState(207);
				match(BooleanLiteral);
				setState(208);
				match(RPAREN);
			}
				break;
			case BooleanLiteral:
				_localctx = new LiteralBoolContext(_localctx);
				enterOuterAlt(_localctx, 2); {
				setState(209);
				match(BooleanLiteral);
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerLiteralContext extends ParserRuleContext {
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_integerLiteral;
		}

		public IntegerLiteralContext() {
		}

		public void copyFrom(IntegerLiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class LiteralIntContext extends IntegerLiteralContext {
		public TerminalNode IntegerLiteral() {
			return getToken(StlParser.IntegerLiteral, 0);
		}

		public LiteralIntContext(IntegerLiteralContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterLiteralInt(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitLiteralInt(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitLiteralInt(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class LiteralIntParenContext extends IntegerLiteralContext {
		public TerminalNode LPAREN() {
			return getToken(StlParser.LPAREN, 0);
		}

		public TerminalNode BooleanLiteral() {
			return getToken(StlParser.BooleanLiteral, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(StlParser.RPAREN, 0);
		}

		public LiteralIntParenContext(IntegerLiteralContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterLiteralIntParen(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitLiteralIntParen(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitLiteralIntParen(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_integerLiteral);
		try {
			setState(216);
			switch (_input.LA(1)) {
			case LPAREN:
				_localctx = new LiteralIntParenContext(_localctx);
				enterOuterAlt(_localctx, 1); {
				setState(212);
				match(LPAREN);
				setState(213);
				match(BooleanLiteral);
				setState(214);
				match(RPAREN);
			}
				break;
			case IntegerLiteral:
				_localctx = new LiteralIntContext(_localctx);
				enterOuterAlt(_localctx, 2); {
				setState(215);
				match(IntegerLiteral);
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_literal;
		}

		public LiteralContext() {
		}

		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class LiteralIntegerContext extends LiteralContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class, 0);
		}

		public LiteralIntegerContext(LiteralContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterLiteralInteger(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitLiteralInteger(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitLiteralInteger(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class LiteralBooleanContext extends LiteralContext {
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class, 0);
		}

		public LiteralBooleanContext(LiteralContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterLiteralBoolean(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitLiteralBoolean(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitLiteralBoolean(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_literal);
		try {
			setState(220);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 24, _ctx)) {
			case 1:
				_localctx = new LiteralBooleanContext(_localctx);
				enterOuterAlt(_localctx, 1); {
				setState(218);
				booleanLiteral();
			}
				break;
			case 2:
				_localctx = new LiteralIntegerContext(_localctx);
				enterOuterAlt(_localctx, 2); {
				setState(219);
				integerLiteral();
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_identifier;
		}

		public IdentifierContext() {
		}

		public void copyFrom(IdentifierContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class IdContext extends IdentifierContext {
		public TerminalNode Identifier() {
			return getToken(StlParser.Identifier, 0);
		}

		public IdContext(IdentifierContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).enterId(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof StlParserListener)
				((StlParserListener) listener).exitId(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof StlParserVisitor)
				return ((StlParserVisitor<? extends T>) visitor).visitId(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_identifier);
		try {
			_localctx = new IdContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
				setState(222);
				match(Identifier);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return expression_sempred((ExpressionContext) _localctx, predIndex);
		}
		return true;
	}

	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 16);
		case 1:
			return precpred(_ctx, 14);
		case 2:
			return precpred(_ctx, 13);
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 11);
		case 5:
			return precpred(_ctx, 10);
		case 6:
			return precpred(_ctx, 9);
		case 7:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\67\u00e3\4\2\t\2"
			+ "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"
			+ "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"
			+ "\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\3\7\3-\n\3\f\3\16\3\60\13\3\3\3\6\3"
			+ "\63\n\3\r\3\16\3\64\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\5\6@\n\6\3\6\5"
			+ "\6C\n\6\3\6\3\6\3\6\5\6H\n\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7P\n\7\3\b\3\b"
			+ "\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f"
			+ "\3\f\3\f\3\f\3\f\5\fi\n\f\3\f\3\f\3\f\5\fn\n\f\3\f\3\f\3\f\5\fs\n\f\3"
			+ "\f\3\f\3\f\5\fx\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0082\n\f\3\f"
			+ "\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"
			+ "\f\3\f\3\f\3\f\5\f\u009a\n\f\3\f\3\f\3\f\3\f\5\f\u00a0\n\f\3\f\7\f\u00a3"
			+ "\n\f\f\f\16\f\u00a6\13\f\3\r\3\r\3\r\5\r\u00ab\n\r\3\r\3\r\3\r\3\r\3\r"
			+ "\5\r\u00b2\n\r\3\r\3\r\3\r\3\r\3\r\5\r\u00b9\n\r\3\r\3\r\5\r\u00bd\n\r"
			+ "\3\16\3\16\5\16\u00c1\n\16\3\17\3\17\3\17\3\17\5\17\u00c7\n\17\3\20\3"
			+ "\20\3\20\3\20\3\20\3\20\5\20\u00cf\n\20\3\21\3\21\3\21\3\21\5\21\u00d5"
			+ "\n\21\3\22\3\22\3\22\3\22\5\22\u00db\n\22\3\23\3\23\5\23\u00df\n\23\3"
			+ "\24\3\24\3\24\2\3\26\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&\2"
			+ "\7\3\2\21\23\3\2\r\17\4\2\3\3\7\7\4\2\4\4\b\b\3\2\34\35\u00fc\2(\3\2\2"
			+ "\2\4.\3\2\2\2\6\66\3\2\2\2\b<\3\2\2\2\n?\3\2\2\2\fO\3\2\2\2\16Q\3\2\2"
			+ "\2\20S\3\2\2\2\22U\3\2\2\2\24[\3\2\2\2\26\u0081\3\2\2\2\30\u00bc\3\2\2"
			+ "\2\32\u00c0\3\2\2\2\34\u00c6\3\2\2\2\36\u00ce\3\2\2\2 \u00d4\3\2\2\2\""
			+ "\u00da\3\2\2\2$\u00de\3\2\2\2&\u00e0\3\2\2\2()\5\4\3\2)*\7\2\2\3*\3\3"
			+ "\2\2\2+-\5\b\5\2,+\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\62\3\2\2\2"
			+ "\60.\3\2\2\2\61\63\5\6\4\2\62\61\3\2\2\2\63\64\3\2\2\2\64\62\3\2\2\2\64"
			+ "\65\3\2\2\2\65\5\3\2\2\2\66\67\7\24\2\2\678\5&\24\289\7\n\2\29:\5\26\f"
			+ "\2:;\7\t\2\2;\7\3\2\2\2<=\5\n\6\2=\t\3\2\2\2>@\7\20\2\2?>\3\2\2\2?@\3"
			+ "\2\2\2@B\3\2\2\2AC\5\20\t\2BA\3\2\2\2BC\3\2\2\2CD\3\2\2\2DE\5\16\b\2E"
			+ "G\5&\24\2FH\5\f\7\2GF\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\7\t\2\2J\13\3\2\2"
			+ "\2KL\7-\2\2LP\5$\23\2MN\7-\2\2NP\5\26\f\2OK\3\2\2\2OM\3\2\2\2P\r\3\2\2"
			+ "\2QR\t\2\2\2R\17\3\2\2\2ST\t\3\2\2T\21\3\2\2\2UV\t\4\2\2VW\5\24\13\2W"
			+ "X\7\n\2\2XY\5\24\13\2YZ\t\5\2\2Z\23\3\2\2\2[\\\7\61\2\2\\\25\3\2\2\2]"
			+ "^\b\f\1\2^\u0082\5\36\20\2_`\7\26\2\2`\u0082\5\26\f\25ab\7\26\2\2b\u0082"
			+ "\5&\24\2c\u0082\5\30\r\2de\t\6\2\2e\u0082\5\26\f\21fh\7\37\2\2gi\5\22"
			+ "\n\2hg\3\2\2\2hi\3\2\2\2ij\3\2\2\2j\u0082\5\26\f\tkm\7\"\2\2ln\5\22\n"
			+ "\2ml\3\2\2\2mn\3\2\2\2no\3\2\2\2o\u0082\5\26\f\bpr\7\36\2\2qs\5\22\n\2"
			+ "rq\3\2\2\2rs\3\2\2\2st\3\2\2\2t\u0082\5\26\f\7uw\7!\2\2vx\5\22\n\2wv\3"
			+ "\2\2\2wx\3\2\2\2xy\3\2\2\2y\u0082\5\26\f\6z{\7&\2\2{\u0082\5\26\f\5|}"
			+ "\7$\2\2}\u0082\5\26\f\4~\177\7%\2\2\177\u0080\7\61\2\2\u0080\u0082\5\26"
			+ "\f\3\u0081]\3\2\2\2\u0081_\3\2\2\2\u0081a\3\2\2\2\u0081c\3\2\2\2\u0081"
			+ "d\3\2\2\2\u0081f\3\2\2\2\u0081k\3\2\2\2\u0081p\3\2\2\2\u0081u\3\2\2\2"
			+ "\u0081z\3\2\2\2\u0081|\3\2\2\2\u0081~\3\2\2\2\u0082\u00a4\3\2\2\2\u0083"
			+ "\u0084\f\22\2\2\u0084\u0085\5\32\16\2\u0085\u0086\5\26\f\23\u0086\u00a3"
			+ "\3\2\2\2\u0087\u0088\f\20\2\2\u0088\u0089\7\30\2\2\u0089\u00a3\5\26\f"
			+ "\21\u008a\u008b\f\17\2\2\u008b\u008c\7\27\2\2\u008c\u00a3\5\26\f\20\u008d"
			+ "\u008e\f\16\2\2\u008e\u008f\7\33\2\2\u008f\u00a3\5\26\f\17\u0090\u0091"
			+ "\f\r\2\2\u0091\u0092\7\32\2\2\u0092\u00a3\5\26\f\16\u0093\u0094\f\f\2"
			+ "\2\u0094\u0095\7\31\2\2\u0095\u00a3\5\26\f\r\u0096\u0097\f\13\2\2\u0097"
			+ "\u0099\7 \2\2\u0098\u009a\5\22\n\2\u0099\u0098\3\2\2\2\u0099\u009a\3\2"
			+ "\2\2\u009a\u009b\3\2\2\2\u009b\u00a3\5\26\f\f\u009c\u009d\f\n\2\2\u009d"
			+ "\u009f\7#\2\2\u009e\u00a0\5\22\n\2\u009f\u009e\3\2\2\2\u009f\u00a0\3\2"
			+ "\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\5\26\f\13\u00a2\u0083\3\2\2\2\u00a2"
			+ "\u0087\3\2\2\2\u00a2\u008a\3\2\2\2\u00a2\u008d\3\2\2\2\u00a2\u0090\3\2"
			+ "\2\2\u00a2\u0093\3\2\2\2\u00a2\u0096\3\2\2\2\u00a2\u009c\3\2\2\2\u00a3"
			+ "\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\27\3\2\2"
			+ "\2\u00a6\u00a4\3\2\2\2\u00a7\u00aa\5&\24\2\u00a8\u00ab\5\34\17\2\u00a9"
			+ "\u00ab\5\32\16\2\u00aa\u00a8\3\2\2\2\u00aa\u00a9\3\2\2\2\u00ab\u00ac\3"
			+ "\2\2\2\u00ac\u00ad\5&\24\2\u00ad\u00bd\3\2\2\2\u00ae\u00b1\5&\24\2\u00af"
			+ "\u00b2\5\34\17\2\u00b0\u00b2\5\32\16\2\u00b1\u00af\3\2\2\2\u00b1\u00b0"
			+ "\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\7\61\2\2\u00b4\u00bd\3\2\2\2"
			+ "\u00b5\u00b8\7\61\2\2\u00b6\u00b9\5\34\17\2\u00b7\u00b9\5\32\16\2\u00b8"
			+ "\u00b6\3\2\2\2\u00b8\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\5&"
			+ "\24\2\u00bb\u00bd\3\2\2\2\u00bc\u00a7\3\2\2\2\u00bc\u00ae\3\2\2\2\u00bc"
			+ "\u00b5\3\2\2\2\u00bd\31\3\2\2\2\u00be\u00c1\7\'\2\2\u00bf\u00c1\7(\2\2"
			+ "\u00c0\u00be\3\2\2\2\u00c0\u00bf\3\2\2\2\u00c1\33\3\2\2\2\u00c2\u00c7"
			+ "\7*\2\2\u00c3\u00c7\7)\2\2\u00c4\u00c7\7,\2\2\u00c5\u00c7\7+\2\2\u00c6"
			+ "\u00c2\3\2\2\2\u00c6\u00c3\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c5\3\2"
			+ "\2\2\u00c7\35\3\2\2\2\u00c8\u00c9\7\3\2\2\u00c9\u00ca\5\26\f\2\u00ca\u00cb"
			+ "\7\4\2\2\u00cb\u00cf\3\2\2\2\u00cc\u00cf\5 \21\2\u00cd\u00cf\5&\24\2\u00ce"
			+ "\u00c8\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cd\3\2\2\2\u00cf\37\3\2\2"
			+ "\2\u00d0\u00d1\7\3\2\2\u00d1\u00d2\7.\2\2\u00d2\u00d5\7\4\2\2\u00d3\u00d5"
			+ "\7.\2\2\u00d4\u00d0\3\2\2\2\u00d4\u00d3\3\2\2\2\u00d5!\3\2\2\2\u00d6\u00d7"
			+ "\7\3\2\2\u00d7\u00d8\7.\2\2\u00d8\u00db\7\4\2\2\u00d9\u00db\7\61\2\2\u00da"
			+ "\u00d6\3\2\2\2\u00da\u00d9\3\2\2\2\u00db#\3\2\2\2\u00dc\u00df\5 \21\2"
			+ "\u00dd\u00df\5\"\22\2\u00de\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2\u00df%\3"
			+ "\2\2\2\u00e0\u00e1\7\63\2\2\u00e1\'\3\2\2\2\33.\64?BGOhmrw\u0081\u0099"
			+ "\u009f\u00a2\u00a4\u00aa\u00b1\u00b8\u00bc\u00c0\u00c6\u00ce\u00d4\u00da" + "\u00de";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}