/**
 * 
 */
package com.epam.devteam.dao;

import java.util.List;

import com.epam.devteam.entity.User;

/**
 * @date Dec 15, 2013
 * @author Andrey Kovalskiy
 * 
 */
public interface UserDao {
	
	/**
	 * Is used to create the given user in the database. 
	 * @param user The user to create.
	 * @throws DaoException If something fails at database  level.
	 */
	void createUser(User user) throws DaoException;
	
	/**
	 * Is used to find a user in the database by the given id, otherwise {@code null}. 
	 * @param id The id of the user to be returned. 
	 * @return The user from the database with required id, otherwise {@code null}. 
	 * @throws DaoException If something fails at database level. 
	 */
	User findUser(Long id) throws DaoException;
	
	/**
	 * Is used to find a user in the database by email and password, otherwise {@code null}. 
	 * @param email The email of the user to be returned.
	 * @param password The password of the user to be returned.
	 * @return The user from the database with required email and password, otherwise {@code null}. 
	 * @throws DaoException If something fails at database level.
	 */
	User findUser(String email, String password) throws DaoException;
	
	/**
	 * Is used to update the given user.
	 * @param user The user to update.
	 * @throws DaoException If something fails at database level.
	 */
	void updateUser(User user) throws DaoException;
	
	/**
	 * Is used to delete the given user from the database.
	 * @param user The user to delete.
	 * @throws DaoException If something fails at database level.
	 */
	void deleteUser(User user) throws DaoException;
	
	/**
	 * Is used to get all of the users from the database.
	 * @return The list of all users in the database.
	 * @throws DaoException If something fails at database level.
	 */
	List<User> listUsers() throws DaoException;
	
}
