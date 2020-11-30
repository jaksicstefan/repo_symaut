package constraint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.sosy_lab.java_smt.api.BooleanFormula;
import org.sosy_lab.java_smt.api.SolverContext;

import policy.SatisfactionPolicy;
import automaton.Variable;

/***
 * 
 * @author JaksicS
 * 
 *         Every transition in symbolic automaton has an associated constraint.
 *         This class represents a single constraint node in a tree-like
 *         constraint representation.
 * 
 *
 */
public class ConstraintNode implements Serializable {

	// ===============================================================================
	// ///////////////////////// NODE TYPE enum definition
	// //////////////////////////

	/**
	 * 
	 */
	private static final long serialVersionUID = -2972913242444158893L;

	/***
	 * 
	 * @author JaksicS: enumeration used to describe different node types in
	 *         constraint tree
	 *
	 */
	public enum NodeType {
		LOG_AND, LOG_OR, LOG_NOT, CMP_LESS, CMP_GREAT, CMP_EQ, CMP_NEQ, CMP_LEQ, CMP_GEQ, LEAF_ID, LEAF_NUM, LEAF_BOOL;

		/**
		 * NodeTtype class for designating different node types.
		 * 
		 * @param nt
		 * @return
		 */
		public static NodeType getInverted(NodeType nt) {

			switch (nt) {
			case LOG_AND:
				return LOG_OR;
			case LOG_OR:
				return LOG_AND;
			case CMP_LESS:
				return CMP_GEQ;
			case CMP_GREAT:
				return CMP_LEQ;
			case CMP_EQ:
				return CMP_NEQ;
			case CMP_NEQ:
				return CMP_EQ;
			case CMP_LEQ:
				return CMP_GREAT;
			case CMP_GEQ:
				return CMP_LESS;

			default:
				System.err.println("value " + nt + " cannot be inverted.");
				try {
					throw new NonInvertableNodeTypeException();
				} catch (NonInvertableNodeTypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return LEAF_NUM;
				}
			}// end switch

		}

		/**
		 * If the node is binary operator, return true.
		 * 
		 * @param nt
		 * @return
		 */
		public static boolean isLogicalBinary(NodeType nt) {
			return ((nt == NodeType.LOG_AND) || (nt == NodeType.LOG_OR)) ? true : false;
		}

		public static boolean isComparison(NodeType nt) {
			return ((nt == NodeType.CMP_LESS) || (nt == NodeType.CMP_GREAT) || (nt == NodeType.CMP_EQ)
					|| (nt == NodeType.CMP_NEQ) || (nt == NodeType.CMP_LEQ) || (nt == NodeType.CMP_GEQ)) ? true : false;
		}

		public static boolean isBinary(NodeType nt) {
			return (isLogicalBinary(nt) || isComparison(nt));
		}

		public static boolean isLeaf(NodeType nt) {
			return (nt == NodeType.LEAF_ID || nt == NodeType.LEAF_NUM || nt == NodeType.LEAF_BOOL);
		}

		public static boolean isNegation(NodeType nt) {
			return (nt == NodeType.LOG_NOT);
		}

	}// end enum class

	// =========================================================================
	// /////////////////////////// M E M B E R S /////////////////////////////

	/***
	 * 
	 */
	private static int cstNodeCounterID = 0;

	/***
	 * Unique node identification number.
	 */
	private int nodeID;

	/***
	 * Type of the constraint node.
	 */
	protected NodeType nodeType;

	/***
	 * String represented by this terminal or an operator string.
	 * 
	 */
	protected String strValue;

	/***
	 * Parent Node
	 * 
	 */
	protected ConstraintNode parent;

	/***
	 * Left child of a constraint node. Null if this is a leaf (terminal) node.
	 * 
	 */
	protected ConstraintNode leftChild;

	/***
	 * Right child of a constraint node. Used only in case of binary operators or
	 * comparison operators.
	 * 
	 */
	protected ConstraintNode rightChild;

