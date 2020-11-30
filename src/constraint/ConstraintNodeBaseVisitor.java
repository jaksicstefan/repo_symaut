package constraint;

public interface ConstraintNodeBaseVisitor {
	
	/**
	 * 
	 * @param current
	 * @param toNegate
	 */
	public void visit(ConstraintNode current, boolean toNegate);


}
