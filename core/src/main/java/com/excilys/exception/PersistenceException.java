package com.excilys.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class PersistenceException.
 */
@SuppressWarnings("serial")
public class PersistenceException extends RuntimeException {
	
	/**
	 * Instantiates a new persistence exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a new persistence exception.
	 *
	 * @param cause the cause
	 */
	public PersistenceException(Throwable cause) {
		super(cause);
	}
}