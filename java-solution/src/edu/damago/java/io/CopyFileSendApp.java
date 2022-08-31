package edu.damago.java.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Facade for the non-interactive copy file send application (a client),
 * based on runtime arguments and structured programming techniques.
 */
public class CopyFileSendApp {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException if there is an I/O related problem
	 */
	static public void main (final String[] args) throws IOException {
		final String serviceHostname = args[0].trim();
		final int servicePort = Integer.parseInt(args[1].trim());
		final Path sourcePath = Paths.get(args[2].trim());
		if (!sourcePath.isAbsolute()) throw new IllegalArgumentException("source path argument must be absolute!");

		try (InputStream fileSource = Files.newInputStream(sourcePath)) {
			try (Socket tcpConnection = new Socket(serviceHostname, servicePort)) {
				fileSource.transferTo(tcpConnection.getOutputStream());
			}
		}

		System.out.println("ok.");
	}
}