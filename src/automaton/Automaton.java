package automaton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/***
 * Class which encapsulates Automaton abstraction. Every Automaton had set of
 * input/clock Variables, States, Initial States and Transitions.
 */
public class Automaton implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7561039069739354024L;

	/***
	 * Variables of this automata. This set includes input variables
	 */
	protected HashMap<String, Variable> variables;

	/***
	 * States of this automata.
	 */
	protected HashMap<String, AutomatonState> states;

	/***
	 * Initial States of this automata.
	 */
	protected ArrayList<AutomatonState> initStates;

	/***
	 * Final States of this automata.
	 */
	protected ArrayList<AutomatonState> acceptingStates;

	/***
	 * Transitions of the automaton.
	 */
	protected ArrayList<AutomatonTransition> transitions;

	/***
	 * Field used only when creating automata for nested eventually operator.
	 */
	public int signalVariability = 0;

	// ------------- methods ------------- //

	/***
	 * Constructor which creates an empty automaton.
	 * 
	 */
	public Automaton(boolean hasErrState) {
		setVariables(new HashMap<String, Variable>());
		states = new HashMap<String, AutomatonState>();

		initStates = new ArrayList<AutomatonState>();
		acceptingStates = new ArrayList<AutomatonState>();
		transitions = new ArrayList<AutomatonTransition>();

		if (hasErrState) {
			AutomatonState err_state = new AutomatonState(-1, false, false);
			// TODO - is this correct way to represent error state
			addState(err_state);
		}
	}

	/**
	 * Implementing the Object.equals method used to compare the objects.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof Automaton) {

			Automaton objAuto = (Automaton) obj;

			// simple checks
			if (this.variables.size() != objAuto.variables.size())
				return false;

			if (this.states.size() != objAuto.states.size())
				return false;

			if (this.initStates.size() != objAuto.initStates.size())
				return false;

			if (this.acceptingStates.size() != objAuto.acceptingStates.size())
				return false;

			if (this.transitions.size() != objAuto.transitions.size())
				return false;

			// HashMap<String, Variable> variables;
			Variable myVar1, myVar2;
			boolean found;

			for (Iterator<Variable> iter1 = variables.values().iterator(); iter1.hasNext();) {
				myVar1 = iter1.next();

				found = false;
				for (Iterator<Variable> iter2 = objAuto.variables.values().iterator(); iter2.hasNext();) {
					myVar2 = iter2.next();
					if (myVar1.equals(myVar2))
						found = true;
				}

				if (!(found))
					return false;
			}

			// HashMap<String, AutomatonState> states;
			AutomatonState myState1, myState2;

			for (Iterator<AutomatonState> iter1 = states.values().iterator(); iter1.hasNext();) {
				myState1 = iter1.next();

				found = false;
				for (Iterator<AutomatonState> iter2 = objAuto.states.values().iterator(); iter2.hasNext();) {
					myState2 = iter2.next();
					if (myState1.equals(myState2))
						found = true;
				}

				if (!(found))
					return false;
			}

			// initStates
			for (Iterator<AutomatonState> iter1 = initStates.iterator(); iter1.hasNext();) {
				myState1 = iter1.next();

				found = false;
				for (Iterator<AutomatonState> iter2 = objAuto.initStates.iterator(); iter2.hasNext();) {
					myState2 = iter2.next();
					if (myState1.equals(myState2))
						found = true;
				}
				if (!(found))
					return false;
			}

			// acceptingStates
			for (Iterator<AutomatonState> iter1 = acceptingStates.iterator(); iter1.hasNext();) {
				myState1 = iter1.next();

				found = false;
				for (Iterator<AutomatonState> iter2 = objAuto.acceptingStates.iterator(); iter2.hasNext();) {
					myState2 = iter2.next();
					if (myState1.equals(myState2))
						found = true;
				}
				if (!(found))
					return false;
			}

			// transitions
			AutomatonTransition myTr1, myTr2;
			for (Iterator<AutomatonTransition> iter1 = transitions.iterator(); iter1.hasNext();) {
				myTr1 = iter1.next();

				found = false;
				for (Iterator<AutomatonTransition> iter2 = objAuto.transitions.iterator(); iter2.hasNext();) {
					myTr2 = iter2.next();

					if (myTr1.equals(myTr2))
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

	/***
	 * Adds a variable to the set of automaton states.
	 * 
	 * @param aVar
	 */
	public void addVar(Variable aVar) {

		// TODO -- HashMap Vs Set + iterator
		/*
		 * HashMap<Integer, Integer> hm = new HashMap<>(); Set<Entry<Integer,
		 * Integer>> mySet = hm.entrySet(); Iterator<Entry<Integer, Integer>>
		 * setIter = mySet.iterator(); setIter.next();
		 */

		if (!getVariables().containsKey(aVar.toString())) {
			getVariables().put(aVar.getName(), aVar);
		}
	}

	/***
	 * Adds a state to the set of automaton states. If the state already exists,
	 * no state is added and the function returns null.
	 * 
	 * @param aState
	 */
	public AutomatonState addState(AutomatonState aState) {

		if (!states.containsKey(aState.toString())) {
			states.put(aState.toString(), aState);

			if (aState.isInitial())
				addInitState(aState);
			if (aState.isAccepting())
				addAcceptingState(aState);

			return aState;
		}

		return null;
	}

	/***
	 * Adds a state to the set of init states.
	 * 
	 * @param aState
	 */
	private void addInitState(AutomatonState iState) {
		initStates.add(iState);
	}

	/***
	 * Adds a state to the set of accepting states.
	 * 
	 * @param aState
	 */
	private void addAcceptingState(AutomatonState iState) {
		acceptingStates.add(iState);
	}

	/***
	 * Adds a transition to the set of automaton transitions.
	 * 
	 * @param aTrans
	 */
	public void addTransition(AutomatonTransition aTrans) {
		// add as exiting trans from a starting state
		states.get("" + aTrans.getSrcState().toString()).exitingTrans.add(aTrans);
		transitions.add(aTrans);
	}

	/***
	 * This should perform breadth first traversal of the automaton. This method
	 * should be used only to print automaton in human readable format.
	 * 
	 * @return
	 */
	public String breadthFirst() {
		StringBuffer strBuf = new StringBuffer("");
		StringBuffer transBuf = new StringBuffer("");
		StringBuffer allStates = new StringBuffer("{");
		StringBuffer allVars = new StringBuffer("{");
		StringBuffer accBuf = new StringBuffer("");

		ArrayList<AutomatonState> auxList = new ArrayList<AutomatonState>();
		ArrayList<Integer> visitedStatesIDList = new ArrayList<Integer>();

		
		HashMap<String, AutomatonState> traversedList = new HashMap<String, AutomatonState>();

		AutomatonState aux_state;

		for (int i = 0; i < initStates.size(); i++) {
			auxList.add(initStates.get(i));
			
			for (AutomatonState state : auxList) {
				visitedStatesIDList.add(state.getId());
			}
		}

		while (auxList.size() > 0) {
			aux_state = (AutomatonState) auxList.remove(0);
			visitedStatesIDList.remove(0);

			// traverse aux state. = add it to the printout
			strBuf.append(aux_state.toString() + ",");
			traversedList.put(aux_state.toString(), aux_state);

			for (AutomatonTransition trans : aux_state.exitingTrans) {

				transBuf.append(trans.toString() + "\n");

				if (traversedList.containsKey(trans.getDstState().toString()) == false)
					if (!(visitedStatesIDList.contains(trans.getDstState().getId())))
						auxList.add(trans.getDstState());
						visitedStatesIDList.add(trans.getDstState().getId());
			}
		}

		auxList.clear();
		for (int i = 0; i < acceptingStates.size(); i++) {
			accBuf.append(acceptingStates.get(i).toString() + ",");
		}

		if (this.acceptingStates.size() != 0)
			accBuf.deleteCharAt(accBuf.length() - 1);

		if (strBuf.substring(strBuf.length() - 1).equals(","))
			strBuf.deleteCharAt(strBuf.length() - 1);

		for (AutomatonState st : this.states.values()) {
			allStates.append(st + ",");
		}
		if (this.states.size() != 0)
			allStates.deleteCharAt(allStates.length() - 1);

		allStates.append("}");

		for (Variable v : this.variables.values()) {
			allVars.append(v.getName() + ",");
		}
		if (this.variables.size() != 0)
			allVars.deleteCharAt(allVars.length() - 1);
		allVars.append("}");

		return "Variables = " + allVars.toString() + "\nAll states = " + allStates.toString() + "\nReachable states = {"
				+ strBuf.toString() + "}\nAccepting states = {" + accBuf.toString() + "}\nTransitions:\n" + transBuf.toString();
	}

	/***
	 * Print.
	 */
	public String toString() {
		return breadthFirst();
	}

	/***
	 * Get the list of initial states of automaton.
	 */
	public ArrayList<AutomatonState> getInitStates() {
		return initStates;
	}

	/***
	 * Get the list of accepting states of automaton.
	 */
	public ArrayList<AutomatonState> getAcceptingStates() {
		return acceptingStates;
	}

	/***
	 * Returns default error state (last state) Returns NULL if there is no error
	 * state.
	 * 
	 * @return
	 */
	public AutomatonState getErrorState() {
		// error state has ID = -1
		return this.states.get("-1");
	}

	/***
	 * Returns default error state (last state) Returns NULL if there is no error
	 * state.
	 * 
	 * @return
	 */
	public boolean hasErrState() {
		// error state has ID = -1
		if (this.states.get("-1") == null)
			return false;
		else
			return true;
	}

	/***
	 * Getter for the states.
	 * 
	 * @return
	 */
	public HashMap<String, AutomatonState> getStates() {
		return states;
	}

	public HashMap<String, Variable> getVariables() {
		return variables;
	}

	public void setVariables(HashMap<String, Variable> variables) {
		this.variables = variables;
	}

	public ArrayList<AutomatonTransition> getTransitions() {
		return transitions;
	}

	public int getNumberOfStates() {
		return this.states.size();
	}

	public int getNumberOfTransitions() {
		return this.transitions.size();
	}

}
