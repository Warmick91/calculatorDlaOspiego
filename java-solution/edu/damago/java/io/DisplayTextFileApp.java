package edu.damago.java.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Facade for the non-interactive display text file text application,
 * based on runtime arguments and structured programming techniques.
 */
public class DisplayTextFileApp {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException if there is an I/O related problem
	 */
	static public void main (final String[] args) throws IOException {
		final Path filePath = Paths.get(args[0].trim());
		if (!filePath.isAbsolute()) throw new IllegalArgumentException("the path argument must be absolute!");

		final String fileContent = Files.readString(filePath);
		System.out.println(fileContent);
		System.out.println("ok.");
	}
}