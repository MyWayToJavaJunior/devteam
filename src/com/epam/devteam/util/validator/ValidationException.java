package com.epam.devteam.util.validator;

/**
 * The <code>Class</code> ...
 * 
 * @date Jan 16, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ValidationException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with <code>null</code> as its detail message.
     */
    public ValidationException() {
	super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param message the detail message.
     */
    public ValidationException(String message) {
	super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message
     * of <tt>(cause==null ? null : cause.toString())</tt>
     * 
     * @param cause the cause.
     */
    public ValidationException(Throwable cause) {
	super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * 
     * @param message the detail message.
     * @param cause the cause.
     */
    public ValidationException(String message, Throwable cause) {
	super(message, cause);
    }
}
