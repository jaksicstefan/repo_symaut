package policy;

import java.util.ArrayList;
import java.util.HashMap;

import automaton.Variable;
import constraint.ConstraintNode.NodeType;

//Strategy design pattern 
//Singleton Design pattern
public class QuantitativePolicy implements SatisfactionPolicy {

	/**
	 * Maps variables to indices.
	 */
	public HashMap<String, Integer> indexMap;

	/**
	 * Number of variables used to calculate qualitative verdict.
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
	 * AND node yields MAX operation on the subtree values. OR operation yields
	 * MIN operation on the subtree values.
	 * 
	 * Root node returns distance of variable set from set of constraints,
	 * distance is presented per variable in the ArrayList<Integer> returned
	 * object.
	 */
	public ArrayList<Double> getCost(ArrayList<Double> left_dist, ArrayList<Double> right_dist, NodeType nodeType) {
		ArrayList<Double> arr2ret = new ArrayList<Double>(1);

		//
		// TODO - make sure we don't have UNSAT constraints here, otherwise
		// the procedure calculates wrong value
		//
		Double dbl2put;
		if (nodeType == NodeType.LOG_AND) // max
		{
			for (int i = 0; i < left_dist.size(); i++) {
				dbl2put = (left_dist.get(i) > right_dist.get(i)) ? left_dist.get(i) : right_dist.get(i); // TODO
																																																	// -
																																																	// what
																																																	// about
																																																	// null?
				arr2ret.add(i, dbl2put);
			}
		} else if (nodeType == NodeType.LOG_OR) // min
		{
			for (int i = 0; i < Variable.getClassID(); i++) {

				// handle init values -1
				if (left_dist.get(i).intValue() == -1)
					dbl2put = right_dist.get(i);
				else if (right_dist.get(i).intValue() == -1)
					dbl2put = left_dist.get(i);
				else
					dbl2put = (left_dist.get(i) < right_dist.get(i)) ? left_dist.get(i) : right_dist.get(i);

				arr2ret.add(i, dbl2put);
			}
		} else
			System.err.println("ERROR");

		return arr2ret;
	}

	/**
	 * Calculates simple pointwise distance of a variable value from the set.
	 * 
	 */
	public ArrayList<Double> getDistanceFromSet(HashMap<String, Variable> varSnapshot, String varName, NodeType nt,
			int threshold) {

		// get the current variable value
		Variable var = varSnapshot.get(varName);

		int varValue = var.getValue();
		int retval;

		switch (nt) {
		case CMP_EQ:
			retval = (varValue == threshold) ? 0 : Math.abs(varValue - threshold);
			break;
		case CMP_NEQ:
			retval = (varValue != threshold) ? 0 : 1; // TODO -check this line
			break;
		case CMP_LESS:
			retval = (varValue < threshold) ? 0 : (varValue + 1) - threshold;
			break;
		case CMP_GREAT:
			retval = (varValue > threshold) ? 0 : (threshold + 1) - varValue;
			break;
		case CMP_LEQ:
			retval = (varValue <= threshold) ? 0 : varValue - threshold;
			break;
		case CMP_GEQ:
			retval = (varValue >= threshold) ? 0 : threshold - varValue;
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

		// DONT PUT BY ID, PUT BY SMTH ELSE.
		// create mapping

		// System.out.println("SET " + retval + " index = " + index);

		arr2ret.set(index, Double.valueOf(retval));
		return arr2ret;
	}

	/**
	 * printout.
	 */
	public String toString() {
		return "Quantitative Policy";
	}

}
