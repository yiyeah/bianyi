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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, Hexadecimal_prefix=9, 
		Decimal_const=10, Octal_const=11, Hexadecimal_const=12, Plus=13, Sub=14, 
		Mul=15, Div=16, Mod=17, WHITE_SPACE=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "Hexadecimal_prefix", 
			"Decimal_const", "Octal_const", "Hexadecimal_const", "Plus", "Sub", "Mul", 
			"Div", "Mod", "WHITE_SPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'int'", "'main'", "'{'", "'}'", "'return'", "';'", 
			null, null, null, null, "'+'", "'-'", "'*'", "'/'", "'%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "Hexadecimal_prefix", 
			"Decimal_const", "Octal_const", "Hexadecimal_const", "Plus", "Sub", "Mul", 
			"Div", "Mod", "WHITE_SPACE"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24n\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\5\nF\n\n"+
		"\3\13\3\13\3\13\6\13K\n\13\r\13\16\13L\5\13O\n\13\3\f\3\f\7\fS\n\f\f\f"+
		"\16\fV\13\f\3\r\3\r\6\rZ\n\r\r\r\16\r[\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\3\23\6\23i\n\23\r\23\16\23j\3\23\3\23\2\2\24\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\3\2\7\3\2\62;\3\2\63;\3\2\629\5\2\62;C\\c|\5\2\13\f\17\17"+
		"\"\"\2s\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\3\'\3\2\2\2\5)\3\2\2\2\7+\3\2\2\2\t/\3\2\2\2\13"+
		"\64\3\2\2\2\r\66\3\2\2\2\178\3\2\2\2\21?\3\2\2\2\23E\3\2\2\2\25N\3\2\2"+
		"\2\27P\3\2\2\2\31W\3\2\2\2\33]\3\2\2\2\35_\3\2\2\2\37a\3\2\2\2!c\3\2\2"+
		"\2#e\3\2\2\2%h\3\2\2\2\'(\7*\2\2(\4\3\2\2\2)*\7+\2\2*\6\3\2\2\2+,\7k\2"+
		"\2,-\7p\2\2-.\7v\2\2.\b\3\2\2\2/\60\7o\2\2\60\61\7c\2\2\61\62\7k\2\2\62"+
		"\63\7p\2\2\63\n\3\2\2\2\64\65\7}\2\2\65\f\3\2\2\2\66\67\7\177\2\2\67\16"+
		"\3\2\2\289\7t\2\29:\7g\2\2:;\7v\2\2;<\7w\2\2<=\7t\2\2=>\7p\2\2>\20\3\2"+
		"\2\2?@\7=\2\2@\22\3\2\2\2AB\7\62\2\2BF\7z\2\2CD\7\62\2\2DF\7Z\2\2EA\3"+
		"\2\2\2EC\3\2\2\2F\24\3\2\2\2GO\t\2\2\2HJ\t\3\2\2IK\t\2\2\2JI\3\2\2\2K"+
		"L\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NG\3\2\2\2NH\3\2\2\2O\26\3\2\2"+
		"\2PT\7\62\2\2QS\t\4\2\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\30\3"+
		"\2\2\2VT\3\2\2\2WY\5\23\n\2XZ\t\5\2\2YX\3\2\2\2Z[\3\2\2\2[Y\3\2\2\2[\\"+
		"\3\2\2\2\\\32\3\2\2\2]^\7-\2\2^\34\3\2\2\2_`\7/\2\2`\36\3\2\2\2ab\7,\2"+
		"\2b \3\2\2\2cd\7\61\2\2d\"\3\2\2\2ef\7\'\2\2f$\3\2\2\2gi\t\6\2\2hg\3\2"+
		"\2\2ij\3\2\2\2jh\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\b\23\2\2m&\3\2\2\2\t\2"+
		"ELNT[j\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}