/**
 * 
 */
package com.epam.devteam.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.epam.devteam.db.ConnectionPool;
import com.epam.devteam.db.ConnectionPoolException;

/**
 * @date Dec 19, 2013
 * @author Andrey Kovalskiy
 * 
 */
public final class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = Logger
	    .getLogger(ContextListener.class);
    
    /**
     ** Notification that the web application initialization process is starting.
     * All ServletContextListeners are notified of context initialization before
     * any filter or servlet in the web application is initialized.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
	LOGGER.debug("Servlet initilization...");
    }
    
    /**
     ** Notification that the servlet context is about to be shut down. All
     * servlets and filters have been destroy()ed before any
     * ServletContextListeners are notified of context destruction.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
	ConnectionPool connectionPool;
	try {
	    connectionPool = ConnectionPool.getInstance();
	    connectionPool.closeConnections();
	    LOGGER.debug("Connections have been closed.");
	} catch (ConnectionPoolException e) {
	    LOGGER.error("Somethng fails with connection pool.");
	}
    }

}