	/***
	 * visitor class to separate NNF code
	 * 
	 */
	protected ConstraintNodeNNFVisitor nnfVisitor;

	/**
	 * Visitor which converts to Boolean formula.
	 */
	protected ConstraintNodeConvertVisitor convertVisitor;

	// =========================================================================
	// /////////////////////////// M E T H O D S /////////////////////////////

	/***
	 * 
	 * @param nt
	 * @param strVal
	 */
	public ConstraintNode(NodeType nt, String strVal) {
		this.nodeType = nt;
		this.strValue = strVal;
		this.nodeID = cstNodeCounterID++;
	}

	/***
	 * 
	 * @param nt
	 * @param strVal
	 * @param leftChild
	 */
	public ConstraintNode(NodeType nt, String strVal, ConstraintNode leftChild) {
		this.nodeType = nt;
		this.strValue = strVal;
		this.leftChild = leftChild;
		this.nodeID = cstNodeCounterID++;
	}

	/***
	 * 
	 * @param nt
	 * @param strVal
	 * @param leftChild
	 * @param rightChild
	 */
	public ConstraintNode(NodeType nt, String strVal, ConstraintNode leftChild, ConstraintNode rightChild) {
		this.nodeType = nt;
		this.strValue = strVal;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.nodeID = cstNodeCounterID++;
	}

	/***
	 * This is a recursive method which is used to convert the constraint into
	 * Negation Normal Form. It is invoked top-down, inverting constraint nodes if
	 * necessary.
	 * 
	 * @return void
	 */
	public void toNNF(boolean toNegate) {
		nnfVisitor.visit(this, toNegate);
	};

	/***
	 * 
	 * @return
	 */
	public ConstraintNode getParent() {
		return parent;
	}

	/***
	 * 
	 * @return
	 */
	public void setParent(ConstraintNode parent) {
		this.parent = parent;
	}

	/***
	 * This method replaces a child in ConstraintNode object, specified with the
	 * current node , with new child node specified by newChild
	 */
	public void replaceChild(ConstraintNode current, ConstraintNode newChild) {

		if (this.leftChild.equals(current))
			this.leftChild = newChild; // by definition, there will be no right
		// node of the LOG_NOT node
		else if (this.rightChild.equals(current))
			this.rightChild = newChild;
		else
			System.err.println("error when replacing the child node. Node to be replaced is not found.");
	}

	/***
	 * Returns the node as a string
	 */
	public String toString() {
		// return "nodeID = " + nodeID + "nodeType = " + nodeType.toString() +
		// "; strValue = " + strValue;
		return "nodeType = " + nodeType.toString() + "; strValue = " + strValue;
	}

	/***
	 * Prints the node inline
	 */
	public String toStringInline() {
		// return "nodeID = " + nodeID + "nodeType = " + nodeType.toString() +
		// "; strValue = " + strValue;
		StringBuffer toReturn = new StringBuffer();

		if (this.leftChild != null)
			toReturn.append(this.leftChild.toStringInline());

		toReturn.append(" " + this.strValue + " ");

		if (this.rightChild != null)
			toReturn.append(this.rightChild.toStringInline());

		return toReturn.toString();
	}

	/***
	 * Prints the node and the tree below in human-readable format
	 * 
	 */
	public String toStringTree() {
		StringBuffer strBuf = new StringBuffer();
		printSubtree(strBuf, "", true);

		return strBuf.toString();
	}

	/***
	 * Print the entire subtree recursively.
	 * 
	 * @param prefix
	 * @param isTail
	 */
	private void printSubtree(StringBuffer strBuf, String prefix, boolean isTail) {

		strBuf.append(prefix + (isTail ? "└── " : "├── ") + strValue);
		strBuf.append("\n");

		if ((this.leftChild != null) && (this.rightChild != null)) {
			this.leftChild.printSubtree(strBuf, prefix + (isTail ? "    " : "│   "), false);
		} else if ((this.leftChild != null) && (this.rightChild == null)) {
			this.leftChild.printSubtree(strBuf, prefix + (isTail ? "    " : "│   "), true);
		}

		if (this.rightChild != null) {
			this.rightChild.printSubtree(strBuf, prefix + (isTail ? "    " : "│   "), true);
		}
		// System.err.println(strBuf.toString());
	}

