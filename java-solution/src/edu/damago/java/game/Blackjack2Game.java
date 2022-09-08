package edu.damago.java.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.damago.java.tool.Maths;


/**
 * Instances of this class represent Blackjack games.
 */
public class Blackjack2Game {
	static private final List<Integer> BLACK_JACK = Arrays.asList(7, 7, 7);
	static private final int DICE_FACE_COUNT = 10;

	private final List<Integer> playerRolls = new ArrayList<>();
	private final List<Integer> dealerRolls = new ArrayList<>();


	/**
	 * Resets this game.
	 */
	public void reset () {
		this.playerRolls.clear();
		this.dealerRolls.clear();
	}


	/**
	 * Returns the player rolls. 
	 * @return the player rolls
	 */
	public List<Integer> getPlayerRolls () {
		return this.playerRolls;
	}


	/**
	 * Returns the dealer rolls. 
	 * @return the dealer rolls
	 */
	public List<Integer> getDealerRolls () {
		return this.dealerRolls;
	}


	/**
	 * Returns the player total. 
	 * @return the player's roll sum
	 */
	public int getPlayerTotal () {
		// outlook: compact summation using collection streams (functional programming)
		// return this.playerRolls.stream().mapToInt(x -> x).sum();

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
		// outlook: compact summation using collection streams (functional programming)
		// return this.dealerRolls.stream().mapToInt(x -> x).sum();

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

		this.playerRolls.add(Maths.randomInt(DICE_FACE_COUNT) + 1);
	}


	/**
	 * Rolls dices for the dealer.
	 * @throws IllegalStateException if the player's total already exceeds 21,
	 * 			or if the dealer has already rolled
	 */
	public void rollDealer () throws IllegalStateException {
		if (this.getPlayerTotal() > 21 | this.getDealerTotal() > 0) throw new IllegalStateException();

		while (this.getDealerTotal() < 17)
			this.dealerRolls.add(Maths.randomInt(DICE_FACE_COUNT) + 1);
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
		factory.append(this.playerRolls);
		factory.append(" resulting in a sum of ");
		factory.append(playerTotal);
		factory.append('!');

		if (dealerTotal > 0) {
			factory.append("\nDealer dice rolls ");
			factory.append(this.dealerRolls);
			factory.append(" resulting in a sum of ");
			factory.append(dealerTotal);
			factory.append('!');

			if (playerTotal > 21)
				factory.append("\nPlayer lost the game!");
			else if (dealerTotal > 21)
				factory.append("\nPlayer won the game!");
			else if (BLACK_JACK.equals(this.playerRolls) & BLACK_JACK.equals(this.dealerRolls))
				factory.append("\nPlayer drew the game!");
			else if (BLACK_JACK.equals(this.playerRolls) | playerTotal > dealerTotal)
				factory.append("\nPlayer won the game!");
			else if (BLACK_JACK.equals(this.dealerRolls) | playerTotal < dealerTotal)
				factory.append("\nPlayer lost the game!");
			else
				factory.append("\nPlayer drew the game!");
		}

		return factory.toString();
	}
}