// Generated from C:/repo_symaut/sym_automata/src/cst_parser/CstParser.g4 by ANTLR 4.5.3

package cst_gen;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CstParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CstParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code CstExpr}
	 * labeled alternative in {@link CstParser#cst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCstExpr(CstParser.CstExprContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprPrimary}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPrimary(CstParser.ExprPrimaryContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprNotUnaryExpr}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNotUnaryExpr(CstParser.ExprNotUnaryExprContext ctx);

	/**
	 * Visit a parse tree produced by the {@code IdCompInt1}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdCompInt1(CstParser.IdCompInt1Context ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAnd(CstParser.ExprAndContext ctx);

	/**
	 * Visit a parse tree produced by the {@code IdCompInt2}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdCompInt2(CstParser.IdCompInt2Context ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprEqualExpr}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprEqualExpr(CstParser.ExprEqualExprContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprNotUnaryId}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNotUnaryId(CstParser.ExprNotUnaryIdContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprOr}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOr(CstParser.ExprOrContext ctx);

	/**
	 * Visit a parse tree produced by the {@code IdCompInt0}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdCompInt0(CstParser.IdCompInt0Context ctx);

	/**
	 * Visit a parse tree produced by the {@code IdCompId}
	 * labeled alternative in {@link CstParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdCompId(CstParser.IdCompIdContext ctx);

	/**
	 * Visit a parse tree produced by the {@code EqOp}
	 * labeled alternative in {@link CstParser#equalityCmpOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqOp(CstParser.EqOpContext ctx);

	/**
	 * Visit a parse tree produced by the {@code NeqOp}
	 * labeled alternative in {@link CstParser#equalityCmpOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNeqOp(CstParser.NeqOpContext ctx);

	/**
	 * Visit a parse tree produced by the {@code CmpOpLs}
	 * labeled alternative in {@link CstParser#comparisonOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpOpLs(CstParser.CmpOpLsContext ctx);

	/**
	 * Visit a parse tree produced by the {@code CmpOpGte}
	 * labeled alternative in {@link CstParser#comparisonOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpOpGte(CstParser.CmpOpGteContext ctx);

	/**
	 * Visit a parse tree produced by the {@code CmpOpLse}
	 * labeled alternative in {@link CstParser#comparisonOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpOpLse(CstParser.CmpOpLseContext ctx);

	/**
	 * Visit a parse tree produced by the {@code CmpOpGt}
	 * labeled alternative in {@link CstParser#comparisonOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpOpGt(CstParser.CmpOpGtContext ctx);

	/**
	 * Visit a parse tree produced by the {@code PrimaryParenthesis}
	 * labeled alternative in {@link CstParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryParenthesis(CstParser.PrimaryParenthesisContext ctx);

	/**
	 * Visit a parse tree produced by the {@code PrimaryLiteralBool}
	 * labeled alternative in {@link CstParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryLiteralBool(CstParser.PrimaryLiteralBoolContext ctx);

	/**
	 * Visit a parse tree produced by the {@code PrimaryId}
	 * labeled alternative in {@link CstParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryId(CstParser.PrimaryIdContext ctx);

	/**
	 * Visit a parse tree produced by the {@code LiteralBoolParen}
	 * labeled alternative in {@link CstParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralBoolParen(CstParser.LiteralBoolParenContext ctx);

	/**
	 * Visit a parse tree produced by the {@code LiteralBool}
	 * labeled alternative in {@link CstParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralBool(CstParser.LiteralBoolContext ctx);

	/**
	 * Visit a parse tree produced by the {@code LiteralIntParen}
	 * labeled alternative in {@link CstParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralIntParen(CstParser.LiteralIntParenContext ctx);

	/**
	 * Visit a parse tree produced by the {@code LiteralInt}
	 * labeled alternative in {@link CstParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralInt(CstParser.LiteralIntContext ctx);

	/**
	 * Visit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link CstParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(CstParser.IdContext ctx);
}