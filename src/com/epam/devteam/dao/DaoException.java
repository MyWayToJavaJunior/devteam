/**
 * 
 */
package com.epam.devteam.dao;

/**
 * @date Dec 15, 2013 	
 * @author Andrey Kovalskiy
 *
 */
public class DaoException extends Exception {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with <code>null</code> as its detail message.
	 */
	public DaoException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message.
	 * @param message the detail message.
	 */
	public DaoException(String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> 
	 * @param cause the cause.
	 */
	public DaoException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new exception with the specified detail message and
     * cause.
	 * @param message the detail message.
	 * @param cause the cause.
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

}
