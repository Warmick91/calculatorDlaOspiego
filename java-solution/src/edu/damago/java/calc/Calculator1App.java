package edu.damago.java.calc;


/**
 * Facade for the non-interactive calculator text application,
 * based on runtime arguments and structured programming techniques.
 */
public class Calculator1App {

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 */
	static public void main (final String[] args) {
		if (args.length != 3) {
			System.err.println("Problem: expecting exactly three runtime arguments: <left operand> <operator symbol> <right operand>");
			return;
		}

		// input:    parse/copy runtime arguments into local variables
		final String operatorSymbol = args[1].trim();
		final double leftOperator = Double.parseDouble(args[0].trim());
		final double rightOperator = Double.parseDouble(args[2].trim());

		// process:  assign a new variable with the result of calling the calculate operation
		final double result = calculate(operatorSymbol, leftOperator, rightOperator);

		// output:   print the calculation and it's result to sysout
		System.out.format("%s %s %s = %s%n", leftOperator, operatorSymbol, rightOperator, result);
	}


	/**
	 * Returns the result of the given calculation.
	 * @param operatorSymbol the operator symbol
	 * @param leftOperand the left operand
	 * @param rightOperand the right operand
	 * @return the calculation result
	 */
	static public double calculate (final String operatorSymbol, final double leftOperand, final double rightOperand) {
		switch (operatorSymbol) {
			case "+":
				return leftOperand + rightOperand;
			case "-":
				return leftOperand - rightOperand;
			case "*":
			case "x":
				return leftOperand * rightOperand;
			case "/":
				return leftOperand / rightOperand;
			case "%":
				return leftOperand % rightOperand;
			case "**":
			case "xx":
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