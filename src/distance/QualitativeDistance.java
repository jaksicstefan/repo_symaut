package distance;

import java.util.ArrayList;

public class QualitativeDistance implements DistanceMetrics {

	/***
	 * Values across all dimensions have to be the same,
	 * otherwise return 1. Only if every dimension is the same, then 
	 * the qualitative distance is zero.
	 */
	public Double getDistance(ArrayList<Double> distVector) {
		double sum = 0;

		//effectively doing AND
		for (Double val : distVector) {
			if (val.doubleValue() == 1.0)
				sum = 1;
		}

		return Double.valueOf(sum);
	}
	
	/**
	 * Print.
	 */
	public String toString(){		
		return "Qualitative Distance";
	}


}
