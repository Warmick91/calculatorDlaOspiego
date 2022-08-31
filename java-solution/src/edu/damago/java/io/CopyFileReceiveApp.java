package edu.damago.java.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


/**
 * Facade for the non-interactive copy file receive text application (a server),
 * based on runtime arguments and structured programming techniques.
 */
public class CopyFileReceiveApp {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException if there is an I/O related problem
	 */
	static public void main (final String[] args) throws IOException {
		final int servicePort = Integer.parseInt(args[0].trim());
		final Path sinkPath = Paths.get(args[1].trim());
		if (!sinkPath.isAbsolute()) throw new IllegalArgumentException("sink path argument must be absolute!");

		try (ServerSocket serviceHost = new ServerSocket(servicePort)) {
			try (OutputStream fileSink = Files.newOutputStream(sinkPath, StandardOpenOption.CREATE)) {
				System.out.println("TCP server started on port 8001.");
				System.out.println("Waiting for client connection ...");

				try (Socket tcpConnection = serviceHost.accept()) {
					tcpConnection.getInputStream().transferTo(fileSink);
				}
			}			
		}

		System.out.println("ok.");
	}
}