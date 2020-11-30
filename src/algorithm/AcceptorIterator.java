package algorithm;

import java.util.ArrayList;

import policy.SatisfactionPolicy;
import automaton.Automaton;
import automaton.AutomatonState;
import automaton.AutomatonTransition;
import automaton.Variable;


/***
 * This iterator implements an acceptor functionality. 
 */

public class AcceptorIterator extends BaseAutomatonIterator {
	/**
	 * Constructor which simply forwards the parameter to superclass constructor.
	 * 
	 * @param auto
	 *          : Automaton object to traverse.
	 *          
	 * In each step, the iterator takes the first transition satisfied.
	 */
	public AcceptorIterator(Automaton auto, SatisfactionPolicy wpolicy) {
		super(auto, wpolicy);
		currentState = auto.getInitStates().get(0); //start from the init state
	}

	/***
	 * Current state which is reached after applying a sequence.
	 */
	protected AutomatonState currentState;

	
	/**
	 * Applies a set of newly obtained input variable values to the current
	 * automaton. Transfers the iterator into new state, returning 1 if the
	 * sequence is accepting.
	 */
	public int applyInput(ArrayList<Variable> inputValues) {
		AutomatonState newState = null;
		AutomatonTransition transition2take = null;

		System.out.println("applying input" + inputValues + "");
		
		// update snapshot with new values
		for (Variable variable : inputValues) {
			varSnapshot.put(variable.getName(), variable);
		}

		//TODO - the satisfiedBy output should be aggregated explicitly through aggregator object.
		
		if (currentState.getId() != automaton.getErrorState().getId()) // not stuck in error state
			for (AutomatonTransition exTrans : currentState.exitingTrans) {
			if ( calcSAT((exTrans.getCstGuard().satisfiedBy(varSnapshot, policy))) == 1.0) {
				
				

					System.out.println("Transition taken: " + exTrans + " ");
					newState = exTrans.getDstState();
					transition2take = exTrans;
					break;
				}
			}

		if ((newState == null) || (currentState == automaton.getErrorState())) {
			System.out.println("NO DESTINATION STATE FOUND. TRANSITING TO ERROR STATE!");
			newState = automaton.getErrorState(); // last state is the default error
																						// state
			currentState = newState;
			return -1; // illegal response
		} else {
			currentState = newState;
			System.out.println("Transiting to new state: " + currentState + " ");

			// apply assignments on var snapshot
			transition2take.getAssignment().assign(varSnapshot);

			if (automaton.getAcceptingStates().contains(currentState)) //TODO - check if works..
				return 1;
			else
				return 0;
		}
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

	/***
	 * Current state getter.
	 * @return
	 */
	public Object getCurrentState() {
		return currentState;
	}

	
	
/***Aux function*/	
public int calcSAT(ArrayList<Double> distArr) {

	int retSAT = 1;
	for (Double dist : distArr) {
		if (dist.doubleValue() < 1)
			retSAT = 0;
	}

	return retSAT;
}

	
}