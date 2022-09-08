package edu.damago.java.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Facade for the interactive calculator text application,
 * based on object-based programming techniques.
 */
public class Calculator3App {

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
		if (parameters.length != 3) {
			System.err.println("Problem: expecting exactly three command arguments: <left operator> <operator symbol> <right operator>");
			return;
		}

		// input:    parse/copy runtime arguments into local variables
		final String operatorSymbol = parameters[1].trim();
		final double leftOperator = Double.parseDouble(parameters[0].trim());
		final double rightOperator = Double.parseDouble(parameters[2].trim());

		// process:  assign a new variable with the result of calling the calculate operation
		final BinaryOperator operator = new BinaryOperator(operatorSymbol);
		final double result = operator.calculate(leftOperator, rightOperator);

		// output:   print the calculation and it's result to sysout
		System.out.format("%s %s %s = %s%n", leftOperator, operatorSymbol, rightOperator, result);
	}

	
	
	/**
	 * Instances represent binary operators, i.e. operators featuring
	 * exactly two parameters.
	 */
	static protected class BinaryOperator {
		private final String symbol;

		public BinaryOperator (final String symbol) {
			super();
			this.symbol = symbol;
		}


		public String symbol () {
			return this.symbol;
		}


		/**
		 * Returns the result of the given calculation.
		 * @param leftOperand the left operand
		 * @param rightOperand the right operand
		 * @return the calculation result
		 */
		public double calculate (final double leftOperand, final double rightOperand) {
			switch (this.symbol) {
				case "+":
					return leftOperand + rightOperand;
				case "-":
					return leftOperand - rightOperand;
				case "*":
					return leftOperand * rightOperand;
				case "/":
					return leftOperand / rightOperand;
				case "%":
					return leftOperand % rightOperand;
				case "**":
					return Math.pow(leftOperand, rightOperand);
				case "root":
					return Math.pow(rightOperand, 1 / leftOperand);
				case "log":
					return Math.log(rightOperand) / Math.log(leftOperand);
				default:
					return Double.NaN;
			}
		}
	}
}