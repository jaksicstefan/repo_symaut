package test;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.sosy_lab.common.configuration.InvalidConfigurationException;
import org.sosy_lab.java_smt.api.SolverException;

import automaton.AutomataService;
import automaton.Automaton;
import automaton.AutomatonState;
import automaton.TTester;
import automaton.Variable;
import automaton.VariableInstance;
import constraint.Constraint;
import constraint.ConstraintGenVisitor;
import cst_gen.CstLexer;
import cst_gen.CstParser;
import gen.StlLexer;
import gen.StlParser;
import gen.StlParser.ExprAlwaysExprContext;
import gen.StlParser.ExprEventuallyContext;
import gen.StlParser.ExprHistoricallyExprContext;
import gen.StlParser.ExprOnceExprContext;
import gen.StlParser.ExprSinceContext;

public class TestAutomataService {

	/**
	 * Main test function for class AutomataService.
	 * @param args
	 */
	public static void main(String[] args) {

		//basic_test();
		
		testAlwaysTrueTester();
		
		testTTtoAutomata();

		//product_test0();
		
		
		
/*	System.out.println("________ ________ ________ ________");
		System.out.println("___ ___ ___ ___ ___ ___ ___ ___ ___");

		System.out.println("===================================");
		System.out.println("\n\nProduct test 1\n");
		System.out.println(
				"This test will create a temporal tester for formula\nFI = always (p)\nusing syncAndCompose(...) function .");
//		product_test1();
		System.out.println("________ ________ ________ ________");
		System.out.println("___ ___ ___ ___ ___ ___ ___ ___ ___");
		
		System.out.println("===================================");
		System.out.println("\n\nProduct test 2\n");
		System.out.println(
				"This test will create a temporal tester for formula\nFI = (eventually[0:2]  p)\nusing syncAndCompose(...) function .");
//		product_test2();
		System.out.println("________ ________ ________ ________");
		System.out.println("___ ___ ___ ___ ___ ___ ___ ___ ___");
		
*/
	}

