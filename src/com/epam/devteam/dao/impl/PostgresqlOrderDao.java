/**
 * 
 */
package com.epam.devteam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.devteam.dao.AbstractDao;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.OrderDao;
import com.epam.devteam.db.ConnectionPool;
import com.epam.devteam.db.ConnectionPoolException;
import com.epam.devteam.entity.order.Order;

/**
 * @date Jan 11, 2014
 * @author Andrey Kovalskiy
 */
public class PostgresqlOrderDao extends AbstractDao implements OrderDao {
    private static final Logger LOGGER = Logger
	    .getLogger(PostgresqlOrderDao.class);
    private final static String sqlOrderCreate = "INSERT INTO orders (date, status, subject, topic, message, file_name, file_content, customer_id) VALUES (?,?,?,?,?,?,?,?)";

    /**
     * Initializes a newly created {@code connectionPool} object.
     */
    public PostgresqlOrderDao() {
	super();
    }

    /**
     * Initializes a newly created {@code connectionPool} object.
     * 
     * @param connectionPool
     */
    public PostgresqlOrderDao(ConnectionPool connectionPool) {
	super(connectionPool);
    }

    @Override
    public void create(Order object) throws DaoException {
	Order order;
	Connection connection;
	PreparedStatement statement;

	try {
	    connection = getConnectionPool().takeConnection();
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException();
	}
	order = (Order) object;
	try {
	    statement = connection.prepareStatement(sqlOrderCreate);
	    statement.setDate(1, order.getDate());
	    statement.setString(2, order.getStatus().name());
	    statement.setString(3, order.getSubject().name());
	    statement.setString(4, order.getTopic());
	    statement.setString(5, order.getMessage());
	    statement.setString(6, order.getFileName());
	    statement.setBytes(7, order.getFileContent());
	    ;
	    statement.setInt(8, order.getCustomer().getId());
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be created.", e);
	    throw new DaoException();
	}
	try {
	    statement.execute();
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be executed.", e);
	    throw new DaoException();
	}
	freeConnection(connection, statement);
    }

    @Override
    public Order find(Integer id) throws DaoException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void update(Order object) throws DaoException {
	// TODO Auto-generated method stub

    }

    @Override
    public void delete(Order object) throws DaoException {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Order> list() throws DaoException {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * Is used to close statement and return connection to the connection pool.
     * 
     * @param connection The connection to return.
     * @param statement The statement to close.
     */
    private void freeConnection(Connection connection, Statement statement) {
	if (statement != null) {
	    try {
		statement.close();
		LOGGER.debug("Statement has been closed.");
	    } catch (SQLException e) {
		LOGGER.warn("Statement cannot be closed.");
	    }
	}
	getConnectionPool().returnConnection(connection);
    }

}
