/**
 * 
 */
package com.epam.devteam.dao;

import com.epam.devteam.entity.User;


/**
 * @date Dec 15, 2013
 * @author Andrey Kovalskiy
 */
public interface UserDao extends Dao<User> {

    User find(String email, String password) throws DaoException;

}
