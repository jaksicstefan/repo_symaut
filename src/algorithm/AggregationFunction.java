package algorithm;

public class AggregationFunction {

	public enum AggregateType {
		MIN, MAX, SUM;		
	}

	/**
	 * Defines the aggregation function
	 */
	private AggregateType type;
	
	/***
	 * Class constructor
	 */
	public AggregationFunction( AggregateType type){
		this.type = type;		
	}

	/***
	 * This function calculates the aggregation, depending on Aggregate type.
	 * 
	 * @param prevCost
	 * @param newVal
	 * @return
	 */
	public double aggregate(double prevCost, double newVal) {

		double toReturn = -1; // default value

		switch (type) {
		case MIN:
			toReturn = Math.min(prevCost, newVal);
			break;
		case MAX:
			toReturn = Math.max(prevCost, newVal);
			break;
		case SUM:  //Hamming
			toReturn = prevCost + newVal;
			break;
		}

		return toReturn;
	}
	
	/**
	 * 
	 */
	public String toString(){
		switch(type) {
		case MIN:
			return "MIN";
		case MAX:
			return "MAX";
		case SUM:
			return "SUM";
		default:
			return " E R R O R!";
		}
	}


}
