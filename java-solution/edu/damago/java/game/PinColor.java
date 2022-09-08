package edu.damago.java.game;


/**
 * Instances represent pin colors.
 */
public enum PinColor {
	YELLOW('Y'),
	ORANGE('O'),
	RED('R'),
	GREEN('G'),
	BLUE('B'),
	CYAN('C'),
	PINK('P'),
	MAGENTA('M'),
	SILVER('S'),
	GOLD('D'),
	WHITE('W'),
	BLACK('K');

	private final char symbol;


	/**
	 * Initializes a new instance.
	 * @param symbol the symbol
	 */
	private PinColor (final char symbol) {
		this.symbol = symbol;
	}


	/**
	 * Returns the symbol.
	 * @return the symbol
	 */
	public char symbol () {
		return this.symbol;
	}


	/**
	 * Returns the pin color matching the given symbol.
	 * @param symbol the symbol
	 * @return the matching pin color
	 * @throws IllegalArgumentException if the given symbol is not one of 
	 * 	Y, O, R, G, B, C, P, M, S, D, W, K
	 */
	static public PinColor valueOf (final char symbol) {
		for (final PinColor pinColor : PinColor.values())
			if (pinColor.symbol == symbol) return pinColor;

		throw new IllegalArgumentException("illegal pin symbol!");
	}
}