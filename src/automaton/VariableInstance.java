package automaton;

import java.io.Serializable;

import constraint.Constraint;

/**
 * An instance of the Variable used to attach to the Temporal Tester Transition.
 * 
 * @author JaksicS
 *
 */
public class VariableInstance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2606021880046482424L;

	/**
	 * referenced variable
	 */
	public Variable var;

	/**
	 * Is variable instance negated or not
	 */
	public boolean isNotInverted;

	/**
	 * Constructor
	 * 
	 * @param var
	 * @param isNegated
	 */
	public VariableInstance(Variable var, boolean isNotInverted) {
		this.var = var;
		this.isNotInverted = isNotInverted;
	}

	/**
	 * Prints out the variable instance in convenient form.
	 */
	public String toString() {
		String varStr = var.getName();

		if (isNotInverted)
			return var.getName();
		else
			return "not " + varStr + "";
	}

	private String toStringReversed() {
		String varStr = var.getName();

		if (isNotInverted)
			return var.getName();
		else
			return varStr + " not";
	}

	/**
	 * 
	 * @param cst_replacement
	 * @return
	 */
	public boolean isEqualToCst(Constraint cst_replacement) {
		String cstStr = cst_replacement.toStringInline().trim();
		cstStr = cstStr.replaceAll("\\s+", " ").trim();

		if (cstStr.equals(this.toStringReversed()))
			return true;
		else
			return false;
	}

	/**
	 * 
	 */
	public boolean isInvertTo(VariableInstance varInst){
		if (!(this.var.getName().equals(varInst.var.getName())))
				return false;
		else if(this.isNotInverted == varInst.isNotInverted)
			return false;
			else return true;
	}

}
