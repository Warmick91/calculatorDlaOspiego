package edu.damago.java.io;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Facade for the non-interactive display binary file text application,
 * based on runtime arguments and structured programming techniques.
 */
public class DisplayBinaryFileApp {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException if there is an I/O related problem
	 */
	static public void main (final String[] args) throws IOException {
		final Path filePath = Paths.get(args[0].trim());
		if (!filePath.isAbsolute()) throw new IllegalArgumentException("the path argument must be absolute!");

		final byte[] fileContent = Files.readAllBytes(filePath);
		String hexContent = new BigInteger(+1, fileContent).toString(16);
		if (hexContent.length() % 2 == 1) hexContent = "0" + hexContent;
		System.out.println(hexContent);
		System.out.println("ok.");
	}
}