/**
 * 
 */
package com.epam.devteam.util.property;

/**
 * @date Dec 18, 2013
 * @author anjey
 * 
 */
public class PropertyManagerException extends Exception {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with <code>null</code> as its detail message.
     */
    public PropertyManagerException() {
	super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param message the detail message.
     */
    public PropertyManagerException(String message) {
	super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message
     * of <tt>(cause==null ? null : cause.toString())</tt>
     * 
     * @param cause the cause.
     */
    public PropertyManagerException(Throwable cause) {
	super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * 
     * @param message the detail message.
     * @param cause the cause.
     */
    public PropertyManagerException(String message, Throwable cause) {
	super(message, cause);
    }
}
