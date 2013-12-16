/**
 * 
 */
package com.epam.devteam.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.entity.User;

/**
 * @date Dec 15, 2013
 * @author anjey
 * 
 */
public class PostgresqlUserDao implements UserDao {

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
	// TODO Auto-generated method stub
	return null;
    }

}
