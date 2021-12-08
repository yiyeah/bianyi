// Generated from lab1.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link lab1Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface lab1Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link lab1Parser#compUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompUnit(lab1Parser.CompUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(lab1Parser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#funcType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncType(lab1Parser.FuncTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(lab1Parser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#blockItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItem(lab1Parser.BlockItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(lab1Parser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#constDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDecl(lab1Parser.ConstDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#bType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBType(lab1Parser.BTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#constDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDef(lab1Parser.ConstDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#constInitVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstInitVal(lab1Parser.ConstInitValContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#constExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstExp(lab1Parser.ConstExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(lab1Parser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(lab1Parser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#initVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitVal(lab1Parser.InitValContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(lab1Parser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#lVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLVal(lab1Parser.LValContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(lab1Parser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#addExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExp(lab1Parser.AddExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#mulExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExp(lab1Parser.MulExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#unaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExp(lab1Parser.UnaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExp(lab1Parser.PrimaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#unaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(lab1Parser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(lab1Parser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#funcParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncParams(lab1Parser.FuncParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(lab1Parser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#lOrExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLOrExp(lab1Parser.LOrExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#lAndExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLAndExp(lab1Parser.LAndExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#eqExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExp(lab1Parser.EqExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab1Parser#relExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExp(lab1Parser.RelExpContext ctx);
}