package constraint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.sosy_lab.common.configuration.InvalidConfigurationException;
import org.sosy_lab.java_smt.SolverContextFactory.Solvers;
import org.sosy_lab.java_smt.api.BooleanFormula;

//import org.sosy_lab.solver.SolverContextFactory.Solvers;
//import org.sosy_lab.solver.api.BooleanFormula;
import policy.SatisfactionPolicy;
import automaton.Variable;
import constraint.ConstraintNode.NodeType;
import cst_gen.CstLexer;
import cst_gen.CstParser;

/**
 * Container class for a constraint represented by a tree. Tree is represented
 * by a root node.
 * 
 * @author JaksicS
 *
 */
public class Constraint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5896577807043311511L;

	/**
	 * Root node of the constraint tree, representing the entire constraint
	 * expression.
	 */
	private ConstraintNode root;

	/**
	 * Default solver used when creating ConstraintNodeConvertVisitor
	 * 
	 */
	transient public static Solvers defaultSolver = Solvers.PRINCESS;

	/**
	 * Makes a human readable printout of the tree.
	 */
	public String toString() {
		return root.toStringTree();
	}

	/**
	 * Makes an inline print. TODO - this method can be improved
	 */
	public String toStringInline() {
		StringBuffer toReturn = new StringBuffer();

		if (root.leftChild != null)
			toReturn.append(root.leftChild.toStringInline());

		toReturn.append(" " + root.strValue + " ");

		if (root.rightChild != null)
			toReturn.append(root.rightChild.toStringInline());

		return toReturn.toString();
	}

	/**
	 * Constraint instance constructor. It is necessary to specify an array of
	 * Constraint Nodes. Such an array will have the constraint specified in
	 * postfix notation.
	 * 
	 * In example , the infix expression A & (B|C)|(F&D) will be represented as an
	 * array of ConstraintNode object in the following order (postfix notation):
	 * ABC|&FDA|
	 * 
	 * @throws InvalidConfigurationException
	 */
	public Constraint(ArrayList<ConstraintNode> nodeList) {
		Stack<ConstraintNode> nodeStack = new Stack<ConstraintNode>();
		ConstraintNode leftNode, rightNode;

		ConstraintNodeConvertVisitor cncv = null;
		try {
			cncv = new ConstraintNodeConvertVisitor(defaultSolver);
		} catch (InvalidConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (nodeList != null) {
			for (ConstraintNode cn : nodeList) {

				cn.setConvertVisitor(cncv);

				if (NodeType.isLeaf(cn.nodeType)) {
					nodeStack.push(cn);
				} else if (NodeType.isBinary(cn.nodeType)) {
					rightNode = nodeStack.pop();
					leftNode = nodeStack.pop();

					leftNode.parent = cn;
					rightNode.parent = cn;
					cn.leftChild = leftNode;
					cn.rightChild = rightNode;

					nodeStack.push(cn);
				} else if (NodeType.isNegation(cn.nodeType)) {
					leftNode = nodeStack.pop();

					leftNode.parent = cn;
					cn.leftChild = leftNode;
					cn.rightChild = null;
					nodeStack.push(cn);
				} else {
					try {
						throw new NotSupportedNodeTypeException();
					} catch (NotSupportedNodeTypeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} // endfor

			try {
				root = nodeStack.pop();
			} catch (EmptyStackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// add some listeners.
			root.nnfVisitor = new ConstraintNodeNNFVisitor();

		}
	}

	/**
	 * Zero parameter constructor. Does not set any field.
	 */
	public Constraint() {
	}

	/**
	 * Constraint instance constructor. It is necessary to specify an array of
	 * Constraint Nodes. Such an array will have the constraint specified in
	 * postfix notation.
	 * 
	 * In example , the infix expression A & (B|C)|(F&D) will be represented as an
	 * array of ConstraintNode object in the following order (postfix notation):
	 * ABC|&FDA|
	 * 
	 * @throws InvalidConfigurationException
	 */
	public Constraint(ConstraintNode nodeRoot) {
		ConstraintNodeConvertVisitor cncv = null;
		try {
			cncv = new ConstraintNodeConvertVisitor(defaultSolver);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		root = nodeRoot;

		// add a visitor
		root.nnfVisitor = new ConstraintNodeNNFVisitor();
		root.setConvertVisitor(cncv);

	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Constraint aClone = new Constraint();

		if (this.root != null)
			aClone.root = (ConstraintNode) this.root.clone();

		return aClone;
	}
	// ----------------- visitor based methods ------------------------//

	/**
	 * This is a method which is used to convert the constraint into Negation
	 * Normal Form.
	 * 
	 * @return void
	 */
	public void toNNF() {
		if (root.nodeType == NodeType.LOG_NOT) {
			root.leftChild.nnfVisitor = new ConstraintNodeNNFVisitor();
			root = root.leftChild; // remove root node
			root.parent = null;
			root.toNNF(true);
		}

		root.toNNF(false);
	};

	/**
	 * This is a method which is used to convert the constraint into
	 * BooleanFormulaobject which is used as an input to JAVA SMT API.
	 * 
	 * @return BooleanFormula object
	 */
	public BooleanFormula toJavaSMTConstraint() {
		return root.toJavaSMTConstraint();
	};

	// ----------------- visitor based methods ------------------------//
	// ----------------------------------------------------------------//

	/**
	 * Determines if this constraint is equal to another constraint.
	 * 
	 */
	public boolean equals(Constraint cst) {
		return root.equalTree(cst.root);
	}

	/**
	 * 
	 * @param varSnapshot
	 *          - valuation of the variables
	 * @param policy
	 *          - specifies the distance metrics
	 * @return
	 */
	public ArrayList<Double> satisfiedBy(HashMap<String, Variable> varSnapshot, SatisfactionPolicy policy) {
		// TODO Auto-generated method stub

		// System.out.println(this.toString());
		return root.satDegree(varSnapshot, policy);
	}

	public ConstraintNode getRoot() {
		return root;
	}

	public void setRoot(ConstraintNode root) {
		this.root = root;
	}

	// /**
	// * Auxiliary method to get the constraint object based on very basic string
	// parsing.
	// * @param str2parse
	// * @return
	// */
	// public static Constraint getBasicConstraintFromString(String str2parse){
	//
	// str2parse.spl
	//
	//
	//
	// }

	/**
	 * Contains a conversion function which helps to map ANTLR parser rule context
	 * objects to Node Types.
	 * 
	 * @param str
	 * @return
	 */
	public static NodeType convertToNodeType(String str) {

		// LOG_AND, LOG_OR, LOG_NOT, CMP_LESS, CMP_GREAT, CMP_EQ,
		// CMP_NEQ, CMP_LEQ, CMP_GEQ, LEAF_ID, LEAF_NUM;
		NodeType ntToRet = null;

		switch (str) {
		case "and":
			ntToRet = NodeType.LOG_AND;
			break;
		case "or":
			ntToRet = NodeType.LOG_OR;
			break;
		case "not":
			ntToRet = NodeType.LOG_NOT;
			break;
		case "<":
			ntToRet = NodeType.CMP_LESS;
			break;
		case ">":
			ntToRet = NodeType.CMP_GREAT;
			break;
		case "==":
			ntToRet = NodeType.CMP_EQ;
			break;
		case "!==":
			ntToRet = NodeType.CMP_NEQ;
			break;
		case "<=":
			ntToRet = NodeType.CMP_LEQ;
			break;
		case ">=":
			ntToRet = NodeType.CMP_GEQ;
			break;
		default:
			try {
				Integer.parseInt(str);
				ntToRet = NodeType.LEAF_NUM;
			} catch (NumberFormatException e) {
				try {
					Boolean.parseBoolean(str);
					ntToRet = NodeType.LEAF_BOOL;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ntToRet = NodeType.LEAF_ID;
			}
			break;
		}
		return ntToRet;
	}

	/**
	 * Returns a pointer to the constraint node we're looking for. Returns null if
	 * the constraint node is not found.
	 * 
	 * @param cn
	 * @return
	 */
	public ConstraintNode findConstraintNode(ConstraintNode cn) {

		ConstraintNode node2ret = null;

		if (root.equalTree(cn)) {
			return root;
		} else {

			if (root.leftChild != null) {
				if (root.leftChild.equalTree(cn)){
//					System.out.println("FOUND at root.leftChild"+root.leftChild);
					return root.leftChild;
				}
				else {
					node2ret = root.leftChild.findConstraintNode(cn);
					
					if (node2ret != null) {
//						System.out.println("FOUND at root.leftChild"+root.leftChild);
						return node2ret;
					}
				}
			}

			if (root.rightChild != null) {
				if (root.rightChild.equalTree(cn)) {
//					System.out.println("FOUND at root.rightChild"+root.rightChild);
					return root.rightChild;
				} else { 
					node2ret = root.rightChild.findConstraintNode(cn);
					
					if (node2ret != null) {
//						System.out.println("FOUND at root.rightChild"+root.rightChild);
						return node2ret;
					}
				}
			}
		}
		return null; // if nothing is found
	}

	/**
	 * This function will enable us to obtain Constraint object from textual
	 * representation (String).
	 * 
	 * @return
	 */
	public static Constraint getCstFromStr(String strCst) {
		Constraint cst2ret = null;
		ConstraintGenVisitor visitor = new ConstraintGenVisitor();

		CstLexer lexer = new CstLexer(CharStreams.fromString(""));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CstParser parser = new CstParser(tokens);
		ParseTree tree;

		lexer.setInputStream(CharStreams.fromString(strCst));
		tokens = new CommonTokenStream(lexer);
		parser.setTokenStream(tokens);

		tree = parser.cst();
		cst2ret = visitor.visit(tree);

		return cst2ret;
	}

	/**
	 * Sets the appropriate SolverContext to enable obtatining BooleanFormula from
	 * this Constraint.
	 * 
	 * @param context
	 */
	public void setConvertVisitor(ConstraintNodeConvertVisitor constraintNodeConvertVisitor) {
		root.setConvertVisitor(constraintNodeConvertVisitor);
	}

	/**
	 * Returns a new Constraint object which is a negation of *this* Constraint
	 * object.
	 * 
	 * @return
	 */
	public Constraint negate() {
		Constraint newCst = null;

		try {
			newCst = (Constraint) this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConstraintNode cstNode = new ConstraintNode(NodeType.LOG_NOT, "not");

		newCst.root.setParent(cstNode);
		cstNode.leftChild = newCst.root;
		newCst.root = cstNode;

		return newCst;
	}
	
	/**
	 * Simple reduction.
	 */
	public void reduceSimplify(){
		root.recursivelyReduce();		
	}


}
