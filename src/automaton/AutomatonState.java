package automaton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/***
 * This class represents the internal state of the automaton.
 * 
 * @author JaksicS
 *
 */

public class AutomatonState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7004382706502677796L;

	/***
	 * ID seed.
	 * 
	 */
	public static int stateID = 0;

	/***
	 * State object ID
	 * 
	 */
	private int id;

	/***
	 * Transitions having current state as a source.
	 */
	public ArrayList<AutomatonTransition> exitingTrans;

	/**
	 * Set to 'true' if the state is an accepting state.
	 */
	private boolean isAccepting;

	/**
	 * Set to 'true' if the state is an initial state.
	 */
	private boolean isInitial;

	/**
	 * Used as an in order to remember prediction for this state. This field will
	 * be used to identify redundant states in next^n temporal tester.
	 */
	public int label;

	// -------------- methods --------------//

	/***
	 * Creates an empty state
	 */
	public AutomatonState(boolean init, boolean acc) {
		id = stateID++;
		this.exitingTrans = new ArrayList<AutomatonTransition>();
		this.isAccepting = acc;
		this.isInitial = init;
	}

	public AutomatonState(int newStateId, boolean init, boolean acc) {
		id = newStateId;
		this.exitingTrans = new ArrayList<AutomatonTransition>();
		this.isAccepting = acc;
		this.isInitial = init;
	}

	/***
	 * Determines if this State is equal to another State.
	 * 
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof AutomatonState) {

			AutomatonState objState = (AutomatonState) obj;

			// DISCARD ID CHECKS
			// if (!(this.id == objState.id))
			// return false;

			if (!(this.isAccepting == objState.isAccepting))
				return false;

			if (!(this.isInitial == objState.isInitial))
				return false;

			if (!(this.exitingTrans.size() == objState.exitingTrans.size()))
				return false;

			AutomatonTransition myTr1, myTr2;
			boolean found;

			for (Iterator<AutomatonTransition> iter1 = this.exitingTrans.iterator(); iter1.hasNext();) {
				myTr1 = iter1.next();

				found = false;
				for (Iterator<AutomatonTransition> iter2 = objState.exitingTrans.iterator(); iter2.hasNext();) {
					myTr2 = iter2.next();

					// ID CHECK DISCARDED
					if ((myTr1.getCstGuard()
							.equals(myTr2.getCstGuard())) /*
																						 * && (myTr1.getDstState().id ==
																						 * myTr2.getDstState().id)
																						 */)
						if ((myTr1.getDstState().isAccepting == myTr2.getDstState().isAccepting)
								&& (myTr1.getDstState().isInitial == myTr2.getDstState().isInitial))
							found = true;
				}
				if (!(found))
					return false;
			}
			
			return true;
		}
		else
			return false;
	}

	/**
	 * Regardless of the ID - states are equivalent if they have same properties
	 * and exit transition
	 * 
	 * @param state
	 * @return
	 */
	// public boolean equivalent(AutomatonState state) {
	// boolean found;
	//
	// if (!(this.isAccepting == state.isAccepting))
	// return false;
	//
	// if (!(this.isInitial == state.isInitial))
	// return false;
	//
	// AutomatonTransition myTr1, myTr2;
	// for (Iterator<AutomatonTransition> iter1 = exitingTrans.iterator();
	// iter1.hasNext();) {
	// myTr1 = iter1.next();
	// AutomatonState dstState1 = myTr1.getDstState();
	//
	// found = false;
	//
	// for (Iterator<AutomatonTransition> iter2 = state.exitingTrans.iterator();
	// iter2.hasNext();) {
	// myTr2 = iter2.next();
	// AutomatonState dstState2 = myTr1.getDstState();
	//
	// if (myTr1.equivalent(myTr2)) {// equivalent conditions
	// found = true;
	//
	// }
	//
	// }
	// if (!(found))
	// return false;
	// }
	//
	// return true;
	//
	// }

	public String toString() {
		return "" + this.id;
	}

	/***
	 * Adding exiting transition to the set of exiting states.
	 * 
	 * @param tr
	 */
	public void addTrans(AutomatonTransition tr) {
		this.exitingTrans.add(tr);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAccepting() {
		return isAccepting;
	}

	public boolean isInitial() {
		return isInitial;
	}

	public void setAccepting(boolean isAccepting) {
		this.isAccepting = isAccepting;
	}

	public void setInitial(boolean isInitial) {
		this.isInitial = isInitial;
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	};

}