	/**
	 * Tests basic Automata Service methods
	 */
	public static void basic_test() {
		System.out.println("\n\nJust a basic test\n");
		System.out.println(
				"This test will invoke methods for unbounded eventually, since, always and bounded eventually temporal testers.");

		StlLexer lexer = null;
		CommonTokenStream tokens;
		StlParser parser;
		ParseTree tree = null;

		lexer = new StlLexer(CharStreams.fromString("p since q"));
		tokens = new CommonTokenStream(lexer);

		parser = new StlParser(tokens);
		tree = parser.expression();

		Variable varP = new Variable("P", 0, 1, true); // input boolean var P
		Variable varQ = new Variable("Q", 0, 1, true); // input boolean var Q
		Variable varU = new Variable("U", 0, 1, false); // output boolean var U

		System.out.println("------------------------------------------------------------------\n\n");

		System.out.println("------------------------------------------------------------------");
		System.out.println("-----------------   Testing unbounded since  ---------------------");
		System.out.println("------------------------------------------------------------------");

		TTester sinceTT = AutomataService.getUnboundedSinceTester((ExprSinceContext) tree, varP, varQ, varU);

		System.out.println(sinceTT.toString());

		// ======================================

		System.out.println("------------------------------------------------------------------");
		System.out.println("--------------   Testing unbounded eventually  -------------------");
		System.out.println("------------------------------------------------------------------");

		AutomatonState.stateID = 0; // reset
		lexer = new StlLexer(CharStreams.fromString("eventually p"));
		tokens = new CommonTokenStream(lexer);

		parser = new StlParser(tokens);
		tree = parser.expression();

		TTester eventuallyTT = AutomataService.getUnboundedEventuallyTester((ExprEventuallyContext) tree, varP, varU);

		System.out.println(eventuallyTT.toString());

		System.out.println("------------------------------------------------------------------");
		System.out.println("-----------------  Testing unbounded always  ---------------------");
		System.out.println("------------------------------------------------------------------");

		AutomatonState.stateID = 0; // reset
		lexer = new StlLexer(CharStreams.fromString("always p"));
		tokens = new CommonTokenStream(lexer);

		parser = new StlParser(tokens);
		tree = parser.expression();

		TTester alwaysTT = AutomataService.getUnboundedAlwaysTester((ExprAlwaysExprContext) tree, varP, varU);

		System.out.println(alwaysTT.toString());

		System.out.println("------------------------------------------------------------------");
		System.out.println("-----------------  Testing bounded eventually  -------------------");
		System.out.println("------------------------------------------------------------------");

		AutomatonState.stateID = 0; // reset
		lexer = new StlLexer(CharStreams.fromString("eventually[0:2] p"));
		tokens = new CommonTokenStream(lexer);

		parser = new StlParser(tokens);
		tree = parser.expression();

		TTester boundedEventuallyTT = AutomataService.getBoundedEventuallyTester((ExprEventuallyContext) tree, varP, varU);

		System.out.println(boundedEventuallyTT.toString());
		

		System.out.println("------------------------------------------------------------------");
		System.out.println("-----------------  Testing historically[0:3]p  -------------------");
		System.out.println("------------------------------------------------------------------");

		AutomatonState.stateID = 0; // reset
		lexer = new StlLexer(CharStreams.fromString("historically[0:3] p"));
		tokens = new CommonTokenStream(lexer);

		parser = new StlParser(tokens);
		tree = parser.expression();

		TTester historicallyTT = AutomataService.getBoundedHistoricallyTester((ExprHistoricallyExprContext) tree, varP, varU);

		System.out.println(historicallyTT.toString());
		
		
		System.out.println("------------------------------------------------------------------");
		System.out.println("-----------------  Testing once[0:3]p  -------------------");
		System.out.println("------------------------------------------------------------------");

		AutomatonState.stateID = 0; // reset
		lexer = new StlLexer(CharStreams.fromString("once[0:3] p"));
		tokens = new CommonTokenStream(lexer);

		parser = new StlParser(tokens);
		tree = parser.expression();

		TTester onceTT = AutomataService.getBoundedOnceTester((ExprOnceExprContext) tree, varP, varU);

		System.out.println(onceTT.toString());



		System.out.println("------------------------------------------------------------------");
		System.out.println("-----------------  Testing replace var in tree  ------------------");
		System.out.println("------------------------------------------------------------------");

		Constraint newCst, cstToReplace;
		CstLexer lexer1 = new CstLexer(CharStreams.fromString(""));
		CommonTokenStream tokens1 = new CommonTokenStream(lexer1);
		CstParser parser1 = new CstParser(tokens1);
		ParseTree tree1;
		ConstraintGenVisitor visitor1 = new ConstraintGenVisitor();

		lexer1.setInputStream(CharStreams.fromString("RR"));
		tokens1 = new CommonTokenStream(lexer1);
		parser1.setTokenStream(tokens1);

		tree1 = parser1.cst();
		newCst = visitor1.visit(tree1); // this is a replacement for cst.

		//
		VariableInstance varinst1 = new VariableInstance(varP, false);

		lexer1.reset();
		lexer1.setInputStream(CharStreams.fromString("not P and Q"));
		tokens1 = new CommonTokenStream(lexer1);
		parser1.setTokenStream(tokens1);

		tree1 = parser1.cst();
		cstToReplace = visitor1.visit(tree1); // this is a replacement for cst.

		System.out
				.println("Replacing variable instance " + varinst1 + " in constraint \n" + cstToReplace + " with \n" + newCst);

		System.out.println("________________");
		System.out.println("before replacing\n\n " + cstToReplace);
		AutomataService.replaceVarWithCst(varinst1, cstToReplace, newCst);
		System.out.println("________________");
		System.out.println("after replacing\n\n " + cstToReplace);
		

	}

