/**
 * 
 */
package com.epam.devteam.dao;

import com.epam.devteam.entity.user.User;

/**
 * The <code>UserDao</code> interface extends <code>Dao</code> interface and
 * provides additional methods to work with <code>User</> entity and database.
 * 
 * @date Dec 15, 2014
 * @author Andrey Kovalskiy
 * @see com.epam.devteam.dao.Dao
 * @see com.epam.devteam.entity.User
 */
public interface UserDao extends Dao<User> {
    	
    
    /**
     * Is used to create and return id of a new object.
     * 
     * @param object The object to create.
     * @return Object id.
     * @throws DaoException If something fails at database level
     */
    int createWithIdReturn(User object) throws DaoException;
    
    /**
     * Is used to get user by email. Method returns null if there is no user in
     * database .
     * 
     * @param email The user email.
     * @return The user with the given email, null otherwise.
     * @throws DaoException If something fails during method performing.
     */
    User find(String email) throws DaoException;

    /**
     * Is used to get user with the given email and password. Method returns
     * null if there is no user in database .
     * 
     * @param email The user email.
     * @param password The user password.
     * @return The user, null otherwise.
     * @throws DaoException If something fails during method performing.
     */
    User find(String email, String password) throws DaoException;
    
    /**
     * Is used to set new status for a user wit the given id in database .
     * 
     * @param id The id of the user.
     * @param newStatus The status to be set.
     * @throws DaoException If something fails during method performing.
     */
    void updateActiveStatus(int id, boolean newStatus) throws DaoException;

    /**
     * Is used to set new password for a user wit the given id in database .
     * 
     * @param id The id of the user.
     * @param newPassword The new password to be set.
     * @throws DaoException If something fails during method performing.
     */
    void updatePassword(int id, String newPassword) throws DaoException;

}
