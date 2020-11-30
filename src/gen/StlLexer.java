// Generated from C:/repo_symaut/sym_automata/src/parser/StlLexer.g4 by ANTLR 4.5.3

package gen;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class StlLexer extends Lexer {
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
	public static String[] modeNames = { "DEFAULT_MODE" };

	public static final String[] ruleNames = { "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMICOLON",
			"COLON", "COMMA", "DOT", "Input", "Output", "Internal", "Constant", "DomainTypeReal", "DomainTypeInt",
			"DomainTypeBool", "Assertion", "From", "NotOperator", "OrOperator", "AndOperator", "IffOperator",
			"ImpliesOperator", "XorOperator", "RiseOperator", "FallOperator", "AlwaysOperator", "EventuallyOperator",
			"UntilOperator", "HistoricallyOperator", "OnceOperator", "SinceOperator", "NextOperator", "OracleOperator",
			"PreviousOperator", "EqualOperator", "NotEqualOperator", "GreaterOrEqualOperator", "LesserOrEqualOperator",
			"GreaterOperator", "LesserOperator", "EQUAL", "BooleanLiteral", "TRUE", "FALSE", "IntegerLiteral",
			"DecimalNumeral", "Digits", "Digit", "NonZeroDigit", "DigitsAndUnderscores", "DigitOrUnderscore", "Underscores",
			"HexNumeral", "HexDigits", "HexDigit", "HexDigitsAndUnderscores", "HexDigitOrUnderscore", "BinaryNumeral",
			"BinaryDigits", "BinaryDigit", "BinaryDigitsAndUnderscores", "BinaryDigitOrUnderscore", "RealLiteral",
			"DecimalRealLiteral", "ExponentPart", "ExponentIndicator", "SignedInteger", "Sign", "Identifier",
			"IdentifierStart", "IdentifierPart", "LetterOrUnderscore", "Letter", "LINE_TERMINATOR", "WHITESPACE", "COMMENT",
			"LINE_COMMENT" };

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

	public StlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@Override
	public String getGrammarFileName() {
		return "StlLexer.g4";
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
	public String[] getModeNames() {
		return modeNames;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	public static final String _serializedATN = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\67\u023c\b\1\4\2"
			+ "\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"
			+ "\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"
			+ "\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"
			+ "\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"
			+ " \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"
			+ "+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"
			+ "\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"
			+ "=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4"
			+ "I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\3\2\3\2\3\3\3\3\3\4\3\4"
			+ "\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f"
			+ "\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16"
			+ "\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20"
			+ "\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23"
			+ "\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"
			+ "\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\5\30"
			+ "\u0100\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u010b\n"
			+ "\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3"
			+ "\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3"
			+ "\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3"
			+ " \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3"
			+ "#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3\'\3"
			+ "(\3(\3(\3)\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\5-\u0171\n-\3.\3.\3.\3.\3.\3"
			+ ".\3.\3.\5.\u017b\n.\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\5/\u0187\n/\3\60\3\60"
			+ "\3\60\5\60\u018c\n\60\3\61\3\61\3\61\5\61\u0191\n\61\3\61\3\61\3\61\5"
			+ "\61\u0196\n\61\5\61\u0198\n\61\3\62\3\62\5\62\u019c\n\62\3\62\5\62\u019f"
			+ "\n\62\3\63\3\63\5\63\u01a3\n\63\3\64\3\64\3\65\6\65\u01a8\n\65\r\65\16"
			+ "\65\u01a9\3\66\3\66\5\66\u01ae\n\66\3\67\6\67\u01b1\n\67\r\67\16\67\u01b2"
			+ "\38\38\38\38\39\39\59\u01bb\n9\39\59\u01be\n9\3:\3:\3;\6;\u01c3\n;\r;"
			+ "\16;\u01c4\3<\3<\5<\u01c9\n<\3=\3=\3=\3=\3>\3>\5>\u01d1\n>\3>\5>\u01d4"
			+ "\n>\3?\3?\3@\6@\u01d9\n@\r@\16@\u01da\3A\3A\5A\u01df\nA\3B\3B\3C\3C\3"
			+ "C\5C\u01e6\nC\3C\5C\u01e9\nC\3C\3C\3C\5C\u01ee\nC\3C\3C\3C\5C\u01f3\n"
			+ "C\3D\3D\3D\3E\3E\3F\5F\u01fb\nF\3F\6F\u01fe\nF\rF\16F\u01ff\3G\3G\3H\3"
			+ "H\7H\u0206\nH\fH\16H\u0209\13H\3I\3I\5I\u020d\nI\3J\3J\5J\u0211\nJ\3K"
			+ "\3K\5K\u0215\nK\3L\3L\3M\3M\3M\3M\3N\6N\u021e\nN\rN\16N\u021f\3N\3N\3"
			+ "O\3O\3O\3O\7O\u0228\nO\fO\16O\u022b\13O\3O\3O\3O\3O\3O\3P\3P\3P\3P\7P"
			+ "\u0236\nP\fP\16P\u0239\13P\3P\3P\3\u0229\2Q\3\3\5\4\7\5\t\6\13\7\r\b\17"
			+ "\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"
			+ "\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+"
			+ "U,W-Y.[/]\60_\61a\2c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2w\2y\2{\2}\2\177\2\u0081"
			+ "\2\u0083\62\u0085\2\u0087\2\u0089\2\u008b\2\u008d\2\u008f\63\u0091\2\u0093"
			+ "\2\u0095\2\u0097\2\u0099\64\u009b\65\u009d\66\u009f\67\3\2\r\3\2\63;\4"
			+ "\2ZZzz\5\2\62;CHch\4\2DDdd\3\2\62\63\4\2GGgg\4\2--//\4\2C\\c|\3\2\f\f"
			+ "\5\2\13\13\16\17\"\"\4\2\f\f\17\17\u0247\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"
			+ "\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"
			+ "\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"
			+ "\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"
			+ "\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"
			+ "\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2"
			+ "A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3"
			+ "\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2"
			+ "\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2\u0083\3\2\2\2\2\u008f\3\2\2\2\2"
			+ "\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2\2\3\u00a1"
			+ "\3\2\2\2\5\u00a3\3\2\2\2\7\u00a5\3\2\2\2\t\u00a7\3\2\2\2\13\u00a9\3\2"
			+ "\2\2\r\u00ab\3\2\2\2\17\u00ad\3\2\2\2\21\u00af\3\2\2\2\23\u00b1\3\2\2"
			+ "\2\25\u00b3\3\2\2\2\27\u00b5\3\2\2\2\31\u00bb\3\2\2\2\33\u00c2\3\2\2\2"
			+ "\35\u00cb\3\2\2\2\37\u00d1\3\2\2\2!\u00d6\3\2\2\2#\u00da\3\2\2\2%\u00df"
			+ "\3\2\2\2\'\u00e9\3\2\2\2)\u00ee\3\2\2\2+\u00f2\3\2\2\2-\u00f5\3\2\2\2"
			+ "/\u00ff\3\2\2\2\61\u010a\3\2\2\2\63\u010c\3\2\2\2\65\u0110\3\2\2\2\67"
			+ "\u0115\3\2\2\29\u011a\3\2\2\2;\u0121\3\2\2\2=\u012c\3\2\2\2?\u0132\3\2"
			+ "\2\2A\u013f\3\2\2\2C\u0144\3\2\2\2E\u014a\3\2\2\2G\u014f\3\2\2\2I\u0156"
			+ "\3\2\2\2K\u015b\3\2\2\2M\u015e\3\2\2\2O\u0162\3\2\2\2Q\u0165\3\2\2\2S"
			+ "\u0168\3\2\2\2U\u016a\3\2\2\2W\u016c\3\2\2\2Y\u0170\3\2\2\2[\u017a\3\2"
			+ "\2\2]\u0186\3\2\2\2_\u018b\3\2\2\2a\u0197\3\2\2\2c\u0199\3\2\2\2e\u01a2"
			+ "\3\2\2\2g\u01a4\3\2\2\2i\u01a7\3\2\2\2k\u01ad\3\2\2\2m\u01b0\3\2\2\2o"
			+ "\u01b4\3\2\2\2q\u01b8\3\2\2\2s\u01bf\3\2\2\2u\u01c2\3\2\2\2w\u01c8\3\2"
			+ "\2\2y\u01ca\3\2\2\2{\u01ce\3\2\2\2}\u01d5\3\2\2\2\177\u01d8\3\2\2\2\u0081"
			+ "\u01de\3\2\2\2\u0083\u01e0\3\2\2\2\u0085\u01f2\3\2\2\2\u0087\u01f4\3\2"
			+ "\2\2\u0089\u01f7\3\2\2\2\u008b\u01fa\3\2\2\2\u008d\u0201\3\2\2\2\u008f"
			+ "\u0203\3\2\2\2\u0091\u020c\3\2\2\2\u0093\u0210\3\2\2\2\u0095\u0214\3\2"
			+ "\2\2\u0097\u0216\3\2\2\2\u0099\u0218\3\2\2\2\u009b\u021d\3\2\2\2\u009d"
			+ "\u0223\3\2\2\2\u009f\u0231\3\2\2\2\u00a1\u00a2\7*\2\2\u00a2\4\3\2\2\2"
			+ "\u00a3\u00a4\7+\2\2\u00a4\6\3\2\2\2\u00a5\u00a6\7}\2\2\u00a6\b\3\2\2\2"
			+ "\u00a7\u00a8\7\177\2\2\u00a8\n\3\2\2\2\u00a9\u00aa\7]\2\2\u00aa\f\3\2"
			+ "\2\2\u00ab\u00ac\7_\2\2\u00ac\16\3\2\2\2\u00ad\u00ae\7=\2\2\u00ae\20\3"
			+ "\2\2\2\u00af\u00b0\7<\2\2\u00b0\22\3\2\2\2\u00b1\u00b2\7.\2\2\u00b2\24"
			+ "\3\2\2\2\u00b3\u00b4\7\60\2\2\u00b4\26\3\2\2\2\u00b5\u00b6\7k\2\2\u00b6"
			+ "\u00b7\7p\2\2\u00b7\u00b8\7r\2\2\u00b8\u00b9\7w\2\2\u00b9\u00ba\7v\2\2"
			+ "\u00ba\30\3\2\2\2\u00bb\u00bc\7q\2\2\u00bc\u00bd\7w\2\2\u00bd\u00be\7"
			+ "v\2\2\u00be\u00bf\7r\2\2\u00bf\u00c0\7w\2\2\u00c0\u00c1\7v\2\2\u00c1\32"
			+ "\3\2\2\2\u00c2\u00c3\7k\2\2\u00c3\u00c4\7p\2\2\u00c4\u00c5\7v\2\2\u00c5"
			+ "\u00c6\7g\2\2\u00c6\u00c7\7t\2\2\u00c7\u00c8\7p\2\2\u00c8\u00c9\7c\2\2"
			+ "\u00c9\u00ca\7n\2\2\u00ca\34\3\2\2\2\u00cb\u00cc\7e\2\2\u00cc\u00cd\7"
			+ "q\2\2\u00cd\u00ce\7p\2\2\u00ce\u00cf\7u\2\2\u00cf\u00d0\7v\2\2\u00d0\36"
			+ "\3\2\2\2\u00d1\u00d2\7t\2\2\u00d2\u00d3\7g\2\2\u00d3\u00d4\7c\2\2\u00d4"
			+ "\u00d5\7n\2\2\u00d5 \3\2\2\2\u00d6\u00d7\7k\2\2\u00d7\u00d8\7p\2\2\u00d8"
			+ "\u00d9\7v\2\2\u00d9\"\3\2\2\2\u00da\u00db\7d\2\2\u00db\u00dc\7q\2\2\u00dc"
			+ "\u00dd\7q\2\2\u00dd\u00de\7n\2\2\u00de$\3\2\2\2\u00df\u00e0\7c\2\2\u00e0"
			+ "\u00e1\7u\2\2\u00e1\u00e2\7u\2\2\u00e2\u00e3\7g\2\2\u00e3\u00e4\7t\2\2"
			+ "\u00e4\u00e5\7v\2\2\u00e5\u00e6\7k\2\2\u00e6\u00e7\7q\2\2\u00e7\u00e8"
			+ "\7p\2\2\u00e8&\3\2\2\2\u00e9\u00ea\7h\2\2\u00ea\u00eb\7t\2\2\u00eb\u00ec"
			+ "\7q\2\2\u00ec\u00ed\7o\2\2\u00ed(\3\2\2\2\u00ee\u00ef\7p\2\2\u00ef\u00f0"
			+ "\7q\2\2\u00f0\u00f1\7v\2\2\u00f1*\3\2\2\2\u00f2\u00f3\7q\2\2\u00f3\u00f4"
			+ "\7t\2\2\u00f4,\3\2\2\2\u00f5\u00f6\7c\2\2\u00f6\u00f7\7p\2\2\u00f7\u00f8"
			+ "\7f\2\2\u00f8.\3\2\2\2\u00f9\u00fa\7k\2\2\u00fa\u00fb\7h\2\2\u00fb\u0100"
			+ "\7h\2\2\u00fc\u00fd\7>\2\2\u00fd\u00fe\7/\2\2\u00fe\u0100\7@\2\2\u00ff"
			+ "\u00f9\3\2\2\2\u00ff\u00fc\3\2\2\2\u0100\60\3\2\2\2\u0101\u0102\7k\2\2"
			+ "\u0102\u0103\7o\2\2\u0103\u0104\7r\2\2\u0104\u0105\7n\2\2\u0105\u0106"
			+ "\7k\2\2\u0106\u0107\7g\2\2\u0107\u010b\7u\2\2\u0108\u0109\7/\2\2\u0109"
			+ "\u010b\7@\2\2\u010a\u0101\3\2\2\2\u010a\u0108\3\2\2\2\u010b\62\3\2\2\2"
			+ "\u010c\u010d\7z\2\2\u010d\u010e\7q\2\2\u010e\u010f\7t\2\2\u010f\64\3\2"
			+ "\2\2\u0110\u0111\7t\2\2\u0111\u0112\7k\2\2\u0112\u0113\7u\2\2\u0113\u0114"
			+ "\7g\2\2\u0114\66\3\2\2\2\u0115\u0116\7h\2\2\u0116\u0117\7c\2\2\u0117\u0118"
			+ "\7n\2\2\u0118\u0119\7n\2\2\u01198\3\2\2\2\u011a\u011b\7c\2\2\u011b\u011c"
			+ "\7n\2\2\u011c\u011d\7y\2\2\u011d\u011e\7c\2\2\u011e\u011f\7{\2\2\u011f"
			+ "\u0120\7u\2\2\u0120:\3\2\2\2\u0121\u0122\7g\2\2\u0122\u0123\7x\2\2\u0123"
			+ "\u0124\7g\2\2\u0124\u0125\7p\2\2\u0125\u0126\7v\2\2\u0126\u0127\7w\2\2"
			+ "\u0127\u0128\7c\2\2\u0128\u0129\7n\2\2\u0129\u012a\7n\2\2\u012a\u012b"
			+ "\7{\2\2\u012b<\3\2\2\2\u012c\u012d\7w\2\2\u012d\u012e\7p\2\2\u012e\u012f"
			+ "\7v\2\2\u012f\u0130\7k\2\2\u0130\u0131\7n\2\2\u0131>\3\2\2\2\u0132\u0133"
			+ "\7j\2\2\u0133\u0134\7k\2\2\u0134\u0135\7u\2\2\u0135\u0136\7v\2\2\u0136"
			+ "\u0137\7q\2\2\u0137\u0138\7t\2\2\u0138\u0139\7k\2\2\u0139\u013a\7e\2\2"
			+ "\u013a\u013b\7c\2\2\u013b\u013c\7n\2\2\u013c\u013d\7n\2\2\u013d\u013e"
			+ "\7{\2\2\u013e@\3\2\2\2\u013f\u0140\7q\2\2\u0140\u0141\7p\2\2\u0141\u0142"
			+ "\7e\2\2\u0142\u0143\7g\2\2\u0143B\3\2\2\2\u0144\u0145\7u\2\2\u0145\u0146"
			+ "\7k\2\2\u0146\u0147\7p\2\2\u0147\u0148\7e\2\2\u0148\u0149\7g\2\2\u0149"
			+ "D\3\2\2\2\u014a\u014b\7p\2\2\u014b\u014c\7g\2\2\u014c\u014d\7z\2\2\u014d"
			+ "\u014e\7v\2\2\u014eF\3\2\2\2\u014f\u0150\7q\2\2\u0150\u0151\7t\2\2\u0151"
			+ "\u0152\7c\2\2\u0152\u0153\7e\2\2\u0153\u0154\7n\2\2\u0154\u0155\7g\2\2"
			+ "\u0155H\3\2\2\2\u0156\u0157\7r\2\2\u0157\u0158\7t\2\2\u0158\u0159\7g\2"
			+ "\2\u0159\u015a\7x\2\2\u015aJ\3\2\2\2\u015b\u015c\7?\2\2\u015c\u015d\7"
			+ "?\2\2\u015dL\3\2\2\2\u015e\u015f\7#\2\2\u015f\u0160\7?\2\2\u0160\u0161"
			+ "\7?\2\2\u0161N\3\2\2\2\u0162\u0163\7@\2\2\u0163\u0164\7?\2\2\u0164P\3"
			+ "\2\2\2\u0165\u0166\7>\2\2\u0166\u0167\7?\2\2\u0167R\3\2\2\2\u0168\u0169"
			+ "\7@\2\2\u0169T\3\2\2\2\u016a\u016b\7>\2\2\u016bV\3\2\2\2\u016c\u016d\7"
			+ "?\2\2\u016dX\3\2\2\2\u016e\u0171\5[.\2\u016f\u0171\5]/\2\u0170\u016e\3"
			+ "\2\2\2\u0170\u016f\3\2\2\2\u0171Z\3\2\2\2\u0172\u0173\7v\2\2\u0173\u0174"
			+ "\7t\2\2\u0174\u0175\7w\2\2\u0175\u017b\7g\2\2\u0176\u0177\7V\2\2\u0177"
			+ "\u0178\7T\2\2\u0178\u0179\7W\2\2\u0179\u017b\7G\2\2\u017a\u0172\3\2\2"
			+ "\2\u017a\u0176\3\2\2\2\u017b\\\3\2\2\2\u017c\u017d\7h\2\2\u017d\u017e"
			+ "\7c\2\2\u017e\u017f\7n\2\2\u017f\u0180\7u\2\2\u0180\u0187\7g\2\2\u0181"
			+ "\u0182\7H\2\2\u0182\u0183\7C\2\2\u0183\u0184\7N\2\2\u0184\u0185\7U\2\2"
			+ "\u0185\u0187\7G\2\2\u0186\u017c\3\2\2\2\u0186\u0181\3\2\2\2\u0187^\3\2"
			+ "\2\2\u0188\u018c\5a\61\2\u0189\u018c\5o8\2\u018a\u018c\5y=\2\u018b\u0188"
			+ "\3\2\2\2\u018b\u0189\3\2\2\2\u018b\u018a\3\2\2\2\u018c`\3\2\2\2\u018d"
			+ "\u0198\7\62\2\2\u018e\u0195\5g\64\2\u018f\u0191\5c\62\2\u0190\u018f\3"
			+ "\2\2\2\u0190\u0191\3\2\2\2\u0191\u0196\3\2\2\2\u0192\u0193\5m\67\2\u0193"
			+ "\u0194\5c\62\2\u0194\u0196\3\2\2\2\u0195\u0190\3\2\2\2\u0195\u0192\3\2"
			+ "\2\2\u0196\u0198\3\2\2\2\u0197\u018d\3\2\2\2\u0197\u018e\3\2\2\2\u0198"
			+ "b\3\2\2\2\u0199\u019e\5e\63\2\u019a\u019c\5i\65\2\u019b\u019a\3\2\2\2"
			+ "\u019b\u019c\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019f\5e\63\2\u019e\u019b"
			+ "\3\2\2\2\u019e\u019f\3\2\2\2\u019fd\3\2\2\2\u01a0\u01a3\7\62\2\2\u01a1"
			+ "\u01a3\5g\64\2\u01a2\u01a0\3\2\2\2\u01a2\u01a1\3\2\2\2\u01a3f\3\2\2\2"
			+ "\u01a4\u01a5\t\2\2\2\u01a5h\3\2\2\2\u01a6\u01a8\5k\66\2\u01a7\u01a6\3"
			+ "\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01a7\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa"
			+ "j\3\2\2\2\u01ab\u01ae\5e\63\2\u01ac\u01ae\7a\2\2\u01ad\u01ab\3\2\2\2\u01ad"
			+ "\u01ac\3\2\2\2\u01ael\3\2\2\2\u01af\u01b1\7a\2\2\u01b0\u01af\3\2\2\2\u01b1"
			+ "\u01b2\3\2\2\2\u01b2\u01b0\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3n\3\2\2\2"
			+ "\u01b4\u01b5\7\62\2\2\u01b5\u01b6\t\3\2\2\u01b6\u01b7\5q9\2\u01b7p\3\2"
			+ "\2\2\u01b8\u01bd\5s:\2\u01b9\u01bb\5u;\2\u01ba\u01b9\3\2\2\2\u01ba\u01bb"
			+ "\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bc\u01be\5s:\2\u01bd\u01ba\3\2\2\2\u01bd"
			+ "\u01be\3\2\2\2\u01ber\3\2\2\2\u01bf\u01c0\t\4\2\2\u01c0t\3\2\2\2\u01c1"
			+ "\u01c3\5w<\2\u01c2\u01c1\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c2\3\2\2"
			+ "\2\u01c4\u01c5\3\2\2\2\u01c5v\3\2\2\2\u01c6\u01c9\5s:\2\u01c7\u01c9\7"
			+ "a\2\2\u01c8\u01c6\3\2\2\2\u01c8\u01c7\3\2\2\2\u01c9x\3\2\2\2\u01ca\u01cb"
			+ "\7\62\2\2\u01cb\u01cc\t\5\2\2\u01cc\u01cd\5{>\2\u01cdz\3\2\2\2\u01ce\u01d3"
			+ "\5}?\2\u01cf\u01d1\5\177@\2\u01d0\u01cf\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1"
			+ "\u01d2\3\2\2\2\u01d2\u01d4\5}?\2\u01d3\u01d0\3\2\2\2\u01d3\u01d4\3\2\2"
			+ "\2\u01d4|\3\2\2\2\u01d5\u01d6\t\6\2\2\u01d6~\3\2\2\2\u01d7\u01d9\5\u0081"
			+ "A\2\u01d8\u01d7\3\2\2\2\u01d9\u01da\3\2\2\2\u01da\u01d8\3\2\2\2\u01da"
			+ "\u01db\3\2\2\2\u01db\u0080\3\2\2\2\u01dc\u01df\5}?\2\u01dd\u01df\7a\2"
			+ "\2\u01de\u01dc\3\2\2\2\u01de\u01dd\3\2\2\2\u01df\u0082\3\2\2\2\u01e0\u01e1"
			+ "\5\u0085C\2\u01e1\u0084\3\2\2\2\u01e2\u01e3\5c\62\2\u01e3\u01e5\7\60\2"
			+ "\2\u01e4\u01e6\5c\62\2\u01e5\u01e4\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6\u01e8"
			+ "\3\2\2\2\u01e7\u01e9\5\u0087D\2\u01e8\u01e7\3\2\2\2\u01e8\u01e9\3\2\2"
			+ "\2\u01e9\u01f3\3\2\2\2\u01ea\u01eb\7\60\2\2\u01eb\u01ed\5c\62\2\u01ec"
			+ "\u01ee\5\u0087D\2\u01ed\u01ec\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01f3"
			+ "\3\2\2\2\u01ef\u01f0\5c\62\2\u01f0\u01f1\5\u0087D\2\u01f1\u01f3\3\2\2"
			+ "\2\u01f2\u01e2\3\2\2\2\u01f2\u01ea\3\2\2\2\u01f2\u01ef\3\2\2\2\u01f3\u0086"
			+ "\3\2\2\2\u01f4\u01f5\5\u0089E\2\u01f5\u01f6\5\u008bF\2\u01f6\u0088\3\2"
			+ "\2\2\u01f7\u01f8\t\7\2\2\u01f8\u008a\3\2\2\2\u01f9\u01fb\5\u008dG\2\u01fa"
			+ "\u01f9\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb\u01fd\3\2\2\2\u01fc\u01fe\5e"
			+ "\63\2\u01fd\u01fc\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff\u01fd\3\2\2\2\u01ff"
			+ "\u0200\3\2\2\2\u0200\u008c\3\2\2\2\u0201\u0202\t\b\2\2\u0202\u008e\3\2"
			+ "\2\2\u0203\u0207\5\u0091I\2\u0204\u0206\5\u0093J\2\u0205\u0204\3\2\2\2"
			+ "\u0206\u0209\3\2\2\2\u0207\u0205\3\2\2\2\u0207\u0208\3\2\2\2\u0208\u0090"
			+ "\3\2\2\2\u0209\u0207\3\2\2\2\u020a\u020d\5\u0095K\2\u020b\u020d\7&\2\2"
			+ "\u020c\u020a\3\2\2\2\u020c\u020b\3\2\2\2\u020d\u0092\3\2\2\2\u020e\u0211"
			+ "\5\u0091I\2\u020f\u0211\5e\63\2\u0210\u020e\3\2\2\2\u0210\u020f\3\2\2"
			+ "\2\u0211\u0094\3\2\2\2\u0212\u0215\5\u0097L\2\u0213\u0215\7a\2\2\u0214"
			+ "\u0212\3\2\2\2\u0214\u0213\3\2\2\2\u0215\u0096\3\2\2\2\u0216\u0217\t\t"
			+ "\2\2\u0217\u0098\3\2\2\2\u0218\u0219\t\n\2\2\u0219\u021a\3\2\2\2\u021a"
			+ "\u021b\bM\2\2\u021b\u009a\3\2\2\2\u021c\u021e\t\13\2\2\u021d\u021c\3\2"
			+ "\2\2\u021e\u021f\3\2\2\2\u021f\u021d\3\2\2\2\u021f\u0220\3\2\2\2\u0220"
			+ "\u0221\3\2\2\2\u0221\u0222\bN\2\2\u0222\u009c\3\2\2\2\u0223\u0224\7\61"
			+ "\2\2\u0224\u0225\7,\2\2\u0225\u0229\3\2\2\2\u0226\u0228\13\2\2\2\u0227"
			+ "\u0226\3\2\2\2\u0228\u022b\3\2\2\2\u0229\u022a\3\2\2\2\u0229\u0227\3\2"
			+ "\2\2\u022a\u022c\3\2\2\2\u022b\u0229\3\2\2\2\u022c\u022d\7,\2\2\u022d"
			+ "\u022e\7\61\2\2\u022e\u022f\3\2\2\2\u022f\u0230\bO\2\2\u0230\u009e\3\2"
			+ "\2\2\u0231\u0232\7\61\2\2\u0232\u0233\7\61\2\2\u0233\u0237\3\2\2\2\u0234"
			+ "\u0236\n\f\2\2\u0235\u0234\3\2\2\2\u0236\u0239\3\2\2\2\u0237\u0235\3\2"
			+ "\2\2\u0237\u0238\3\2\2\2\u0238\u023a\3\2\2\2\u0239\u0237\3\2\2\2\u023a"
			+ "\u023b\bP\2\2\u023b\u00a0\3\2\2\2\'\2\u00ff\u010a\u0170\u017a\u0186\u018b"
			+ "\u0190\u0195\u0197\u019b\u019e\u01a2\u01a9\u01ad\u01b2\u01ba\u01bd\u01c4"
			+ "\u01c8\u01d0\u01d3\u01da\u01de\u01e5\u01e8\u01ed\u01f2\u01fa\u01ff\u0207"
			+ "\u020c\u0210\u0214\u021f\u0229\u0237\3\b\2\2";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}