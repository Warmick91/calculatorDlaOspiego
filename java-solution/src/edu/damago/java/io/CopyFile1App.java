package edu.damago.java.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


/**
 * Facade for the non-interactive copy file text application (version 1),
 * based on runtime arguments and structured programming techniques.
 */
public class CopyFile1App {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException if there is an I/O related problem
	 */
	static public void main (final String[] args) throws IOException {
		final Path sourcePath = Paths.get(args[0].trim());
		final Path sinkPath = Paths.get(args[1].trim());
		if (!sourcePath.isAbsolute() | !sinkPath.isAbsolute()) throw new IllegalArgumentException("all path arguments must be absolute!");

		Files.copy(sourcePath, sinkPath, StandardCopyOption.REPLACE_EXISTING);
		System.out.println("ok.");
	}
}