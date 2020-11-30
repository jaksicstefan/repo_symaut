package distance;

import java.util.ArrayList;

public class MinDistance implements DistanceMetrics{

	/***
	 * Get a minimum value from an array of values.
	 * 
	 * @param distVector
	 * @return
	 */
	public Double getDistance(ArrayList<Double> distVector) {
		double min = Double.MAX_VALUE;

		for (Double val : distVector) {
			if (val < min)
				min = val;
		}

		return Double.valueOf(min);
	}
	
	public String toString(){		
		return "MIN Distance";
	}


}
