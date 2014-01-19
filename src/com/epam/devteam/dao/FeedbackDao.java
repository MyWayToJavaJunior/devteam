package com.epam.devteam.dao;

import com.epam.devteam.entity.feedback.Feedback;

/**
 * The <code>FeedbackDao</code> extends <code>Dao</code> interface and provides
 * additional methods to work with database and feedback entities.
 * 
 * @date Jan 20, 2014
 * @author Andrey Kovalskiy
 * 
 */
public interface FeedbackDao extends Dao<Feedback> {

    /**
     * 
     * Is used to find feedback by order id. if there is now feedback with
     * required id it returns null.
     * 
     * @param id The order id.
     * @return The feedback, null otherwise.
     * @throws DaoException If something fails during method performing.
     */
    Feedback findByOrderId(int id) throws DaoException;

}
