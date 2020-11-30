//package test;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import policy.QualitativePolicy;
//import policy.QuantitativePolicy;
//import policy.SatisfactionPolicy;
//import algorithm.AggregationFunction;
//import algorithm.DynamicAlgorithm;
//import algorithm.AggregationFunction.AggregateType;
//import algorithm.SelectionFunction.SelectionType;
//import algorithm.SelectionFunction;
//import automaton.Automaton;
//import automaton.AutomatonState;
//import automaton.AutomatonTransition;
//import automaton.TransitionAssignment;
//import automaton.Variable;
//import constraint.Constraint;
//import constraint.ConstraintNode;
//import constraint.ConstraintNode.NodeType;
//import distance.DistanceMetrics;
//import distance.ManhattanDistance;
//import distance.QualitativeDistance;
//
///**
// * 
// * @author JaksicS
// *
// */
//public class TestDynamicAlgorithm {
//	public static void main(String[] args) throws Exception {
//
//		// create automaton
//		Automaton myAutomaton = new Automaton(false);
//
//		// add a variable
//		Variable varX = new Variable("X", 0, 5, true);
//		myAutomaton.addVar(varX);
//
//		// add states
//		AutomatonState[] stateArr = new AutomatonState[3];
//		for (int i = 0; i < 3; i++) {
//			if (i == 0) // init state
//				stateArr[i] = new AutomatonState(true, true);
//			else
//				stateArr[i] = new AutomatonState(false, true);
//		}
//
//		for (int i = 0; i < 3; i++) {
//				myAutomaton.addState(stateArr[i]);
//		}
//
//		// add global error state
//		// by default it is the last state
//		// NO ERROR STATE
//		// stateArr[3] = new AutomatonState();
//		// myAutomaton.addState(stateArr[3], false, false);
//
//		// create assignments
//		TransitionAssignment assgn1 = new TransitionAssignment();
//
//		// --------------------------------------------------------//
//		// CONSTRAINTS
//		ArrayList<ConstraintNode> cstNodeList1 = new ArrayList<ConstraintNode>();
//		cstNodeList1.add(new ConstraintNode(NodeType.LEAF_ID, "X"));
//		cstNodeList1.add(new ConstraintNode(NodeType.LEAF_NUM, "3"));
//		cstNodeList1.add(new ConstraintNode(NodeType.CMP_LESS, "<"));
//
//		ArrayList<ConstraintNode> cstNodeList2 = new ArrayList<ConstraintNode>();
//		cstNodeList2.add(new ConstraintNode(NodeType.LEAF_ID, "X"));
//		cstNodeList2.add(new ConstraintNode(NodeType.LEAF_NUM, "3"));
//		cstNodeList2.add(new ConstraintNode(NodeType.CMP_EQ, "=="));
//		cstNodeList2.add(new ConstraintNode(NodeType.LEAF_ID, "X"));
//		cstNodeList2.add(new ConstraintNode(NodeType.LEAF_NUM, "5"));
//		cstNodeList2.add(new ConstraintNode(NodeType.CMP_EQ, "=="));
//		cstNodeList2.add(new ConstraintNode(NodeType.LOG_OR, "or"));
//
//		ArrayList<ConstraintNode> cstNodeList3 = new ArrayList<ConstraintNode>();
//		cstNodeList3.add(new ConstraintNode(NodeType.LEAF_ID, "X"));
//		cstNodeList3.add(new ConstraintNode(NodeType.LEAF_NUM, "5"));
//		cstNodeList3.add(new ConstraintNode(NodeType.CMP_LEQ, "<="));
//
//		ArrayList<ConstraintNode> cstNodeList4 = cstNodeList1;
//		ArrayList<ConstraintNode> cstNodeList5 = cstNodeList2;
//
//		Constraint cst1 = new Constraint(cstNodeList1);
//		Constraint cst2 = new Constraint(cstNodeList2);
//		Constraint cst3 = new Constraint(cstNodeList3);
//		Constraint cst4 = new Constraint(cstNodeList4);
//		Constraint cst5 = new Constraint(cstNodeList5);
//		// --------------------------------------------------------//
//
//		AutomatonTransition t0 = new AutomatonTransition(stateArr[0], stateArr[1], assgn1, cst1);
//		AutomatonTransition t1 = new AutomatonTransition(stateArr[0], stateArr[2], assgn1, cst2);
//		AutomatonTransition t2 = new AutomatonTransition(stateArr[1], stateArr[1], assgn1, cst3);
//		AutomatonTransition t3 = new AutomatonTransition(stateArr[2], stateArr[1], assgn1, cst4);
//		AutomatonTransition t4 = new AutomatonTransition(stateArr[2], stateArr[2], assgn1, cst5);
//
//		myAutomaton.addTransition(t0);
//		myAutomaton.addTransition(t1);
//		myAutomaton.addTransition(t2);
//		myAutomaton.addTransition(t3);
//		myAutomaton.addTransition(t4);
//
//		System.out.println("AUTOMATON: " + myAutomaton.hashCode() + "\n" + myAutomaton.toString());		
//		
//		
//		
////QUANTITATIVE
////		ManhattanDistance manhattan 		= new ManhattanDistance();
////		QuantitativePolicy quantitative = QuantitativePolicy.getInstance(myAutomaton.getVariables());
////		AggregationFunction sum = new AggregationFunction(AggregateType.SUM);
////		SelectionFunction min 	= new SelectionFunction(SelectionType.MIN);
////		DynamicAlgorithm iter 	= new DynamicAlgorithm(myAutomaton, manhattan, quantitative, sum, min);
//	
//		/*
//		 * QUALITATIVE CASE
//		 * 		// --------------------------------------------------------//
//		DistanceMetrics  dist = new QualitativeDistance();
//		SatisfactionPolicy pol = QualitativePolicy.getInstance(myAutomaton.getVariables());
//		AggregationFunction aggr = new AggregationFunction(AggregateType.MAX);
//		SelectionFunction min = new SelectionFunction(SelectionType.MIN);
//		DynamicAlgorithm iter = new DynamicAlgorithm(myAutomaton, dist, pol, aggr, min);
//
//		// /-------------------------------------------------------//
//
//		 */
//		// --------------------------------------------------------//
//		DistanceMetrics  dist = new QualitativeDistance();
//		SatisfactionPolicy pol = QualitativePolicy.getInstance(myAutomaton.getVariables());
//		AggregationFunction aggr = new AggregationFunction(AggregateType.MAX);
//		SelectionFunction min = new SelectionFunction(SelectionType.MIN);
//		DynamicAlgorithm iter = new DynamicAlgorithm(myAutomaton, dist, pol, aggr, min);
//
//		// /-------------------------------------------------------//
//		// create sequence
//		
//		String trace = read_trace();		
//		ArrayList<Variable> inputValues = new ArrayList<Variable>();
//		Variable myVarX = iter.getVarSnapshot().get("X"); // this is the right way
//																											// to go
//																											// - DO NOT create new var
//																											// new Variable("X", 0,
//																											// 5);		
//		inputValues.add(myVarX);		
//		String[] tokens = trace.split("\\s+");
//		
//		System.out.println("Initial values of distance to each node: ");
//		System.out.println(iter.distances);
//		System.out.println("Applying the input values");
//		
//		for (int i = 0; i < tokens.length; i++) {
//			myVarX.setValue(Integer.parseInt(tokens[i]));
//			iter.applyInput(inputValues);
//		}
//
//		System.out.println("//---------------------------------------------//");
//		System.out.println("INDUCED COST OF A TRACE TO REACH EACH OF THE STATES");
//		System.out.println(iter.distances);
//	}
//	
//	
//	/**
//	 * 
//	 * @return
//	 */
//	private static String read_trace() {
//
//		BufferedReader br = null;
//		StringBuilder sb = new StringBuilder();
//
//		try {
//			br = new BufferedReader(new FileReader("src\\parser\\trace"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			String line = br.readLine();
//
//			while (line != null) {
//				sb.append(line);
//				sb.append(System.lineSeparator());
//				line = br.readLine();
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				br.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}		
//		return sb.toString();
//	}
//	
//}
