package test;

import algorithm.*;
import java.util.ArrayList;

import policy.QualitativePolicy;
import automaton.*;
import constraint.Constraint;
import constraint.ConstraintNode;
import constraint.ConstraintNode.NodeType;

/**
 * 
 * Runs a simple sequence on acceptor automaton.
 * 
 * @author JaksicS
 *
 */
public class TestAutomatonEnv {

	public static void main(String[] args) throws Exception {

		// create automaton
		Automaton myAutomaton = new Automaton(true); /// has an implicit error state
																									/// = TRUE

		// add a variable
		Variable varX = new Variable("X", 0, 5, true);
		myAutomaton.addVar(varX);

		// add states
		AutomatonState[] stateArr = new AutomatonState[4];

		for (int i = 0; i < 3; i++) {
			if (i == 0) // init state
				stateArr[i] = new AutomatonState(true, true);
			else
				stateArr[i] = new AutomatonState(false, true);
		}

		for (int i = 0; i < 3; i++) {
			myAutomaton.addState(stateArr[i]);
		}

		// add global error state
		// by default it is the last state
		// stateArr[3] = new AutomatonState();
		// myAutomaton.addState(stateArr[3], false, false);

		// create assignments
		TransitionAssignment assgn1 = new TransitionAssignment();

		// --------------------------------------------------------//
		// CONSTRAINTS
		ArrayList<ConstraintNode> cstNodeList1 = new ArrayList<ConstraintNode>();
		cstNodeList1.add(new ConstraintNode(NodeType.LEAF_ID, "X"));
		cstNodeList1.add(new ConstraintNode(NodeType.LEAF_NUM, "3"));
		cstNodeList1.add(new ConstraintNode(NodeType.CMP_LESS, "<"));

		ArrayList<ConstraintNode> cstNodeList2 = new ArrayList<ConstraintNode>();
		cstNodeList2.add(new ConstraintNode(NodeType.LEAF_ID, "X"));
		cstNodeList2.add(new ConstraintNode(NodeType.LEAF_NUM, "3"));
		cstNodeList2.add(new ConstraintNode(NodeType.CMP_EQ, "=="));
		cstNodeList2.add(new ConstraintNode(NodeType.LEAF_ID, "X"));
		cstNodeList2.add(new ConstraintNode(NodeType.LEAF_NUM, "5"));
		cstNodeList2.add(new ConstraintNode(NodeType.CMP_EQ, "=="));
		cstNodeList2.add(new ConstraintNode(NodeType.LOG_OR, "or"));

		ArrayList<ConstraintNode> cstNodeList3 = new ArrayList<ConstraintNode>();
		cstNodeList3.add(new ConstraintNode(NodeType.LEAF_ID, "X"));
		cstNodeList3.add(new ConstraintNode(NodeType.LEAF_NUM, "5"));
		cstNodeList3.add(new ConstraintNode(NodeType.CMP_LEQ, "<="));

		ArrayList<ConstraintNode> cstNodeList4 = cstNodeList1;
		ArrayList<ConstraintNode> cstNodeList5 = cstNodeList2;

		Constraint cst1 = new Constraint(cstNodeList1);
		Constraint cst2 = new Constraint(cstNodeList2);
		Constraint cst3 = new Constraint(cstNodeList3);
		Constraint cst4 = new Constraint(cstNodeList4);
		Constraint cst5 = new Constraint(cstNodeList5);
		// --------------------------------------------------------//

		AutomatonTransition t0 = new AutomatonTransition(stateArr[0], stateArr[1], assgn1, cst1);
		AutomatonTransition t1 = new AutomatonTransition(stateArr[0], stateArr[2], assgn1, cst2);
		AutomatonTransition t2 = new AutomatonTransition(stateArr[1], stateArr[1], assgn1, cst3);
		AutomatonTransition t3 = new AutomatonTransition(stateArr[2], stateArr[1], assgn1, cst4);
		AutomatonTransition t4 = new AutomatonTransition(stateArr[2], stateArr[2], assgn1, cst5);

		myAutomaton.addTransition(t0);
		myAutomaton.addTransition(t1);
		myAutomaton.addTransition(t2);
		myAutomaton.addTransition(t3);
		myAutomaton.addTransition(t4);

		System.out.println("AUTOMATON: " + myAutomaton.hashCode() + "\n" + myAutomaton.toString());

		// --------------------------------------------------------//
		// get iterator - ACCEPTOR
		QualitativePolicy qp = new QualitativePolicy();
		AcceptorIterator iter = new AcceptorIterator(myAutomaton, qp);

		// /-------------------------------------------------------//
		// create sequence
		ArrayList<Variable> inputValues = new ArrayList<Variable>();
		Variable myVarX = new Variable("X", 0, 5, true);
		myVarX.setValue(5);

		inputValues.add(myVarX);

		iter.applyInput(inputValues);

		System.out.println("");
		myVarX.setValue(3);
		iter.applyInput(inputValues);

		System.out.println("");
		myVarX.setValue(3);
		iter.applyInput(inputValues);

		System.out.println("");
		myVarX.setValue(5);
		iter.applyInput(inputValues);

		System.out.println("");
		myVarX.setValue(3);
		iter.applyInput(inputValues);

		System.out.println("");
		myVarX.setValue(2);
		iter.applyInput(inputValues);

		if (myAutomaton.getAcceptingStates().contains(myAutomaton.getStates().get(iter.getCurrentState().toString()))) {
			// ------------ acceptance notification --------------
			System.out.println("//---------------------------------------------//");
			System.out.println("[OK] SEQUENCE IS ACCEPTED BY THE AUTOMATON [OK]");
			System.out.println("//---------------------------------------------//");
		} else {
			System.err.println("//---------------------------------------------//");
			System.err.println("!!!SEQUENCE IS  N O T  ACCEPTED BY THE AUTOMATON!!!");
			System.err.println("//---------------------------------------------//");
		}
	}
}