	/**
	 * Used to test a function which creates TTester used to obtain automaton.
	 */
	public static void testAlwaysTrueTester(){
		
		System.out.println("------------------------------------------------------------------");
		System.out.println("-----------------  Testing getAlwaysTrueTester  ------------------");
		System.out.println("------------------------------------------------------------------");

		Variable varU_output = new Variable("U", 0, 1, false); // output boolean varU

		TTester alwaysTTT = AutomataService.getAlwaysTrueTester(varU_output);
		System.out.println(alwaysTTT.toString());
	
	}
	
	
	/**
	 * Used to test a function which creates TTester used to obtain automaton.
	 */
	public static void testTTtoAutomata(){
		
		System.out.println("------------------------------------------------------------------");
		System.out.println("-----  Testing converstion of Temporal Tester to Automaton  ------");
		System.out.println("------------------------------------------------------------------");

		StlLexer lexer = null;
		CommonTokenStream tokens;
		StlParser parser;
		ParseTree tree = null;

		lexer = new StlLexer(CharStreams.fromString("always q"));
		tokens = new CommonTokenStream(lexer);

		parser = new StlParser(tokens);
		tree = parser.expression();

//		Variable varP = new Variable("P", 0, 1, true); // input boolean var P
		Variable varQ = new Variable("Q", 0, 1, true); // input boolean var Q
		Variable varU = new Variable("U", 0, 1, false); // output boolean var U

		TTester sinceTT = AutomataService.getUnboundedAlwaysTester((ExprAlwaysExprContext) tree, varQ, varU);

		Automaton resultingAutomata = AutomataService.convertToAcceptor(sinceTT);
		
		System.out.println("printing Unbounded Always Temporal Tester");
		System.out.println(sinceTT);

		System.out.println(">   >  > >>");

		System.out.println("printing resulting AUTOMATON");
		System.out.println(resultingAutomata);
		
		
		//TESTING THE PRODUCT AND CONVERSION TO AUTOMATA---------------------
		
		System.out.println("________________________________");
		System.out.println("printing Product Temporal Tester");

		TTester ptt = product_test0();
		
//		System.out.println(ptt);
		Automaton resultingAutomata1 = AutomataService.convertToAcceptor(ptt);
		System.out.println(">   >  > >>");

		System.out.println("printing Product AUTOMATON");
		System.out.println(resultingAutomata1);

		
		
		System.out.println("________________________________");
		System.out.println("eventually[0:2] p");
		System.out.println("printing Product Temporal Tester");
		TTester mtt = product_test2();
//		System.out.println(mtt);
		Automaton resultingAutomata2 = AutomataService.convertToAcceptor(mtt);
		System.out.println(">   >  > >>");
		System.out.println("printing Product AUTOMATON");
		System.out.println(resultingAutomata2);


		
		
	}

	
	
	
	//----------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------
	
