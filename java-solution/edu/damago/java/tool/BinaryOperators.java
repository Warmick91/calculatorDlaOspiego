package edu.damago.java.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;


/**
 * Facade providing operations for binary operators,
 * i.e. operators featuring exactly two parameters.
 */
public class BinaryOperators {
	static public final Map<String,DoubleBinaryOperator> POOL = new HashMap<>();
	static {
		POOL.put("+", (l, r) -> l + r);
		POOL.put("-", (l, r) -> l - r);
		POOL.put("*", (l, r) -> l * r);
		POOL.put("/", (l, r) -> l / r);
		POOL.put("%", (l, r) -> l % r);
		POOL.put("**", (l, r) -> Math.pow(l, r));
		POOL.put("root", (l, r) -> Math.pow(r, 1 / l));
		POOL.put("log", (l, r) -> Math.log(r) / Math.log(l));
	}
}