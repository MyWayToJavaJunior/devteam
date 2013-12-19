/**
 * 
 */
package com.epam.devteam.db;

/**
 * @date Dec 15, 2013 	
 * @author Andrey Kovalskiy
 *
 */
public class ConnectionPoolException extends Exception {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with <code>null</code> as its detail message.
	 */
	public ConnectionPoolException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message.
	 * @param message the detail message.
	 */
	public ConnectionPoolException(String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> 
	 * @param cause the cause.
	 */
	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new exception with the specified detail message and
     * cause.
	 * @param message the detail message.
	 * @param cause the cause.
	 */
	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

}
