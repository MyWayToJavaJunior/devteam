/**
 * 
 */
package com.epam.devteam.action.exception;

/**
 * The <code>ActionBadRequestException</code> is used when request fields are
 * not correct or absent.
 * 
 * @date Jan 15, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ActionBadRequestException extends ActionException {
    private static final long serialVersionUID = 1L;

    /**
     * Initializes a newly created {@code Object} object.
     */
    public ActionBadRequestException() {
	super();
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * 
     * @param message the detail message.
     * @param cause the cause.
     */
    public ActionBadRequestException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param message the detail message.
     */
    public ActionBadRequestException(String message) {
	super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message
     * of <tt>(cause==null ? null : cause.toString())</tt>.
     * 
     * @param cause the cause.
     */
    public ActionBadRequestException(Throwable cause) {
	super(cause);
    }

}
