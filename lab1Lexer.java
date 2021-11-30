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
		T__9=10, Hexadecimal_prefix=11, Decimal_const=12, Octal_const=13, Hexadecimal_const=14, 
		Ident=15, Plus=16, Sub=17, Mul=18, Div=19, Mod=20, WHITE_SPACE=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "Hexadecimal_prefix", "Decimal_const", "Octal_const", "Hexadecimal_const", 
			"Ident", "Plus", "Sub", "Mul", "Div", "Mod", "WHITE_SPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'int'", "'{'", "'}'", "'const'", "','", "';'", "'='", 
			"'return'", null, null, null, null, null, "'+'", "'-'", "'*'", "'/'", 
			"'%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "Hexadecimal_prefix", 
			"Decimal_const", "Octal_const", "Hexadecimal_const", "Ident", "Plus", 
			"Sub", "Mul", "Div", "Mod", "WHITE_SPACE"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u0089\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\5\fQ\n\f\3\r\3\r"+
		"\3\r\6\rV\n\r\r\r\16\rW\5\rZ\n\r\3\16\3\16\7\16^\n\16\f\16\16\16a\13\16"+
		"\3\17\3\17\6\17e\n\17\r\17\16\17f\3\20\5\20j\n\20\3\20\5\20m\n\20\3\20"+
		"\3\20\5\20q\n\20\6\20s\n\20\r\20\16\20t\5\20w\n\20\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\24\3\24\3\25\3\25\3\26\6\26\u0084\n\26\r\26\16\26\u0085\3"+
		"\26\3\26\2\2\27\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27\3\2\b\3\2\62;\3\2\63;\3"+
		"\2\629\5\2\62;C\\c|\5\2C\\aac|\5\2\13\f\17\17\"\"\2\u0091\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2\5/\3\2\2\2\7\61\3\2\2"+
		"\2\t\65\3\2\2\2\13\67\3\2\2\2\r9\3\2\2\2\17?\3\2\2\2\21A\3\2\2\2\23C\3"+
		"\2\2\2\25E\3\2\2\2\27P\3\2\2\2\31Y\3\2\2\2\33[\3\2\2\2\35b\3\2\2\2\37"+
		"v\3\2\2\2!x\3\2\2\2#z\3\2\2\2%|\3\2\2\2\'~\3\2\2\2)\u0080\3\2\2\2+\u0083"+
		"\3\2\2\2-.\7*\2\2.\4\3\2\2\2/\60\7+\2\2\60\6\3\2\2\2\61\62\7k\2\2\62\63"+
		"\7p\2\2\63\64\7v\2\2\64\b\3\2\2\2\65\66\7}\2\2\66\n\3\2\2\2\678\7\177"+
		"\2\28\f\3\2\2\29:\7e\2\2:;\7q\2\2;<\7p\2\2<=\7u\2\2=>\7v\2\2>\16\3\2\2"+
		"\2?@\7.\2\2@\20\3\2\2\2AB\7=\2\2B\22\3\2\2\2CD\7?\2\2D\24\3\2\2\2EF\7"+
		"t\2\2FG\7g\2\2GH\7v\2\2HI\7w\2\2IJ\7t\2\2JK\7p\2\2K\26\3\2\2\2LM\7\62"+
		"\2\2MQ\7z\2\2NO\7\62\2\2OQ\7Z\2\2PL\3\2\2\2PN\3\2\2\2Q\30\3\2\2\2RZ\t"+
		"\2\2\2SU\t\3\2\2TV\t\2\2\2UT\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2\2XZ\3"+
		"\2\2\2YR\3\2\2\2YS\3\2\2\2Z\32\3\2\2\2[_\7\62\2\2\\^\t\4\2\2]\\\3\2\2"+
		"\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`\34\3\2\2\2a_\3\2\2\2bd\5\27\f\2ce\t"+
		"\5\2\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2g\36\3\2\2\2hj\t\6\2\2i"+
		"h\3\2\2\2jw\3\2\2\2km\t\6\2\2lk\3\2\2\2mr\3\2\2\2ns\t\2\2\2oq\t\6\2\2"+
		"po\3\2\2\2qs\3\2\2\2rn\3\2\2\2rp\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2"+
		"uw\3\2\2\2vi\3\2\2\2vl\3\2\2\2w \3\2\2\2xy\7-\2\2y\"\3\2\2\2z{\7/\2\2"+
		"{$\3\2\2\2|}\7,\2\2}&\3\2\2\2~\177\7\61\2\2\177(\3\2\2\2\u0080\u0081\7"+
		"\'\2\2\u0081*\3\2\2\2\u0082\u0084\t\7\2\2\u0083\u0082\3\2\2\2\u0084\u0085"+
		"\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u0088\b\26\2\2\u0088,\3\2\2\2\17\2PWY_filprtv\u0085\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}