	/***
	 * Function to compare the nodes for equality.
	 * 
	 * @param cNode
	 * @return
	 */
	private boolean equals(ConstraintNode cNode) {

		if (this.nodeType != cNode.nodeType)
			return false;

		if ((NodeType.isLeaf(nodeType)) && (!this.strValue.equalsIgnoreCase(cNode.strValue)))
			return false;

		return true;
	}

	/**
	 * 
	 * @param cNode
	 * @return
	 */
	private boolean recursivelyEquals(ConstraintNode cNode) {
		boolean b2return = true;

		if (this.nodeType != cNode.nodeType)
			return false;

		if ((NodeType.isLeaf(nodeType)) && (!this.strValue.equalsIgnoreCase(cNode.strValue)))
			return false;

		if (!(NodeType.isLeaf(nodeType))) {
			if ((this.leftChild != null) && (cNode.leftChild != null))
				b2return = b2return && this.leftChild.recursivelyEquals(cNode.leftChild);
			else if ((this.leftChild == null) ^ (cNode.leftChild == null))
				return false;

			if ((this.rightChild != null) && (cNode.rightChild != null))
				b2return = b2return && this.rightChild.recursivelyEquals(cNode.rightChild);
			else if ((this.rightChild == null) ^ (cNode.rightChild == null))
				return false;
		}

		// System.out.println("recursivelyEquals::returns true :::::"+b2return);

		// System.out.println(this.toStringTree() + "\n" + cNode.toStringTree());

		return b2return;
	}

	/***
	 * Returns true if the current node and the given node have same trees.
	 * 
	 * @param cNode
	 * @return
	 */
	public boolean equalTree(ConstraintNode cNode) {
		if ((this.leftChild != null) ^ (cNode.leftChild != null))
			return false;

		if ((this.rightChild != null) ^ (cNode.rightChild != null))
			return false;

		if (this.nodeType != cNode.nodeType)
			return false;

		if ((NodeType.isLeaf(nodeType)) && (!this.strValue.equalsIgnoreCase(cNode.strValue))
				&& (this.nodeType == cNode.nodeType))
			return false;

		if (leftChild != null) {
			if (!this.leftChild.equalTree(cNode.leftChild)) {
				return false;
			}
		}

		if (rightChild != null) {
			if (!this.rightChild.equalTree(cNode.rightChild)) {
				return false;
			}
		}
		return true;
	}

	/***
	 * 
	 * invert the node value
	 * 
	 */
	public void invertNode() {
		this.nodeType = NodeType.getInverted(this.nodeType); /* invert node type */
		String newString;

		switch (this.nodeType) {
		case LOG_AND:
			newString = "and";
			break;
		case LOG_OR:
			newString = "or";
			break;
		case CMP_LESS:
			newString = "<";
			break;
		case CMP_GREAT:
			newString = ">";
			break;
		case CMP_EQ:
			newString = "==";
			break;
		case CMP_NEQ:
			newString = "!==";
			break;
		case CMP_LEQ:
			newString = "<=";
			break;
		case CMP_GEQ:
			newString = ">=";
			break;
		default:
			newString = " NO STRING";
			break;
		}

		this.strValue = newString;

	}

