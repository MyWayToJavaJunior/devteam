/**
 * 
 */
package com.epam.devteam.dao;

import org.apache.log4j.Logger;

import com.epam.devteam.dao.impl.PostgresqlDaoFactory;
import com.epam.devteam.db.ConnectionPool;
import com.epam.devteam.db.ConnectionPoolException;
import com.epam.devteam.db.DatabaseType;
import com.epam.devteam.util.property.PropertyManager;
import com.epam.devteam.util.property.PropertyManagerException;

/**
 * @date Dec 15, 2013
 * @author Andrey Kovalskiy
 * 
 */
public abstract class DaoFactory {
    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);
    private static DatabaseType databaseType = null;

    /**
     * Is used to set connection pool to the dao factory.
     * 
     * @param connectionPool The connection pool to set.
     */
    public abstract void setConnectionPool(ConnectionPool connectionPool);

    /**
     * Is used to take user dao.
     * 
     * @return The user dao implementation.
     * @throws DaoException If something fails.
     */
    public abstract UserDao takeUserDao() throws DaoException;

    /**
     * Is used to take dao factory implementation to work with required
     * database. Database type is defined in property file.
     * 
     * @param daoFactoryTypes The implementation type.
     * @return The dao factory instance.
     * @throws DaoException If something wrong with database type or something
     *             fails with connection pool.
     * @see DatabaseType
     */
    public static DaoFactory takeDaoFactory() throws DaoException {
	DaoFactory daoFactory = null;
	if (databaseType == null) {
	    initDatabaseType();
	}
	switch (databaseType) {
	case POSTGRESQL:
	    daoFactory = new PostgresqlDaoFactory();
	    break;
	default:
	    LOGGER.warn("Database type is defined but not available!");
	    throw new DaoException();
	}
	try {
	    daoFactory.setConnectionPool(ConnectionPool.getInstance());
	} catch (ConnectionPoolException e) {
	    LOGGER.error("Connection pool can not be initialized");
	    throw new DaoException();
	}
	return daoFactory;
    }

    /**
     * Is used to initialize current used database type during the first dao
     * factory access.
     * 
     * @throws DaoException If something fails with property manager or current
     *             database property is wrong.
     * @see DatabaseType
     */
    public static void initDatabaseType() throws DaoException {
	try {
	    PropertyManager propertyManager = PropertyManager.getInstance();
	    databaseType = DatabaseType.valueOf(propertyManager.getString(
		    "db.currentdb").toUpperCase());
	    LOGGER.debug("Database type was initialized as " + databaseType);
	} catch (PropertyManagerException e) {
	    LOGGER.error("Database type can not be initialized.");
	    throw new DaoException();
	}
    }
}
