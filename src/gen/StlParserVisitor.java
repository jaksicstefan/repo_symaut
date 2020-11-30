// Generated from C:/repo_symaut/sym_automata/src/parser/StlParser.g4 by ANTLR 4.5.3

package gen;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link StlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface StlParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link StlParser#stlfile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStlfile(StlParser.StlfileContext ctx);

	/**
	 * Visit a parse tree produced by {@link StlParser#stlSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStlSpecification(StlParser.StlSpecificationContext ctx);

	/**
	 * Visit a parse tree produced by {@link StlParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssertion(StlParser.AssertionContext ctx);

	/**
	 * Visit a parse tree produced by the {@code declVariable}
	 * labeled alternative in {@link StlParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclVariable(StlParser.DeclVariableContext ctx);

	/**
	 * Visit a parse tree produced by {@link StlParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(StlParser.VariableDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by the {@code AsgnLiteral}
	 * labeled alternative in {@link StlParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsgnLiteral(StlParser.AsgnLiteralContext ctx);

	/**
	 * Visit a parse tree produced by the {@code AsgnExpr}
	 * labeled alternative in {@link StlParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsgnExpr(StlParser.AsgnExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link StlParser#domainType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainType(StlParser.DomainTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link StlParser#ioType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIoType(StlParser.IoTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link StlParser#interval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterval(StlParser.IntervalContext ctx);

	/**
	 * Visit a parse tree produced by the {@code intervalTimeLiteral}
	 * labeled alternative in {@link StlParser#intervalTime}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalTimeLiteral(StlParser.IntervalTimeLiteralContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprNotUnaryExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNotUnaryExpr(StlParser.ExprNotUnaryExprContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprSince}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSince(StlParser.ExprSinceContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprIdComp}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIdComp(StlParser.ExprIdCompContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprEventually}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprEventually(StlParser.ExprEventuallyContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprNotUnaryId}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNotUnaryId(StlParser.ExprNotUnaryIdContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprAlwaysExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAlwaysExpr(StlParser.ExprAlwaysExprContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprIff}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIff(StlParser.ExprIffContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprImplies}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprImplies(StlParser.ExprImpliesContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprUntil}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprUntil(StlParser.ExprUntilContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprPrimary}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPrimary(StlParser.ExprPrimaryContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAnd(StlParser.ExprAndContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprOnceExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOnceExpr(StlParser.ExprOnceExprContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprOracleExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOracleExpr(StlParser.ExprOracleExprContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprXor}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprXor(StlParser.ExprXorContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprRiseFall}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRiseFall(StlParser.ExprRiseFallContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprEqualExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprEqualExpr(StlParser.ExprEqualExprContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprHistoricallyExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprHistoricallyExpr(StlParser.ExprHistoricallyExprContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprPreviousExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPreviousExpr(StlParser.ExprPreviousExprContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprOr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOr(StlParser.ExprOrContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ExprNextExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNextExpr(StlParser.ExprNextExprContext ctx);

	/**
	 * Visit a parse tree produced by the {@code IdCompId}
	 * labeled alternative in {@link StlParser#idComp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdCompId(StlParser.IdCompIdContext ctx);

	/**
	 * Visit a parse tree produced by the {@code IdCompInt}
	 * labeled alternative in {@link StlParser#idComp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdCompInt(StlParser.IdCompIntContext ctx);

	/**
	 * Visit a parse tree produced by the {@code IntCompId}
	 * labeled alternative in {@link StlParser#idComp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntCompId(StlParser.IntCompIdContext ctx);

	/**
	 * Visit a parse tree produced by the {@code EqOp}
	 * labeled alternative in {@link StlParser#equalityCmpOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqOp(StlParser.EqOpContext ctx);

	/**
	 * Visit a parse tree produced by the {@code NeqOp}
	 * labeled alternative in {@link StlParser#equalityCmpOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNeqOp(StlParser.NeqOpContext ctx);

	/**
	 * Visit a parse tree produced by the {@code CmpOpLs}
	 * labeled alternative in {@link StlParser#comparisonOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpOpLs(StlParser.CmpOpLsContext ctx);

	/**
	 * Visit a parse tree produced by the {@code CmpOpGte}
	 * labeled alternative in {@link StlParser#comparisonOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpOpGte(StlParser.CmpOpGteContext ctx);

	/**
	 * Visit a parse tree produced by the {@code CmpOpLse}
	 * labeled alternative in {@link StlParser#comparisonOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpOpLse(StlParser.CmpOpLseContext ctx);

	/**
	 * Visit a parse tree produced by the {@code CmpOpGt}
	 * labeled alternative in {@link StlParser#comparisonOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpOpGt(StlParser.CmpOpGtContext ctx);

	/**
	 * Visit a parse tree produced by the {@code PrimaryParenthesis}
	 * labeled alternative in {@link StlParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryParenthesis(StlParser.PrimaryParenthesisContext ctx);

	/**
	 * Visit a parse tree produced by the {@code PrimaryLiteralBool}
	 * labeled alternative in {@link StlParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryLiteralBool(StlParser.PrimaryLiteralBoolContext ctx);

	/**
	 * Visit a parse tree produced by the {@code PrimaryId}
	 * labeled alternative in {@link StlParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryId(StlParser.PrimaryIdContext ctx);

	/**
	 * Visit a parse tree produced by the {@code LiteralBoolParen}
	 * labeled alternative in {@link StlParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralBoolParen(StlParser.LiteralBoolParenContext ctx);

	/**
	 * Visit a parse tree produced by the {@code LiteralBool}
	 * labeled alternative in {@link StlParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralBool(StlParser.LiteralBoolContext ctx);

	/**
	 * Visit a parse tree produced by the {@code LiteralIntParen}
	 * labeled alternative in {@link StlParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralIntParen(StlParser.LiteralIntParenContext ctx);

	/**
	 * Visit a parse tree produced by the {@code LiteralInt}
	 * labeled alternative in {@link StlParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralInt(StlParser.LiteralIntContext ctx);

	/**
	 * Visit a parse tree produced by the {@code LiteralBoolean}
	 * labeled alternative in {@link StlParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralBoolean(StlParser.LiteralBooleanContext ctx);

	/**
	 * Visit a parse tree produced by the {@code LiteralInteger}
	 * labeled alternative in {@link StlParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralInteger(StlParser.LiteralIntegerContext ctx);

	/**
	 * Visit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link StlParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(StlParser.IdContext ctx);
}