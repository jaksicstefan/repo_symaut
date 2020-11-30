package frontend;

import java.util.ArrayList;
import java.util.HashMap;

import automaton.AutomataService;
import automaton.AutomatonTransition;
import automaton.TTester;
import automaton.Variable;
import gen.StlParser;
import gen.StlParser.IdCompContext;
import gen.StlParser.IdCompIdContext;
import gen.StlParser.IdCompIntContext;
import gen.StlParser.IntCompIdContext;
import gen.StlParserBaseListener;

public class AutomataGenListener extends StlParserBaseListener {

	/**
	 * The key which points to the top level automaton
	 * in the automataMap.
	 */
	public String topKey;
	
	/**
	 * This map will be used to exchange automata objects among different parse
	 * rules.
	 */
	public HashMap<String, TTester> automataMap;

	/**
	 * This hash map will store pairs of ParserRuleContext objects and assigned
	 * variable names.
	 */
	public HashMap<String, Variable> prc2signalMap;

	/**
	 * Keeping strings for numerical constraints
	 */
	public HashMap<String, String> numericalMap;

	/**
	 * Used to minimize the acceptor automaton. It will keep all the transitions
	 * for specific source and destination state pairs.
	 */
	public HashMap<String, ArrayList<AutomatonTransition>> acceptorTransitionMap;

	/**
	 * Map that will be used to count and store all the variables
	 */
	public HashMap<String, Variable> variableMap;

	// ---------------------------------------------------------
	// ---------------------------------------------------------
	/**
	 * 
	 */
	public AutomataGenListener() {
		automataMap = new HashMap<String, TTester>();
		prc2signalMap = new HashMap<String, Variable>();
		numericalMap = new HashMap<String, String>();
		acceptorTransitionMap = new HashMap<String, ArrayList<AutomatonTransition>>();
		variableMap = new HashMap<String, Variable>();
		topKey = "";
	}

	// TODO
	// HAVE TO ADD what happens in case of
	// STL SPECIFICATION:>>> JUST ADD ALWAYS TRUE AUTOMATON.

