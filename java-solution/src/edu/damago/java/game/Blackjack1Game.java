package edu.damago.java.game;

import java.util.Arrays;
import edu.damago.java.tool.Maths;


/**
 * Instances of this class represent Blackjack games.
 */
public class Blackjack1Game {
	static private final int[] BLACK_JACK = { 7, 7, 7 };
	static private final int DICE_FACE_COUNT = 10;

	private int[] playerRolls;
	private int[] dealerRolls;


	/**
	 * Initializes a new instance.
	 */
	public Blackjack1Game () {
		this.reset();
	}


	/**
	 * Resets this game.
	 */
	public void reset () {
		this.playerRolls = new int[0];
		this.dealerRolls = new int[0];
	}


	/**
	 * Returns the player rolls. 
	 * @return the player rolls
	 */
	public int[] getPlayerRolls () {
		return this.playerRolls;
	}


	/**
	 * Returns the dealer rolls. 
	 * @return the dealer rolls
	 */
	public int[] getDealerRolls () {
		return this.dealerRolls;
	}


	/**
	 * Returns the player total. 
	 * @return the player's roll sum
	 */
	public int getPlayerTotal () {
		int total = 0;
		for (final int roll : this.playerRolls)
			total += roll;

		return total;
	}


	/**
	 * Returns the dealer total. 
	 * @return the dealer's roll sum
	 */
	public int getDealerTotal () {
		int total = 0;
		for (final int roll : this.dealerRolls)
			total += roll;

		return total;
	}


	/**
	 * Rolls one dice for the player.
	 * @throws IllegalStateException if the player's total already exceeds 21,
	 * 			or if the dealer has already rolled  
	 */
	public void rollPlayer () throws IllegalStateException {
		if (this.getPlayerTotal() > 21 | this.getDealerTotal() > 0) throw new IllegalStateException();

		this.playerRolls = Arrays.copyOf(this.playerRolls, this.playerRolls.length + 1);
		this.playerRolls[this.playerRolls.length - 1] = Maths.randomInt(DICE_FACE_COUNT) + 1;
	}


	/**
	 * Rolls dices for the dealer.
	 * @throws IllegalStateException if the player's total already exceeds 21,
	 * 			or if the dealer has already rolled
	 */
	public void rollDealer () throws IllegalStateException {
		if (this.getPlayerTotal() > 21 | this.getDealerTotal() > 0) throw new IllegalStateException();

		while (this.getDealerTotal() < 17) {
			this.dealerRolls = Arrays.copyOf(this.dealerRolls, this.dealerRolls.length + 1);
			this.dealerRolls[this.dealerRolls.length - 1] = Maths.randomInt(DICE_FACE_COUNT) + 1;
		}
	}


	/**
	 * Returns a text representation of this game for display purposes.
	 * @return the display text representation
	 */
	public String toDisplayString () {
		final StringBuilder factory = new StringBuilder();

		final int playerTotal = this.getPlayerTotal();
		final int dealerTotal = this.getDealerTotal();
		factory.append("Player dice rolls ");
		factory.append(Arrays.toString(this.playerRolls));
		factory.append(" resulting in a sum of ");
		factory.append(playerTotal);
		factory.append('!');

		if (dealerTotal > 0) {
			factory.append("\nDealer dice rolls ");
			factory.append(Arrays.toString(this.dealerRolls));
			factory.append(" resulting in a sum of ");
			factory.append(dealerTotal);
			factory.append('!');

			if (playerTotal > 21)
				factory.append("\nPlayer lost the game!");
			else if (dealerTotal > 21)
				factory.append("\nPlayer won the game!");
			else if (Arrays.equals(this.playerRolls, BLACK_JACK) & Arrays.equals(this.dealerRolls, BLACK_JACK))
				factory.append("\nPlayer drew the game!");
			else if (Arrays.equals(this.playerRolls, BLACK_JACK) | playerTotal > dealerTotal)
				factory.append("\nPlayer won the game!");
			else if (Arrays.equals(this.dealerRolls, BLACK_JACK) | playerTotal < dealerTotal)
				factory.append("\nPlayer lost the game!");
			else
				factory.append("\nPlayer drew the game!");
		}

		return factory.toString();
	}
}