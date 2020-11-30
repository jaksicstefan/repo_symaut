package test;

import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import automaton.TTester;
import frontend.AutomataCompositionBuilder;
import frontend.AutomataGenListener;
//import frontend.StlSemanticListener;
import gen.StlLexer;
import gen.StlParser;


/***
 * This class will be used to implement and test
 * the parser and semantic analysis. Furthermore,
 * it will be used to test the front end: stl 
 * to automata.
 * @author JaksicS
 *
 */
public class TestFrontEnd {
	
	/***
	 * The size of the input alphabet.
	 */
	static int alphabetSize;
	
	/***
	 * Just a default value for alphabet size.
	 */
	private static final int defaultAlphaSize = 10;	

	/***
	 * Main class function.
	 * The goal is to parse STL formula, 
	 * traverse AST,
	 * create the automata in the automata Map.
	 * @param args
	 */
	public static void main(String[] args) {
		

		if (args.length>0) {
			alphabetSize = Integer.parseInt(args[0]);			
		}
		else {
			System.out.println("WARNING: user did not specify alphabet size. Using the default value of: " + defaultAlphaSize);
			alphabetSize = defaultAlphaSize;
		}
		
		//--------------------------------------------------------------------------//
		//-------------------   BEGIN TEST  ------------------------------//
		System.out.println("\n----------------------- BEGIN TEST ------------------------\n");

		StlLexer lexer= null;
		CommonTokenStream tokens = null;
		StlParser parser;
		ParseTree tree = null;		
		
		try {
			lexer  = new StlLexer(CharStreams.fromFileName("src\\parser\\toparse"));
			tokens = new CommonTokenStream(lexer);
		
			parser = new StlParser(tokens);
			tree = parser.stlfile();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		ParseTreeWalker walker = new ParseTreeWalker();
		System.out.println("\nParsing the file:\n\n" + lexer.getInputStream().toString());

		//--------------------------------------------------------------------------//
		// This phase reduces p until[a:b]     to     p until[0, b-a]
		//-------------------   PRE-PROCESSING PHASE  ------------------------------//
		System.out.println("\n------------------- PRE-PROCESSING PHASE --------------------\n");
		STLPreProcessingListener ppSinceListener = new STLPreProcessingListener();
		walker.walk(ppSinceListener, tree);
			
		System.out.println("\nFormula after preprocessing:\n\n"+ppSinceListener.getOutputFormula()+"\n");
		
		//this preprocessing phase handles     p Until[0:B] q 
		System.out.println("\n------------------- PRE-PROCESSING PHASE 2 --------------------\n");
		lexer  = new StlLexer(CharStreams.fromString(ppSinceListener.getOutputFormula()));
		tokens = new CommonTokenStream(lexer);

		parser = new StlParser(tokens);
		tree = parser.stlfile();		
		ParseTreeWalker walker2 = new ParseTreeWalker();
		
		STLPreProcessingListener ppSinceListener2 = new STLPreProcessingListener();
		walker2.walk(ppSinceListener2, tree);			
		System.out.println("\nFormula after preprocessing2:\n\n"+ppSinceListener2.getOutputFormula()+"\n");

		//------ this will further distribute oracle operator
		System.out.println("\n------------------- PRE-PROCESSING PHASE 3 --------------------\n");
		
		lexer  = new StlLexer(CharStreams.fromString(ppSinceListener2.getOutputFormula()));
		tokens = new CommonTokenStream(lexer);
		parser = new StlParser(tokens);
		tree = parser.stlfile();		
		ParseTreeWalker walker3 = new ParseTreeWalker();
		
		STLPreProcessingListener ppSinceListener3 = new STLPreProcessingListener();
		walker3.walk(ppSinceListener3, tree);
		System.out.println("\nFormula after preprocessing3:\n\n"+ppSinceListener3.getOutputFormula()+"\n");

		
	
		//--------------------------------------------------------------------------//
		//-------------------   AUTOMATA GENERATION PHASE  ------------------------------//
		System.out.println("\n----------------- AUTOMATA GENERATION PHASE -----------------\n");

		lexer = new StlLexer(CharStreams.fromString(ppSinceListener3.getOutputFormula()));
		tokens = new CommonTokenStream(lexer);
		parser = new StlParser(tokens);
		tree = parser.stlfile();

		System.out.println("-------------------------------------------------------------");
		AutomataGenListener aListener = new AutomataGenListener(); 
		walker.walk(aListener, tree);	
		
		System.out.println("\n-------------------------------------------------------------\n");
		System.out.println("Top Level Temporal Tester" + aListener.getTopTester());
		
		System.out.println("\n-------------------------------------------------------------\n");
		System.out.println(aListener.printAutomataMap());
		
		System.out.println("-------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------");
		
		//--------------------------------------------------------------------------//
		//-------------------   COMPOSING TOP LEVEL TESTER  ------------------------------//
		System.out.println("\n----------------- COMPOSING TOP LEVEL TESTER --------------\n");
		
//		AutomataCompositionBuilder acl = new AutomataCompositionBuilder(aListener.automataMap, aListener.topKey);
		AutomataCompositionBuilder acl = new AutomataCompositionBuilder(aListener.automataMap, aListener.topKey);

		
	  TTester composition = acl.composeProduct();
	  
	  System.out.println("\nThe composition contains " + composition.getNumberOfStates() + " and " + composition.getNumberOfTransitions() + " transitions");
	  
	  if ((composition.getNumberOfStates()<100) && (composition.getNumberOfTransitions()<610))
	  	System.out.println("Printing composition:\n"+composition.toString());

	  System.out.println("---------------------------------------------------------------");
		System.out.println("-------------------- T E S T  E N D S -------------------------");
		
	}

}
