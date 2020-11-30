package _deprecated;

import automaton.Automaton;

/**
 * 
 * @author JaksicS
 * 
 * WeightedAutomaton is a Automaton with WeighingPolicy.
 * The policy defines how to calculate distance of current input
 * symbol or set of symbols from the legal set of symbols.
 * By applying a weighing policy, to convert from transitions into
 * WeightedTransition objects.
 *
 */
public class WeightedAutomaton extends Automaton{
	
	/**
	 * Policy which defines weight function for all transitions.
	 */
	policy.SatisfactionPolicy policy;
	
	public WeightedAutomaton(policy.SatisfactionPolicy policy) {
		super(false);		
		this.policy = policy;
	}
	
	
	public WeightedAutomaton(Automaton automaton, policy.SatisfactionPolicy policy) {
		super(false);		
		setVariables(automaton.getVariables());
		states 			= automaton.getStates();
		initStates  = automaton.getInitStates();
		transitions = automaton.getTransitions();
		
		this.policy = policy;
	}

}
