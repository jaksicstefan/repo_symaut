// Generated from C:/repo_symaut/sym_automata/src/cst_parser/CstLexer.g4 by ANTLR 4.5.3

package cst_gen;

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
public class CstLexer extends Lexer {
	static {
		RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int LPAREN = 1, RPAREN = 2, NotOperator = 3, OrOperator = 4, AndOperator = 5, EqualOperator = 6,
			NotEqualOperator = 7, GreaterOrEqualOperator = 8, LesserOrEqualOperator = 9, GreaterOperator = 10,
			LesserOperator = 11, BooleanLiteral = 12, TRUE = 13, FALSE = 14, IntegerLiteral = 15, Identifier = 16,
			LINE_TERMINATOR = 17, WHITESPACE = 18, COMMENT = 19, LINE_COMMENT = 20;
	public static String[] modeNames = { "DEFAULT_MODE" };

	public static final String[] ruleNames = { "LPAREN", "RPAREN", "NotOperator", "OrOperator", "AndOperator",
			"EqualOperator", "NotEqualOperator", "GreaterOrEqualOperator", "LesserOrEqualOperator", "GreaterOperator",
			"LesserOperator", "BooleanLiteral", "TRUE", "FALSE", "IntegerLiteral", "DecimalNumeral", "Digits", "Digit",
			"NonZeroDigit", "DigitsAndUnderscores", "DigitOrUnderscore", "Underscores", "HexNumeral", "HexDigits", "HexDigit",
			"HexDigitsAndUnderscores", "HexDigitOrUnderscore", "BinaryNumeral", "BinaryDigits", "BinaryDigit",
			"BinaryDigitsAndUnderscores", "BinaryDigitOrUnderscore", "Identifier", "IdentifierStart", "IdentifierPart",
			"LetterOrUnderscore", "Letter", "LINE_TERMINATOR", "WHITESPACE", "COMMENT", "LINE_COMMENT" };

	private static final String[] _LITERAL_NAMES = {};
	private static final String[] _SYMBOLIC_NAMES = { null, "LPAREN", "RPAREN", "NotOperator", "OrOperator",
			"AndOperator", "EqualOperator", "NotEqualOperator", "GreaterOrEqualOperator", "LesserOrEqualOperator",
			"GreaterOperator", "LesserOperator", "BooleanLiteral", "TRUE", "FALSE", "IntegerLiteral", "Identifier",
			"LINE_TERMINATOR", "WHITESPACE", "COMMENT", "LINE_COMMENT" };
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

	public CstLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@Override
	public String getGrammarFileName() {
		return "CstLexer.g4";
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

	public static final String _serializedATN = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\26\u0120\b\1\4\2"
			+ "\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"
			+ "\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"
			+ "\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"
			+ "\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"
			+ " \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2"
			+ "\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"
			+ "\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\5\rx"
			+ "\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0082\n\16\3\17\3\17"
			+ "\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u008e\n\17\3\20\3\20\3\20"
			+ "\5\20\u0093\n\20\3\21\3\21\3\21\5\21\u0098\n\21\3\21\3\21\3\21\5\21\u009d"
			+ "\n\21\5\21\u009f\n\21\3\22\3\22\5\22\u00a3\n\22\3\22\5\22\u00a6\n\22\3"
			+ "\23\3\23\5\23\u00aa\n\23\3\24\3\24\3\25\6\25\u00af\n\25\r\25\16\25\u00b0"
			+ "\3\26\3\26\5\26\u00b5\n\26\3\27\6\27\u00b8\n\27\r\27\16\27\u00b9\3\30"
			+ "\3\30\3\30\3\30\3\31\3\31\5\31\u00c2\n\31\3\31\5\31\u00c5\n\31\3\32\3"
			+ "\32\3\33\6\33\u00ca\n\33\r\33\16\33\u00cb\3\34\3\34\5\34\u00d0\n\34\3"
			+ "\35\3\35\3\35\3\35\3\36\3\36\5\36\u00d8\n\36\3\36\5\36\u00db\n\36\3\37"
			+ "\3\37\3 \6 \u00e0\n \r \16 \u00e1\3!\3!\5!\u00e6\n!\3\"\3\"\7\"\u00ea"
			+ "\n\"\f\"\16\"\u00ed\13\"\3#\3#\5#\u00f1\n#\3$\3$\5$\u00f5\n$\3%\3%\5%"
			+ "\u00f9\n%\3&\3&\3\'\3\'\3\'\3\'\3(\6(\u0102\n(\r(\16(\u0103\3(\3(\3)\3"
			+ ")\3)\3)\7)\u010c\n)\f)\16)\u010f\13)\3)\3)\3)\3)\3)\3*\3*\3*\3*\7*\u011a"
			+ "\n*\f*\16*\u011d\13*\3*\3*\3\u010d\2+\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"
			+ "\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\2#\2%\2\'\2)\2+\2-\2/\2\61"
			+ "\2\63\2\65\2\67\29\2;\2=\2?\2A\2C\22E\2G\2I\2K\2M\23O\24Q\25S\26\3\2\13"
			+ "\3\2\63;\4\2ZZzz\5\2\62;CHch\4\2DDdd\3\2\62\63\4\2C\\c|\3\2\f\f\5\2\13"
			+ "\13\16\17\"\"\4\2\f\f\17\17\u0127\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"
			+ "\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"
			+ "\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"
			+ "\2\2\37\3\2\2\2\2C\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2"
			+ "\3U\3\2\2\2\5W\3\2\2\2\7Y\3\2\2\2\t]\3\2\2\2\13`\3\2\2\2\rd\3\2\2\2\17"
			+ "g\3\2\2\2\21k\3\2\2\2\23n\3\2\2\2\25q\3\2\2\2\27s\3\2\2\2\31w\3\2\2\2"
			+ "\33\u0081\3\2\2\2\35\u008d\3\2\2\2\37\u0092\3\2\2\2!\u009e\3\2\2\2#\u00a0"
			+ "\3\2\2\2%\u00a9\3\2\2\2\'\u00ab\3\2\2\2)\u00ae\3\2\2\2+\u00b4\3\2\2\2"
			+ "-\u00b7\3\2\2\2/\u00bb\3\2\2\2\61\u00bf\3\2\2\2\63\u00c6\3\2\2\2\65\u00c9"
			+ "\3\2\2\2\67\u00cf\3\2\2\29\u00d1\3\2\2\2;\u00d5\3\2\2\2=\u00dc\3\2\2\2"
			+ "?\u00df\3\2\2\2A\u00e5\3\2\2\2C\u00e7\3\2\2\2E\u00f0\3\2\2\2G\u00f4\3"
			+ "\2\2\2I\u00f8\3\2\2\2K\u00fa\3\2\2\2M\u00fc\3\2\2\2O\u0101\3\2\2\2Q\u0107"
			+ "\3\2\2\2S\u0115\3\2\2\2UV\7*\2\2V\4\3\2\2\2WX\7+\2\2X\6\3\2\2\2YZ\7p\2"
			+ "\2Z[\7q\2\2[\\\7v\2\2\\\b\3\2\2\2]^\7q\2\2^_\7t\2\2_\n\3\2\2\2`a\7c\2"
			+ "\2ab\7p\2\2bc\7f\2\2c\f\3\2\2\2de\7?\2\2ef\7?\2\2f\16\3\2\2\2gh\7#\2\2"
			+ "hi\7?\2\2ij\7?\2\2j\20\3\2\2\2kl\7@\2\2lm\7?\2\2m\22\3\2\2\2no\7>\2\2"
			+ "op\7?\2\2p\24\3\2\2\2qr\7@\2\2r\26\3\2\2\2st\7>\2\2t\30\3\2\2\2ux\5\33"
			+ "\16\2vx\5\35\17\2wu\3\2\2\2wv\3\2\2\2x\32\3\2\2\2yz\7v\2\2z{\7t\2\2{|"
			+ "\7w\2\2|\u0082\7g\2\2}~\7V\2\2~\177\7T\2\2\177\u0080\7W\2\2\u0080\u0082"
			+ "\7G\2\2\u0081y\3\2\2\2\u0081}\3\2\2\2\u0082\34\3\2\2\2\u0083\u0084\7h"
			+ "\2\2\u0084\u0085\7c\2\2\u0085\u0086\7n\2\2\u0086\u0087\7u\2\2\u0087\u008e"
			+ "\7g\2\2\u0088\u0089\7H\2\2\u0089\u008a\7C\2\2\u008a\u008b\7N\2\2\u008b"
			+ "\u008c\7U\2\2\u008c\u008e\7G\2\2\u008d\u0083\3\2\2\2\u008d\u0088\3\2\2"
			+ "\2\u008e\36\3\2\2\2\u008f\u0093\5!\21\2\u0090\u0093\5/\30\2\u0091\u0093"
			+ "\59\35\2\u0092\u008f\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093"
			+ " \3\2\2\2\u0094\u009f\7\62\2\2\u0095\u009c\5\'\24\2\u0096\u0098\5#\22"
			+ "\2\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009d\3\2\2\2\u0099\u009a"
			+ "\5-\27\2\u009a\u009b\5#\22\2\u009b\u009d\3\2\2\2\u009c\u0097\3\2\2\2\u009c"
			+ "\u0099\3\2\2\2\u009d\u009f\3\2\2\2\u009e\u0094\3\2\2\2\u009e\u0095\3\2"
			+ "\2\2\u009f\"\3\2\2\2\u00a0\u00a5\5%\23\2\u00a1\u00a3\5)\25\2\u00a2\u00a1"
			+ "\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\5%\23\2\u00a5"
			+ "\u00a2\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6$\3\2\2\2\u00a7\u00aa\7\62\2\2"
			+ "\u00a8\u00aa\5\'\24\2\u00a9\u00a7\3\2\2\2\u00a9\u00a8\3\2\2\2\u00aa&\3"
			+ "\2\2\2\u00ab\u00ac\t\2\2\2\u00ac(\3\2\2\2\u00ad\u00af\5+\26\2\u00ae\u00ad"
			+ "\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"
			+ "*\3\2\2\2\u00b2\u00b5\5%\23\2\u00b3\u00b5\7a\2\2\u00b4\u00b2\3\2\2\2\u00b4"
			+ "\u00b3\3\2\2\2\u00b5,\3\2\2\2\u00b6\u00b8\7a\2\2\u00b7\u00b6\3\2\2\2\u00b8"
			+ "\u00b9\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba.\3\2\2\2"
			+ "\u00bb\u00bc\7\62\2\2\u00bc\u00bd\t\3\2\2\u00bd\u00be\5\61\31\2\u00be"
			+ "\60\3\2\2\2\u00bf\u00c4\5\63\32\2\u00c0\u00c2\5\65\33\2\u00c1\u00c0\3"
			+ "\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c5\5\63\32\2\u00c4"
			+ "\u00c1\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\62\3\2\2\2\u00c6\u00c7\t\4\2"
			+ "\2\u00c7\64\3\2\2\2\u00c8\u00ca\5\67\34\2\u00c9\u00c8\3\2\2\2\u00ca\u00cb"
			+ "\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\66\3\2\2\2\u00cd"
			+ "\u00d0\5\63\32\2\u00ce\u00d0\7a\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00ce\3"
			+ "\2\2\2\u00d08\3\2\2\2\u00d1\u00d2\7\62\2\2\u00d2\u00d3\t\5\2\2\u00d3\u00d4"
			+ "\5;\36\2\u00d4:\3\2\2\2\u00d5\u00da\5=\37\2\u00d6\u00d8\5? \2\u00d7\u00d6"
			+ "\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00db\5=\37\2\u00da"
			+ "\u00d7\3\2\2\2\u00da\u00db\3\2\2\2\u00db<\3\2\2\2\u00dc\u00dd\t\6\2\2"
			+ "\u00dd>\3\2\2\2\u00de\u00e0\5A!\2\u00df\u00de\3\2\2\2\u00e0\u00e1\3\2"
			+ "\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2@\3\2\2\2\u00e3\u00e6"
			+ "\5=\37\2\u00e4\u00e6\7a\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e4\3\2\2\2\u00e6"
			+ "B\3\2\2\2\u00e7\u00eb\5E#\2\u00e8\u00ea\5G$\2\u00e9\u00e8\3\2\2\2\u00ea"
			+ "\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ecD\3\2\2\2"
			+ "\u00ed\u00eb\3\2\2\2\u00ee\u00f1\5I%\2\u00ef\u00f1\7&\2\2\u00f0\u00ee"
			+ "\3\2\2\2\u00f0\u00ef\3\2\2\2\u00f1F\3\2\2\2\u00f2\u00f5\5E#\2\u00f3\u00f5"
			+ "\5%\23\2\u00f4\u00f2\3\2\2\2\u00f4\u00f3\3\2\2\2\u00f5H\3\2\2\2\u00f6"
			+ "\u00f9\5K&\2\u00f7\u00f9\7a\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f7\3\2\2"
			+ "\2\u00f9J\3\2\2\2\u00fa\u00fb\t\7\2\2\u00fbL\3\2\2\2\u00fc\u00fd\t\b\2"
			+ "\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\b\'\2\2\u00ffN\3\2\2\2\u0100\u0102"
			+ "\t\t\2\2\u0101\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0101\3\2\2\2\u0103"
			+ "\u0104\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0106\b(\2\2\u0106P\3\2\2\2\u0107"
			+ "\u0108\7\61\2\2\u0108\u0109\7,\2\2\u0109\u010d\3\2\2\2\u010a\u010c\13"
			+ "\2\2\2\u010b\u010a\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010e\3\2\2\2\u010d"
			+ "\u010b\3\2\2\2\u010e\u0110\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u0111\7,"
			+ "\2\2\u0111\u0112\7\61\2\2\u0112\u0113\3\2\2\2\u0113\u0114\b)\2\2\u0114"
			+ "R\3\2\2\2\u0115\u0116\7\61\2\2\u0116\u0117\7\61\2\2\u0117\u011b\3\2\2"
			+ "\2\u0118\u011a\n\n\2\2\u0119\u0118\3\2\2\2\u011a\u011d\3\2\2\2\u011b\u0119"
			+ "\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011e\3\2\2\2\u011d\u011b\3\2\2\2\u011e"
			+ "\u011f\b*\2\2\u011fT\3\2\2\2\37\2w\u0081\u008d\u0092\u0097\u009c\u009e"
			+ "\u00a2\u00a5\u00a9\u00b0\u00b4\u00b9\u00c1\u00c4\u00cb\u00cf\u00d7\u00da"
			+ "\u00e1\u00e5\u00eb\u00f0\u00f4\u00f8\u0103\u010d\u011b\3\b\2\2";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}