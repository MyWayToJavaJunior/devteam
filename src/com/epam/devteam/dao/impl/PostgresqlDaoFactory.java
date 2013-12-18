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

    /**
     * Is used to set connection pool to the dao factory.
     * 
     * @param connectionPool The connection pool to set.
     */
    @Override
    public void setConnectionPool(ConnectionPool connectionPool) {
	this.connectionPool = connectionPool;
    }
    
    /**
     * Is used to take user dao.
     * @return The user dao implementation.
     * @throws DaoException If something fails.
     */
    @Override
    public UserDao takeUserDao() throws DaoException {
	Connection connection = connectionPool.takeConnection();
	if (connection == null) {
	    throw new DaoException("Can not take connection.");
	}
	return new PostgresqlUserDao(connection);
    }
}
