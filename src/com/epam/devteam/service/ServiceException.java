package com.epam.devteam.service;

/**
 * The <code>ServiceException</code> is used to wrap any exception that occurs
 * during service performing.
 * 
 * @date Jan 19, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ServiceException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with <code>null</code> as its detail message.
     */
    public ServiceException() {
	super();
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * 
     * @param message the detail message.
     * @param cause the cause.
     */
    public ServiceException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param message the detail message.
     */
    public ServiceException(String message) {
	super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message
     * of <tt>(cause==null ? null : cause.toString())</tt>.
     * 
     * @param cause the cause.
     */
    public ServiceException(Throwable cause) {
	super(cause);
    }
}
