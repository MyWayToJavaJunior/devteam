/**
 * 
 */
package com.epam.devteam.dao;

import java.util.List;

/**
 * @date Jan 2, 2014
 * @author Andrey Kovalskiy
 * 
 */
public interface Dao<T> {

    /**
     * Is used to create the given object in the database.
     * 
     * @param object The object to create.
     * @throws DaoException If something fails at database level.
     */
    void create(T object) throws DaoException;

    /**
     * Is used to return an object in the database by the given id, otherwise
     * {@code null}.
     * 
     * @param id The id of the object to be returned.
     * @return The object from the database with required id, otherwise
     *         {@code null}.
     * @throws DaoException If something fails at database level.
     */
    T find(Integer id) throws DaoException;

    /**
     * Is used to update the given object.
     * 
     * @param object The object to update.
     * @throws DaoException If something fails at database level.
     */
    void update(T object) throws DaoException;

    /**
     * Is used to delete the given object from the database.
     * 
     * @param object The object to delete.
     * @throws DaoException If something fails at database level.
     */
    void delete(T object) throws DaoException;

    /**
     * Is used to get all of the objects from the database.
     * 
     * @return The list of all objects in the database.
     * @throws DaoException If something fails at database level.
     */
    List<T> list() throws DaoException;

}
