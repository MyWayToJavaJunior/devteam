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
 * The <code>DaoFactory</code> is used to get instances of required dao
 * implementations.
 * 
 * @date Dec 15, 2013
 * @author Andrey Kovalskiy
 * 
 */
public abstract class DaoFactory {
    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);
    private static DatabaseType databaseType;

    /**
     * Is used to get dao factory implementation to work with required database.
     * Database type is defined in property file.
     * 
     * @param daoFactoryTypes The implementation type.
     * @return The dao factory instance.
     * @throws DaoException If something wrong with database type or something
     *             fails with connection pool.
     * @see DatabaseType
     */
    public static DaoFactory getDaoFactory() throws DaoException {
	DaoFactory daoFactory = null;
	ConnectionPool connectionPool = null;
	if (databaseType == null) {
	    initDatabaseType();
	}
	try {
	    connectionPool = ConnectionPool.getInstance();
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection pool cannot be initialized.");
	    throw new DaoException();
	}
	switch (databaseType) {
	case POSTGRESQL:
	    daoFactory = new PostgresqlDaoFactory(connectionPool);
	    break;
	default:
	    LOGGER.warn("Database type is defined but not available.");
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
    private static void initDatabaseType() throws DaoException {
	try {
	    PropertyManager propertyManager = PropertyManager.getInstance();
	    databaseType = DatabaseType.valueOf(propertyManager.getString(
		    "db.name").toUpperCase());
	    LOGGER.debug("Database type was initialized as " + databaseType);
	} catch (PropertyManagerException e) {
	    LOGGER.warn("Database type can not be initialized.");
	    throw new DaoException();
	}
    }

    /**
     * Is used to get user dao.
     * 
     * @return The user dao implementation.
     * @throws DaoException If something fails.
     */
    public abstract UserDao getUserDao();

    /**
     * Is used to get order dao.
     * 
     * @return The order dao implementation.
     * @throws DaoException If something fails.
     */
    public abstract OrderDao getOrderDao();

    /**
     * Is used to get order feedback dao.
     * 
     * @return The order dao implementation.
     * @throws DaoException If something fails.
     */
    public abstract FeedbackDao getFeedbackDao();

}
