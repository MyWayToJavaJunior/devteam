/**
 * 
 */
package com.epam.devteam.action.exception;

/**
 * The <code>ActionDatabaseFailException</code> is udsed when it is impossible
 * to work with database.
 * 
 * @date Jan 15, 2014
 * @author Andrey Kovalskiy
 */
public class ActionDatabaseFailException extends ActionException {
    private static final long serialVersionUID = 1L;

    /**
     * Initializes a newly created {@code Object} object.
     */
    public ActionDatabaseFailException() {
	super();
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * 
     * @param message the detail message.
     * @param cause the cause.
     */
    public ActionDatabaseFailException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param message the detail message.
     */
    public ActionDatabaseFailException(String message) {
	super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message
     * of <tt>(cause==null ? null : cause.toString())</tt>.
     * 
     * @param cause the cause.
     */
    public ActionDatabaseFailException(Throwable cause) {
	super(cause);
    }

}
