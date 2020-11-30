package constraint;

import java.io.Serializable;

import org.sosy_lab.common.ShutdownManager;
import org.sosy_lab.common.configuration.Configuration;
import org.sosy_lab.common.configuration.InvalidConfigurationException;
import org.sosy_lab.common.log.BasicLogManager;
import org.sosy_lab.common.log.LogManager;
import org.sosy_lab.java_smt.SolverContextFactory;
import org.sosy_lab.java_smt.SolverContextFactory.Solvers;
import org.sosy_lab.java_smt.api.BooleanFormula;
import org.sosy_lab.java_smt.api.BooleanFormulaManager;
import org.sosy_lab.java_smt.api.FormulaManager;
import org.sosy_lab.java_smt.api.IntegerFormulaManager;
import org.sosy_lab.java_smt.api.NumeralFormula.IntegerFormula;
import org.sosy_lab.java_smt.api.SolverContext;

import constraint.ConstraintNode.NodeType;

public class ConstraintNodeConvertVisitor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 874156092306785454L;
	
	
	transient Configuration config; 	//TODO - TRANSIENT?
	transient LogManager logger;		  //TODO - TRANSIENT?
	transient ShutdownManager shutdown;//TODO - TRANSIENT?
	transient SolverContext context;	 //TODO - TRANSIENT?
	transient FormulaManager fmgr;		 //TODO - TRANSIENT?
	transient BooleanFormulaManager bmgr;//TODO - TRANSIENT?
	transient IntegerFormulaManager imgr;//TODO - TRANSIENT?

	public ConstraintNodeConvertVisitor(Solvers solver) throws InvalidConfigurationException {
		Configuration config = Configuration.defaultConfiguration();
		LogManager logger = BasicLogManager.create(config);
		ShutdownManager shutdown = ShutdownManager.create();

		SolverContext context = SolverContextFactory.createSolverContext(config, logger, shutdown.getNotifier(),
				solver);
		
		// ------------------------------
		// ---------- formula -----------
		fmgr = context.getFormulaManager();
		bmgr = fmgr.getBooleanFormulaManager();
		imgr = fmgr.getIntegerFormulaManager();
	}
	

	/**
	 * Construct ConstraintNodeConvertVisitor for a specific
	 * SolverContext object.
	 * @param ctx
	 */
	public ConstraintNodeConvertVisitor(SolverContext ctx) {
			Configuration config = Configuration.defaultConfiguration();
		
			try {
				logger = BasicLogManager.create(config);
			} catch (InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			shutdown = ShutdownManager.create();
			context = ctx;
					
			fmgr = context.getFormulaManager();
			bmgr = fmgr.getBooleanFormulaManager();
			imgr = fmgr.getIntegerFormulaManager();		
	}

	/**
	 * Visits the constrain node in order to convert it to a Formula object
	 * accepted by JAVA SMT API.
	 * 
	 * @param cstNode
	 * @return
	 */
	public BooleanFormula visit(ConstraintNode cstNode) {
		BooleanFormula f2ret = null;

		switch (cstNode.nodeType) {
		case CMP_EQ:
			f2ret = visitCMP_EQ(cstNode);
			break;
		case CMP_GEQ:
			f2ret = visitCMP_GEQ(cstNode);
			break;
		case CMP_GREAT:
			f2ret = visitCMP_GREAT(cstNode);
			break;
		case CMP_LEQ:
			f2ret = visitCMP_LEQ(cstNode);

			break;
		case CMP_LESS:
			f2ret = visitCMP_LESS(cstNode);

			break;
		case CMP_NEQ:
			f2ret = visitCMP_NEQ(cstNode);

			break;
		case LEAF_BOOL:
			f2ret = visitLEAF_BOOL(cstNode);

			break;
		case LEAF_ID:
			f2ret = visitLEAF_ID(cstNode);

			break;
		case LEAF_NUM:
			f2ret = visitLEAF_NUM(cstNode);

			break;
		case LOG_AND:
			f2ret = visitLOG_AND(cstNode);

			break;
		case LOG_NOT:
			f2ret = visitLOG_NOT(cstNode);

			break;
		case LOG_OR:
			f2ret = visitLOG_OR(cstNode);

			break;
		default:
			System.err.println("ConstraintNodeConvertVisitor:visit:: UNKNOWN NODE TYPE!");
		}

		return f2ret;
	}

	private BooleanFormula visitLOG_OR(ConstraintNode cstNode) {

		BooleanFormula leftF = visit(cstNode.leftChild);
		BooleanFormula rightF = visit(cstNode.rightChild);

		return bmgr.or(leftF, rightF);

	}

	private BooleanFormula visitLOG_NOT(ConstraintNode cstNode) {
		// TODO Auto-generated method stub
		return bmgr.not(visit(cstNode.leftChild));
	}

	private BooleanFormula visitLOG_AND(ConstraintNode cstNode) {
		BooleanFormula leftF = visit(cstNode.leftChild);
		BooleanFormula rightF = visit(cstNode.rightChild);

		return bmgr.and(leftF, rightF);
	}

	//---- to skip these
	private BooleanFormula visitLEAF_NUM(ConstraintNode cstNode) {
		//THIS WILL BE HANDLED IN PARENT RULE
		return null;
	}

	private BooleanFormula visitLEAF_ID(ConstraintNode cstNode) {
		//by default returns boolVar
		//THIS WILL BE HANDLED IN PARENT RULE
		return bmgr.makeVariable(cstNode.strValue);
	}

	private BooleanFormula visitLEAF_BOOL(ConstraintNode cstNode) {
		//THIS WILL BE HANDLED IN PARENT RULE
		return bmgr.makeBoolean(Boolean.parseBoolean(cstNode.strValue));
	}
	// ------------------------------

	/**
	 * Used both for x !== TRUE and X !== 5
	 * 
	 * @param cstNode
	 * @return
	 */
	private BooleanFormula visitCMP_NEQ(ConstraintNode cstNode) {
		boolean isBF = false;
		BooleanFormula bf2ret;

		if ((cstNode.leftChild.nodeType == NodeType.LEAF_BOOL) || (cstNode.rightChild.nodeType == NodeType.LEAF_BOOL))
			isBF = true;

		if (isBF) {

			BooleanFormula leftBF = null;
			if (cstNode.leftChild.nodeType == NodeType.LEAF_ID) // bool var
				leftBF = bmgr.makeVariable(cstNode.leftChild.strValue);
			else if (cstNode.leftChild.nodeType == NodeType.LEAF_BOOL)
				leftBF = bmgr.makeBoolean(Boolean.parseBoolean(cstNode.leftChild.strValue));

			BooleanFormula rightBF = null;
			if (cstNode.rightChild.nodeType == NodeType.LEAF_ID) // bool var
				rightBF = bmgr.makeVariable(cstNode.rightChild.strValue);
			else if (cstNode.rightChild.nodeType == NodeType.LEAF_BOOL)
				rightBF = bmgr.makeBoolean(Boolean.parseBoolean(cstNode.rightChild.strValue));

			bf2ret = bmgr.not(bmgr.equivalence(leftBF, rightBF));

		} else { // integerF
			// have to convert ever formula to a boolean one
			// x!==5 <=> true AND (x!==5)

			IntegerFormula leftIF = null;
			if (cstNode.leftChild.nodeType == NodeType.LEAF_ID)
				leftIF = imgr.makeVariable(cstNode.leftChild.strValue);
			else if (cstNode.leftChild.nodeType == NodeType.LEAF_NUM)
				leftIF = imgr.makeNumber(cstNode.leftChild.strValue);

			IntegerFormula rightIF = null;
			if (cstNode.rightChild.nodeType == NodeType.LEAF_ID)
				rightIF = imgr.makeVariable(cstNode.rightChild.strValue);
			else if (cstNode.rightChild.nodeType == NodeType.LEAF_NUM)
				rightIF = imgr.makeNumber(cstNode.rightChild.strValue);

			bf2ret = bmgr.and(bmgr.makeBoolean(true), bmgr.not(imgr.equal(leftIF, rightIF)));
		}

		return bf2ret;
	}

	/**
	 * Used both for x==TRUE and X!==5
	 * 
	 * @param cstNode
	 * @return
	 */
	private BooleanFormula visitCMP_EQ(ConstraintNode cstNode) {
		boolean isBF = false;
		BooleanFormula bf2ret;

		if ((cstNode.leftChild.nodeType == NodeType.LEAF_BOOL) || (cstNode.rightChild.nodeType == NodeType.LEAF_BOOL))
			isBF = true;

		if (isBF) {
			BooleanFormula leftBF = null;
			if (cstNode.leftChild.nodeType == NodeType.LEAF_ID) // bool var
				leftBF = bmgr.makeVariable(cstNode.leftChild.strValue);
			else if (cstNode.leftChild.nodeType == NodeType.LEAF_BOOL)
				leftBF = bmgr.makeBoolean(Boolean.parseBoolean(cstNode.leftChild.strValue));

			BooleanFormula rightBF = null;
			if (cstNode.rightChild.nodeType == NodeType.LEAF_ID) // bool var
				rightBF = bmgr.makeVariable(cstNode.rightChild.strValue);
			else if (cstNode.rightChild.nodeType == NodeType.LEAF_BOOL)
				rightBF = bmgr.makeBoolean(Boolean.parseBoolean(cstNode.rightChild.strValue));

			bf2ret = bmgr.equivalence(leftBF, rightBF);

		} else { // integerF
			// have to convert ever formula to a boolean one
			// x==5 <=> true AND (x==5)

			IntegerFormula leftIF = null;
			if (cstNode.leftChild.nodeType == NodeType.LEAF_ID)
				leftIF = imgr.makeVariable(cstNode.leftChild.strValue);
			else if (cstNode.leftChild.nodeType == NodeType.LEAF_NUM)
				leftIF = imgr.makeNumber(cstNode.leftChild.strValue);

			IntegerFormula rightIF = null;
			if (cstNode.rightChild.nodeType == NodeType.LEAF_ID)
				rightIF = imgr.makeVariable(cstNode.rightChild.strValue);
			else if (cstNode.rightChild.nodeType == NodeType.LEAF_NUM)
				rightIF = imgr.makeNumber(cstNode.rightChild.strValue);

			bf2ret = bmgr.and(bmgr.makeBoolean(true), imgr.equal(leftIF, rightIF));
		}

		return bf2ret;
	}

	/// ----------- int only

	private BooleanFormula visitCMP_LESS(ConstraintNode cstNode) {
		BooleanFormula bf2ret;

		if ((cstNode.leftChild.nodeType == NodeType.LEAF_BOOL) || (cstNode.rightChild.nodeType == NodeType.LEAF_BOOL))
			System.err.println("ERROR: no bool value allowed here");

		// integerF
		// have to convert ever formula to a boolean one
		// x==5 <=> true AND (x==5)

		IntegerFormula leftIF = null;
		if (cstNode.leftChild.nodeType == NodeType.LEAF_ID)
			leftIF = imgr.makeVariable(cstNode.leftChild.strValue);
		else if (cstNode.leftChild.nodeType == NodeType.LEAF_NUM)
			leftIF = imgr.makeNumber(cstNode.leftChild.strValue);

		IntegerFormula rightIF = null;
		if (cstNode.rightChild.nodeType == NodeType.LEAF_ID)
			rightIF = imgr.makeVariable(cstNode.rightChild.strValue);
		else if (cstNode.rightChild.nodeType == NodeType.LEAF_NUM)
			rightIF = imgr.makeNumber(cstNode.rightChild.strValue);

		bf2ret = bmgr.and(bmgr.makeBoolean(true), imgr.lessThan(leftIF, rightIF));

		return bf2ret;

	}

	/**
	 * 
	 * @param cstNode
	 * @return
	 */
	private BooleanFormula visitCMP_LEQ(ConstraintNode cstNode) {
		BooleanFormula bf2ret;

		if ((cstNode.leftChild.nodeType == NodeType.LEAF_BOOL) || (cstNode.rightChild.nodeType == NodeType.LEAF_BOOL))
			System.err.println("ERROR: no bool value allowed here");

		// integerF
		// have to convert ever formula to a boolean one
		// x==5 <=> true AND (x==5)

		IntegerFormula leftIF = null;
		if (cstNode.leftChild.nodeType == NodeType.LEAF_ID)
			leftIF = imgr.makeVariable(cstNode.leftChild.strValue);
		else if (cstNode.leftChild.nodeType == NodeType.LEAF_NUM)
			leftIF = imgr.makeNumber(cstNode.leftChild.strValue);

		IntegerFormula rightIF = null;
		if (cstNode.rightChild.nodeType == NodeType.LEAF_ID)
			rightIF = imgr.makeVariable(cstNode.rightChild.strValue);
		else if (cstNode.rightChild.nodeType == NodeType.LEAF_NUM)
			rightIF = imgr.makeNumber(cstNode.rightChild.strValue);

		bf2ret = bmgr.and(bmgr.makeBoolean(true), bmgr.or(imgr.equal(leftIF, rightIF), imgr.lessThan(leftIF, rightIF)));

		return bf2ret;
	}

	/**
	 * 
	 * @param cstNode
	 * @return
	 */
	private BooleanFormula visitCMP_GREAT(ConstraintNode cstNode) {
		return bmgr.not(visitCMP_LEQ(cstNode));
	}

	/**
	 * 
	 * @param cstNode
	 * @return
	 */
	private BooleanFormula visitCMP_GEQ(ConstraintNode cstNode) {
		// TODO Auto-generated method stub
		return bmgr.not(visitCMP_LESS(cstNode));
	}

	public void setSolverContext(SolverContext context2) {
		this.context = context2;		
	}


	public SolverContext getContext() {
		return context;
	}


	public void setContext(SolverContext context) {
		this.context = context;
	}

}
