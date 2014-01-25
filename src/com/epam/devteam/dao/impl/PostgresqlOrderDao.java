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

    /**
     * Is used to create the given order in the database.
     * 
     * @param object The order to create.
     * @throws DaoException If something fails at database level.
     */
    @Override
    public void create(Order object) throws DaoException {
	Order order = (Order) object;
	Connection connection;
	PreparedStatement statement = null;
	LOGGER.debug("Create order action...");
	try {
	    connection = getConnectionPool().takeConnection();
	    LOGGER.debug("Connection has been taken.");
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException(e);
	}
	try {
	    statement = connection
		    .prepareStatement("INSERT INTO orders (date, status, subject, topic, message, file_name, file_content, customer_id) VALUES (?,?,?,?,?,?,?,?)");
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
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException(e);
	}
	try {
	    statement.execute();
	    LOGGER.debug("Statement has been executed.");
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException(e);
	}
	freeConnection(connection, statement);
    }

    /**
     * Is used to return an order from the database by the given id, otherwise
     * {@code null}.
     * 
     * @param id The id of the order to be returned.
     * @return The order from the database with required id, otherwise
     *         {@code null}.
     * @throws DaoException If something fails at database level.
     */
    @Override
    public Order find(int id) throws DaoException {
	Order order = null;
	Connection connection;
	Statement statement = null;
	ResultSet resultSet;
	LOGGER.debug("Find order...");
	try {
	    connection = getConnectionPool().takeConnection();
	    LOGGER.debug("Connection has been taken.");
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException(e);
	}
	try {
	    statement = connection.createStatement();
	    LOGGER.debug("Statement has been created.");
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException(e);
	}
	try {
	    resultSet = statement
		    .executeQuery("SELECT orders.id, orders.date, orders.status, orders.subject, orders.topic, orders.message, orders.file_content, orders.file_name, orders.customer_id, users.first_name, users.last_name, users.company, users.position, users.phone, users.address, users.email FROM orders JOIN users ON orders.customer_id=users.id WHERE orders.id="
			    + id + ";");
	    LOGGER.debug("Statement has been executed.");
	    if (resultSet.next()) {
		order = createOrder(resultSet, true);
	    }
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException(e);
	}
	try {
	    resultSet.close();
	    LOGGER.debug("Result set has been closed.");
	} catch (SQLException e) {
	    LOGGER.debug("Result set cannot be closed.");
	}
	freeConnection(connection, statement);
	return order;
    }

    /**
     * Is used to update the given order.
     * 
     * @param object The order to update.
     * @throws DaoException If something fails at database level.
     */
    @Override
    public void update(Order object) throws DaoException {
	Order order = (Order) object;
	Connection connection;
	PreparedStatement statement = null;
	LOGGER.debug("Update order action...");
	try {
	    connection = getConnectionPool().takeConnection();
	    LOGGER.debug("Connection has been taken.");
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException(e);
	}
	try {
	    statement = connection
		    .prepareStatement("UPDATE orders SET status = ?, date = ?, subject = ?, topic = ?, message = ?, file_name = ?, file_content = ? WHERE id = ?");
	    statement.setString(1, order.getStatus().name());
	    statement.setDate(2, order.getDate());
	    statement.setString(3, order.getSubject().name());
	    statement.setString(4, order.getTopic());
	    statement.setString(5, order.getMessage());
	    statement.setString(6, order.getFileName());
	    statement.setBytes(7, order.getFileContent());
	    statement.setInt(8, order.getId());
	    LOGGER.debug("Statement has been created.");
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException(e);
	}
	try {
	    statement.execute();
	    LOGGER.debug("Statement has been executed.");
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException(e);
	}
	freeConnection(connection, statement);
    }

    /**
     * Is used to delete the order with the given id from the database.
     * 
     * @param id The id of the order to be deleted.
     * @throws DaoException If something fails at database level.
     */
    @Override
    public void delete(int id) throws DaoException {
	LOGGER.warn("Method is not implemented.");
	throw new DaoException();
    }

    /**
     * Is used to get all of the objects from the database.
     * 
     * @return The list of all objects in the database.
     * @throws DaoException If something fails at database level.
     */
    @Override
    public List<Order> list() throws DaoException {
	List<Order> orders;
	Order order;
	Connection connection;
	Statement statement = null;
	ResultSet resultSet;
	LOGGER.debug("List orders action...");
	try {
	    connection = getConnectionPool().takeConnection();
	    LOGGER.debug("Connection has been taken.");
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException(e);
	}
	try {
	    statement = connection.createStatement();
	    LOGGER.debug("Statement has been created.");
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException(e);
	}
	try {
	    resultSet = statement
		    .executeQuery("SELECT orders.id, orders.date, orders.status, orders.subject, orders.topic, orders.message, orders.file_content, orders.file_name, orders.customer_id, users.first_name, users.last_name, users.company, users.position, users.phone, users.address, users.email FROM orders JOIN users ON orders.customer_id=users.id;");
	    LOGGER.debug("Statement has been executed.");
	    orders = new ArrayList<Order>();
	    while (resultSet.next()) {
		order = createOrder(resultSet, true);
		orders.add(order);
	    }
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException(e);
	}
	try {
	    resultSet.close();
	    LOGGER.debug("Result set has been closed.");
	} catch (SQLException e) {
	    LOGGER.debug("Result set cannot be closed.");
	}
	freeConnection(connection, statement);
	return orders;
    }

    /**
     * Is used to get orders from the database with paging.
     * 
     * @param firstRow The row from where to start list orders.
     * @param rowNumber The number of orders to list.
     * @return The list of all orders in the database.
     * @throws DaoException If something fails at database level.
     */
    @Override
    public List<Order> list(int firstRow, int rowNumber) throws DaoException {
	List<Order> orders;
	Order order;
	Connection connection;
	PreparedStatement statement = null;
	ResultSet resultSet;
	LOGGER.debug("List orders action...");
	try {
	    connection = getConnectionPool().takeConnection();
	    LOGGER.debug("Connection has been taken.");
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException(e);
	}
	try {
	    statement = connection
		    .prepareStatement("SELECT orders.id, orders.date, orders.status, orders.subject, orders.topic, orders.message, orders.file_content, orders.file_name, orders.customer_id, users.first_name, users.last_name, users.company, users.position, users.phone, users.address, users.email FROM orders JOIN users ON orders.customer_id=users.id WHERE orders.status != 'TERMINATED' ORDER BY date DESC OFFSET ? LIMIT ?");
	    statement.setInt(1, firstRow);
	    statement.setInt(2, rowNumber);
	    LOGGER.debug("Statement has been created.");
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException(e);
	}
	try {
	    resultSet = statement.executeQuery();
	    LOGGER.debug("Statement has been executed.");
	    orders = new ArrayList<Order>();
	    while (resultSet.next()) {
		order = createOrder(resultSet, true);
		orders.add(order);
	    }
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException(e);
	}
	try {
	    resultSet.close();
	    LOGGER.debug("Result set has been closed.");
	} catch (SQLException e) {
	    LOGGER.debug("Result set cannot be closed.");
	}
	freeConnection(connection, statement);
	return orders;
    }

    /**
     * Is used to get orders from the database with paging. Method returns
     * orders that were created by customer with the given id.
     * 
     * @param id The customer id.
     * @param firstRow The row from where to start list orders.
     * @param rowNumber The number of orders to list.
     * @return The list of all orders in the database.
     * @throws DaoException If something fails at database level.
     */
    @Override
    public List<Order> listByCustomerId(int id, int firstRow, int rowNumber)
	    throws DaoException {
	List<Order> orders;
	Order order;
	Connection connection;
	PreparedStatement statement = null;
	ResultSet resultSet;
	LOGGER.debug("List orders by customer id action...");
	try {
	    connection = getConnectionPool().takeConnection();
	    LOGGER.debug("Connection has been taken.");
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException(e);
	}
	try {
	    statement = connection
		    .prepareStatement("SELECT id, date, status, subject, topic, message, file_content, file_name FROM orders WHERE customer_id = ? ORDER BY date DESC OFFSET ? LIMIT ?");
	    statement.setInt(1, id);
	    statement.setInt(2, firstRow);
	    statement.setInt(3, rowNumber);
	    LOGGER.debug("Statement has been created.");
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException(e);
	}
	try {
	    resultSet = statement.executeQuery();
	    LOGGER.debug("Statement has been executed.");
	    orders = new ArrayList<Order>();
	    while (resultSet.next()) {
		order = createOrder(resultSet, false);
		orders.add(order);
	    }
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException(e);
	}
	try {
	    resultSet.close();
	    LOGGER.debug("Result set has been closed.");
	} catch (SQLException e) {
	    LOGGER.debug("Result set cannot be closed.");
	}
	freeConnection(connection, statement);
	return orders;
    }

    /**
     * Is used to update order status.
     * 
     * @param id The order id to be updated.
     * @param status The status to set.
     * @throws DaoException If something fails.
     */
    @Override
    public void updateStatus(int id, OrderStatus status) throws DaoException {
	Connection connection;
	PreparedStatement statement = null;
	LOGGER.debug("Update order satus...");
	try {
	    connection = getConnectionPool().takeConnection();
	    LOGGER.debug("Connection has been taken.");
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException(e);
	}
	try {
	    statement = connection
		    .prepareStatement("UPDATE orders set status = ? WHERE id = ?");
	    statement.setString(1, status.name());
	    statement.setInt(2, id);
	    LOGGER.debug("Statement has been created.");
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException(e);
	}
	try {
	    statement.execute();
	    LOGGER.debug("Statement has been executed.");
	} catch (SQLException e) {
	    freeConnection(connection, statement);
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException(e);
	}
	freeConnection(connection, statement);
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
	LOGGER.debug("Connection has been returned.");
    }

}
