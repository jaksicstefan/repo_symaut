package distance;

import java.util.ArrayList;

public class ManhattanDistance implements DistanceMetrics {

/***
 * just an average of the distances in N-dimensional space
 */
	public Double getDistance(ArrayList<Double> distVector) {
		double sum = 0;

		for (Double val : distVector) {
			sum = sum + val;
		}

		return Double.valueOf(sum / distVector.size());
	}	
	
	/**
	 * 
	 */
	public String toString(){
		return "Manhattan Distance";
	}

}
