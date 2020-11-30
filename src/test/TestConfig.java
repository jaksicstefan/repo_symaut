package test;


import algorithm.AggregationFunction;
import algorithm.SelectionFunction;
import distance.DistanceMetrics;
import policy.SatisfactionPolicy;

/**
 * This will be the configuration object for the tests.
 * 
 * It must specify which semantics is used, distance metrics,
 * aggregation and selection function.
 * 
 * The fields in this class will be configured from XML file.
 * @author JaksicS
 *
 */
public class TestConfig {
	
//	DistanceMetrics dist = new QualitativeDistance();
//	SatisfactionPolicy pol = QualitativePolicy.getInstance(myAutomaton.getVariables());
//	AggregationFunction aggr = new AggregationFunction(AggregateType.MAX);
//	SelectionFunction min = new SelectionFunction(SelectionType.MIN);

	
	public DistanceMetrics distanceMetrics;
	public SatisfactionPolicy satisfactionPolicy;
	public AggregationFunction aggregationFunction;
	public SelectionFunction selectionFunction;
	public int varTotalRange;
	
	public boolean validate() {
		boolean toReturn = true;
		
		//TODO - 
		//do the checks
		//whether it is in the legal set of values.
		
		return toReturn; 
	}
	
}
