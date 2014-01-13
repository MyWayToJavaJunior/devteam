package com.epam.devteam.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.devteam.dao.AbstractDao;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.db.ConnectionPool;
import com.epam.devteam.db.ConnectionPoolException;
import com.epam.devteam.entity.user.Customer;
import com.epam.devteam.entity.user.Employee;
import com.epam.devteam.entity.user.User;
import com.epam.devteam.entity.user.UserRole;

public class PostgresqlUserDao extends AbstractDao implements UserDao {
    private static final Logger LOGGER = Logger
	    .getLogger(PostgresqlUserDao.class);
    private final static String sqlEmployeeCreate = "INSERT INTO users (email, password, registration_date, role, is_active, first_name, last_name,  birth_date, address, phone, qualification) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private final static String sqlCustomerCreate = "INSERT INTO users (email, password, registration_date, role, is_active, first_name, last_name,  birth_date, address, phone, company, position) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private final static String sqlEmployeeUpdate = "UPDATE users set role = ?, is_active = ?, first_name = ?, last_name = ?,  birth_date = ?, address = ?, phone = ?, qualification = ?  WHERE id = ?";
    private final static String sqlCustomerUpdate = "UPDATE users set role = ?, is_active = ?, first_name = ?, last_name = ?,  birth_date = ?, address = ?, phone = ?, company = ?, position = ?  WHERE id = ?";
    private final static String sqlUserSelectByEmailPassword = "SELECT * FROM users WHERE (users.email=? AND users.password=?)";
    private final static String sqlUserSelectById = "SELECT * FROM users WHERE (users.id=?)";
    private final static String sqlAllUsersSelectByEmailPassword = "SELECT * from users";

    /**
     * Initializes a newly created {@code PostgresqlCustomerDao} object.
     */
    public PostgresqlUserDao() {
	super();
    }

    /**
     * Initializes a newly created {@code PostgresqlCustomerDao} object and
     * connection with the given connection value.
     * 
     * @param connection The connection to use to connect to the database.
     */
    public PostgresqlUserDao(ConnectionPool connectionPool) {
	setConnectionPool(connectionPool);
    }

    @Override
    public void create(User object) throws DaoException {
	User user;
	Connection connection;
	PreparedStatement statement;
	try {
	    connection = getConnectionPool().takeConnection();
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException();
	}

	try {
	    switch (object.getRole()) {
	    case CUSTOMER:
		Customer customer = (Customer) object;
		statement = connection.prepareStatement(sqlCustomerCreate);
		statement.setString(11, customer.getCompany());
		statement.setString(12, customer.getPosition());
		user = customer;
		break;
	    default:
		Employee employee = (Employee) object;
		statement = connection.prepareStatement(sqlEmployeeCreate);
		statement.setString(11, employee.getQualification());
		user = employee;
		break;
	    }
	    statement.setString(1, user.getEmail());
	    statement.setString(2, user.getPassword());
	    statement.setDate(3, new Date(new java.util.Date().getTime()));
	    statement.setString(4, user.getRole().name());
	    statement.setBoolean(5, true);
	    statement.setString(6, user.getFirstName());
	    statement.setString(7, user.getLastName());
	    statement.setDate(8, user.getBirthDate());
	    statement.setString(9, user.getAddress());
	    statement.setString(10, user.getPhone());
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException();
	}
	try {
	    statement.execute();
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException();
	}
	freeConnection(connection, statement);
    }

