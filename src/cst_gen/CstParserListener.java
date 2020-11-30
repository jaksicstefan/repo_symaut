// Generated from C:/repo_symaut/sym_automata/src/cst_parser/CstParser.g4 by ANTLR 4.5.3

package cst_gen;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CstParser}.
 */
public interface CstParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code CstExpr}
	 * labeled alternative in {@link CstParser#cst}.
	 * @param ctx the parse tree
	 */
	void enterCstExpr(CstParser.CstExprContext ctx);

	/**
	 * Exit a parse tree produced by the {@code CstExpr}
	 * labeled alternative in {@link CstParser#cst}.
	 * @param ctx the parse tree
	 */
	void exitCstExpr(CstParser.CstExprContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprPrimary}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprPrimary(CstParser.ExprPrimaryContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprPrimary}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprPrimary(CstParser.ExprPrimaryContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprNotUnaryExpr}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprNotUnaryExpr(CstParser.ExprNotUnaryExprContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprNotUnaryExpr}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprNotUnaryExpr(CstParser.ExprNotUnaryExprContext ctx);

	/**
	 * Enter a parse tree produced by the {@code IdCompInt1}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdCompInt1(CstParser.IdCompInt1Context ctx);

	/**
	 * Exit a parse tree produced by the {@code IdCompInt1}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdCompInt1(CstParser.IdCompInt1Context ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprAnd(CstParser.ExprAndContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprAnd(CstParser.ExprAndContext ctx);

	/**
	 * Enter a parse tree produced by the {@code IdCompInt2}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdCompInt2(CstParser.IdCompInt2Context ctx);

	/**
	 * Exit a parse tree produced by the {@code IdCompInt2}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdCompInt2(CstParser.IdCompInt2Context ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprEqualExpr}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprEqualExpr(CstParser.ExprEqualExprContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprEqualExpr}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprEqualExpr(CstParser.ExprEqualExprContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprNotUnaryId}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprNotUnaryId(CstParser.ExprNotUnaryIdContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprNotUnaryId}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprNotUnaryId(CstParser.ExprNotUnaryIdContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprOr}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprOr(CstParser.ExprOrContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprOr}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprOr(CstParser.ExprOrContext ctx);

	/**
	 * Enter a parse tree produced by the {@code IdCompInt0}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdCompInt0(CstParser.IdCompInt0Context ctx);

	/**
	 * Exit a parse tree produced by the {@code IdCompInt0}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdCompInt0(CstParser.IdCompInt0Context ctx);

	/**
	 * Enter a parse tree produced by the {@code IdCompId}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdCompId(CstParser.IdCompIdContext ctx);

	/**
	 * Exit a parse tree produced by the {@code IdCompId}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdCompId(CstParser.IdCompIdContext ctx);

	/**
	 * Enter a parse tree produced by the {@code EqOp}
	 * labeled alternative in {@link CstParser#equalityCmpOp}.
	 * @param ctx the parse tree
	 */
	void enterEqOp(CstParser.EqOpContext ctx);

	/**
	 * Exit a parse tree produced by the {@code EqOp}
	 * labeled alternative in {@link CstParser#equalityCmpOp}.
	 * @param ctx the parse tree
	 */
	void exitEqOp(CstParser.EqOpContext ctx);

	/**
	 * Enter a parse tree produced by the {@code NeqOp}
	 * labeled alternative in {@link CstParser#equalityCmpOp}.
	 * @param ctx the parse tree
	 */
	void enterNeqOp(CstParser.NeqOpContext ctx);

	/**
	 * Exit a parse tree produced by the {@code NeqOp}
	 * labeled alternative in {@link CstParser#equalityCmpOp}.
	 * @param ctx the parse tree
	 */
	void exitNeqOp(CstParser.NeqOpContext ctx);

	/**
	 * Enter a parse tree produced by the {@code CmpOpLs}
	 * labeled alternative in {@link CstParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void enterCmpOpLs(CstParser.CmpOpLsContext ctx);

	/**
	 * Exit a parse tree produced by the {@code CmpOpLs}
	 * labeled alternative in {@link CstParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void exitCmpOpLs(CstParser.CmpOpLsContext ctx);

	/**
	 * Enter a parse tree produced by the {@code CmpOpGte}
	 * labeled alternative in {@link CstParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void enterCmpOpGte(CstParser.CmpOpGteContext ctx);

	/**
	 * Exit a parse tree produced by the {@code CmpOpGte}
	 * labeled alternative in {@link CstParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void exitCmpOpGte(CstParser.CmpOpGteContext ctx);

	/**
	 * Enter a parse tree produced by the {@code CmpOpLse}
	 * labeled alternative in {@link CstParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void enterCmpOpLse(CstParser.CmpOpLseContext ctx);

	/**
	 * Exit a parse tree produced by the {@code CmpOpLse}
	 * labeled alternative in {@link CstParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void exitCmpOpLse(CstParser.CmpOpLseContext ctx);

	/**
	 * Enter a parse tree produced by the {@code CmpOpGt}
	 * labeled alternative in {@link CstParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void enterCmpOpGt(CstParser.CmpOpGtContext ctx);

	/**
	 * Exit a parse tree produced by the {@code CmpOpGt}
	 * labeled alternative in {@link CstParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void exitCmpOpGt(CstParser.CmpOpGtContext ctx);

	/**
	 * Enter a parse tree produced by the {@code PrimaryParenthesis}
	 * labeled alternative in {@link CstParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryParenthesis(CstParser.PrimaryParenthesisContext ctx);

	/**
	 * Exit a parse tree produced by the {@code PrimaryParenthesis}
	 * labeled alternative in {@link CstParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryParenthesis(CstParser.PrimaryParenthesisContext ctx);

	/**
	 * Enter a parse tree produced by the {@code PrimaryLiteralBool}
	 * labeled alternative in {@link CstParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryLiteralBool(CstParser.PrimaryLiteralBoolContext ctx);

	/**
	 * Exit a parse tree produced by the {@code PrimaryLiteralBool}
	 * labeled alternative in {@link CstParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryLiteralBool(CstParser.PrimaryLiteralBoolContext ctx);

	/**
	 * Enter a parse tree produced by the {@code PrimaryId}
	 * labeled alternative in {@link CstParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryId(CstParser.PrimaryIdContext ctx);

	/**
	 * Exit a parse tree produced by the {@code PrimaryId}
	 * labeled alternative in {@link CstParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryId(CstParser.PrimaryIdContext ctx);

	/**
	 * Enter a parse tree produced by the {@code LiteralBoolParen}
	 * labeled alternative in {@link CstParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterLiteralBoolParen(CstParser.LiteralBoolParenContext ctx);

	/**
	 * Exit a parse tree produced by the {@code LiteralBoolParen}
	 * labeled alternative in {@link CstParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitLiteralBoolParen(CstParser.LiteralBoolParenContext ctx);

	/**
	 * Enter a parse tree produced by the {@code LiteralBool}
	 * labeled alternative in {@link CstParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterLiteralBool(CstParser.LiteralBoolContext ctx);

	/**
	 * Exit a parse tree produced by the {@code LiteralBool}
	 * labeled alternative in {@link CstParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitLiteralBool(CstParser.LiteralBoolContext ctx);

	/**
	 * Enter a parse tree produced by the {@code LiteralIntParen}
	 * labeled alternative in {@link CstParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterLiteralIntParen(CstParser.LiteralIntParenContext ctx);

	/**
	 * Exit a parse tree produced by the {@code LiteralIntParen}
	 * labeled alternative in {@link CstParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitLiteralIntParen(CstParser.LiteralIntParenContext ctx);

	/**
	 * Enter a parse tree produced by the {@code LiteralInt}
	 * labeled alternative in {@link CstParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterLiteralInt(CstParser.LiteralIntContext ctx);

	/**
	 * Exit a parse tree produced by the {@code LiteralInt}
	 * labeled alternative in {@link CstParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitLiteralInt(CstParser.LiteralIntContext ctx);

	/**
	 * Enter a parse tree produced by the {@code Id}
	 * labeled alternative in {@link CstParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterId(CstParser.IdContext ctx);

	/**
	 * Exit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link CstParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitId(CstParser.IdContext ctx);
}