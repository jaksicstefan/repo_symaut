package test;

import java.util.ArrayList;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import automaton.AutomataService;
import automaton.Variable;
import automaton.VariableInstance;
import constraint.*;
import constraint.ConstraintNode.*;
import cst_gen.CstLexer;
import cst_gen.CstParser;

/***
 * Test class for testing the constraint class implementation.
 * 
 * @author JaksicS
 *
 */
public class TestConstraintEnv {

	public static void main(String[] args) throws Exception {

		
		//formula to represent and convert to NNF form:
		// not( not( A and (B or C)) OR (F and D))
		

		ConstraintNode cnode0 = new ConstraintNode(NodeType.LEAF_ID, "A");
		ConstraintNode cnode1 = new ConstraintNode(NodeType.LEAF_ID, "B");
		ConstraintNode cnode2 = new ConstraintNode(NodeType.LEAF_ID, "C");
		ConstraintNode cnode3 = new ConstraintNode(NodeType.LOG_OR, "or");
		ConstraintNode cnode4 = new ConstraintNode(NodeType.LOG_AND, "and");
		
		ConstraintNode cnode4NOT = new ConstraintNode(NodeType.LOG_NOT, "not");
		ConstraintNode cnode5 = new ConstraintNode(NodeType.LEAF_ID, "F");
		ConstraintNode cnode6 = new ConstraintNode(NodeType.LEAF_ID, "D");
		ConstraintNode cnode7 = new ConstraintNode(NodeType.LOG_AND, "and");
//		ConstraintNode cnode7NOT = new ConstraintNode(NodeType.LOG_NOT, "not");
		ConstraintNode cnode8 = new ConstraintNode(NodeType.LOG_OR, "or");
		
		ConstraintNode cnode10NOT = new ConstraintNode(NodeType.LOG_NOT, "not");
		
	

		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, "not(  (" + "M " + ")");
	  ArrayList<Constraint> cstList = AutomataService.prepareCstList(strList);

		ArrayList<ConstraintNode> nodeList = new ArrayList<>();
		nodeList.add(cnode0);
		nodeList.add(cnode1);
		nodeList.add(cnode2);
		nodeList.add(cnode3);
		
		nodeList.add(cnode4);
		nodeList.add(cnode4NOT);

		nodeList.add(cnode5);
		nodeList.add(cnode6);
		nodeList.add(cnode7);
//		nodeList.add(cnode7NOT);
		nodeList.add(cnode8);
		nodeList.add(cnode10NOT);

		Constraint myConstraint = cstList.get(0);// new Constraint(nodeList);
		System.out.println("Constraint to test: ");
		System.out.println(myConstraint.toString());


		myConstraint = myConstraint.negate();
		System.out.println("negated constraint to test: \n" + myConstraint);



		// now test NNF
		myConstraint.toNNF();
		
		System.out.println("\nConstraint tree after applying NNF:\n");
		System.out.println(myConstraint.toString());
		
		System.out.println("\n Apply NEGATION :\n");
		System.out.println(myConstraint.negate());
		
		
		System.out.println("_____________________________________");
		System.out.println("_____________________________________");
		
		
		strList = new ArrayList<String>(2);
		strList.add(0, " (not p) and  (r  and  (not p) and (not u18))");
		strList.add(1, " q and  m");

	  cstList = AutomataService.prepareCstList(strList);
		myConstraint = cstList.get(0);						// new Constraint(nodeList);
		Constraint myConstraint2 = cstList.get(1);// new Constraint(nodeList);
		
		VariableInstance varInst = new VariableInstance(new Variable("u18", 0, 1, true), false);
		
		System.out.println("Constraint to edit: ");
		System.out.println(myConstraint.toString());
		
		System.out.println("To replace: ");
		System.out.println(varInst.toString());

		System.out.println("\nreplacement: ");
		System.out.println(myConstraint2.toString());		
		
		CstLexer lexer = new CstLexer(CharStreams.fromString(varInst.toString()));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CstParser parser = new CstParser(tokens);
		ParseTree tree;
		ConstraintGenVisitor visitor = new ConstraintGenVisitor();

		tree = parser.cst();
		Constraint searchedCst = visitor.visit(tree); // this is a replacement for cst.

		
		System.out.println("F O U N D "+ myConstraint.findConstraintNode(searchedCst.getRoot()));;		
		
		myConstraint = AutomataService.replaceVarWithCst(varInst, myConstraint, myConstraint2);
		
		System.out.println("\nConstraint tree after applying replaceVarWithCst:\n");
		System.out.println(myConstraint.toString());
		
		System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("> < > < > < > < > < > < > < > < > < > <");
		System.out.println("_______  TESTING MINIMIZATION ________ ");
		System.out.println("> < > < > < > < > < > < > < > < > < > <");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");

