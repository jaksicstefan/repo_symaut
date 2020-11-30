package distance;

import java.util.ArrayList;

public class MaxDistance implements DistanceMetrics{

	/***
	 * Get a MAX value from an array of values.
	 * 
	 * @param distVector
	 * @return
	 */
	public Double getDistance(ArrayList<Double> distVector) {
		double max = 0; //TODO - to check

		for (Double val : distVector) {
			if (val > max)
				max = val;
		}

		return Double.valueOf(max);
	}
	
	public String toString(){		
		return "MAX Distance";
	}


}
