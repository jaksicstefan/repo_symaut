package test;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import automaton.TTester;
import automaton.Variable;
import frontend.AutomataGenListener;

/**
 * We shall use this class to obtain automata from STL formulas and test it with
 * our algorithm. This class implements only software monitors.
 * 
 * @author JaksicS
 *
 */
public class MainTestSwMonitors extends BaseTest {


	/***
	 * Main class function. The goal is to parse STL formula, traverse AST, create
	 * the automata in the automata Map.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String theFormula;
		ParseTree tree;

		loadXMLconfig();
		// ------------------ SETUP -----------------
		applyConfig();
		
		// ---------------------------------------------------------
		System.out.println("\n------------------------ BEGIN TEST -------------------------\n");
		tree = parseSTLFormulaFromFile("src\\parser\\toparse");
		ParseTreeWalker walker = new ParseTreeWalker();

		// -------------------------------------------------------------
		// ------------------- PRE-PROCESSING PHASE --------------------
		theFormula = preprocessingPhase(tree);

		// ---------------------------------------------------------
		System.out.println("\n----------------- AUTOMATA GENERATION PHASE -----------------\n");
		tree = parseSTLFormulaFromString(theFormula);

		System.out.println("-------------------------------------------------------------");
		AutomataGenListener aListener = new AutomataGenListener();
		walker.walk(aListener, tree);

		System.out.println("\n-------------------------------------------------------------\n");
		System.out.println("-------------------------------------------------------------");
		System.out.println("\n----------------- COMPOSING TOP LEVEL TESTER --------------\n");

		TTester composition = composeTT(aListener);
		printCompositionInfo(composition);
		updateCfgWithVars(composition.getVariables()); 

		// ------------------- RUN THE ALGORITHM ------------------------//
		System.out.println("\n----------------- OBTAINING THE DISTANCE --------------\n");

		Variable.numVars = composition.getVariables().size();

//		trace = read_trace();		
//		run_distance(composition);

		//readCSV_trace("trace\\trace.csv", ",");
		readCSV_trace("trace\\acc_velcity.txt", "[,\\s+]");
		
		runDistanceCSV(composition);

		System.out.println("---------------------------------------------------------------");
		System.out.println("-------------------- T E S T  E N D S -------------------------");
	}


}