	/**
	 * This function will be used to test the
	 * AutomataService.syncAndCompose(tt1,tt2) method.
	 */
	public static TTester product_test0() {
		
		System.out.println("________ ________ ________ ________");
		System.out.println("___ ___ ___ ___ ___ ___ ___ ___ ___");

		System.out.println("===================================");
		System.out.println("\n\nProduct test 0\n");
		System.out.println(
				"This test will create a temporal tester for formula\nFI = always (eventually[0:2] p)\nusing syncAndCompose(...) function .");

		
		StlLexer lexer = null;
		CommonTokenStream tokens;
		StlParser parser;
		ParseTree tree = null;

		lexer = new StlLexer(CharStreams.fromString("eventually[0:2] p"));
		tokens = new CommonTokenStream(lexer);

		parser = new StlParser(tokens);
		tree = parser.expression();

		Variable varP_input = new Variable("P", 0, 1, true); // input boolean var P
		Variable varU_output = new Variable("U", 0, 1, false); // output boolean var
																														// U

		TTester boundedEventuallyTT = AutomataService.getBoundedEventuallyTester((ExprEventuallyContext) tree, varP_input,
				varU_output);

		System.out.println("\nTemporal tester for formula: eventually[0:2] p");
		System.out.println(boundedEventuallyTT.toString());

		// --------------always temporal tester--------------
		Variable varU_input = new Variable("U", 0, 1, true); // input boolean var U
		Variable varW_output = new Variable("W", 0, 1, false); // output boolean var
																														// W

		lexer = new StlLexer(CharStreams.fromString("always u"));
		tokens = new CommonTokenStream(lexer);

		parser = new StlParser(tokens);
		tree = parser.expression();

		TTester alwaysTT = AutomataService.getUnboundedAlwaysTester((ExprAlwaysExprContext) tree, varU_input, varW_output);

		System.out.println("Temporal tester for formula: always u");
		System.out.println(alwaysTT.toString());

		// --------------------------------------------------
		TTester productTT = null;
		try {
			productTT = AutomataService.syncAndCompose(boundedEventuallyTT, alwaysTT);
		} catch (InvalidConfigurationException | SolverException | InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\n------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("Temporal tester for formula: always (eventually[0:2] p)");
		System.out.println(productTT.toString());
		System.out.println("Total number of states:" + productTT.getStates().size());
		System.out.println("Total number of accepting states:" + productTT.getAcceptingStates().size());
		System.out.println("Total number of initial states:" + productTT.getInitStates().size());
		System.out.println("Total number of transitions:" + productTT.getTransitions().size());
		System.out.println("Total number of variables:" + productTT.getVariables().size());
		
		return productTT;
	}

	/**
	 * always P.
	 */
	public static TTester product_test1() {

		// tester for FI = P
		Variable varP_input = new Variable("P", 0, 1, true); // input boolean var P
		Variable varU_output = new Variable("U", 0, 1, false); // output boolean varU

		TTester pTT = AutomataService.getVariableTester(varP_input, varU_output);
		System.out.println("\nTemporal tester for formula: p");
		System.out.println(pTT.toString());

		// tester for always u
		Variable varU_input = new Variable("U", 0, 1, true); // input boolean var Q
		Variable varW_output = new Variable("W", 0, 1, false); // output boolean varW
		TTester alwaysTT = AutomataService.getUnboundedAlwaysTester(null, varU_input, varW_output);

		// --------------------------------------------------
		TTester productTT = null;
		try {
			productTT = AutomataService.syncAndCompose(pTT, alwaysTT);
		} catch (InvalidConfigurationException | SolverException | InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\n------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("Temporal tester for formula: always (p)");
		System.out.println(productTT.toString());
		System.out.println("Total number of states:" + productTT.getStates().size());
		System.out.println("Total number of accepting states:" + productTT.getAcceptingStates().size());
		System.out.println("Total number of initial states:" + productTT.getInitStates().size());
		System.out.println("Total number of transitions:" + productTT.getTransitions().size());
		System.out.println("Total number of variables:" + productTT.getVariables().size());
		
		return productTT;
	}

	/**
	 * (eventually[0:2]  p)
	 */
	public static TTester product_test2() {
		StlLexer lexer = null;
		CommonTokenStream tokens;
		StlParser parser;
		ParseTree tree = null;

		// tester for FI = P
		Variable varP_input = new Variable("P", 0, 1, true); // input boolean var P
		Variable varU_output = new Variable("U", 0, 1, false); // output boolean varU

		AutomatonState.stateID = 0; // reset
		lexer = new StlLexer(CharStreams.fromString("eventually[0:2] p"));
		tokens = new CommonTokenStream(lexer);

		parser = new StlParser(tokens);
		tree = parser.expression();

		TTester mTT = AutomataService.getBoundedEventuallyTester((ExprEventuallyContext) tree, varP_input, varU_output);

		System.out.println("\n------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("Temporal tester for formula: (eventually[0:2]  p)");
		System.out.println(mTT.toString());
		System.out.println("Total number of states:" + mTT.getStates().size());
		System.out.println("Total number of accepting states:" + mTT.getAcceptingStates().size());
		System.out.println("Total number of initial states:" + mTT.getInitStates().size());
		System.out.println("Total number of transitions:" + mTT.getTransitions().size());
		System.out.println("Total number of variables:" + mTT.getVariables().size());
		
		return mTT;
	}

}