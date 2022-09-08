package edu.damago.java.employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import edu.damago.java.employee.Employee.Gender;


/**
 * Facade for the interactive employee text application (version 1),
 * based on object-based programming techniques.
 */
public class Employee2App {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException 
	 */
	static public void main (final String[] args) throws IOException {
		final BufferedReader terminalSource = new BufferedReader(new InputStreamReader(System.in));
		Employee[] employees = {};

		while (true) {
			System.out.print("> ");
			final String line = terminalSource.readLine().trim();
			final int delimiterPosition = line.indexOf(' ');
			final String command = (delimiterPosition == -1 ? line : line.substring(0, delimiterPosition)).trim();
			final String arguments = (delimiterPosition == -1 ? "" : line.substring(delimiterPosition + 1)).trim();

			// added coarse-grained exception handling here
			try {
				switch (command.toLowerCase()) {
					case "add":
						employees = processAddCommand(employees, arguments);
						System.out.println("ok.");
						break;
					case "remove":
						employees = processRemoveCommand(employees, arguments);
						System.out.println("ok.");
						break;
					case "display":
						processDisplayCommand(employees);
						System.out.println("ok.");
						break;
					case "save":
						processSaveCommand(employees, arguments);
						System.out.println("ok.");
						break;
					case "load":
						employees = processLoadCommand(arguments);
						System.out.println("ok.");
						break;
					case "quit":
						System.out.println("Bye!");
						return;
					default:
						processHelp(arguments);
						break;
				}
			} catch (final Exception e) {
				String message = e.getClass().getSimpleName();
				if (e.getMessage() != null) message += ": " + e.getMessage();
				System.err.println(message);

				// e.printStackTrace();
			}
		}
	}


	static public void processHelp (final String arguments) {
		System.out.println("Available commands:");
		System.out.println("- add <id>,<surname>,<forename>,<age>,<gender>: adds a new employee");
		System.out.println("- remove <index>: removes an existing employee");
		System.out.println("- display: displays all existing employees");
		System.out.println("- save <file-path>: saves a text representation of all existing employees");
		System.out.println("- load <file-path>: replaces the employees with new ones based on the given text representation");
		System.out.println("- help: displays this help");
		System.out.println("- quit: terminates this program");
	}


	// (A) arguments splitten nach Komma
	// (B) Employee-Instanz erzeugen
	// (C) Instanzvariablen der Employee-Instanz zuweisen, gegebenenfalls Parsen
	// (D) Mittel Arrays.copyOf() eine Kopie von employees erzeugen deren Länge um eins größer ist
	// (E) Dem letzten Slot des neuen Arrays (N - 1) die Employee-Instanz zuweisen
	// (F) Das neue Array zurückgeben
	static public Employee[] processAddCommand (final Employee[] employees, final String arguments) {
		// - added check for exactly 5 values
		// - handled parse exceptions by assigning 0 respectively NaN as default values
		final String[] values = arguments.split(",");
		if (values.length != 5) throw new IllegalArgumentException("employee data must contain exactly 5 properties!");
		
		long identity;
		try {
			identity = Long.parseLong(values[0].trim());
		} catch (final NumberFormatException e) {
			identity = 0;
		}

		float age;
		try {
			age = Float.parseFloat(values[3].trim());
		} catch (final NumberFormatException e) {
			age = Float.NaN;
		}

		Gender gender;
		try {
			gender = Gender.valueOf(values[4].trim());
		} catch (final IllegalArgumentException e) {
			gender = null;
		}

		final Employee employee = new Employee();
		employee.setIdentity(identity);
		employee.setSurname(values[1].trim());
		employee.setForename(values[2].trim());
		employee.setAge(age);
		employee.setGender(gender);

		final Employee[] people = Arrays.copyOf(employees, employees.length + 1);
		people[employees.length] = employee;
		return people;
	}


	// (A) Arguments parsen und Ergebnis einer Variable "index" zuweisen
	// (B) Hintere Elementeeines nach vorne Kopieren, entweder mittels for-index Schleife
	//     oder mittels System.arraycopy(employees, index + 1, employees, index, employees.length - index - 1)
	// (C) gib Arrays.copyOf(employees, employees.length - 1) zurück
	static public Employee[] processRemoveCommand (final Employee[] employees, final String arguments) {
		final int index = Integer.parseInt(arguments.trim());
		if (index < 0 | index >= employees.length) throw new IllegalArgumentException("Illegal index value: " + index);

		System.arraycopy(employees, index + 1, employees, index, employees.length - index - 1);
		return Arrays.copyOf(employees, employees.length - 1);
	}


	// jeden Employee in eigener Zeile ausgeben, zusammen mit dem zugehörigen Index
	static public void processDisplayCommand (final Employee[] employees) {
		for (int index = 0; index < employees.length; ++index) {
			final Employee employee = employees[index];
			System.out.format("% 5d: %s, %s, %s, %s, %s%n", index, employee.getIdentity(), employee.getSurname(), employee.getForename(), employee.getAge(), employee.getGender());
		}
	}


	// (A) erzeuge StringBuilder
	// (B) Iteriere über alle employees, und füge eine Zeile mit Daten dem StringBuilder hinzu
	// (C) Erzeuge aus arguments eine Path-Instanz, und verwende Files.writeString() um den Inhalt
	//     des StringBuilders in eine UTF-8 encodierte Datei zu schreiben
	static public void processSaveCommand (final Employee[] employees, final String arguments) throws IOException {
		final StringBuilder factory = new StringBuilder();
		for (final Employee employee : employees)
			factory.append(String.format("%s, %s, %s, %s, %s%n", employee.getIdentity(), employee.getSurname(), employee.getForename(), employee.getAge(), employee.getGender()));

		final Path filePath = Paths.get(arguments);
		Files.writeString(filePath, factory, StandardCharsets.UTF_8);
	}


	// (A) Erzeuge aus arguments eine Path-Instanz, und verwende Files.readString() um den Text-
	//     Inhalt aus einer UTF-8 encodierten Datei zu lesen
	// (B) Verwende text.split() um diesen Text-Inhalt in Zeilen aufzuteilen
	// (C) Initialisiere die Menge der employees mit leerem Array
	// (D) Iteriere über die Zeilen: Rufe pro Zeile processAddCommand() auf, und merke employees
	// (E) Gib letzten Stand von employees zurück
	static public Employee[] processLoadCommand (final String arguments) throws IOException {
		final Path filePath = Paths.get(arguments);
		final String text = Files.readString(filePath, StandardCharsets.UTF_8);
		final String[] lines = text.split("[\\n\\r]+");

		Employee[] employees = {};
		for (final String line : lines)
			if (!line.isBlank()) employees = processAddCommand(employees, line);

		return employees;
	}
}