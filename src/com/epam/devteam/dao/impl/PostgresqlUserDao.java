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
import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.db.ConnectionPool;
import com.epam.devteam.db.ConnectionPoolException;
import com.epam.devteam.entity.User;

/**
 * @date Dec 15, 2013
 * @author Andrey Kovalskiy
 * 
 */
public class PostgresqlUserDao implements UserDao {
    private static final Logger LOGGER = Logger
	    .getLogger(PostgresqlUserDao.class);
    private ConnectionPool connectionPool;

    /**
     * Initializes a newly created {@code PostgresqlUserDao} object.
     */
    public PostgresqlUserDao() {
	super();
    }

    /**
     * Initializes a newly created {@code PostgresqlUserDao} object and
     * connection with the given connection value.
     * 
     * @param connection The connection to use to connect to the database.
     */
    public PostgresqlUserDao(ConnectionPool connectionPool) {
	this.connectionPool = connectionPool;
    }

    @Override
    public void createUser(User user) throws DaoException {
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    connection = connectionPool.takeConnection();
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection can not be taken.");
	    throw new DaoException();
	}
	try {
	    statement = connection
		    .prepareStatement("INSERT INTO users (id,email,password,firstname,lastname,patronymic,birthdate) VALUES (?,?,?,?,?,?,?)");
	    LOGGER.debug("Statement has been created.");
	} catch (SQLException e) {
	    LOGGER.warn("Statement can not be created.");
	    finish(connection, statement);
	    throw new DaoException();
	}

	try {
	    statement.setInt(1, 2);
	    statement.setString(2, "k@mail.ru");
	    statement.setString(3, "123456");
	    statement.setString(4, "alex");
	    statement.setString(5, "kim");
	    statement.setString(6, "hen");
	    statement.setDate(7, new Date(new java.util.Date().getTime()));
	    statement.execute();
	} catch (SQLException e) {
	    LOGGER.warn("User cannot be created.");
	}
	finish(connection, statement);
    }

    @Override
    public User findUser(Long id) throws DaoException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public User findUser(String email, String password) throws DaoException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void updateUser(User user) throws DaoException {
	// TODO Auto-generated method stub

    }

    @Override
    public void deleteUser(User user) throws DaoException {
	// TODO Auto-generated method stub

    }

    @Override
    public List<User> listUsers() throws DaoException {
	List<User> users = null;
	User user = null;
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	createUser(null);
	try {
	    connection = connectionPool.takeConnection();
	} catch (ConnectionPoolException e) {
	    LOGGER.warn("Connection can not be taken.");
	    throw new DaoException();
	}
	try {
	    statement = connection.createStatement();
	    LOGGER.debug("Statement has been created.");
	} catch (SQLException e) {
	    LOGGER.warn("Statement can not be created.");
	    finish(connection, statement);
	    throw new DaoException();
	}
	try {
	    resultSet = statement.executeQuery("SELECT * FROM users;");
	    users = new ArrayList<User>();
	    while (resultSet.next()) {
		user = new User();
		/*user.setId(resultSet.getLong("id"));
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getString("password"));
		user.setFirstname(resultSet.getString("firstname"));
		user.setLastname(resultSet.getString("lastname"));
		user.setPatronymic(resultSet.getString("patronymic"));
		user.setBirthdate(resultSet.getDate("birthdate"));*/
		users.add(user);
	    }
	} catch (SQLException e) {
	    LOGGER.warn("Query can not be executed.");
	    finish(connection, statement);
	    throw new DaoException();
	}
	finish(connection, statement);
	return users;
    }

    private void finish(Connection connection, Statement statement) {
	if (statement != null) {
	    try {
		statement.close();
		LOGGER.debug("Statement has been closed.");
	    } catch (SQLException e) {
		LOGGER.warn("Statement can not be closed.");
	    }
	}
	;
	connectionPool.returnConnection(connection);
    }
}
