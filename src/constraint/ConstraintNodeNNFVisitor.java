package constraint;

import java.io.Serializable;

import constraint.ConstraintNode.NodeType;

/***
 * ConstraingNodeNNFVisitor
 * @author JaksicS
 *
 * Code which implements constraint NNF conversion.
 */
public class ConstraintNodeNNFVisitor implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 565455972244219027L;

	/**
	 * 
	 * @param current: The node currently visited.
	 * @param toNegate: The information from the node invoking this 
	 * method on whether to negate the current node or not.
	 */
	public void visit(ConstraintNode current, boolean toNegate) {
		if (toNegate) {
			
			if (NodeType.isLogicalBinary(current.nodeType)) {
				current.invertNode(); // /* invert node type*/
				
				if (current.leftChild.nodeType == NodeType.LOG_NOT) {
					visit(current.leftChild, true);
				} else {
					ConstraintNode newNotNode1 = new ConstraintNode(
							NodeType.LOG_NOT, "not", current.leftChild);
					newNotNode1.parent = current;
					current.leftChild.parent = newNotNode1;
					current.leftChild = newNotNode1;
					visit(current.leftChild, false);
				}

				if (current.rightChild.nodeType == NodeType.LOG_NOT) {					
					visit(current.rightChild, true);
				} else {
					ConstraintNode newNotNode2 = new ConstraintNode(
							NodeType.LOG_NOT, "not", current.rightChild);
					newNotNode2.parent = current;
					current.rightChild.parent = newNotNode2;
					current.rightChild = newNotNode2;
					visit(current.rightChild, false);
				}
			} else if (NodeType.isComparison(current.nodeType)) {
				current.invertNode();
			} else if (current.nodeType == NodeType.LOG_NOT) {
				
				if (current.parent!= null)	 {		 //NOT NOT)		
					current.parent.replaceChild(current, current.leftChild);
					current.leftChild.parent = current.parent;
				}
				else { //in the root
					
					current.nodeType = current.leftChild.nodeType;
					current.strValue = current.leftChild.strValue;
					
					current.rightChild = current.leftChild.rightChild;
					current.leftChild = current.leftChild.leftChild;

					if (current.rightChild!= null) current.rightChild.parent = current;
					if (current.leftChild!= null) current.leftChild.parent  = current;
				}
				
				if (current.leftChild!= null)
				visit(current.leftChild, false);
			}

		} else { // no negate
			if (NodeType.isBinary(current.nodeType)) {
				visit(current.leftChild, toNegate);
				visit(current.rightChild, toNegate);
			} else if (current.nodeType == NodeType.LOG_NOT) {
				if (NodeType.isBinary(current.leftChild.nodeType)
						|| current.leftChild.nodeType == NodeType.LOG_NOT)
					current.parent.replaceChild(current, current.leftChild);
				visit(current.leftChild, true);
			}

		}

	}
}
