/**
 * 
 */
package com.epam.devteam.dao;

import org.apache.log4j.Logger;

import com.epam.devteam.dao.impl.PostgresqlDaoFactory;
import com.epam.devteam.db.ConnectionPool;

/**
 * @date Dec 15, 2013
 * @author Andrey Kovalskiy
 * 
 */
public abstract class DaoFactory {
    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);
    
    /**
     * Is used to set connection pool to the dao factory.
     * 
     * @param connectionPool The connection pool to set.
     */
    public abstract void setConnectionPool(ConnectionPool connectionPool);
    
    /**
     * Is used to take user dao.
     * @return The user dao implementation.
     * @throws DaoException If something fails.
     */
    public abstract UserDao takeUserDao() throws DaoException;
    
    /**
     * Is used to take dao factory implementation to work with required database.  
     * @param daoFactoryTypes The implementation type.
     * @return The dao factory instance.
     * @throws DaoException If required databse type is not required.
     */
    public static DaoFactory takeDaoFactory(DaoFactoryTypes daoFactoryTypes)
	    throws DaoException {
	DaoFactory daoFactory = null;
	switch (daoFactoryTypes) {
	case POSTGRESQL:
	    daoFactory = new PostgresqlDaoFactory();
	    break;
	default:
	    LOGGER.error("Not available dao factory type!");
	    throw new DaoException();
	}
	return daoFactory;
    }

}
