/**
 * 
 */
package com.epam.devteam.dao;

import java.util.List;

import com.epam.devteam.entity.order.Order;
import com.epam.devteam.entity.order.OrderStatus;

/**
 * The <code>OrderDao</code> extends <code>Dao</code> interfaces and provides
 * additional methods to work with database and <code>Order</code> entities.
 * 
 * @date Jan 11, 2014
 * @author Andrey Kovalskiy
 */
public interface OrderDao extends Dao<Order> {

    /**
     * Is used to get orders from the database with paging. Method returns
     * orders that were created by customer with the given id.
     * 
     * @param id The customer id.
     * @param firstRow The row from where to start list orders.
     * @param rowNumber The number of orders to list.
     * @return The list of all orders in the database.
     * @throws DaoException If something fails at database level.
     */
    List<Order> listByCustomerId(int id, int firstRow, int row)
	    throws DaoException;

    /**
     * Is used to update order status.
     * 
     * @param id The order id to be updated.
     * @param status The status to set.
     * @throws DaoException If something fails.
     */
    void updateStatus(int id, OrderStatus status) throws DaoException;
    
}