		//two var formulas
//		testAndSwapping("(not p) and  (not q)");
//		testAndSwapping("(not p) and  (    q)");
//		testAndSwapping("(    p) and  (not q)");
//		testAndSwapping("(    p) and  (    q)");
//		
//		testAndSwapping("(not q) and  (not q)");
//		testAndSwapping("(not q) and  (    q)");
//		testAndSwapping("(    q) and  (not q)");
//		testAndSwapping("(    q) and  (    q)");
//
//		testAndSwapping("(not p) and  (not q) and  (not r)"); //		no minimization whatsoever.
//		testAndSwapping("(not p) and  (not q) and  (    r)");		
//		testAndSwapping("(not p) and  (    q) and  (not r)");		
//		testAndSwapping("(not p) and  (    q) and  (	  r)");
//		testAndSwapping("(    p) and  (not q) and  (not r)");
//		testAndSwapping("(    p) and  (not q) and  (    r)");	
//		testAndSwapping("(    p) and  (    q) and  (not r)");
//		testAndSwapping("(    p) and  (    q) and  (    r)");
//		
//		testAndSwapping("(not q) and  (not q) and  (not q)");
//		testAndSwapping("(not q) and  (not q) and  (    q)");		
//		testAndSwapping("(not q) and  (    q) and  (not q)");		
//		testAndSwapping("(not q) and  (    q) and  (	  q)");
//		testAndSwapping("(    q) and  (not q) and  (not q)");
//		testAndSwapping("(    q) and  (not q) and  (    q)");	
//		testAndSwapping("(    q) and  (    q) and  (not q)");
//		testAndSwapping("(    q) and  (    q) and  (    q)");
//		
//		testAndSwapping("(not q) and  (not q) and  (not q) and  (not q)");
//		testAndSwapping("(not q) and  (not q) and  (not q) and  (    q)");		
//		testAndSwapping("(not q) and  (not q) and  (    q) and  (not q)");		
//		testAndSwapping("(not q) and  (not q) and  (    q) and  (	  q)");
//		testAndSwapping("(not q) and  (    q) and  (not q) and  (not q)");
//		testAndSwapping("(not q) and  (    q) and  (not q) and  (    q)");	
//		testAndSwapping("(not q) and  (    q) and  (    q) and  (not q)");
//		testAndSwapping("(not q) and  (    q) and  (    q) and  (    q)");
//
//		testAndSwapping("( q) and  (not q) and  (not q) and  (not q)");
//		testAndSwapping("( q) and  (not q) and  (not q) and  (    q)");		
//		testAndSwapping("( q) and  (not q) and  (    q) and  (not q)");		
//		testAndSwapping("( q) and  (not q) and  (    q) and  (	  q)");
//		testAndSwapping("( q) and  (    q) and  (not q) and  (not q)");
//		testAndSwapping("( q) and  (    q) and  (not q) and  (    q)");	
//		testAndSwapping("( q) and  (    q) and  (    q) and  (not q)");
//		testAndSwapping("( q) and  (    q) and  (    q) and  (    q)");
		
		
		
		System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("> < > < > < > < > < > < > < > < > < > <");
		System.out.println("_______  TESTING NNF CONVERSION ________ ");
		System.out.println("> < > < > < > < > < > < > < > < > < > <");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");

		
		testNNFConversionNumerical( "not (x==3)");
		testNNFConversionNumerical( "not (x!==3)");
		testNNFConversionNumerical( "not (x>3)");
		testNNFConversionNumerical( "not (x>=3)");
		testNNFConversionNumerical( "not (x<3)");
		testNNFConversionNumerical( "not (x<=3)");
	}

	/**
	 * Testing the Constraint object minimization
	 * @param formula2test
	 */
	@SuppressWarnings("unused")
	private static void testAndSwapping(String formula2test) {
		System.out.println("______________________________");
		System.out.println("      testAndSwapping test    ");
		System.out.println("______________________________");

		System.out.println("testAndSampling : formula to minimize:\n" + formula2test);
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, formula2test);
	  ArrayList<Constraint> cstList = AutomataService.prepareCstList(strList);

	  cstList = AutomataService.prepareCstList(strList);
		Constraint myConstraint = null;
		try {
			myConstraint = (Constraint) cstList.get(0).clone();
		} catch (CloneNotSupportedException e) {
	// TODO Auto-generated catch block
			e.printStackTrace();
		}						// new Constraint(nodeList);
		System.out.println("> pass1: Constraint to minimize: ");
		System.out.println(myConstraint.toString());
				
		myConstraint.reduceSimplify();
		
		if (myConstraint.equals(cstList.get(0)))
			System.out.println("Nothing to reduce here.");
		else{
			System.out.println("After Minimization: ");
			System.out.println(myConstraint);	
		}
	}
	
	/**
	 * 
	 */
	private static void testNNFConversionNumerical(String str2test){
		System.out.println("______________________________");
		System.out.println(" testNNFConversionNumerical test ");
		System.out.println("______________________________");

		
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, str2test);
		ArrayList<Constraint> cstList = AutomataService.prepareCstList(strList);
	  
	  Constraint cst2test = cstList.get(0);
	  System.out.println("pre NNF\n"+cst2test);

	  cst2test.toNNF();
	  System.out.println("to NNF\n"+cst2test);

	}

}
