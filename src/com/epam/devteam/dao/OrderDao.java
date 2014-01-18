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
     * Is used to find all the orders of definite customer.
     * 
     * @param id The customer id.
     * @return The list of orders.
     */
    List<Order> listByCustomerId(int id) throws DaoException;

    /**
     * Is used to update order status.
     * 
     * @param id The order id to be updated.
     * @param status The status to set.
     * @throws DaoException If something fails.
     */
    void updateStatus(int id, OrderStatus status) throws DaoException;;
}
