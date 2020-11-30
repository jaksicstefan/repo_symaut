package automaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.sosy_lab.common.ShutdownManager;
import org.sosy_lab.common.configuration.Configuration;
import org.sosy_lab.common.configuration.InvalidConfigurationException;
import org.sosy_lab.common.log.BasicLogManager;
import org.sosy_lab.common.log.LogManager;
import org.sosy_lab.java_smt.SolverContextFactory;
import org.sosy_lab.java_smt.SolverContextFactory.Solvers;
import org.sosy_lab.java_smt.api.BooleanFormula;
import org.sosy_lab.java_smt.api.ProverEnvironment;
import org.sosy_lab.java_smt.api.SolverContext;
import org.sosy_lab.java_smt.api.SolverContext.ProverOptions;
import org.sosy_lab.java_smt.api.SolverException;

import constraint.Constraint;
import constraint.ConstraintGenVisitor;
import constraint.ConstraintNode;
import constraint.ConstraintNodeConvertVisitor;
import constraint.ConstraintNode.NodeType;
import cst_gen.CstLexer;
import cst_gen.CstParser;
import gen.StlParser.ExprAlwaysExprContext;
import gen.StlParser.ExprEventuallyContext;
import gen.StlParser.ExprHistoricallyExprContext;
import gen.StlParser.ExprNextExprContext;
import gen.StlParser.ExprOnceExprContext;
import gen.StlParser.ExprPreviousExprContext;
import gen.StlParser.ExprSinceContext;
import gen.StlParser.ExprUntilContext;
import gen.StlParser.IdCompContext;

public class AutomataService {

	/**
	 * HashMap used to implement TemporalTester composition. Each newly generated
	 * state is paired with a name.
	 */
	static HashMap<AutomatonState, String> stateStringMap = new HashMap<AutomatonState, String>();

	/**
	 * HashMap used to implement TemporalTester composition. Mapping a state name
	 * to a AutomatonState object.
	 */
	static HashMap<String, AutomatonState> stringStateMap = new HashMap<String, AutomatonState>();

	/**
	 * HashMap used to implement TemporalTester composition. This map is used to
	 * map a newly generated state from the composition to a pair of the states
	 * from the automata originally involved in the composition.
	 */
	static HashMap<AutomatonState, StatePair> stateMap = new HashMap<AutomatonState, StatePair>();

	/**
	 * HashMap used to implement TemporalTester composition. Mapping inverse to
	 * stateMap.
	 */
	static HashMap<StatePair, AutomatonState> statePairMap = new HashMap<StatePair, AutomatonState>();

	/**
	 * HashMap used to implement TemporalTester composition. This map enables us
	 * to memorize only distinct Constraint objects.
	 */
	static HashMap<String, Constraint> constraintsMap = new HashMap<String, Constraint>();

	/**
	 * Default solver used when creating ConstraintNodeConvertVisitor
	 * 
	 */
	public static Solvers defaultSolver = Solvers.PRINCESS;

	/**
	 * Configuration object used by SMT solver API.
	 */
	private static Configuration config;

	/**
	 * Logger object used by SMT solver API.
	 */
	private static LogManager logger;

	/**
	 * ShutdownManager object used by SMT solver API.
	 */
	private static ShutdownManager shutdown;

	/**
	 * ShutdownManager object used by SMT solver API.
	 */
	private static SolverContext context;

	/**
	 * Prover Environment object for SMT solver API.
	 */
	private static ProverEnvironment prover;

