package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import gen.StlParser;
import gen.StlParser.AssertionContext;
import gen.StlParser.ExpressionContext;
import gen.StlParser.PrimaryContext;
import gen.StlParserBaseListener;

public class STLPreProcessingListener extends StlParserBaseListener {

	/**
	 * This map will remember which context occurred and which formula was
	 * resulted after processing the context.
	 */
	public HashMap<String, String> ctx2strMap;

	/**
	 * The key string necessary to find the top formula.
	 */
	public String top_string = "";

	// ------------------------------------------------------------------------------
	/**
	 * Constructs members of the class
	 */
	public STLPreProcessingListener() {
		ctx2strMap = new HashMap<String, String>();
		top_string = "";
	}

	/**
	 * Applies the following rewriting rules:
	 * 
	 * p Until[0:b] q <=> p Until q AND eventually[0:b] q
	 * 
	 * p Until[a:b] q <=> always[0:a]p AND next^a (p Until[0,b-a] q)
	 */
	public void exitExprUntil(StlParser.ExprUntilContext ctx) {
		System.out.println("UNTIL  " + ctx.getText());
	
		String lhs_formula; 
		if (ctx2strMap.get(ctx.expression(0).getText()).trim().substring(0,1).equalsIgnoreCase("("))
			lhs_formula = "" + ctx2strMap.get(ctx.expression(0).getText()).trim() + "";
		else
			lhs_formula = "(" + ctx2strMap.get(ctx.expression(0).getText()).trim() + ")";

		String rhs_formula; 
		if (ctx2strMap.get(ctx.expression(1).getText()).trim().substring(0,1).equalsIgnoreCase("("))
			rhs_formula = "" + ctx2strMap.get(ctx.expression(1).getText()).trim() + "";
		else
			rhs_formula = "(" + ctx2strMap.get(ctx.expression(1).getText()).trim() + ")";
		
//		String lhs_formula = "(" + ctx2strMap.get(ctx.expression(0).getText()).trim() + ")";
//		String rhs_formula = "(" + ctx2strMap.get(ctx.expression(1).getText()).trim() + ")";
		StringBuffer aux_buf = new StringBuffer();
		StringBuffer next_buf = new StringBuffer();
		StringBuffer brace_buf = new StringBuffer();

		if (ctx.interval() != null) { // bounded version

			int a = Integer.parseInt(ctx.interval().getChild(1).getText());
			int b = Integer.parseInt(ctx.interval().getChild(3).getText());

			if (b == 0)
				System.err.println("exitExprUntil::Upper bound B should be greater than 0");

			if (a == 0) { // option#1
				aux_buf
						.append("(" + lhs_formula + " until " + rhs_formula + ") and (eventually[0:" + b + "]" + rhs_formula + ")");
			} else { // option#2
				
				next_buf.append("oracle "+a +"(");
				brace_buf.append(")");

//				for (int i = 0; i < a; i++) {
//					next_buf.append("next(");
//					brace_buf.append(")");
//				}
				aux_buf.append("((always[0:" + a + "]" + lhs_formula + ") and " + next_buf.toString() + lhs_formula
						+ " until[0:" + (b - a) + "]" + rhs_formula + brace_buf.toString() + ")");
			}

			top_string = ctx.getText();
			ctx2strMap.put(ctx.getText(), "" + aux_buf.toString() + "");
		}
	}

	/**
	 * Applies the following rewriting rules:
	 * 
	 * p Since[0:b] q <=> p Since q AND historically[0:b] q
	 * 
	 * p Since[a:b] q <=> historically[0:a]p AND prev^a (p Since[0,b-a] q)
	 */
	public void exitExprSince(StlParser.ExprSinceContext ctx) {
		// System.out.println("Since " + ctx.getText());
		String lhs_formula = "(" + ctx2strMap.get(ctx.expression(0).getText()).trim() + ")";
		String rhs_formula = "(" + ctx2strMap.get(ctx.expression(1).getText()).trim() + ")";
		StringBuffer aux_buf = new StringBuffer();
		StringBuffer prev_buf = new StringBuffer();
		StringBuffer brace_buf = new StringBuffer();

		if (ctx.interval() != null) { // bounded version

			int a = Integer.parseInt(ctx.interval().getChild(1).getText());
			int b = Integer.parseInt(ctx.interval().getChild(3).getText());

			if (b == 0)
				System.err.println("exitExprSince::Upper bound B should be greater than 0");

			if (a == 0) { // option#1
				aux_buf.append("(" + lhs_formula + " since " + rhs_formula + ") and (once[0:" + b + "]" + rhs_formula + ")");
			} else { // option#2
				for (int i = 0; i < a; i++) {
					prev_buf.append("prev(");
					brace_buf.append(")");
				}
				aux_buf.append("((historically[0:" + a + "]" + lhs_formula + ") and " + prev_buf.toString() + lhs_formula
						+ " since[0:" + (b - a) + "]" + rhs_formula + brace_buf.toString() + ")");
			}

			top_string = ctx.getText();
			ctx2strMap.put(ctx.getText(), "" + aux_buf.toString() + "");
		}
	}

