package test;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import algorithm.AggregationFunction;
import algorithm.DynamicAlgorithm;
import algorithm.AggregationFunction.AggregateType;
import algorithm.SelectionFunction;
import algorithm.SelectionFunction.SelectionType;
import automaton.Automaton;
import automaton.TTester;
import automaton.Variable;
import distance.DistanceMetrics;
import distance.ManhattanDistance;
import distance.MaxDistance;
import distance.MinDistance;
import distance.QualitativeDistance;
import frontend.AutomataCompositionBuilder;
import frontend.AutomataGenListener;
import gen.StlLexer;
import gen.StlParser;
import policy.QualitativePolicy;
import policy.QuantitativePolicy;
import policy.SatisfactionPolicy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BaseTest {

	/**
	 * Configuration for the algorithm.
	 */
	protected static TestConfig config;
	
	/***
	 * The size of the input alphabet.
	 */
	static int alphabetSize;

	/**
	 * TODO - this is not a good way to read long traces.!
	 */
	static String trace;

	/***
	 * Just a default value for alphabet size.
	 */
	protected static final int defaultAlphaSize = 10;

	/**
	 * Names of the signals which are parsed from a trace.
	 */
	protected static String[] signalNames;

	/**
	 * List of int vectors.
	 */
	protected static ArrayList<int[]> traceArr;


	/**
	 * Several stages of formula preprocessing.
	 * @param tree
	 * @return
	 */
	protected static String preprocessingPhase(ParseTree tree) {
		ParseTreeWalker walker = new ParseTreeWalker();
		System.out.println("\n------------------- PRE-PROCESSING PHASE --------------------\n");
		STLPreProcessingListener ppSinceListener = new STLPreProcessingListener();
		walker.walk(ppSinceListener, tree);			
		System.out.println("\nFormula after preprocessing:\n\n"+ppSinceListener.getOutputFormula()+"\n");
		
		
		//this preprocessing phase handles     p Until[0:B] q 
		System.out.println("\n------------------- PRE-PROCESSING PHASE 2 --------------------\n");		
		tree = parseSTLFormulaFromString(ppSinceListener.getOutputFormula());
		ParseTreeWalker walker2 = new ParseTreeWalker();
		
		STLPreProcessingListener ppSinceListener2 = new STLPreProcessingListener();
		walker2.walk(ppSinceListener2, tree);			
		System.out.println("\nFormula after preprocessing2:\n"+ppSinceListener2.getOutputFormula()+"\n");		
		
		//------ this will further distribute oracle operator
		System.out.println("\n------------------- PRE-PROCESSING PHASE 3 --------------------\n");
		
		tree = parseSTLFormulaFromString(ppSinceListener2.getOutputFormula());
		ParseTreeWalker walker3 = new ParseTreeWalker();		
		STLPreProcessingListener ppSinceListener3 = new STLPreProcessingListener();
		walker3.walk(ppSinceListener3, tree);
		System.out.println("\nFormula after preprocessing3:\n"+ppSinceListener3.getOutputFormula()+"\n");

		return ppSinceListener3.getOutputFormula();
	}
	
	
	/**
	 * Parse an STL formula read from a file and return Parse Tree.
	 * 
	 * @param string
	 * @return
	 */
	protected static ParseTree parseSTLFormulaFromFile(String string) {
		StlLexer lexer = null;
		CommonTokenStream tokens = null;
		StlParser parser;
		ParseTree tree = null;

		try {
			lexer = new StlLexer(CharStreams.fromFileName(string));
			tokens = new CommonTokenStream(lexer);

			parser = new StlParser(tokens);
			tree = parser.stlfile();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("parseSTLFormulaFromFile:: parsing the file:\n" + lexer.getInputStream().toString());

		return tree;
	}
	

	/**
	 * Parse an STL formula which is supplied as a String.
	 * 
	 * @param string
	 * @return
	 */
	protected static ParseTree parseSTLFormulaFromString(String string) {
		StlLexer lexer = null;
		CommonTokenStream tokens = null;
		StlParser parser;
		ParseTree tree = null;

		lexer = new StlLexer(CharStreams.fromString(string));
		tokens = new CommonTokenStream(lexer);

		parser = new StlParser(tokens);
		tree = parser.stlfile();

		System.out.println("parseSTLFormulaFromString:: parsing the string:\n" + lexer.getInputStream().toString());

		return tree;
	}

	

	/**
	 * Create a config object based on the file.
	 */
	public static void loadXMLconfig() {
		try {
			File fXmlFile = new File("XMLcfg\\config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList0 = doc.getElementsByTagName("distanceMetrics");
			NodeList nList1 = doc.getElementsByTagName("satisfactionPolicy");
			NodeList nList2 = doc.getElementsByTagName("aggregationFunction");
			NodeList nList3 = doc.getElementsByTagName("selectionFunction");
			NodeList nList4 = doc.getElementsByTagName("varTotalRange");


			if (nList0.getLength() > 1)
				System.err.println("ERROR: loadXMLconfig:: XML : more than one distance metrics specified");
			if (nList1.getLength() > 1)
				System.err.println("ERROR: loadXMLconfig:: XML : more than one satisfactionPolicy specified");
			if (nList2.getLength() > 1)
				System.err.println("ERROR: loadXMLconfig:: XML : more than one aggregationFunction specified");
			if (nList3.getLength() > 1)
				System.err.println("ERROR: loadXMLconfig:: XML : more than one selectionFunction specified");
			if (nList4.getLength() > 1)
				System.err.println("ERROR: loadXMLconfig:: XML : more than one varTotalRange specified");


			config = new TestConfig();
			config.distanceMetrics = getDistanceM(nList0.item(0).getTextContent().trim());
			config.satisfactionPolicy = getSatisfactionP(nList1.item(0).getTextContent().trim());
			config.aggregationFunction = getAggregationF(nList2.item(0).getTextContent().trim());
			config.selectionFunction = getSelectionF(nList3.item(0).getTextContent().trim());
			config.varTotalRange = Integer.parseInt((nList4.item(0).getTextContent()).trim());


			System.out.println("\nloadXMLconfig:: TestConfiguration successfully loaded from XML file");
			System.out.println("Loaded Distance Metric      " + config.distanceMetrics.toString());
			System.out.println("Loaded Satisfaction Policy  " + config.satisfactionPolicy.toString());
			System.out.println("Loaded Aggregation Function " + config.aggregationFunction.toString());
			System.out.println("Loaded Selection Function   " + config.selectionFunction.toString());
			System.out.println("Loaded Variable Total Range " + config.varTotalRange);
			System.out.println("-------------------------------------------------------\n");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param textContent
	 * @return
	 */
	private static DistanceMetrics getDistanceM(String textContent) {

		if (textContent.equalsIgnoreCase("Manhattan"))
			return new ManhattanDistance();

		if (textContent.equalsIgnoreCase("Max"))
			return new MaxDistance();

		if (textContent.equalsIgnoreCase("Min"))
			return new MinDistance();

		if (textContent.equalsIgnoreCase("Qualitative"))
			return new QualitativeDistance();
		return null;
	}

	/**
	 * 
	 * @param textContent
	 * @return
	 */
	private static SatisfactionPolicy getSatisfactionP(String textContent) {

		if (textContent.equalsIgnoreCase("Quantitative"))
			return new QuantitativePolicy();

		if (textContent.equalsIgnoreCase("Qualitative"))
			return new QualitativePolicy();

		return null;
	}

	/**
	 * 
	 * @param textContent
	 * @return
	 */
	private static AggregationFunction getAggregationF(String textContent) {

		if (textContent.equalsIgnoreCase("Max"))
			return new AggregationFunction(AggregateType.MAX);

		if (textContent.equalsIgnoreCase("Min"))
			return new AggregationFunction(AggregateType.MIN);

		if (textContent.equalsIgnoreCase("Sum"))
			return new AggregationFunction(AggregateType.SUM);

		return null;
	}

	/**
	 * 
	 * @param textContent
	 * @return
	 */
	private static SelectionFunction getSelectionF(String textContent) {

		if (textContent.equalsIgnoreCase("Max"))
			return new SelectionFunction(SelectionType.MAX);

		if (textContent.equalsIgnoreCase("Min"))
			return new SelectionFunction(SelectionType.MIN);

		return null;
	}

	/**
	 * check if everything is OK in XML file.
	 * 
	 * @return
	 */
	protected boolean validateXMLfile() {
		return false;
	}
	
	/**
	 * A function which initiates satisfaction policy to remap the variable index pairs.
	 * @param variables
	 */
	protected static void updateCfgWithVars(HashMap<String, Variable> variables) {
		config.satisfactionPolicy.setVars(variables);
	}

	/**
	 * Print info about the automaton.
	 * @param composition
	 */
	protected static void printCompositionInfo(TTester composition) {
		
		System.out.println("\nThe composition contains " + composition.getNumberOfStates() + " and "
				+ composition.getNumberOfTransitions() + " transitions");
		if ((composition.getNumberOfStates() < 100) && (composition.getNumberOfTransitions() < 610))
			System.out.println("Printing composition:\n" + composition.toString());
		
	}
	

	/**
	 * Main.
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {		
		BaseTest myTest = new BaseTest();
		loadXMLconfig();
	}
	
	/**
	 * Reads a trace from a file. Currently it reads the entire trace and packs it
	 * into a String.
	 */
	@SuppressWarnings("unused")
	private static String read_trace() {

		//_DEPRECATED_
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		try {
			br = new BufferedReader(new FileReader("src\\parser\\trace"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	/**
	 * Reads a trace from a CSV file.
	 * 
	 */
	protected static void readCSV_trace(String path, String csvSplitBy) {

		traceArr = new ArrayList<int[]>();
		BufferedReader br = null;
		//String csvSplitBy = ",";
		String values[];
		int valInt[];

		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String line = br.readLine(); //read init line

			if (line != null){ // read the mapping			
				signalNames = line.split(csvSplitBy);
				valInt = new int[signalNames.length];
			} else {
				System.out.println("empty CSV trace");
				return;
			}

			line = br.readLine();
			while (line != null) {
				values = line.split(csvSplitBy);
				
				valInt = new int[signalNames.length];
				for (int i = 0; i < values.length; i++) {
					if ((values[i].indexOf(',') != -1) || (values[i].indexOf('.') != -1)){//trunc the mantissa
						
						int ix = values[i].indexOf(',') > values[i].indexOf('.') ? values[i].indexOf(',') : values[i].indexOf('.');
						
						values[i]= values[i].substring(0, ix);					
					}
					
					valInt[i] = Integer.parseInt(values[i]);
				}
				traceArr.add(valInt);
				line = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("CSV trace succesfully loaded from " + path);
	}

	/**
	 * Runs a distance algorithm for a given automaton and trace. 
	 */
	@SuppressWarnings("unused")
	private static void run_distance(Automaton myAutomaton) {
		//_DEPRECATED
		DynamicAlgorithm iter = new DynamicAlgorithm(myAutomaton, config);

		// /-------------------------------------------------------//

		System.out.println("Selected Distance Metric      " + config.distanceMetrics.toString());
		System.out.println("Selected Satisfaction Policy  " + config.satisfactionPolicy.toString());
		System.out.println("Selected Aggregation Function " + config.aggregationFunction.toString());
		System.out.println("Selected Selection Function   " + config.selectionFunction.toString());
		System.out.println("-------------------------------------------------------");

		// create sequence
		ArrayList<Variable> inputValues = new ArrayList<Variable>();
		Variable myVarX = iter.getVarSnapshot().get("X"); // this is the right way
																											// to go
																											// - DO NOT create new var
																											// new Variable("X", 0,
																											// 5);

		inputValues.add(myVarX);

		String[] tokens = trace.split("\\s+");

		System.out.println("Initial values of distance to each node: ");
		System.out.println(iter.distances);
		System.out.println("Applying the input values");

		// iteratively apply input values
		for (int i = 0; i < tokens.length; i++) {
			myVarX.setValue(Integer.parseInt(tokens[i]));
			iter.applyInput(inputValues);
		}

		System.out.println("//---------------------------------------------//");
		System.out.println("INDUCED COST OF A TRACE TO REACH EACH OF THE STATES");
		System.out.println(iter.distances);
	}

	/**
	 * Runs a dynamic programming algorithm. Trace is read from CSV file.
	 * 
	 */
	protected static void runDistanceCSV(Automaton myAutomaton) {

		DynamicAlgorithm iter = new DynamicAlgorithm(myAutomaton, config);

		// /-------------------------------------------------------//

		System.out.println("Selected Distance Metric      " + config.distanceMetrics.toString());
		System.out.println("Selected Satisfaction Policy  " + config.satisfactionPolicy.toString());
		System.out.println("Selected Aggregation Function " + config.aggregationFunction.toString());
		System.out.println("Selected Selection Function   " + config.selectionFunction.toString());
		System.out.println("-------------------------------------------------------");

		// create sequence
		ArrayList<Variable> inputValues = new ArrayList<Variable> (iter.getVarSnapshot().values());
		HashMap<Variable, Integer> var2index = new HashMap<Variable, Integer>();

		for (int i = 0; i < signalNames.length; i++) { // do the mapping
			for (Variable variable : inputValues) {
				if (variable.getName().equals(signalNames[i])) {
					var2index.put(variable, i);
				}
			}
		}			

		//no such variable HERE WE HAVE TO PERFORM A CHECK
		if (var2index.values().size() == 0) {
			System.err.println("BaseTest::runDistanceCSV::There is no mutual variable between the trace and the automaton");
			return;
		}		
			
			
		System.out.println("Initial values of distance to each node: ");
		System.out.println(iter.distances);
		System.out.println("Applying the input values");

		int[] vector;
		// iteratively apply input values
		for (int i = 0; i < traceArr.size(); i++) {
			vector = traceArr.get(i);

			for (int j = 0; j < inputValues.size() ; j++) {
				inputValues.get(j).setValue(vector[var2index.get(inputValues.get(j))]);
			}
			iter.applyInput(inputValues);
		}

		System.out.println("//---------------------------------------------//");
		System.out.println("INDUCED COST OF A TRACE TO REACH EACH OF THE STATES");
		System.out.println(iter.distances);
	}

	/**
	 * Create composition from automataMap provided by the automata generation
	 * listener.
	 * 
	 * @param aListener
	 * @return
	 */
	protected static TTester composeTT(AutomataGenListener aListener) {
		AutomataCompositionBuilder acl = new AutomataCompositionBuilder(aListener.automataMap, aListener.topKey);
		return acl.composeProduct();
	}

	/***
	 * Applies the values from the preloaded configuration.
	 */
	protected static void applyConfig(){
		Variable.setTotalMaxValue(config.varTotalRange);
		alphabetSize = config.varTotalRange;	 //TODO - this could be removed?	
	};

}
