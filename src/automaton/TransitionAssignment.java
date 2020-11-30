package automaton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class TransitionAssignment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1009359995630066612L;

	/***
	 * List of the variables which this assignment will reset to 0
	 */
	private ArrayList<Variable> varsToReset;

	/***
	 * List of the variables which this assignment will increment
	 */
	private ArrayList<Variable> varsToInc;

	/***
	 * Constructs objects of the class. creates empty assignment
	 */
	public TransitionAssignment() {
		varsToReset = new ArrayList<Variable>();
		varsToInc = new ArrayList<Variable>();
	}

	/***
	 * Performs the assignment.
	 */
	public void assign(HashMap<String, Variable> variableMap) {

		for (Variable var : varsToReset) {
			variableMap.get(var.getName()).setValue(0);
		}

		for (Variable var : varsToInc) {
			variableMap.get(var.getName()).setValue(variableMap.get(var.getName()).getValue()+1);
		}
	}

	/***
	 * Adds a var that must be reset.
	 */
	public void addVarToReset(Variable var) {
		varsToReset.add(var);
	}

	/***
	 * Adds a var that must be incremented.
	 */
	public void addVarToInc(Variable var) {
		varsToInc.add(var);
	}

	/***
	 * Adds vars that must be incremented.
	 */
	public void addVarsToInc(Variable[] vars) {
		for (Variable var : vars) {
			varsToInc.add(var);
		}
	}

	/***
	 * Adds vars that must be reset.
	 */
	public void addVarsToReset(Variable[] vars) {
		for (Variable var : vars) {
			varsToReset.add(var);
		}
	}

	/***
	 * Prints the transition assignment
	 */
	public String toString() {
		StringBuffer auxBuf = new StringBuffer();

		for (Variable variable : varsToInc) {
			auxBuf.append(variable.getName() + "++;");
		}

		for (Variable variable : varsToReset) {
			auxBuf.append(variable.getName() + ":=0;");
		}

		return auxBuf.toString();
	}

	/**
	 * Determines if this assignment is equal to another assignment.
	 * 
	 */
	public boolean equals(TransitionAssignment assignment) {
		boolean toReturn = true;

		if (varsToInc.size() != assignment.varsToInc.size())
			toReturn = false;

		if (varsToReset.size() != assignment.varsToReset.size())
			toReturn = false;

		for (Variable rstVar : varsToReset) {
			if (!assignment.varsToReset.contains(rstVar))
				toReturn = false;
		}

		for (Variable incVar : varsToInc) {
			if (!assignment.varsToInc.contains(incVar))
				toReturn = false;
		}

		return toReturn;
	}

}
