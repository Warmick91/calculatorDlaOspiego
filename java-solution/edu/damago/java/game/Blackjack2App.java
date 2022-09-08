package edu.damago.java.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Facade for the text line input based Black-Jack application
 * using object-based programming techniques and exception handling.
 */
public class Blackjack2App {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException if there is an I/O related problem
	 */
	static public void main (final String[] args) throws IOException {
		final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		final Blackjack2Game game = new Blackjack2Game();

		while (true) {
			System.out.print("> ");
			final String line = consoleReader.readLine().trim();
			final int delimiterPosition = line.indexOf(' ');
			final String command = (delimiterPosition == -1 ? line : line.substring(0, delimiterPosition)).trim();
			final String arguments = (delimiterPosition == -1 ? "" : line.substring(delimiterPosition + 1)).trim();

			try {
				switch (command.toLowerCase()) {
					case "quit":
						System.out.println("bye bye!");
						return;
					case "roll":
						processRollCommand(game, arguments);
						break;
					case "stand":
						processStandCommand(game, arguments);
						break;
					case "reset":
						processResetCommand(game, arguments);
						break;
					default:
						processHelpCommand(arguments);
						break;
				}
			} catch (final Exception e) {
				// e.printStackTrace();
				System.out.println("Game is already won or lost, reset game and try again!");
				processHelpCommand(arguments);
			}
		}
	}


	/**
	 * Displays the available commands.
	 * @param arguments the arguments
	 */
	static public void processHelpCommand (final String arguments) {
		System.out.println("List of available commands:");
		System.out.println("- help: displays this help");
		System.out.println("- roll: rolls the player's dice once");
		System.out.println("- stand: rolls the dealer's dice, determines the game outcome, and resets it");
		System.out.println("- reset: resets the current game, restarting it");
		System.out.println("- quit: terminates this program");
	}


	/**
	 * Processes rolling a dice for the player.
	 * @param game the game
	 * @param arguments the arguments
	 */
	static private void processRollCommand (final Blackjack2Game game, final String arguments) {
		try {
			game.rollPlayer();

			final int playerRollCount = game.getPlayerRolls().size();
			System.out.println("You rolled a " + game.getPlayerRolls().get(playerRollCount - 1));
			System.out.println(game.toDisplayString());

			if (game.getPlayerTotal() > 21) {
				System.out.println("Sadly, your roll sum exceeds 21, and therefore you lost the game!");
				game.reset();
			}
		} catch (final IllegalStateException e) {
			System.out.println("Either your roll sum aleady exceeds 21, or the dealer already rolled!");
			game.reset();
		}
	}


	/**
	 * Processes rolling the dice for the dealer, determines the game outcome, and resets it.
	 * @param game the game
	 * @param arguments the arguments
	 */
	static private void processStandCommand (final Blackjack2Game game, final String arguments) {
		try {
			game.rollDealer();

			System.out.println(game.toDisplayString());
		} catch (final IllegalStateException e) {
			System.out.println("Either your roll sum aleady exceeds 21, or the dealer already rolled!");
		} finally {
			game.reset();
		}
	}


	/**
	 * Processes the reset command.
	 * @param game the game
	 * @param arguments the arguments
	 */
	static private void processResetCommand (final Blackjack2Game game, final String arguments) {
		game.reset();
		System.out.println("ok.");
	}
}