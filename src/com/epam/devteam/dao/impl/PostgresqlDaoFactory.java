/**
 * 
 */
package com.epam.devteam.dao.impl;

import java.sql.Connection;

import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.db.ConnectionPool;

/**
 * @date Dec 15, 2013
 * @author Kovalskiy Andrey
 * 
 */
public class PostgresqlDaoFactory extends DaoFactory {

    private ConnectionPool connectionPool;

    /**
     * Initializes a newly created {@code Object} object.
     */
    public PostgresqlDaoFactory() {
	super();
    }

    @Override
    public void setConnectionPool(ConnectionPool connectionPool) {
	this.connectionPool = connectionPool;
    }

    @Override
    public UserDao takeUserDao() throws DaoException {
	// Connection connection = createConnection();
	Connection connection = connectionPool.takeConnection();
	if (connection == null) {
	    throw new DaoException("Cannot take connection!");
	}
	return new PostgresqlUserDao(connection);
    }
}
