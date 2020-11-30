package algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import policy.SatisfactionPolicy;
import automaton.Automaton;
import automaton.Variable;


//Iterator Design Pattern
/**
 * 
 * This class shall be used as base iterator class whenever it is necessary to
 * iterate through the automaton.
 * 
 * This class contains a reference to the iterated automaton. It
 * specifies the method to iterate through Automaton by applying 
 * certain input sequence.
 * 
 * @author JaksicS
 *
 */
public abstract class BaseAutomatonIterator {

	/***
	 * Snapshot of automata traversal. This set includes both input variables and
	 * clock variables.
	 */
	protected HashMap<String, Variable> varSnapshot;

	/**
	 * Automaton which is iterated.
	 */
	protected Automaton automaton;

	/**
	 * Policy which specifies distance cost function for every input symbol set
	 * and constraint pair.
	 */
	protected SatisfactionPolicy policy;

	/**
	 * Applies input values to the automata which causes automaton current state
	 * and variables.
	 * 
	 * @param inputValues
	 *          : the input values to be applied
	 * @return : by default , it should return 1 if sequence is accepted
	 */
	public abstract int applyInput(ArrayList<Variable> inputValues);
	
	/***
	 * Runs a sequence of input vectors on an automaton.
	 * @param inputValues
	 * @return - the result depends on the distance metric and semantics
	 */
	public abstract int runSequence(ArrayList<Variable> inputValues[]);

	/**
	 * Constructor. Sets the automaton traversed, and sets current state as one of
	 * the initial states.
	 * 
	 * @param auto
	 *          - the Automaton object which will be iterated.
	 */
	public BaseAutomatonIterator(Automaton auto, SatisfactionPolicy policy) {
		this.automaton = auto;

		if (automaton.getVariables().size() > 0)
			this.varSnapshot = new HashMap<String, Variable>();
		else 
			System.err.println("BaseAutomatonIterator :-> empty variable list in automaton!");

		
		this.varSnapshot.putAll(auto.getVariables());
//		for (Variable var : auto.variables) {
//			this.varSnapshot.put(var.getName(), var);
//		}
		
		this.policy = policy;
	}

	/**
	 * Prints Automata iteration snapshot.
	 */
	public String toString() {
		return "snapshot: " + varSnapshot.toString();
	}

	public SatisfactionPolicy getPolicy() {
		return policy;
	}

	/***
	 * Set desirable Weighing policy: Qualitative, Hamming,...
	 * @param policy
	 */
	public void setPolicy(SatisfactionPolicy policy) {
		this.policy = policy;
	}

	/***
	 * Getter for VarSnapshot
	 * @return
	 */
	public HashMap<String, Variable> getVarSnapshot() {
		return varSnapshot;
	}

}
