package automaton;

import java.io.Serializable;

import constraint.Constraint;

/**
 * Class which represents automaton transition.
 * 
 * @author JaksicS
 *
 */
public class AutomatonTransition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7482120167444058012L;

	/***
	 * ID seed.
	 */
	public static int transitionID = 0;

	/***
	 * Transition id number.
	 */
	private int id;

	/***
	 * Source state for a Transition
	 */
	private AutomatonState srcState;

	/***
	 * Destination state for a Transition
	 */
	private AutomatonState dstState;

	/***
	 * Variables assigned in this transition. This object allows assignment of
	 * variables: incrementing or reseting to zero.
	 */
	private TransitionAssignment assignment;

	/***
	 * TODO: make sure no null object here
	 */
	private Constraint cstGuard;

	/***
	 * getter method for the guard.
	 * 
	 * @return
	 */
	public Constraint getCstGuard() {
		return cstGuard;
	}

	/****
	 * Constructs the objects of this class.
	 * 
	 * @param srcState
	 * @param dstState
	 * @param assignment
	 * @param guard
	 */
	public AutomatonTransition(AutomatonState srcState, AutomatonState dstState, TransitionAssignment assignment,
			Constraint guard) {
		id = transitionID++;
		this.srcState = srcState;
		this.dstState = dstState;
		this.assignment = assignment;
		this.cstGuard = guard;
	}

	/**
	 * Determines if this transition is equal to another transition.
	 * 
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof AutomatonTransition) {

			AutomatonTransition objTrans = (AutomatonTransition) obj;

			if (!srcState.equals(objTrans.srcState)) {
				return false;
			}

			if (!dstState.equals(objTrans.dstState)) {
				return false;
			}

			if (!assignment.equals(objTrans.assignment)) {
				return false;
			}

			if (!cstGuard.equals(objTrans.cstGuard)) {
				return false;
			}
			
			return true;
		}
		else 
			return false;
	}

	/***
	 * Transforms transition with symbolic constraint which allows only a subset
	 * of inputs into a weighted transition where all the inputs are allowed with
	 * a specific cost.
	 */
	public void toWeightedTransition() {
		// TODO Auto-generated method stub

	}

	/**
	 * Getter for destination state.
	 */
	public AutomatonState getDstState() {
		return dstState;
	}

	/**
	 * Setter for destination state.
	 * 
	 * @param dstState
	 */
	public void setDstState(AutomatonState dstState) {
		this.dstState = dstState;
	}

	/**
	 * Converts object to String.
	 */
	public String toString() {
		StringBuffer retStr = new StringBuffer();
		String addSpace = "";

		if (id < 10)
			addSpace = " ";

		retStr.append("T" + id + ": " + addSpace + "[");
		retStr.append(" (" + srcState + ",");
		retStr.append(dstState + "), ");

		if (assignment.toString().length() > 0)
			retStr.append("assign [" + assignment + "]; ");

		retStr.append("{" + cstGuard.toStringInline() + "}]");
		return retStr.toString();
	}

	/***
	 * Getter for assignment object.
	 */
	public TransitionAssignment getAssignment() {
		return assignment;
	}

	/***
	 * Getter for src state object.
	 */
	public AutomatonState getSrcState() {
		return srcState;
	}

	/***
	 * Setter for src state object.
	 */
	public void setSrcState(AutomatonState srcState) {
		this.srcState = srcState;
	}

	public String toStringNoID() {
		StringBuffer retStr = new StringBuffer();

		retStr.append("" + "[");
		retStr.append(" (" + srcState + ",");
		retStr.append(dstState + "), ");

		if (assignment.toString().length() > 0)
			retStr.append("assign [" + assignment + "]; ");

		retStr.append("{" + cstGuard.toStringInline() + "}]");
		return retStr.toString();
	}

}
