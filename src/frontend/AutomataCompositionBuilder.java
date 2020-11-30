package frontend;

import java.util.ArrayList;
import java.util.HashMap;

import org.sosy_lab.common.configuration.InvalidConfigurationException;
import org.sosy_lab.java_smt.api.SolverException;

import automaton.AutomataService;
import automaton.TTester;
import automaton.Variable;
//import scala.collection.parallel.ParIterableLike.Forall;

public class AutomataCompositionBuilder {

	/**
	 * This ArrayList contains non-processed input vars. It will be used to traverse
	 * automata in top-down fashion.
	 */
	public  ArrayList<Variable> varQueue;

	/**
	 * Local field which stores the product.
	 */
	private TTester product;

	/**
	 * This map will be used to exchange automata objects among fadifferent parse
	 * rules.
	 */
	public HashMap<String, TTester> automataMap;

	/**
	 * Constructor.
	 */
	public AutomataCompositionBuilder(HashMap<String, TTester> automataMap, String topKey) {
		this.automataMap = automataMap;
		this.varQueue = new ArrayList<Variable>();
		
		this.varQueue.addAll(automataMap.get(topKey).getVariables().values());
		
		product = automataMap.get(topKey); //Inital product is top-level automaton.
		
		this.automataMap.remove(topKey);

		redefineKeysAutomataMap();
	}

	/**
	 * automataMap will be reordered s.t. the key is not anymore
	 * the ParseTreeContext string, but rather the string representing
	 * the output variable.
	 */
	private void redefineKeysAutomataMap() {
		HashMap<String, TTester> nuAutomataMap = new HashMap<String, TTester>();

		ArrayList<TTester> vals = new ArrayList<TTester>(this.automataMap.values());
		for (TTester tTester : vals) {
			nuAutomataMap.put(tTester.getSingleOutputVar().getName(), tTester);
		}

		this.automataMap = nuAutomataMap;
//		System.out.println("redefineKeysAutomataMap::DBG");
//		System.out.println(this.automataMap.toString());
	}

	/**
	 * Composing a product in top-down fashion
	 */
	public TTester composeProduct() {
		HashMap<String, Variable> varSet = new HashMap<String, Variable>();
		TTester auxTT;
		
		while (!varQueue.isEmpty()) {
			Variable var2process = varQueue.remove(0);
			
//			System.out.println("STEF: "+varQueue.toString());
			
			try {
				if (automataMap.containsKey(var2process.getName())) {									
					
					auxTT =	automataMap.get(var2process.getName());										//if such automata exist
					
					if (!(auxTT.isSingleVarTester())) { //and it is not trivial
						
						product = AutomataService.syncAndCompose(automataMap.get(var2process.getName()), product);
												
						for (Variable variable : automataMap.get(var2process.getName()).getVariables().values()) {
							if (!varSet.containsKey(variable.getName()))
								varQueue.add(variable);

							varSet.put(variable.getName(), variable);
						}
					}
				}
				else 
					System.out.println("key " + var2process.getName() + " not found in the automataMap");
			} catch (InvalidConfigurationException | SolverException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};			
		}

		return product;
	}

	/**
	 * Getter.
	 * @return
	 */
	public TTester getProduct() {
		return product;
	}

}
