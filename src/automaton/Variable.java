package automaton;

import java.io.Serializable;

/***
 * @author JaksicS
 *
 *         Class Variable represents automaton variable.
 * 
 */
public class Variable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3043516551474000650L;

	/**
	 * TODO - write comment here - BTW is 
	 * this member actually necessary?
	 */
	public static int classID = 0;

	/***
	 * Identifier for a variable
	 */
	public int id;

	/***
	 * Name of the variable.
	 */
	private String name;

	/***
	 * Value of the variable.
	 */
	private int value;

	/***
	 * Minimum value of the variable range.
	 */
	private int minValue;

	/***
	 * Maximum value of the variable range.
	 */
	private int maxValue;

	/***
	 * In case the amplitude range is shifted, due to original negative minValue
	 * then it will show how much the range is shifted.
	 */
	private int ampShift;

	/***
	 * Total maximum value of all the variables. Variables with different ranges
	 * must be normalized when calculating the distance. Total Maximum Value will
	 * be used to calculate the scaling factor.
	 */
	private static int totalMaxValue;

	/**
	 * TOtal number of variables _ TODO - this field needs revision
	 */

	public static int numVars;

	/***
	 * Designates whether the variable is an input variable.
	 */
	@SuppressWarnings("unused")
	private boolean isInputVar;

	// ----------------------------- //
	// ---------- methods ---------- //

	public Variable(String name, int minRange, int maxRange, boolean isInput) {
		this.id = classID++;

		this.name = name;
		this.value = 0;

		if (minRange < 0) {
			minValue = 0;
			ampShift = Math.abs(minRange);
			maxValue = maxRange + ampShift;
		} else {
			minValue = minRange;
			maxValue = maxRange;
			ampShift = 0;
		}

		if (maxValue > totalMaxValue) {
			totalMaxValue = maxValue;
		}

		this.isInputVar = isInput;
	}

	/***
	 * Prints the variable
	 */
	public String toString() {
		return name /* + " = " + value */;
	}

	/**
	 * Implementing the Object.equals method used to compare the objects.
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof Variable) {

			Variable objVar = (Variable) obj;

			// simple checks
			if (!(this.name.equalsIgnoreCase(objVar.name)))
				return false;

			if (!(this.value == objVar.value))
				return false;

			if (!(this.minValue == objVar.minValue))
				return false;

			if (!(this.maxValue == objVar.maxValue))
				return false;

			if (!(this.ampShift == objVar.ampShift))
				return false;

			// ID not used
			// if (!(this.id == objVar.id))
			// return false;
			return true;
		}
		else
			return false;
	}

	// ////////////////////////////////////////////////////////
	/* GETTERS AND SETTERS */

	/***
	 *
	 */
	public int getMinValue() {
		return minValue;
	}

	/***
	 *
	 */
	public void setMinValue(int minValue) {
		// TODO - make sure that minValue always > maxValue
		this.minValue = minValue;
	}

	/***
	 *
	 */
	public int getMaxValue() {
		return maxValue;
	}

	/***
	 *
	 */
	public void setMaxValue(int maxValue) {
		// TODO - make sure that maxValue always > minValue
		this.maxValue = maxValue;
	}

	/***
	 * Getter for field: name.
	 */
	public String getName() {
		return name;
	}

	/***
	 * Setter for field: name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/***
	 * Getter for field: value.
	 */
	public int getValue() {
		return value;
	}

	/***
	 * Setter for field: value
	 */
	public void setValue(int value) {
		// System.out.println("setting value" + value);
		int aux_val = value + ampShift;

		if ((aux_val > maxValue) || (aux_val < minValue)) {
			System.err.println("Variable::setValue : Variable value for var: " + name + " is out of range ["+ minValue +","+ maxValue +"].");
		}

		this.value = aux_val;
	}

	public static int getClassID() {
		return classID;
	}

	public static int getTotalMaxValue() {
		return totalMaxValue;
	}

	/**
	 * If this is boolean variable return TRUE.
	 * 
	 * @return
	 */
	public boolean isBoolean() {
		if ((minValue == 0) && (maxValue == 1))
			return true;
		else
			return false;
	}

	/**
	 * Creates a brand new output variable.
	 * 
	 * @return
	 */
	public static Variable getFreshOutVar() {
		Variable v = new Variable("u" + classID, 0, totalMaxValue, false);
		classID++;
		return v;
	}

	public static void setTotalMaxValue(int totalMaxValue) {
		Variable.totalMaxValue = totalMaxValue;
	}

}