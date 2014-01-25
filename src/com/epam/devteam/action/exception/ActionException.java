/**
 * 
 */
package com.epam.devteam.action.exception;

/**
 * The <code>ActionException</code> is used when something fails during action.
 * It is a wrapper for all exceptions that occur during action performing.
 * 
 * @date Dec 14, 2013
 * @author Andrey Kovalskiy
 * 
 */
public class ActionException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with <code>null</code> as its detail message.
     */
    public ActionException() {
	super();
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * 
     * @param message the detail message.
     * @param cause the cause.
     */
    public ActionException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param message the detail message.
     */
    public ActionException(String message) {
	super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message
     * of <tt>(cause==null ? null : cause.toString())</tt>.
     * 
     * @param cause the cause.
     */
    public ActionException(Throwable cause) {
	super(cause);
    }

}
