package edu.damago.java.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.damago.java.tool.Maths;
import edu.damago.java.tool.Strings;


/**
 * Instances of this class represent Mastermind boards.
 */
public class MastermindBoard {
	static private final int MAX_PIN_COLOR_COUNT = PinColor.values().length;

	private int pinCount;
	private int pinColorCount;
	private int guessCount;
	private final List<PinColor> secret;
	private final List<List<PinColor>> guesses;


	/**
	 * Initializes a new instance using the original board geometry.
	 */
	public MastermindBoard () {
		this(4, 6, 12);
	}


	/**
	 * Initializes a new instance.
	 * @param pinCount the pin count
	 * @param pinColorCount the pin color count
	 * @param guessCount the guess count
	 * @throws IllegalArgumentException if any of the given values are negative,
	 *    or if the pin color count exceeds twelve
	 */
	public MastermindBoard (final int pinCount, final int pinColorCount, final int guessCount) throws IllegalArgumentException {
		this.secret = new ArrayList<>();
		this.guesses = new ArrayList<>();
		this.reset(pinCount, pinColorCount, guessCount);
	}


	/**
	 * Resets this board.
	 * @param pinCount the pin count
	 * @param pinColorCount the pin color count
	 * @param guessCount the guess count
	 * @throws IllegalArgumentException if any of the given values are negative,
	 *    or if the pin color count exceeds twelve
	 */
	public void reset (final int pinCount, final int pinColorCount, final int guessCount) {
		if (pinCount <= 0 | pinColorCount <= 0 | pinColorCount > MAX_PIN_COLOR_COUNT | guessCount <= 0) throw new IllegalArgumentException();

		this.pinCount = pinCount;
		this.pinColorCount = pinColorCount;
		this.guessCount = guessCount;
		this.secret.clear();
		this.guesses.clear();

		final PinColor[] pinColors = PinColor.values(); 
		for (int loop = 0; loop < this.pinCount; ++loop) {
			final int ordinal = Maths.randomInt(this.pinColorCount);
			this.secret.add(pinColors[ordinal]);
		}
	}


	/**
	 * Returns the pin count. 
	 * @return the pin count
	 */
	public int getPinCount () {
		return this.pinCount;
	}


	/**
	 * Returns the pin color count. 
	 * @return the pin color count
	 */
	public int getPinColorCount () {
		return this.pinColorCount;
	}


	/**
	 * Returns the guess count. 
	 * @return the guess count
	 */
	public int getGuessCount () {
		return this.guessCount;
	}


	/**
	 * Returns the secret. 
	 * @return the secret
	 */
	public List<PinColor> getSecret () {
		return this.secret;
	}


	/**
	 * Returns the guesses. 
	 * @return the guesses
	 */
	public List<List<PinColor>> getGuesses () {
		return this.guesses;
	}


	/**
	 * Returns whether or not this board is won. 
	 * @return {@code true} if this board is won, {@code false} otherwise  
	 */
	public boolean isWon () {
		return !this.guesses.isEmpty() && this.guesses.size() <= this.guessCount && this.secret.equals(this.guesses.get(this.guesses.size() - 1));
	}


	/**
	 * Returns whether or not this board is lost. 
	 * @return {@code true} if this board is lost, {@code false} otherwise  
	 */
	public boolean isLost () {
		return !this.isWon() && this.guesses.size() >= this.guessCount;
	}


	/**
	 * Adds the given guess to this game.
	 * @param guess the guess combination
	 * @throws NullPointerException if the given argument is {@code null}
	 * @throws IllegalArgumentException if the given combination does not share the game secret's length
	 * @throws IllegalStateException if this game is already won or lost 
	 */
	public void guess (final List<PinColor> guess) throws NullPointerException, IllegalArgumentException, IllegalStateException {
		if (guess.size() != this.secret.size()) throw new IllegalArgumentException();
		if (this.isWon() | this.isLost()) throw new IllegalStateException("match is already over!");

		this.guesses.add(guess);
	}

	
	/**
	 * Evaluates the given guess.
	 * @param guess the guess combination
	 * @return an array with two elements (the number of hits and near-misses)
	 * @throws NullPointerException if the given argument is {@code null}
	 * @throws IllegalArgumentException if the given combination does not share the game secret's length
	 * @throws IllegalStateException if this game is already won or lost 
	 */
	public int[] evaluate (final List<PinColor> guess) throws NullPointerException, IllegalArgumentException {
		if (guess.size() != this.secret.size()) throw new IllegalArgumentException();

		final Map<PinColor,Integer> pinColorCounts = new HashMap<>();
		for (final PinColor pinColor : this.secret)
			pinColorCounts.put(pinColor, pinColorCounts.getOrDefault(pinColor, 0) + 1);

		final Map<PinColor,Integer> hitCounts = new HashMap<>();
		final Map<PinColor,Integer> missCounts = new HashMap<>();
		for (int file = 0; file < this.secret.size(); ++file) {
			final PinColor pinColor = guess.get(file);
			if (pinColor == this.secret.get(file))
				hitCounts.put(pinColor, hitCounts.getOrDefault(pinColor, 0) + 1);
			else 
				missCounts.put(pinColor, missCounts.getOrDefault(pinColor, 0) + 1);
		}

		final int[] evaluation = new int[2];
		for (final PinColor pinColor : pinColorCounts.keySet()) {
			final int pinColorCount = pinColorCounts.get(pinColor);
			final int pinColorHitCount = hitCounts.getOrDefault(pinColor, 0);
			final int pinColorMissCount = missCounts.getOrDefault(pinColor, 0);
			evaluation[0] += pinColorHitCount;
			evaluation[1] += Math.min(pinColorCount - pinColorHitCount, pinColorMissCount);
		}

		return evaluation;
	}


	/**
	 * Returns a text representation of this game for display purposes.
	 * @return the display text representation
	 */
	public String toDisplayString () {
		final boolean displaySecret = this.isWon() | this.isLost();

		final StringBuilder factory = new StringBuilder();
		for (int file = 0; file < this.secret.size(); ++file) {
			if (file > 0) factory.append(' ');
			factory.append(displaySecret ? this.secret.get(file).symbol() : '*');
		}

		factory.append("\n");
		factory.append(Strings.resize("", 2 * this.secret.size() - 1, false, '='));

		for (final List<PinColor> guess : this.guesses) {
			factory.append("\n");

			for (int file = 0; file < guess.size(); ++file) {
				if (file > 0) factory.append(' ');
				factory.append(guess.get(file).symbol());
			}

			final int[] evaluation = this.evaluate(guess);
			factory.append("\t(");
			factory.append(evaluation[0]);
			factory.append(" hits, ");
			factory.append(evaluation[1]);
			factory.append(" near misses, ");
			factory.append(this.secret.size() - evaluation[0] - evaluation[1]);
			factory.append(" complete misses)");
		}

		return factory.toString();			
	}
}