    @Override
    public User find(Integer id) throws DaoException {
	User user = null;
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
	    statement = connection.prepareStatement(sqlUserSelectById);
	    statement.setInt(1, id);
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException();
	}
	try {
	    resultSet = statement.executeQuery();
	    LOGGER.debug("Statement was executed.");
	    if (resultSet.next()) {
		UserRole role;
		try {
		    role = UserRole.valueOf(resultSet.getString("role"));
		} catch (IllegalArgumentException e) {
		    LOGGER.warn("Unknown role: " + resultSet.getString("role"));
		    throw new DaoException();
		}
		switch (role) {
		case CUSTOMER:
		    Customer customer = new Customer();
		    customer.setCompany(resultSet.getString("company"));
		    customer.setPosition(resultSet.getString("position"));
		    user = customer;
		    break;
		default:
		    Employee employee = new Employee();
		    employee.setQualification(resultSet
			    .getString("qualification"));
		    user = employee;
		    break;
		}
		user.setId(resultSet.getInt("id"));
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getString("password"));
		user.setRole(role);
		user.setActive(resultSet.getBoolean("is_active"));
		user.setRegistrationDate(resultSet.getDate("registration_date"));
		user.setFirstName(resultSet.getString("first_name"));
		user.setLastName(resultSet.getString("last_name"));
		user.setBirthDate(resultSet.getDate("birth_date"));
		user.setAddress(resultSet.getString("address"));
		user.setPhone(resultSet.getString("phone"));
	    }
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException();
	}
	freeConnection(connection, statement);
	return user;
    }

    @Override
    public void update(User object) throws DaoException {
	User user;
	Connection connection;
	PreparedStatement statement;
	try {
	    connection = getConnectionPool().takeConnection();
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException();
	}

	try {
	    switch (object.getRole()) {
	    case CUSTOMER:
		Customer customer = (Customer) object;
		statement = connection.prepareStatement(sqlCustomerUpdate);
		statement.setString(8, customer.getCompany());
		statement.setString(9, customer.getPosition());
		statement.setInt(10, customer.getId());
		user = customer;
		break;
	    default:
		Employee employee = (Employee) object;
		statement = connection.prepareStatement(sqlEmployeeUpdate);
		statement.setString(8, employee.getQualification());
		statement.setInt(9, employee.getId());
		user = employee;
		break;
	    }
	    statement.setString(1, user.getRole().name());
	    statement.setBoolean(2, user.isActive());
	    statement.setString(3, user.getFirstName());
	    statement.setString(4, user.getLastName());
	    statement.setDate(5, user.getBirthDate());
	    statement.setString(6, user.getAddress());
	    statement.setString(7, user.getPhone());
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be created.", e);
	    throw new DaoException();
	}
	try {
	    statement.execute();
	    LOGGER.debug("Statement was executed.");
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException();
	}
	freeConnection(connection, statement);
    }

    @Override
    public void delete(User object) throws DaoException {
	LOGGER.warn("Operation is not supported");
	throw new DaoException();
    }

    @Override
    public List<User> list() throws DaoException {
	List<User> users;
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	try {
	    connection = getConnectionPool().takeConnection();
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection cannot be taken.");
	    throw new DaoException();
	}
	try {
	    statement = connection.createStatement();
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException();
	}
	try {
	    resultSet = statement
		    .executeQuery(sqlAllUsersSelectByEmailPassword);
	    LOGGER.debug("Statement was executed.");
	    User user;
	    users = new ArrayList<User>();
	    while (resultSet.next()) {
		UserRole role;
		try {
		    role = UserRole.valueOf(resultSet.getString("role"));
		} catch (IllegalArgumentException e) {
		    LOGGER.warn("Unknown role: " + resultSet.getString("role"));
		    throw new DaoException();
		}
		switch (role) {
		case CUSTOMER:
		    Customer customer = new Customer();
		    customer.setCompany(resultSet.getString("company"));
		    customer.setPosition(resultSet.getString("position"));
		    user = customer;
		    break;
		default:
		    Employee employee = new Employee();
		    employee.setQualification(resultSet
			    .getString("qualification"));
		    user = employee;
		    break;
		}
		user.setId(resultSet.getInt("id"));
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getString("password"));
		user.setRole(role);
		user.setActive(resultSet.getBoolean("is_active"));
		user.setRegistrationDate(resultSet.getDate("registration_date"));
		user.setFirstName(resultSet.getString("first_name"));
		user.setLastName(resultSet.getString("last_name"));
		user.setBirthDate(resultSet.getDate("birth_date"));
		user.setAddress(resultSet.getString("address"));
		user.setPhone(resultSet.getString("phone"));
		users.add(user);
	    }
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException();
	}
	freeConnection(connection, statement);
	return users;
    }

    @Override
    public User find(String email, String password) throws DaoException {
	User user = null;
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
	    statement = connection
		    .prepareStatement(sqlUserSelectByEmailPassword);
	    statement.setString(1, email);
	    statement.setString(2, password);
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be created.");
	    throw new DaoException();
	}
	try {
	    resultSet = statement.executeQuery();
	    LOGGER.debug("Statement was executed.");
	    if (resultSet.next()) {
		UserRole role;
		try {
		    role = UserRole.valueOf(resultSet.getString("role"));
		} catch (IllegalArgumentException e) {
		    LOGGER.warn("Unknown role: " + resultSet.getString("role"));
		    throw new DaoException();
		}
		switch (role) {
		case CUSTOMER:
		    Customer customer = new Customer();
		    customer.setCompany(resultSet.getString("company"));
		    customer.setPosition(resultSet.getString("position"));
		    user = customer;
		    break;
		default:
		    Employee employee = new Employee();
		    employee.setQualification(resultSet
			    .getString("qualification"));
		    user = employee;
		    break;
		}
		user.setId(resultSet.getInt("id"));
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getString("password"));
		user.setRole(role);
		user.setActive(resultSet.getBoolean("is_active"));
		user.setRegistrationDate(resultSet.getDate("registration_date"));
		user.setFirstName(resultSet.getString("first_name"));
		user.setLastName(resultSet.getString("last_name"));
		user.setBirthDate(resultSet.getDate("birth_date"));
		user.setAddress(resultSet.getString("address"));
		user.setPhone(resultSet.getString("phone"));
	    }
	} catch (SQLException e) {
	    LOGGER.warn("Statement cannot be executed.");
	    throw new DaoException();
	}
	freeConnection(connection, statement);
	return user;
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
