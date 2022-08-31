package edu.damago.java.tool;

import java.sql.SQLException;


/**
 * Wraps an {@link SQLException} with an unchecked exception.
 */
public class UncheckedSQLException extends RuntimeException {
	static private final long serialVersionUID = 1L;

	/**
	 * Initializes a new instance without message and cause.
	 */
	public UncheckedSQLException () {
		super();
	}


	/**
	 * Initializes a new instance with the given message but no cause.
	 * @param message the message, or {@code null} for none
	 */
	public UncheckedSQLException (final String message) {
		super(message);
	}

	
	/**
	 * Initializes a new instance without message but the given cause.
	 * @param cause the cause, or {@code null} for none
	 */
	public UncheckedSQLException (final SQLException cause) {
		super(cause);
	}

	
	/**
	 * Initializes a new instance with both the given message and cause.
	 * @param message the message, or {@code null} for none
	 * @param cause the cause, or {@code null} for none
	 */
	public UncheckedSQLException (final String message, final SQLException cause) {
		super(message, cause);
	}


	/**
	 * Returns the cause.
	 * @return the cause, or {@code null} for none
	 */
	@Override
	public SQLException getCause () {
		return (SQLException) super.getCause();
	}
}