	/**
	 * Calculates whether constraint represented by this node is satisfied by the
	 * variables from snapshot. The satisfaction grade is calculated according to
	 * WeighingPolicy.
	 * 
	 * @param varSnapshot
	 * @param policy
	 * @return In general case, N-dimensional satisfaction/distance information
	 */
	public ArrayList<Double> satDegree(HashMap<String, Variable> varSnapshot, SatisfactionPolicy policy) {
		// TODO TODO - does not prevent from malformed constraint trees.
		ArrayList<Double> leftDistArr, rightDistArr;

		if (NodeType.isBinary(nodeType)) {
			leftDistArr = leftChild.satDegree(varSnapshot, policy);
			rightDistArr = rightChild.satDegree(varSnapshot, policy);
		} else {
			// System.err.println("SKIPPING Unary/leaf NODE :"+ this.strValue);
			// skipping leaf nodes
			// due to NNF pre-processing there may be no unary nodes at this point
			if (!NodeType.isLeaf(nodeType)) {
				System.err.println("Unary node " + this.strValue + " not expected!");
			}
			return new ArrayList<Double>();
		}
		// System.err.println("NO UNARY OPS ALLOWED AT THIS POINT");

		if (NodeType.isLogicalBinary(nodeType)) // i.e AND,OR nodes
			return policy.getCost(leftDistArr, rightDistArr, nodeType);
		else if (NodeType.isComparison(this.nodeType)) {
			return policy.getDistanceFromSet(varSnapshot, leftChild.strValue, nodeType,
					Integer.parseInt(rightChild.strValue)); // i.e.
																									// x<4
		}

		System.err.println("THE SATISFACTION NOT CALLED ON BINARY NOR COMPARISON TYPE OF NODE");
		return new ArrayList<Double>(); // this replaces return 0
	}

	public int getNodeID() {
		return nodeID;
	}

	public NodeType getNodeType() {
		return nodeType;
	}

	public String getStrValue() {
		return strValue;
	}

	public ConstraintNode getLeftChild() {
		return leftChild;
	}

	public ConstraintNode getRightChild() {
		return rightChild;
	}

	public ConstraintNodeNNFVisitor getNnfVisitor() {
		return nnfVisitor;
	}

	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public void setLeftChild(ConstraintNode leftChild) {
		this.leftChild = leftChild;
	}

	public void setRightChild(ConstraintNode rightChild) {
		this.rightChild = rightChild;
	}

	public void setNnfVisitor(ConstraintNodeNNFVisitor nnfVisitor) {
		this.nnfVisitor = nnfVisitor;
	}

	public ConstraintNodeConvertVisitor getConvertVisitor() {
		return convertVisitor;
	}

	public void setConvertVisitor(ConstraintNodeConvertVisitor convertVisitor) {
		this.convertVisitor = convertVisitor;
		if (leftChild != null)
			leftChild.setConvertVisitor(convertVisitor);
		if (rightChild != null)
			rightChild.setConvertVisitor(convertVisitor);
	}

	public BooleanFormula toJavaSMTConstraint() {
		// TODO Auto-generated method stub
		return convertVisitor.visit(this);
	}

	public void setSolverContext(SolverContext ctx) {
		this.convertVisitor.setSolverContext(ctx);
	}

	protected Object clone() throws CloneNotSupportedException {
		ConstraintNode aClone;
		aClone = new ConstraintNode(this.nodeType, this.strValue);
		// aClone.parent = this.parent;
		aClone.nodeID = this.nodeID;
		aClone.nnfVisitor = new ConstraintNodeNNFVisitor();
		aClone.convertVisitor = this.convertVisitor;

		if (this.leftChild != null) {
			aClone.leftChild = (ConstraintNode) this.leftChild.clone();
			aClone.leftChild.setParent(aClone);
		}

		if (this.rightChild != null) {
			aClone.rightChild = (ConstraintNode) this.rightChild.clone();
			aClone.rightChild.setParent(aClone);
		}

		return aClone;
	}

