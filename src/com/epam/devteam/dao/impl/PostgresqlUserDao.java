/**
 * 
 */
package com.epam.devteam.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.entity.User;

/**
 * @date Dec 15, 2013
 * @author anjey
 * 
 */
public class PostgresqlUserDao implements UserDao {
    private static final Logger LOGGER = Logger
	    .getLogger(PostgresqlUserDao.class);
    private Connection connection;

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
    public PostgresqlUserDao(Connection connection) {
	this.connection = connection;
    }

    @Override
    public void createUser(User user) throws DaoException {
	// TODO Auto-generated method stub

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
	Statement statement = null;
	ResultSet resultSet = null;
	try {
	    statement = connection.createStatement();
	    LOGGER.debug("A statement was created");
	    resultSet = statement.executeQuery("SELECT * FROM users");
	    users = new ArrayList<User>();
	    while (resultSet.next()) {
		user = new User();
		user.setId(resultSet.getLong("id"));
		user.setPassword(resultSet.getString("password"));
		user.setFirstname(resultSet.getString("firstname"));
		user.setLastname(resultSet.getString("lastname"));
		user.setPatronymic(resultSet.getString("patronymic"));
		user.setBirthdate(resultSet.getDate("birthdate"));
		users.add(user);
	    }
	} catch (SQLException e) {
	    LOGGER.warn("Can't create a statement.");
	}
	return users;
    }
}
