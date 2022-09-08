package edu.damago.java.employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import edu.damago.java.employee.Employee.Gender;


/**
 * Facade for the interactive employee text application (version 1),
 * based on object-oriented programming techniques.
 */
public class Employee3App {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException 
	 */
	static public void main (final String[] args) throws IOException {
		final BufferedReader terminalSource = new BufferedReader(new InputStreamReader(System.in));
		final List<Employee> employees = new ArrayList<>();

		while (true) {
			System.out.print("> ");
			final String line = terminalSource.readLine().trim();
			final int delimiterPosition = line.indexOf(' ');
			final String command = (delimiterPosition == -1 ? line : line.substring(0, delimiterPosition)).trim();
			final String arguments = (delimiterPosition == -1 ? "" : line.substring(delimiterPosition + 1)).trim();

			try {
				switch (command.toLowerCase()) {
					case "add":
						processAddCommand(employees, arguments);
						System.out.println("ok.");
						break;
					case "remove":
						processRemoveCommand(employees, arguments);
						System.out.println("ok.");
						break;
					case "display":
						processDisplayCommand(employees, arguments);
						System.out.println("ok.");
						break;
					case "save":
						processSaveCommand(employees, arguments);
						System.out.println("ok.");
						break;
					case "load":
						processLoadCommand(employees, arguments);
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


	static public void processAddCommand (final List<Employee> employees, final String arguments) {
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

		employees.add(employee);
	}


	static public void processRemoveCommand (final List<Employee> employees, final String arguments) {
		final int index = Integer.parseInt(arguments.trim());
		if (index < 0 | index >= employees.size()) throw new IllegalArgumentException("Illegal index value: " + index);

		employees.remove(index);
	}


	static public void processDisplayCommand (final List<Employee> employees, final String arguments) {
		for (int index = 0; index < employees.size(); ++index) {
			final Employee employee = employees.get(index);
			System.out.format("% 5d: %s, %s, %s, %s, %s%n", index, employee.getIdentity(), employee.getSurname(), employee.getForename(), employee.getAge(), employee.getGender());
		}
	}


	static public void processSaveCommand (final List<Employee> employees, final String arguments) throws IOException {
		final StringBuilder factory = new StringBuilder();
		for (final Employee employee : employees)
			factory.append(String.format("%s, %s, %s, %s, %s%n", employee.getIdentity(), employee.getSurname(), employee.getForename(), employee.getAge(), employee.getGender()));

		final Path filePath = Paths.get(arguments);
		Files.writeString(filePath, factory, StandardCharsets.UTF_8);
	}


	static public void processLoadCommand (final List<Employee> employees, final String arguments) throws IOException {
		final Path filePath = Paths.get(arguments);
		final String text = Files.readString(filePath, StandardCharsets.UTF_8);
		final String[] lines = text.split("[\\n\\r]+");

		employees.clear();
		for (final String line : lines)
			if (!line.isBlank()) processAddCommand(employees, line);
	}
}