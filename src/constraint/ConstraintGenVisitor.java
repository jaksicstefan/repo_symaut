package constraint;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import constraint.ConstraintNode.NodeType;
import cst_gen.CstParser;
import cst_gen.CstParserBaseVisitor;

/**
 * This visitor class is used to convert an ANTLR Parse Tree object into a
 * Constraint object.
 * 
 * @author JaksicS
 *
 */
public class ConstraintGenVisitor extends CstParserBaseVisitor<Constraint> {

	public Constraint visitExprNotUnaryExpr(CstParser.ExprNotUnaryExprContext ctx) {
		return notOp(ctx);
	}

	public Constraint visitExprNotUnaryId(CstParser.ExprNotUnaryIdContext ctx) {
		return notOp(ctx);
	}

	public Constraint visitIdCompId(CstParser.IdCompIdContext ctx) {
		return visitBinaryOpNode(ctx);
	}

	public Constraint visitIdCompInt0(CstParser.IdCompInt0Context ctx) {
		return visitBinaryOpNode(ctx);
	}

	public Constraint visitIdCompInt1(CstParser.IdCompInt1Context ctx) {
		return visitBinaryOpNode(ctx);
	}

	public Constraint visitIdCompInt2(CstParser.IdCompInt2Context ctx) {
		return visitBinaryOpNode(ctx);
	}

	public Constraint visitExprEqualExpr(CstParser.ExprEqualExprContext ctx) {
		return visitBinaryOpNode(ctx);
	}

	public Constraint visitExprAnd(CstParser.ExprAndContext ctx) {
		return visitBinaryOpNode(ctx);
	}

	public Constraint visitExprOr(CstParser.ExprOrContext ctx) {
		return visitBinaryOpNode(ctx);
	}

	public Constraint visitEqOp(CstParser.EqOpContext ctx) {
		return visitToken(ctx);
	}

	public Constraint visitNeqOp(CstParser.NeqOpContext ctx) {
		return visitToken(ctx);
	}

	public Constraint visitCmpOpLs(CstParser.CmpOpLsContext ctx) {
		return visitToken(ctx);
	}

	public Constraint visitCmpOpGte(CstParser.CmpOpGteContext ctx) {
		return visitToken(ctx);
	}

	public Constraint visitCmpOpLse(CstParser.CmpOpLseContext ctx) {
		return visitToken(ctx);
	}

	public Constraint visitCmpOpGt(CstParser.CmpOpGtContext ctx) {
		return visitToken(ctx);
	}

	public Constraint visitPrimaryLiteralBool(CstParser.PrimaryLiteralBoolContext ctx) {
		return visitToken(ctx);
	}

	public Constraint visitPrimaryId(CstParser.PrimaryIdContext ctx) {
		return visitToken(ctx);
	}

	public Constraint visitLiteralBoolParen(CstParser.LiteralBoolParenContext ctx) {
		return visitToken(ctx.BooleanLiteral());
	}

	public Constraint visitLiteralBool(CstParser.LiteralBoolContext ctx) {
		return visitToken(ctx.BooleanLiteral());
	}

	public Constraint visitLiteralIntParen(CstParser.LiteralIntParenContext ctx) {
		return visitToken(ctx.IntegerLiteral());
	}

	public Constraint visitLiteralInt(CstParser.LiteralIntContext ctx) {
		return visitToken(ctx.IntegerLiteral());
	}

	public Constraint visitId(CstParser.IdContext ctx) {
		return visitToken(ctx);
	}

	// my methods
	// -------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------

	private Constraint visitToken(TerminalNode tn) {
		// System.out.println("visitToken : Terminal node : " + tn.getText());
		Constraint newCst = new Constraint(new ConstraintNode(Constraint.convertToNodeType(tn.getText()), tn.getText()));
		return newCst;
	}

	private Constraint visitToken(ParserRuleContext ctx) {
		// System.out.println("visitToken : ctx : " + ctx.getText());
		Constraint newCst = new Constraint(new ConstraintNode(Constraint.convertToNodeType(ctx.getText()), ctx.getText()));
		return newCst;
	}

	private Constraint visitBinaryOpNode(ParserRuleContext ctx) {
		// System.out.println("visitBinaryOpNode : ctx : " + ctx.getText());
		ArrayList<Constraint> childrenCsts = new ArrayList<Constraint>();

		for (ParseTree childCtx : ctx.children) {
			childrenCsts.add(visit(childCtx));
		}

		String midChString = ctx.children.get(1).getText();

		ConstraintNode newRoot;
		newRoot = new ConstraintNode(Constraint.convertToNodeType(midChString), midChString, childrenCsts.get(0).getRoot(),
				childrenCsts.get(2).getRoot());
		childrenCsts.get(0).getRoot().setParent(newRoot);
		childrenCsts.get(2).getRoot().setParent(newRoot);
		
		Constraint newCst = new Constraint(newRoot);
		return newCst;
	}

	// public Constraint defaultResult(){
	// System.err.println("ConstraintGenVisitor.defaultResult: Possible Syntax
	// Error");
	// return null;
	// };

	public Constraint notOp(ParserRuleContext ctx) {
		// System.out.println("notOp : ctx : " + ctx.getText());

		Constraint chldCst = visitChildren(ctx);

		ConstraintNode newRoot = new ConstraintNode(NodeType.LOG_NOT, "not", chldCst.getRoot());
		chldCst.getRoot().setParent(newRoot);
		chldCst.setRoot(newRoot);
		return chldCst;
	}

	//
	// just omit the parenthesis
	public Constraint visitPrimaryParenthesis(CstParser.PrimaryParenthesisContext ctx) {
		return visit(ctx.children.get(1));
	}

	public Constraint visitCstExpr(CstParser.CstExprContext ctx) {
		return visit(ctx.children.get(0));
	}

}
