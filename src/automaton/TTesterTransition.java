package automaton;

import java.util.ArrayList;

import constraint.Constraint;

/**
 * Temporal Tester Transition does everything as ordinary transition, except
 * that it has certain set of (possibly negated) output variables. The output
 * variables in the set must correspond to the output variables of the parent
 * automaton.
 * 
 * @author JaksicS
 *
 */
public class TTesterTransition extends AutomatonTransition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * List of output variables
	 */
	public ArrayList<VariableInstance> outVars;
	
	/**
	 * Used as an in order to remember prediction for this transition.
	 * This field will be used to identify redundant states in 
	 * next^n temporal tester.
	 */
	public int label;
	

	/**
	 * Constructor
	 * 
	 * @param srcState
	 * @param dstState
	 * @param assignment
	 * @param guard
	 * @param outVars
	 */
	public TTesterTransition(AutomatonState srcState, AutomatonState dstState, TransitionAssignment assignment,
			Constraint guard, ArrayList<VariableInstance> outVars) {
		super(srcState, dstState, assignment, guard);
		this.outVars = outVars;

	}

	public String toString() {
		String excl = "";
		String addSpace = "";

		if (this.outVars.get(0).isNotInverted == false)
			excl = "!";
		else
			addSpace = " ";

		return super.toString() + " " + addSpace + excl + this.outVars.get(0).var.getName();

	}

	public String toStringNoID() {
		String excl = "";
		String addSpace = "";

		if (this.outVars.get(0).isNotInverted == false)
			excl = "!";
		else
			addSpace = " ";

		return super.toStringNoID() + " " + addSpace + excl + this.outVars.get(0).var.getName();

	}

}
