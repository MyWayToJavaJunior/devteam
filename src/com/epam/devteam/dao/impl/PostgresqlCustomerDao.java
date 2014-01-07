/**
 * 
 */
package com.epam.devteam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.devteam.dao.CustomerDao;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.db.ConnectionPool;
import com.epam.devteam.db.ConnectionPoolException;
import com.epam.devteam.entity.Customer;
import com.epam.devteam.entity.UserRole;

/**
 * @date Jan 4, 2014
 * @author Andrey Kovalskiy
 */
public class PostgresqlCustomerDao extends CustomerDao {
    private static final Logger LOGGER = Logger
	    .getLogger(PostgresqlCustomerDao.class);
    private static String sqlCustomerCreate = "INSERT INTO users (email,password,registration_date,role,is_active,first_name,last_name,birth_date,address,phone,company,position) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String sqlCustomerSelect = "SELECT * FROM users WHERE (users.email=? AND users.password=?)";
    private static String sqlCustomerUpdate = "UPDATE users set first_name = ?, last_name = ?, birth_date = ?, address = ?, phone = ?, company = ?, position = ? WHERE id = ? ";

    /**
     * Initializes a newly created {@code PostgresqlCustomerDao} object.
     */
    public PostgresqlCustomerDao() {
	super();
    }

    /**
     * Initializes a newly created {@code PostgresqlCustomerDao} object and
     * connection with the given connection value.
     * 
     * @param connection The connection to use to connect to the database.
     */
    public PostgresqlCustomerDao(ConnectionPool connectionPool) {
	setConnectionPool(connectionPool);
    }

    @Override
    public void create(Customer object) throws DaoException {
	Customer customer = object;
	Connection connection;
	PreparedStatement statement;
	try {
	    connection = getConnectionPool().takeConnection();
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException();
	}
	try {
	    statement = connection.prepareStatement(sqlCustomerCreate);
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException();
	}
	try {
	    statement.setString(1, customer.getEmail());
	    statement.setString(2, customer.getPassword());
	    statement.setDate(3, customer.getRegistrationDate());
	    statement.setString(4, customer.getRole().toString());
	    statement.setBoolean(5, customer.isActive());
	    statement.setString(6, customer.getFirstName());
	    statement.setString(7, customer.getLastName());
	    statement.setDate(8, customer.getBirthDate());
	    statement.setString(9, customer.getAddress());
	    statement.setString(10, customer.getPhone());
	    statement.setString(11, customer.getCompany());
	    statement.setString(12, customer.getPosition());
	    statement.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException();
	}
	freeConnection(connection, statement);
    }

    @Override
    public Customer find(Long id) throws DaoException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void update(Customer object) throws DaoException {
	Connection connection;
	PreparedStatement statement;
	try {
	    connection = getConnectionPool().takeConnection();
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException();
	}
	try {
	    statement = connection.prepareStatement(sqlCustomerUpdate);
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException();
	}
	Customer customer = (Customer) object;
	try {
	    statement.setString(1, customer.getFirstName());
	    statement.setString(2, customer.getLastName());
	    statement.setDate(3, customer.getBirthDate());
	    statement.setString(4, customer.getAddress());
	    statement.setString(5, customer.getPhone());
	    statement.setString(6, customer.getCompany());
	    statement.setString(7, customer.getPosition());
	    statement.setInt(8, customer.getId());
	    statement.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException();
	}
	freeConnection(connection, statement);

    }

    @Override
    public void delete(Customer object) throws DaoException {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Customer> list() throws DaoException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Customer find(String email, String password) throws DaoException {
	Customer customer = null;
	Connection connection;
	PreparedStatement statement;
	ResultSet resultSet;
	try {
	    connection = getConnectionPool().takeConnection();
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException();
	}
	try {
	    statement = connection.prepareStatement(sqlCustomerSelect);
	    statement.setString(1, email);
	    statement.setString(2, password);
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException();
	}
	try {
	    resultSet = statement.executeQuery();
	    LOGGER.debug("Query was executed.");
	    if (resultSet.next()) {
		customer = new Customer();
		customer.setId(resultSet.getInt("id"));
		customer.setEmail(resultSet.getString("email"));
		customer.setPassword(resultSet.getString("password"));
		customer.setRegistrationDate(resultSet
			.getDate("registration_date"));
		try {
		    customer.setRole(UserRole.valueOf(resultSet
			    .getString("role")));
		} catch (IllegalArgumentException e) {
		    LOGGER.warn("Unknown role: " + resultSet.getString("role"));
		}
		customer.setActive(resultSet.getBoolean("is_active"));
		customer.setFirstName(resultSet.getString("first_name"));
		customer.setLastName(resultSet.getString("last_name"));
		customer.setBirthDate(resultSet.getDate("birth_date"));
		customer.setAddress(resultSet.getString("address"));
		customer.setPhone(resultSet.getString("phone"));
		customer.setCompany(resultSet.getString("company"));
		customer.setPosition(resultSet.getString("position"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException();
	}

	freeConnection(connection, statement);

	return customer;
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
