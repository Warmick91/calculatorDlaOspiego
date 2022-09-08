package edu.damago.java.game;

import java.util.Arrays;

/**
 * TicTacToe boards based on object-based programming techniques.
 */
public class TicTacToeBoard {
	static public enum Opponent { X, O }

	private Opponent activePlayer;
	private final Opponent[][] content;


	/**
	 * Initializes a new instance.
	 */
	public TicTacToeBoard () {
		this.activePlayer = Opponent.X;
		this.content = new Opponent[3][3];
	}


	/**
	 * Resets this board.
	 */
	public void reset () {
		this.activePlayer = Opponent.X;
		for (final Opponent[] contentRow : this.content)
			Arrays.fill(contentRow, null);
	}


	/**
	 * Returns the active opponent.
	 * @return the active opponent
	 */
	public Opponent getActivePlayer () {
		return this.activePlayer;
	}


	/**
	 * Returns the content.
	 * @return the content
	 */
	public Opponent[][] getContent () {
		return this.content;
	}


	/**
	 * Returns whether or not this board is won. 
	 * @return true if this board is won by any opponent, false otherwise
	 */
	public boolean isWon () {
		for (int rank = 0; rank < this.content.length; ++rank) {
			if (this.content[rank][0] == null) continue;
			if (this.content[rank][0] == null & this.content[rank][0] == this.content[rank][1] & this.content[rank][1] == this.content[rank][2]) return true;
		}

		for (int file = 0; file < this.content.length; ++file) {
			if (this.content[0][file] == null) continue;
			if (this.content[0][file] == this.content[1][file] & this.content[1][file] == this.content[2][file]) return true;
		}

		if (this.content[0][0] != null)
			if (this.content[0][0] == this.content[1][1] & this.content[1][1] == this.content[2][2]) return true;

		if (this.content[0][2] != null)
			if (this.content[0][2] == this.content[1][1] & this.content[1][1] == this.content[2][0]) return true;

		return false;
	}


	/**
	 * Sets the active player's symbol at the given position,
	 * and switches the active player.
	 * @param rank the rank
	 * @param file the file
	 * @throws IllegalArgumentException if the given rank or file is out of range,
	 * 			or if the board position specified is already occupied
	 * @throws IllegalStateException if this board is already won
	 */
	public void set (final int rank, final int file) throws IllegalArgumentException, IllegalStateException {
		if (rank < 0 | rank >= this.content.length | file < 0 | file >= this.content[0].length) throw new IllegalArgumentException();
		if (this.content[rank][file] != null) throw new IllegalArgumentException("board cell is already occupied!");
		if (this.isWon()) throw new IllegalStateException("game is already won!");

		this.content[rank][file] = this.activePlayer;
		this.activePlayer = this.activePlayer == Opponent.X ? Opponent.O : Opponent.X;
	}


	/**
	 * Returns a text representation of this board for display purposes.
	 * @return the display text representation
	 */
	public String toDisplayString () {
		final StringBuilder factory = new StringBuilder();

		for (int rank = 0; rank < this.content.length; ++rank) {
			final Opponent[] contentRow = this.content[rank];
			if (rank > 0) factory.append('\n');

			for (int file = 0; file < contentRow.length; ++file) {
				final Opponent contentCell = contentRow[file];
				if (file > 0) factory.append(' ');

				factory.append(contentCell == null ? "-" : contentCell.name());
			}
		}

		return factory.toString();
	}
}