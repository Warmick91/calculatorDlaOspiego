package edu.damago.java.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import edu.damago.java.game.TicTacToeBoard.Opponent;


/**
 * Facade for the interactive TicTacToe text application,
 * based on object-based programming techniques.
 */
public class TicTacToeApp {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException if there is an I/O related problem
	 */
	static public void main (final String[] args) throws IOException {
		final BufferedReader terminalSource = new BufferedReader(new InputStreamReader(System.in));
		final TicTacToeBoard board = new TicTacToeBoard();

		while (true) {
			System.out.println(board.toDisplayString());
			System.out.println();
			System.out.print(board.getActivePlayer());
			System.out.print("> ");

			final String line = terminalSource.readLine().trim();
			final int delimiterPosition = line.indexOf(' ');
			final String command = (delimiterPosition == -1 ? line : line.substring(0, delimiterPosition)).trim();
			final String arguments = (delimiterPosition == -1 ? "" : line.substring(delimiterPosition + 1)).trim();

			try {
				switch (command.toLowerCase()) {
					case "set":
						processSetCommand(board, arguments);
						break;
					case "reset":
						processResetCommand(board, arguments);
						break;
					case "quit":
						System.out.println("Bye!");
						return;
					default:
						processHelpCommand(arguments);
						break;
				}
			} catch (final Exception e) {
				String message = e.getClass().getSimpleName();
				if (e.getMessage() != null) message += ": " + e.getMessage();
				System.err.println(message);

//				e.printStackTrace();
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
		System.out.println("- set <rank> <file>: sets the current opponent using zero based coordinates");
		System.out.println("- reset: resets the board");
		System.out.println("- quit: terminates this program");
	}


	/**
	 * Processes the set command.
	 * @param arguments the command arguments
	 */
	static private void processSetCommand (final TicTacToeBoard board, final String arguments) {
		final Opponent activePlayer = board.getActivePlayer();

		final String[] coordinateTexts = arguments.split("\\s+");
		if (coordinateTexts.length != 2) throw new IllegalArgumentException("coordinates must consist of one rank and one file!");

		final int rank = Integer.parseInt(coordinateTexts[0]);
		final int file = Integer.parseInt(coordinateTexts[1]);
		board.set(rank, file);

		if (board.isWon())
			System.out.format("Player %s won this match, reset board to continue!%n", activePlayer);
	}


	/**
	 * Processes the reset command.
	 * @param arguments the command arguments
	 */
	static private void processResetCommand (final TicTacToeBoard board, final String arguments) {
		board.reset();
	}
}