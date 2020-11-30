package test;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import gen.StlLexer;
import gen.StlParser;

/**
 * This class will be used to test STL preprocessing.
 * This phase will apply the rewriting rules in 
 * order to transform the formula to a convenient
 * form.
 * @author JaksicS
 *
 */
public class TestSTLPreprocessListener {

	//it takes an STL formula and returns another STL formula
	
	//parse a formula and preprocess it.
	
	public static void main(String[] args) throws Exception{
		
		StlLexer lexer = null;
		CommonTokenStream tokens;
		StlParser parser;
		ParseTree tree = null;

		lexer = new StlLexer(CharStreams.fromFileName("C:\\repo_symaut\\sym_automata\\formula\\formula"));
		tokens = new CommonTokenStream(lexer);

		parser = new StlParser(tokens);
		tree = parser.stlfile();
		ParseTreeWalker walker = new ParseTreeWalker();
		
		System.out.println("\n\t\t-------------------------------------------------------------------\n");
		System.out.println("Generating monitor code for formula: "+tokens.getText());

		
		//--------------------------------------------------------------------------//
		//-------------------   PRE-PROCESSING PHASE  ------------------------------//
		System.out.println("\n\t\t--------------------- PRE-PROCESSING PHASE ----------------------\n");
		STLPreProcessingListener ppSinceListener = new STLPreProcessingListener();
		walker.walk(ppSinceListener, tree);
		
		String new_formula = ppSinceListener.getOutputFormula();//ctx2strMap.get(ppSinceListener.top_string);
		System.out.println(new_formula);

		
	}
	
}
