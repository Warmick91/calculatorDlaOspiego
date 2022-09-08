package edu.damago.java.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Facade for the interactive calculator text application,
 * based on object-oriented programming techniques and exception handling.
 */
public class Calculator6App {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException 
	 */
	static public void main (final String[] args) throws IOException {
		final BufferedReader terminalSource = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.print("> ");
			final String line = terminalSource.readLine().trim();
			final int delimiterPosition = line.indexOf(' ');
			final String command = (delimiterPosition == -1 ? line : line.substring(0, delimiterPosition)).trim();
			final String arguments = (delimiterPosition == -1 ? "" : line.substring(delimiterPosition + 1)).trim();

			try {
				switch (command.toLowerCase()) {
					case "calc":
						processCalcCommand(arguments);
						break;
					case "quit":
						System.out.println("Bye!");
						return;
					default:
						processHelpCommand(arguments);
						break;
				}
			} catch (final Exception e) {
				String message = e.getClass().getSimpleName();
				if (e.getMessage() != null) message += ": " + e.getMessage();
				System.err.println(message);

				// outlook: using the logging API to log the exceptions:
				// Logger.getGlobal().log(Level.INFO, e.getMessage(), e);
			}
		}
	}


	static public void processHelpCommand (final String arguments) {
		System.out.println("Available commands:");
		System.out.println("- help: displays this help");
		System.out.println("- calc <operand> <operator> <operand>: calculates the given expression and displays the result");
		System.out.println("- quit: terminates this program\n");
		System.out.println("Supported operators: +, -, *, /, %, **, root, log");
	}

	
	static public void processCalcCommand (final String arguments) {
		final String[] parameters = arguments.split("\\s+");
		if (parameters.length != 3) throw new IllegalArgumentException("expecting exactly three command arguments: <left operator> <operator symbol> <right operator>!");

		// input:    parse/copy runtime arguments into local variables
		final String operatorSymbol = parameters[1].trim();
		double leftOperator, rightOperator;
		try {
			leftOperator = Double.parseDouble(parameters[0].trim());
		} catch (final NumberFormatException e) {
			leftOperator = Double.NaN;
		}
		try {
			rightOperator = Double.parseDouble(parameters[2].trim());
		} catch (final NumberFormatException e) {
			rightOperator = Double.NaN;
		}

		// process:  assign a new variable with the result of calling the calculate operation
		final BinaryOperator operator = BinaryOperator.newInstance(operatorSymbol);
		final double result = operator.calculate(leftOperator, rightOperator);

		// output:   print the calculation and it's result to sysout
		System.out.format("%s %s %s = %s%n", leftOperator, operatorSymbol, rightOperator, result);
	}



	/**
	 * Instances represent binary operators, i.e. operators featuring
	 * exactly two parameters.
	 */
	static protected abstract class BinaryOperator {

		/**
		 * Returns the result of the given calculation.
		 * @param leftOperand the left operand
		 * @param rightOperand the right operand
		 * @return the calculation result
		 */
		public abstract double calculate (final double leftOperand, final double rightOperand);


		/**
		 * Returns a new instance matching the given operator symbol.
		 * @param operatorSymbol the operator symbol
		 * @return the matching instance
		 * @throws NullPointerException if the given operator symbol is {@code null}
		 * @throws IllegalArgumentException if the given operator symbol is illegal
		 */
		static public BinaryOperator newInstance (final String operatorSymbol) throws NullPointerException, IllegalArgumentException {
			switch (operatorSymbol) {
				case "+":
					return new SumOperator();
				case "-":
					return new DifferenceOperator();
				case "*":
					return new ProductOperator();
				case "/":
					return new QuotientOperator();
				case "%":
					return new ModuloOperator();
				case "**":
					return new ExponentOperator();
				case "root":
					return new RootOperator();
				case "log":
					return new LogarithmOperator();
				default:
					throw new IllegalArgumentException();
			}
		}
	}


	static protected class SumOperator extends BinaryOperator {
		public double calculate (final double leftOperand, final double rightOperand) {
			return leftOperand + rightOperand;
		}
	}


	static protected class DifferenceOperator extends BinaryOperator {
		public double calculate (final double leftOperand, final double rightOperand) {
			return leftOperand - rightOperand;
		}
	}


	static protected class ProductOperator extends BinaryOperator {
		public double calculate (final double leftOperand, final double rightOperand) {
			return leftOperand * rightOperand;
		}
	}


	static protected class QuotientOperator extends BinaryOperator {
		public double calculate (final double leftOperand, final double rightOperand) {
			return leftOperand / rightOperand;
		}
	}


	static protected class ModuloOperator extends BinaryOperator {
		public double calculate (final double leftOperand, final double rightOperand) {
			return leftOperand % rightOperand;
		}
	}


	static protected class ExponentOperator extends BinaryOperator {
		public double calculate (final double leftOperand, final double rightOperand) {
			return Math.pow(leftOperand, rightOperand);
		}
	}


	static protected class RootOperator extends BinaryOperator {
		public double calculate (final double leftOperand, final double rightOperand) {
			return Math.pow(rightOperand, 1 / leftOperand);
		}
	}


	static protected class LogarithmOperator extends BinaryOperator {
		public double calculate (final double leftOperand, final double rightOperand) {
			return Math.log(rightOperand) / Math.log(leftOperand);
		}
	}
}