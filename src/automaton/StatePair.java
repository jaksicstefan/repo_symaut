package automaton;

public class StatePair {

	public AutomatonState s1;
	public AutomatonState s2;
	
	public StatePair(AutomatonState state1, AutomatonState state2) {
		s1 = state1;
		s2 = state2;
	}
	
	public String ToString() {
		return s1.toString() + " " + s2.toString();
	}
	

}