	/**
	 * Applies the following rewriting rules:
	 * 
	 * historically[a:b] q <=> prev^a (historically[0:b-a] q)
	 * 
	 * where a>0.
	 */
	public void exitExprHistoricallyExpr(StlParser.ExprHistoricallyExprContext ctx) {
		String subformula = "(" + ctx2strMap.get(ctx.expression().getText()).trim() + ")";

		StringBuffer aux_buf = new StringBuffer();
		StringBuffer prev_buf = new StringBuffer();
		StringBuffer brace_buf = new StringBuffer();

		if (ctx.interval() != null) { // bounded version

			int a = Integer.parseInt(ctx.interval().getChild(1).getText());
			int b = Integer.parseInt(ctx.interval().getChild(3).getText());

			if (b == 0)
				System.err.println("exitExprHistoricallyExpr::Upper bound B should be greater than 0");

			if (a != 0) { // option#1

				for (int i = 0; i < a; i++) {
					prev_buf.append("prev(");
					brace_buf.append(")");
				}
				aux_buf
						.append(prev_buf.toString() + "(historically[0:" + (b - a) + "]" + subformula + brace_buf.toString() + ")");

				top_string = ctx.getText();
				ctx2strMap.put(ctx.getText(), "" + aux_buf.toString() + "");
			}
		}

	}

	/**
	 * Applies the following rewriting rules:
	 * 
	 * Once[a:b] q <=> prev^a (once[0:b-a] q)
	 * 
	 * where a>0.
	 */
	public void exitExprOnceExpr(StlParser.ExprOnceExprContext ctx) {
		String subformula = "(" + ctx2strMap.get(ctx.expression().getText()).trim() + ")";

		StringBuffer aux_buf = new StringBuffer();
		StringBuffer prev_buf = new StringBuffer();
		StringBuffer brace_buf = new StringBuffer();

		if (ctx.interval() != null) { // bounded version

			int a = Integer.parseInt(ctx.interval().getChild(1).getText());
			int b = Integer.parseInt(ctx.interval().getChild(3).getText());

			if (b == 0)
				System.err.println("exitExprOnceExpr::Upper bound B should be greater than 0");

			if (a != 0) { // option#1

				for (int i = 0; i < a; i++) {
					prev_buf.append("prev(");
					brace_buf.append(")");
				}
				aux_buf.append(prev_buf.toString() + "(once[0:" + (b - a) + "]" + subformula + brace_buf.toString() + ")");

				top_string = ctx.getText();
				ctx2strMap.put(ctx.getText(), "" + aux_buf.toString() + "");
			}
		}
	}

	/**
	 * Process a Terminal Node.
	 */
	public void visitTerminal(TerminalNode node) {
		ctx2strMap.put(node.getText(), node.toString() + " ");
	}

	/**
	 * Obtain the result of preprocessing stage.
	 * 
	 * @return
	 */
	public String getOutputFormula() {
		return ctx2strMap.get(top_string);
	}

	/**
	 * Propagating the terminal strings up the tree.
	 */
	public void exitEveryRule(ParserRuleContext ctx) {
		// System.out.println(" P R I N T "+ctx.getText()+"");
		List<ParseTree> children = ctx.children;
		ListIterator<ParseTree> listIter = children.listIterator();
		StringBuffer buff = new StringBuffer();

		while (listIter.hasNext()) {
			String str = listIter.next().getText();
			if (!(str.equals("<EOF>")))  //skip EOF
				buff.append(ctx2strMap.get(str) + " ");
		}

		String str = buff.toString().replaceAll("\\s+", " ").trim();
		str = str.replaceAll("assertion", "\nassertion").trim();

		if (ctx.getParent() != null) {
			if (!ctx2strMap.containsKey(ctx.getText())) {
				ctx2strMap.put(ctx.getText(), str + " ");
			}
		} else { /// top level rule
			ctx2strMap.put("top", str + " ");
			top_string = "top";
		}
	}
	
	/**
	 * 
	 */
	public void exitStlSpecification(StlParser.StlSpecificationContext ctx) {

		List<AssertionContext> assertList = ctx.assertion();

		StringBuffer strbuf = new StringBuffer();

		Iterator<AssertionContext> iter = assertList.iterator();

		while (iter.hasNext()) {
			strbuf.append(ctx2strMap.get(iter.next().getText()));
		}

		ctx2strMap.put(ctx.getText(), strbuf.toString() + " ");
	}
	
