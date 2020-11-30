package algorithm;

import java.util.ArrayList;

public class SelectionFunction {

	public enum SelectionType {
		MIN, MAX;
	}

	/**
	 * Defines the aggregation function
	 */
	private SelectionType type;

	/***
	 * Class constructor
	 */
	public SelectionFunction(SelectionType type) {
		this.type = type;

		switch (type) {
		case MIN:
			INIT_VALUE = Integer.MAX_VALUE;//
			break;
		case MAX:
			INIT_VALUE = 0;//
			break;
		}
	}

	/***
	 * Depending on the selection type, it is 
	 * necessary to have the appropriate INIT_VALUE.
	 */
	public static int INIT_VALUE;

	/***
	 * This function calculates the aggregation, depending on Aggregate type.
	 * 
	 * @param prevCost
	 * @param newVal
	 * @return
	 */
	public Double select(ArrayList<Double> newDistances) {

		Double toReturn = Double.valueOf(-1); // default value

		for (int i = 0; i < newDistances.size(); i++) {

			switch (type) {
			case MIN:
				toReturn = Math.min(toReturn, newDistances.get(i));
				break;
			case MAX:
				toReturn = Math.max(toReturn, newDistances.get(i));
				break;
			}

		}

		return toReturn;
	}

	/**
	 * Apply conditional assignment according to selection policy.
	 * 
	 * @param newDistances
	 * @return
	 */
	public double assign(double num1, double num2) {

		double toReturn = -1; // default value

		switch (type) {
		case MIN:
			toReturn = Math.min(num1, num2);
			break;
		case MAX:
			toReturn = Math.max(num1, num2);
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
		default:
			return " E R R O R!";
		}
	}


}