	static {
		// String[] noargs = new String[0];
		config = Configuration.defaultConfiguration(); // TODO:
		try {
			logger = BasicLogManager.create(config);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shutdown = ShutdownManager.create();
		try {
			context = SolverContextFactory.createSolverContext(config, logger, shutdown.getNotifier(), defaultSolver);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		prover = context.newProverEnvironment(ProverOptions.GENERATE_MODELS);
	}

	// -------------------------

	/**
	 * This will create Temporal Tester for Once[a,b] operator, in case a=0.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param outVar
	 * @return
	 */
	public static TTester getBoundedOnceTester(ExprOnceExprContext ctx, Variable inVar0, Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addOutVar(outVar);

		int a = Integer.parseInt(ctx.interval().getChild(1).getText());
		int b = Integer.parseInt(ctx.interval().getChild(3).getText());

		if (a != 0)
			System.err.println("getBoundedOnceTester: a is not 0!");

		int stateArrLen = b + 2;

		AutomatonState stateArr[] = new AutomatonState[stateArrLen];

		for (int i = 0; i < stateArrLen; i++) {
			if (i == 0)
				stateArr[i] = new AutomatonState(true, false); // init non-acc
			else
				stateArr[i] = new AutomatonState(false, true); // acc
			tt.addState(stateArr[i]);
		}

		TransitionAssignment assgn1 = new TransitionAssignment();
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, "" + inVar0.getName() + "");
		strList.add(1, "not(" + inVar0.getName() + ")");

		ArrayList<Constraint> cstList = prepareCstList(strList);

		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));
		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));
		// cstList.get(0) = p
		// cstList.get(1) = not p

		TTesterTransition transArr[] = new TTesterTransition[2 * stateArrLen];

		for (int j = 0; j < stateArr.length - 1; j++) {

			transArr[2 * j] = new TTesterTransition(stateArr[j], stateArr[b + 1], assgn1, cstList.get(0), outVars); // pU

			if (j == stateArr.length - 2) {
				transArr[(2 * j) + 1] = new TTesterTransition(stateArr[j], stateArr[j], assgn1, cstList.get(1), outVarsNeg); // !p!U
			} else {
				if (j == 0)
					transArr[(2 * j) + 1] = new TTesterTransition(stateArr[j], stateArr[b], assgn1, cstList.get(1), outVarsNeg); // !p!U
				else
					transArr[(2 * j) + 1] = new TTesterTransition(stateArr[j], stateArr[j + 1], assgn1, cstList.get(1), outVars); // !pU
			}

			tt.addTransition(transArr[2 * j]);
			tt.addTransition(transArr[2 * j + 1]);
		}

		int k = b + 1;
		transArr[2 * k] = new TTesterTransition(stateArr[k], stateArr[1], assgn1, cstList.get(1), outVars); // !pU
		transArr[2 * k + 1] = new TTesterTransition(stateArr[k], stateArr[k], assgn1, cstList.get(0), outVars); // !p!U

		tt.addTransition(transArr[2 * k]);
		tt.addTransition(transArr[2 * k + 1]);

		return tt;
	}

	/**
	 * This will create Temporal Tester for historically[a,b] operator, in case
	 * a=0.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param outVar
	 * @return
	 */
	public static TTester getBoundedHistoricallyTester(ExprHistoricallyExprContext ctx, Variable inVar0,
			Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addOutVar(outVar);

		int a = Integer.parseInt(ctx.interval().getChild(1).getText());
		int b = Integer.parseInt(ctx.interval().getChild(3).getText());

		if (a != 0)
			System.err.println("getHistoricallyTester:error: a is not 0!");

		int stateArrLen = b + 2;

		AutomatonState stateArr[] = new AutomatonState[stateArrLen];

		for (int i = 0; i < stateArrLen; i++) {
			if (i == 0)
				stateArr[i] = new AutomatonState(true, false); // init non-acc
			else
				stateArr[i] = new AutomatonState(false, true); // acc

			tt.addState(stateArr[i]);
		}

		TransitionAssignment assgn1 = new TransitionAssignment();
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, "" + inVar0.getName() + "");
		strList.add(1, "not(" + inVar0.getName() + ")");

		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));
		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));
		// cstList.get(0) = p
		// cstList.get(1) = not p

		TTesterTransition transArr[] = new TTesterTransition[2 * stateArrLen];

		for (int j = 0; j < stateArr.length - 1; j++) {

			transArr[2 * j] = new TTesterTransition(stateArr[j], stateArr[b + 1], assgn1, cstList.get(1), outVarsNeg); // !p!U

			if (j == stateArr.length - 2) {
				transArr[(2 * j) + 1] = new TTesterTransition(stateArr[j], stateArr[j], assgn1, cstList.get(0), outVars); // pU
			} else {
				if (j == 0)
					transArr[(2 * j) + 1] = new TTesterTransition(stateArr[j], stateArr[b], assgn1, cstList.get(0), outVars); // pU
				else
					transArr[(2 * j) + 1] = new TTesterTransition(stateArr[j], stateArr[j + 1], assgn1, cstList.get(0),
							outVarsNeg); // p!U
			}

			tt.addTransition(transArr[2 * j]);
			tt.addTransition(transArr[2 * j + 1]);
		}

		int k = b + 1;
		transArr[2 * k] = new TTesterTransition(stateArr[k], stateArr[1], assgn1, cstList.get(0), outVarsNeg); // p!U
		transArr[2 * k + 1] = new TTesterTransition(stateArr[k], stateArr[k], assgn1, cstList.get(1), outVarsNeg); // !p!U

		tt.addTransition(transArr[2 * k]);
		tt.addTransition(transArr[2 * k + 1]);

		return tt;
	}

	/**
	 * Creates Temporal Tester for STL Bounded Eventually operator. The lower time
	 * bound must be zero. In other words, this method must not be used for
	 * expressions like eventually[2:3] P . It is assumed that statements like
	 * these are eliminated in pre-processing stage.
	 */
	@SuppressWarnings("unused")
	public static TTester getBoundedEventuallyTester(ExprEventuallyContext ctx, Variable inVar0, Variable outVar) {
		TTester retTester = null;
		resetIDs();

		if (ctx.interval() != null) {
			TransitionAssignment assgn1 = new TransitionAssignment();
			AutomatonState sx = new AutomatonState(false, false); // a dummy state
			resetIDs();

			int a = Integer.parseInt(ctx.interval().getChild(1).getText());
			int b = Integer.parseInt(ctx.interval().getChild(3).getText());

			if (a != 0)
				System.err.println("getBoundedEventuallyTester:: a!=0 is still not supported");

			int totalStatesNum = 5 + 2 * (b - a - 1);
			int totalTransNum = 6 + 2 * (b - a) + (b - a + 1); // 16Trans in the

			retTester = new TTester(false);
			retTester.signalVariability = b - a + 1;

			retTester.addVar(inVar0);
			retTester.addOutVar(outVar);
			AutomatonState stateArr[] = new AutomatonState[totalStatesNum];

			// System.out.println("states num" + totalStatesNum + "trans num" +
			// totalTransNum);

			stateArr[0] = retTester.addState(new AutomatonState(true, true));
			stateArr[1] = retTester.addState(new AutomatonState(false, true));
			stateArr[2] = retTester.addState(new AutomatonState(false, true));

			for (int i = 3; i < stateArr.length; i++) {
				stateArr[i] = retTester.addState(new AutomatonState(false, false));
			}

			ArrayList<TTesterTransition> tttArr = new ArrayList<TTesterTransition>(totalTransNum);

			for (TTesterTransition tTesterTransition : tttArr) {
				tTesterTransition = new TTesterTransition(sx, sx, assgn1, null, null);
			}

			// specify constraints
			ArrayList<String> strList = new ArrayList<String>(2);
			strList.add(0, "" + inVar0.getName() + "");
			strList.add(1, "not(" + inVar0.getName() + ")");
			ArrayList<Constraint> cstList = prepareCstList(strList);

			// create out vars
			ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
			outVars.add(new VariableInstance(outVar, true));
			ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
			outVarsNeg.add(new VariableInstance(outVar, false));

			// cstList.get(0) = p
			// cstList.get(1) = not p

			// transitions //like in the legacy code
			tttArr.add(0, new TTesterTransition(stateArr[0], stateArr[2], assgn1, cstList.get(1), outVarsNeg)); // !p!U
			tttArr.add(1, new TTesterTransition(stateArr[0], stateArr[1], assgn1, cstList.get(0), outVars)); // pU
			tttArr.add(2, new TTesterTransition(stateArr[0], stateArr[3], assgn1, cstList.get(1), outVars)); // !pU

			tttArr.add(3, new TTesterTransition(stateArr[2], stateArr[2], assgn1, cstList.get(1), outVarsNeg)); // !p!U

			tttArr.add(4, new TTesterTransition(stateArr[1], stateArr[2], assgn1, cstList.get(1), outVarsNeg)); // !p!U
			tttArr.add(5, new TTesterTransition(stateArr[1], stateArr[1], assgn1, cstList.get(0), outVars)); // pU
			tttArr.add(6, new TTesterTransition(stateArr[1], stateArr[3], assgn1, cstList.get(1), outVars)); // !pU

			// create transitions
			for (int i = 7; i < 7 + 2 * (b - a) - 1; i++) { // 7,8,9,10
				if (i % 2 == 1) {
					tttArr.add(i, new TTesterTransition(sx, sx, assgn1, cstList.get(0), outVars)); // pU
				} else {
					tttArr.add(i, new TTesterTransition(sx, sx, assgn1, cstList.get(1), outVars)); // !pU
				}
			}

			for (int i = 7 + 2 * (b - a) - 1; i < totalTransNum; i++) { // 11,12,13
				if (i == tttArr.size() - 1) { // the last one in array
					tttArr.add(i, new TTesterTransition(sx, sx, assgn1, cstList.get(0), outVars)); // p/u
				} else {
					tttArr.add(i, new TTesterTransition(sx, sx, assgn1, cstList.get(1), outVars)); // !p/u
				}
			}

			// ----------- repatch states for transitions
			for (int i = 3; i < 3 + (b - a); i++) { // for states 3,4,5
				tttArr.get((2 * i) + 1).setSrcState(stateArr[i]);
				tttArr.get((2 * i) + 1).setDstState(stateArr[1]);

				if (i < (3 + (b - a) - 1)) {
					tttArr.get((2 * i) + 2).setSrcState(stateArr[i]);
					tttArr.get((2 * i) + 2).setDstState(stateArr[i + 1]);
				}
			}

			int lastTransitionNum = 2 * (3 + (b - a));

			for (int i = 3 + (b - a); i < stateArr.length; i++) { // for states 6,7,8

				if (i == (3 + (b - a))) {
					// auto.addTransition(new Transition(stArr[2],
					// altArr[lastTransitionNum++], stArr[i]));
					tttArr.get(lastTransitionNum).setSrcState(stateArr[2]);
					tttArr.get(lastTransitionNum).setDstState(stateArr[i]);
					lastTransitionNum++;
				} else {
					// auto.addTransition(new Transition(stArr[i - 1],
					// altArr[lastTransitionNum++], stArr[i]));
					tttArr.get(lastTransitionNum).setSrcState(stateArr[i - 1]);
					tttArr.get(lastTransitionNum).setDstState(stateArr[i]);
					lastTransitionNum++;
				}

				if (i == (stateArr.length - 1)) { // additional transition
					// auto.addTransition(new Transition(stArr[i],
					// altArr[lastTransitionNum], stArr[1]));
					tttArr.get(lastTransitionNum).setSrcState(stateArr[i]); //i think we have a bug here for eventually[0,2] p, this transitions is neg p / u and should be p/u  
					tttArr.get(lastTransitionNum).setDstState(stateArr[1]);
				}
			}

			for (TTesterTransition ttt : tttArr) {
				retTester.addTransition(ttt);
			}
		} else {
			System.err.println("getBoundedEventuallyTester: no interval found!");
			retTester = null;
		}
		System.out.println("getBoundedEventuallyTester returns: \n" + retTester.toString());		
		return retTester;
	}

	/**
	 * This will create Temporal Tester for always[a,b] operator, in case a=0.
	 * 
	 * @param ctx
	 * @param inVar1
	 * @param outVar
	 * @return
	 */
	@SuppressWarnings("unused")
	public static TTester getBoundedAlwaysTester(ExprAlwaysExprContext ctx, Variable inVar0, Variable outVar) {
		TTester retTester = null;
		resetIDs();

		if (ctx.interval() != null) {
			TransitionAssignment assgn1 = new TransitionAssignment();
			AutomatonState sx = new AutomatonState(false, false); // a dummy state
			sx.setId(-1);
			resetIDs();

			int a = Integer.parseInt(ctx.interval().getChild(1).getText());
			int b = Integer.parseInt(ctx.interval().getChild(3).getText());

			if (a != 0)
				System.err.println("getBoundedAlwaysTester:: a!=0 is still not supported");

			int totalStatesNum = 5 + 2 * (b - a - 1);
			int totalTransNum = 6 + 2 * (b - a) + (b - a + 1); // 16Trans in the

			retTester = new TTester(false);
			retTester.addVar(inVar0);
			retTester.addOutVar(outVar);
			AutomatonState stateArr[] = new AutomatonState[totalStatesNum];

			// System.out.println("states num" + totalStatesNum + "trans num" +
			// totalTransNum);

			stateArr[0] = retTester.addState(new AutomatonState(true, true));
			stateArr[1] = retTester.addState(new AutomatonState(false, true));
			stateArr[2] = retTester.addState(new AutomatonState(false, true));

			for (int i = 3; i < stateArr.length; i++) {
				stateArr[i] = retTester.addState(new AutomatonState(false, false));
			}

			ArrayList<TTesterTransition> tttArr = new ArrayList<TTesterTransition>(totalTransNum);

			for (TTesterTransition tTesterTransition : tttArr) {
				tTesterTransition = new TTesterTransition(sx, sx, assgn1, null, null);
			}

			// specify constraints
			ArrayList<String> strList = new ArrayList<String>(2);
			strList.add(0, "" + inVar0.getName() + "");
			strList.add(1, "not(" + inVar0.getName() + ")");
			ArrayList<Constraint> cstList = prepareCstList(strList);

			// create out vars
			ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
			outVars.add(new VariableInstance(outVar, true));
			ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
			outVarsNeg.add(new VariableInstance(outVar, false));

			// cstList.get(0) = p
			// cstList.get(1) = not p

			// transitions //like in the legacy code
			tttArr.add(0, new TTesterTransition(stateArr[0], stateArr[2], assgn1, cstList.get(0), outVars)); // pU
			tttArr.add(1, new TTesterTransition(stateArr[0], stateArr[1], assgn1, cstList.get(1), outVarsNeg)); // !p!U
			tttArr.add(2, new TTesterTransition(stateArr[0], stateArr[3], assgn1, cstList.get(0), outVarsNeg)); // p!U

			tttArr.add(3, new TTesterTransition(stateArr[2], stateArr[2], assgn1, cstList.get(0), outVars)); // pU

			tttArr.add(4, new TTesterTransition(stateArr[1], stateArr[2], assgn1, cstList.get(0), outVars)); // pU
			tttArr.add(5, new TTesterTransition(stateArr[1], stateArr[1], assgn1, cstList.get(1), outVarsNeg)); // !p!U
			tttArr.add(6, new TTesterTransition(stateArr[1], stateArr[3], assgn1, cstList.get(0), outVarsNeg)); // p!U

			// create transitions
			for (int i = 7; i < 7 + 2 * (b - a) - 1; i++) { // 7,8,9,10
				if (i % 2 == 1) {
					tttArr.add(i, new TTesterTransition(sx, sx, assgn1, cstList.get(1), outVarsNeg)); // !p!U
				} else {
					tttArr.add(i, new TTesterTransition(sx, sx, assgn1, cstList.get(0), outVarsNeg)); // p!U
				}
			}

			for (int i = 7 + 2 * (b - a) - 1; i < totalTransNum; i++) { // 11,12,13
				if (i == totalTransNum - 1) { // the last one in array
					tttArr.add(i, new TTesterTransition(sx, sx, assgn1, cstList.get(1), outVarsNeg)); // !p/!U
				} else {
					tttArr.add(i, new TTesterTransition(sx, sx, assgn1, cstList.get(0), outVarsNeg)); // p/!U
				}
			}

			// ----------- repatch states for transitions
			for (int i = 3; i < 3 + (b - a); i++) { // for states 3,4,5
				tttArr.get((2 * i) + 1).setSrcState(stateArr[i]);
				tttArr.get((2 * i) + 1).setDstState(stateArr[1]);

				if (i < (3 + (b - a) - 1)) {
					tttArr.get((2 * i) + 2).setSrcState(stateArr[i]);
					tttArr.get((2 * i) + 2).setDstState(stateArr[i + 1]);
				}
			}

			int lastTransitionNum = 2 * (3 + (b - a));

			for (int i = 3 + (b - a); i < stateArr.length; i++) { // for states 6,7,8

				if (i == (3 + (b - a))) {
					// auto.addTransition(new Transition(stArr[2],
					// altArr[lastTransitionNum++], stArr[i]));
					tttArr.get(lastTransitionNum).setSrcState(stateArr[2]);
					tttArr.get(lastTransitionNum).setDstState(stateArr[i]);
					lastTransitionNum++;
				} else {
					// auto.addTransition(new Transition(stArr[i - 1],
					// altArr[lastTransitionNum++], stArr[i]));
					tttArr.get(lastTransitionNum).setSrcState(stateArr[i - 1]);
					tttArr.get(lastTransitionNum).setDstState(stateArr[i]);
					lastTransitionNum++;
				}

				if (i == (stateArr.length - 1)) { // additional transition
					// auto.addTransition(new Transition(stArr[i],
					// altArr[lastTransitionNum], stArr[1]));
					tttArr.get(lastTransitionNum).setSrcState(stateArr[i]);
					tttArr.get(lastTransitionNum).setDstState(stateArr[1]);
				}
			}

			for (TTesterTransition ttt : tttArr) {
				retTester.addTransition(ttt);
			}
		} else {
			System.err.println("getBoundedAlwaysTester: no interval found!");
			retTester = null;
		}

		return retTester;
	}

	/**
	 * Obtaining basic automata for since operator.
	 * 
	 * @param ctx
	 * @param inVar0
	 *          Tester input variable \#1
	 * @param inVar1
	 *          Tester input variable \#2
	 * @param outVar
	 *          Tester output variable
	 * @return
	 */
	// TODO - why exactly is CTX necessary as a function parameter?
	public static TTester getUnboundedSinceTester(ExprSinceContext ctx, Variable inVar0, Variable inVar1,
			Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision

		tt.addVar(inVar0);
		tt.addVar(inVar1);
		tt.addOutVar(outVar);

		AutomatonState s0 = new AutomatonState(true, true);
		AutomatonState s1 = new AutomatonState(false, true);

		tt.addState(s0);
		tt.addState(s1);

		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(4);
		strList.add(0, "not(" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(1, "not(" + inVar0.getName() + ") and    (" + inVar1.getName() + ")");
		strList.add(2, "   (" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(3, "   (" + inVar0.getName() + ") and 	 (" + inVar1.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = not p not q
		// cstList.get(1) = not p q
		// cstList.get(2) = p not q
		// cstList.get(3) = p q

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s0, assgn1, cstList.get(2), outVarsNeg);
		TTesterTransition t1 = new TTesterTransition(s0, s0, assgn1, cstList.get(0), outVarsNeg);
		TTesterTransition t2 = new TTesterTransition(s0, s1, assgn1, cstList.get(3), outVars);
		TTesterTransition t3 = new TTesterTransition(s0, s1, assgn1, cstList.get(1), outVars);

		TTesterTransition t4 = new TTesterTransition(s1, s1, assgn1, cstList.get(3), outVars);
		TTesterTransition t5 = new TTesterTransition(s1, s1, assgn1, cstList.get(2), outVars);
		TTesterTransition t6 = new TTesterTransition(s1, s1, assgn1, cstList.get(1), outVars);
		TTesterTransition t7 = new TTesterTransition(s1, s0, assgn1, cstList.get(0), outVarsNeg);

		tt.addTransition(t0);
		tt.addTransition(t1);
		tt.addTransition(t2);
		tt.addTransition(t3);
		tt.addTransition(t4);
		tt.addTransition(t5);
		tt.addTransition(t6);
		tt.addTransition(t7);

		return tt;
	}

	/**
	 * Creates Temporal Tester for STL Unbounded Always operator.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param outVar
	 * @return
	 */
	// TODO - why exactly is CTX necessary as a function parameter?
	public static TTester getUnboundedAlwaysTester(ExprAlwaysExprContext ctx, Variable inVar0, Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addOutVar(outVar);

		AutomatonState s0 = new AutomatonState(true, true);
		AutomatonState s1 = new AutomatonState(false, true);
		AutomatonState s2 = new AutomatonState(false, false);
		AutomatonState s3 = new AutomatonState(false, true);
		tt.addState(s0);
		tt.addState(s1);
		tt.addState(s2);
		tt.addState(s3);
		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, "" + inVar0.getName() + "");
		strList.add(1, "not(" + inVar0.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = p
		// cstList.get(1) = not p

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s1, assgn1, cstList.get(0), outVars);
		TTesterTransition t1 = new TTesterTransition(s0, s2, assgn1, cstList.get(0), outVarsNeg);
		TTesterTransition t2 = new TTesterTransition(s0, s3, assgn1, cstList.get(1), outVarsNeg);

		TTesterTransition t3 = new TTesterTransition(s1, s1, assgn1, cstList.get(0), outVars);

		TTesterTransition t4 = new TTesterTransition(s2, s2, assgn1, cstList.get(0), outVarsNeg);
		TTesterTransition t5 = new TTesterTransition(s3, s2, assgn1, cstList.get(0), outVarsNeg);
		TTesterTransition t6 = new TTesterTransition(s3, s3, assgn1, cstList.get(1), outVarsNeg);
		TTesterTransition t7 = new TTesterTransition(s2, s3, assgn1, cstList.get(1), outVarsNeg);

		TTesterTransition t8 = new TTesterTransition(s3, s1, assgn1, cstList.get(0), outVars);

		tt.addTransition(t0);
		tt.addTransition(t1);
		tt.addTransition(t2);
		tt.addTransition(t3);
		tt.addTransition(t4);
		tt.addTransition(t5);
		tt.addTransition(t6);
		tt.addTransition(t7);
		tt.addTransition(t8);

		return tt;
	}

	/**
	 * Creates Temporal Tester for STL Unbounded Eventually operator.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param outVar
	 * @return
	 */
	// TODO - why exactly is CTX necessary as a function parameter?
	public static TTester getUnboundedEventuallyTester(ExprEventuallyContext ctx, Variable inVar0, Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addOutVar(outVar);

		AutomatonState s0 = new AutomatonState(true, true);
		AutomatonState s1 = new AutomatonState(false, true);
		AutomatonState s2 = new AutomatonState(false, false);
		AutomatonState s3 = new AutomatonState(false, true);

		tt.addState(s0);
		tt.addState(s1);
		tt.addState(s2);
		tt.addState(s3);

		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, "" + inVar0.getName() + "");
		strList.add(1, "not(" + inVar0.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = p
		// cstList.get(1) = not p

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s1, assgn1, cstList.get(1), outVarsNeg);
		TTesterTransition t1 = new TTesterTransition(s0, s2, assgn1, cstList.get(1), outVars);
		TTesterTransition t2 = new TTesterTransition(s0, s3, assgn1, cstList.get(0), outVars);

		TTesterTransition t3 = new TTesterTransition(s1, s1, assgn1, cstList.get(1), outVarsNeg);

		TTesterTransition t4 = new TTesterTransition(s2, s2, assgn1, cstList.get(1), outVars);
		TTesterTransition t5 = new TTesterTransition(s3, s2, assgn1, cstList.get(1), outVars);
		TTesterTransition t6 = new TTesterTransition(s3, s3, assgn1, cstList.get(0), outVars);
		TTesterTransition t7 = new TTesterTransition(s2, s3, assgn1, cstList.get(0), outVars);

		TTesterTransition t8 = new TTesterTransition(s3, s1, assgn1, cstList.get(1), outVarsNeg);

		tt.addTransition(t0);
		tt.addTransition(t1);
		tt.addTransition(t2);
		tt.addTransition(t3);
		tt.addTransition(t4);
		tt.addTransition(t5);
		tt.addTransition(t6);
		tt.addTransition(t7);
		tt.addTransition(t8);

		return tt;
	}

	/**
	 * Creates Temporal Tester for STL Unbounded Until operator.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param inVar1
	 * @param outVar
	 * @return
	 */
	// TODO - why exactly is CTX necessary as a function parameter?
	public static TTester getUnboundedUntilTester(ExprUntilContext ctx, Variable inVar0, Variable inVar1,
			Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addVar(inVar1);
		tt.addOutVar(outVar);

		AutomatonState s0 = new AutomatonState(true, true); // acc
		AutomatonState s1 = new AutomatonState(false, false); // non-acc
		AutomatonState s2 = new AutomatonState(false, true); // acc
		AutomatonState s3 = new AutomatonState(false, true); // acc
		AutomatonState s4 = new AutomatonState(false, true); // acc
		tt.addState(s0);
		tt.addState(s1);
		tt.addState(s2);
		tt.addState(s3);
		tt.addState(s4);
		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(5);
		strList.add(0, "not(" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(1, "not(" + inVar0.getName() + ") and    (" + inVar1.getName() + ")");
		strList.add(2, "   (" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(3, "   (" + inVar0.getName() + ") and 	 (" + inVar1.getName() + ")");
		strList.add(4, "" + inVar1.getName());
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = not p not q
		// cstList.get(1) = not p q
		// cstList.get(2) = p not q
		// cstList.get(3) = p q
		// cstList.get(4) = q

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s1, assgn1, cstList.get(2), outVars); // p!qU
		TTesterTransition t1 = new TTesterTransition(s0, s2, assgn1, cstList.get(4), outVars); // qU
		TTesterTransition t2 = new TTesterTransition(s0, s3, assgn1, cstList.get(0), outVarsNeg); // !p!q!U
		TTesterTransition t3 = new TTesterTransition(s0, s4, assgn1, cstList.get(2), outVarsNeg); // p!q!U

		TTesterTransition t4 = new TTesterTransition(s1, s1, assgn1, cstList.get(2), outVars); // p!qU
		TTesterTransition t5 = new TTesterTransition(s2, s1, assgn1, cstList.get(2), outVars); // p!qU
		TTesterTransition t6 = new TTesterTransition(s2, s3, assgn1, cstList.get(0), outVarsNeg); // !p!q!U
		TTesterTransition t7 = new TTesterTransition(s3, s3, assgn1, cstList.get(0), outVarsNeg); // !p!q!U

		TTesterTransition t8 = new TTesterTransition(s4, s3, assgn1, cstList.get(0), outVarsNeg); // !p!q!U
		TTesterTransition t9 = new TTesterTransition(s1, s2, assgn1, cstList.get(4), outVars); // !qU

		TTesterTransition t10 = new TTesterTransition(s2, s2, assgn1, cstList.get(4), outVars); // qU
		TTesterTransition t11 = new TTesterTransition(s3, s2, assgn1, cstList.get(4), outVars); // qU
		TTesterTransition t12 = new TTesterTransition(s3, s4, assgn1, cstList.get(2), outVarsNeg); // p!q!U
		TTesterTransition t13 = new TTesterTransition(s4, s4, assgn1, cstList.get(2), outVarsNeg); // p!q!U
		TTesterTransition t14 = new TTesterTransition(s3, s1, assgn1, cstList.get(2), outVars); // p!qU
		TTesterTransition t15 = new TTesterTransition(s2, s4, assgn1, cstList.get(2), outVarsNeg); // p!q!U

		tt.addTransition(t0);
		tt.addTransition(t1);
		tt.addTransition(t2);
		tt.addTransition(t3);
		tt.addTransition(t4);
		tt.addTransition(t5);
		tt.addTransition(t6);
		tt.addTransition(t7);
		tt.addTransition(t8);

		tt.addTransition(t9);
		tt.addTransition(t10);
		tt.addTransition(t11);
		tt.addTransition(t12);
		tt.addTransition(t13);
		tt.addTransition(t14);
		tt.addTransition(t15);

		return tt;
	}

	/**
	 * Creates Temporal Tester for STL Next operator.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param outVar
	 * @return
	 */
	// TODO - why exactly is CTX necessary as a function parameter?
	public static TTester getNextTester(ExprNextExprContext ctx, Variable inVar0, Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addOutVar(outVar);

		AutomatonState s0 = new AutomatonState(true, true);// init acc
		AutomatonState s1 = new AutomatonState(false, false); // acc
		AutomatonState s2 = new AutomatonState(false, true); // acc

		tt.addState(s0); // init acc
		tt.addState(s1); // acc
		tt.addState(s2); // acc

		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, "" + inVar0.getName() + "");
		strList.add(1, "not(" + inVar0.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		Constraint cstTrue = getConstraintTrue();

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = p
		// cstList.get(1) = not p

		// transitions

		TTesterTransition t0_1 = new TTesterTransition(s0, s1, assgn1, cstTrue, outVars); // TRUE,U
		// TTesterTransition t0 = new TTesterTransition(s0, s1, assgn1,
		// cstList.get(0), outVars); // pU
		// TTesterTransition t1 = new TTesterTransition(s0, s1, assgn1,
		// cstList.get(1), outVars); // !pU

		TTesterTransition t2_3 = new TTesterTransition(s0, s2, assgn1, cstTrue, outVarsNeg); // TRUE,!U
		// TTesterTransition t2 = new TTesterTransition(s0, s2, assgn1,
		// cstList.get(0), outVarsNeg); // p!U
		// TTesterTransition t3 = new TTesterTransition(s0, s2, assgn1,
		// cstList.get(1), outVarsNeg); // !p!U

		TTesterTransition t4 = new TTesterTransition(s1, s1, assgn1, cstList.get(0), outVars); // pU
		TTesterTransition t5 = new TTesterTransition(s2, s1, assgn1, cstList.get(1), outVars); // !pU
		TTesterTransition t6 = new TTesterTransition(s2, s2, assgn1, cstList.get(1), outVarsNeg); // !p!U
		TTesterTransition t7 = new TTesterTransition(s1, s2, assgn1, cstList.get(0), outVarsNeg); // p!U

		// tt.addTransition(t0);
		// tt.addTransition(t1);
		tt.addTransition(t0_1);

		// tt.addTransition(t2);
		// tt.addTransition(t3);
		tt.addTransition(t2_3);

		tt.addTransition(t4);
		tt.addTransition(t5);
		tt.addTransition(t6);
		tt.addTransition(t7);

		return tt;
	}

	/**
	 * Returns a simple constraint for Boolean constant value TRUE.
	 * 
	 * @return
	 */
	private static Constraint getConstraintTrue() {

		ConstraintNode root = new ConstraintNode(NodeType.LEAF_BOOL, "TRUE");
		Constraint cst2ret = new Constraint(root);
		return cst2ret;
	}

	/**
	 * Creates Temporal Tester for STL Prev operator.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param outVar
	 * @return
	 */
	// TODO - why exactly is CTX necessary as a function parameter?
	public static TTester getPrevTester(ExprPreviousExprContext ctx, Variable inVar0, Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addOutVar(outVar);

		AutomatonState s0 = new AutomatonState(true, true); // init acc
		AutomatonState s1 = new AutomatonState(false, false); // acc
		AutomatonState s2 = new AutomatonState(false, true); // acc

		tt.addState(s0);
		tt.addState(s1);
		tt.addState(s2);

		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, "" + inVar0.getName() + "");
		strList.add(1, "not(" + inVar0.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = p
		// cstList.get(1) = not p

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s1, assgn1, cstList.get(1), outVarsNeg); // !p!U
		TTesterTransition t1 = new TTesterTransition(s0, s2, assgn1, cstList.get(0), outVarsNeg); // p!U

		TTesterTransition t2 = new TTesterTransition(s1, s1, assgn1, cstList.get(1), outVarsNeg); // !p!U
		TTesterTransition t3 = new TTesterTransition(s2, s2, assgn1, cstList.get(0), outVars); // pU

		TTesterTransition t4 = new TTesterTransition(s1, s1, assgn1, cstList.get(1), outVars); // !pU
		TTesterTransition t5 = new TTesterTransition(s2, s1, assgn1, cstList.get(0), outVarsNeg); // p!U

		tt.addTransition(t0);
		tt.addTransition(t1);
		tt.addTransition(t2);
		tt.addTransition(t3);
		tt.addTransition(t4);
		tt.addTransition(t5);

		return tt;
	}

	/**
	 * Creates Temporal Tester for testing a single-variable formulas.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param outVar
	 * @return
	 */
	// TODO - why is ctx variable necessary here?
	public static TTester getVariableTester(Variable inVar0, Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addOutVar(outVar);

		AutomatonState s0 = new AutomatonState(true, true); // init acc
		tt.addState(s0);
		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, "" + inVar0.getName() + "");
		strList.add(1, "not(" + inVar0.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = p
		// cstList.get(1) = not p

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s0, assgn1, cstList.get(1), outVarsNeg); // !p
																																															// !U
		TTesterTransition t1 = new TTesterTransition(s0, s0, assgn1, cstList.get(0), outVars); // p
																																														// U
		tt.addTransition(t0);
		tt.addTransition(t1);
		return tt;
	}

	/**
	 * Obtain Temporal Tester for AND logical operator.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param inVar1
	 * @param outVar
	 * @return
	 */
	public static TTester getAndTester(Variable inVar0, Variable inVar1, Variable outVar) {
		resetIDs();
		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision

		tt.addVar(inVar0);
		tt.addVar(inVar1);
		tt.addOutVar(outVar);
		AutomatonState s0 = new AutomatonState(true, true);

		tt.addState(s0);
		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(4);
		strList.add(0, "not(" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(1, "not(" + inVar0.getName() + ") and    (" + inVar1.getName() + ")");
		strList.add(2, "   (" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(3, "   (" + inVar0.getName() + ") and 	 (" + inVar1.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = not p not q
		// cstList.get(1) = not p q
		// cstList.get(2) = p not q
		// cstList.get(3) = p q

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s0, assgn1, cstList.get(0), outVarsNeg);
		TTesterTransition t1 = new TTesterTransition(s0, s0, assgn1, cstList.get(1), outVarsNeg);
		TTesterTransition t2 = new TTesterTransition(s0, s0, assgn1, cstList.get(2), outVarsNeg);
		TTesterTransition t3 = new TTesterTransition(s0, s0, assgn1, cstList.get(3), outVars);

		tt.addTransition(t0);
		tt.addTransition(t1);
		tt.addTransition(t2);
		tt.addTransition(t3);
		return tt;
	}

	/**
	 * Obtain Temporal Tester for XOR logical operator.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param inVar1
	 * @param outVar
	 * @return
	 */
	public static TTester getXorTester(Variable inVar0, Variable inVar1, Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addVar(inVar1);
		tt.addOutVar(outVar);
		AutomatonState s0 = new AutomatonState(true, true);

		tt.addState(s0);
		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(4);
		strList.add(0, "not(" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(1, "not(" + inVar0.getName() + ") and    (" + inVar1.getName() + ")");
		strList.add(2, "   (" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(3, "   (" + inVar0.getName() + ") and 	 (" + inVar1.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = not p not q
		// cstList.get(1) = not p q
		// cstList.get(2) = p not q
		// cstList.get(3) = p q

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s0, assgn1, cstList.get(0), outVarsNeg);
		TTesterTransition t1 = new TTesterTransition(s0, s0, assgn1, cstList.get(1), outVars);
		TTesterTransition t2 = new TTesterTransition(s0, s0, assgn1, cstList.get(2), outVars);
		TTesterTransition t3 = new TTesterTransition(s0, s0, assgn1, cstList.get(3), outVarsNeg);

		tt.addTransition(t0);
		tt.addTransition(t1);
		tt.addTransition(t2);
		tt.addTransition(t3);
		return tt;
	}

	/**
	 * Obtain Temporal Tester for IFF logical operator.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param inVar1
	 * @param outVar
	 * @return
	 */
	public static TTester getIffTester(Variable inVar0, Variable inVar1, Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addVar(inVar1);
		tt.addOutVar(outVar);
		AutomatonState s0 = new AutomatonState(true, true);

		tt.addState(s0);
		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(4);
		strList.add(0, "not(" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(1, "not(" + inVar0.getName() + ") and    (" + inVar1.getName() + ")");
		strList.add(2, "   (" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(3, "   (" + inVar0.getName() + ") and 	 (" + inVar1.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = not p not q
		// cstList.get(1) = not p q
		// cstList.get(2) = p not q
		// cstList.get(3) = p q

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s0, assgn1, cstList.get(0), outVars);
		TTesterTransition t1 = new TTesterTransition(s0, s0, assgn1, cstList.get(1), outVarsNeg);
		TTesterTransition t2 = new TTesterTransition(s0, s0, assgn1, cstList.get(2), outVarsNeg);
		TTesterTransition t3 = new TTesterTransition(s0, s0, assgn1, cstList.get(3), outVars);

		tt.addTransition(t0);
		tt.addTransition(t1);
		tt.addTransition(t2);
		tt.addTransition(t3);
		return tt;
	}

	/**
	 * Obtain Temporal Tester for AND logical operator.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param inVar1
	 * @param outVar
	 * @return
	 */
	public static TTester getOrTester(Variable inVar0, Variable inVar1, Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addVar(inVar1);
		tt.addOutVar(outVar);
		AutomatonState s0 = new AutomatonState(true, true);

		tt.addState(s0);
		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(4);
		strList.add(0, "not(" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(1, "not(" + inVar0.getName() + ") and    (" + inVar1.getName() + ")");
		strList.add(2, "   (" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(3, "   (" + inVar0.getName() + ") and 	 (" + inVar1.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = not p not q
		// cstList.get(1) = not p q
		// cstList.get(2) = p not q
		// cstList.get(3) = p q

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s0, assgn1, cstList.get(0), outVarsNeg);
		TTesterTransition t1 = new TTesterTransition(s0, s0, assgn1, cstList.get(1), outVars);
		TTesterTransition t2 = new TTesterTransition(s0, s0, assgn1, cstList.get(2), outVars);
		TTesterTransition t3 = new TTesterTransition(s0, s0, assgn1, cstList.get(3), outVars);

		tt.addTransition(t0);
		tt.addTransition(t1);
		tt.addTransition(t2);
		tt.addTransition(t3);
		return tt;
	}

	/**
	 * Obtain Temporal Tester for IMPLICATION logical operator.
	 * 
	 * @param ctx
	 * @param inVar1
	 * @param inVar2
	 * @param outVar
	 * @return
	 */

	public static TTester getImpliesTester(Variable inVar0, Variable inVar1, Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addVar(inVar1);
		tt.addOutVar(outVar);
		AutomatonState s0 = new AutomatonState(true, true);

		tt.addState(s0);
		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(4);
		strList.add(0, "not(" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(1, "not(" + inVar0.getName() + ") and    (" + inVar1.getName() + ")");
		strList.add(2, "   (" + inVar0.getName() + ") and not(" + inVar1.getName() + ")");
		strList.add(3, "   (" + inVar0.getName() + ") and 	 (" + inVar1.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = not p not q
		// cstList.get(1) = not p q
		// cstList.get(2) = p not q
		// cstList.get(3) = p q

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s0, assgn1, cstList.get(0), outVars);
		TTesterTransition t1 = new TTesterTransition(s0, s0, assgn1, cstList.get(1), outVars);
		TTesterTransition t2 = new TTesterTransition(s0, s0, assgn1, cstList.get(2), outVarsNeg);
		TTesterTransition t3 = new TTesterTransition(s0, s0, assgn1, cstList.get(3), outVars);

		tt.addTransition(t0);
		tt.addTransition(t1);
		tt.addTransition(t2);
		tt.addTransition(t3);
		return tt;
	}

	/**
	 * Obtain Temporal Tester for NOT logical operator.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param outVar
	 * @return
	 */
	public static TTester getNotTester(Variable inVar0, Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addOutVar(outVar);

		AutomatonState s0 = new AutomatonState(true, true); // init acc
		tt.addState(s0);
		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, "" + inVar0.getName() + "");
		strList.add(1, "not(" + inVar0.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = p
		// cstList.get(1) = not p

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s0, assgn1, cstList.get(1), outVars); // !p
																																														// !U
		TTesterTransition t1 = new TTesterTransition(s0, s0, assgn1, cstList.get(0), outVarsNeg); // p
																																															// U
		tt.addTransition(t0);
		tt.addTransition(t1);
		return tt;
	}

	// ----------------------------------------------------------------------

	/**
	 * This function determines if 2 temporal testers can be synchronized.
	 *
	 * By definition, the two temporal testers can be synchronized if there is at
	 * least one output variable in tt1 which is an input variable for tt2.
	 */
	public static Variable getSyncVar(TTester tt1, TTester tt2) {
		Variable v2ret = null;

		// ArrayList<Variable> hashOutVar1 = (ArrayList<Variable>)
		// tt1.getOutVariables().values();
		// ArrayList<Variable> hashInVar2 = (ArrayList<Variable>)
		// tt2.getVariables().values();

		Variable[] hashOutVar1 = tt1.getOutVariables().values().toArray(new Variable[0]);
		Variable[] hashInVar2 = tt2.getVariables().values().toArray(new Variable[0]);

		for (Variable varOut : hashOutVar1) {
			for (Variable varIn : hashInVar2) {
				if (varOut.getName().equals(varIn.getName())) {
					v2ret = varOut;
					break;
				}
			}
		}

		return v2ret;
	}

	/**
	 * Transforms a set of textual constraints into Constraint objects.
	 * 
	 * @param strList
	 * @return
	 */
	public static ArrayList<Constraint> prepareCstList(ArrayList<String> strList) {
		CstLexer lexer = new CstLexer(CharStreams.fromString(""));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CstParser parser = new CstParser(tokens);
		ParseTree tree;
		ConstraintGenVisitor visitor = new ConstraintGenVisitor();
		ArrayList<Constraint> cstList = new ArrayList<Constraint>(strList.size());

		int i = 0;
		for (String str : strList) {
			lexer.reset();
			lexer.setInputStream(CharStreams.fromString(str));
			tokens = new CommonTokenStream(lexer);
			parser.setTokenStream(tokens);

			tree = parser.cst();
			cstList.add(i, visitor.visit(tree));
			i++;
		}

		return cstList;
	}

	/**
	 * This function will be used to synchronize the output of one temporal tester
	 * with the input of another one and compose product which is also a temporal
	 * tester.
	 * 
	 * TT1 output is synchronized with TT2 input.
	 * 
	 * @param tt1
	 * @param tt2
	 * @return
	 * @throws InterruptedException
	 * @throws SolverException
	 * @throws InvalidConfigurationException
	 */
	public static TTester syncAndCompose(TTester tt1, TTester tt2)
			throws InvalidConfigurationException, SolverException, InterruptedException {
		resetIDs();

		System.out.println("\n\n >>> Composing tt1 and tt2: \n" + tt1.states.size() + "x" + tt2.states.size()
				+ " states and " + tt1.transitions.size() + "x" + tt2.transitions.size() + " transitions");

		System.out
				.println("composing tt1 {inVars, outVar}: {" + tt1.variables.values() + "," + tt1.getSingleOutputVar() + "}");
		System.out
				.println("     with tt2 {inVars, outVar}: {" + tt2.variables.values() + "," + tt2.getSingleOutputVar() + "}");
		// System.out.println("TT1 " + tt1);
		// System.out.println("TT2 " + tt2);

		TTester composition = new TTester(false);
		TransitionAssignment assgn = new TransitionAssignment();

		AutomatonState currentState, initState = null;

		stateStringMap.clear();
		stringStateMap.clear();
		stateMap.clear();
		statePairMap.clear();
		constraintsMap.clear();

		HashSet<String> setTransString = new HashSet<String>();

		Stack<AutomatonState> stateStack = new Stack<AutomatonState>();

		AutomatonState dst1 = null;
		AutomatonState dst2 = null;

		HashMap<String, Variable> candidateInVars1 = new HashMap<String, Variable>(tt1.getVariables());
		HashMap<String, Variable> candidateInVars2 = new HashMap<String, Variable>(tt2.getVariables());

		if (getSyncVar(tt1, tt2) == null) {
			System.err.println("TT1 cannot be synchronized with TT2, there is no shared variable!");
			return null;
		}

		// - add variables to composition, omitting the sync Var

		// candidateInVars1.remove(getSyncVar(tt1, tt2).getName());
		candidateInVars2.remove(getSyncVar(tt1, tt2).getName());

		// TODO - in case inVar == outVar , then return the removed VAR

		HashMap<String, Variable> newVars = new HashMap<String, Variable>();

		for (Variable var : candidateInVars1.values()) {
			newVars.put(var.toString(), var);
		}

		for (Variable var : candidateInVars2.values()) {
			newVars.put(var.toString(), var);
		}

		composition.setVariables(newVars);
		composition.setOutVariables(tt2.getOutVariables());

		// ---- init ---------------------------------------------
		for (AutomatonState s1 : tt1.initStates) {
			for (AutomatonState s2 : tt2.initStates) {
				initState = composition
						.addState(new AutomatonState(true, tt1.acceptingStates.contains(s1) && tt2.acceptingStates.contains(s2)));
				StatePair aux_pair = new StatePair(s1, s2);

				stateMap.put(initState, aux_pair);
				statePairMap.put(aux_pair, initState);
				stateStringMap.put(initState, s1.toString() + " " + s2.toString());
				stringStateMap.put(s1.toString() + " " + s2.toString(), initState);
			}
		}
		// -----------------------------------------------------------

		// TODO - zasto nije ovaj statement u petlji? Zasto se ne dodaju SVA init
		// stanja?
		stateStack.push(initState);

		while (!stateStack.isEmpty()) {

			System.out.append('.');

			currentState = stateStack.pop();
			/* System.out.println("processing current state S" + currentState); */
			StatePair s_pair = stateMap.get(currentState);
			String stringPair = stateStringMap.get(currentState);

			ArrayList<AutomatonTransition> deltaIter1 = s_pair.s1.exitingTrans;
			ArrayList<AutomatonTransition> deltaIter2 = s_pair.s2.exitingTrans;

			dst1 = null;
			dst2 = null;
			VariableInstance syncVar;

			for (AutomatonTransition aTrans1 : deltaIter1) {
				syncVar = ((TTesterTransition) aTrans1).outVars
						.get(0); /* TODO -assumed we have a single */

				for (AutomatonTransition aTrans2 : deltaIter2) {
					// sve input promenljive koje su u aTrans2 a nisu navedene na
					// constrainty se smatra da mogu uzeti proizvoljnu vrednost
					Constraint newConstraint = getVarAndCst(syncVar, aTrans2.getCstGuard());

					if (!isSAT(newConstraint))
						continue;

					dst1 = aTrans1.getDstState();
					dst2 = aTrans2.getDstState();

					// create transition
					AutomatonState compSrc = currentState; // src
					AutomatonState newDst; // dst

					if (!((dst1.toString().equals(s_pair.s1.toString())) && (dst2.toString().equals(s_pair.s2.toString())))) {
						if (stringStateMap.containsKey(dst1.toString() + " "
								+ dst2.toString())) { /* state exists already */
							newDst = stringStateMap.get(dst1.toString() + " " + dst2.toString());
						} else { // new state
							newDst = computeDestinationState(new StatePair(dst1, dst2));
							composition.addState(newDst);
							stateStack.push(newDst);
						}
					} else { // same state
						newDst = currentState;
					}

					Constraint compCst = null;
					String cstMapKey = "" + syncVar + aTrans2.getCstGuard() + aTrans1.getCstGuard();
					if (constraintsMap.containsKey(cstMapKey)) {
						compCst = constraintsMap.get(cstMapKey);
					} else {
						compCst = replaceVarWithCst(syncVar, aTrans2.getCstGuard(), aTrans1.getCstGuard());
						compCst.reduceSimplify();
						constraintsMap.put(cstMapKey, compCst);
					}

					if (isSAT(compCst)) {
						TTesterTransition compTT = new TTesterTransition(compSrc, newDst, assgn, compCst,
								((TTesterTransition) aTrans2).outVars);

						if (!setTransString.contains(compTT.toStringNoID())) {

							composition.addTransition(compTT);
							setTransString.add(compTT.toStringNoID());

							/*
							 * System.out.println("added new transition" + compTT +
							 * " from SRC (" + stateStringMap.get(currentState) + ")" +
							 * " to DEST (" + dst1 + "," + dst2 + ")");
							 */
						} else {
							// System.out.println("\t\t>>>>compTT exists\n " +
							// compTT.toStringNoID());
						}
					} else {
						// System.out.println("\t\t>>>>compCst is UNSAT\n " + compCst);
					}
				}
			}
		}

		// System.out.println("COMPOSITION " + composition.toString());
  	return composition;
	} // end SyncAndCompose

	/**
	 * Computes/retrieves the destination state for a new transition
	 * 
	 * @param dstPair
	 *          : original destination states
	 * @return
	 */
	private static AutomatonState computeDestinationState(StatePair dstPair) {
		AutomatonState computedDst = null;
		AutomatonState dst1 = dstPair.s1;
		AutomatonState dst2 = dstPair.s2;

		computedDst = new AutomatonState(false, dst1.isAccepting() && dst2.isAccepting());

		/*
		 * System.out.println("added new state" + computedDst + " for dest1 S" +
		 * dst1 + " and for dest2 S" + dst2);
		 */
		StatePair newStatePair = new StatePair(dst1, dst2);

		stateMap.put(computedDst, newStatePair);
		statePairMap.put(newStatePair, computedDst);
		stateStringMap.put(computedDst, dst1.toString() + " " + dst2.toString());
		stringStateMap.put(dst1.toString() + " " + dst2.toString(), computedDst);

		return computedDst;
	}

	/**
	 * Takes a sync var and generates AND constraint with another constraint
	 * object.
	 */
	private static Constraint getVarAndCst(VariableInstance varInst, Constraint oldCst) {
		Constraint newCst;

		CstLexer lexer = new CstLexer(CharStreams.fromString(""));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CstParser parser = new CstParser(tokens);
		ParseTree tree;
		ConstraintGenVisitor visitor = new ConstraintGenVisitor();

		lexer.setInputStream(CharStreams.fromString(varInst.toString()));
		tokens = new CommonTokenStream(lexer);
		parser.setTokenStream(tokens);

		tree = parser.cst();
		newCst = visitor.visit(tree);

		ConstraintNode newRoot = new ConstraintNode(NodeType.LOG_AND, "and", newCst.getRoot(), oldCst.getRoot());
		return new Constraint(newRoot);
	}

	/**
	 * Replaces a subtree in the constraint , specified by variable instance, with
	 * another constraint subtree.
	 * 
	 * @param varInst
	 * @param cst
	 * @param cst_replacement
	 * @return
	 */
	public static Constraint replaceVarWithCst(VariableInstance varInst, Constraint cst, Constraint cst_replacement) {

		// TODO - before any replacement takes place , avoid replacing Var by
		// itself,
		// otherwise we get infinite loop

		if (varInst.isEqualToCst(cst_replacement))
			return cst;

		Constraint newCst = null;
		try {
			newCst = (Constraint) cst.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // TODO - CHECK THIS METHOD
		Constraint searchedCst;
		CstLexer lexer = new CstLexer(CharStreams.fromString(varInst.toString()));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CstParser parser = new CstParser(tokens);
		ParseTree tree;
		ConstraintGenVisitor visitor = new ConstraintGenVisitor();

		tree = parser.cst();
		searchedCst = visitor.visit(tree); // this is a replacement for cst.

		ConstraintNode theNode; /* find such a node in constraint tree */
		// ConstraintNode searchedForNode = new ConstraintNode(NodeType.LEAF_ID,
		// varInst.var.getName());

		while (true) {

			// System.out.println("replace");

			theNode = newCst.findConstraintNode(searchedCst.getRoot());
			if (theNode == null)
				break;

			// speedup
			// theNode = newCst.findConstraintNode(
			// searchedCst.getRoot()); /* find such a node in constraint tree */

			if (varInst.isNotInverted == false) {
				theNode.setParent(
						theNode.getParent()); /* set the parent to skip the "NOT" node */
			}
			theNode.setLeftChild(cst_replacement.getRoot().getLeftChild());
			theNode.setNnfVisitor(cst_replacement.getRoot().getNnfVisitor());
			theNode.setNodeID(cst_replacement.getRoot().getNodeID());
			theNode.setNodeType(cst_replacement.getRoot().getNodeType());
			theNode.setRightChild(cst_replacement.getRoot().getRightChild());
			theNode.setStrValue(cst_replacement.getRoot().getStrValue());

			if (theNode.getParent() != null)
				theNode.getParent().recursivelyReduce();
		}

		// if not found we get the same constraint
		return newCst;// !!!
	}

	/**
	 * Invokes a SMT solver in order
	 * 
	 * @return
	 * @throws InvalidConfigurationException
	 * @throws InterruptedException
	 * @throws SolverException
	 */
	public static boolean isSAT(Constraint cst)
			throws InvalidConfigurationException, SolverException, InterruptedException {

		// ---------- formula -----------

		cst.setConvertVisitor(new ConstraintNodeConvertVisitor(
				context)); /*
										 * TODO ! every Boolean formula we create has to share the
										 * same instance of SolverContext
										 */
		BooleanFormula formula2solve = cst.toJavaSMTConstraint();

		prover = context.newProverEnvironment(ProverOptions.GENERATE_MODELS);

		prover.addConstraint(formula2solve);

		if (prover.isUnsat())
			return false;
		else
			return true;
	}

	/**
	 * Function used to reset IDs for transitions and states.
	 */
	private static void resetIDs() {
		AutomatonState.stateID = 0; // reset
		AutomatonTransition.transitionID = 0;
	}

	/**
	 * Tester used to obtain automata from TTesters.
	 * 
	 * @param ctx
	 * @param inVar1
	 * @param inVar2
	 * @param outVar
	 * @return
	 */
	public static TTester getAlwaysTrueTester(Variable outVar) {
		resetIDs();
		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(outVar);
		tt.addOutVar(outVar);

		AutomatonState s0 = new AutomatonState(true, true);
		AutomatonState s1 = new AutomatonState(false, true);

		tt.addState(s0);
		tt.addState(s1);

		TransitionAssignment assgn1 = new TransitionAssignment();

		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, "not(" + outVar.getName() + ")");
		strList.add(1, "   (" + outVar.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));

		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = not p
		// cstList.get(1) = p

		TTesterTransition t0 = new TTesterTransition(s0, s1, assgn1, cstList.get(1), outVars);
		TTesterTransition t1 = new TTesterTransition(s1, s1, assgn1, cstList.get(1), outVars);
		TTesterTransition t2 = new TTesterTransition(s1, s1, assgn1, cstList.get(0), outVarsNeg);

		tt.addTransition(t0);
		tt.addTransition(t1);
		tt.addTransition(t2);
    System.out.println("\nALWAYS TRUE TESTER "+tt.toString());
		return tt;
	}

	/**
	 * Generates temporal testers for numerical constraint expressions such as x<3
	 * and X==TRUE.
	 * 
	 * @param inVar0
	 * @param outVar
	 * @return
	 */
	public static TTester getNumericalTT(IdCompContext ctx, Variable inVar0, Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addOutVar(outVar);

		AutomatonState s0 = new AutomatonState(true, true); // init acc
		tt.addState(s0);
		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, " " + ctx.getChild(0).getText() + " " + ctx.getChild(1).getText() + " " + ctx.getChild(2).getText());
		ArrayList<Constraint> cstList = prepareCstList(strList);

		cstList.add(cstList.get(0).negate());
		cstList.get(1).toNNF();

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));
		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = cst
		// cstList.get(1) = not (cst)

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s0, assgn1, cstList.get(0), outVars); // X>5,U
		TTesterTransition t1 = new TTesterTransition(s0, s0, assgn1, cstList.get(1), outVarsNeg); // !(X>5),!U
		tt.addTransition(t0);
		tt.addTransition(t1);
		return tt;
	}

	/**
	 * Generates temporal testers for numerical constraint expressions such as x<3
	 * and X==TRUE.
	 * 
	 * @param inVar0
	 * @param outVar
	 * @return
	 */
	public static TTester getNumericalIdTT(IdCompContext ctx, Variable inVar0, Variable inVar1, Variable outVar) {
		resetIDs();

		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addVar(inVar1);
		tt.addOutVar(outVar);

		AutomatonState s0 = new AutomatonState(true, true); // init acc
		tt.addState(s0);
		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, " " + ctx.getChild(0).getText() + " " + ctx.getChild(1).getText() + " " + ctx.getChild(2).getText());
		ArrayList<Constraint> cstList = prepareCstList(strList);

		cstList.add(cstList.get(0).negate());

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));
		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = cst
		// cstList.get(1) = not (cst)

		// transitions
		TTesterTransition t0 = new TTesterTransition(s0, s0, assgn1, cstList.get(0), outVars); // X>Y,U
		TTesterTransition t1 = new TTesterTransition(s0, s0, assgn1, cstList.get(1), outVarsNeg); // !(X>Y),!U
		tt.addTransition(t0);
		tt.addTransition(t1);
		return tt;
	}

	/**
	 * Converts the Temporal Tester into an acceptor.
	 * 
	 * @return
	 */
	public static Automaton convertToAcceptor(TTester ttester) {

		Iterator<Variable> outVars = ttester.getOutVariables().values().iterator();

		if (ttester.getOutVariables().values().size() > 1)
			System.err.println("more than a single output variable!");

		try {
			return syncAndCompose(ttester, getAlwaysTrueTester(outVars.next()));
		} catch (InvalidConfigurationException | SolverException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Generates temporal testers for unbounded historically operator.
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param freshOutVar
	 * @return
	 */
	public static TTester getUnboundedHistoricallyTester(ExprHistoricallyExprContext ctx, Variable inVar0,
			Variable outVar) {
		resetIDs();
		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addOutVar(outVar);

		AutomatonState s0 = new AutomatonState(true, true); // init acc
		AutomatonState s1 = new AutomatonState(false, true); // acc
		tt.addState(s0);
		tt.addState(s1);
		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, "" + inVar0.getName() + "");
		strList.add(1, "not(" + inVar0.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));
		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = p
		// cstList.get(1) = not p
		TTesterTransition t0 = new TTesterTransition(s0, s0, assgn1, cstList.get(0), outVars); // pU
		TTesterTransition t1 = new TTesterTransition(s0, s1, assgn1, cstList.get(1), outVarsNeg); // !p!U
		TTesterTransition t2 = new TTesterTransition(s1, s1, assgn1, cstList.get(0), outVarsNeg); // p!U
		TTesterTransition t3 = new TTesterTransition(s1, s1, assgn1, cstList.get(1), outVarsNeg); // !p!U
		tt.addTransition(t0);
		tt.addTransition(t1);
		tt.addTransition(t2);
		tt.addTransition(t3);

		return tt;
	}

	/**
	 * Generates temporal testers for unbounded once operator.
	 */
	public static TTester getUnboundedOnceTester(ExprOnceExprContext ctx, Variable inVar0, Variable outVar) {
		resetIDs();
		TTester tt = new TTester(false);// no error state here. TODO-review the
																		// decision
		tt.addVar(inVar0);
		tt.addOutVar(outVar);

		AutomatonState s0 = new AutomatonState(true, true); // init acc
		AutomatonState s1 = new AutomatonState(false, true); // acc
		tt.addState(s0);
		tt.addState(s1);
		TransitionAssignment assgn1 = new TransitionAssignment();

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, "" + inVar0.getName() + "");
		strList.add(1, "not(" + inVar0.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));
		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = p
		// cstList.get(1) = not p
		TTesterTransition t0 = new TTesterTransition(s0, s0, assgn1, cstList.get(1), outVarsNeg); // !p!U
		TTesterTransition t1 = new TTesterTransition(s0, s1, assgn1, cstList.get(0), outVars); // pU
		TTesterTransition t2 = new TTesterTransition(s1, s1, assgn1, cstList.get(1), outVars); // !pU
		TTesterTransition t3 = new TTesterTransition(s1, s1, assgn1, cstList.get(0), outVars); // pU
		tt.addTransition(t0);
		tt.addTransition(t1);
		tt.addTransition(t2);
		tt.addTransition(t3);

		return tt;
	}

	/**
	 * 
	 * @param ttIn
	 * @return
	 */
	public static TTester trimAutomata(TTester ttIn, int boundedVariability) {

		return ttIn;
	}

	/**
	 * This will directly create Temporal tester for formula next^n (p) which is
	 * equivalent to (next....(next(next(p))))
	 * 
	 * @param ctx
	 * @param inVar0
	 * @param outVar
	 * @param power
	 * @param variability
	 * @return
	 */
	public static TTester getNextPowerTester(Variable inVar0, Variable outVar, int power, int variability) {
		resetIDs();

		// boolean doReductions = true;
		// if (variability == 0)
		// doReductions = false;

		System.out.println("\n>>generating NextPower tester for oracle " + power + " and variability " + variability);
		TTester tt = new TTester(false);// no error state here. TODO-review the
		TransitionAssignment assgn1 = new TransitionAssignment();
		tt.addVar(inVar0);
		tt.addOutVar(outVar);

		int totalNumStates = (int) (Math.pow(2, power));
		int maxNumTransitions = Math.multiplyExact(3, (int) (Math.pow(2, power)));

		AutomatonState initState = new AutomatonState(true, true);// init acc
		AutomatonState stateArr[] = new AutomatonState[totalNumStates];

		for (int i = 0; i < totalNumStates; i++) {
			if (!(isRedundantState(i, power, variability))) {
				if (i < (totalNumStates - 1)) {
					stateArr[i] = new AutomatonState(false, false);
				} else {
					stateArr[i] = new AutomatonState(false, true);
				}
				stateArr[i].setLabel(i);
			}
		}

		// tt.addState(s0); // init acc
		// tt.addState(s1); // acc
		// tt.addState(s2); // acc

		// specify constraints
		ArrayList<String> strList = new ArrayList<String>(2);
		strList.add(0, "" + inVar0.getName() + "");
		strList.add(1, "not(" + inVar0.getName() + ")");
		ArrayList<Constraint> cstList = prepareCstList(strList);
		Constraint cstTrue = getConstraintTrue();

		// create out vars
		ArrayList<VariableInstance> outVars = new ArrayList<VariableInstance>();
		outVars.add(new VariableInstance(outVar, true));
		ArrayList<VariableInstance> outVarsNeg = new ArrayList<VariableInstance>();
		outVarsNeg.add(new VariableInstance(outVar, false));

		// cstList.get(0) = p
		// cstList.get(1) = not p

		// transitions
		TTesterTransition transArr[] = new TTesterTransition[maxNumTransitions];

		// init trans
		int init_trans_num = (int) Math.pow(2, power);

		for (int i = 0; i < init_trans_num; i++) {
			if (!(isRedundantInitTransition(i, power, variability))) {

				if ((stateArr[i].getLabel() & 1) == 0) { // even
					transArr[i] = new TTesterTransition(initState, stateArr[i], assgn1, cstTrue, outVarsNeg);
				} else { // odd
					transArr[i] = new TTesterTransition(initState, stateArr[i], assgn1, cstTrue, outVars);
				}
			}
		}

		int vmask = (int) (Math.pow(2, variability) - 1); // 0011

		int powermask = (int) (Math.pow(2, power) - 1); // 1111
		int MSB = (int) (Math.pow(2, power - 1)); // 1000
		int j = init_trans_num;

		for (AutomatonState state : stateArr) {
			if ((state != null)) {

				int shiftedLabel = (state.getLabel() * 2) & powermask;
				int posPrediction = (shiftedLabel + 1) & powermask;
				int first_prediction = state.getLabel() & MSB;

				Constraint newCst;

				if (first_prediction == 0)
					newCst = cstList.get(1);
				else
					newCst = cstList.get(0);

				transArr[j++] = new TTesterTransition(state, stateArr[posPrediction], assgn1, newCst, outVars);
				
				
				if (state.getLabel() %2 == 1) {

					if ((state.getLabel() & vmask) == vmask) {  // last L
																										  // predictions
																										  // true, then pessimistic
																										  // prediction is OK
						transArr[j++] = new TTesterTransition(state, stateArr[shiftedLabel], assgn1, newCst, outVarsNeg);
					}

				} else { //LSB is 0
					transArr[j++] = new TTesterTransition(state, stateArr[shiftedLabel], assgn1, newCst, outVarsNeg);

				}

			}
		}

		// add to the tester

		tt.addState(initState);
		
		for (AutomatonState state : stateArr) {
			if (state != null) {
				tt.addState(state);
			}
		}

		for (TTesterTransition trans : transArr) {
			if (trans != null) {
				tt.addTransition(trans);
			}
		}

//		System.out.println("__________________++_________________++________________");
//		System.out.println("__________________++_________________++________________");
//
//		for (AutomatonState state : tt.states.values()) {
//			System.out.println("state" + state.toString() + " label " + Integer.toBinaryString(state.getLabel()));
//		}
//
//		System.out.println(tt);
//		System.out.println("__________________++_________________++________________");
//		System.out.println("__________________++_________________++________________");
		
		
		return tt;
	}

	/**
	 * Discovers redundant states in NEXT^n Temporal Testers.
	 * 
	 * @return
	 */
	public static boolean isRedundantState(int stateNum, int length, int variability) {
		// int size 64 bits.

		if (variability == 0)
			return false;

		int arrayBitMask[] = new int[length];
		int lengthMask = (int) Math.pow(2, length) - 1;
		int theState = stateNum & lengthMask;

		for (int i = (length - 1); i >= 0; i--) {
			arrayBitMask[i] = ((int) Math.pow(2, i)) & lengthMask;
		}

		String formatStr = "%" + length + "s";
		boolean foundShorterSequence = false;

		// main thing
		int oneArrLen = 0;
		int minArrLen = length + 1;

		// proverava se i najnizi bit, a smatra se
		// nizom keceva niz koji se zavrsava nulom.
		// ako se ne zavrsava nulom , odbacuje se
		int j = 0;

		for (int i = length - 1; i >= 0; i--) {

			if ((theState & arrayBitMask[i]) == 0) {
				if (oneArrLen != 0) { // potencijalni niz, mora se napraviti izuzetak

					if (oneArrLen != j) // eliminisi pocetne keceve
						minArrLen = (oneArrLen < minArrLen) ? oneArrLen : minArrLen;
				}
				oneArrLen = 0;
			} else {
				oneArrLen++;
			}
			j++;
		}

		System.out.println("\nSTATE NUMBER " + String.format(formatStr, Integer.toBinaryString(stateNum)));

		if (minArrLen < variability) {
			// System.out.println("FOUND SHORTER SEQUENCE of length = " + minArrLen +
			// " , signal variability = " + variability);
			foundShorterSequence = true;
			System.out.println("\t >>> state " + stateNum + " removed");

		} else {
			// System.out.println("did not find shorter sequence. Min sequence length
			// found = " + minArrLen + " , signal variability = " + variability);
		}

		return foundShorterSequence;
	}

	/**
	 * This function tells us which transitions from init state are redundant
	 * 
	 * @param stateNum
	 * @param length
	 * @param variability
	 * @return
	 */
	public static boolean isRedundantInitTransition(int transNum, int length, int variability) {
		// int size 64 bits.

		if (variability == 0)
			return false;

		int arrayBitMask[] = new int[length];
		int lengthMask = (int) Math.pow(2, length) - 1;
		int theState = transNum & lengthMask;

		for (int i = (length - 1); i >= 0; i--) {
			arrayBitMask[i] = ((int) Math.pow(2, i)) & lengthMask;
		}

//		String formatStr = "%" + length + "s";
		boolean foundShorterSequence = false;

		// main thing
		int oneArrLen = 0;
		int minArrLen = length + 1;

		for (int i = length - 1; i >= 0; i--) {

			if ((theState & arrayBitMask[i]) == 0) {
				if (oneArrLen != 0) { // potencijalni niz, mora se napraviti izuzetak
					minArrLen = (oneArrLen < minArrLen) ? oneArrLen : minArrLen;
				}
				oneArrLen = 0;
			} else {
				oneArrLen++;
			}

		}

		// System.out.println("\nTRANS NUMBER " + String.format(formatStr,
		// Integer.toBinaryString(transNum)));

		if (minArrLen < variability) {
			// System.out.println("FOUND SHORTER SEQUENCE of length = " + minArrLen +
			// " , signal variability = " + variability);
			foundShorterSequence = true;
			System.out.println("\t >>> transition " + transNum + " removed");

		} else {
			// System.out.println("did not find SHORTER SEQUENCE. Min sequence length
			// found = " + minArrLen + " , signal variability = " + variability);
		}

		return foundShorterSequence;
	}

	/*
	 * 
	 * public static TTester getAlwaysTrueTester(Variable outVar) { resetIDs();
	 * 
	 * TTester tt = new TTester(false);// no error state here. TODO-review the //
	 * decision
	 * 
	 * tt.addVar(outVar); tt.addOutVar(outVar); AutomatonState s0 = new
	 * AutomatonState(true, true); tt.addState(s0); TransitionAssignment assgn1 =
	 * new TransitionAssignment();
	 * 
	 * ArrayList<String> strList = new ArrayList<String>(2); strList.add(0, "not("
	 * + outVar.getName() + ")"); strList.add(1, "   (" + outVar.getName() + ")");
	 * ArrayList<Constraint> cstList = prepareCstList(strList);
	 * 
	 * // create out vars ArrayList<VariableInstance> outVars = new
	 * ArrayList<VariableInstance>(); outVars.add(new VariableInstance(outVar,
	 * true)); ArrayList<VariableInstance> outVarsNeg = new
	 * ArrayList<VariableInstance>(); outVarsNeg.add(new VariableInstance(outVar,
	 * false));
	 * 
	 * // cstList.get(0) = not p not q // cstList.get(1) = not p q //
	 * cstList.get(2) = p not q // cstList.get(3) = p q // transitions - no input
	 * constraint TTesterTransition t0 = new TTesterTransition(s0, s0, assgn1,
	 * cstList.get(1), outVars); tt.addTransition(t0);
	 * 
	 * return tt; }
	 * 
	 */

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		// test is redundant state
		// corner cases
		// isRedundantState(0, 9, 4);
		// isRedundantState(1, 9, 4);
		// isRedundantState(2, 9, 4);
		// isRedundantState(3, 9, 4);
		// isRedundantState(6, 9, 4);
		// isRedundantState(7, 9, 4);
		// isRedundantState(14, 9, 4);
		// isRedundantState(15, 9, 4);
		// isRedundantState(30, 9, 4);
		// isRedundantState(31, 9, 4);
		// isRedundantState(62, 9, 4);
		// isRedundantState(63, 9, 4);
		//
		// isRedundantState(256, 9, 4);
		// isRedundantState(256/2, 9, 4);
		//
		// isRedundantState(384, 9, 4);
		// isRedundantState(384/2, 9, 4);
		//
		// isRedundantState(448, 9, 4);
		// isRedundantState(448/2, 9, 4);
		//
		// isRedundantState(480, 9, 4);
		// isRedundantState(480/2, 9, 4);
		//
		// isRedundantState(496, 9, 4);
		// isRedundantState(496/2, 9, 4);
		//
		//
		//
		//
		//
		// isRedundantState(448, 9, 4);

		// length 1 sequence is the minimum sequence
		// isRedundantState(405, 9, 4);
		// isRedundantState(341, 9, 4);
		// isRedundantState(170, 9, 4);
		// isRedundantState(171, 9, 4);
		// isRedundantState(235, 9, 4);
		// isRedundantState(107, 9, 4);

		System.out.println("------------------------------");
		System.out.println("------------------------------");
		// length 1 sequence is the minimum sequence
		// isRedundantState(3, 9, 4);
		// isRedundantState(6, 9, 4);
		// isRedundantState(12, 9, 4);
		// isRedundantState(24, 9, 4);
		// isRedundantState(48, 9, 4);
		// isRedundantState(96, 9, 4);
		// isRedundantState(192, 9, 4);
		// isRedundantState(384, 9, 4);

		for (int i = 0; i < 16; i++) {
			if (isRedundantState(i, 4, 2)) {
				// System.out.println("\t >>> state " + i + " removed");
			}
		}

		System.out.println("");
		System.out.println("-------TRANSITIONS--------------");

		for (int i = 0; i < 16; i++) {
			if (isRedundantInitTransition(i, 4, 2)) {
				// System.out.println("\t >>> transition " + i + " removed");
			}
		}

	}

}
