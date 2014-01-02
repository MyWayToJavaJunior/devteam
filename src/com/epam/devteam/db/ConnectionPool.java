/**
 * 
 */
package com.epam.devteam.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.epam.devteam.util.property.PropertyManager;
import com.epam.devteam.util.property.PropertyManagerException;

/**
 * @date Dec 15, 2013
 * @author Andrey Kovalskiy
 * 
 */
public class ConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static volatile ConnectionPool instance;
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int connections;
    private long waitTime;
    private BlockingQueue<Connection> freeConnections;

    public static ConnectionPool getInstance() throws ConnectionPoolException {
	ConnectionPool localInstance = instance;
	if (localInstance == null) {
	    synchronized (ConnectionPool.class) {
		localInstance = instance;
		if (localInstance == null) {
		    instance = localInstance = new ConnectionPool();
		    instance.init();
		}
	    }
	}
	return localInstance;
    }

    /**
     * It is used to initialize connection pool instance.
     * 
     * @throws ConnectionPoolException
     */
    private void init() throws ConnectionPoolException {
	try {
	    PropertyManager propertyManager = PropertyManager.getInstance();
	    String db = "db." + propertyManager.getString("db.name");
	    driverName = propertyManager.getString(db + ".driver");
	    url = propertyManager.getString(db + ".url");
	    user = propertyManager.getString(db + ".user");
	    password = propertyManager.getString(db + ".password");
	    connections = propertyManager.getInt(db + ".connections");
	    waitTime = propertyManager.getLong(db + ".waitTime");
	    LOGGER.debug("Connection pool fields were initialized.");
	} catch (PropertyManagerException e) {
	    LOGGER.error("Connection pool fields can not be initialized.");
	    throw new ConnectionPoolException();
	}
	try {
	    Class.forName(driverName);
	    LOGGER.debug("Database driver was initialized.");
	} catch (ClassNotFoundException e) {
	    LOGGER.error("Data base driver was not found.");
	    throw new ConnectionPoolException();
	}
	freeConnections = new ArrayBlockingQueue<Connection>(connections);
	for (int i = 0; i < connections; i++) {
	    Connection connection = createConnection();
	    freeConnections.add(connection);
	}
	LOGGER.debug("Connection pool was initialized with " + connections
		+ " connections.");
    }

    /**
     * Is used to get free connection. If there is no free connection client
     * will wait for a while. If there is still no free connections method will
     * return null.
     * 
     * @return Free connection from the connection pool.
     * @throws ConnectionPoolException If connection can not be taken or is not
     *             valid and can not be created.
     */
    public Connection takeConnection() throws ConnectionPoolException {
	Connection connection = null;
	try {
	    connection = freeConnections.poll(waitTime, TimeUnit.MILLISECONDS);
	} catch (InterruptedException e) {
	    LOGGER.warn("Connection can not be taken: interrupted while waiting");
	    throw new ConnectionPoolException();
	}
	if (!isConnectionValid(connection)) {
	    LOGGER.debug("Connection is not valid.");
	    Connection newConnection = createConnection();
	    connection = newConnection;
	}
	LOGGER.debug("Connection has been taken.");
	return connection;
    }

    /**
     * Is used to return and release connection into connection pool.
     * 
     * @param connection The connection to return.
     */
    public void returnConnection(Connection connection) {
	try {
	    freeConnections.offer(connection, waitTime, TimeUnit.MILLISECONDS);
	    LOGGER.debug("Connection has been returned.");
	} catch (InterruptedException e) {
	    LOGGER.debug("Connection can not be returned");
	}
    }

    /**
     * Is used to close all connections in the connection pool.
     */
    public void closeConnections() {
	for (Connection connection : freeConnections) {
	    try {
		connection.close();
		LOGGER.debug("Connection has been closed.");
	    } catch (SQLException e) {
		LOGGER.error("Connection can not be closed.");
	    }
	}
    }

    /**
     * Is used to create new connection.
     * 
     * @return The new connection.
     * @throws ConnectionPoolException If connection can not be created.
     */
    private Connection createConnection() throws ConnectionPoolException {
	Connection connection;
	try {
	    connection = DriverManager.getConnection(url, user, password);
	    LOGGER.debug("Connection has been created.");
	} catch (SQLException e) {
	    LOGGER.warn("Connection cannot be created.");
	    throw new ConnectionPoolException();
	}
	return connection;
    }

    /**
     * Is used to check if the connection has not been closed and still is
     * valid.
     * 
     * @return true if connection is valid, false otherwise.
     * @throws ConnectionPoolException If something wrong with connection
     *             validation.
     */
    private boolean isConnectionValid(Connection connection)
	    throws ConnectionPoolException {
	boolean result = false;
	try {
	    result = connection.isValid((int) waitTime);
	} catch (SQLException e) {
	    LOGGER.warn("Connection validation failed.");
	    throw new ConnectionPoolException();
	}
	return result;
    }

}
