// Generated from C:/repo_symaut/sym_automata/src/parser/StlParser.g4 by ANTLR 4.5.3

package gen;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link StlParser}.
 */
public interface StlParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link StlParser#stlfile}.
	 * @param ctx the parse tree
	 */
	void enterStlfile(StlParser.StlfileContext ctx);

	/**
	 * Exit a parse tree produced by {@link StlParser#stlfile}.
	 * @param ctx the parse tree
	 */
	void exitStlfile(StlParser.StlfileContext ctx);

	/**
	 * Enter a parse tree produced by {@link StlParser#stlSpecification}.
	 * @param ctx the parse tree
	 */
	void enterStlSpecification(StlParser.StlSpecificationContext ctx);

	/**
	 * Exit a parse tree produced by {@link StlParser#stlSpecification}.
	 * @param ctx the parse tree
	 */
	void exitStlSpecification(StlParser.StlSpecificationContext ctx);

	/**
	 * Enter a parse tree produced by {@link StlParser#assertion}.
	 * @param ctx the parse tree
	 */
	void enterAssertion(StlParser.AssertionContext ctx);

	/**
	 * Exit a parse tree produced by {@link StlParser#assertion}.
	 * @param ctx the parse tree
	 */
	void exitAssertion(StlParser.AssertionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code declVariable}
	 * labeled alternative in {@link StlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclVariable(StlParser.DeclVariableContext ctx);

	/**
	 * Exit a parse tree produced by the {@code declVariable}
	 * labeled alternative in {@link StlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclVariable(StlParser.DeclVariableContext ctx);

	/**
	 * Enter a parse tree produced by {@link StlParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(StlParser.VariableDeclarationContext ctx);

	/**
	 * Exit a parse tree produced by {@link StlParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(StlParser.VariableDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by the {@code AsgnLiteral}
	 * labeled alternative in {@link StlParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAsgnLiteral(StlParser.AsgnLiteralContext ctx);

	/**
	 * Exit a parse tree produced by the {@code AsgnLiteral}
	 * labeled alternative in {@link StlParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAsgnLiteral(StlParser.AsgnLiteralContext ctx);

	/**
	 * Enter a parse tree produced by the {@code AsgnExpr}
	 * labeled alternative in {@link StlParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAsgnExpr(StlParser.AsgnExprContext ctx);

	/**
	 * Exit a parse tree produced by the {@code AsgnExpr}
	 * labeled alternative in {@link StlParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAsgnExpr(StlParser.AsgnExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link StlParser#domainType}.
	 * @param ctx the parse tree
	 */
	void enterDomainType(StlParser.DomainTypeContext ctx);

	/**
	 * Exit a parse tree produced by {@link StlParser#domainType}.
	 * @param ctx the parse tree
	 */
	void exitDomainType(StlParser.DomainTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link StlParser#ioType}.
	 * @param ctx the parse tree
	 */
	void enterIoType(StlParser.IoTypeContext ctx);

	/**
	 * Exit a parse tree produced by {@link StlParser#ioType}.
	 * @param ctx the parse tree
	 */
	void exitIoType(StlParser.IoTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link StlParser#interval}.
	 * @param ctx the parse tree
	 */
	void enterInterval(StlParser.IntervalContext ctx);

	/**
	 * Exit a parse tree produced by {@link StlParser#interval}.
	 * @param ctx the parse tree
	 */
	void exitInterval(StlParser.IntervalContext ctx);

	/**
	 * Enter a parse tree produced by the {@code intervalTimeLiteral}
	 * labeled alternative in {@link StlParser#intervalTime}.
	 * @param ctx the parse tree
	 */
	void enterIntervalTimeLiteral(StlParser.IntervalTimeLiteralContext ctx);

	/**
	 * Exit a parse tree produced by the {@code intervalTimeLiteral}
	 * labeled alternative in {@link StlParser#intervalTime}.
	 * @param ctx the parse tree
	 */
	void exitIntervalTimeLiteral(StlParser.IntervalTimeLiteralContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprNotUnaryExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprNotUnaryExpr(StlParser.ExprNotUnaryExprContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprNotUnaryExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprNotUnaryExpr(StlParser.ExprNotUnaryExprContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprSince}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprSince(StlParser.ExprSinceContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprSince}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprSince(StlParser.ExprSinceContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprIdComp}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprIdComp(StlParser.ExprIdCompContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprIdComp}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprIdComp(StlParser.ExprIdCompContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprEventually}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprEventually(StlParser.ExprEventuallyContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprEventually}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprEventually(StlParser.ExprEventuallyContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprNotUnaryId}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprNotUnaryId(StlParser.ExprNotUnaryIdContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprNotUnaryId}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprNotUnaryId(StlParser.ExprNotUnaryIdContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprAlwaysExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprAlwaysExpr(StlParser.ExprAlwaysExprContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprAlwaysExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprAlwaysExpr(StlParser.ExprAlwaysExprContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprIff}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprIff(StlParser.ExprIffContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprIff}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprIff(StlParser.ExprIffContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprImplies}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprImplies(StlParser.ExprImpliesContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprImplies}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprImplies(StlParser.ExprImpliesContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprUntil}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprUntil(StlParser.ExprUntilContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprUntil}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprUntil(StlParser.ExprUntilContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprPrimary}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprPrimary(StlParser.ExprPrimaryContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprPrimary}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprPrimary(StlParser.ExprPrimaryContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprAnd(StlParser.ExprAndContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprAnd(StlParser.ExprAndContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprOnceExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprOnceExpr(StlParser.ExprOnceExprContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprOnceExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprOnceExpr(StlParser.ExprOnceExprContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprOracleExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprOracleExpr(StlParser.ExprOracleExprContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprOracleExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprOracleExpr(StlParser.ExprOracleExprContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprXor}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprXor(StlParser.ExprXorContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprXor}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprXor(StlParser.ExprXorContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprRiseFall}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprRiseFall(StlParser.ExprRiseFallContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprRiseFall}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprRiseFall(StlParser.ExprRiseFallContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprEqualExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprEqualExpr(StlParser.ExprEqualExprContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprEqualExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprEqualExpr(StlParser.ExprEqualExprContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprHistoricallyExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprHistoricallyExpr(StlParser.ExprHistoricallyExprContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprHistoricallyExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprHistoricallyExpr(StlParser.ExprHistoricallyExprContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprPreviousExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprPreviousExpr(StlParser.ExprPreviousExprContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprPreviousExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprPreviousExpr(StlParser.ExprPreviousExprContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprOr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprOr(StlParser.ExprOrContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprOr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprOr(StlParser.ExprOrContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ExprNextExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprNextExpr(StlParser.ExprNextExprContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ExprNextExpr}
	 * labeled alternative in {@link StlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprNextExpr(StlParser.ExprNextExprContext ctx);

	/**
	 * Enter a parse tree produced by the {@code IdCompId}
	 * labeled alternative in {@link StlParser#idComp}.
	 * @param ctx the parse tree
	 */
	void enterIdCompId(StlParser.IdCompIdContext ctx);

	/**
	 * Exit a parse tree produced by the {@code IdCompId}
	 * labeled alternative in {@link StlParser#idComp}.
	 * @param ctx the parse tree
	 */
	void exitIdCompId(StlParser.IdCompIdContext ctx);

	/**
	 * Enter a parse tree produced by the {@code IdCompInt}
	 * labeled alternative in {@link StlParser#idComp}.
	 * @param ctx the parse tree
	 */
	void enterIdCompInt(StlParser.IdCompIntContext ctx);

	/**
	 * Exit a parse tree produced by the {@code IdCompInt}
	 * labeled alternative in {@link StlParser#idComp}.
	 * @param ctx the parse tree
	 */
	void exitIdCompInt(StlParser.IdCompIntContext ctx);

	/**
	 * Enter a parse tree produced by the {@code IntCompId}
	 * labeled alternative in {@link StlParser#idComp}.
	 * @param ctx the parse tree
	 */
	void enterIntCompId(StlParser.IntCompIdContext ctx);

	/**
	 * Exit a parse tree produced by the {@code IntCompId}
	 * labeled alternative in {@link StlParser#idComp}.
	 * @param ctx the parse tree
	 */
	void exitIntCompId(StlParser.IntCompIdContext ctx);

	/**
	 * Enter a parse tree produced by the {@code EqOp}
	 * labeled alternative in {@link StlParser#equalityCmpOp}.
	 * @param ctx the parse tree
	 */
	void enterEqOp(StlParser.EqOpContext ctx);

	/**
	 * Exit a parse tree produced by the {@code EqOp}
	 * labeled alternative in {@link StlParser#equalityCmpOp}.
	 * @param ctx the parse tree
	 */
	void exitEqOp(StlParser.EqOpContext ctx);

	/**
	 * Enter a parse tree produced by the {@code NeqOp}
	 * labeled alternative in {@link StlParser#equalityCmpOp}.
	 * @param ctx the parse tree
	 */
	void enterNeqOp(StlParser.NeqOpContext ctx);

	/**
	 * Exit a parse tree produced by the {@code NeqOp}
	 * labeled alternative in {@link StlParser#equalityCmpOp}.
	 * @param ctx the parse tree
	 */
	void exitNeqOp(StlParser.NeqOpContext ctx);

	/**
	 * Enter a parse tree produced by the {@code CmpOpLs}
	 * labeled alternative in {@link StlParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void enterCmpOpLs(StlParser.CmpOpLsContext ctx);

	/**
	 * Exit a parse tree produced by the {@code CmpOpLs}
	 * labeled alternative in {@link StlParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void exitCmpOpLs(StlParser.CmpOpLsContext ctx);

	/**
	 * Enter a parse tree produced by the {@code CmpOpGte}
	 * labeled alternative in {@link StlParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void enterCmpOpGte(StlParser.CmpOpGteContext ctx);

	/**
	 * Exit a parse tree produced by the {@code CmpOpGte}
	 * labeled alternative in {@link StlParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void exitCmpOpGte(StlParser.CmpOpGteContext ctx);

	/**
	 * Enter a parse tree produced by the {@code CmpOpLse}
	 * labeled alternative in {@link StlParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void enterCmpOpLse(StlParser.CmpOpLseContext ctx);

	/**
	 * Exit a parse tree produced by the {@code CmpOpLse}
	 * labeled alternative in {@link StlParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void exitCmpOpLse(StlParser.CmpOpLseContext ctx);

	/**
	 * Enter a parse tree produced by the {@code CmpOpGt}
	 * labeled alternative in {@link StlParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void enterCmpOpGt(StlParser.CmpOpGtContext ctx);

	/**
	 * Exit a parse tree produced by the {@code CmpOpGt}
	 * labeled alternative in {@link StlParser#comparisonOp}.
	 * @param ctx the parse tree
	 */
	void exitCmpOpGt(StlParser.CmpOpGtContext ctx);

	/**
	 * Enter a parse tree produced by the {@code PrimaryParenthesis}
	 * labeled alternative in {@link StlParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryParenthesis(StlParser.PrimaryParenthesisContext ctx);

	/**
	 * Exit a parse tree produced by the {@code PrimaryParenthesis}
	 * labeled alternative in {@link StlParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryParenthesis(StlParser.PrimaryParenthesisContext ctx);

	/**
	 * Enter a parse tree produced by the {@code PrimaryLiteralBool}
	 * labeled alternative in {@link StlParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryLiteralBool(StlParser.PrimaryLiteralBoolContext ctx);

	/**
	 * Exit a parse tree produced by the {@code PrimaryLiteralBool}
	 * labeled alternative in {@link StlParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryLiteralBool(StlParser.PrimaryLiteralBoolContext ctx);

	/**
	 * Enter a parse tree produced by the {@code PrimaryId}
	 * labeled alternative in {@link StlParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryId(StlParser.PrimaryIdContext ctx);

	/**
	 * Exit a parse tree produced by the {@code PrimaryId}
	 * labeled alternative in {@link StlParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryId(StlParser.PrimaryIdContext ctx);

	/**
	 * Enter a parse tree produced by the {@code LiteralBoolParen}
	 * labeled alternative in {@link StlParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterLiteralBoolParen(StlParser.LiteralBoolParenContext ctx);

	/**
	 * Exit a parse tree produced by the {@code LiteralBoolParen}
	 * labeled alternative in {@link StlParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitLiteralBoolParen(StlParser.LiteralBoolParenContext ctx);

	/**
	 * Enter a parse tree produced by the {@code LiteralBool}
	 * labeled alternative in {@link StlParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterLiteralBool(StlParser.LiteralBoolContext ctx);

	/**
	 * Exit a parse tree produced by the {@code LiteralBool}
	 * labeled alternative in {@link StlParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitLiteralBool(StlParser.LiteralBoolContext ctx);

	/**
	 * Enter a parse tree produced by the {@code LiteralIntParen}
	 * labeled alternative in {@link StlParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterLiteralIntParen(StlParser.LiteralIntParenContext ctx);

	/**
	 * Exit a parse tree produced by the {@code LiteralIntParen}
	 * labeled alternative in {@link StlParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitLiteralIntParen(StlParser.LiteralIntParenContext ctx);

	/**
	 * Enter a parse tree produced by the {@code LiteralInt}
	 * labeled alternative in {@link StlParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterLiteralInt(StlParser.LiteralIntContext ctx);

	/**
	 * Exit a parse tree produced by the {@code LiteralInt}
	 * labeled alternative in {@link StlParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitLiteralInt(StlParser.LiteralIntContext ctx);

	/**
	 * Enter a parse tree produced by the {@code LiteralBoolean}
	 * labeled alternative in {@link StlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteralBoolean(StlParser.LiteralBooleanContext ctx);

	/**
	 * Exit a parse tree produced by the {@code LiteralBoolean}
	 * labeled alternative in {@link StlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteralBoolean(StlParser.LiteralBooleanContext ctx);

	/**
	 * Enter a parse tree produced by the {@code LiteralInteger}
	 * labeled alternative in {@link StlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteralInteger(StlParser.LiteralIntegerContext ctx);

	/**
	 * Exit a parse tree produced by the {@code LiteralInteger}
	 * labeled alternative in {@link StlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteralInteger(StlParser.LiteralIntegerContext ctx);

	/**
	 * Enter a parse tree produced by the {@code Id}
	 * labeled alternative in {@link StlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterId(StlParser.IdContext ctx);

	/**
	 * Exit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link StlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitId(StlParser.IdContext ctx);
}