/**
 * 
 */
package com.epam.devteam.dao;

import java.util.List;

/**
 * The <code></code> provides methods to work with database and required
 * entities. Every dao class should instantiate this interface.
 * 
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
     * Is used to return an object from the database by the given id, otherwise
     * {@code null}.
     * 
     * @param id The id of the object to be returned.
     * @return The object from the database with required id, otherwise
     *         {@code null}.
     * @throws DaoException If something fails at database level.
     */
    T find(int id) throws DaoException;

    /**
     * Is used to update the given object.
     * 
     * @param object The object to update.
     * @throws DaoException If something fails at database level.
     */
    void update(T object) throws DaoException;

    /**
     * Is used to delete the object with the given id from the database.
     * 
     * @param id The id of the object to be deleted.
     * @throws DaoException If something fails at database level.
     */
    void delete(int id) throws DaoException;

    /**
     * Is used to get all of the objects from the database.
     * 
     * @return The list of all objects in the database.
     * @throws DaoException If something fails at database level.
     */
    List<T> list() throws DaoException;

    /**
     * Is used to get objects from the database with paging.
     * 
     * @param firstRow The row from where to start list objects.
     * @param rowNumber The number of objects to list.
     * @return The list of objects in the database.
     * @throws DaoException If something fails at database level.
     */
    List<T> list(int firstRow, int rowNumber) throws DaoException;

}
