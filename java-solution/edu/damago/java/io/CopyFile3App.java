package edu.damago.java.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


/**
 * Facade for the non-interactive copy file text application (version 3),
 * based on runtime arguments and structured programming techniques.
 */
public class CopyFile3App {
	static private final int BUFFER_SIZE = 0x100000;

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException if there is an I/O related problem
	 */
	static public void main (final String[] args) throws IOException {
		final Path sourcePath = Paths.get(args[0].trim());
		final Path sinkPath = Paths.get(args[1].trim());
		if (!sourcePath.isAbsolute() | !sinkPath.isAbsolute()) throw new IllegalArgumentException("all path arguments must be absolute!");

		try (InputStream fileSource = Files.newInputStream(sourcePath)) {
			try (OutputStream fileSink = Files.newOutputStream(sinkPath, StandardOpenOption.CREATE)) {
				final byte[] buffer = new byte[BUFFER_SIZE];
				for (int bytesRead = fileSource.read(buffer); bytesRead != -1; bytesRead = fileSource.read(buffer)) {
					fileSink.write(buffer, 0, bytesRead);
				}
			}
		}

		System.out.println("ok.");
	}
}