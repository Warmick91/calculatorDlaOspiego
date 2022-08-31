package edu.damago.java.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Facade for the non-interactive text file editor application,
 * based on runtime arguments and structured programming techniques.
 */
public class EditTextFileApp {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException if there is an I/O related problem
	 */
	static public void main (final String[] args) throws IOException {
		final BufferedReader terminalSource = new BufferedReader(new InputStreamReader(System.in));
		final StringBuilder textFactory = new StringBuilder();

		while (true) {
			System.out.print("> ");
			final String line = terminalSource.readLine().trim();
			final int delimiterPosition = line.indexOf(' ');
			final String command = (delimiterPosition == -1 ? line : line.substring(0, delimiterPosition)).trim();
			final String arguments = (delimiterPosition == -1 ? "" : line.substring(delimiterPosition + 1)).trim();

			switch (command.toLowerCase()) {
				case "save":
					processSave(textFactory.toString(), arguments);
					System.out.println("ok.");
					break;
				case "append":
					textFactory.append(arguments + "\n");
					System.out.println("ok.");
					break;
				case "quit":
					System.out.println("Bye!");
					return;
				default:
					processHelp(arguments);
					break;
			}
		}
	}


	static public void processHelp (final String arguments) {
		System.out.println("Available commands:");
		System.out.println("- help: displays this help");
		System.out.println("- append <text>: adds the text to the internal text storage");
		System.out.println("- save <file-path>: saves the current text storage into the given file");
		System.out.println("- quit: terminates this program");
	}


	/**
	 * Saves the current text content.
	 * @param textContent the command arguments
	 * @param arguments the command arguments
	 * @throws IOException if there is an I/O related problem
	 */
	static public void processSave (final String textContent, final String arguments) throws IOException {
		final Path filePath = Paths.get(arguments.trim());
		if (!filePath.isAbsolute()) throw new IllegalArgumentException("the path argument must be absolute!");

		Files.writeString(filePath, textContent, StandardCharsets.UTF_8);
	}
}