	public ConstraintNode findConstraintNode(ConstraintNode cn) {
		ConstraintNode node2ret = null;

		if (this.equalTree(cn)) {
			// System.out.println("FOUND this"+this);
			return this;
		} else {

			if (this.leftChild != null) {
				if (this.leftChild.equalTree(cn)) {
					// System.out.println("FOUND left this"+this.leftChild);
					return this.leftChild;
				} else {
					node2ret = this.leftChild.findConstraintNode(cn);
					if (node2ret != null) {
						// System.out.println("FOUND left"+node2ret);
						return node2ret;
					}
				}
			}

			if (this.rightChild != null) {
				if (this.rightChild.equalTree(cn)) {
					// System.out.println("FOUND right this"+this.rightChild);
					return this.rightChild;
				} else {
					node2ret = this.rightChild.findConstraintNode(cn);
					if (node2ret != null) {
						// System.out.println("FOUND right"+node2ret);
						return node2ret;
					}
				}
			}
		}
		return null; // if nothing is found }
	}

	/**
	 * Simple reduction of "q and q" to "q".
	 */
	public ConstraintNode toSingleVar() {
		ConstraintNode nuNode = null;

		if (this.nodeType == NodeType.LOG_AND) {
			if (this.leftChild.recursivelyEquals(this.rightChild)) {
				// System.out.println("creating nu Node");
				nuNode = new ConstraintNode(this.leftChild.nodeType, this.leftChild.strValue, this.leftChild.leftChild,
						this.leftChild.rightChild);
				nuNode.parent = this.parent;
			}
		}
		return nuNode;
	}

	/**
	 * 
	 * @return
	 */
	public void recursivelyReduce() {
		// check if we can minimize nested
		// AND , and minimize
		checkAndMinimizeNestedAnd();
		
		ConstraintNode cn = this.toSingleVar();

		if (cn != null) {
//			System.out.println("CST: \n" + this.toStringTree() + "\nREDUCED to \n" + cn.toStringTree());
			this.setAll(cn);
		} else if (this.leftChild != null)
			this.leftChild.recursivelyReduce();
		else if (this.rightChild != null)
			this.rightChild.recursivelyReduce();
	}

	/**
	 * Used to minimize constraint such as
	 * 
	 * p and q and q
	 * 
	 * to
	 * 
	 * 
	 * p and q
	 */
	private boolean checkAndMinimizeNestedAnd() {

		if ((this.leftChild == null) || (this.rightChild == null))
			return false;

		if (this.leftChild.nodeType == NodeType.LOG_AND) {

			if ((this.leftChild.leftChild == null) || (this.leftChild.rightChild == null))
				return false;

			if (this.rightChild.recursivelyEquals(this.leftChild.leftChild)) {
				swap(this, this.leftChild);
				return true;
			} else if (this.rightChild.recursivelyEquals(this.leftChild.rightChild)) {
				swap(this, this.leftChild);
				return true;
			}
		} else if (this.rightChild.nodeType == NodeType.LOG_AND) {

			if ((this.rightChild.leftChild == null) || (this.rightChild.rightChild == null))
				return false;

			if (this.leftChild.recursivelyEquals(this.rightChild.leftChild)) {
				swap(this, this.rightChild);
				return true;
			} else if (this.leftChild.recursivelyEquals(this.rightChild.rightChild)) {
				swap(this, this.rightChild);
				return true;
			}
		}

		return false;

	}

	/**
	 * replaces cn1 with cn2 in a tree
	 * 
	 * @param constraintNode
	 * @param leftChild2
	 */
	private void swap(ConstraintNode cn1, ConstraintNode cn2) {
		// System.out.println("swapping(" + cn1.toStringTree() + " with " +
		// cn2.toStringTree());
		cn1.setAll(cn2);
	}

	/**
	 * 
	 * @param cn
	 */
	private void setAll(ConstraintNode cn) {

		// System.out.println("invoking set all for \n" + this.toStringTree());
		// System.out.println("to be replaced with \n" + cn.toStringTree());
		// this.convertVisitor = cn.convertVisitor;
		this.leftChild = cn.leftChild;
		// this.nnfVisitor = cn.nnfVisitor;
		this.nodeID = cn.nodeID;
		this.nodeType = cn.nodeType;
		this.parent = cn.parent;
		this.rightChild = cn.rightChild;
		this.strValue = cn.strValue;
	};

}
