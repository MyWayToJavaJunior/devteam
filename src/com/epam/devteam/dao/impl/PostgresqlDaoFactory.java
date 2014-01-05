/**
 * 
 */
package com.epam.devteam.dao.impl;

import com.epam.devteam.dao.CustomerDao;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.RequestDao;
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
     * @return The user dao implementation.
     * @throws DaoException If something fails.
     */
    @Override
    public UserDao getUserDao() {
	return new PostgresqlUserDao(connectionPool);
    }

    /**
     * Is used to get customer dao.
     * 
     * @return The request dao implementation.
     * @throws DaoException If something fails.
     */
    @Override
    public CustomerDao getCustomerDao() {
	return new PostgresqlCustomerDao(connectionPool);
    }

    /**
     * Is used to get request dao.
     * 
     * @return The request dao implementation.
     * @throws DaoException If something fails.
     */
    @Override
    public RequestDao getRequestDao() {
	return new PostgresqlRequestDao(connectionPool);
    }
}
