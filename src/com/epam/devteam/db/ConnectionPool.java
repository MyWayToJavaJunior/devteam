/**
 * 
 */
package com.epam.devteam.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * @date Dec 15, 2013
 * @author Andrey Kovalskiy
 * 
 */
public enum ConnectionPool {

    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/devteam";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final int CONNECTIONS_QUANTITY = 10;
    private static final long MAX_WAIT_TIME = 5000;
    private Semaphore semaphore;
    private BlockingQueue<Connection> freeConnections;

    /**
     * It is not possible to initialize this object outside of this class.
     */
    private ConnectionPool() {
	//init();
    }

    /**
     * Is is used to initialize connection pool instance.
     * 
     * @throws ConnectionPoolException
     */
    public void init() throws ConnectionPoolException {

	try {
	    Class.forName(DRIVER_NAME);
	    LOGGER.debug("Database driver was initialized.");
	} catch (ClassNotFoundException e) {
	    LOGGER.error("Data base driver not found.");
	    throw new ConnectionPoolException();
	}
	semaphore = new Semaphore(CONNECTIONS_QUANTITY);
	freeConnections = new ArrayBlockingQueue<Connection>(CONNECTIONS_QUANTITY);
	for (int i = 0; i < CONNECTIONS_QUANTITY; i++) {
	    Connection connection;
	    try {
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
	    } catch (SQLException e) {
		LOGGER.error("Can't initialize pool connections!");
		throw new ConnectionPoolException();
	    }
	    freeConnections.add(connection);
	}
	LOGGER.debug("Connection pool was initialized with "
		+ CONNECTIONS_QUANTITY + "connections.");
    }

    /**
     * Is used to get free connection. If there is no free connection client
     * will wait for a while. If there is still no free connections method will
     * return null.
     * 
     * @return Free connection, otherwise null.
     */
    public Connection takeConnection() {
	Connection connection = null;
	try {
	    if (semaphore.tryAcquire(MAX_WAIT_TIME, TimeUnit.MILLISECONDS)) {
		connection = freeConnections.poll(MAX_WAIT_TIME,
			TimeUnit.MILLISECONDS);
		LOGGER.debug("Connection has been taken.");
	    }
	} catch (InterruptedException e) {
	    LOGGER.warn("Can't take connection");
	}
	return connection;
    }

    /**
     * Is used to return and release connection into connection pool.
     * 
     * @param connection The connection to return.
     */
    public void returnConnection(Connection connection) {
	try {
	    freeConnections.offer(connection, MAX_WAIT_TIME,
		    TimeUnit.MILLISECONDS);
	    semaphore.release();
	    LOGGER.debug("Connection has been returned.");
	} catch (InterruptedException e) {
	    LOGGER.warn("Can't return connection!");
	}
    }

    /**
     * Is used to close all connections in the connection pool.
     */
    public void closeConnections() {
	for (Connection connection : freeConnections) {
	    try {
		connection.close();
		semaphore.release();
	    } catch (SQLException e) {
		LOGGER.error("Can't close connection!");
	    }
	}
    }

}
