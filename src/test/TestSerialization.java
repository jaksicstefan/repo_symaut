package test;

import java.io.IOException;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import automaton.Automaton;
import automaton.TTester;
import frontend.AutomataGenListener;

/**
 * This test will be a very simple unit test to show that 
 * serialization of automata object i working properly/
 * 
 * Bacically, this test will create an automaton, serialize it.
 * Then, it will deserialize it and compare it with the original one.
 * @author JaksicS
 *
 */
public class TestSerialization extends BaseTest {
	
	
	/**
	 * Main function of the test. 
	 * @param args
	 */
	public static void main(String args[]){
		Automaton a1,a2;
		
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

		
		
		a1 = composition;
		a2 = null;
		
		
		
		// ------------- SERIALIZE
		try {
			SerializationUtil.serialize(a1, "serialized\\automaton_serialized");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Automata A1 successfully serialized. A1:");		
		System.out.println(a1.toString());
		
		
		
		//-------------- DESERIALIZE
		try {
			a2 = (Automaton) SerializationUtil.deserialize("serialized\\automaton_serialized");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Automata A1 deserialized. A1:");		
		System.out.println(a2.toString());
		
		if (a1.equals(a2)) {
			System.out.println("[ [ SUCCESS ] ]TestSerialization ended successfully.");
		}
		else{
			System.err.println(
					"\n\n[ [ FAIL ] ]TestSerialization *NOT* ended successfully. The compare object are not the same before and after serialiation .\n");
		}
	}

}
