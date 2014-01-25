package com.epam.devteam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.devteam.db.ConnectionPool;
import com.epam.devteam.db.ConnectionPoolException;
import com.epam.devteam.entity.feedback.Feedback;
import com.epam.devteam.entity.order.Order;

public class FeedbackService {
    private final static Logger LOGGER = Logger
	    .getLogger(FeedbackService.class);
    private ConnectionPool connectionPool;

    public void createFeedback(Feedback feedback, Order order)
	    throws ServiceException {
	Connection connection;
	PreparedStatement feedbackStatement = null;
	PreparedStatement orderStatement = null;
	LOGGER.debug("Feedback create service...");
	try {
	    connection = connectionPool().takeConnection();
	    LOGGER.debug("Connection has been taken.");
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new ServiceException(e);
	}
	try {
	    connection.setAutoCommit(false);
	    LOGGER.debug("Auto commit has been disabled.");
	} catch (SQLException e) {
	    connectionPool.returnConnection(connection);
	    LOGGER.warn("Auto commit cannot be set.");
	    throw new ServiceException(e);
	}
	try {
	    feedbackStatement = connection
		    .prepareStatement("INSERT INTO feedbacks (date, order_id, message, file_name, file_content, manager_id) VALUES (?,?,?,?,?,?)");
	    feedbackStatement.setDate(1, feedback.getDate());
	    feedbackStatement.setInt(2, feedback.getOrderId());
	    feedbackStatement.setString(3, feedback.getMessage());
	    feedbackStatement.setString(4, feedback.getFileName());
	    feedbackStatement.setBytes(5, feedback.getFileContent());
	    feedbackStatement.setInt(6, feedback.getManager().getId());
	    LOGGER.debug("Feedback statement has been created.");
	    feedbackStatement.execute();
	    LOGGER.debug("Feedback statement has been executed.");
	    orderStatement = connection
		    .prepareStatement("UPDATE orders SET status = ? WHERE id = ?");
	    orderStatement.setString(1, order.getStatus().name());
	    orderStatement.setInt(2, order.getId());
	    LOGGER.debug("Order statement has been created.");
	    orderStatement.execute();
	    LOGGER.debug("Order statement has been executed.");
	    connection.commit();
	    LOGGER.debug("Commit success.");
	    connection.setAutoCommit(true);
	    LOGGER.debug("Auto commit has been enabled.");
	} catch (SQLException e) {
	    LOGGER.debug("Feedback cannot be created");
	    try {
		connection.rollback();
		LOGGER.debug("Roll back success");
	    } catch (SQLException e1) {
		LOGGER.warn("Roll back failed.");
	    }
	    throw new ServiceException(e);
	} finally {
	    try {
		if (feedbackStatement != null) {
		    feedbackStatement.close();
		    LOGGER.debug("Feedback statement has been closed.");
		}
		if (orderStatement != null) {
		    orderStatement.close();
		    LOGGER.debug("Order statement has been closed.");
		}
	    } catch (SQLException e) {
		LOGGER.debug("Statements cannot be closed.");
	    }
	    connectionPool.returnConnection(connection);
	}
    }

    /**
     * Is used to get connection pool. It initializes pool during the first use.
     * 
     * @return The connection pool instance.
     * @throws ConnectionPoolException
     */
    private ConnectionPool connectionPool() throws ConnectionPoolException {
	if (connectionPool == null) {
	    connectionPool = ConnectionPool.getInstance();
	    LOGGER.debug("Connection pool has been taken.");
	}
	return connectionPool;
    }

}