	/**
	 * Adds always(true) TTester into TTester map
	 */
	public void exitAssertion(StlParser.AssertionContext ctx) {
		TTester child = null;

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression().getText()))
				child = automataMap.get(ctx.expression().getText());
			else
				System.err.println("exitAssertion::CHILD AUTOMATON0 IS NOT IN THE MAP");

			TTester automaton = AutomataService.getAlwaysTrueTester(child.getSingleOutputVar()); // for
			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}
	}

	/**
	 * Generates Temporal tester for Since operator and stores it in the map.
	 */
	public void exitExprSince(StlParser.ExprSinceContext ctx) {
		TTester automaton = new TTester(false);
		TTester child0 = new TTester(false);
		TTester child1 = new TTester(false);
		
		if (ctx.interval()!=null)
			System.err.println(
					"exitExprSince: Bounded Since not supported. It should've been rewritten using unbounded Since.");

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression(0).getText()))
				child0 = automataMap.get(ctx.expression(0).getText());
			else
				System.err.println("exitLogSince::CHILD AUTOMATON0 IS NOT IN THE MAP");

			if (automataMap.containsKey(ctx.expression(1).getText()))
				child1 = automataMap.get(ctx.expression(1).getText());
			else
				System.err.println("exitLogSince::CHILD AUTOMATON1 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable var1 = child1.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);
			Variable varIn1 = new Variable(var1.getName(), var1.getMinValue(), var1.getMaxValue(), true);

			automaton = AutomataService.getUnboundedSinceTester(ctx, varIn0, varIn1, Variable.getFreshOutVar());

			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}
	}

	/**
	 * Generates Temporal tester for Previous operator and stores it in the map.
	 */
	public void exitExprPreviousExpr(StlParser.ExprPreviousExprContext ctx) {
		TTester child0 = null;
		TTester automaton = null;

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression().getText()))
				child0 = automataMap.get(ctx.expression().getText());
			else
				System.err.println("exitExprPreviousExpr::CHILD AUTOMATON0 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);

			automaton = AutomataService.getPrevTester(ctx, varIn0, Variable.getFreshOutVar());
			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}
	}

	/**
	 * Generates Temporal tester for an identifier and stores it in the map.
	 */
	public void exitId(StlParser.IdContext ctx) {
		Variable var = null, varOut = null;

		if (ctx.Identifier() != null) {
			var = new Variable(ctx.Identifier().toString(), 0, Variable.getTotalMaxValue(), true);
			varOut  = new Variable(ctx.Identifier().toString(), 0, Variable.getTotalMaxValue(), false);
			variableMap.put(ctx.Identifier().toString(), var);
		}

		if (!automataMap.containsKey(ctx.getText())) {
			automataMap.put(ctx.getText(), AutomataService.getVariableTester(var,varOut));
			topKey = ctx.getText();
		}

	}

	/**
	 * NOT YET IMPLEMENTED
	 */
	public void exitLiteralBoolean(StlParser.LiteralBooleanContext ctx) {
		// TODO
		// TODO
		System.err.println("NOT YET IMPLEMENTED.");
		// TODO
	}

	/**
	 * 
	 */
	public void exitExprNotUnaryExpr(StlParser.ExprNotUnaryExprContext ctx) {
		TTester child0 = null;
		TTester automaton = null;
		Variable var0;

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression().getText()))
				child0 = automataMap.get(ctx.expression().getText());
			else
				System.err.println("exitExprNotUnaryExpr::CHILD AUTOMATON0 IS NOT IN THE MAP");

			var0 = child0.getSingleOutputVar();

			Variable varIn = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);
			automaton = AutomataService.getNotTester(varIn, Variable.getFreshOutVar());
			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}
	}

	/**
	 * Generates Temporal Tester for "NOT" logic operation .
	 */
	public void exitExprNotUnaryId(StlParser.ExprNotUnaryIdContext ctx) {
		TTester child0 = null;
		TTester automaton = null;
		Variable var0;

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.identifier().getText()))
				child0 = automataMap.get(ctx.identifier().getText());
			else
				System.err.println("exitExprNotUnaryId::CHILD AUTOMATON0 IS NOT IN THE MAP");

			var0 = child0.getSingleOutputVar();

			Variable varIn = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);
			automaton = AutomataService.getNotTester(varIn, Variable.getFreshOutVar());
			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}
	}

	/**
	 * Generates Temporal Tester for "AND" logic operation .
	 */
	public void exitExprAnd(StlParser.ExprAndContext ctx) {
		TTester automaton = new TTester(false);
		TTester child0 = new TTester(false);
		TTester child1 = new TTester(false);

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression(0).getText()))
				child0 = automataMap.get(ctx.expression(0).getText());
			else
				System.err.println("exitExprAnd::CHILD AUTOMATON0 IS NOT IN THE MAP");

			if (automataMap.containsKey(ctx.expression(1).getText()))
				child1 = automataMap.get(ctx.expression(1).getText());
			else
				System.err.println("exitExprAnd::CHILD AUTOMATON1 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable var1 = child1.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);
			Variable varIn1 = new Variable(var1.getName(), var1.getMinValue(), var1.getMaxValue(), true);

			automaton = AutomataService.getAndTester(varIn0, varIn1, Variable.getFreshOutVar());

			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}
	}

	/**
	 * Generates Temporal Tester for "AND" logic operation .
	 */
	public void exitExprOr(StlParser.ExprOrContext ctx) {
		TTester automaton = new TTester(false);
		TTester child0 = new TTester(false);
		TTester child1 = new TTester(false);

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression(0).getText()))
				child0 = automataMap.get(ctx.expression(0).getText());
			else
				System.err.println("exitExprAnd::CHILD AUTOMATON0 IS NOT IN THE MAP");

			if (automataMap.containsKey(ctx.expression(1).getText()))
				child1 = automataMap.get(ctx.expression(1).getText());
			else
				System.err.println("exitExprAnd::CHILD AUTOMATON1 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable var1 = child1.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);
			Variable varIn1 = new Variable(var1.getName(), var1.getMinValue(), var1.getMaxValue(), true);

			automaton = AutomataService.getOrTester(varIn0, varIn1, Variable.getFreshOutVar());

			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}
	}

	/**
	 * Generates Temporal Tester for "XOR" logic operation .
	 */
	public void exitExprXor(StlParser.ExprXorContext ctx) {
		TTester automaton = new TTester(false);
		TTester child0 = new TTester(false);
		TTester child1 = new TTester(false);

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression(0).getText()))
				child0 = automataMap.get(ctx.expression(0).getText());
			else
				System.err.println("exitExprXor::CHILD AUTOMATON0 IS NOT IN THE MAP");

			if (automataMap.containsKey(ctx.expression(1).getText()))
				child1 = automataMap.get(ctx.expression(1).getText());
			else
				System.err.println("exitExprXor::CHILD AUTOMATON1 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable var1 = child1.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);
			Variable varIn1 = new Variable(var1.getName(), var1.getMinValue(), var1.getMaxValue(), true);

			automaton = AutomataService.getXorTester(varIn0, varIn1, Variable.getFreshOutVar());

			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}
	}
	
	/**
	 * Generates Temporal Tester for "XOR" logic operation .
	 */
	public void exitExprIff(StlParser.ExprIffContext ctx) {
		TTester automaton = new TTester(false);
		TTester child0 = new TTester(false);
		TTester child1 = new TTester(false);

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression(0).getText()))
				child0 = automataMap.get(ctx.expression(0).getText());
			else
				System.err.println("exitExprIff::CHILD AUTOMATON0 IS NOT IN THE MAP");

			if (automataMap.containsKey(ctx.expression(1).getText()))
				child1 = automataMap.get(ctx.expression(1).getText());
			else
				System.err.println("exitExprIff::CHILD AUTOMATON1 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable var1 = child1.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);
			Variable varIn1 = new Variable(var1.getName(), var1.getMinValue(), var1.getMaxValue(), true);

			automaton = AutomataService.getIffTester(varIn0, varIn1, Variable.getFreshOutVar());

			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}
	}


	/////////////////////////////////////////////////////////
	// TODO TODO TODO
	// public void exitEqOp(StlParser.EqOpContext ctx) {
	// ///TODO - this can be either X==5
	// // or X == TRUE
	// exitNumericalCst(ctx);
	// }
	//
	// public void exitNeqOp(StlParser.NeqOpContext ctx) {
	// ///TODO - this can be either X==5
	// // or X == TRUE
	// exitNumericalCst(ctx);
	// }

	//////////////////////////////////////////////////////////


	/////////////////////////////////////////////////////////
	public void exitIdCompId(StlParser.IdCompIdContext ctx) {
		exitNumericalCst(ctx);
	}

	public void exitIdCompInt(StlParser.IdCompIntContext ctx) {
		exitNumericalCst(ctx);
	}

	public void exitIntCompId(StlParser.IntCompIdContext ctx) {
		exitNumericalCst(ctx);
	}
	
	/////////////////////////////////////////////////////////
	
	/**
	 * Generates Temporal Tester for "Implication" logic operation .
	 */
	public void exitExprImplies(StlParser.ExprImpliesContext ctx) { 		
		TTester automaton = new TTester(false);
		TTester child0 = new TTester(false);
		TTester child1 = new TTester(false);

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression(0).getText()))
				child0 = automataMap.get(ctx.expression(0).getText());
			else
				System.err.println("exitExprAnd::CHILD AUTOMATON0 IS NOT IN THE MAP");

			if (automataMap.containsKey(ctx.expression(1).getText()))
				child1 = automataMap.get(ctx.expression(1).getText());
			else
				System.err.println("exitExprAnd::CHILD AUTOMATON1 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable var1 = child1.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);
			Variable varIn1 = new Variable(var1.getName(), var1.getMinValue(), var1.getMaxValue(), true);

			automaton = AutomataService.getImpliesTester(varIn0, varIn1, Variable.getFreshOutVar());

			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}	
	}
	
	
	/**
	 * Generates Temporal Tester for bounded "Historically" logic operator.
	 */
	public void exitExprHistoricallyExpr(StlParser.ExprHistoricallyExprContext ctx) { 
		TTester child0 = null;
		TTester automaton = null;

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression().getText()))
				child0 = automataMap.get(ctx.expression().getText());
			else
				System.err.println("exitExprHistoricallyExpr::CHILD AUTOMATON0 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);

			if (ctx.interval()!=null)				
				automaton = AutomataService.getBoundedHistoricallyTester(ctx, varIn0, Variable.getFreshOutVar());
			else
				automaton = AutomataService.getUnboundedHistoricallyTester(ctx, varIn0, Variable.getFreshOutVar());
			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}		
	}
	
	/**
	 * Generates Temporal Tester for bounded "Always" logic operator. 
	 */
	public void exitExprAlwaysExpr(StlParser.ExprAlwaysExprContext ctx) { 
		TTester child0 = null;
		TTester automaton = null;

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression().getText()))
				child0 = automataMap.get(ctx.expression().getText());
			else
				System.err.println("exitExprAlwaysExpr::CHILD AUTOMATON0 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);

			if (ctx.interval()!=null)				
				automaton = AutomataService.getBoundedAlwaysTester(ctx, varIn0, Variable.getFreshOutVar());
			else
				automaton = AutomataService.getUnboundedAlwaysTester(ctx, varIn0, Variable.getFreshOutVar());
			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}
	}

	/**
	 * Generates Temporal Tester for bounded "Once" logic operator. 
	 */
	public void exitExprOnceExpr(StlParser.ExprOnceExprContext ctx) { 
		TTester child0 = null;
		TTester automaton = null;

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression().getText()))
				child0 = automataMap.get(ctx.expression().getText());
			else
				System.err.println("exitExprOnceExpr::CHILD AUTOMATON0 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);

			if (ctx.interval()!=null)				
				automaton = AutomataService.getBoundedOnceTester(ctx, varIn0, Variable.getFreshOutVar());
			else
				automaton = AutomataService.getUnboundedOnceTester(ctx, varIn0, Variable.getFreshOutVar());
			
			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}		
	}
	 
	/**
	 * This will process numerical constraints such as X<4.
	 * It will create the Temporal Tester and put it in 
	 * automata map.
	 * @param ctx
	 */
	private void exitNumericalCst(IdCompContext ctx) {
		// this does not support 4<5
		// THIS SUPPORTS ONLY X<3 and X<Y and4>X
		Variable var0, var1, varOut;

		if (prc2signalMap.containsKey(ctx.getText())) {
			varOut = prc2signalMap.get(ctx.getText());
		} else {
			varOut = new Variable("u" + Variable.classID++, 0, Variable.getTotalMaxValue(), false);
			prc2signalMap.put(ctx.getText(), varOut);
			// TODO - check in other places
		}

		numericalMap.put(varOut.getName(), ctx.getText()); // TODO - why do we doThis


		if (ctx instanceof IdCompIntContext) {
			var0 = variableMap.get(((IdCompIntContext) ctx).identifier().getText());			
			if (!automataMap.containsKey(ctx.getText())) {
				automataMap.put(ctx.getText(), AutomataService.getNumericalTT(ctx, var0, Variable.getFreshOutVar()));
				topKey = ctx.getText();
			}
			
		} else if (ctx instanceof IntCompIdContext) {
			var0 = variableMap.get(((IntCompIdContext) ctx).identifier().getText());			
			if (!automataMap.containsKey(ctx.getText())) {
				automataMap.put(ctx.getText(), AutomataService.getNumericalTT(ctx, var0, Variable.getFreshOutVar()));
				topKey = ctx.getText();
			}
			
		} 
		else if (ctx instanceof IdCompIdContext){
			var0 = variableMap.get(((IdCompIdContext) ctx).identifier(0).getText());
			var1 = variableMap.get(((IdCompIdContext) ctx).identifier(1).getText());			
			if (!automataMap.containsKey(ctx.getText())) {
				automataMap.put(ctx.getText(), AutomataService.getNumericalIdTT(ctx, var0, var1, Variable.getFreshOutVar()));
				topKey = ctx.getText();
			}

		}
		else
			System.err.println("exitNumericalCst::Error, other not supported yet");

	}
	
	/**
	 * Generates Temporal tester for Next operator and stores it in the map.
	 */
	public void exitExprNextExpr(StlParser.ExprNextExprContext ctx) {
		TTester child0 = null;
		TTester automaton = null;

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression().getText()))
				child0 = automataMap.get(ctx.expression().getText());
			else
				System.err.println("exitExprPreviousExpr::CHILD AUTOMATON0 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);

			automaton = AutomataService.getNextTester(ctx, varIn0, Variable.getFreshOutVar());
			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}
	}
	
	/**
	 * Generates Temporal tester for Eventually operator and stores it in the map.
	 */
	public void exitExprEventually(StlParser.ExprEventuallyContext ctx) {
		TTester child0 = null;
		TTester automaton = null;

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression().getText()))
				child0 = automataMap.get(ctx.expression().getText());
			else
				System.err.println("exitExprOnceExpr::CHILD AUTOMATON0 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);

			if (ctx.interval()!=null)				
				automaton = AutomataService.getBoundedEventuallyTester(ctx, varIn0, Variable.getFreshOutVar());
			else
				automaton = AutomataService.getUnboundedEventuallyTester(ctx, varIn0, Variable.getFreshOutVar());
			
			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}		
	}

	
	/**
	 * Generates Temporal tester for Unbounded Until operator and stores it in the map.
	 * Bounded Until operator is handled in the preprocessing stage.
	 */
	public void exitExprUntil(StlParser.ExprUntilContext ctx) {
		TTester automaton = new TTester(false);
		TTester child0 = new TTester(false);
		TTester child1 = new TTester(false);
		
		if (ctx.interval()!=null)
			System.err.println(
					"exitExprUntil: Bounded Until not supported. It should've been rewritten using unbounded Until.");

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression(0).getText()))
				child0 = automataMap.get(ctx.expression(0).getText());
			else
				System.err.println("exitExprUntil::CHILD AUTOMATON0 IS NOT IN THE MAP");

			if (automataMap.containsKey(ctx.expression(1).getText()))
				child1 = automataMap.get(ctx.expression(1).getText());
			else
				System.err.println("exitExprUntil::CHILD AUTOMATON1 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable var1 = child1.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);
			Variable varIn1 = new Variable(var1.getName(), var1.getMinValue(), var1.getMaxValue(), true);

			automaton = AutomataService.getUnboundedUntilTester(ctx, varIn0, varIn1, Variable.getFreshOutVar());

			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();
		}
	}
	
	/**
	 * 
	 */
	public void exitPrimaryParenthesis(StlParser.PrimaryParenthesisContext ctx) { 
		
		TTester child			= new TTester(false);
		
		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression().getText()))
				child = automataMap.get(ctx.expression().getText());
			else
				System.err.println("exitPrimaryParenthesis::CHILD AUTOMATON IS NOT IN THE MAP");

			automataMap.put(ctx.getText(), child);
			topKey = ctx.getText();
		}

		
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * generating automaton for NEXT^N operator
	 */
	@Override
	public void exitExprOracleExpr(StlParser.ExprOracleExprContext ctx) {		
		TTester child0 = null;
		TTester automaton = null;
		
		int power = Integer.parseInt(ctx.IntegerLiteral().getText());

		if (!automataMap.containsKey(ctx.getText())) {

			if (automataMap.containsKey(ctx.expression().getText()))
				child0 = automataMap.get(ctx.expression().getText());
			else
				System.err.println("exitExprOracleExpr::CHILD AUTOMATON0 IS NOT IN THE MAP");

			Variable var0 = child0.getSingleOutputVar();
			Variable varIn0 = new Variable(var0.getName(), var0.getMinValue(), var0.getMaxValue(), true);

			System.out.println("obtained signal variability value: "+child0.signalVariability);
			automaton = AutomataService.getNextPowerTester(varIn0, Variable.getFreshOutVar(), power, child0.signalVariability);
			automataMap.put(ctx.getText(), automaton);
			topKey = ctx.getText();		
		}			
	}


	
	
	/**
	 * Getter for top level Temporal Tester.
	 * @return
	 */
	public TTester getTopTester(){
		return automataMap.get(topKey);		
	}

	/**
	 * 
	 * @return
	 */
	public String printAutomataMap() {		
		StringBuffer strbuf = new StringBuffer();
		
		strbuf.append("\nPrinting automata map:");
		
		for (String key : this.automataMap.keySet()) {
			strbuf.append("\n----------------------------");			
			strbuf.append("\nAutomata for key: "+ key);
			strbuf.append("\n"+this.automataMap.get(key));
		}
		
		return strbuf.toString();
	}

}
