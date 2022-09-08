package edu.damago.java.tool;

import java.util.Objects;


/**
 * Facade for additional array operations.
 */
public class Containers {

	/**
	 * Prevents external instantiation.
	 */
	private Containers () {}


	/**
	 * Returns the leftmost position of the given value within the given array,
	 * or {@code -1} if the array does not contain the given value. 
	 * @param array the array
	 * @param value the value
	 * @return the leftmost position of the given value, or {@code -1} for none
	 */
	static public int indexOf (final byte[] array, final byte value) {
		for (int index = 0; index < array.length; ++index)
			if (array[index] == value) return index;

		return -1;
	}


	/**
	 * Returns the leftmost position of the given value within the given array,
	 * or {@code -1} if the array does not contain the given value. 
	 * @param array the array
	 * @param value the value
	 * @return the leftmost position of the given value, or {@code -1} for none
	 */
	static public int indexOf (final short[] array, final short value) {
		for (int index = 0; index < array.length; ++index)
			if (array[index] == value) return index;

		return -1;
	}


	/**
	 * Returns the leftmost position of the given value within the given array,
	 * or {@code -1} if the array does not contain the given value. 
	 * @param array the array
	 * @param value the value
	 * @return the leftmost position of the given value, or {@code -1} for none
	 */
	static public int indexOf (final int[] array, final int value) {
		for (int index = 0; index < array.length; ++index)
			if (array[index] == value) return index;

		return -1;
	}


	/**
	 * Returns the leftmost position of the given value within the given array,
	 * or {@code -1} if the array does not contain the given value. 
	 * @param array the array
	 * @param value the value
	 * @return the leftmost position of the given value, or {@code -1} for none
	 */
	static public int indexOf (final long[] array, final long value) {
		for (int index = 0; index < array.length; ++index)
			if (array[index] == value) return index;

		return -1;
	}


	/**
	 * Returns the leftmost position of the given value within the given array,
	 * or {@code -1} if the array does not contain the given value. 
	 * @param array the array
	 * @param value the value
	 * @return the leftmost position of the given value, or {@code -1} for none
	 */
	static public int indexOf (final char[] array, final char value) {
		for (int index = 0; index < array.length; ++index)
			if (array[index] == value) return index;

		return -1;
	}


	/**
	 * Returns the leftmost position of the given value within the given array,
	 * or {@code -1} if the array does not contain the given value. 
	 * @param array the array
	 * @param value the value
	 * @return the leftmost position of the given value, or {@code -1} for none
	 */
	static public int indexOf (final float[] array, final float value) {
		for (int index = 0; index < array.length; ++index)
			if (array[index] == value) return index;

		return -1;
	}


	/**
	 * Returns the leftmost position of the given value within the given array,
	 * or {@code -1} if the array does not contain the given value. 
	 * @param array the array
	 * @param value the value
	 * @return the leftmost position of the given value, or {@code -1} for none
	 */
	static public int indexOf (final double[] array, final double value) {
		for (int index = 0; index < array.length; ++index)
			if (array[index] == value) return index;

		return -1;
	}


	/**
	 * Returns the leftmost position of the given value within the given array,
	 * or {@code -1} if the array does not contain the given value. 
	 * @param array the array
	 * @param value the value
	 * @return the leftmost position of the given value, or {@code -1} for none
	 */
	static public int indexOf (final boolean[] array, final boolean value) {
		for (int index = 0; index < array.length; ++index)
			if (array[index] == value) return index;

		return -1;
	}


	/**
	 * Returns the leftmost position of the given value within the given array,
	 * or {@code -1} if the array does not contain the given value. 
	 * @param array the array
	 * @param value the value
	 * @return the leftmost position of the given value, or {@code -1} for none
	 */
	static public int indexOf (final Object[] array, final Object value) {
		for (int index = 0; index < array.length; ++index)
			if (Objects.equals(array[index], value)) return index;

		return -1;
	}


	/**
	 * Returns whether or not the given array contains the given value.
	 * @param array the array
	 * @param value the value
	 * @return {@code true} if the value is contained, {@code false} otherwise
	 */
	static public boolean contains (final byte[] array, final byte value) {
		return indexOf(array, value) != -1;
	}


	/**
	 * Returns whether or not the given array contains the given value.
	 * @param array the array
	 * @param value the value
	 * @return {@code true} if the value is contained, {@code false} otherwise
	 */
	static public boolean contains (final short[] array, final short value) {
		return indexOf(array, value) != -1;
	}


	/**
	 * Returns whether or not the given array contains the given value.
	 * @param array the array
	 * @param value the value
	 * @return {@code true} if the value is contained, {@code false} otherwise
	 */
	static public boolean contains (final int[] array, final int value) {
		return indexOf(array, value) != -1;
	}


	/**
	 * Returns whether or not the given array contains the given value.
	 * @param array the array
	 * @param value the value
	 * @return {@code true} if the value is contained, {@code false} otherwise
	 */
	static public boolean contains (final long[] array, final long value) {
		return indexOf(array, value) != -1;
	}


	/**
	 * Returns whether or not the given array contains the given value.
	 * @param array the array
	 * @param value the value
	 * @return {@code true} if the value is contained, {@code false} otherwise
	 */
	static public boolean contains (final char[] array, final char value) {
		return indexOf(array, value) != -1;
	}


	/**
	 * Returns whether or not the given array contains the given value.
	 * @param array the array
	 * @param value the value
	 * @return {@code true} if the value is contained, {@code false} otherwise
	 */
	static public boolean contains (final float[] array, final float value) {
		return indexOf(array, value) != -1;
	}


	/**
	 * Returns whether or not the given array contains the given value.
	 * @param array the array
	 * @param value the value
	 * @return {@code true} if the value is contained, {@code false} otherwise
	 */
	static public boolean contains (final double[] array, final double value) {
		return indexOf(array, value) != -1;
	}


	/**
	 * Returns whether or not the given array contains the given value.
	 * @param array the array
	 * @param value the value
	 * @return {@code true} if the value is contained, {@code false} otherwise
	 */
	static public boolean contains (final boolean[] array, final boolean value) {
		return indexOf(array, value) != -1;
	}



	/**
	 * Returns whether or not the given array contains the given value.
	 * @param array the array
	 * @param value the value
	 * @return {@code true} if the value is contained, {@code false} otherwise
	 */
	static public boolean contains (final Object[] array, final Object value) {
		return indexOf(array, value) != -1;
	}
}