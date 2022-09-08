package edu.damago.java.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Facade for the text line input based Mastermind application
 * using object-based programming techniques and exception handling.
 */
public class MastermindApp {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException if there is an I/O related problem
	 */
	static public void main (final String[] args) throws IOException {
		final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		final MastermindBoard board = new MastermindBoard();

		while (true) {
			System.out.println(board.toDisplayString());
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
					case "guess":
						processGuessCommand(board, arguments);
						break;
					case "reset":
						processResetCommand(board, arguments);
						break;
					default:
						processHelpCommand(arguments);
						break;
				}
			} catch (final Exception e) {
				// e.printStackTrace();
				System.out.println("Illegal command syntax: " + command + " " + arguments);
				processHelpCommand(arguments);
			}
		}
	}


	/**
	 * Displays the available commands.
	 * @param arguments the command arguments
	 */
	static private void processHelpCommand (final String arguments) {
		System.out.println("List of available commands:");
		System.out.println("- help: displays this help");
		System.out.println("- guess <combination>: guesses the given combination");
		System.out.println("- reset: resets the current game, restarting it");
		System.out.println("- reset <secret-length> <symbol-count> <guess-count>: restarts the game with the given dimensions");
		System.out.println("- quit: terminates this program");
	}


	/**
	 * Processes the reset command.
	 * @param arguments the command arguments
	 */
	static private void processResetCommand (final MastermindBoard board, final String arguments) {
		final int pinCount, pinColorCount, guessCount;
		if (arguments.isBlank()) {
			pinCount = board.getPinCount();
			pinColorCount = board.getPinColorCount();
			guessCount = board.getGuessCount();
		} else {
			final String[] values = arguments.split("\\s+");
			if (values.length != 3) throw new IllegalArgumentException("illegal board geometry!");

			pinCount = Integer.parseInt(values[0]);
			pinColorCount = Integer.parseInt(values[1]);
			guessCount = Integer.parseInt(values[2]);
		}

		board.reset(pinCount, pinColorCount, guessCount);
	}


	/**
	 * Processes the guess command.
	 * @param arguments the command arguments
	 */
	static private void processGuessCommand (final MastermindBoard board, final String arguments) {
		try {
			final String[] symbols = arguments.split("\\s+");
			if (symbols.length != board.getSecret().size()) throw new IllegalArgumentException("must specify " + board.getSecret().size() + " pin symbols!");

			final List<PinColor> guess = new ArrayList<>();
			for (int index = 0; index < symbols.length; ++index)
				guess.add(PinColor.valueOf(Character.toUpperCase(symbols[index].charAt(0))));

			board.guess(guess);
		} catch (final IllegalArgumentException e) {
			System.out.println("Invalid argument: " + arguments);
		} catch (final IllegalStateException e) {
			System.out.println("Game is already " + (board.isWon() ? "won" : "lost") + "!");
		}
	}
}
