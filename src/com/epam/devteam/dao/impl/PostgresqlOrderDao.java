/**
 * 
 */
package com.epam.devteam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.devteam.dao.AbstractDao;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.OrderDao;
import com.epam.devteam.db.ConnectionPool;
import com.epam.devteam.db.ConnectionPoolException;
import com.epam.devteam.entity.order.Order;
import com.epam.devteam.entity.order.OrderStatus;
import com.epam.devteam.entity.order.OrderSubject;
import com.epam.devteam.entity.user.Customer;

/**
 * @date Jan 11, 2014
 * @author Andrey Kovalskiy
 */
public class PostgresqlOrderDao extends AbstractDao implements OrderDao {
    private static final Logger LOGGER = Logger
	    .getLogger(PostgresqlOrderDao.class);
    private final static String sqlOrderCreate = "INSERT INTO orders (date, status, subject, topic, message, file_name, file_content, customer_id) VALUES (?,?,?,?,?,?,?,?)";
    private final static String sqlOrderFindById = "SELECT * FROM orders JOIN users ON orders.id=users.id WHERE orders.id=";
    private final static String sqlOrderFindAll = "SELECT * FROM orders JOIN users ON orders.customer_id=users.id;";
    private final static String sqlOrderFindByCustomerId = "SELECT * FROM orders WHERE customer_id=";
    private final static String sqlOrderUpdateStatus = "UPDATE orders set status=? WHERE id=?";

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
	LOGGER.debug("create()");
	Order order;
	Connection connection;
	PreparedStatement statement;

	try {
	    connection = getConnectionPool().takeConnection();
	    LOGGER.debug("Connection has been taken.");
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
	    statement.setInt(8, order.getCustomer().getId());
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
    public Order find(Integer id) throws DaoException {
	LOGGER.debug("find()");
	Order order;
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
	order = new Order();
	try {
	    resultSet = statement.executeQuery(sqlOrderFindById + id + ";");
	    LOGGER.debug("Statement has been executed.");
	    if (resultSet.next()) {
		order = createOrder(resultSet, true);
	    }
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be executed.", e);
	    throw new DaoException();
	}
	freeConnection(connection, statement);
	return order;
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
	LOGGER.debug("list()");
	List<Order> orders;
	Order order;
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
	order = new Order();
	try {
	    resultSet = statement.executeQuery(sqlOrderFindAll);
	    LOGGER.debug("Statement has been executed.");
	    orders = new ArrayList<Order>();
	    while (resultSet.next()) {
		order = createOrder(resultSet, true);
		orders.add(order);
	    }
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be executed.", e);
	    throw new DaoException();
	}
	freeConnection(connection, statement);
	return orders;
    }

    @Override
    public List<Order> listByCustomerId(int id) throws DaoException {
	LOGGER.debug("listByCustomerId()");
	List<Order> orders;
	Order order;
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
	order = new Order();
	try {
	    resultSet = statement.executeQuery(sqlOrderFindByCustomerId + id
		    + ";");
	    LOGGER.debug("Statement has been executed.");
	    orders = new ArrayList<Order>();
	    while (resultSet.next()) {
		order = createOrder(resultSet, false);
		orders.add(order);
	    }
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be executed.", e);
	    throw new DaoException();
	}
	freeConnection(connection, statement);
	return orders;
    }

    /**
     * Is used to create order from result set.
     * 
     * @param resultSet The result set.
     * @param customerFlag If true creates and sets the customer.
     * @return The order.
     * @throws SQLException If something fails.
     */
    private Order createOrder(ResultSet resultSet, boolean customerFlag)
	    throws SQLException {
	Order order = new Order();
	order.setId(resultSet.getInt("id"));
	order.setDate(resultSet.getDate("date"));
	order.setStatus(OrderStatus.valueOf(resultSet.getString("status")));
	order.setSubject(OrderSubject.valueOf(resultSet.getString("subject")));
	order.setTopic(resultSet.getString("topic"));
	order.setMessage(resultSet.getString("message"));
	order.setFileName(resultSet.getString("file_name"));
	order.setFileContent(resultSet.getBytes("file_content"));
	if (customerFlag) {
	    Customer customer = new Customer();
	    customer.setId(resultSet.getInt("customer_id"));
	    customer.setFirstName(resultSet.getString("first_name"));
	    customer.setLastName(resultSet.getString("last_name"));
	    customer.setCompany(resultSet.getString("company"));
	    customer.setPosition(resultSet.getString("position"));
	    customer.setEmail(resultSet.getString("email"));
	    customer.setPhone(resultSet.getString("phone"));
	    customer.setAddress(resultSet.getString("address"));
	    order.setCustomer(customer);
	}
	return order;
    }

    @Override
    public void updateStatus(int id, OrderStatus status) throws DaoException {
	LOGGER.debug("updateStatus()");
	Connection connection;
	PreparedStatement statement;

	try {
	    connection = getConnectionPool().takeConnection();
	    LOGGER.debug("Connection has been taken.");
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException();
	}
	try {
	    statement = connection.prepareStatement(sqlOrderUpdateStatus);
	    statement.setString(1, status.name());
	    statement.setInt(2, id);
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
