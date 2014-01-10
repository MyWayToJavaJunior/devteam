/**
 * 
 */
package com.epam.devteam.dao;

import com.epam.devteam.db.ConnectionPool;

/**
 * @date Jan 2, 2014
 * @author Andrey Kovalskiy
 * 
 */
public abstract class AbstractDao {

    private ConnectionPool connectionPool;

    /**
     * Initializes a newly created {@code AbstractDao} object.
     */
    public AbstractDao() {
	super();
    }

    /**
     * Initializes a newly created {@code AbstractDao} object and connection
     * with the given connection value.
     * 
     * @param connection The connection to use to connect to the database.
     */
    public AbstractDao(ConnectionPool connectionPool) {
	this.connectionPool = connectionPool;
    }

    /**
     * Returns the connectionPool field value.
     * 
     * @return the connectionPool
     */
    public ConnectionPool getConnectionPool() {
	return connectionPool;
    }

    /**
     * Sets the connectionPool field value.
     * 
     * @param connectionPool the connectionPool to set
     */
    public void setConnectionPool(ConnectionPool connectionPool) {
	this.connectionPool = connectionPool;
    }

}
