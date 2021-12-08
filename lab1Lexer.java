// Generated from lab1.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class lab1Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, Hexadecimal_prefix=15, 
		Decimal_const=16, Octal_const=17, Hexadecimal_const=18, Ident=19, Plus=20, 
		Sub=21, Mul=22, Div=23, Mod=24, Not=25, Equal=26, NEqual=27, Lt=28, Le=29, 
		Gt=30, Ge=31, WHITE_SPACE=32;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "Hexadecimal_prefix", "Decimal_const", 
			"Octal_const", "Hexadecimal_const", "Ident", "Plus", "Sub", "Mul", "Div", 
			"Mod", "Not", "Equal", "NEqual", "Lt", "Le", "Gt", "Ge", "WHITE_SPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'int'", "'{'", "'}'", "'const'", "','", "';'", "'='", 
			"'return'", "'if'", "'else'", "'||'", "'&&'", null, null, null, null, 
			null, "'+'", "'-'", "'*'", "'/'", "'%'", "'!'", "'=='", "'!='", "'<'", 
			"'<='", "'>'", "'>='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "Hexadecimal_prefix", "Decimal_const", "Octal_const", 
			"Hexadecimal_const", "Ident", "Plus", "Sub", "Mul", "Div", "Mod", "Not", 
			"Equal", "NEqual", "Lt", "Le", "Gt", "Ge", "WHITE_SPACE"
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


	public lab1Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "lab1.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\"\u00bf\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\5\20u\n\20\3\21\3\21\3\21\6\21z\n\21\r\21\16\21{\5\21~\n\21\3"+
		"\22\3\22\7\22\u0082\n\22\f\22\16\22\u0085\13\22\3\23\3\23\6\23\u0089\n"+
		"\23\r\23\16\23\u008a\3\24\5\24\u008e\n\24\3\24\5\24\u0091\n\24\3\24\3"+
		"\24\5\24\u0095\n\24\6\24\u0097\n\24\r\24\16\24\u0098\5\24\u009b\n\24\3"+
		"\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3"+
		"\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3 \3 \3 \3!\6!\u00ba"+
		"\n!\r!\16!\u00bb\3!\3!\2\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\65\34\67\359\36;\37= ?!A\"\3\2\b\3\2\62;\3\2\63;\3\2\629\5\2"+
		"\62;C\\c|\5\2C\\aac|\5\2\13\f\17\17\"\"\2\u00c7\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\3C\3\2\2\2\5E\3\2\2\2\7G\3\2\2\2\tK\3\2\2\2\13M\3\2"+
		"\2\2\rO\3\2\2\2\17U\3\2\2\2\21W\3\2\2\2\23Y\3\2\2\2\25[\3\2\2\2\27b\3"+
		"\2\2\2\31e\3\2\2\2\33j\3\2\2\2\35m\3\2\2\2\37t\3\2\2\2!}\3\2\2\2#\177"+
		"\3\2\2\2%\u0086\3\2\2\2\'\u009a\3\2\2\2)\u009c\3\2\2\2+\u009e\3\2\2\2"+
		"-\u00a0\3\2\2\2/\u00a2\3\2\2\2\61\u00a4\3\2\2\2\63\u00a6\3\2\2\2\65\u00a8"+
		"\3\2\2\2\67\u00ab\3\2\2\29\u00ae\3\2\2\2;\u00b0\3\2\2\2=\u00b3\3\2\2\2"+
		"?\u00b5\3\2\2\2A\u00b9\3\2\2\2CD\7*\2\2D\4\3\2\2\2EF\7+\2\2F\6\3\2\2\2"+
		"GH\7k\2\2HI\7p\2\2IJ\7v\2\2J\b\3\2\2\2KL\7}\2\2L\n\3\2\2\2MN\7\177\2\2"+
		"N\f\3\2\2\2OP\7e\2\2PQ\7q\2\2QR\7p\2\2RS\7u\2\2ST\7v\2\2T\16\3\2\2\2U"+
		"V\7.\2\2V\20\3\2\2\2WX\7=\2\2X\22\3\2\2\2YZ\7?\2\2Z\24\3\2\2\2[\\\7t\2"+
		"\2\\]\7g\2\2]^\7v\2\2^_\7w\2\2_`\7t\2\2`a\7p\2\2a\26\3\2\2\2bc\7k\2\2"+
		"cd\7h\2\2d\30\3\2\2\2ef\7g\2\2fg\7n\2\2gh\7u\2\2hi\7g\2\2i\32\3\2\2\2"+
		"jk\7~\2\2kl\7~\2\2l\34\3\2\2\2mn\7(\2\2no\7(\2\2o\36\3\2\2\2pq\7\62\2"+
		"\2qu\7z\2\2rs\7\62\2\2su\7Z\2\2tp\3\2\2\2tr\3\2\2\2u \3\2\2\2v~\t\2\2"+
		"\2wy\t\3\2\2xz\t\2\2\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|~\3\2\2"+
		"\2}v\3\2\2\2}w\3\2\2\2~\"\3\2\2\2\177\u0083\7\62\2\2\u0080\u0082\t\4\2"+
		"\2\u0081\u0080\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084"+
		"\3\2\2\2\u0084$\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0088\5\37\20\2\u0087"+
		"\u0089\t\5\2\2\u0088\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0088\3\2"+
		"\2\2\u008a\u008b\3\2\2\2\u008b&\3\2\2\2\u008c\u008e\t\6\2\2\u008d\u008c"+
		"\3\2\2\2\u008e\u009b\3\2\2\2\u008f\u0091\t\6\2\2\u0090\u008f\3\2\2\2\u0091"+
		"\u0096\3\2\2\2\u0092\u0097\t\2\2\2\u0093\u0095\t\6\2\2\u0094\u0093\3\2"+
		"\2\2\u0095\u0097\3\2\2\2\u0096\u0092\3\2\2\2\u0096\u0094\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\3\2"+
		"\2\2\u009a\u008d\3\2\2\2\u009a\u0090\3\2\2\2\u009b(\3\2\2\2\u009c\u009d"+
		"\7-\2\2\u009d*\3\2\2\2\u009e\u009f\7/\2\2\u009f,\3\2\2\2\u00a0\u00a1\7"+
		",\2\2\u00a1.\3\2\2\2\u00a2\u00a3\7\61\2\2\u00a3\60\3\2\2\2\u00a4\u00a5"+
		"\7\'\2\2\u00a5\62\3\2\2\2\u00a6\u00a7\7#\2\2\u00a7\64\3\2\2\2\u00a8\u00a9"+
		"\7?\2\2\u00a9\u00aa\7?\2\2\u00aa\66\3\2\2\2\u00ab\u00ac\7#\2\2\u00ac\u00ad"+
		"\7?\2\2\u00ad8\3\2\2\2\u00ae\u00af\7>\2\2\u00af:\3\2\2\2\u00b0\u00b1\7"+
		">\2\2\u00b1\u00b2\7?\2\2\u00b2<\3\2\2\2\u00b3\u00b4\7@\2\2\u00b4>\3\2"+
		"\2\2\u00b5\u00b6\7@\2\2\u00b6\u00b7\7?\2\2\u00b7@\3\2\2\2\u00b8\u00ba"+
		"\t\7\2\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\b!\2\2\u00beB\3\2\2\2\17"+
		"\2t{}\u0083\u008a\u008d\u0090\u0094\u0096\u0098\u009a\u00bb\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}