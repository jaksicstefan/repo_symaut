package test;

import java.math.BigInteger;

import org.sosy_lab.common.ShutdownManager;
import org.sosy_lab.common.configuration.Configuration;
import org.sosy_lab.common.configuration.InvalidConfigurationException;
import org.sosy_lab.common.log.BasicLogManager;
import org.sosy_lab.common.log.LogManager;

import org.sosy_lab.java_smt.SolverContextFactory.Solvers;
import org.sosy_lab.java_smt.SolverContextFactory;

import org.sosy_lab.java_smt.api.BooleanFormula;
import org.sosy_lab.java_smt.api.BooleanFormulaManager;
import org.sosy_lab.java_smt.api.FormulaManager;
import org.sosy_lab.java_smt.api.IntegerFormulaManager;
import org.sosy_lab.java_smt.api.Model;
import org.sosy_lab.java_smt.api.NumeralFormula.IntegerFormula;
import org.sosy_lab.java_smt.api.ProverEnvironment;
import org.sosy_lab.java_smt.api.SolverContext;
import org.sosy_lab.java_smt.api.SolverContext.ProverOptions;
import org.sosy_lab.java_smt.api.SolverException;

import automaton.AutomataService;
import constraint.Constraint;
import constraint.ConstraintNodeConvertVisitor;

/**
 * This class will be used to play with Java SMT API.
 * 
 * @author jaksicS
 *
 */

public class TestJavaSMTAPI {

	/**
	 * 
	 * @param args
	 * @throws InvalidConfigurationException
	 * @throws SolverException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InvalidConfigurationException, SolverException, InterruptedException {

		AutomataService.defaultSolver = Solvers.Z3;
		Constraint.defaultSolver = Solvers.Z3;

		Configuration 			config = Configuration.fromCmdLineArguments(args);
		LogManager 					logger = BasicLogManager.create(config);
		ShutdownManager 	shutdown = ShutdownManager.create();
		SolverContext 		 context = SolverContextFactory.createSolverContext(config, logger, shutdown.getNotifier(), Solvers.Z3);
		FormulaManager 				fmgr = context.getFormulaManager();
		BooleanFormulaManager bmgr = fmgr.getBooleanFormulaManager();
		IntegerFormulaManager imgr = fmgr.getIntegerFormulaManager();
		
		IntegerFormula a = imgr.makeVariable("a"), b = imgr.makeVariable("b"), c = imgr.makeVariable("c");
		BooleanFormula constraint = bmgr.or(imgr.equal(imgr.add(a, b), c),
				imgr.equal(imgr.add(a, c), imgr.multiply(imgr.makeNumber(2), b)));
		BigInteger value = null;

		try (ProverEnvironment prover = context.newProverEnvironment(ProverOptions.GENERATE_MODELS)) {
			prover.addConstraint(constraint);
			boolean isUnsat = prover.isUnsat();
			if (!isUnsat) {
				Model model = prover.getModel();
				value = model.evaluate(a);
			}
		}
		
		System.out.println("Basic example : Evaluated "+ a +" with value "+ value);
		
		//-------------------- my formulas	
		
		BooleanFormula varP = bmgr.makeVariable("p");
		BooleanFormula varQ = bmgr.makeVariable("q");
		
		BooleanFormula andF = bmgr.and(varP,varQ);

		
		BooleanFormula andFunsat = bmgr.and(andF, bmgr.not(varP));

		//            basic example from JAVA SMT website
		//----------------------------------------------------------------------------------------------
		try (ProverEnvironment prover = context.newProverEnvironment(ProverOptions.GENERATE_MODELS))	{
				prover.addConstraint(andFunsat);
				boolean isUnsat = prover.isUnsat();

				if (!(isUnsat))
					System.out.println("Formula " + andFunsat.toString() + " is SAT");
				else
					System.out.println("Formula " + andFunsat.toString() + " is UNSAT");
		}
		
		//            basic example from JAVA SMT website
		//----------------------------------------------------------------------------------------------
		try (ProverEnvironment prover = context.newProverEnvironment(ProverOptions.GENERATE_MODELS))	{
			prover.addConstraint(andF);
			boolean isUnsat = prover.isUnsat();

			if (!(isUnsat))
				System.out.println("Formula " + andF.toString() + " is SAT");
			else
				System.out.println("Formula " + andF.toString() + " is UNSAT");
		}
		
		//            TESTING THE CST < > JAVA SMT API
		//----------------------------------------------------------------------------------------------
		System.out.println("------------------------------------------------------");
		System.out.println("TESTING THE CST < > JAVA SMT API ");
		try (ProverEnvironment prover = context.newProverEnvironment(ProverOptions.GENERATE_MODELS))	{

		Constraint cst2test;
		cst2test = Constraint.getCstFromStr("m and n and not(n)");
		cst2test.setConvertVisitor(new ConstraintNodeConvertVisitor(context));

		BooleanFormula bf = cst2test.toJavaSMTConstraint();

		prover.addConstraint(bf);
		boolean isUnsat = prover.isUnsat();

		System.out.println("");
		if (!(isUnsat))
			System.out.println("Formula " + bf.toString() + " is SAT");
		else
			System.out.println("Formula " + bf.toString() + " is UNSAT");
		}

	}

}
