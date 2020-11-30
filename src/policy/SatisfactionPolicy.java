package policy;

import java.util.ArrayList;
import java.util.HashMap;

import automaton.Variable;
import constraint.ConstraintNode.NodeType;

public interface SatisfactionPolicy {

	/***
	 * This function specifies the rule for calculating the distance cost based on
	 * the specified logical operator semantics. The semantics of the operators is
	 * defined in this method. In general case, every node can return
	 * N-dimensional weight.
	 * 
	 * @param left_dist
	 *          : Distances obtained from the left child subtree
	 * @param right_dist
	 *          : Distances obtained from the right child subtree
	 * @param nodeType
	 *          : type of node
	 * @return the cost according to the semantics
	 * 
	 *         in example, we can assign AND operation with MAX function, OR with
	 *         MIN, or any other mapping
	 */
	public ArrayList<Double> getCost(ArrayList<Double> left_dist, ArrayList<Double> right_dist, NodeType nodeType);

	/***
	 * This method will calculate how far is the current variable snapshot from
	 * the set of legal values defined by the constraint node.
	 * 
	 * @param varSnapshot
	 * @param varName
	 * @param nt
	 * @param threshold
	 * @return the distance
	 */
	public ArrayList<Double> getDistanceFromSet(HashMap<String, Variable> varSnapshot, String varName, NodeType nt, int threshold);
		
	/***
	 * Set the appropriate variables and indices, based on the automata.
	 * @param varSnapshot
	 */
	public void setVars(HashMap<String, Variable> varSnapshot);

}
