// Generated from lab1.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link lab1Parser}.
 */
public interface lab1Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link lab1Parser#treeRoot}.
	 * @param ctx the parse tree
	 */
	void enterTreeRoot(lab1Parser.TreeRootContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#treeRoot}.
	 * @param ctx the parse tree
	 */
	void exitTreeRoot(lab1Parser.TreeRootContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#compUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompUnit(lab1Parser.CompUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#compUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompUnit(lab1Parser.CompUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(lab1Parser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(lab1Parser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#funcType}.
	 * @param ctx the parse tree
	 */
	void enterFuncType(lab1Parser.FuncTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#funcType}.
	 * @param ctx the parse tree
	 */
	void exitFuncType(lab1Parser.FuncTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(lab1Parser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(lab1Parser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#blockItem}.
	 * @param ctx the parse tree
	 */
	void enterBlockItem(lab1Parser.BlockItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#blockItem}.
	 * @param ctx the parse tree
	 */
	void exitBlockItem(lab1Parser.BlockItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(lab1Parser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(lab1Parser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#constDecl}.
	 * @param ctx the parse tree
	 */
	void enterConstDecl(lab1Parser.ConstDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#constDecl}.
	 * @param ctx the parse tree
	 */
	void exitConstDecl(lab1Parser.ConstDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#bType}.
	 * @param ctx the parse tree
	 */
	void enterBType(lab1Parser.BTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#bType}.
	 * @param ctx the parse tree
	 */
	void exitBType(lab1Parser.BTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#constDef}.
	 * @param ctx the parse tree
	 */
	void enterConstDef(lab1Parser.ConstDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#constDef}.
	 * @param ctx the parse tree
	 */
	void exitConstDef(lab1Parser.ConstDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#constInitVal}.
	 * @param ctx the parse tree
	 */
	void enterConstInitVal(lab1Parser.ConstInitValContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#constInitVal}.
	 * @param ctx the parse tree
	 */
	void exitConstInitVal(lab1Parser.ConstInitValContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#constExp}.
	 * @param ctx the parse tree
	 */
	void enterConstExp(lab1Parser.ConstExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#constExp}.
	 * @param ctx the parse tree
	 */
	void exitConstExp(lab1Parser.ConstExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(lab1Parser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(lab1Parser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(lab1Parser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(lab1Parser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#initVal}.
	 * @param ctx the parse tree
	 */
	void enterInitVal(lab1Parser.InitValContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#initVal}.
	 * @param ctx the parse tree
	 */
	void exitInitVal(lab1Parser.InitValContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(lab1Parser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(lab1Parser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#lVal}.
	 * @param ctx the parse tree
	 */
	void enterLVal(lab1Parser.LValContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#lVal}.
	 * @param ctx the parse tree
	 */
	void exitLVal(lab1Parser.LValContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(lab1Parser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(lab1Parser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#addExp}.
	 * @param ctx the parse tree
	 */
	void enterAddExp(lab1Parser.AddExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#addExp}.
	 * @param ctx the parse tree
	 */
	void exitAddExp(lab1Parser.AddExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#mulExp}.
	 * @param ctx the parse tree
	 */
	void enterMulExp(lab1Parser.MulExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#mulExp}.
	 * @param ctx the parse tree
	 */
	void exitMulExp(lab1Parser.MulExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExp(lab1Parser.UnaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExp(lab1Parser.UnaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExp(lab1Parser.PrimaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExp(lab1Parser.PrimaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(lab1Parser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(lab1Parser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(lab1Parser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(lab1Parser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#funcRParams}.
	 * @param ctx the parse tree
	 */
	void enterFuncRParams(lab1Parser.FuncRParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#funcRParams}.
	 * @param ctx the parse tree
	 */
	void exitFuncRParams(lab1Parser.FuncRParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#funcFParams}.
	 * @param ctx the parse tree
	 */
	void enterFuncFParams(lab1Parser.FuncFParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#funcFParams}.
	 * @param ctx the parse tree
	 */
	void exitFuncFParams(lab1Parser.FuncFParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#funcFParam}.
	 * @param ctx the parse tree
	 */
	void enterFuncFParam(lab1Parser.FuncFParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#funcFParam}.
	 * @param ctx the parse tree
	 */
	void exitFuncFParam(lab1Parser.FuncFParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(lab1Parser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(lab1Parser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#lOrExp}.
	 * @param ctx the parse tree
	 */
	void enterLOrExp(lab1Parser.LOrExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#lOrExp}.
	 * @param ctx the parse tree
	 */
	void exitLOrExp(lab1Parser.LOrExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#lAndExp}.
	 * @param ctx the parse tree
	 */
	void enterLAndExp(lab1Parser.LAndExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#lAndExp}.
	 * @param ctx the parse tree
	 */
	void exitLAndExp(lab1Parser.LAndExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#eqExp}.
	 * @param ctx the parse tree
	 */
	void enterEqExp(lab1Parser.EqExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#eqExp}.
	 * @param ctx the parse tree
	 */
	void exitEqExp(lab1Parser.EqExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link lab1Parser#relExp}.
	 * @param ctx the parse tree
	 */
	void enterRelExp(lab1Parser.RelExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link lab1Parser#relExp}.
	 * @param ctx the parse tree
	 */
	void exitRelExp(lab1Parser.RelExpContext ctx);
}