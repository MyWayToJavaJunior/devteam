package com.epam.devteam.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.RequestDao;
import com.epam.devteam.db.ConnectionPool;
import com.epam.devteam.db.ConnectionPoolException;
import com.epam.devteam.entity.Request;

public class PostgresqlRequestDao extends RequestDao {
    private static final Logger LOGGER = Logger
	    .getLogger(PostgresqlRequestDao.class);
    
    /**
     * Initializes a newly created {@code PostgresqlRequestDao} object.
     */
    public PostgresqlRequestDao() {
	super();
    }

    /**
     * Initializes a newly created {@code PostgresqlRequestDao} object and
     * connection with the given connection value.
     * 
     * @param connection The connection to use to connect to the database.
     */
    public PostgresqlRequestDao(ConnectionPool connectionPool) {
	setConnectionPool(connectionPool);
    }
    
    /**
     * Is used to create given request in the database.
     * 
     * @param object The request to create.
     * @throws DaoException If something fails at database level.
     */
    @Override
    public void create(Request object) throws DaoException {
	String sql = "INSERT INTO requests (date,status,customerId,managerId) VALUES (?,?,?,?)";
	Connection connection;
	PreparedStatement statement;
	try {
	    connection = getConnectionPool().takeConnection();
	    LOGGER.debug("Connection has been taken");
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException();
	}
	try {
	    statement = connection.prepareStatement(sql);
	    LOGGER.debug("Statement has been created.");
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException();
	}
	try {
	    statement.setDate(1, object.getDate());
	    statement.setString(2, object.getStatus().toString());
	    statement.setLong(3, object.getCustomerId());
	    statement.setLong(4, object.getManagerId());
	    statement.execute();
	    LOGGER.debug("Statement has been executed.");
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException();
	}
	freeConnection(connection, statement);
    }

    /**
     * Is used to return a request in the database by the given id, otherwise
     * {@code null}.
     * 
     * @param id The id of the object to be returned.
     * @return The request from the database with required id, otherwise
     *         {@code null}.
     * @throws DaoException If something fails at database level.
     */
    @Override
    public Request find(Long id) throws DaoException {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * Is used to update given request.
     * 
     * @param object The object to update.
     * @throws DaoException If something fails at database level.
     */
    @Override
    public void update(Request object) throws DaoException {
	// TODO Auto-generated method stub

    }

    /**
     * Is used to delete the given request from the database.
     * 
     * @param object The object to delete.
     * @throws DaoException If something fails at database level.
     */
    @Override
    public void delete(Request object) throws DaoException {
	// TODO Auto-generated method stub

    }

    /**
     * Is used to get all of the requests from the database.
     * 
     * @return The list of all requests in the database.
     * @throws DaoException If something fails at database level.
     */
    @Override
    public List<Request> list() throws DaoException {
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
