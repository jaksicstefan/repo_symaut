package constraint;

import java.util.BitSet;

public class BitArray extends BitSet {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * Concatenates two bitSet objects
	 * 
	 * @param toConcat
	 * @return
	 */

	public static BitSet concatenate_vectors(BitSet vector_1_in, BitSet vector_2_in) {
		BitSet vector_1_in_clone = (BitSet) vector_1_in.clone();
		BitSet vector_2_in_clone = (BitSet) vector_2_in.clone();
		
		BitSet vectorConcat = new BitSet(vector_1_in.length() + vector_2_in.length());
		
		int index = -1;
		while (index < (vector_2_in_clone.length() - 1)) {
			index = vector_2_in_clone.nextSetBit((index + 1));
			vectorConcat.set(index);
		}
		
		index = -1;
		while (index < (vector_1_in_clone.length() - 1)) {
			index = vector_1_in_clone.nextSetBit((index + 1));
			vectorConcat.set(vector_2_in_clone.length() + index);
		}

		return vectorConcat;
	}

	public static void main(String args[]) {
		BitSet bsbs = new BitSet();
		bsbs.set(0, 3);
		
		BitSet bsbsA = new BitSet(4);
		bsbsA.set(3);

		
		System.out.println("concatenated "+ bsbs +" and " + bsbsA  +" equals "	+ BitArray.concatenate_vectors(bsbs, bsbsA));
		
	}

}
