package edu.damago.java.io;

import java.io.IOException;


/**
 * Facade for the non-interactive echo text application,
 * based on runtime arguments and structured programming techniques.
 * This application only makes sense if started using piping for
 * sysin and sysout, like "< input.zip > output.zip".
 */
public class EchoApp {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException if there is an I/O related problem
	 */
	static public void main (final String[] args) throws IOException {
		System.in.transferTo(System.out);
	}
}