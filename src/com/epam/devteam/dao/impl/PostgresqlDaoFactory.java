/**
 * 
 */
package com.epam.devteam.dao.impl;

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
     * Initializes a newly created {@code PostgresqlDaoFactory} object.
     */
    public PostgresqlDaoFactory(ConnectionPool connectionPool) {
	this.connectionPool = connectionPool;
    }

    /**
     * Is used to get user dao.
     * 
     * @return The request dao implementation.
     * @throws DaoException If something fails.
     */
    public UserDao getUserDao() {
	return new PostgresqlUserDao(connectionPool);
    }

}
