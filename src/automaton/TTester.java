package automaton;

import java.util.HashMap;
import java.util.Iterator;

import constraint.Constraint;

/**
 * Temporal tester is an automaton with output variables.
 * 
 * @author JaksicS
 *
 */
public class TTester extends Automaton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***
	 * Output variables of a temporal tester.
	 */
	private HashMap<String, Variable> outVariables;

	/**
	 * Constructor.
	 * 
	 * @param hasErrState
	 */
	public TTester(boolean hasErrState) {
		super(hasErrState);
		outVariables = new HashMap<String, Variable>();
	}

	/**
	 * Getter of the list of output variables.
	 * 
	 * @return
	 */
	public HashMap<String, Variable> getOutVariables() {
		return outVariables;
	}

	/**
	 * Setter of the list of output variables.
	 * 
	 * @return
	 */
	public void setOutVariables(HashMap<String, Variable> outVariables) {
		this.outVariables = outVariables;
	}

	/**
	 * Method which adds an output variable into the list of outputs.
	 * 
	 * @param aVar
	 */
	public void addOutVar(Variable aVar) {

		// TODO -- HashMap Vs Set + iterator
		/*
		 * HashMap<Integer, Integer> hm = new HashMap<>(); Set<Entry<Integer,
		 * Integer>> mySet = hm.entrySet(); Iterator<Entry<Integer, Integer>>
		 * setIter = mySet.iterator(); setIter.next();
		 */

		if (!(outVariables.containsKey(aVar.toString()))) {
			outVariables.put(aVar.toString(), aVar);
		}
	}

	/**
	 * Printing the object
	 */
	public String toString() {
		StringBuffer strbuf = new StringBuffer("\nOutput Variables: {");

		for (Variable v : this.outVariables.values()) {
			strbuf.append(v.getName() + ",");
		}
		strbuf.deleteCharAt(strbuf.length() - 1);
		strbuf.append("}");

		return strbuf.toString() + "\n" + super.toString();
	}

	/**
	 * Gets output variable, in case there is just one such variable.
	 * 
	 * In case there are multiple vars, return null.
	 * 
	 * @return
	 */
	public Variable getSingleOutputVar() {
		Iterator<Variable> iter = this.outVariables.values().iterator();

		if (this.outVariables.values().size() > 1) {
			System.err.println("error: expected only one output variable");
			return null;
		} else {
			return iter.next();
		}
	}

	/**
	 * This will determine if the tester is simply testing for a single variable.
	 * 
	 * @return
	 */
	public boolean isSingleVarTester() {

		if (this.outVariables.size() > 1)
			return false;

		if (this.variables.size() > 1)
			return false;

		if (this.states.size() > 1)
			return false;

		if (this.transitions.size() != 2)
			return false;
		
		if (this.getVariables().get(this.getSingleOutputVar().getName()) == null) //ako nije ista I/O varijabla
			return false;

		Constraint cst0 = this.transitions.get(0).getCstGuard();
		Constraint cst1 = this.transitions.get(1).getCstGuard();

		cst0 = cst0.negate();
		cst0.toNNF();

		if (!(cst0.equals(cst1)))
			return false;

		TTesterTransition tt0 = (TTesterTransition) transitions.get(0);
		TTesterTransition tt1 = (TTesterTransition) transitions.get(1);

		if (!(tt0.outVars.get(0).isInvertTo(tt1.outVars.get(0))))
			return false;
		else {
//			System.out.println("isSingleVarTester TRUE::"+ this.toString());
			return true;
		}
	}
}
