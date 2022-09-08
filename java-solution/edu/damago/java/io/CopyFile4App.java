package edu.damago.java.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.UncheckedIOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


/**
 * Facade for the non-interactive copy file text application (version 4),
 * based on runtime arguments and structured programming techniques.
 * This multi-threaded design targets systems with at least two hard disks,
 * copying data between them.
 */
public class CopyFile4App {
	static private final int BUFFER_SIZE = 0x10_000_000;
	
	// example for a default exception handler suitable for multithreading
	// by terminating the program in response to any uncaught exception
	static {
		final UncaughtExceptionHandler handler = (Thread thread, Throwable exception) -> {
			exception.printStackTrace(System.err);
			
			final int responseCode = exception instanceof Error ? 2 : 1;
			System.exit(responseCode);
		};

		Thread.setDefaultUncaughtExceptionHandler(handler);
	}


	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException if there is an I/O related problem
	 */
	static public void main (final String[] args) throws IOException {
		final Path sourcePath = Paths.get(args[0].trim());
		final Path sinkPath = Paths.get(args[1].trim());
		if (!sourcePath.isAbsolute() | !sinkPath.isAbsolute()) throw new IllegalArgumentException("all path arguments must be absolute!");

		final InputStream fileSource = Files.newInputStream(sourcePath);
		final OutputStream fileSink = Files.newOutputStream(sinkPath, StandardOpenOption.CREATE);

		final PipedInputStream pipeSource = new PipedInputStream(BUFFER_SIZE);
		final PipedOutputStream pipeSink = new PipedOutputStream(pipeSource);

		final Runnable readWorker = () -> {
			try (fileSource) {
				try (pipeSink) {
					fileSource.transferTo(pipeSink);
				}
			} catch (final IOException e) {
				throw new UncheckedIOException(e);
			}
		};
		
		final Runnable writeWorker = () -> {
			try (pipeSource) {
				try (fileSink) {
					pipeSource.transferTo(fileSink);
				}
				System.out.println("ok.");
			} catch (final IOException e) {
				throw new UncheckedIOException(e);
			}
		};

		new Thread(readWorker, "file-read-thread").start();
		new Thread(writeWorker, "file-write-thread").start();

		System.out.print("copying ... ");
	}
}