package com.excilys.exception;

@SuppressWarnings("serial")
public class PersistenceException extends RuntimeException {
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
	public PersistenceException(Throwable cause) {
		super(cause);
	}
}