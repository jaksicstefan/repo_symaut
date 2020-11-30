package algorithm;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import automaton.Automaton;
import automaton.AutomatonState;
import automaton.AutomatonTransition;
import automaton.Variable;
import distance.DistanceMetrics;
import distance.QualitativeDistance;
import test.TestConfig;

/***
 * This class should implement the dynamic programming algorithm.
 * 
 * @author JaksicS
 *
 */
public class DynamicAlgorithm extends BaseAutomatonIterator {

	/***
	 * Cost calculated to reach each of the states of the automaton.
	 */
	public ArrayList<Double> distances;

	/***
	 * Contains the list of preceding transitions for every state
	 */
	public ArrayList<ArrayList<AutomatonTransition>> precedingTransitions;

	/***
	 * Manhattan, MIN-MAX, or Qualitative metrics for finding a distance between a
	 * point and a set.
	 */
	private DistanceMetrics distanceMetrics;

	/***
	 * This object defines how to aggregate the distance over time.
	 */
	private AggregationFunction aggregation;

	/***
	 * This object defines how to the optimal path.
	 */
	private SelectionFunction selection;

	/***
	 * Determines whether the output should be logged to a file.
	 */
	public static boolean logOutput = true;
	

	/***
	 * File to write the outputs.
	 */
	Path file ;

	/***
	 * Constructor which simply forwards the parameter to superclass constructor.
	 *
	 * @param auto
	 *          : Automaton object to traverse
	 * @param wpolicy
	 *          : policy for calculating the distance
	 */
	public DynamicAlgorithm(Automaton auto, TestConfig config) {
		super(auto, config.satisfactionPolicy);
		this.distanceMetrics = config.distanceMetrics;
		this.aggregation = config.aggregationFunction;
		this.selection = config.selectionFunction;

		distances = new ArrayList<Double>(auto.getStates().size());

		// init all non-init states to +infinity.
		for (int i = 0; i < auto.getStates().size(); i++) {
			//for qualitative infinity = 2
			if (config.distanceMetrics instanceof QualitativeDistance) {
				distances.add(Double.valueOf(2)); // 2 means + infinity

			}			
			else{
			distances.add(Double.valueOf(Integer.MAX_VALUE)); // this means + infinity
			}
		}

		// distance is 0 for init states.
		for (AutomatonState automatonState : auto.getInitStates()) {
			if (automatonState.getId() >= 0) // skip -1 index for err state
				distances.set(automatonState.getId(), Double.valueOf(0));
		}

		// construct precedence
		// TODO - important - this object is not updated if the automata changes
		precedingTransitions = new ArrayList<ArrayList<AutomatonTransition>>(distances.size());

		// init
		for (int i = 0; i < distances.size(); i++) {
			precedingTransitions.add(i, new ArrayList<AutomatonTransition>());
		}

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
		// for (int i = 0; i < precedingTransitions.size(); i++) {
		// System.out.println("Printing predecessors of " + i + ". state");
		//
		// for (int j = 0; j < precedingTransitions.get(i).size(); j++) {
		// System.out.println(precedingTransitions.get(i).get(j).getSrcState());
		// }
		// }

		// open file for writing.

		List<String> lines = Arrays.asList("");
		file = Paths.get("output\\output.txt");
		try {
			//clear the file
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Files.write(file, lines, Charset.forName("UTF-8"));


	}

	/***
	 * Calculate incurred costs for all the transitions.
	 * 
	 * Apply the input symbol and update the distances.
	 *
	 **/
	public int applyInput(ArrayList<Variable> inputValues) {
		ArrayList<Double> new_distances = new ArrayList<Double>(distances.size());

		System.out.println("\nApplying input " + inputValues + " = " + inputValues.get(0).getValue());

		for (int i = 0; i < distances.size(); i++)
			new_distances.add(Double.valueOf(Integer.MAX_VALUE));

		for (Variable variable : inputValues)
			varSnapshot.put(variable.getName(), variable);

		// for each state, take minimum of the predecessors
		// except for the error state
		for (AutomatonState autoState : automaton.getStates().values()) {

			if (autoState.getId() == -1) { // skip err state
			} else {

				ArrayList<AutomatonTransition> pTrans = precedingTransitions.get(autoState.getId());
				ArrayList<Double> distVector;

				double candidateSubDist, subDist;

				candidateSubDist = SelectionFunction.INIT_VALUE;

				// find cheapest sub
				// if no incoming transitions, assign MAX_VALUE, (unreachable)
				for (AutomatonTransition aTrans : pTrans) {

					distVector = aTrans.getCstGuard().satisfiedBy(varSnapshot, policy);
					// System.out.println("\nDistanceVector:" + distVector);

					// MANHATTAN/MIN-MAX/QUALITATIVE implemented in distanceMetrics.
					double distance = distanceMetrics.getDistance(distVector);
					// System.out.println("\nDistance:" + distance);

					// aggregate new value
					subDist = aggregation.aggregate(distances.get(aTrans.getSrcState().getId()).intValue(), distance);
					// System.out.println("\nAggregate new value :" + subDist);

					// implements min/max
					candidateSubDist = selection.assign(candidateSubDist, subDist);
					// System.out.println("\nSelected new value :" + candidateSubDist);
				}

				// System.out.println("Setting node ID " + autoState.getId() + " with
				// value " + new Double(candidateSubDist));
				new_distances.set(autoState.getId(), Double.valueOf(candidateSubDist));
			}
		}

		distances = new_distances;

		System.out.println("Distances after consuming the input values:" + inputValues);
		System.out.println(distances);

		// log

		if (logOutput) {			
			StringBuffer strbuf = new StringBuffer();
			
			for (Variable var : inputValues) {
				strbuf.append(var.getValue()+", ");
			}
			
			List<String> line = Arrays.asList(strbuf.toString() + getMin(distances, automaton.getAcceptingStates()).toString());
			try {
				Files.write(file, line, Charset.forName("UTF-8"),StandardOpenOption.APPEND);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return 1; // every execution accepting
	}
	
	/***
	 * gets the min of all the ACCEPTING states
	 * @param dist
	 * @param accStates 
	 * @return
	 */
	private Double getMin( ArrayList<Double> dist, ArrayList<AutomatonState> accStates){
		Double min = Double.MAX_VALUE;
		
		for (AutomatonState thestate : accStates) {
			if (dist.get(thestate.getId()) < min) 
				min = dist.get(thestate.getId()); 
		}		
		return min;		
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
	 * Returns the optimal path cost
	 * 
	 * @return
	 */
	public double getOptimalPathCost() {
		// select the optimal distance according to selection function
		return selection.select(distances);
	}

}