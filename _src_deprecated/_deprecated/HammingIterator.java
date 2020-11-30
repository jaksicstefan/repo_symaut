package _deprecated;

import java.util.ArrayList;

import algorithm.BaseAutomatonIterator;
import automaton.Automaton;
import automaton.AutomatonState;
import automaton.AutomatonTransition;
import automaton.Variable;
import policy.SatisfactionPolicy;

public class HammingIterator extends BaseAutomatonIterator {
	
	/***
	 * Distance incurred to each of the states of the automaton.
	 */
	public ArrayList<Double> distances;

	/***
	 * The cost of performing delete operation, if possible.
	 */
	private int deleteCost;

	/***
	 * User can select if he wants to use delete operations. The field is disabled
	 * by default.
	 */
	private boolean use_delete_ops = false;

	/**
	 * Contains the list of preceding states
	 */
	// public ArrayList<ArrayList<AutomatonState>> precedingStates;

	public ArrayList<ArrayList<AutomatonTransition>> precedingTransitions;

	/**
	 * Constructor which simply forwards the parameter to superclass constructor.
	 * 
	 * @param auto
	 *          : Automaton object to traverse.
	 * 
	 *          In each step, the iterator takes the first transition satisfied.
	 */
	public HammingIterator(Automaton auto, SatisfactionPolicy wpolicy) {
		super(auto, wpolicy);

		// delete cost is taken as MAX of all variables
		deleteCost = Variable.getTotalMaxValue();
		distances = new ArrayList<Double>(auto.getStates().size());

		// init all states except final states to +infinity

		for (int i = 0; i < auto.getStates().size(); i++) {
			distances.add(new Double(Integer.MAX_VALUE)); // this means + infinity
		}

		for (AutomatonState automatonState : auto.getInitStates()) {
			if (automatonState.getId() >= 0) // skip -1 index for err state
				distances.set(automatonState.getId(), new Double(0));
		}

		// construct precedence
		// TODO - important - this object is not updated if the automata changes
		precedingTransitions = new ArrayList<ArrayList<AutomatonTransition>>(distances.size());

		// init
		for (int i = 0; i < distances.size(); i++) {
			precedingTransitions.add(i, new ArrayList<AutomatonTransition>());
		}

		//
		ArrayList<AutomatonTransition> precedeList;
		for (AutomatonState currState : auto.getStates().values()) {
			for (AutomatonTransition transition : auto.getTransitions()) {
				if (transition.getDstState().getId() == currState.getId()) {
					precedeList = precedingTransitions.get(currState.getId());
					precedeList.add(transition);
				}
			}
		}

		// plist print
		for (int i = 0; i < precedingTransitions.size(); i++) {
			System.out.println("Printing predecessors of " + i + ". state");

			for (int j = 0; j < precedingTransitions.get(i).size(); j++) {
				System.out.println(precedingTransitions.get(i).get(j).getSrcState());
			}
		}

	}

	/**
	 * Calculate incurred costs for all the transitions.
	 * 
	 * Apply the input symbol and update the distances.
	 *
	 **/
	public int applyInput(ArrayList<Variable> inputValues) {

		ArrayList<Double> new_distances = new ArrayList<Double>(distances.size());

		for (int i = 0; i < distances.size(); i++) {
			new_distances.add(new Double(Integer.MAX_VALUE));
		}

		System.out.println("applying input" + inputValues + "");

		// update snapshot with new values
		for (Variable variable : inputValues) {
			varSnapshot.put(variable.getName(), variable);
		}

		// for each state, take minimum of the predecessors
		// except for the error state

		for (AutomatonState autoState : automaton.getStates().values()) {

			if (autoState.getId() == -1) { // skip err state
			} else {

				ArrayList<AutomatonTransition> pTrans = precedingTransitions.get(autoState.getId());
				ArrayList<Double> distVector;

				double min_sub_dist, sub_dist;
				int del_cost;

				min_sub_dist = Short.MAX_VALUE; //unreachable
				// deleteCost = 65535; //basically perform no delete

				// how much does it cost to stay in the same state
				del_cost = distances.get(autoState.getId()).intValue() + deleteCost;

				// find cheapest sub
				for (AutomatonTransition aTrans : pTrans) {

					distVector = aTrans.getCstGuard().satisfiedBy(varSnapshot, policy);
					double avg = getAverage(distVector);
					sub_dist = distances.get(aTrans.getSrcState().getId()).intValue() + avg;

					if (sub_dist < min_sub_dist)
						min_sub_dist = sub_dist;
				}

				if (use_delete_ops) {
					if (min_sub_dist < del_cost)
						new_distances.set(autoState.getId(), new Double(min_sub_dist));
					else
						new_distances.set(autoState.getId(), new Double(del_cost)); // MIN
				} else
					new_distances.set(autoState.getId(), new Double(min_sub_dist));

			}
		}

		distances = new_distances;

		System.out.println("Distances after consuming the input values:" + inputValues);
		System.out.println(distances);
		return 1; // every execution accepting

	}

	/***
	 * Runs an input symbol sequence on automaton, and returns 1 if the sequence
	 * is accepted.
	 * 
	 * @param inputValues
	 * @return
	 */
	public int runSequence(ArrayList<Variable> inputValues[]) {
		int retval = -1;

		for (int i = 0; i < inputValues.length; i++) {
			retval = applyInput(inputValues[i]);
		}

		return retval;
	}

	private double getAverage(ArrayList<Double> distVector) {
		// TODO Auto-generated method stub

		// int len = distVector.size();
		double sum = 0;

		for (Double val : distVector) {
			sum = sum + val.doubleValue();
		}

		return sum / distVector.size();
	}

	public boolean isUse_delete_ops() {
		return use_delete_ops;
	}

	public void setUse_delete_ops(boolean use_delete_ops) {
		this.use_delete_ops = use_delete_ops;
	}

}