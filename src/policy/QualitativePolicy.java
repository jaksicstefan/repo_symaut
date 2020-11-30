package policy;

import java.util.ArrayList;
import java.util.HashMap;

import automaton.Variable;
import constraint.ConstraintNode.NodeType;

public class QualitativePolicy implements SatisfactionPolicy {

	/**
	 * Maps variables to indices.
	 */
	public HashMap<String, Integer> indexMap;

	/**
	 * NUmber of variables used to calculate qualitative verdict.
	 */
	private int numOfVars = 0;

	/**
	 * Obtain a singleton instance of the class.
	 * 
	 * @return
	 */
	public void setVars(HashMap<String, Variable> varSnapshot) {
		int k = 0;
		indexMap = new HashMap<String, Integer>();

		for (Variable varS : varSnapshot.values()) {
			indexMap.put(varS.getName(), k);
			k++;
		}
		numOfVars = varSnapshot.values().size();
	}

	/**
	 * Abstract method implementation. Calculates the cost. In case of qualitative
	 * semantics, AND operation yields MIN function on integer result which is
	 * either 1(true) or 0(false). OR operation yields MAX.
	 * 
	 * @left left subtree result
	 * @return left subtree result
	 * @return Returns -1 as illegal value. 1 = true, 0 = false
	 * 
	 *         This function defines value propagation rule for distance
	 *         calculation (by performing bottom-up traversal of the
	 *         ConstraintTree)
	 */
	public ArrayList<Double> getCost(ArrayList<Double> left_dist, ArrayList<Double> right_dist, NodeType nodeType) {

		Double dbl2put;
		ArrayList<Double> arr2ret = new ArrayList<Double>(1);

		if (nodeType == NodeType.LOG_AND) // max
		{
			for (int i = 0; i < Variable.numVars; i++) {
				dbl2put = (left_dist.get(i) > right_dist.get(i)) ? left_dist.get(i) : right_dist.get(i); // null?
																																																	// TODO
				arr2ret.add(i, dbl2put);
			}
		} else if (nodeType == NodeType.LOG_OR) // min
		{
			for (int i = 0; i < Variable.numVars; i++) {
				dbl2put = (left_dist.get(i) < right_dist.get(i)) ? left_dist.get(i) : right_dist.get(i); // null?
																																																	// TODO
				arr2ret.add(i, dbl2put);
			}
		} else
			arr2ret.add(0, Double.valueOf(-1));

		// arr2ret.add(new Double(toReturn));
		return arr2ret;
	}

	/**
	 * Abstract method implementation. Calculates set inclusion. If the variable
	 * snapshot belongs to the set, the function returns 1, otherwise 0.
	 * 
	 * In example, if X>5 and current value of X is 6 then it is in the set and we
	 * return 1.
	 */
	public ArrayList<Double> getDistanceFromSet(HashMap<String, Variable> varSnapshot, String varName, NodeType nt,
			int threshold) {

		Variable var = varSnapshot.get(varName);
		int varValue = var.getValue();
		double retval;

		// ZERO DISTANCE MEANS TRUE!

		switch (nt) {
		case CMP_EQ:
			retval = (varValue == threshold) ? 0 : 1;
			break;
		case CMP_NEQ:
			retval = (varValue != threshold) ? 0 : 1;
			break;
		case CMP_LESS:
			retval = (varValue < threshold) ? 0 : 1;
			break;
		case CMP_GREAT:
			retval = (varValue > threshold) ? 0 : 1;
			break;
		case CMP_LEQ:
			retval = (varValue <= threshold) ? 0 : 1;
			break;
		case CMP_GEQ:
			retval = (varValue >= threshold) ? 0 : 1;
			break;
		default:
			retval = 0;
			System.err.println("getDistanceFromSet::PROBLEM");
			break;
		}

		int index = indexMap.get(varName);

		// place in correct position
		ArrayList<Double> arr2ret = new ArrayList<Double>(numOfVars);
		for (int i = 0; i < numOfVars; i++) {
			arr2ret.add(Double.valueOf(-1)); // initialize
		}

		arr2ret.set(index, Double.valueOf(retval));
		return arr2ret;
	}

	/**
	 * Printout.
	 */
	public String toString() {
		return "Qualitative Policy";
	}

}