package edu.damago.java.tool;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Facade for additional math operations.
 */
public class Maths {
	static private final Random RANDOMIZER = new SecureRandom();

	/**
	 * Prevents external instantiation.
	 */
	private Maths () {}


	/**
	 * Returns the minimum of the given values.
	 * @param values the values as a var-arg array
	 * @return the minimum
	 */
	static public double min (final double... values) {
		double minimum = values[0];
		for (int index = 1; index < values.length; ++index)
			minimum = Math.min(minimum, values[index]);

		return minimum;
	}


	/**
	 * Returns the maximum of the given values.
	 * @param values the values as a var-arg array
	 * @return the maximum
	 */
	static public double max (final double... values) {
		double maximum = values[0];
		for (int index = 1; index < values.length; ++index)
			maximum = Math.max(maximum, values[index]);

		return maximum;
	}


	/**
	 * Returns the {@code Euclidean modulo} of the given dividend and divisor, based on the
	 * {@code truncated modulo} returned by the {@code %} operator. Note that both the
	 * truncated modulo and the divisor absolute are precalculated in order to profit
	 * from both supersymmetric computation and branch-free conditional assignment (<i>CMOV</i>).
	 * @param x the dividend value
	 * @param y the divisor value
	 * @return the value <tt>x mod<sub>e</sub> y</tt> within range {@code [0,|y|[},
	 * 			or {@code NaN} if the given divisor is zero
	 */
	static public double mod (final double x, final double y) {
		final double mod = x % y, abs = Math.abs(y);
		return x >= 0 ? mod : abs + mod;
	}


	/**
	 * Returns the root of the given value relative to the given base. 
	 * @param base the base
	 * @param value the value
	 * @return the root calculated, or {@code NaN} if any the given base is negative
	 */
	static public double root (final double base, final double value) {
		return Math.pow(value, 1 / base);
	}


	/**
	 * Returns the logarithm of the given value relative to the given base. 
	 * @param base the base
	 * @param value the value
	 * @return the logarithm calculated, or {@code NaN} if any of the given arguments is negative
	 */
	static public double log (final double base, final double value) {
		return Math.log(value) / Math.log(base);
	}


	/**
	 * Returns the faculty of the given value. 
	 * @param value the value
	 * @return the faculty calculated
	 * @throws IllegalArgumentException if the given value is outside range [1, 20]
	 */
	static public long faculty (final byte value) {
		if (value < 1 | value > 20) throw new IllegalArgumentException(String.valueOf(value));
		long product = 1;

		for (int index = 2; index <= value; ++index)
			product *= index;

		return product;
	}


	/**
	 * Returns a secure random integer between {@code 0} (inclusive) and the given limit (exclusive).
	 * @param limit the upper limit (exclusive)
	 * @return the secure random integer
	 * @throws IllegalArgumentException if the given limit is negative
	 */
	static public int randomInt (final int limit) throws IllegalArgumentException {
		if (limit <= 0) throw new IllegalArgumentException();

		return RANDOMIZER.nextInt(limit);
	}


	/**
	 * Returns a secure random character between {@code A} (inclusive) and the given limit (exclusive).
	 * @param limit the upper limit (exclusive)
	 * @return the secure random character
	 * @throws IllegalArgumentException if the given limit is negative or exceeds the available number
	 * 			of upper-case Roman characters (26) 
	 */
	static public char randomChar (final int limit) throws IllegalArgumentException {
		if (limit <= 0 | limit > 'Z' - 'A' + 1) throw new IllegalArgumentException();

		return (char) ('A' + RANDOMIZER.nextInt(limit));
	}
}