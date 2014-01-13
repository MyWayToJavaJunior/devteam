package com.epam.devteam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.devteam.dao.AbstractDao;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.FeedbackDao;
import com.epam.devteam.db.ConnectionPool;
import com.epam.devteam.db.ConnectionPoolException;
import com.epam.devteam.entity.response.Feedback;
import com.epam.devteam.entity.user.Employee;

public class PostgresqlFeedbackDao extends AbstractDao implements FeedbackDao {
    private static final Logger LOGGER = Logger
	    .getLogger(PostgresqlFeedbackDao.class);
    private final static String sqlCreate = "INSERT INTO feedbacks (date, order_id, message, file_name, file_content, manager_id) VALUES (?,?,?,?,?,?)";
    private final static String sqlFindByOrderId = "SELECT * FROM feedbacks JOIN users ON feedbacks.manager_id=users.id WHERE feedbacks.order_id=";

    /**
     * Initializes a newly created {@code PostgresqlFeedbackDao} object.
     */
    public PostgresqlFeedbackDao() {
	super();
    }

    /**
     * Initializes a newly created {@code PostgresqlFeedbackDao} object.
     * 
     * @param connectionPool
     */
    public PostgresqlFeedbackDao(ConnectionPool connectionPool) {
	super(connectionPool);
    }

    @Override
    public void create(Feedback object) throws DaoException {
	LOGGER.debug("create()");
	Feedback feedback;
	Connection connection;
	PreparedStatement statement;

	try {
	    connection = getConnectionPool().takeConnection();
	    LOGGER.debug("Connection has been taken.");
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException();
	}
	feedback = (Feedback) object;
	try {
	    statement = connection.prepareStatement(sqlCreate);
	    statement.setDate(1, feedback.getDate());
	    statement.setInt(2, feedback.getOrderId());
	    statement.setString(3, feedback.getMessage());
	    statement.setString(4, feedback.getFileName());
	    statement.setBytes(5, feedback.getFileContent());
	    statement.setInt(6, feedback.getManager().getId());
	    LOGGER.debug("Statement has been created.");
	} catch (SQLException e) {
	    getConnectionPool().returnConnection(connection);
	    LOGGER.warn("Statement cannot be created.", e);
	    throw new DaoException();
	}
	try {
	    statement.execute();
	    LOGGER.debug("Statement has been executed.");
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be executed.", e);
	    throw new DaoException();
	}
	freeConnection(connection, statement);

    }

    @Override
    public Feedback find(Integer id) throws DaoException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void update(Feedback object) throws DaoException {
	// TODO Auto-generated method stub

    }

    @Override
    public void delete(Feedback object) throws DaoException {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Feedback> list() throws DaoException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Feedback findByOrderId(int id) throws DaoException {
	LOGGER.debug("findByOrderId()");
	Feedback feedback;
	Connection connection;
	Statement statement;
	ResultSet resultSet;

	try {
	    connection = getConnectionPool().takeConnection();
	    LOGGER.debug("Connection has been taken.");
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException();
	}
	try {
	    statement = connection.createStatement();
	    LOGGER.debug("Statement has been created.");
	} catch (SQLException e) {
	    getConnectionPool().returnConnection(connection);
	    LOGGER.warn("Statement cannot be created.", e);
	    throw new DaoException();
	}
	try {
	    resultSet = statement.executeQuery(sqlFindByOrderId + id + ";");
	    feedback = new Feedback();
	    if (resultSet.next()) {
		feedback = createFeedback(resultSet);
	    }
	    LOGGER.debug("Statement has been executed.");
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be executed.", e);
	    throw new DaoException();
	}
	freeConnection(connection, statement);
	return feedback;
    }

    /**
     * Is used to create feedback from result set.
     * 
     * @param resultSet The result set.
     * @param customerFlag If true creates and sets the customer.
     * @return The order.
     * @throws SQLException If something fails.
     */
    private Feedback createFeedback(ResultSet resultSet) throws SQLException {
	Feedback feedback = new Feedback();
	feedback.setOrderId(resultSet.getInt("order_id"));
	feedback.setDate(resultSet.getDate("date"));
	feedback.setMessage(resultSet.getString("message"));
	feedback.setFileName(resultSet.getString("file_name"));
	feedback.setFileContent(resultSet.getBytes("file_content"));
	Employee manager = new Employee();
	manager.setId(resultSet.getInt("manager_id"));
	manager.setFirstName(resultSet.getString("first_name"));
	manager.setLastName(resultSet.getString("last_name"));
	manager.setEmail(resultSet.getString("email"));
	manager.setPhone(resultSet.getString("phone"));
	manager.setAddress(resultSet.getString("address"));
	feedback.setManager(manager);
	return feedback;
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