	/**
	 * Applies the following rewriting rules:
	 * 
	 * always[a:b] q <=> next^a (always[0:b-a] q)
	 * 
	 * where a>0.
	 */
	public void exitExprAlwaysExpr(StlParser.ExprAlwaysExprContext ctx) {
		String subformula = "(" + ctx2strMap.get(ctx.expression().getText()).trim() + ")";

		StringBuffer aux_buf = new StringBuffer();
		StringBuffer prev_buf = new StringBuffer();
		StringBuffer brace_buf = new StringBuffer();

		if (ctx.interval() != null) { // bounded version

			int a = Integer.parseInt(ctx.interval().getChild(1).getText());
			int b = Integer.parseInt(ctx.interval().getChild(3).getText());

			if (b == 0)
				System.err.println("exitExprAlwaysExpr::Upper bound B should be greater than 0");

			if (a != 0) { // option#1

				prev_buf.append("oracle "+a +"(");
				brace_buf.append(")");

//				for (int i = 0; i < a; i++) {
//					prev_buf.append("next(");
//					brace_buf.append(")");
//				}
				aux_buf
						.append(prev_buf.toString() + "(always[0:" + (b - a) + "]" + subformula + brace_buf.toString() + ")");

				top_string = ctx.getText();
				ctx2strMap.put(ctx.getText(), "" + aux_buf.toString() + "");
			}
		}

	}
	
	/**
	 * Applies the following rewriting rules:
	 * 
	 * eventually[a:b] q <=> next^a (eventually[0:b-a] q)
	 * 
	 * where a>0.
	 */
	public void exitExprEventually(StlParser.ExprEventuallyContext ctx) {
		String subformula = "(" + ctx2strMap.get(ctx.expression().getText()).trim() + ")";

		StringBuffer aux_buf = new StringBuffer();
		StringBuffer prev_buf = new StringBuffer();
		StringBuffer brace_buf = new StringBuffer();

		if (ctx.interval() != null) { // bounded version

			int a = Integer.parseInt(ctx.interval().getChild(1).getText());
			int b = Integer.parseInt(ctx.interval().getChild(3).getText());

			if (b == 0)
				System.err.println("exitExprEventually::Upper bound B should be greater than 0");

			if (a != 0) { // option#1

			prev_buf.append("oracle "+a +"(");
			brace_buf.append(")");

//				for (int i = 0; i < a; i++) {
//					prev_buf.append("next(");
//					brace_buf.append(")");
//				}
				aux_buf.append(prev_buf.toString() + "(eventually[0:" + (b - a) + "]" + subformula + brace_buf.toString() + ")");

				top_string = ctx.getText();
				ctx2strMap.put(ctx.getText(), "" + aux_buf.toString() + "");
			}
		}
	}
	
	
	/**
	 * This will be used to implement 
	 * 
	 * 	oracle N ( a and b) <=> oracle N a and oracle N b 
	 */
	public void exitExprOracleExpr(StlParser.ExprOracleExprContext ctx) { 		
//		String subformula = "(" + ctx2strMap.get(ctx.expression().getText()).trim() + ")";
		StringBuffer left_buf = new StringBuffer();
		StringBuffer right_buf = new StringBuffer();
		
		int oraclePower = Integer.parseInt(ctx.IntegerLiteral().getText());
		
		ExpressionContext expr = ctx.expression();
		PrimaryContext primary = (PrimaryContext) expr.getChild(0);
		ExpressionContext theExpression = (ExpressionContext) primary.getChild(1);
		
		boolean doRw = false;
		
		
		if ((theExpression.getChild(1) != null ) && ((theExpression.getChild(1).getText().equalsIgnoreCase("and")) || (theExpression.getChild(1).getText().equalsIgnoreCase("or")))) 
					doRw = true;
		
		if (doRw) //do the rewriting 
		{
			ExpressionContext theExpressionLeft = (ExpressionContext) theExpression.getChild(0);
			ExpressionContext theExpressionRight = (ExpressionContext) theExpression.getChild(2);
			
			String subformulaLeft, subformulaRight;
			
			if (theExpressionLeft.getText().substring(0,1).equalsIgnoreCase("("))
				subformulaLeft  = theExpressionLeft.getText().trim();
			else
				subformulaLeft  = "(" + theExpressionLeft.getText().trim() + ")";
			
			if (theExpressionRight.getText().trim().substring(0,1).equalsIgnoreCase("("))
				subformulaRight  = theExpressionRight.getText().trim();
			else
				subformulaRight  = "(" + theExpressionRight.getText().trim() + ")";
			
			left_buf.append("oracle "+ oraclePower + " " + subformulaLeft );
			right_buf.append("oracle "+ oraclePower + " " + subformulaRight );
			
			String str2put = "("+ left_buf.toString() + " " + theExpression.getChild(1).getText() + " " + right_buf.toString() + ")";		
			ctx2strMap.put(ctx.getText(), "" + str2put + "");
		}	
	}
}
